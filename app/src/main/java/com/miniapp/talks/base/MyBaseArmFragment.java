package com.miniapp.talks.base;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.tu.loadingdialog.LoadingDailog;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.app.converter.ApiIOException;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.bean.EnterRoom;
import com.miniapp.talks.popup.PwdWindow;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 基类
 */
public abstract class MyBaseArmFragment extends LazyBaseFragments implements IView {
    //    public Context mContext;
    public LoadingDailog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        mContext = getActivity();
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(getActivity())
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true);
        dialog = loadBuilder.create();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void visibleToLoadData() {

    }

    /**
     * 设置状态栏颜色
     *
     * @param colorPrimaryDark
     */
    protected void setThemeColor(int colorPrimaryDark) {

        int color = Color.parseColor("#ffffffff");

        int colors = ContextCompat.getColor(getActivity(), colorPrimaryDark);

        if (color == colors) {
            setThemeColorWhite();
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(), colorPrimaryDark));
        }
        if (Build.VERSION.SDK_INT >= 23) {
            Window window = getActivity().getWindow();
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
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
//            getWindow().setStatusBarColor(0xffffffff);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            Window window = getActivity().getWindow();
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        }
    }

    public void showToast(String content) {
        ToastUtil.showToast(mContext, content);
    }


    @Override
    public void showMessage(@NonNull String message) {
        showToast(message);
    }

    protected void logD(String msg) {
        Log.d("123", msg);
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
                        Intent intent = new Intent(getActivity(), AdminHomeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("enterRoom", enterRoom);
                        bundle.putString("uid", uid);
                        bundle.putInt("flag", flag);
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        if (t instanceof ApiIOException) {
                            ApiIOException apiIOException = (ApiIOException) t;
                            String code = apiIOException.code;
                            if (code.equals("4")) {
                                //弹窗
//                                new MaterialDialog.Builder(getActivity())
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
                                PwdWindow pwdWindow = new PwdWindow(getActivity());
                                pwdWindow.show();
                                if (!TextUtils.isEmpty(headUrl) && !"0".equals(headUrl)) {
                                    loadImage(pwdWindow.getHeadImage(), headUrl, R.mipmap.gender_zhuce_girl);
                                }
                                pwdWindow.getPwdText().setOnTextChangeListener(pwd -> {
                                    if (pwd.length()==pwdWindow.getPwdText().getTextLength()){
                                        RxUtils.loading(commonModel.enter_room(uid, pwd, String.valueOf(UserManager.getUser().getUserId())), MyBaseArmFragment.this)
                                                .subscribe(new ErrorHandleSubscriber<EnterRoom>(mErrorHandler) {
                                                    @Override
                                                    public void onNext(EnterRoom enterRoom) {
                                                        if (AdminHomeActivity.isStart && !uid.equals(AdminHomeActivity.mContext.getUid())) {
                                                            AdminHomeActivity.isStart = false;
                                                            AdminHomeActivity.mContext.finish();//先销毁
                                                        }
                                                        Intent intent = new Intent(getActivity(), AdminHomeActivity.class);
                                                        Bundle bundle = new Bundle();
                                                        bundle.putSerializable("enterRoom", enterRoom);
                                                        bundle.putString("uid", uid);
                                                        bundle.putInt("flag", flag);
                                                        intent.putExtras(bundle);
                                                        startActivity(intent);
                                                        getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
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


    public void loadImage(ImageView imageView, String url, int defaultId) {
        ArmsUtils.obtainAppComponentFromContext(getActivity())
                .imageLoader()
                .loadImage(getActivity(), ImageConfigImpl
                        .builder()
                        .url(url)
                        .placeholder(defaultId)
                        .imageView(imageView)
                        .errorPic(defaultId)
                        .build());
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onResume(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(getActivity());
    }
}
