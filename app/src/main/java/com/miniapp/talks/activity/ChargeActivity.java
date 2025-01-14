package com.miniapp.talks.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.ChargeAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.BaseWebActivity;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.GoodsList;
import com.miniapp.talks.bean.PayBean;
import com.miniapp.talks.bean.WxEndBean;
import com.miniapp.talks.bean.Wxmodel;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.utils.PayResult;
import com.miniapp.talks.view.MyGridView;
import com.miniapp.talks.view.ShapeTextView;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 充值
 */
public class ChargeActivity extends MyBaseArmActivity {

    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rightTitle)
    TextView rightTitle;
    @BindView(R.id.rightConfirm)
    ShapeTextView rightConfirm;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textNum)
    TextView textNum;
    @BindView(R.id.myGrid)
    MyGridView myGrid;
    @BindView(R.id.textVx)
    TextView textVx;
    @BindView(R.id.textZfb)
    TextView textZfb;
    @BindView(R.id.btn_chongzhi)
    ShapeTextView btnChongzhi;
    @BindView(R.id.yonghu)
    TextView xieyiText;
    private int type;

    @Inject
    CommonModel commonModel;

    private ChargeAdapter chargeAdapter;

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
        return R.layout.activity_charge;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        type = getIntent().getIntExtra("type", 0);
        chargeAdapter = new ChargeAdapter(this);
        myGrid.setAdapter(chargeAdapter);
        RxUtils.loading(commonModel.goodsList(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<GoodsList>(mErrorHandler) {
                    @Override
                    public void onNext(GoodsList goodsList) {
                        textNum.setText(goodsList.getData().getMizuan());
                        chargeAdapter.getList_adapter().clear();
                        chargeAdapter.getList_adapter().addAll(goodsList.getData().getGoods());
                        chargeAdapter.notifyDataSetChanged();
                    }
                });
        myGrid.setOnItemClickListener((parent, view, position, id) -> {
            List<GoodsList.DataBean.GoodsBean> list_adapter = chargeAdapter.getList_adapter();
            if (list_adapter.get(position).isSelect) {

            } else {
                for (GoodsList.DataBean.GoodsBean list : list_adapter) {
                    list.isSelect = false;
                }
                list_adapter.get(position).isSelect = true;
                chargeAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.textVx, R.id.textZfb, R.id.btn_chongzhi,R.id.yonghu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textVx:
                textVx.setSelected(true);
                textZfb.setSelected(false);
                break;
            case R.id.textZfb:
                textVx.setSelected(false);
                textZfb.setSelected(true);
                break;
            case R.id.btn_chongzhi:
                List<GoodsList.DataBean.GoodsBean> list_adapter = chargeAdapter.getList_adapter();
                List<GoodsList.DataBean.GoodsBean> listNew = new ArrayList<>();
                for (GoodsList.DataBean.GoodsBean list : list_adapter) {
                    if (list.isSelect) {
                        listNew.add(list);
                    }
                }
                if (listNew.size() > 0) {
                    if (textVx.isSelected()) {
                        loadWxData(String.valueOf(listNew.get(0).getId()));
                    } else if (textZfb.isSelected()) {
                        loadZfbData(String.valueOf(listNew.get(0).getId()));
                    } else if (!textVx.isSelected() && !textZfb.isSelected()) {
                        showToast("请选择充值方式");
                    }
                } else {
                    showToast("请选择充值金额");
                }
                break;
            case R.id.yonghu:
                Intent intent = new Intent(ChargeActivity.this, BaseWebActivity.class);
                intent.putExtra("url", "http://b.juchuyuncang.com/index/index/recharge_protocol");
                intent.putExtra("title", "充值协议");
                startActivity(intent);
                break;
        }
    }

    /**
     * 后台支付宝订单
     */
    private void loadZfbData(String goods_id) {
        RxUtils.loading(commonModel.rechargePay(String.valueOf(UserManager.getUser().getUserId()),
                goods_id, "1"), this)
                .subscribe(new ErrorHandleSubscriber<PayBean>(mErrorHandler) {
                    @Override
                    public void onNext(PayBean payBean) {
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(ChargeActivity.this);
                                Map<String, String> result = alipay.payV2(payBean.getData(),
                                        true);
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

    /**
     * 后台微信订单
     */
    private void loadWxData(String goods_id) {
        RxUtils.loading(commonModel.rechargeWxPay(String.valueOf(UserManager.getUser().getUserId()),
                goods_id, "2"), this)
                .subscribe(new ErrorHandleSubscriber<Wxmodel>(mErrorHandler) {
                    @Override
                    public void onNext(Wxmodel wxmodel) {
                        final IWXAPI mWxApi = WXAPIFactory.createWXAPI(ChargeActivity.this,
                                "wx23a0e7e411a9b40d", true);
                        // 将该app注册到微信
                        mWxApi.registerApp("wx23a0e7e411a9b40d");
                        // 判断是否安装客户端
                        if (!mWxApi.isWXAppInstalled()) {
                            toast("请您先安装微信客户端！");
                            return;
                        }
                        PayReq req = new PayReq();
                        req.appId = "wx23a0e7e411a9b40d";// 微信开放平台审核通过的应用APPID
                        req.partnerId = wxmodel.getData().getPartnerid();// 微信支付分配的商户号
                        req.prepayId = wxmodel.getData().getPrepayid();// 预支付订单号，app服务器调用“统一下单”接口获取
                        req.nonceStr = wxmodel.getData().getNoncestr();// 随机字符串，不长于32位
                        req.timeStamp = String.valueOf(wxmodel.getData().getTimestamp());// 时间戳
                        req.packageValue = wxmodel.getData().getPackageX();// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
                        req.sign = wxmodel.getData().getSign();// 签名，
                        // 调用微信SDK，发起支付，回调WxPayEntryActivity
                        mWxApi.sendReq(req);
                    }
                });
    }


    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            PayResult payResult2 = new PayResult((Map<String, String>) msg.obj);
//            LogUtils.e("sgm","====支付结果" + payResult2.getResult());
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showToast("支付成功");
                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.CHONGZHISHUAXIN));
                        loadYue();
                    } else {
                        // 失败。
                        showToast("支付失败,请重试");
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    /**
     * 请求余额
     */
    private void loadYue() {
        RxUtils.loading(commonModel.goodsList(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<GoodsList>(mErrorHandler) {
                    @Override
                    public void onNext(GoodsList goodsList) {
                        textNum.setText(goodsList.getData().getMizuan());
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (type == 1) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                startActivity(new Intent(this, AdminHomeActivity.class));
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarBack.setOnClickListener(v -> {
            if (type == 1) {
                startActivity(new Intent(this, AdminHomeActivity.class));
                finish();
            } else {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        System.out.println(event.getMsg());
        String tag = event.getTag();
        String msg = event.getMsg();
        if (Constant.WEIXINZHIFU.equals(tag)) {
            WxEndBean baseResp = JSON.parseObject(msg, WxEndBean.class);
            if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
                if (TextUtils.equals(baseResp.getErrCode(), "0")) {
                    toast("支付成功");
                    loadYue();
                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.CHONGZHISHUAXIN));
                } else if (TextUtils.equals(baseResp.getErrCode(), "-2")) {
                    toast("取消了！");
                } else {
                    // 失败。
                    showToast("支付失败,请重试");
                }
            } else {
                // 失败。
                showToast("支付失败,请重试");
            }
        }
    }
}
