package com.miniapp.talks.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebViewClient;
import com.miniapp.talks.R;

import butterknife.BindView;

/**
 * 公有的webview
 */
public class BaseWebActivity extends MyBaseArmActivity {

    //    @BindView(R.id.coupon_webview)
//    WebView mWebView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.ll)
    LinearLayout ll;

    private String url;
    private String title;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_coupon_web;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
//        toolbarTitle.setText(title);
//        WebSettings ws = mWebView.getSettings();
//        ws.setBuiltInZoomControls(false);// 隐藏缩放按钮
//        ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
//        ws.setJavaScriptEnabled(true);
//        ws.setDomStorageEnabled(true);
//        ws.setSupportMultipleWindows(true);// 新加
//        mWebView.setWebChromeClient(new WebChromeClient());
//        //如果不设置WebViewClient，请求会跳转系统浏览器
//        mWebView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//        });
//        mWebView.loadDataWithBaseURL(null,url, "text/html", "UTF-8",null);

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
//        mAgentWeb.getUrlLoader().loadUrl(url);
//        mAgentWeb.getWebCreator().getWebView().setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarTitle.setText("详情");
    }
}
