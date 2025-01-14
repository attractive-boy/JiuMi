package com.miniapp.talks.activity.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
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
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.CodeBean;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.VerificationUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 修改密码页面
 * 老王
 */
public class ModifyPsActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.phoneText)
    TextView phoneText;
    @BindView(R.id.coadsend)
    TextView coadsend;
    @BindView(R.id.coadedit)
    EditText coadedit;
    @BindView(R.id.newpsedit)
    EditText newpsedit;
    @BindView(R.id.bbb)
    CardView bbb;
    @BindView(R.id.surebtn)
    Button surebtn;


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
        return R.layout.activity_modify;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        phoneText.setText(UserManager.getUser().getPhone());
        coadedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String newPsStr = newpsedit.getText().toString().replace(" ", "");
                if (TextUtils.isEmpty(s.toString().replace(" ", "")) || TextUtils.isEmpty(newPsStr)) {
                    surebtn.setEnabled(false);
                } else {
                    surebtn.setEnabled(true);
                }
            }
        });
        newpsedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String coadStr = coadedit.getText().toString().replace(" ", "");
                if (TextUtils.isEmpty(s.toString().replace(" ", "")) || TextUtils.isEmpty(coadStr)) {
                    surebtn.setEnabled(false);
                } else {
                    surebtn.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("修改密码", true);
    }

    @OnClick({R.id.coadsend, R.id.surebtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.coadsend:
                String phone1 = phoneText.getText().toString().replace(" ", "");
                if (TextUtils.isEmpty(phone1)) {
                    ArmsUtils.snackbarText("账号不能为空");
                } else if (!VerificationUtils.VildateMobile(phone1)) {
                    ArmsUtils.snackbarText("账号输入不合法");
                } else {
                    if (TextUtils.equals(coadsend.getText().toString(), "发送验证码")
                            || TextUtils.equals(coadsend.getText().toString(), "重新发送")) {
                        RxUtils.loading(commonModel.verification(phone1, ""), this)
                                .subscribe(new ErrorHandleSubscriber<CodeBean>(mErrorHandler) {
                                    @Override
                                    public void onNext(CodeBean codeBean) {
                                        showCode();
                                    }

                                    @Override
                                    public void onError(Throwable t) {
                                        super.onError(t);
                                        showCode();
                                    }
                                });
                    }
                }
                break;
            case R.id.surebtn:
                String phoneCode = coadedit.getText().toString().replace(" ", ""); //验证码
                String phone2 = phoneText.getText().toString().replace(" ", ""); //手机号
                String password = newpsedit.getText().toString(); //新密码
                if (TextUtils.isEmpty(phone2)) {
                    ArmsUtils.makeText(this, "账号不能为空");
                } else if (TextUtils.isEmpty(phoneCode)) {
                    ArmsUtils.snackbarText("手机验证码不能为空");
                } else if (TextUtils.isEmpty(password)) {
                    ArmsUtils.snackbarText("密码不能为空");
                } else if (!VerificationUtils.VildateMobile(phone2)) {
                    ArmsUtils.snackbarText("账号输入不合法");
                } else {
                    if (password.length() < 6 || password.length() > 20) {
                        ArmsUtils.snackbarText("密码必须大于6位，小于20位！");
                    } else {
                        RxUtils.loading(commonModel.ForGetPWD(phone2, phoneCode, password), this)
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
                int i = (int) (millisUntilFinished / 1000);
                coadsend.setText(i + "s");
            }

            @Override
            public void onFinish() {
                coadsend.setText("重新发送");
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
}
