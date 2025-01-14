package com.miniapp.talks.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class EnterRoom implements Serializable {


    /**
     * code : 1
     * message : 进入成功
     * room_info : [{"giftPrice":369,"headimgurl":"http://59.110.169.251/upload/avatar/20190613/25220_155229_4875.jpg","is_afk":1,"is_mykeep":2,"is_sound":1,"microphone":"1151734,0,1,0,0,0,0,1","name":"开黑","nickname":"田中华(Mythe)","now_total":0,"numid":"113114","roomAdmin":"1151706,1151734","roomJudge":"1151707","roomSound":"1151707","roomSpeak":"1151702#1561714431,1151703#1561800735,1151704#1561800744","room_background":"","room_cover":"http://59.110.169.251/uploadhttp://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg","room_intro":"欢迎来到我的房间玩~","room_name":"田中华(Mythe)","room_status":"1","room_type":"3","room_welcome":"~~~~~欢迎来到我的房间~~~~~~\n希望你玩的开心","sex":1,"uid":113114,"uid_black":1,"uid_sound":1,"user_type":5}]
     */

    private int code;
    private String message;
    @SerializedName("data")
    private List<RoomInfoBean> room_info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RoomInfoBean> getRoom_info() {
        return room_info;
    }

    public void setRoom_info(List<RoomInfoBean> room_info) {
        this.room_info = room_info;
    }

    public static class RoomInfoBean implements Serializable{
        /**
         * giftPrice : 369
         * headimgurl : http://59.110.169.251/upload/avatar/20190613/25220_155229_4875.jpg
         * is_afk : 1
         * is_mykeep : 2
         * is_sound : 1
         * microphone : 1151734,0,1,0,0,0,0,1
         * name : 开黑
         * nickname : 田中华(Mythe)
         * now_total : 0
         * numid : 113114
         * roomAdmin : 1151706,1151734
         * roomJudge : 1151707
         * roomSound : 1151707
         * roomSpeak : 1151702#1561714431,1151703#1561800735,1151704#1561800744
         * room_background :
         * room_cover : http://59.110.169.251/uploadhttp://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg
         * room_intro : 欢迎来到我的房间玩~
         * room_name : 田中华(Mythe)
         * room_status : 1
         * room_type : 3
         * room_welcome : ~~~~~欢迎来到我的房间~~~~~~
         希望你玩的开心
         * sex : 1
         * uid : 113114
         * uid_black : 1
         * uid_sound : 1
         * user_type : 5
         */

        private int giftPrice;
        private String headimgurl;
        private int is_afk;
        private int is_mykeep;
        private int is_sound;
        private String microphone;
        private String name;
        private String nickname;
        private int now_total;
        private String numid;
        private String roomAdmin;
        private String roomJudge;
        private String roomSound;
        private String roomSpeak;
        private String room_background;
        private String room_cover;
        private String room_intro;
        private String room_name;
        private String room_status;
        private String room_type;
        private String room_welcome;
        public String back_img;
        public String mic_color;//麦上光圈颜色
        public String txk;//特效卡
        private int sex;
        private int uid;
        private int uid_black;
        private int uid_sound;
        private int user_type;
        private String ml;
        public String hot;

        public String getMl() {
            return ml;
        }

        public void setMl(String ml) {
            this.ml = ml;
        }

        public int getGiftPrice() {
            return giftPrice;
        }

        public void setGiftPrice(int giftPrice) {
            this.giftPrice = giftPrice;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public int getIs_afk() {
            return is_afk;
        }

        public void setIs_afk(int is_afk) {
            this.is_afk = is_afk;
        }

        public int getIs_mykeep() {
            return is_mykeep;
        }

        public void setIs_mykeep(int is_mykeep) {
            this.is_mykeep = is_mykeep;
        }

        public int getIs_sound() {
            return is_sound;
        }

        public void setIs_sound(int is_sound) {
            this.is_sound = is_sound;
        }

        public String getMicrophone() {
            return microphone;
        }

        public void setMicrophone(String microphone) {
            this.microphone = microphone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getNow_total() {
            return now_total;
        }

        public void setNow_total(int now_total) {
            this.now_total = now_total;
        }

        public String getNumid() {
            return numid;
        }

        public void setNumid(String numid) {
            this.numid = numid;
        }

        public String getRoomAdmin() {
            return roomAdmin;
        }

        public void setRoomAdmin(String roomAdmin) {
            this.roomAdmin = roomAdmin;
        }

        public String getRoomJudge() {
            return roomJudge;
        }

        public void setRoomJudge(String roomJudge) {
            this.roomJudge = roomJudge;
        }

        public String getRoomSound() {
            return roomSound;
        }

        public void setRoomSound(String roomSound) {
            this.roomSound = roomSound;
        }

        public String getRoomSpeak() {
            return roomSpeak;
        }

        public void setRoomSpeak(String roomSpeak) {
            this.roomSpeak = roomSpeak;
        }

        public String getRoom_background() {
            return room_background;
        }

        public void setRoom_background(String room_background) {
            this.room_background = room_background;
        }

        public String getRoom_cover() {
            return room_cover;
        }

        public void setRoom_cover(String room_cover) {
            this.room_cover = room_cover;
        }

        public String getRoom_intro() {
            return room_intro;
        }

        public void setRoom_intro(String room_intro) {
            this.room_intro = room_intro;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getRoom_status() {
            return room_status;
        }

        public void setRoom_status(String room_status) {
            this.room_status = room_status;
        }

        public String getRoom_type() {
            return room_type;
        }

        public void setRoom_type(String room_type) {
            this.room_type = room_type;
        }

        public String getRoom_welcome() {
            return room_welcome;
        }

        public void setRoom_welcome(String room_welcome) {
            this.room_welcome = room_welcome;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid_black() {
            return uid_black;
        }

        public void setUid_black(int uid_black) {
            this.uid_black = uid_black;
        }

        public int getUid_sound() {
            return uid_sound;
        }

        public void setUid_sound(int uid_sound) {
            this.uid_sound = uid_sound;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }
    }
}
