package com.miniapp.talks.bean;

/**
 * 消息类型
 */
public enum StateMessage {


    CLOSE_GIFT_WINDOW(2),//关闭礼物弹窗
    PEOPLE_OPEN_GEMSTONE(1),//有人开宝箱
    SEND_GEMSTONE(0);//发送宝石

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    StateMessage(int state) {
        this.state = state;
    }
}
