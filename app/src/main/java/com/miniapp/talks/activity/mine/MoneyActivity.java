package com.miniapp.talks.activity.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alipay.sdk.app.AuthTask;
import com.gyf.immersionbar.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.ChargeActivity;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.AliInfor;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.BindBean;
import com.miniapp.talks.bean.ChaMoneyBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.MoneyBean;
import com.miniapp.talks.bean.PayBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.AuthResult;
import com.miniapp.talks.utils.BaseUtils;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.JIEBANGALI;

/**
 * 我的钱包
 */
public class MoneyActivity extends MyBaseArmActivity {


    @BindView(R.id.textZuanNum)
    TextView textZuanNum;
    @BindView(R.id.imgCHongzhi)
    ImageView imgCHongzhi;
    @BindView(R.id.textBiNum)
    TextView textBiNum;
    @BindView(R.id.imgTixian)
    ImageView imgTixian;
    @BindView(R.id.imgDuihuan)
    ImageView imgDuihuan;
    @BindView(R.id.imgJilu)
    ImageView imgJilu;
    @BindView(R.id.llBang)
    LinearLayout llBang;

    @Inject
    CommonModel commonModel;
    @BindView(R.id.textAliName)
    TextView textAliName;
    @BindView(R.id.view_empty_head)
    View viewEmptyHead;

