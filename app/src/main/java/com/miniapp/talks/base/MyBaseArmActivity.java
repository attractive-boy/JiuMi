package com.miniapp.talks.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.tu.loadingdialog.LoadingDailog;
import com.baidu.mobstat.StatService;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gyf.immersionbar.ImmersionBar;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.kongzue.dialog.util.DialogSettings;
import com.miniapp.talks.BuildConfig;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.login.LoginActivity;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.app.Api;
import com.miniapp.talks.app.converter.ApiIOException;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.bean.EnterRoom;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.popup.PwdWindow;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.utils.PwdEditText;
import com.miniapp.talks.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 基类
 */
public abstract class MyBaseArmActivity extends BaseActivity implements IView {
    //    public Context mContext;
    public LoadingDailog dialog;

    protected String mClassName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

//        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);
//        StatusBarUtil.setLightMode(this);
        String className = this.getClass().getName();

        String realClassName = className.substring(className
                .lastIndexOf(".") + 1);

        mClassName = realClassName;
        ImmersionBar.with(this).statusBarColor(R.color.white)
                .autoStatusBarDarkModeEnable(true,0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
                .addTag(mClassName)//给以上设置的参数打标记
//                .getTag("tag")  //根据tag获得沉浸式参数
//                .reset()  //重置所以沉浸式参数
                .init();//设置状态栏白色
        super.onCreate(savedInstanceState);
//        mContext = this;
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true);
        dialog = loadBuilder.create();
        DialogSettings.style = (DialogSettings.STYLE.STYLE_IOS);
        RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus connectionStatus) {
                LogUtils.debugInfo("====sgm", "====顶没顶" + connectionStatus.getValue());
                if (connectionStatus.getValue() == ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT.getValue()) {
                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.LOGOUT));
                    UserManager.layout();
                    Intent intent = new Intent(MyBaseArmActivity.this, LoginActivity.class);
                    intent.putExtra("sign", 1);
                    ArmsUtils.startActivity(intent);
                    RongIM.getInstance().logout();
                }
            }
        });
    }

    /**
     * 设置状态栏颜色
     *
     * @param colorPrimaryDark
     */
    protected void setThemeColor(int colorPrimaryDark) {

        int color = Color.parseColor("#ffffffff");

        int colors =  colorPrimaryDark;

        if (color == colors) {
            setThemeColorWhite();
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor( colorPrimaryDark);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            Window window = getWindow();
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        }
    }

    /**
     * 设置状态栏白色，黑字体
     */
    protected void setThemeColorWhite() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
//            getWindow().setStatusBarColor(0xffffffff);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            Window window = getWindow();
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        }
    }

    public void showToast(String content) {
        ToastUtil.showToast(this, content);
    }

    public void toast(String content) {
        ToastUtil.showToast(this, content);
    }

    @Override
    public void showMessage(@NonNull String message) {
        showToast(message);
    }

    protected void logD(String msg) {
        Log.d("123", msg);
    }


    protected void setToolbarTitle(String title, boolean boo) {
        TextView tvTitle = (TextView) findViewById(R.id.toolbar_title);
        if (tvTitle != null) {
            tvTitle.setText(title);
            if (boo) {
                tvTitle.setVisibility(View.VISIBLE);
            } else {
                tvTitle.setVisibility(View.GONE);
            }
        }
    }

