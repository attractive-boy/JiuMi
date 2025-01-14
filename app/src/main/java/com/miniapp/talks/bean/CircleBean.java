package com.miniapp.talks.bean;

public class CircleBean {

    public String id;

    public float width;

    public double alpha;

    public float maxWidth;//扩散的最远距离

    public boolean isStartAnimate = false;

    public CircleBean(String id,float width, double alpha,float maxW) {
        this.id = id;
        this.width = width;
        this.alpha = alpha;
        this.maxWidth = maxW;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public float getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(float maxWidth) {
        this.maxWidth = maxWidth;
    }

    public boolean isStartAnimate() {
        return isStartAnimate;
    }

    public void setStartAnimate(boolean startAnimate) {
        isStartAnimate = startAnimate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
