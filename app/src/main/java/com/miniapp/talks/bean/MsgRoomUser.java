package com.miniapp.talks.bean;

public class MsgRoomUser {

    String nickName = "";
    String user_id = "";
    String nick_color = "";

    String toNickName = "";
    String toUser_id = "";
    String toNick_color = "";

    String vip_tx = "";
    String vip_img = "";
    String hz_img = "";
    String messageType = "";

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNick_color() {
        return nick_color;
    }

    public void setNick_color(String nick_color) {
        this.nick_color = nick_color;
    }

    public String getToNickName() {
        return toNickName;
    }

    public void setToNickName(String toNickName) {
        this.toNickName = toNickName;
    }

    public String getToUser_id() {
        return toUser_id;
    }

    public void setToUser_id(String toUser_id) {
        this.toUser_id = toUser_id;
    }

    public String getToNick_color() {
        return toNick_color;
    }

    public void setToNick_color(String toNick_color) {
        this.toNick_color = toNick_color;
    }

    public String getVip_tx() {
        return vip_tx;
    }

    public void setVip_tx(String vip_tx) {
        this.vip_tx = vip_tx;
    }

    public String getVip_img() {
        return vip_img;
    }

    public void setVip_img(String vip_img) {
        this.vip_img = vip_img;
    }

    public String getHz_img() {
        return hz_img;
    }

    public void setHz_img(String hz_img) {
        this.hz_img = hz_img;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
