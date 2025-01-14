package com.miniapp.talks.activity.login;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.MainActivity;
import com.miniapp.talks.activity.message.LiaoBaExtensionModule;
import com.miniapp.talks.app.Api;
import com.miniapp.talks.app.AppLifecyclesImpl;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.CodeBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.Login;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.OtherBean;
import com.miniapp.talks.bean.Register;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.BaseUtils;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.utils.VerificationUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.TextMessageItemProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.REGISTER;

public class BingPhoneActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;

    @BindView(R.id.txt_phone)
    TextView txtPhone;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.textSend)
    TextView textSend;
    @BindView(R.id.edt_login_name)
    EditText edtLoginName;
    @BindView(R.id.surebtn)
    Button surebtn;
    @BindView(R.id.edt_login_pw)
    EditText edtLoginPw;

    private CountDownTimer timer;
    private String uid, type, phoneStr, coadStr, password, sex, nowDate, nickName, headUrl;

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
        return R.layout.activity_bing_phone;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        nowDate = "";
        uid = getIntent().getStringExtra("openid");
        LogUtils.debugInfo("====uid", uid);
        type = getIntent().getStringExtra("type");
        sex = getIntent().getStringExtra("gender");
        nickName = getIntent().getStringExtra("nackName");
        headUrl = getIntent().getStringExtra("iconurl");
        edtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String logNameStr = edtLoginName.getText().toString().replace(" ", "");
                String logPwStr = edtLoginPw.getText().toString().replace(" ", "");
                if (TextUtils.isEmpty(s.toString().replace(" ", "")) || TextUtils.isEmpty(logNameStr) || TextUtils.isEmpty(logPwStr)) {
                    surebtn.setEnabled(false);
                } else {
                    surebtn.setEnabled(true);
                }
            }
        });
        edtLoginName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String logCodeStr = edtCode.getText().toString().replace(" ", "");
                String logPwStr = edtLoginPw.getText().toString().replace(" ", "");
                if (TextUtils.isEmpty(s.toString().replace(" ", "")) || TextUtils.isEmpty(logCodeStr) || TextUtils.isEmpty(logPwStr)) {
                    surebtn.setEnabled(false);
                } else {
                    surebtn.setEnabled(true);
                }
            }
        });
        edtLoginPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String logCodeStr = edtCode.getText().toString().replace(" ", "");
                String logNameStr = edtLoginName.getText().toString().replace(" ", "");
                if (TextUtils.isEmpty(s.toString().replace(" ", "")) || TextUtils.isEmpty(logCodeStr) || TextUtils.isEmpty(logNameStr)) {
                    surebtn.setEnabled(false);
                } else {
                    surebtn.setEnabled(true);
                }
            }
        });
    }

    @OnClick({R.id.textSend, R.id.surebtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textSend:
                String phone = edtCode.getText().toString().replace(" ", "");
                if (TextUtils.isEmpty(phone)) {
                    ArmsUtils.snackbarText("账号不能为空");
                } else if (!VerificationUtils.VildateMobile(phone)) {
                    ArmsUtils.snackbarText("账号输入不合法");
                } else {
                    if (TextUtils.equals(textSend.getText().toString(), "发送验证码")
                            || TextUtils.equals(textSend.getText().toString(), "重新发送")) {
                        RxUtils.loading(commonModel.verification(phone, "reg"), this)
                                .subscribe(new ErrorHandleSubscriber<CodeBean>(mErrorHandler) {
                                    @Override
                                    public void onNext(CodeBean codeBean) {
                                        showCode();
                                    }

                                    @Override
                                    public void onError(Throwable t) {
                                        super.onError(t);
                                    }
                                });
                    }
                }
                break;
            case R.id.surebtn:
                phoneStr = edtCode.getText().toString().replace(" ", "");
                coadStr = edtLoginName.getText().toString().replace(" ", "");
                password = edtLoginPw.getText().toString().replace(" ", "");
                if (TextUtils.isEmpty(phoneStr)) {
                    ArmsUtils.makeText(this, "账号不能为空");
                } else if (TextUtils.isEmpty(coadStr)) {
                    ArmsUtils.snackbarText("手机验证码不能为空");
                } else if (!VerificationUtils.VildateMobile(phoneStr)) {
                    ArmsUtils.snackbarText("账号输入不合法");
                } else if (TextUtils.isEmpty(password)) {
                    ArmsUtils.snackbarText("密码不能为空");
                } else {
                    if (password.length() < 6 || password.length() > 20) {
                        ArmsUtils.snackbarText("密码必须大于6位，小于20位！");
                    } else {

                        regOther();

                    }
                }
                break;
        }
    }

    public void showCode() {
        ArmsUtils.snackbarText("发送成功！");
        timer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int i = (int) (millisUntilFinished / 1000);
                textSend.setText(i + "s");
            }

            @Override
            public void onFinish() {
                textSend.setText("重新发送");
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    private void regOther() {
        RxUtils.loading(commonModel.registerOther(type, uid, phoneStr, sex, nowDate, nickName, headUrl, password, "android", Api.CHANNEL,coadStr), this)
                .subscribe(new ErrorHandleSubscriber<Login>(mErrorHandler) {
                    @Override
                    public void onNext(Login login) {

                        if (login.getCode() == 1) {
                            initRooIm(login);
                            LoginData loginData = new LoginData();
                            loginData.setHeadimgurl(login.getData().getHeadimgurl());
                            loginData.setNickname(login.getData().getNickname());
                            loginData.setUserId(login.getData().getId());
                            loginData.setPhone(login.getData().getPhone());
                            loginData.setRy_token(login.getData().getRy_token());
                            loginData.setToken(login.getData().getToken());
//                                    if(TextUtils.isEmpty(userInfo.getData().getProvince())){
//                                        loginData.setPr(userInfo.getData().getProvince());
//                                    }
//                                    if(TextUtils.isEmpty(userInfo.getData().getCountry())){
//                                        loginData.set(userInfo.getData().getCountry());
//                                    }
                            LitePal.deleteAll(LoginData.class);
                            loginData.save();//litepal数据库，不能随便改LoginData数据
                            UserManager.initData();//存储完，初始化
                            EventBus.getDefault().post(new FirstEvent("指定发送", Constant.LOGIN));  //跳到主页面
                            ArmsUtils.startActivity(MainActivity.class);
                            finish();
                        }
                    }
                });
    }


    /**
     * 初始化融云
     *
     * @param userInfo
     */
    private void initRooIm(Login userInfo) {
        RongIM.connect(userInfo.getData().getRy_token(), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                LogUtils.debugInfo("TAG", "====token错误");
            }

            @Override
            public void onSuccess(String userid) {
                LogUtils.debugInfo("TAG", "--onSuccess" + userid);


                UserInfo user = new UserInfo(userid,
                        userInfo.getData().getNickname(), Uri.parse(userInfo.getData().getHeadimgurl()));
                RongIM.getInstance().setCurrentUserInfo(user);
                RongIM.getInstance().setMessageAttachedUserInfo(true);

                registerExtensionPlugin();

                RongIM.registerMessageTemplate(new MyTextMessageItemProvider());

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.debugInfo("TAG", "--Error" + errorCode);
            }
        });
    }

    /**
     * 删除扩展区域
     */
    private void registerExtensionPlugin() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new LiaoBaExtensionModule());
            }
        }
    }

    /**
     * 聊天字体颜色
     */
    @ProviderTag(messageContent = TextMessage.class, showReadState = true)
    public class MyTextMessageItemProvider extends TextMessageItemProvider {

        @Override
        public void bindView(View v, int position, TextMessage content, UIMessage data) {
            super.bindView(v, position, content, data);
            TextView textView = (TextView) v;
            if (data.getMessageDirection() == Message.MessageDirection.SEND) {
                textView.setTextColor(Color.WHITE);
            } else {
                textView.setTextColor(Color.BLACK);
            }
        }
    }
}
