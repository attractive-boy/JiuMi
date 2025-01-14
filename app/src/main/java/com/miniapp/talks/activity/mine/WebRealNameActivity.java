package com.miniapp.talks.activity.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.just.agentweb.AgentWeb;
import com.miniapp.talks.R;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Constant;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 认证
 */
public class WebRealNameActivity extends MyBaseArmActivity {

//    @BindView(R.id.coupon_webview)
//    WebView mWebView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.ll)
    LinearLayout ll;

    private String url;
    private String title;

    @Inject
    CommonModel commonModel;

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
        return R.layout.activity_coupon_web;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        url = getIntent().getStringExtra("url");

        AgentWeb mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(ll, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
//                .defaultProgressBarColor() // 使用默认进度条颜色
//                .setReceivedTitleCallback(mCallback) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(url);
//        mAgentWeb.getUrlLoader().loadDataWithBaseURL(null,url,
//                "text/html",
//                "UTF-8",null);
        mAgentWeb.getUrlLoader().loadUrl(url);
    }

    @Override
    public void onResume() {
        super.onResume();
//        RxUtils.loading(commonModel.sfrz_query(String.valueOf(UserManager.getUser().getUserId())), this)
//                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(BaseBean baseBean) {
//                        finish();
//                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.RENZHENGCHENGGONG));
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                    }
//                });
    }
}
