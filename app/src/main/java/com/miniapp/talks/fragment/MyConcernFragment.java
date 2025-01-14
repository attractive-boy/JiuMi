package com.miniapp.talks.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.FansAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.UserFriend;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 我的关注（tag=0 我的关注，tag=1 我的粉丝）
 */
public class MyConcernFragment extends MyBaseArmFragment {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private int id;
    private String type = "2";

    private FansAdapter friendAdapter;
    private int page = 0;

    public static MyConcernFragment getInstance(int tag) {
        MyConcernFragment fragment = new MyConcernFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", tag);
        fragment.setArguments(bundle);
        return fragment;
    }

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
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_my_concern);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        id = getArguments().getInt("tag");
        if (id == 0) {
            type = "2";
        } else {
            type = "3";
        }
        friendAdapter = new FansAdapter(id);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(friendAdapter);

        loadData();

        smartRefreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 0;
            loadData();
        });
        smartRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
            page ++;
            loadData();
        });

        friendAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case R.id.btn_ok:
                    if(TextUtils.equals("2",type)){
                        cancelFllow(String.valueOf(friendAdapter.getData().get(position).getId()));
                    }else {
                        fllow(String.valueOf(friendAdapter.getData().get(position).getId()));
                    }
                    break;
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    private void loadData() {
        RxUtils.loading(commonModel.userFriend(String.valueOf(UserManager.getUser().getUserId()),
                type,page + ""), this)
                .subscribe(new ErrorHandleSubscriber<UserFriend>(mErrorHandler) {
                    @Override
                    public void onNext(UserFriend userFriend) {
                        if(page == 0){
                            friendAdapter.setNewData(userFriend.getData());
                            smartRefreshLayout.finishRefresh();
                        }else {
                            friendAdapter.addData(userFriend.getData());
                            smartRefreshLayout.finishLoadMore();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }

    /**
     * 关注
     */
    private void fllow(String id) {
        RxUtils.loading(commonModel.follow(String.valueOf(UserManager.getUser().getUserId()), id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        showToast("关注成功");
                    }
                });
    }


    /**
     * 取消关注
     */
    private void cancelFllow(String id) {
        RxUtils.loading(commonModel.cancel_follow(String.valueOf(UserManager.getUser().getUserId()), id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        showToast("取消成功");
                        page = 0;
                        loadData();
                    }
                });
    }
}
