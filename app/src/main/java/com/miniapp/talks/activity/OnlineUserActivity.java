package com.miniapp.talks.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.OnlineUserAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.OnlineUser;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class OnlineUserActivity extends MyBaseArmActivity {

    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    private static int mRoomId=0;


    public static void open(Context context,int roomId){
        mRoomId=roomId;
        context.startActivity(new Intent(context,OnlineUserActivity.class));
    }


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
        return R.layout.activity_online_user;
    }

    @Inject
    CommonModel commonModel;

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ivBack.setOnClickListener(v -> finish());
        StatusBarUtil.setTranslucentForImageView(this, 0, llRoot);
        RxUtils.loading(commonModel.getOnlineUser(mRoomId, UserManager.getUser().getToken()), this)
                .subscribe(new ErrorHandleSubscriber<OnlineUser>(mErrorHandler) {
                    @Override
                    public void onNext(OnlineUser baseBean) {
                        runOnUiThread(() -> {
                            if(baseBean.getCode()==1){
                                tvTitle.setText("在线人数:"+baseBean.getData().getData().size());
                                onlineUserAdapter=new OnlineUserAdapter(OnlineUserActivity.this,baseBean.getData().getData());
                                recycler.setLayoutManager(new LinearLayoutManager(OnlineUserActivity.this,LinearLayoutManager.VERTICAL,false));
                                recycler.setAdapter(onlineUserAdapter);
                            }
                        });
                    }
                });
    }

    private OnlineUserAdapter onlineUserAdapter;

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void launchActivity(@NonNull Intent intent) {

    }

    @Override
    public void killMyself() {

    }
}
