package com.miniapp.talks.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSON;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.login.LoginActivity;
import com.miniapp.talks.activity.message.LiaoBaExtensionModule;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.DanMuViewHolder;
import com.miniapp.talks.app.AppLifecyclesImpl;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.EnterRoom;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.MessageEvent;
import com.miniapp.talks.bean.PushBean;
import com.miniapp.talks.bean.StateMessage;
import com.miniapp.talks.bean.UserBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.floatingview.EnFloatingView;
import com.miniapp.talks.floatingview.FloatingMagnetView;
import com.miniapp.talks.floatingview.FloatingView;
import com.miniapp.talks.floatingview.MagnetViewListener;
import com.miniapp.talks.fragment.MainCenterFragment;
import com.miniapp.talks.fragment.MainCommunityFragment;
import com.miniapp.talks.fragment.MainFindFragment;
import com.miniapp.talks.fragment.MainHomeFragment;
import com.miniapp.talks.fragment.MainMessageFragment;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.ActivityUtils;
import com.miniapp.talks.utils.BaseUtils;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.utils.EncodeUtils;
import com.miniapp.talks.utils.FastJsonUtils;
import com.miniapp.talks.utils.SharedPreferencesUtils;
import com.orient.tea.barragephoto.adapter.AdapterListener;
import com.orient.tea.barragephoto.adapter.BarrageAdapter;
import com.orient.tea.barragephoto.model.DataSource;
import com.orient.tea.barragephoto.ui.BarrageView;
import com.pgyersdk.update.DownloadFileListener;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.pgyersdk.update.javabean.AppBean;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.inject.Inject;

import butterknife.BindView;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.provider.TextMessageItemProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.FANHUIZHUYE;
import static com.miniapp.talks.utils.Constant.KBXTUISONG;
import static com.miniapp.talks.utils.Constant.LOGOUT;
import static com.miniapp.talks.utils.Constant.TUISONG;
import static com.miniapp.talks.utils.Constant.XUANFUYINCANG;

public class MainActivity extends MyBaseArmActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.frameLayout_main)
    FrameLayout frameLayoutMain;
    @BindView(R.id.radio_home)
    RadioButton radioHome;
    @BindView(R.id.radio_finder)
    RadioButton radioFinder;
    @BindView(R.id.radio_shequ)
    RadioButton radioShequ;
    @BindView(R.id.radio_message)
    RadioButton radioMessage;
    @BindView(R.id.radio_center)
    RadioButton radioCenter;
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.barrage)
    BarrageView barrageView;

    private CircularImage imgHeader;
    private ImageView img1, img2;
    @Inject
    CommonModel commonModel;
    MainHomeFragment mainHomeFragment = new MainHomeFragment();
    MainFindFragment mainFindFragment = new MainFindFragment();
    MainCommunityFragment mainCommunityFragment = new MainCommunityFragment();
    MainMessageFragment mainMessageFragment = new MainMessageFragment();
    MainCenterFragment mainCenterFragment = new MainCenterFragment();

    private BarrageAdapter<PushBean> mAdapter;
    private UserManager userManager;

    List<PushBean> mPushBeanList = new Vector<>();

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
        return R.layout.activity_main;
    }

    @SuppressLint("CheckResult")
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mRadioGroup.setOnCheckedChangeListener(this);
        mRadioGroup.check(R.id.radio_home);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        initUpdate();
                    } else {
                        showToast("请打开存储权限！");
                    }
                });

        //初始化弹幕
        initDanmu();

        LoginData loginData = UserManager.getUser();

        if(!TextUtils.isEmpty(loginData.getRy_token())){

            RongIMClient.setRCLogInfoListener(s -> System.out.println("--------"+s));
            RongIM.connect(loginData.getRy_token(), new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    LogUtils.debugInfo("TAG", "0");
                }

                @Override
                public void onSuccess(String userid) {
                    LogUtils.debugInfo("TAG", "--onSuccess" + userid);
                    //设置用户消息
//                    UserInfo user = new UserInfo(userid,
//                            loginData.getNickname(), Uri.parse(loginData.getHeadimgurl()));
//                    RongIM.getInstance().setCurrentUserInfo(user);
//                    RongIM.getInstance().setMessageAttachedUserInfo(true);

                    registerExtensionPlugin();

                    RongIM.registerMessageTemplate(new MyTextMessageItemProvider());

                    RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
                        @Override
                        public boolean onReceived(Message message, int i) {
                            MessageContent content =  message.getContent();
                            return false;
                        }
                    });

                    //        //连接成功以后设置用户消息
                    RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                        @Override
                        public UserInfo getUserInfo(String s) {
                            //            LogUtils.debugInfo("sgm","====走到设置头像" + userId);
                            LogUtils.debugInfo("====走到设置头像" , s);
                            return findUserId(s);//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
                        }
                    }, true);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtils.debugInfo("TAG", "--onError" + errorCode);
                }
            });
        }


