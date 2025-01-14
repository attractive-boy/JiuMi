package com.miniapp.talks.bean;

import java.util.List;

/**
 * Created by yxb on 2019/6/5.
 */
public class StartLoftBean {


    /**
     * code : 1
     * data : [{"name":"二次元","nickname":"手机用户77065787","numid":"1151567","openid":"oo1FM1FvyT9UKM2BfNJRnLoxOSU0","room_cover":"http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg","room_name":"手机用户77065787的房间","sex":1,"uid":1151567,"week_star":2},{"name":"语圈","nickname":"手机用户91981672","numid":"1151595","openid":"oo1FM1Axi_9ZrrhOBbKsvq-zJeQc","room_cover":"http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg","room_name":"手机用户91981672的房间","sex":1,"uid":1151595,"week_star":2},{"name":"语圈","nickname":"天奇","numid":"1151705","openid":"oo1FM1N2QkC0pDmSeLP3NOze7m5Q","room_cover":"http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg","room_name":"天奇的房间","sex":1,"uid":1151705,"week_star":2},{"name":"语圈","nickname":"武-","numid":"1151568","openid":"oo1FM1LAKLpWH3AKmjmi1Z7AvCPw","room_cover":"http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg","room_name":"武-的房间","sex":1,"uid":1151568,"week_star":2}]
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
         * name : 二次元
         * nickname : 手机用户77065787
         * numid : 1151567
         * openid : oo1FM1FvyT9UKM2BfNJRnLoxOSU0
         * room_cover : http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg
         * room_name : 手机用户77065787的房间
         * sex : 1
         * uid : 1151567
         * week_star : 2
         */

        private String name;
        private String nickname;
        private String numid;
        private String openid;
        private String room_cover;
        private String room_name;
        private int sex;
        private int uid;
        private int week_star;

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

        public int getWeek_star() {
            return week_star;
        }

        public void setWeek_star(int week_star) {
            this.week_star = week_star;
        }
    }
}
