package com.miniapp.talks.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * 算是重写了个吐司,优化误操作多的时候疯狂弹吐司的情况。
 */
public class ToastUtil {

    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            if (mToast != null)
                mToast.cancel();
            mToast = null;
        }
    };

    public static void showToast(Context mContext, String text) {
        mHandler.removeCallbacks(r);
        if (mToast != null) {
            mToast.setText(text);
        } else {
            if (mContext != null)
                mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
            //下边是在吐司中加入图片
//            LinearLayout toastView = (LinearLayout) mToast.getView();
//            ImageView imageCodeProject = new ImageView(mContext);
//            if (type == 1) {
//                imageCodeProject.setImageResource(R.drawable.ic_toast_success);
//                toastView.addView(imageCodeProject, 0);
//            } else if (type == 2) {
//                imageCodeProject.setImageResource(R.drawable.ic_toast_false);
//                toastView.addView(imageCodeProject, 0);
//            } else if (type == 3) {
//                imageCodeProject.setImageResource(R.drawable.ic_toast_tips);
//                toastView.addView(imageCodeProject, 0);
//            }
        }
//        mToast.setGravity(Gravity.CENTER, 0, 0);
        mHandler.postDelayed(r, 3000);
        if (mToast != null)
            mToast.show();
    }

//    public static void showToast(Context mContext, int resId, int duration) {
//        showToast(mContext, Integer.parseInt(mContext.getResources().getString(resId)), duration);
//    }
}