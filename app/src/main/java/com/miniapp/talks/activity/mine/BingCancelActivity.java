package com.miniapp.talks.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.CodeBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.Register;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.utils.VerificationUtils;
import com.miniapp.talks.view.ShapeTextView;


import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class BingCancelActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.head_image)
    CircularImage headImage;
    @BindView(R.id.name_text)
    TextView nameText;
    @BindView(R.id.phone_text)
    TextView phoneText;
    @BindView(R.id.coad_tit)
    TextView coadTit;
    @BindView(R.id.coad_btn)
    ShapeTextView coadBtn;
    @BindView(R.id.surebtn)
    Button surebtn;
    @BindView(R.id.coad_edit)
    EditText coadEdit;

    private String head;
    private String name;
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
        return R.layout.activity_bing_cancel;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        head = intent.getStringExtra("head");
        name = intent.getStringExtra("name");
        if (!TextUtils.isEmpty(head)) {
            loadImage(headImage, head, R.mipmap.touxiang_ziliao_boy);
        }
        if (!TextUtils.isEmpty(name)) {
            nameText.setText(name);
        }
        phoneText.setText(UserManager.getUser().getPhone());

//        if (TextUtils.isEmpty(coadEdit.getText())) {
//            surebtn.setEnabled(false);
//        } else {
        surebtn.setEnabled(true);
//        }
    }

    @OnClick({R.id.coad_btn, R.id.surebtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.coad_btn:
                String phone1 = phoneText.getText().toString().replace(" ", "");
                if (TextUtils.isEmpty(phone1)) {
                    ArmsUtils.snackbarText("账号不能为空");
                } else if (!VerificationUtils.VildateMobile(phone1)) {
                    ArmsUtils.snackbarText("账号输入不合法");
                } else {
                    if (TextUtils.equals(coadBtn.getText().toString(), "发送验证码")
                            || TextUtils.equals(coadBtn.getText().toString(), "重新发送")) {
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
                String phone2 = phoneText.getText().toString().replace(" ", "");
                if (TextUtils.isEmpty(phone2)) {
                    toast("账号不能为空");
                } else if (!VerificationUtils.VildateMobile(phone2)) {
                    toast("账号输入不合法");
                } else if (TextUtils.isEmpty(coadEdit.getText().toString())) {
                    toast("验证码不能为空！");
                } else {
                    RxUtils.loading(commonModel.is_verification(phone2, coadEdit.getText().toString()), this)
                            .subscribe(new ErrorHandleSubscriber<Register>(mErrorHandler) {
                                @Override
                                public void onNext(Register baseBean) {
                                    loadJiebang();
                                }

                                @Override
                                public void onError(Throwable t) {
                                    super.onError(t);
                                }
                            });

                }
                break;
        }
    }

    /**
     * 解绑
     */
    private void loadJiebang() {
        RxUtils.loading(commonModel.UntyingAli(UserManager.getUser().getUserId() + ""), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        toast(baseBean.getMessage());
                        if (baseBean.getCode() == 1) {
                            EventBus.getDefault().post(new FirstEvent("指定发送", Constant.JIEBANGALI));
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });
    }

    public void showCode() {
        ArmsUtils.snackbarText("发送成功！");
        timer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int i = (int) (millisUntilFinished / 1000);
                coadBtn.setText(i + "s");
            }

            @Override
            public void onFinish() {
                coadBtn.setText("重新发送");
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
