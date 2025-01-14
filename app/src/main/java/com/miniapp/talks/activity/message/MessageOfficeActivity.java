package com.miniapp.talks.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.FriendAdapter;
import com.miniapp.talks.adapter.OfficeListAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.BaseWebActivity;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.OffiMessageBean;
import com.miniapp.talks.bean.UserFriend;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Constant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class MessageOfficeActivity extends MyBaseArmActivity {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;

    @Inject
    CommonModel commonModel;
    private int page = 1;

    private OfficeListAdapter officeListAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_message_office;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        officeListAdapter = new OfficeListAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(officeListAdapter);

        loadData();

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            loadData();
        });

//        setToolbarRightText("清空消息", v -> {
//            if(officeListAdapter.getData() == null || officeListAdapter.getData().size()==0){
//                showToast("暂无消息");
//                return;
//            }
//            RxUtils.loading(commonModel.clear_message(String.valueOf(UserManager.getUser().getUserId())), this)
//                    .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
//                        @Override
//                        public void onNext(BaseBean userFriend) {
//                            loadData();
//                        }
//                    });
//        },R.color.textcolor);

        officeListAdapter.setOnItemClickListener((adapter, view, position) -> {
            if(!TextUtils.isEmpty(officeListAdapter.getData().get(position).getUrl())){
                Intent intent = new Intent(this, BaseWebActivity.class);
                intent.putExtra("url", officeListAdapter.getData().get(position).getUrl());
                intent.putExtra("name", "");
                ArmsUtils.startActivity(intent);
            }
        });
    }

    private void loadData() {
        RxUtils.loading(commonModel.official_message(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<OffiMessageBean>(mErrorHandler) {
                    @Override
                    public void onNext(OffiMessageBean userFriend) {
                        officeListAdapter.setNewData(userFriend.getData());
                        refreshLayout.finishRefresh();

                        Intent intent = new Intent();
                        intent.setAction("has_read_office");
                        sendBroadcast(intent);
//                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.SHUAXINGUANFANGXIAOXI));
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });
    }

}
