package com.miniapp.talks.bean;

import java.util.List;

public class RoomUsersBean {


    /**
     * code : 1
     * message : 请求成功
     * data : {"mic_user":[{"id":1151896,"nickname":"春天花","headimgurl":"http://47.92.85.75/upload//avatar/20190816/15659281014158.png","is_mic":0}],"room_user":[{"id":1151896,"nickname":"春天花","headimgurl":"http://47.92.85.75/upload//avatar/20190816/15659281014158.png","is_mic":0}],"sea_user":[{"id":1151896,"nickname":"春天花","headimgurl":"http://47.92.85.75/upload//avatar/20190816/15659281014158.png","is_mic":0}]}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MicUserBean> mic_user;
        private List<MicUserBean> room_user;
        private List<MicUserBean> sea_user;

        public List<MicUserBean> getMic_user() {
            return mic_user;
        }

        public void setMic_user(List<MicUserBean> mic_user) {
            this.mic_user = mic_user;
        }

        public List<MicUserBean> getRoom_user() {
            return room_user;
        }

        public void setRoom_user(List<MicUserBean> room_user) {
            this.room_user = room_user;
        }

        public List<MicUserBean> getSea_user() {
            return sea_user;
        }

        public void setSea_user(List<MicUserBean> sea_user) {
            this.sea_user = sea_user;
        }

    }
}
