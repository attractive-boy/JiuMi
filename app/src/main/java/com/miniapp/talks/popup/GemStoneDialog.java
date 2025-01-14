package com.miniapp.talks.popup;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.mvp.IView;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaoXiangBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.MessageBean;
import com.miniapp.talks.bean.MessageEvent;
import com.miniapp.talks.bean.OpenBoxBean;
import com.miniapp.talks.bean.StateMessage;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Arith;
import com.miniapp.talks.utils.BToast;
import com.miniapp.talks.utils.ToastUtil;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.DUIHUAN;
import static com.miniapp.talks.utils.Constant.GOUMAIYAOSHI;
import static com.miniapp.talks.utils.Constant.JIECHUCP;
import static com.miniapp.talks.utils.Constant.KAIQICPWEI;
import static com.miniapp.talks.utils.Constant.XIANSHIWANBI;
import static com.miniapp.talks.utils.Constant.XIUGAIGERENZILIAOCHENGGONG;


public class GemStoneDialog extends Dialog {

    @BindView(R.id.tv_gemstone_integral)
    TextView mTvGemstoneIntegral;
    @BindView(R.id.img_winning_record)
    ImageView mImgWinningRecord;
    @BindView(R.id.img_gemstone_intro)
    ImageView mImgGemstoneIntro;
    @BindView(R.id.img_gemstone_exchange)
    ImageView mImgGemstoneExchange;
    @BindView(R.id.tv_key_count)
    TextView mTvKeyCount;
    @BindView(R.id.img_add_key)
    ImageView mImgAddKey;
    @BindView(R.id.img_ten_open)
    ImageView mImgTenOpen;
    @BindView(R.id.img_open_one)
    ImageView mImgOpenOne;
    //    @BindView(R.id.img_gemstone_logo)
//    ImageView mImgGemstoneLogo;
    @BindView(R.id.img_open_hundred)
    ImageView mImgOpenHundred;
    @BindView(R.id.layout_baoxiao)
    ConstraintLayout mConstraintLayoutBaoxiao;
    @BindView(R.id.img_svg_open)
    SVGAImageView mSVGAImageView;
    @BindView(R.id.tv_cut_down_time)
    TextView mTvCutDownTime;
    @BindView(R.id.view_empty)
    View mViewEmpty;

    private AdminHomeActivity mContext;
    private CommonModel commonModel;
    private RxErrorHandler mErrorHandler;
    private BaoXiangBean.DataBean mDataBean;
    private TouMingDialog touMingDialog;
//    private OpenBoxBean mOpenBoxBean;
//    private BoxGiftActivity boxGiftActivity;
//    private int anInt = 0;

    boolean mHasThreeMinate = true;//距离守护宝箱开启还有3分钟倒计时

    boolean mIsGuardOpen = true;//距离守护宝箱关闭还有多少时间的倒计时

    long mHasGoneThreeMinateTime = 0;//3分钟倒计时已经过去了多少时间

    long mHasGoneGuardOpenTime = 0;//30分钟倒计时已经过去了多少时间


    long mLimitGuardTime = 0;//进入时距离活动结束还有多少时间

    long mLimitThreeMinateTime = 0;//进入时距离3分钟结束还有多少时间

    Handler mHandlerThreeMinate = new Handler();

    Handler mHandlerGuardOpen = new Handler();


    Runnable mThreeMinateRunnable = new Runnable() {//距离守护宝箱开启还有3分钟倒计时
        @Override
        public void run() {

//            long limite = 3 * 60 * 1000;//总共毫秒

            mHasGoneThreeMinateTime += 1000;

            if (mHasGoneThreeMinateTime >= mLimitThreeMinateTime) {
                mHasGoneThreeMinateTime = mLimitThreeMinateTime;
            }

            long second = (mLimitThreeMinateTime - mHasGoneThreeMinateTime) / 1000;//还有多少秒

            String timeStr = "";

            if (second == 0) {

                timeStr = "0:00";

            } else {

                if (second >= 60) {//剩余时间大于60秒

                    int minate = (int) (second / 60);

                    timeStr = "" + minate;

                    int sec = (int) (second % 60);

                    if (sec >= 10) {
                        timeStr = timeStr + ":" + sec;
                    } else {
                        timeStr = timeStr + ":0" + sec;
                    }

                } else {//剩余时间小于60秒

                    if (second >= 10) {
                        timeStr = "0:" + second;
                    } else {
                        timeStr = "0:0" + second;
                    }

                }

            }
            mTvCutDownTime.setText("守护宝箱开启还有" + timeStr + "分钟");

            if (timeStr.equals("0:00")) {
                getBaoXiang();
                return;
            } else {
                LogUtils.debugInfo("时间啦啦啦啦====" + timeStr);
                mHandlerThreeMinate.postDelayed(this, 1000);
            }

        }
    };

