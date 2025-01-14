package com.miniapp.talks.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.adapter.FriendAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.UserFriend;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Constant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 作者:sgm
 * 描述:朋友
 */
public class MessageFriendFragment extends MyBaseArmFragment {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;

    @Inject
    CommonModel commonModel;
    @BindView(R.id.no_data_image)
    ImageView noDataImage;
    @BindView(R.id.no_data_text)
    TextView noDataText;
    @BindView(R.id.no_data)
    LinearLayout noData;
    Unbinder unbinder;
    private int page = 1;

    private FriendAdapter friendAdapter;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_item_fans);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        friendAdapter = new FriendAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(friendAdapter);

        noDataImage.setImageResource(R.mipmap.no_friend);
        noDataText.setText("还没有好友哦~");

        loadData();

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            loadData();
        });
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            page++;
            loadData();
        });

        friendAdapter.setOnItemClickListener((adapter, view, position) -> {
//            Intent intent = new Intent(getActivity(), MessageActivity.class);
//            intent.putExtra("userId",friendAdapter.getData().get(position).getId());
//            ArmsUtils.startActivity(intent);
            RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.PRIVATE,
                    friendAdapter.getData().get(position).getId() + "",
                    friendAdapter.getData().get(position).getNickname());
        });

        friendAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int id = view.getId();
                if (id == R.id.ci_head) {
                    Intent intent = new Intent(getActivity(), MyPersonalCenterActivity.class);
                    intent.putExtra("sign", 1);
                    intent.putExtra("id", friendAdapter.getData().get(position).getId() + "");
                    ArmsUtils.startActivity(intent);
                }
            }
        });
    }

    private void loadData() {
        RxUtils.loading(commonModel.userFriend(String.valueOf(UserManager.getUser().getUserId()),
                "1", page + ""), this)
                .subscribe(new ErrorHandleSubscriber<UserFriend>(mErrorHandler) {
                    @Override
                    public void onNext(UserFriend userFriend) {
                        if (page == 1) {
                            refreshLayout.finishRefresh();
                            if (userFriend.getData().size() == 0 || userFriend.getData() == null) {
                                noData.setVisibility(View.VISIBLE);
                                refreshLayout.setVisibility(View.GONE);
                            } else {
                                noData.setVisibility(View.GONE);
                                refreshLayout.setVisibility(View.VISIBLE);
                                friendAdapter.setNewData(userFriend.getData());
                            }

                        } else {
                            friendAdapter.addData(userFriend.getData());
                            refreshLayout.finishLoadMore();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                        noData.setVisibility(View.GONE);
                        refreshLayout.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
//        String msg = event.getMsg();
        if ( Constant.SHUAXINPENGYOU.equals(tag)) {
            page = 1;
            loadData();
        }
    }
}
