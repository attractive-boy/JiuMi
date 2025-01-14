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

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.adapter.MyConcernAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.PullRefreshBean;
import com.miniapp.talks.bean.UserFriend;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.DealRefreshHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 作者:sgm
 * 描述:粉丝，关注
 */
public class MessageFansFragment extends MyBaseArmFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.no_data_image)
    ImageView noDataImage;
    @BindView(R.id.no_data_text)
    TextView noDataText;
    @BindView(R.id.no_data)
    LinearLayout noData;

    @Inject
    CommonModel commonModel;


    private MyConcernAdapter friendAdapter;
    private List<UserFriend.DataBean> mDataList = new ArrayList<>();
    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();

    private int tag;
    private String type = "2";

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
        tag = getArguments().getInt("id");
        if (tag == 0) {
            type = "2";
        } else {
            type = "3";
        }

        friendAdapter = new MyConcernAdapter(R.layout.my_concern_item, mDataList);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(friendAdapter);

        loadData();

        refreshLayout.setOnRefreshListener(refreshlayout -> {
//            page = 1;
//            loadData();
            mPullRefreshBean.setRefresh(mPullRefreshBean, refreshLayout);//下拉刷新时 的处理
            loadData();
        });
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
//            page++;
//            loadData();
            mPullRefreshBean.setLoardMore(mPullRefreshBean, refreshLayout);
            loadData();
        });

        friendAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.btn_ok:
                    fllow(String.valueOf(friendAdapter.getData().get(position).getId()), position);
                    break;
                case R.id.btn_no_ok:
                    cancelFllow(String.valueOf(friendAdapter.getData().get(position).getId()), position);
                    break;
                case R.id.ci_head:
                    Intent intent = new Intent(getActivity(), MyPersonalCenterActivity.class);
                    intent.putExtra("sign", 1);
                    intent.putExtra("id", String.valueOf(friendAdapter.getData().get(position).getId()));
                    ArmsUtils.startActivity(intent);

                    break;
            }
        });
        friendAdapter.setOnItemClickListener((adapter, view, position) -> {
            RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.PRIVATE,
                    friendAdapter.getData().get(position).getId() + "",
                    friendAdapter.getData().get(position).getNickname());
    });
    }

    private void loadData() {
        RxUtils.loading(commonModel.userFriend(String.valueOf(UserManager.getUser().getUserId()),
                type, mPullRefreshBean.pageIndex + ""), this)
                .subscribe(new ErrorHandleSubscriber<UserFriend>(mErrorHandler) {
                    @Override
                    public void onNext(UserFriend userFriend) {
                        List<UserFriend.DataBean> list = userFriend.getData();
                        new DealRefreshHelper<UserFriend.DataBean>().dealDataToUI(refreshLayout, friendAdapter, noData, list, mDataList, mPullRefreshBean);
//                        if (page == 1) {
//                            friendAdapter.setNewData(userFriend.getData());
//                            refreshLayout.finishRefresh();
//                        } else {
//                            friendAdapter.addData(userFriend.getData());
//                            refreshLayout.finishLoadMore();
//                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
//                        refreshLayout.finishRefresh();
//                        refreshLayout.finishLoadMore();
                        new DealRefreshHelper<UserFriend.DataBean>().dealDataToUI(refreshLayout, friendAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }


    /**
     * 关注
     */
    private void fllow(String id, int pos) {
        RxUtils.loading(commonModel.follow(String.valueOf(UserManager.getUser().getUserId()), id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        showToast("关注成功");
                        friendAdapter.notifyItemChanged(pos, "follow");
                    }
                });
    }


    /**
     * 取消关注
     */
    private void cancelFllow(String id, int pos) {
        RxUtils.loading(commonModel.cancel_follow(String.valueOf(UserManager.getUser().getUserId()), id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        showToast("取消成功");
                        if (tag == 0) {
                            friendAdapter.remove(pos);
                            friendAdapter.notifyDataSetChanged();
                        } else {
                            friendAdapter.notifyItemChanged(pos, "cancelFollow");
                        }

//                        page = 0;
//                        loadData();
                    }
                });
    }


    @Override
    public void setData(@Nullable Object data) {

    }

    public static MessageFansFragment getInstance(int tag) {
        MessageFansFragment fragment = new MessageFansFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", tag);
        fragment.setArguments(bundle);
        return fragment;
    }

    //    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void receiveMsg(FirstEvent event) {
//        String tag = event.getTag();
//        if (SHUAXINGUANZHU.equals(tag)) {
//            if(id == 0){
//                page = 0;
//                loadData();
//            }
//        }
//    }
}
