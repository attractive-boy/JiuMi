package com.miniapp.talks.bean;

import java.util.List;

public class Winner {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"username":"zz","lwname":"么么哒","user_id":"12218","num":1}]
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
         * username : zz
         * lwname : 么么哒
         * user_id : 12218
         * num : 1
         */

        private String username;
        private String lwname;
        private String user_id;
        private int num;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getLwname() {
            return lwname;
        }

        public void setLwname(String lwname) {
            this.lwname = lwname;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
