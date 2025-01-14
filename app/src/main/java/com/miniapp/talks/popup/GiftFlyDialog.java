package com.miniapp.talks.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.MessageBean;
import com.miniapp.talks.bean.Microphone;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class GiftFlyDialog extends PopupWindow {

    private View mMenuView;
    private Context mContext;

    RelativeLayout mRootLayout;

    int feiLeft;

    int feiTop;

    int[] mStartLocation;

    public View getmMenuView() {
        return mMenuView;
    }

    public GiftFlyDialog(Activity context, int resourceId,int left,int top,int[] startLocation) {
        super(context);
        this.mContext = context;
        feiLeft = left;
        feiTop = top;
        mStartLocation = startLocation;
        mMenuView = LayoutInflater.from(context).inflate(resourceId, null);

        mRootLayout = mMenuView.findViewById(R.id.layout_gift_fly_root);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        int screenHeight = QMUIDisplayHelper.getScreenHeight(mContext);
        LogUtils.debugInfo("屏幕高====="+screenHeight);
        this.setHeight(screenHeight);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(false);
        this.setOutsideTouchable(false);
        this.setTouchable(false);
        // 刷新状态
//        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        //设置透明
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = 0.7f;
        context.getWindow().setAttributes(params);
    }


    /**
     * 设置飞用户头像
     *
     */
    public void startImageFly(ImageView imgTagart,  String imgUrl) {

        RelativeLayout.LayoutParams paramsImgFei = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsImgFei.leftMargin = mStartLocation[0]+40;
        paramsImgFei.topMargin = mStartLocation[1]-40;
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(paramsImgFei);
//        imageView.setBackgroundColor(mContext.getResources().getColor(R.color.red));
        mRootLayout.addView(imageView);

        GlideArms.with(mContext)
                .load(imgUrl)
                .placeholder(R.mipmap.room_kazuo_kong)
                .error(R.mipmap.room_kazuo_kong)
                .circleCrop()
                .into(imageView);

        imageView.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);

        int[] location = new int[2];
        imgTagart.getLocationOnScreen(location);
        int height = imgTagart.getHeight()/2;
//        startAnimotion(location[0] - feiLeft + 70, location[1] - feiTop + 150, imgVisiFei);
        startAnimotion(location[0]- mStartLocation[0]-40, location[1] -mStartLocation[1]-height+40, imageView);
    }

    public void startImageFly(ImageView imgTagart,  String imgUrl,boolean isSeven){

        RelativeLayout.LayoutParams paramsImgFei = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsImgFei.leftMargin = mStartLocation[0]+40;
        paramsImgFei.topMargin = mStartLocation[1]-40;
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(paramsImgFei);
//        imageView.setBackgroundColor(mContext.getResources().getColor(R.color.red));
        mRootLayout.addView(imageView);

        GlideArms.with(mContext)
                .load(imgUrl)
                .placeholder(R.mipmap.room_kazuo_kong)
                .error(R.mipmap.room_kazuo_kong)
                .circleCrop()
                .into(imageView);

        imageView.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);

        int[] location = new int[2];
        imgTagart.getLocationOnScreen(location);
        int height = imgTagart.getHeight()/2;
//        startAnimotion(location[0] - feiLeft + 70, location[1] - feiTop + 150, imgVisiFei);
        if(isSeven){
            startAnimotion(location[0]- mStartLocation[0]+5, location[1] -mStartLocation[1]-height+140, imageView);
        } else {
            startAnimotion(location[0]- mStartLocation[0]-40, location[1] -mStartLocation[1]-height+40, imageView);
        }
    }


    /**
     * 飞的动画
     */
    private void startAnimotion(int width, int height, ImageView imgVisiFei) {

        imgVisiFei.setVisibility(View.VISIBLE);
        final TranslateAnimation translateAnimation = new TranslateAnimation(0,
                width, 0, height);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillBefore(false);
        translateAnimation.setRepeatMode(ScaleAnimation.RESTART);
        translateAnimation.setRepeatCount(0);

        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imgVisiFei.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (imgVisiFei != null) {
                            imgVisiFei.clearAnimation();
                            translateAnimation.cancel();
                            imgVisiFei.setVisibility(View.GONE);
                            RelativeLayout layoutParent = (RelativeLayout) imgVisiFei.getParent();
                            layoutParent.removeView(imgVisiFei);
                            int size = layoutParent.getChildCount();
                            if(size == 0){//所有动画都完成，dismiss
                                dismiss();
                            }
                        }
                    }
                }, 1000);//延时1秒后隐藏

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //开始动画
        imgVisiFei.startAnimation(translateAnimation);
        translateAnimation.start();
    }


    @Override
    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = (MyBaseArmActivity) mContext;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }
}
