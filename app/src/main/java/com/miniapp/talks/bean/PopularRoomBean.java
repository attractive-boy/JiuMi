package com.miniapp.talks.bean;

import java.util.List;

/**
 * Created by yxb on 2019/6/5.
 */
public class PopularRoomBean {


    /**
     * code : 1
     * data : [{"name":"FM","numid":"113127├ X-RateLimit-Limit: 60","openid":"mobile_reg6FCE6444-DBC3-161C-FFE4-354BC404841A","room_cover":"http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg","room_name":"似曾相识","uid":113127,"week_star":1}]
     * message : 获取成功
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : FM
         * numid : 113127├ X-RateLimit-Limit: 60
         * openid : mobile_reg6FCE6444-DBC3-161C-FFE4-354BC404841A
         * room_cover : http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg
         * room_name : 似曾相识
         * uid : 113127
         * week_star : 1
         */

        private String name;
        private String numid;
        private String openid;
        private String room_cover;
        private String room_name;
        private int uid;
        private int week_star;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumid() {
            return numid;
        }

        public void setNumid(String numid) {
            this.numid = numid;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getRoom_cover() {
            return room_cover;
        }

        public void setRoom_cover(String room_cover) {
            this.room_cover = room_cover;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getWeek_star() {
            return week_star;
        }

        public void setWeek_star(int week_star) {
            this.week_star = week_star;
        }
    }
}
