package com.miniapp.talks.bean;

import android.animation.TypeEvaluator;

import com.jess.arms.utils.LogUtils;

public class CircleBeanEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float v, Object startValue, Object endValue) {

        CircleBean startBean = (CircleBean) startValue;

        CircleBean endBean = (CircleBean) endValue;

//        float width = startBean.width+ v*(endBean.width-startBean.width);

        float width = v*endBean.width;

//        LogUtils.debugInfo("v===width=="+width);

//        double starAlpha = 255 - startBean.width * (255 / ((double) startBean.maxWidth / 2));
//
//        double endAlpha = 255 - endBean.width * (255 / ((double) endBean.maxWidth / 2));
//
//        double alpha = starAlpha+v*endAlpha;

//        double alpha = 255-v*255;
        double alpha = 255-v*200;

        CircleBean newCircleBean = new CircleBean(startBean.id,width, alpha, startBean.getMaxWidth());

        return newCircleBean;
    }
}
