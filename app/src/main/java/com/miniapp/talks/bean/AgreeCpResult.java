package com.miniapp.talks.bean;

import java.util.List;

public class AgreeCpResult {

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

    public static class DataBean{

        String user_nick = "";
        String user_color = "";
        String from_nick = "";
        String from_color = "";

        public String getUser_nick() {
            return user_nick;
        }

        public void setUser_nick(String user_nick) {
            this.user_nick = user_nick;
        }

        public String getUser_color() {
            return user_color;
        }

        public void setUser_color(String user_color) {
            this.user_color = user_color;
        }

        public String getFrom_nick() {
            return from_nick;
        }

        public void setFrom_nick(String from_nick) {
            this.from_nick = from_nick;
        }

        public String getFrom_color() {
            return from_color;
        }

        public void setFrom_color(String from_color) {
            this.from_color = from_color;
        }

    }


}