//    protected void hideToolbarLeftImage() {
//        ImageView ivBack = (ImageView) findViewById(R.id.iv_back);
//        if (ivBack != null) {
//            DisPlayUtils.setViewGone(ivBack);
//        }
//        View ivBack2 = findViewById(R.id.toolbar_back);
//        if (ivBack2 != null) {
//            DisPlayUtils.setViewGone(ivBack);
//        }
//    }

    protected void setToolbarRightText(String content, View.OnClickListener onClickListener, int color) {
        TextView tvRight = (TextView) findViewById(R.id.rightTitle);
        if (tvRight != null) {
            tvRight.setTextColor(getResources().getColor(color));
            tvRight.setText(content);
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setOnClickListener(onClickListener);
        }
    }

    protected void setToolbarRightConfirmText(String content, View.OnClickListener onClickListener) {
        TextView tvRight = (TextView) findViewById(R.id.rightConfirm);
        if (tvRight != null) {
            tvRight.setText(content);
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setOnClickListener(onClickListener);
        }
    }

    /**
     * 隐藏键盘
     *
     * @param view
     */
    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    /**
     * 弹键盘
     *
     * @param view
     */
    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    public void loadImage(ImageView imageView, String url, int defaultId) {
        ArmsUtils.obtainAppComponentFromContext(this)
                .imageLoader()
                .loadImage(this, ImageConfigImpl
                        .builder()
                        .url(url)
                        .placeholder(defaultId)
                        .imageView(imageView)
                        .errorPic(defaultId)
                        .build());
    }

    public void loadImage(ImageView imageView, int url, int defaultId) {
        GlideArms.with(this)
                .load(url)
                .placeholder(defaultId)
                .error(defaultId)
                .into(imageView);
    }

    /**
     * 加载一次gif
     */
    public static void loadOneTimeGif(Context context, ImageView imageView, String url, GifListener gifListener) {
        GlideArms
                .with(context)
                .asGif()
                .load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        try {
                            Field gifStateField = GifDrawable.class.getDeclaredField("state");
                            gifStateField.setAccessible(true);
                            Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
                            Field gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader");
                            gifFrameLoaderField.setAccessible(true);
                            Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
                            Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder");
                            gifDecoderField.setAccessible(true);
                            Class gifDecoderClass = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder");
                            Object gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)));
                            Method getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", int.class);
                            getDelayMethod.setAccessible(true);
                            //设置只播放一次
                            resource.setLoopCount(1);
                            //获得总帧数
                            int count = resource.getFrameCount();
                            int delay = 0;
                            for (int i = 0; i < count; i++) {
                                //计算每一帧所需要的时间进行累加
                                delay += (int) getDelayMethod.invoke(gifDecoder, i);
                            }
                            imageView.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (gifListener != null) {
                                        gifListener.gifPlayComplete();
                                    }
                                }
                            }, delay);
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                }).into(imageView);
    }

    /**
     * Gif播放完毕回调
     */
    public interface GifListener {
        void gifPlayComplete();
    }

    public void showDialogLoding() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public void disDialogLoding() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }


    /**
     * 请求房间信息
     */
    public void enterData(String uid, String room_pass, CommonModel commonModel, int flag, String headUrl) {
        RxUtils.loading(commonModel.enter_room(uid, room_pass,
                String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<EnterRoom>(mErrorHandler) {
                    @Override
                    public void onNext(EnterRoom enterRoom) {
                        if (AdminHomeActivity.isStart && !uid.equals(AdminHomeActivity.mContext.getUid())) {
                            AdminHomeActivity.isStart = false;
                            AdminHomeActivity.mContext.finish();//先销毁
                        }
                        Intent intent = new Intent(MyBaseArmActivity.this, AdminHomeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("enterRoom", enterRoom);
                        bundle.putString("uid", uid);
                        bundle.putInt("flag", flag);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        if (t instanceof ApiIOException) {
                            ApiIOException apiIOException = (ApiIOException) t;
                            String code = apiIOException.code;
                            if (code.equals("4")) {
                                //弹窗
//                                new MaterialDialog.Builder(MyBaseArmActivity.this)
//                                        .title("请输入密码")
//                                        //限制输入的长度
//                                        .inputRangeRes(2, 10, R.color.colorPrimary)
//                                        //限制输入类型
//                                        .inputType(InputType.TYPE_CLASS_PHONE)
//                                        .input("输入密码", null, new MaterialDialog.InputCallback() {
//                                            @Override
//                                            public void onInput(MaterialDialog dialog, CharSequence input) {
//                                                if (!TextUtils.isEmpty(input.toString())) {
//                                                    enterData(uid, input.toString(), commonModel, flag);
//                                                } else {
//                                                    showToast("请输入密码");
//                                                }
//                                            }
//                                        })
//                                        .positiveText("确定")
//                                        .show();
                                PwdWindow pwdWindow = new PwdWindow(MyBaseArmActivity.this);
                                pwdWindow.show();
                                if (!TextUtils.isEmpty(headUrl) && !"0".equals(headUrl)) {
                                    loadImage(pwdWindow.getHeadImage(), headUrl, R.mipmap.gender_zhuce_girl);
                                }
                                pwdWindow.getPwdText().setOnTextChangeListener(pwd -> {
                                    if (pwd.length()==pwdWindow.getPwdText().getTextLength()){
                                        RxUtils.loading(commonModel.enter_room(uid, pwd, String.valueOf(UserManager.getUser().getUserId())), MyBaseArmActivity.this)
                                                .subscribe(new ErrorHandleSubscriber<EnterRoom>(mErrorHandler) {
                                                    @Override
                                                    public void onNext(EnterRoom enterRoom) {
                                                        if (AdminHomeActivity.isStart && !uid.equals(AdminHomeActivity.mContext.getUid())) {
                                                            AdminHomeActivity.isStart = false;
                                                            AdminHomeActivity.mContext.finish();//先销毁
                                                        }
                                                        Intent intent = new Intent(MyBaseArmActivity.this, AdminHomeActivity.class);
                                                        Bundle bundle = new Bundle();
                                                        bundle.putSerializable("enterRoom", enterRoom);
                                                        bundle.putString("uid", uid);
                                                        bundle.putInt("flag", flag);
                                                        intent.putExtras(bundle);
                                                        startActivity(intent);
                                                        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                                                        pwdWindow.dismiss();
                                                    }

                                                    @Override
                                                    public void onError(Throwable t) {
                                                        ApiIOException apiIOException = (ApiIOException) t;
                                                        String code = apiIOException.code;
                                                        if ("4".equals(code)) {
                                                            pwdWindow.getErrorTit().setVisibility(View.VISIBLE);
                                                            pwdWindow.getPwdText().clearText();
                                                        }
                                                    }
                                                });
                                    }
                                });
                            }
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
        if(!Api.IS_DEBUG){
            StatService.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
        if(!Api.IS_DEBUG){
            StatService.onPause(this);
        }
    }
}

