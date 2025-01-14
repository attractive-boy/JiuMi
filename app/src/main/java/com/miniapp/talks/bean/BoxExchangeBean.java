package com.miniapp.talks.bean;

import java.util.List;

public class BoxExchangeBean {
    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":6,"type":3,"score":1000,"name":"爆音卡","show_img":"http://47.92.85.75/upload/emoji/kq/byk.png"},{"id":7,"type":3,"score":2000,"name":"扩展卡","show_img":"http://47.92.85.75/upload/emoji/kq/kzk.png"},{"id":8,"type":3,"score":4000,"name":"头像框","show_img":""},{"id":9,"type":3,"score":6000,"name":"头条卡","show_img":"http://47.92.85.75/upload/emoji/kq/ttk.png"},{"id":10,"type":3,"score":10000,"name":"充值券（9.5折）","show_img":"http://47.92.85.75/upload/emoji/kq/95zhe.png"},{"id":45,"type":4,"score":4000,"name":"守护","show_img":"http://47.92.85.75/upload/emoji/txk/sh.png"}]
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
         * id : 6
         * type : 3
         * score : 1000
         * name : 爆音卡
         * show_img : http://47.92.85.75/upload/emoji/kq/byk.png
         */

        private int id;
        private int type;
        private int score;
        private String name;
        private String show_img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShow_img() {
            return show_img;
        }

        public void setShow_img(String show_img) {
            this.show_img = show_img;
        }
    }
}
