package com.miniapp.talks.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.login.LoginActivity;
import com.miniapp.talks.activity.login.ModifyPsActivity;
import com.miniapp.talks.activity.my.BlackListActivity;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.popup.PuTongWindow;
import com.miniapp.talks.utils.CacheDataManager;
import com.miniapp.talks.utils.Constant;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * 设置页面
 * 老王
 */
public class SetActivity extends MyBaseArmActivity {
    @BindView(R.id.modify_pwd)
    RelativeLayout modifyPwd;
    @BindView(R.id.blacklist)
    RelativeLayout blacklist;
    @BindView(R.id.protocol)
    RelativeLayout protocol;
    @BindView(R.id.img_one)
    ImageView imgOne;
    @BindView(R.id.eliminate)
    RelativeLayout eliminate;
    @BindView(R.id.sign_out)
    RelativeLayout signOut;
    @BindView(R.id.clear_huancun)
    TextView clearHuancun;
    @BindView(R.id.shezhi)
    LinearLayout shezhi;

    private String cacheSize;


//    @BindView(R.id.btn_layout)
//    ShapeTextView btnLayout;

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
        return R.layout.activity_set;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getTotalCacheSize();
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("设置", true);
    }

    @OnClick({R.id.modify_pwd, R.id.blacklist, R.id.protocol, R.id.eliminate, R.id.sign_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.modify_pwd: //跳转修改密码页面
                ArmsUtils.startActivity(ModifyPsActivity.class);
                break;
            case R.id.blacklist: //跳转黑名单页面
                ArmsUtils.startActivity(BlackListActivity.class);
                break;
            case R.id.protocol: //跳转平台协议页面
                ArmsUtils.startActivity(AgreementActivity.class);
                break;
            case R.id.eliminate: //清除缓存
                PuTongWindow puTongWindow = new PuTongWindow(this);
                puTongWindow.showAtLocation(shezhi, Gravity.CENTER, 0, 0);
                puTongWindow.getTitText().setText("确认清除全部缓存吗");
                puTongWindow.getCancel().setOnClickListener(v -> {
                    puTongWindow.dismiss();
                });
                puTongWindow.getSure().setOnClickListener(v -> {
                    if (clearHuancun.getText().equals("0k")) {
                        toast("没有缓存可以清除");
                        return;
                    }
                    //在子线程执行删除缓存
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            CacheDataManager.clearAllCache(SetActivity.this);
                            //在主线程执行更新UI操作
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    toast("清除成功");
                                    puTongWindow.dismiss();
                                    getTotalCacheSize();
                                }
                            });
                        }
                    }).start();
                });
                break;
            case R.id.sign_out: //退出登录
                PuTongWindow puTongWindow1 = new PuTongWindow(this);
                puTongWindow1.showAtLocation(shezhi, Gravity.CENTER, 0, 0);
                puTongWindow1.getTitText().setText("确定要退出吗");
                puTongWindow1.getCancel().setOnClickListener(v -> {
                    puTongWindow1.dismiss();
                });
                puTongWindow1.getSure().setOnClickListener(v -> {
                    RongIM.getInstance().logout();
                    RongIM.getInstance().clearConversations(new RongIMClient.UploadMediaCallback() {
                        @Override
                        public void onProgress(Message message, int i) {

                        }

                        @Override
                        public void onError(Message message, RongIMClient.ErrorCode errorCode) {

                        }

                        @Override
                        public void onSuccess(Message message) {

                        }
                    });
                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.LOGOUT));
                    UserManager.layout();
                    ArmsUtils.startActivity(LoginActivity.class);
                    puTongWindow1.dismiss();
                    finish();
                    if (AdminHomeActivity.isStart) {
                        AdminHomeActivity.isStart = false;
                        AdminHomeActivity.mContext.finish();
                    }
                });

                break;
        }
    }

    private void getTotalCacheSize() {
        try {
            //这个操作要放在try-catch当中
            cacheSize = CacheDataManager.getTotalCacheSize(SetActivity.this);
            clearHuancun.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
