package com.miniapp.talks.bean;

public class OneImageBean {
    private String imageString;

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    @Override
    public String toString() {
        return "OneImageBean{" +
                "imageString='" + imageString + '\'' +
                '}';
    }
}
