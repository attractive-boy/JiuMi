package com.miniapp.talks.bean;

import com.google.gson.annotations.SerializedName;
import com.miniapp.talks.bean.OpenBoxBean;
import com.miniapp.talks.bean.PushBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:sgm
 * 描述:
 * messageType     1：正常的 聊天消息  2：进入房间  3： 刷新麦序列表  4 ：礼物消息  5 ：表型消息
 */
public class MessageBean {

    /**
     * message : 0
     * messageType : 0
     * nickName : 0
     * user_id : 0
     */

    private String message;
    private String messageType;
    private String nickName;
    private String user_id;
    private String is_answer;
    private String emoji;
    private String t_length;
    private String room_name;
    private String room_type;
    private String room_background;
    private String room_intro;
    public String show_img;
    public String giftNum;
    public String type;
    public String show_gif_img;
    public String e_name;
    public List<Data> userInfo;
    public String vip_img;
    public String hz_img;
    public String nick_color = "";
    public String vip_tx = "";
    public String headimgurl = "";

    public String toNickName = "";
    public String toUser_id = "";
    public String toNick_color = "";
    public String toheadimgurl = "";
    public String cp_tx = "";//cp同房特效
    public String cp_xssm = "";//cp上麦特效
    public String ltk_left;//聊天框左上图
    public String ltk;//聊天框
    public String ltk_right;//聊天框右下图
    public String box_class = "1";//1 普通宝箱，2 守护宝箱
    public String hot = "";//房间热度
    public List<OpenBoxBean.DataBean.AwardListBean> awardList =new ArrayList<>();//宝箱奖品
    public int[] location = new int[2];
    public String name = "";
    public String exp = "";
    public List<PushBean.DataBean> pushUser;
    public PushAwards push_awards;

    public static class Data{

        @SerializedName(value = "user_id", alternate = "toUser_id")
        public String userId;
        @SerializedName(value = "nickName", alternate = "toNickName")
        public String nickname;
        public String headimgurl;
        public String toNick_color = "";
    }
    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getRoom_background() {
        return room_background;
    }

    public void setRoom_background(String room_background) {
        this.room_background = room_background;
    }

    public String getRoom_intro() {
        return room_intro;
    }

    public void setRoom_intro(String room_intro) {
        this.room_intro = room_intro;
    }

    public String getIs_answer() {
        return is_answer;
    }

    public void setIs_answer(String is_answer) {
        this.is_answer = is_answer;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getT_length() {
        return t_length;
    }

    public void setT_length(String t_length) {
        this.t_length = t_length;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

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

    public static class PushAwards{
        public String user_name = "";
        public String gift_name = "";
    }
}
