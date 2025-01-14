package com.miniapp.talks.popup;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.v3.MessageDialog;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.ChargeActivity;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.XuYaoMiZuanBean;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.AToast;
import com.miniapp.talks.utils.BToast;
import com.miniapp.talks.utils.BaseUtils;
import com.miniapp.talks.utils.MoneyValueFilter;
import com.miniapp.talks.utils.MyUtil;
import com.miniapp.talks.utils.ToastUtil;
import com.miniapp.talks.view.ShapeTextView;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.GOUMAIYAOSHI;

/**
 * 购买开宝箱的钥匙dialog
 */
public class BuyKeyDialog extends Dialog {


    @BindView(R.id.img_sub_key)
    ImageView mImgSubKey;
    @BindView(R.id.img_add_key)
    ImageView mImgAddKey;
    @BindView(R.id.et_key_count)
    EditText mEtKeyCount;
    @BindView(R.id.tv_demand_price)
    TextView mTvDemandPrice;
    @BindView(R.id.btn_buy)
    ShapeTextView mBtnBuy;
    private AdminHomeActivity mContext;
    private CommonModel commonModel;
    private RxErrorHandler rxErrorHandler;
    private String moneyStr;
    private XuYaoMiZuanBean mXuYaoMiZuanBean;

    public BuyKeyDialog(@NonNull Activity context, CommonModel commonModel, RxErrorHandler rxErrorHandler) {
        super(context, R.style.myChooseDialog);
        mContext = (AdminHomeActivity) context;
        this.commonModel = commonModel;
        this.rxErrorHandler = rxErrorHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_buy_key);

        ButterKnife.bind(this);

        mEtKeyCount.setText("10");
        getMiZuan(mEtKeyCount.getText().toString());

        setWidows();
    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth() - QMUIDisplayHelper.dpToPx(120));

        lp.alpha = 1.0f;

        lp.gravity = Gravity.CENTER;

        getWindow().setAttributes(lp);

        this.setCanceledOnTouchOutside(true);

        mEtKeyCount.setFilters(new InputFilter[]{new MoneyValueFilter()});

        mEtKeyCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                moneyStr = s.toString();
//                LogUtils.debugInfo("====第一次进入输入", s.toString());
                //改变完，计算
                try {
//                    if (!TextUtils.isEmpty(BaseUtils.getNumber(s.toString())) && !"0".equals(BaseUtils.getNumber(s.toString())) && !"".equals(s.toString())) {
                        moneyHandler.removeCallbacks(runnable);
                        moneyHandler.post(runnable);
//                    } else {
//                        mTvDemandPrice.setText("0");
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
//                    LogUtils.debugInfo("====第四次进入输出", e.getMessage());
                }
            }
        });
    }

    Handler moneyHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getMiZuan(moneyStr);
        }
    };

    @OnClick({R.id.img_sub_key, R.id.img_add_key, R.id.btn_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_sub_key://钥匙减
                doNumLogic(mEtKeyCount, false);
                break;
            case R.id.img_add_key://钥匙加
                doNumLogic(mEtKeyCount, true);
                break;
            case R.id.btn_buy:
                getKay();
//
                break;
        }
    }

    /**
     * 数量的加减
     *
     * @param isAdd
     */
    private void doNumLogic(EditText tvNum, boolean isAdd) {

        String numS = tvNum.getText().toString().trim();

        if (TextUtils.isEmpty(numS)) {
            tvNum.setText("10");
            return;
        }
        long num = Long.valueOf(numS);
        if (isAdd) {
            num += 10;
        } else {
            if (num <= 0) num = 0;
            else
                num -= 10;
        }
        tvNum.setText(num + "");
    }

    //购买钥匙
    private void getKay() {
        RxUtils.loading(commonModel.actionBuyKeys(mEtKeyCount.getText().toString()), mContext)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mContext.mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        AToast.showText(mContext, commentBean.getMessage());
                        EventBus.getDefault().post(new FirstEvent("买钥匙成功", GOUMAIYAOSHI));
                        dismiss();
                    }

                    @Override
                    public void onError(Throwable t) {

                        MessageDialog.build(mContext)
                                .setStyle(DialogSettings.STYLE.STYLE_IOS)
                                .setTheme(DialogSettings.THEME.LIGHT)
                                .setTitle("")
                                .setMessage("米钻不足，请充值。")
                                .setOkButton("充值", new OnDialogButtonClickListener() {
                                    @Override
                                    public boolean onClick(BaseDialog baseDialog, View v) {
                                        baseDialog.doDismiss();
                                        Intent intent = new Intent(mContext, ChargeActivity.class);
                                        intent.putExtra("type", 0);
                                        ArmsUtils.startActivity(intent);
                                        if (mContext instanceof AdminHomeActivity) {
                                            mContext.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                                        }
                                        return false;
                                    }

                                })
                                .setCancelButton("取消", new OnDialogButtonClickListener() {
                                    @Override
                                    public boolean onClick(BaseDialog baseDialog, View v) {
                                        baseDialog.doDismiss();
                                        return false;
                                    }
                                })
                                .show();
                    }
                });
    }

    //查看所需米钻
    private void getMiZuan(String mizuan) {
        RxUtils.loading(commonModel.getMizuanNum(mizuan), mContext)
                .subscribe(new ErrorHandleSubscriber<XuYaoMiZuanBean>(mContext.mErrorHandler) {
                    @Override
                    public void onNext(XuYaoMiZuanBean xuYaoMiZuanBean) {
                        mXuYaoMiZuanBean = xuYaoMiZuanBean;
                        mTvDemandPrice.setText(xuYaoMiZuanBean.getData().getNeedMizuan());
//                        LogUtils.debugInfo("====第二次进入输出", xuYaoMiZuanBean.getData().getNeedMizuan());
                    }

                    @Override
                    public void onError(Throwable t) {
//                        LogUtils.debugInfo("====第三次进入输出", t.getMessage());
                        mTvDemandPrice.setText("0");
                    }
                });
    }
}
