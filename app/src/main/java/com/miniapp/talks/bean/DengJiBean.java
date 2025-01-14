package com.miniapp.talks.bean;

import java.util.List;

public class DengJiBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : [{"id":1766,"nickname":"把寂寞当晚餐","headimgurl":"http://47.92.85.75/upload//avatar/20190726/15641335799444.jpg","star_num":"1361372.00","gold_num":"25543.00","star_level":13,"next_star_num":3375000,"next_star_level":14,"gold_level":6,"next_gold_num":90000,"next_gold_level":7}]
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
         * id : 1766
         * nickname : 把寂寞当晚餐
         * headimgurl : http://47.92.85.75/upload//avatar/20190726/15641335799444.jpg
         * star_num : 1361372.00
         * gold_num : 25543.00
         * star_level : 13
         * next_star_num : 3375000
         * next_star_level : 14
         * gold_level : 6
         * next_gold_num : 90000
         * next_gold_level : 7
         */

        private int id;
        private String nickname;
        private String headimgurl;
        private String star_num;
        private String gold_num;
        private int star_level;
        private int next_star_num;
        private int next_star_level;
        private int gold_level;
        private int next_gold_num;
        private int next_gold_level;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getStar_num() {
            return star_num;
        }

        public void setStar_num(String star_num) {
            this.star_num = star_num;
        }

        public String getGold_num() {
            return gold_num;
        }

        public void setGold_num(String gold_num) {
            this.gold_num = gold_num;
        }

        public int getStar_level() {
            return star_level;
        }

        public void setStar_level(int star_level) {
            this.star_level = star_level;
        }

        public int getNext_star_num() {
            return next_star_num;
        }

        public void setNext_star_num(int next_star_num) {
            this.next_star_num = next_star_num;
        }

        public int getNext_star_level() {
            return next_star_level;
        }

        public void setNext_star_level(int next_star_level) {
            this.next_star_level = next_star_level;
        }

        public int getGold_level() {
            return gold_level;
        }

        public void setGold_level(int gold_level) {
            this.gold_level = gold_level;
        }

        public int getNext_gold_num() {
            return next_gold_num;
        }

        public void setNext_gold_num(int next_gold_num) {
            this.next_gold_num = next_gold_num;
        }

        public int getNext_gold_level() {
            return next_gold_level;
        }

        public void setNext_gold_level(int next_gold_level) {
            this.next_gold_level = next_gold_level;
        }
    }
}