    Runnable mGuardCloseRunnable = new Runnable() {//距离守护宝箱关闭还有多少分钟倒计时
        @Override
        public void run() {

//            long limite = 30 * 60 * 1000;//总共毫秒

            mHasGoneGuardOpenTime += 1000;

            if (mHasGoneGuardOpenTime >= mLimitGuardTime) {
                mHasGoneGuardOpenTime = mLimitGuardTime;
            }

            long second = (mLimitGuardTime - mHasGoneGuardOpenTime) / 1000;//还有多少秒

            String timeStr = "";

            if (second == 0) {

                timeStr = "0:00";

            } else {

                if (second >= 60) {//剩余时间大于60秒

                    int minate = (int) (second / 60);

                    if (minate >= 10) {
                        timeStr = "" + minate;
                    } else {

                        timeStr = "0" + minate;
                    }


                    int sec = (int) (second % 60);

                    if (sec >= 10) {
                        timeStr = timeStr + ":" + sec;
                    } else {
                        timeStr = timeStr + ":0" + sec;
                    }

                } else {//剩余时间小于60秒

                    if (second >= 10) {
                        timeStr = "0:" + second;
                    } else {
                        timeStr = "0:0" + second;
                    }

                }

            }
            mTvCutDownTime.setText("守护宝箱关闭还有" + timeStr + "分钟");

            if (timeStr.equals("0:00")) {
                getBaoXiang();
                return;
            } else {
                mHandlerGuardOpen.postDelayed(this, 1000);
            }

        }
    };

    public GemStoneDialog(@NonNull Activity context, CommonModel commonModel, RxErrorHandler errorHandler) {
        super(context, R.style.myChooseDialog);
        mContext = (AdminHomeActivity) context;
        this.commonModel = commonModel;
        this.mErrorHandler = errorHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_open_gemstone);

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        setWidows();

//        initTime();

