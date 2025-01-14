package com.miniapp.talks.view;

import android.view.animation.Interpolator;

import com.jess.arms.utils.LogUtils;

public class GuangQuanInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        LogUtils.debugInfo("插值器==="+input);
        if (input > 0 && input < 0.8){
            return input *2;
        }else{
            return input;
        }
    }
}
