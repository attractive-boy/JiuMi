package com.miniapp.talks.activity.login;

import android.content.Intent;
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
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.Register;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.SharedPreferencesUtils;
import com.miniapp.talks.utils.VerificationUtils;
import com.miniapp.talks.view.ShapeTextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.REGISTER;

public class RegisterActivity extends MyBaseArmActivity {

    public static final String tag = "RegisterActivity";
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

    @Inject
    CommonModel commonModel;
    @BindView(R.id.one_line)
    View oneLine;
    @BindView(R.id.two_line)
    View twoLine;
    @BindView(R.id.three_line)
    View threeLine;
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
        return R.layout.activity_register;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        SharedPreferencesUtils.setParam(this, "isFirstIn", false);
        edtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String nameStr = edtLoginName.getText().toString().replace(" ", "").trim();
                String pwStr = edtLoginPw.getText().toString().replace(" ", "").trim();
                if (TextUtils.isEmpty(s.toString()) || TextUtils.isEmpty(nameStr) || TextUtils.isEmpty(pwStr)) {
                    btnOk.setEnabled(false);
                } else {
                    btnOk.setEnabled(true);
                }
                if (TextUtils.isEmpty(s.toString().replace(" ", ""))) {
                    twoLine.setBackgroundColor(getResources().getColor(R.color.line));
                } else {
                    twoLine.setBackgroundColor(getResources().getColor(R.color.font_ff3e6d));
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
                String codaStr = edtCode.getText().toString().replace(" ", "").trim();
                String nameStr = edtLoginName.getText().toString().replace(" ", "").trim();
                if (TextUtils.isEmpty(s.toString()) || TextUtils.isEmpty(codaStr) || TextUtils.isEmpty(nameStr)) {
                    btnOk.setEnabled(false);
                } else {
                    btnOk.setEnabled(true);
                }
                if (TextUtils.isEmpty(s.toString().replace(" ", ""))) {
                    threeLine.setBackgroundColor(getResources().getColor(R.color.line));
                } else {
                    threeLine.setBackgroundColor(getResources().getColor(R.color.font_ff3e6d));
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
                String codaStr = edtCode.getText().toString().replace(" ", "").trim();
                String pwStr = edtLoginPw.getText().toString().replace(" ", "").trim();
                if (TextUtils.isEmpty(s.toString()) || TextUtils.isEmpty(codaStr) || TextUtils.isEmpty(pwStr)) {
                    btnOk.setEnabled(false);
                } else {
                    btnOk.setEnabled(true);
                }
                if (TextUtils.isEmpty(s.toString().replace(" ", ""))) {
                    oneLine.setBackgroundColor(getResources().getColor(R.color.line));
                } else {
                    oneLine.setBackgroundColor(getResources().getColor(R.color.font_ff3e6d));
                }
            }
        });
    }


    @OnClick({R.id.textSend, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textSend:
                hideKeyboard(btnOk);
                String phone1 = edtLoginName.getText().toString().replace(" ", "");
                if (TextUtils.isEmpty(phone1)) {
                    ArmsUtils.snackbarText("账号不能为空");
                } else {
                    if (TextUtils.equals(textSend.getText().toString(), "发送验证码")
                            || TextUtils.equals(textSend.getText().toString(), "重新发送")) {
                        showDialogLoding();
                        RxUtils.loading(commonModel.verification(phone1, "reg"), this)
                                .subscribe(new ErrorHandleSubscriber<CodeBean>(mErrorHandler) {

                                    @Override
                                    public void onNext(CodeBean codeBean) {
                                        disDialogLoding();
                                        showCode();
                                    }

                                    @Override
                                    public void onError(Throwable t) {
                                        super.onError(t);
                                        disDialogLoding();
                                    }
                                });
                    }
                }
                break;
            case R.id.btn_ok:
                String phone = edtLoginName.getText().toString().replace(" ", "");
                String phoneCode = edtCode.getText().toString().replace(" ", "");
                String password = edtLoginPw.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    ArmsUtils.makeText(this, "账号不能为空");
                } else if (TextUtils.isEmpty(phoneCode)) {
                    ArmsUtils.snackbarText("手机验证码不能为空");
                } else if (TextUtils.isEmpty(password)) {
                    ArmsUtils.snackbarText("密码不能为空");
                }else {
                    if (password.length() < 6 || password.length() > 20) {
                        ArmsUtils.snackbarText("密码必须大于6位，小于20位！");
                    } else {
                        showDialogLoding();
                        RxUtils.loading(commonModel.is_verification(phone, phoneCode), this)
                                .subscribe(new ErrorHandleSubscriber<Register>(mErrorHandler) {
                                    @Override
                                    public void onNext(Register codeBean) {
                                        disDialogLoding();
                                        if (codeBean != null && codeBean.getCode() == 1) {
                                            Intent intent = new Intent(RegisterActivity.this, SexActivity.class);
                                            intent.putExtra("phone", phone);
                                            intent.putExtra("password", password);
                                            ArmsUtils.startActivity(RegisterActivity.this, intent);
                                        } else {
                                            ArmsUtils.snackbarText(codeBean.getMessage());
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable t) {
                                        super.onError(t);
                                        disDialogLoding();
                                    }
                                });
                    }
                }
                break;
        }
    }


    @Override
    public void showMessage(@NonNull String message) {

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (REGISTER.equals(tag)) {
            finish();
        }
    }
}
