/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.miniapp.talks.app;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.mobstat.StatService;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.integration.cache.IntelligentCache;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.lzx.starrysky.manager.MusicManager;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.miniapp.talks.BuildConfig;
import com.miniapp.talks.activity.message.LiaoBaExtensionModule;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.PushBean;
import com.miniapp.talks.utils.GlideImageLoader;
import com.pgyersdk.crash.PgyCrashManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;

import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
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
import timber.log.Timber;

import static com.miniapp.talks.utils.Constant.KBXTUISONG;
import static com.miniapp.talks.utils.Constant.TUISONG;

/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * <p>
 * Created by JessYan on 04/09/2017 17:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {
//          MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(@NonNull Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        if (Api.IS_DEBUG) {//Timber初始化
            //Timber 是一个日志框架容器,外部使用统一的Api,内部可以动态的切换成任何日志框架(打印策略)进行日志打印
            //并且支持添加多个日志框架(打印策略),做到外部调用一次 Api,内部却可以做到同时使用多个策略
            //比如添加三个策略,一个打印日志,一个将日志保存本地,一个将日志上传服务器
            Timber.plant(new Timber.DebugTree());
            // 如果你想将框架切换为 Logger 来打印日志,请使用下面的代码,如想切换为其他日志框架请根据下面的方式扩展
//                    Logger.addLogAdapter(new AndroidLogAdapter());
//                    Timber.plant(new Timber.DebugTree() {
//                        @Override
//                        protected void log(int priority, String tag, String message, Throwable t) {
//                            Logger.log(priority, tag, message, t);
//                        }
//                    });
            ButterKnife.setDebug(true);
        }
        //LeakCanary 内存泄露检查
        //使用 IntelligentCache.KEY_KEEP 作为 key 的前缀, 可以使储存的数据永久存储在内存中
        //否则存储在 LRU 算法的存储空间中, 前提是 extras 使用的是 IntelligentCache (框架默认使用)
        ArmsUtils.obtainAppComponentFromContext(application).extras()
                .put(IntelligentCache.getKeyOfKeep(RefWatcher.class.getName())
                        , BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
        //设置全局的下拉刷新
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new ClassicsHeader(context));
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context));
        //图片选择
        initImagePicker();
        //数据库初始化
        LitePal.initialize(application);
        if (!Api.IS_DEBUG) {
            CrashReport.initCrashReport(application, "", Api.IS_DEBUG);
        }
        //音乐库初始化
        MusicManager.initMusicManager(application);
        //读取本地用户信息
        UserManager.initData();
        //融云
        RongIM.init(application);//正式环境的key


        //分享
        initShare(application);

        //蒲公英
//        PgyCrashManager.register(application);  // 弃用
        PgyCrashManager.register(); //推荐使用,蒲公英
        //上报异常
        try {
            // code
        } catch (Exception e) {
            /** 新版本 **/
            PgyCrashManager.reportCaughtException(e);
        }
        //百度统计
//        StatService.autoTrace(application);
        if (!Api.IS_DEBUG) {
            StatService.start(application);
            StatService.setDebugOn(Api.IS_DEBUG);
        }
        // 获取测试设备ID
//        String testDeviceId = StatService.getTestDeviceId(application);
//// 日志输出
//        android.util.Log.d("BaiduMobStat", "Test DeviceId : " + testDeviceId);
    }

    private void initShare(Application application) {
        UMConfigure.init(application, "5db7a7270cafb2f90f00103f"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "1ddad8fc4e5219fd0e2b89ef8f8bdd45");
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(application);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                LogUtils.debugInfo("sgm", "====注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtils.debugInfo("sgm", "====注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });
        PlatformConfig.setWeixin("wx23a0e7e411a9b40d","54ef472539e23874df6be33135d094aa");
        PlatformConfig.setQQZone("101813872", "8358b3268e4a3153181b2d35f1acafc8");
        PlatformConfig.setSinaWeibo("3416535971", "f66ee46acae3d312dabc33637b5b1304", "http://sns.whalecloud.com");
        mPushAgent.setMessageHandler(messageHandler);
    }

    UmengMessageHandler messageHandler = new UmengMessageHandler() {

        @Override
        public Notification getNotification(Context context, UMessage msg) {
            Map<String, String> extra = msg.extra;
            String test = extra.get("");
            LogUtils.debugInfo("====消息：" + extra.toString());
            if (!TextUtils.isEmpty(test)) {

                try {
                    JSONObject userJson = JSONObject.parseObject(test);
                    PushBean pushBean = JSON.toJavaObject(userJson, PushBean.class);
                    if (TextUtils.equals(pushBean.type, "")) {
                        EventBus.getDefault().post(new FirstEvent(pushBean, TUISONG));
                    } else if (TextUtils.equals(pushBean.type, "")) {
                        EventBus.getDefault().post(new FirstEvent(pushBean, KBXTUISONG));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return super.getNotification(context, msg);
        }
    };

    @Override
    public void onTerminate(@NonNull Application application) {

    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
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
                UIMessage userName = data;
//                LogUtils.debugInfo("用户名称："+userName);
            }
        }
    }
}