        getBaoXiang();

    }

    private void initTime() {
        mHandlerThreeMinate.removeCallbacks(mThreeMinateRunnable);
        mHandlerGuardOpen.removeCallbacks(mGuardCloseRunnable);
        LogUtils.debugInfo("当前时间：" + System.currentTimeMillis());

        long currentTime = System.currentTimeMillis() / 1000;

        if (mDataBean != null && !TextUtils.isEmpty(mDataBean.getBoxstartdate()) && !TextUtils.isEmpty(mDataBean.getBoxenddate())) {

            long startTime = Arith.strToLong(mDataBean.getBoxstartdate());

            long endTime = Arith.strToLong(mDataBean.getBoxenddate());

//            startTime=currentTime+2*60;
//            endTime+=currentTime+32*60;

            LogUtils.debugInfo("开启时间：" + startTime);
            LogUtils.debugInfo("关闭时间：" + endTime);

            if (currentTime >= startTime && currentTime < endTime) {//当前时间大于活动开始时间，小于结束时间

                LogUtils.debugInfo("当前时间大于活动开始时间，小于结束时间");

                mLimitGuardTime = endTime - currentTime;//还剩多少时间

                mLimitGuardTime = mLimitGuardTime * 1000;//转成毫秒

                mTvCutDownTime.setText("");

                mTvCutDownTime.setVisibility(View.VISIBLE);

                mHasGoneGuardOpenTime = 0;

                mHandlerGuardOpen.postDelayed(mGuardCloseRunnable, 1000);

                updateViewHeight();

            } else if (currentTime > endTime) {//已经结束

                LogUtils.debugInfo("已经结束");

                mTvCutDownTime.setVisibility(View.GONE);

                updateViewHeight();

            } else if (currentTime < startTime && startTime - currentTime > 3 * 60) {//开启时间大于3分钟

                LogUtils.debugInfo("开启时间大于3分钟");

                mTvCutDownTime.setVisibility(View.GONE);

                updateViewHeight();

            } else if (currentTime < startTime && startTime - currentTime <= 3 * 60) {//守护宝箱开启前3分钟倒计时

                LogUtils.debugInfo("守护宝箱开启前3分钟倒计时");

                mLimitThreeMinateTime = startTime - currentTime;//还剩多少时间

                mLimitThreeMinateTime = mLimitThreeMinateTime * 1000;//转成毫秒

                mTvCutDownTime.setText("");

                mTvCutDownTime.setVisibility(View.VISIBLE);

                mHasGoneThreeMinateTime = 0;

                mHandlerThreeMinate.postDelayed(mThreeMinateRunnable, 1000);

            }


        }

//        if(mHasThreeMinate){//还有3分钟开启守护宝箱的倒计时
//
//            mTvCutDownTime.setVisibility(View.VISIBLE);
//
//            mHandlerThreeMinate.postDelayed(mThreeMinateRunnable, 1000);
//        }
//        if (mIsGuardOpen) {
//
//            mTvCutDownTime.setText("守护宝箱关闭还有30:00分钟");
//
//            mTvCutDownTime.setVisibility(View.VISIBLE);
//
//            mHandlerGuardOpen.postDelayed(mGuardCloseRunnable, 1000);
//
//            updateViewHeight();
//        }

    }

    /**
     * 设置dialog底部空白高度
     */
    private void updateViewHeight() {

        if (mTvCutDownTime.getVisibility() == View.GONE) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mViewEmpty.getLayoutParams();
            params.height = QMUIDisplayHelper.dpToPx(30);
            mViewEmpty.setLayoutParams(params);
        } else {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mViewEmpty.getLayoutParams();
            params.height = QMUIDisplayHelper.dpToPx(10);
            mViewEmpty.setLayoutParams(params);
        }
    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth() - QMUIDisplayHelper.dp2px(mContext, 72));

        lp.alpha = 1.0f;

        lp.gravity = Gravity.CENTER;

        getWindow().setAttributes(lp);

        this.setCanceledOnTouchOutside(true);

    }

    @Override
    public void dismiss() {
        super.dismiss();
        mHandlerThreeMinate.removeCallbacks(mThreeMinateRunnable);
        mHandlerGuardOpen.removeCallbacks(mGuardCloseRunnable);
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.img_winning_record, R.id.img_gemstone_intro, R.id.img_gemstone_exchange, R.id.img_add_key, R.id.img_ten_open, R.id.img_open_one, R.id.img_open_hundred})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_winning_record:
                BoxOpenRecordWindow boxOpenRecordWindow = new BoxOpenRecordWindow(mContext, mConstraintLayoutBaoxiao, commonModel, mErrorHandler);
                boxOpenRecordWindow.showAtLocation(mTvGemstoneIntegral, Gravity.CENTER, 0, 0);
                break;
            case R.id.img_gemstone_intro:
                BoxTitleWindow boxTitleWindow = new BoxTitleWindow(mContext, mConstraintLayoutBaoxiao, commonModel, mErrorHandler);
                boxTitleWindow.showAtLocation(mTvGemstoneIntegral, Gravity.CENTER, 0, 0);
                break;
            case R.id.img_gemstone_exchange:
                BoxExchangeWindow boxExchangeWindow = new BoxExchangeWindow(mContext, commonModel, mErrorHandler, mDataBean.getPoints());
                boxExchangeWindow.showAtLocation(mTvGemstoneIntegral, Gravity.CENTER, 0, 0);
                break;
            case R.id.img_add_key://钥匙添加
                BuyKeyDialog buyKeyDialog = new BuyKeyDialog(mContext, commonModel, mErrorHandler);
                buyKeyDialog.show();
                break;
            case R.id.img_ten_open:
                touMingDialog = new TouMingDialog(mContext);
                touMingDialog.show();
                boo(false);
                setCanceledOnTouchOutside(false);
                getAwardList(10);
                break;
            case R.id.img_open_one:
                touMingDialog = new TouMingDialog(mContext);
                touMingDialog.show();
                boo(false);
                setCanceledOnTouchOutside(false);
                getAwardList(1);
                break;
            case R.id.img_open_hundred:
                touMingDialog = new TouMingDialog(mContext);
                touMingDialog.show();
                boo(false);
                setCanceledOnTouchOutside(false);
                getAwardList(100);
                break;
        }
    }

    //未开宝箱
    public void showWeiSVG(SVGAParser parser, String giftUrl, SVGAImageView svgaImageView) {
        try {
            parser.decodeFromAssets(giftUrl, new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                    svgaImageView.setVideoItem(svgaVideoEntity);
                    svgaImageView.setLoops(0);
                    svgaImageView.stepToFrame(1, true);
                }

                @Override
                public void onError() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.debugInfo("====SVAG错误", e.getMessage());
        }
    }

    //开过宝箱
    public void showServerSVG(SVGAParser parser, String giftUrl, SVGAImageView svgaImageView, OpenBoxBean openBoxBean) {
        try {
            parser.decodeFromAssets(giftUrl, new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                    svgaImageView.setVideoItem(svgaVideoEntity);
                    svgaImageView.setLoops(1);
                    svgaImageView.stepToFrame(1, true);
//                    svgaImageView.startAnimation();
//                    svgaImageView.setClearsAfterStop(false);
                    setSvgImgClickble(svgaImageView, openBoxBean);
                }

                @Override
                public void onError() {
                    LogUtils.debugInfo("====错了呀");
                }
            });
