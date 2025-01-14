package com.miniapp.talks.activity.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.BaseWebActivity;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.MoneyBean;
import com.miniapp.talks.bean.PayBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URLEncoder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class RealNameActivity extends MyBaseArmActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editNumber)
    EditText editNumber;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;

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
        return R.layout.activity_real_name;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


    @OnClick({R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                String name = editName.getText().toString();
                String number = editNumber.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    toast("请输入姓名！");
                } else if (TextUtils.isEmpty(number)) {
                    toast("请输入身份证号！");
                } else {
                    RxUtils.loading(commonModel.sfrz_start(String.valueOf(UserManager.getUser().getUserId()),
                            name, number), this)
                            .subscribe(new ErrorHandleSubscriber<PayBean>(mErrorHandler) {
                                @Override
                                public void onNext(PayBean baseBean) {
//                                    Intent intent = new Intent(RealNameActivity.this, WebRealNameActivity.class);
//                                    intent.putExtra("url",baseBean.getData());
//                                    ArmsUtils.startActivity(intent);
//                                    try {
//                                        String intentFullUrl = baseBean.getData();
//                                        Intent intent = Intent.parseUri(intentFullUrl, Intent.URI_INTENT_SCHEME);
//                                        startActivity(intent);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }

                                    Intent action = new Intent(Intent.ACTION_VIEW);
                                    StringBuilder builder = new StringBuilder();
                                    // 这里使用固定appid 20000067
                                    builder.append("alipays://platformapi/startapp?appId=20000067&url=");
                                    builder.append(URLEncoder.encode(baseBean.getData()));
                                    action.setData(Uri.parse(builder.toString()));
                                    try {
                                        startActivity(action);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        showToast("暂未找到支付宝");
                                    }
                                }
                            });
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (Constant.RENZHENGCHENGGONG.equals(tag)) {
            finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        RxUtils.loading(commonModel.sfrz_query(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        finish();
                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.RENZHENGCHENGGONG));
                    }

                    @Override
                    public void onError(Throwable t) {
                    }
                });
    }
}