//        //设置聊天用户信息

        if (UserManager.IS_LOGIN && TextUtils.isEmpty(UserManager.getUser().getToken())) {//登录状态，token为空，重新登录
            UserManager.layout();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }


    /**
     * 用户信息
     */
    private UserInfo findUserId(String userId) {
        LogUtils.debugInfo("====进不进得来");

        UserInfo userInfo;

        String userListStr = (String) SharedPreferencesUtils.getParam(MainActivity.this,"rim_info","");

        Map<String, Object> userMap = new HashMap<>();

        if(!TextUtils.isEmpty(userListStr)){//本地有用户数据

            userListStr = new String(EncodeUtils.base64Decode(userListStr));//先解码

            LogUtils.debugInfo("====查询到所有聊天用户信息"+userListStr);

            userMap = FastJsonUtils.json2Map(userListStr);

            int m = 0;

            for(String key:userMap.keySet()){

                if (TextUtils.equals(userId, key)) {

                    String userJson = (String) userMap.get(key);

                    if(!TextUtils.isEmpty(userJson)){
                        m = 2;

                        UserBean userBean = JSON.parseObject(userJson,UserBean.class);

                        userInfo = new UserInfo(userBean.getData().getId() + "",
                                userBean.getData().getNickname(),
                                Uri.parse(userBean.getData().getHeadimgurl()));

                        LogUtils.debugInfo("====查询到当前聊天用户信息"+userJson);

                        return userInfo;

                    }
                    break;
                }
            }

            if(m==0){//没有查询到
                getOtherUser(userId,userMap);
            }

        } else {
            getOtherUser(userId,userMap);
        }



//        RongIM.getInstance().refreshUserInfoCache(new UserInfo(userId.getUserId() + "",
//                userId.getNickname(),
//                Uri.parse(userId.getHeadimgurl())));
        return null;
    }

    private void getOtherUser(String userId,Map<String, Object> userMap){

        RxUtils.loading(commonModel.get_user_info(userId), this)
                .subscribe(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {
                    @Override
                    public void onNext(UserBean userBean) {

                        Log.e("获取用户信息了======", userBean.getData().getNickname());
                        UserInfo userInfo = new UserInfo(userBean.getData().getId() + "",
                                userBean.getData().getNickname(),
                                Uri.parse(userBean.getData().getHeadimgurl()));

                        String userStrs = JSON.toJSONString(userBean);//用户信息

                        userMap.put(userId, userStrs);

                        String mapsJsonStr = FastJsonUtils.map2Json(userMap);

                        String baseStr = EncodeUtils.base64Encode2String(mapsJsonStr.getBytes());//转码

                        LogUtils.debugInfo("聊天用户信息"+mapsJsonStr);

                        SharedPreferencesUtils.setParam(MainActivity.this,"rim_info",baseStr);

                        RongIM.getInstance().refreshUserInfoCache(userInfo);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("获取用户信息出错了======", "0000");
                    }
                });
    }

    /**
     * 弹幕
     */
    private void initDanmu() {
        BarrageView.Options options = new BarrageView.Options()
                .setGravity(BarrageView.GRAVITY_TOP) // 设置弹幕的位置
                .setInterval(600)  // 设置弹幕的发送间隔
                .setSpeed(200, 29) // 设置速度和波动值
                .setModel(BarrageView.MODEL_COLLISION_DETECTION)     // 设置弹幕生成模式
                .setRepeat(1)// 循环播放 默认为1次 -1 为无限循环
                .setClick(true);// 设置弹幕是否可以点击
        barrageView.setOptions(options);

        barrageView.setAdapter(mAdapter = new BarrageAdapter<PushBean>(null, this) {
            @Override
            public BarrageViewHolder<PushBean> onCreateViewHolder(View root, int type) {
                return new DanMuViewHolder(root,MainActivity.this);// 返回自己创建的ViewHolder
            }

            @Override
            public int getItemLayout(PushBean barrageData) {
                return R.layout.danmu;// 返回自己设置的布局文件
            }
        });

        // 设置监听器
        mAdapter.setAdapterListener(new AdapterListener<PushBean>() {
            @Override
            public void onItemClick(BarrageAdapter.BarrageViewHolder<PushBean> holder, PushBean item) {
                if (item != null) {
                    if ("gift".equals(item.type)){
                        enterData(item.getData().getUid() + "", "", commonModel, 1,"0");
                    }
                }
            }
        });

//        final PushBean pushBean = new PushBean();
//        PushBean.DataBean dataBean = new PushBean.DataBean();
//        dataBean.setUid(1100001);
//        dataBean.setImg("http://tp5_test.miniyuyin.cn/upload/box/39bebf5a604494481be991f44e0bb04c.png");
//        dataBean.setUser_name("zhongguo");
//        dataBean.setFrom_name("冰凉");
//        dataBean.setGift_name("鲜花");
//        dataBean.setNum(30);
//
//        pushBean.setData(dataBean);
//        pushBean.setType("gift");
//        EventBus.getDefault().post(new FirstEvent(pushBean,TUISONG));
//
//        final PushBean pushBean4 = new PushBean();
//        PushBean.DataBean dataBean4 = new PushBean.DataBean();
//        dataBean4.setUid(1100009);
//        dataBean4.setUser_name("春天");
//        dataBean4.setFrom_name("大洪水");
//        dataBean4.setGift_name("奶牛");
//        dataBean4.setNum(30);
//
//        pushBean4.setData(dataBean4);
//        pushBean4.setType("gift");
//        EventBus.getDefault().post(new FirstEvent(pushBean4,TUISONG));
////        barrageView.postDelayed(() -> EventBus.getDefault().post(new FirstEvent(pushBean,KBXTUISONG)),1000);
////        barrageView.postDelayed(() -> mAdapter.add(pushBean), 1000);
//
//        final PushBean pushBean1 = new PushBean();
//        dataBean = new PushBean.DataBean();
//        dataBean.setUid(1100008);
//        dataBean.setUser_name("zhongguo");
//        dataBean.setBoxclass("普通宝箱");
//        dataBean.setFrom_name("冰凉");
//        dataBean.setGift_name("鲜花X66 冰冷之心X99 女王之鞋X55 雪画梅子X999");
//        dataBean.setNum(30);
//
//        pushBean1.setData(dataBean);
//        pushBean1.setType("award");
//        EventBus.getDefault().post(new FirstEvent(pushBean1,KBXTUISONG));
//        final PushBean pushBean5 = new PushBean();
//        dataBean = new PushBean.DataBean();
//        dataBean.setUid(1100004);
//        dataBean.setUser_name("zhongguo");
//        dataBean.setBoxclass("普通宝箱");
//        dataBean.setFrom_name("黑子");
//        dataBean.setGift_name("手机X66 袋哪壶X99 电线X55 老虎X999");
//        dataBean.setNum(30);
//
//        pushBean5.setData(dataBean);
//        pushBean5.setType("award");
//        EventBus.getDefault().post(new FirstEvent(pushBean5,KBXTUISONG));
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_home:
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
                        mainHomeFragment, R.id.frameLayout_main);
                break;
            case R.id.radio_finder:
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
                        mainFindFragment, R.id.frameLayout_main);
                break;
            case R.id.radio_shequ:
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
                        mainCommunityFragment, R.id.frameLayout_main);
                break;
            case R.id.radio_message:
                LogUtils.debugInfo("sgm", "====点击消息");
                EventBus.getDefault().post(new FirstEvent("指定发送", Constant.SHUAXINPENGYOU));
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
                        mainMessageFragment, R.id.frameLayout_main);
                break;
            case R.id.radio_center:
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(),
                        mainCenterFragment, R.id.frameLayout_main);
                break;
            default:
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //重写返回键
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (barrageView != null) {
            barrageView.destroy();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        LogUtils.debugInfo("====onRestart");
        if (AdminHomeActivity.isStart && AdminHomeActivity.isTop) {
            startActivity(new Intent(this, AdminHomeActivity.class));
        }
    }

    boolean mIsPushRuning = false;

    CountDownTimer mPushTimer = new CountDownTimer(3 * 1000, 500) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            barrageView.postDelayed(() -> mAdapter.addList(mPushBeanList), 500);
            mIsPushRuning = false;
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
//        String msg = event.getMsg();
        if (LOGOUT.equals(tag)) {
            finish();
        } else if (FANHUIZHUYE.equals(tag)) {//显示悬浮窗
            EnterRoom enterRoom = event.getEnterRoom();
            showFlow(enterRoom.getRoom_info().get(0).getRoom_cover());
        } else if (XUANFUYINCANG.equals(tag)) {//显示悬浮窗
            FloatingView.get().remove();
        } else if (TUISONG.equals(tag)) {//推送消息，显示布局
            PushBean pushBean = event.getPushBean();
            if(!mIsPushRuning){//没有运行
                mIsPushRuning = true;
                mPushBeanList.clear();
                mPushTimer.start();
                mPushBeanList.add(pushBean);
            } else {
                mPushBeanList.add(pushBean);
            }
            LogUtils.debugInfo("送礼物消息", "==="+JSON.toJSONString(pushBean));
//            barrageView.postDelayed(() -> mAdapter.add(pushBean), 1000);
        } else if (KBXTUISONG.equals(tag)) { //开宝箱推送消息，显示布局
            PushBean pushBean = event.getPushBean();
            if(!mIsPushRuning){//没有运行
                mIsPushRuning = true;
                mPushBeanList.clear();
                mPushTimer.start();
                mPushBeanList.add(pushBean);
            } else {
                mPushBeanList.add(pushBean);
            }
            LogUtils.debugInfo("开宝箱消息", "==="+JSON.toJSONString(pushBean));
//            barrageView.postDelayed(() -> mAdapter.add(pushBean), 1000);
        }
    }

    public void showFlow(String msg) {
        FloatingView.get().add();
        EnFloatingView view = FloatingView.get().getView();
        imgHeader = view.findViewById(R.id.imgHeader);
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img1.setSelected(true);
        loadImage(imgHeader, msg, R.mipmap.gender_zhuce_boy);
        img1.setOnClickListener(v -> {
            if (AdminHomeActivity.isStart) {
                if (img1.isSelected()) {
                    img1.setSelected(false);
                    AdminHomeActivity.mContext.stopTing(false);
                } else {
                    img1.setSelected(true);
                    AdminHomeActivity.mContext.stopTing(true);
                }
            }
        });
        img2.setOnClickListener(v -> {
            if (AdminHomeActivity.isStart) {
                AdminHomeActivity.isStart = false;
                AdminHomeActivity.mContext.finish();
            }
        });
        FloatingView.get().listener(new MagnetViewListener() {
            @Override
            public void onRemove(FloatingMagnetView magnetView) {
                Toast.makeText(MainActivity.this, "我没了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(FloatingMagnetView magnetView) {
                startActivity(new Intent(MainActivity.this, AdminHomeActivity.class));
            }
        });

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        //让旋转动画一直转，不停顿的重点
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        imgHeader.startAnimation(rotateAnimation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FloatingView.get().attach(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        FloatingView.get().detach(this);
    }

    @Override
    public void onResume() {
        super.onResume();
//        LogUtils.debugInfo("====onResume");
        if (AdminHomeActivity.isStart && AdminHomeActivity.isTop) {
            startActivity(new Intent(this, AdminHomeActivity.class));
        }
    }

    private MaterialDialog progress;

    private void initUpdate() {
        new PgyUpdateManager.Builder()
                .register();
        /** 新版本 **/
        new PgyUpdateManager.Builder()
                .setForced(true)                //设置是否强制提示更新,非自定义回调更新接口此方法有用
                .setUserCanRetry(false)         //失败后是否提示重新下载，非自定义下载 apk 回调此方法有用
                .setDeleteHistroyApk(false)     // 检查更新前是否删除本地历史 Apk， 默认为true
                .setUpdateManagerListener(new UpdateManagerListener() {
                    @Override
                    public void onNoUpdateAvailable() {
                        //没有更新是回调此方法
                        Log.d("pgyer", "there is no new version");
                    }

                    @Override
                    public void onUpdateAvailable(AppBean appBean) {
                        //有更新回调此方法
                        Log.d("pgyer", "there is new version can update"
                                + "new versionCode is " + appBean.getVersionCode());
                        //调用以下方法，DownloadFileListener 才有效；
                        //如果完全使用自己的下载方法，不需要设置DownloadFileListener
                        new MaterialDialog.Builder(MainActivity.this)
                                .title("发现最新版本哦~")
                                .content("请尽快升级到最新版本，以免影响您的正常使用")
                                .positiveText("确认更新")
                                .negativeText("取消")
                                .onPositive((dialog, which) -> {
                                    progress = new MaterialDialog.Builder(MainActivity.this)
                                            .title("正在更新")
                                            .content("请耐心等待")
                                            .canceledOnTouchOutside(false)
                                            .progress(true, 0)
                                            .show();
                                    PgyUpdateManager.downLoadApk(appBean.getDownloadURL());
                                })
                                .show();
                    }

                    @Override
                    public void checkUpdateFailed(Exception e) {
                        //更新检测失败回调
                        //更新拒绝（应用被下架，过期，不在安装有效期，下载次数用尽）以及无网络情况会调用此接口
                        Log.e("pgyer", "check update failed ", e);
                    }
                })
                //注意 ：
                //下载方法调用 PgyUpdateManager.downLoadApk(appBean.getDownloadURL()); 此回调才有效
                //此方法是方便用户自己实现下载进度和状态的 UI 提供的回调
                //想要使用蒲公英的默认下载进度的UI则不设置此方法
                .setDownloadFileListener(new DownloadFileListener() {
                    @Override
                    public void downloadFailed() {
                        //下载失败
                        progress.dismiss();
                    }

                    @Override
                    public void downloadSuccessful(Uri uri) {
                        progress.dismiss();
                        // 使用蒲公英提供的安装方法提示用户 安装apk
                        PgyUpdateManager.installApk(uri);
                    }

                    @Override
                    public void onProgressUpdate(Integer... integers) {
                        Log.e("pgyer", "update download apk progress" + integers);
                    }
                })
                .register();
    }


//    // 在多视图弹幕中自己需要构建多个类型ViewHolder
////    class ViewHolder extends BarrageAdapter.BarrageViewHolder<PushBean> {
////
//////        private ImageView imgGift;
//////        private TextView textUSer1, textUSer2, textNumber, textGiftName, box, OkBtn, wuYongOne;
////
////        public ViewHolder(View itemView) {
////            super(itemView);
////            imgGift = itemView.findViewById(R.id.imgGift);
////            textUSer1 = itemView.findViewById(R.id.textUSer1);
////            textUSer2 = itemView.findViewById(R.id.textUSer2);
////            textNumber = itemView.findViewById(R.id.textNumber);
////            textGiftName = itemView.findViewById(R.id.textGiftName);
////            box = itemView.findViewById(R.id.box);
////            OkBtn = itemView.findViewById(R.id.ok_btn);
////            wuYongOne = itemView.findViewById(R.id.wuyong_one);
////        }
////
////        @Override
////        protected void onBind(PushBean pushBean) {
////            if (pushBean != null) {
////                if ("gift".equals(pushBean.type)) {
////                    wuYongOne.setText("惊现土豪~");
////                    textUSer1.setText(pushBean.getData().getFrom_name());
////                    textUSer2.setText(pushBean.getData().getUser_name());
//////                    textNumber.setText(pushBean.getData().getNum() + "个");
////                    box.setText("赠送给");
////                    textGiftName.setText(pushBean.getData().getGift_name()+"x"+pushBean.getData().getNum());
////                    GlideArms
////                            .with(MainActivity.this)
////                            .load(pushBean.getData().getImg())
////                            .into(imgGift);
////                    OkBtn.setVisibility(View.VISIBLE);
////                } else if ("award".equals(pushBean.type)) {
//////                    String text = "哇哦，"+pushBean.getData().getUser_name()+"在"+pushBean.getData().getBoxclass()+"开出了"+pushBean.getData().getGift_name()+"，真是太优秀了！";
////                    wuYongOne.setText("哇哦~");
////                    textUSer1.setText(pushBean.getData().getUser_name());
////                    box.setText("在" + pushBean.getData().getBoxclass() + "开出了");
////                    textUSer2.setText(pushBean.getData().getGift_name());
////                    textNumber.setText("");
//////                    textNumber.setText("，真是太优秀了！");
////                    GlideArms
////                            .with(MainActivity.this)
////                            .load(pushBean.getData().getImg())
////                            .into(imgGift);
////                    OkBtn.setVisibility(View.GONE);
////
////                    //所有房间公屏提示
//////                    MessageEvent messageEvent = new MessageEvent();
//////                    messageEvent.setStateMessage(StateMessage.PEOPLE_OPEN_GEMSTONE);
//////                    messageEvent.setObject(text);
//////                    EventBus.getDefault().post(messageEvent);
////
////                }
////            }
////        }
////    }

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
                UIMessage userName = data;

                data.getMessage().getContent().getUserInfo();
//                LogUtils.debugInfo("用户名称："+userName);
            }
        }
    }
}