//            setSvgImgClickble(svgaImageView, openBoxBean);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.debugInfo("====错了吗？？？？");
            LogUtils.debugInfo("====错了吗？", e.getLocalizedMessage());
            LogUtils.debugInfo("====错了吗？?", e.getMessage());
            LogUtils.debugInfo("====错了吗???", e.toString());
        }
    }

    /**
     * 宝箱动画完毕之后
     */
    private void setSvgImgClickble(SVGAImageView svgImage, OpenBoxBean openBoxBean) {
        LogUtils.debugInfo("====打不出来哇", "哦哦哦哦哦哦哦");
        svgImage.setCallback(new SVGACallback() {
            @Override
            public void onPause() {
            }

            @Override
            public void onFinished() {
                svgImage.setCallback(null);
//                svgImage.setClickable(false);
                LogUtils.debugInfo("====一直结束");
                if (touMingDialog != null) {
                    touMingDialog.dismiss();
                }
                BoxGiftActivity boxGiftActivity = new BoxGiftActivity(mContext, openBoxBean);
                boxGiftActivity.show();
                touMingDialog = new TouMingDialog(mContext);
                touMingDialog.show();
            }

            @Override
            public void onRepeat() {
            }

            @Override
            public void onStep(int i, double v) {
            }
        });

    }

    //获取宝箱信息
    private void getBaoXiang() {
        RxUtils.loading(commonModel.getBoxInfo("xx"), mContext)
                .subscribe(new ErrorHandleSubscriber<BaoXiangBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaoXiangBean baoXiangBean) {
                        if (baoXiangBean.getCode() == 1) {
                            mDataBean = baoXiangBean.getData();
                            mTvGemstoneIntegral.setText("宝箱积分： " + baoXiangBean.getData().getPoints());
                            mTvKeyCount.setText(String.valueOf(baoXiangBean.getData().getKeys_num()));
                            initTime();
                            if (baoXiangBean.getData().getBoxclass() == 1) {
                                SVGAParser parser = new SVGAParser(mContext);
                                showWeiSVG(parser, "putong_weikai.svga", mSVGAImageView);
                            } else if (baoXiangBean.getData().getBoxclass() == 2) {
                                SVGAParser parser = new SVGAParser(mContext);
                                showWeiSVG(parser, "shouhu_weikai.svga", mSVGAImageView);
                            }
//                           int points= Integer.parseInt(baoXiangBean.getData().getPoints());

                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        LogUtils.debugInfo("====数据请求错误", t.getMessage());
                    }
                });
    }

    //开启宝箱
    private void getAwardList(int keyNum) {
        RxUtils.loading(commonModel.getAwardList(keyNum), mContext)
                .subscribe(new ErrorHandleSubscriber<OpenBoxBean>(mErrorHandler) {
                    @Override
                    public void onNext(OpenBoxBean openBoxBean) {
                        if (openBoxBean.getCode() == 1) {
                            if (mDataBean.getBoxclass() == 1) {
                                SVGAParser parser = new SVGAParser(mContext);
                                showServerSVG(parser, "putong_kai.svga", mSVGAImageView, openBoxBean);
                            } else if (mDataBean.getBoxclass() == 2) {
                                SVGAParser parser = new SVGAParser(mContext);
                                showServerSVG(parser, "shouhu_kai.svga", mSVGAImageView, openBoxBean);
                            }
                            //更新房间公屏消息
                            List<OpenBoxBean.DataBean.AwardListBean> awardList = openBoxBean.getData().getAwardList();
                            if (awardList != null && awardList.size() > 0) {

                                List<OpenBoxBean.DataBean.AwardListBean> newAwardList = new ArrayList<>();
                                //单个礼物的价值大于20米钻，才发频道消息
                                for(OpenBoxBean.DataBean.AwardListBean itemBean:awardList){

                                    double price = Arith.strToDouble(itemBean.getPrice());

                                    if(price>=20){
                                        newAwardList.add(itemBean);
                                    }

                                }

//                                StringBuilder builder = new StringBuilder();
//                                builder.append("哇哦，");
//                                builder.append(UserManager.getUser().getNickname());
//                                builder.append("在");
//                                if (openBoxBean.getData().getBox_class() == 1) {
//                                    builder.append("普通宝箱");
//                                } else {
//                                    builder.append("守护宝箱");
//                                }
//                                builder.append("开出了");
//                                for (OpenBoxBean.DataBean.AwardListBean awardListBean : awardList) {
//                                    String number = "x" + awardListBean.getNum();
//                                    String name = awardListBean.getName();
//                                    builder.append(name);
//                                    builder.append(number);
//                                    builder.append(",");
//                                }
//                                builder.append("真是太优秀了！");

                                //有礼物的价值大于20米钻
                                if(newAwardList.size()>0 || !TextUtils.isEmpty(openBoxBean.getData().getAward_tips())){

                                    LoginData loginData = UserManager.getUser();
                                    MessageBean messageBean = new MessageBean();
                                    messageBean.setNickName(loginData.getNickname());
                                    messageBean.setUser_id(loginData.getUserId()+"");
                                    messageBean.box_class = String.valueOf(openBoxBean.getData().getBox_class());
                                    messageBean.awardList = newAwardList;
                                    messageBean.setMessage(openBoxBean.getData().getAward_tips());
//                                    messageBean.award_tips = openBoxBean.getData().getAward_tips();
                                    messageBean.setMessageType("13");

                                    MessageEvent messageEvent = new MessageEvent();
                                    messageEvent.setStateMessage(StateMessage.PEOPLE_OPEN_GEMSTONE);
                                    messageEvent.setObject(messageBean);
                                    EventBus.getDefault().post(messageEvent);

                                }

                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        BToast.showText(mContext, t.getMessage());
                        touMingDialog.dismiss();
                        setCanceledOnTouchOutside(true);
                        boo(true);
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (DUIHUAN.equals(tag)) {
            getBaoXiang();
        } else if (GOUMAIYAOSHI.equals(tag)) {
            getBaoXiang();
        } else if (XIANSHIWANBI.equals(tag)) {
            getBaoXiang();
        } else if ("donghuawancheng".equals(tag)) {
            if (touMingDialog != null) {
                touMingDialog.dismiss();
            }
            this.setCanceledOnTouchOutside(true);
            boo(true);
        }
    }

    private void boo(boolean booo) {
        mImgTenOpen.setEnabled(booo);
        mImgOpenOne.setEnabled(booo);
        mImgOpenHundred.setEnabled(booo);
    }
}
