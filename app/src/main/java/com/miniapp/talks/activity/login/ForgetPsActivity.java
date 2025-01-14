package com.miniapp.talks.activity.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.CodeBean;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.VerificationUtils;
import com.miniapp.talks.view.ShapeTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 忘记密码
 */
public class ForgetPsActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;

    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rightTitle)
    TextView rightTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_phone)
    TextView txtPhone;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.textSend)
    TextView textSend;
    @BindView(R.id.edt_login_name)
    EditText edtLoginName;
    @BindView(R.id.edt_login_pw)
    EditText edtLoginPw;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;

    private CountDownTimer timer;

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
        return R.layout.activity_forget;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        edtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String logNameStr = edtLoginName.getText().toString().replace(" ", "").trim();
                String logPwStr = edtLoginPw.getText().toString().replace(" ", "").trim();
                if (TextUtils.isEmpty(s.toString().replace(" ", "").trim()) || TextUtils.isEmpty(logNameStr) || TextUtils.isEmpty(logPwStr)) {
                    btnOk.setEnabled(false);
                } else {
                    btnOk.setEnabled(true);
                }
            }
        });
        edtLoginName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String logCoadStr = edtCode.getText().toString().replace(" ", "").trim();
                String logPwStr = edtLoginPw.getText().toString().replace(" ", "").trim();
                if (TextUtils.isEmpty(s.toString().replace(" ", "").trim()) || TextUtils.isEmpty(logCoadStr) || TextUtils.isEmpty(logPwStr)) {
                    btnOk.setEnabled(false);
                } else {
                    btnOk.setEnabled(true);
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
                String logCoadStr = edtCode.getText().toString().replace(" ", "").trim();
                String logNameStr = edtLoginName.getText().toString().replace(" ", "").trim();
                if (TextUtils.isEmpty(s.toString().replace(" ", "").trim()) || TextUtils.isEmpty(logCoadStr) || TextUtils.isEmpty(logNameStr)) {
                    btnOk.setEnabled(false);
                } else {
                    btnOk.setEnabled(true);
                }
            }
        });
    }

    @OnClick({R.id.textSend, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textSend:
                String phone1 = edtCode.getText().toString().replace(" ", "");
                if (TextUtils.isEmpty(phone1)) {
                    ArmsUtils.snackbarText("账号不能为空");
                } else if (!VerificationUtils.VildateMobile(phone1)) {
                    ArmsUtils.snackbarText("账号输入不合法");
                } else {
                    if (TextUtils.equals(textSend.getText().toString(), "发送验证码")
                            || TextUtils.equals(textSend.getText().toString(), "重新发送")) {
                        RxUtils.loading(commonModel.verification(phone1, ""), this)
                                .subscribe(new ErrorHandleSubscriber<CodeBean>(mErrorHandler) {
                                    @Override
                                    public void onNext(CodeBean codeBean) {
                                        showCode();
                                    }

                                    @Override
                                    public void onError(Throwable t) {
                                        super.onError(t);
//                                        showCode();
                                    }
                                });
                    }
                }
                break;
            case R.id.btn_ok:
                String phoneCode = edtLoginName.getText().toString().replace(" ", ""); //验证码
                String phone = edtCode.getText().toString().replace(" ", ""); //手机号
                String password = edtLoginPw.getText().toString(); //新密码
                if (TextUtils.isEmpty(phone)) {
                    ArmsUtils.makeText(this, "账号不能为空");
                } else if (TextUtils.isEmpty(phoneCode)) {
                    ArmsUtils.snackbarText("手机验证码不能为空");
                } else if (TextUtils.isEmpty(password)) {
                    ArmsUtils.snackbarText("密码不能为空");
                } else if (!VerificationUtils.VildateMobile(phone)) {
                    ArmsUtils.snackbarText("账号输入不合法");
                } else {
                    if (password.length() < 6 || password.length() > 20) {
                        ArmsUtils.snackbarText("密码必须大于6位，小于20位！");
                    } else {
                        RxUtils.loading(commonModel.ForGetPWD(phone, phoneCode, password), this)
                                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                                    @Override
                                    public void onNext(CommentBean commentBean) {
                                        toast(commentBean.getMessage());
                                        finish();
                                    }
                                });
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
                try {
                    int i = (int) (millisUntilFinished / 1000);
                    textSend.setText(i + "s");
                }catch (Exception e){
                    e.printStackTrace();
                }

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
        if(timer!=null){
            timer.cancel();
            timer = null;
        }
    }
}