    private MoneyBean mMoneyBean;
    private int allSign = 0;
    private String moneyStr;
    private TextView uselessText;
    private TextView zengText;
    private TextView zuanNum;

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
        return R.layout.activity_money;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        ImmersionBar.with(this)
////                .statusBarView(viewEmptyHead)
//                .init();
        loadData();
    }

    private void loadData() {
        RxUtils.loading(commonModel.my_store(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<MoneyBean>(mErrorHandler) {
                    @Override
                    public void onNext(MoneyBean moneyBean) {
                        mMoneyBean = moneyBean;
                        textBiNum.setText(moneyBean.getData().get(0).getMibi());
                        textZuanNum.setText(moneyBean.getData().get(0).getMizuan());
                        if (!TextUtils.isEmpty(moneyBean.getData().get(0).getAli_nick_name())) {
                            textAliName.setText(moneyBean.getData().get(0).getAli_nick_name());
                        } else {
                            textAliName.setText("");
                        }
                    }
                });
    }


    @OnClick({R.id.imgCHongzhi, R.id.imgTixian, R.id.imgDuihuan, R.id.imgJilu, R.id.llBang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgCHongzhi:
                ArmsUtils.startActivity(ChargeActivity.class);
                break;
            case R.id.imgTixian:
                if (mMoneyBean.getData().get(0).getIs_ali() == 1) {
                    ArmsUtils.startActivity(CashMoneyActivity.class);
                } else {
                    toast("请先绑定支付宝！");
                }
                break;
            case R.id.imgDuihuan:
                MaterialDialog dialog = new MaterialDialog.Builder(this)
                        .customView(R.layout.dialog_duhuan, true)
                        .show();
                TextView textId = (TextView) dialog.findViewById(R.id.textId);
                EditText textBiNum = (EditText) dialog.findViewById(R.id.textBiNum);
                zuanNum = (TextView) dialog.findViewById(R.id.textZuanNum);
                ShapeTextView btn_ok = (ShapeTextView) dialog.findViewById(R.id.btn_ok);
                TextView textAll = (TextView) dialog.findViewById(R.id.textAll);
                uselessText = (TextView) dialog.findViewById(R.id.one);
                zengText = (TextView) dialog.findViewById(R.id.zeng_money);
                if (mMoneyBean != null) {
                    textId.setText("账户可兑换上限：" + mMoneyBean.getData().get(0).getMibi());
                    zuanNum.setText(0 + "");
                }
                textBiNum.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        moneyStr = s.toString();
                        //改变完，计算
                        try {
                            if (!TextUtils.isEmpty(BaseUtils.getNumber(s.toString()))) {
                                moneyHandler.removeCallbacks(runnable);
                                moneyHandler.postDelayed(runnable, 300);
                                if (s.toString().equals(mMoneyBean.getData().get(0).getMibi())) {
                                    allSign = 1;
                                } else {
                                    allSign = 0;
                                }
                            } else {
                                zuanNum.setText("0");
                                uselessText.setVisibility(View.GONE);
                                zengText.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                btn_ok.setOnClickListener(v -> {
                    if (allSign == 0) {
                        RxUtils.loading(commonModel.exchange(String.valueOf(UserManager.getUser().getUserId()),
                                textBiNum.getText().toString()), this)
                                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                    @Override
                                    public void onNext(BaseBean moneyBean) {
                                        dialog.dismiss();
                                        toast(moneyBean.getMessage());
                                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.CHONGZHISHUAXIN));
                                    }
                                });
                    } else {
                        RxUtils.loading(commonModel.exchange(String.valueOf(UserManager.getUser().getUserId()),
                                mMoneyBean.getData().get(0).getMibi() + ""), this)
                                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                    @Override
                                    public void onNext(BaseBean moneyBean) {
                                        dialog.dismiss();
                                        toast(moneyBean.getMessage());
                                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.CHONGZHISHUAXIN));
                                    }
                                });
                    }
                });
                textAll.setOnClickListener(v -> {
                    textBiNum.setText(String.valueOf(mMoneyBean.getData().get(0).getMibi()));
                });
                break;
            case R.id.imgJilu:
                ArmsUtils.startActivity(CashHisActivity.class);
                break;
            case R.id.llBang:
                if (mMoneyBean.getData().get(0).getIs_ali() == 1) {
                    Intent intent = new Intent(this, BindSuccessActivity.class);
                    intent.putExtra("head", mMoneyBean.getData().get(0).getAli_avatar());
                    intent.putExtra("name", mMoneyBean.getData().get(0).getAli_nick_name());
//                    intent.putExtra("phone",mMoneyBean.getData().get(0).get)
                    ArmsUtils.startActivity(intent);
                } else {
                    RxUtils.loading(commonModel.ali_oauth_code(null), this)
                            .subscribe(new ErrorHandleSubscriber<BindBean>(mErrorHandler) {
                                @Override
                                public void onNext(BindBean moneyBean) {
                                    Runnable payRunnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            AuthTask alipay = new AuthTask(MoneyActivity.this);
                                            Map<String, String> result = alipay.authV2(moneyBean.getData().getSign(), true);
                                            Message msg = new Message();
                                            msg.what = SDK_PAY_FLAG;
                                            msg.obj = result;
                                            mHandler.sendMessage(msg);
                                        }
                                    };
                                    // 必须异步调用
                                    Thread payThread = new Thread(payRunnable);
                                    payThread.start();
                                }
                            });
                }

                break;
        }
    }

    Handler moneyHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getMoney(moneyStr);
        }
    };

    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult payResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    LogUtils.debugInfo("sgm", "====result" + resultInfo);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")
                            && TextUtils.equals(payResult.getResultCode(), "200")) {
                        loadInfor(payResult.getAuthCode());
                    } else {
                        // 失败。
                        toast("授权失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    private void loadInfor(String code) {
        RxUtils.loading(commonModel.ali_oauth_token(code, UserManager.getUser().getUserId() + ""), this)
                .subscribe(new ErrorHandleSubscriber<AliInfor>(mErrorHandler) {
                    @Override
                    public void onNext(AliInfor aliInfor) {
                        loadData();
//                        aliInfor.getData().getAvatar();
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (Constant.CHONGZHISHUAXIN.equals(tag)) {
            loadData();
        } else if (JIEBANGALI.equals(tag)) {
            loadData();
        }
    }

    private void getMoney(String money) {
        RxUtils.loading(commonModel.getMoney(money), this)
                .subscribe(new ErrorHandleSubscriber<ChaMoneyBean>(mErrorHandler) {
                    @Override
                    public void onNext(ChaMoneyBean chaMoneyBean) {
                        if (chaMoneyBean.getCode() == 1) {
                            if (chaMoneyBean.getData().getGive() == 0) {
                                uselessText.setVisibility(View.GONE);
                                zengText.setVisibility(View.GONE);
                            } else {
                                uselessText.setVisibility(View.VISIBLE);
                                zengText.setVisibility(View.VISIBLE);
                            }
                            zuanNum.setText(String.valueOf(chaMoneyBean.getData().getMizuan()));
                            zengText.setText(String.valueOf(chaMoneyBean.getData().getGive()));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        uselessText.setVisibility(View.GONE);
                        zengText.setVisibility(View.GONE);
                    }
                });
    }
}
