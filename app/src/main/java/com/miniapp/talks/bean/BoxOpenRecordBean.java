package com.miniapp.talks.bean;

import java.util.List;

public class BoxOpenRecordBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":1232,"wares_id":9,"type":3,"addtime":"2019-09-06","is_play":1,"num":2,"name":"头条卡","show_img":"http://47.92.85.75/upload/emoji/kq/ttk.png"},{"id":1233,"wares_id":2,"type":2,"addtime":"2019-09-06","is_play":1,"num":2,"name":"呲水枪","show_img":"http://47.92.85.75/upload/gifts/png/gift_list_sq@2x.png"},{"id":1234,"wares_id":3,"type":2,"addtime":"2019-09-06","is_play":1,"num":4,"name":"小黄瓜","show_img":"http://47.92.85.75/upload/gifts/png/gift_list_xhg@2x.png"},{"id":1235,"wares_id":1,"type":2,"addtime":"2019-09-06","is_play":1,"num":1,"name":"么么哒","show_img":"http://47.92.85.75/upload/gifts/png/gift_list_mmd@2x.png"},{"id":1236,"wares_id":4,"type":2,"addtime":"2019-09-06","is_play":1,"num":1,"name":"冰激凌","show_img":"http://47.92.85.75/upload/gifts/png/gift_list_bql@2x.png"},{"id":1331,"wares_id":1,"type":2,"addtime":"2019-09-06","is_play":1,"num":1,"name":"么么哒","show_img":"http://47.92.85.75/upload/gifts/png/gift_list_mmd@2x.png"},{"id":1332,"wares_id":4,"type":2,"addtime":"2019-09-06","is_play":1,"num":1,"name":"冰激凌","show_img":"http://47.92.85.75/upload/gifts/png/gift_list_bql@2x.png"},{"id":1333,"wares_id":4,"type":2,"addtime":"2019-09-06","is_play":1,"num":1,"name":"冰激凌","show_img":"http://47.92.85.75/upload/gifts/png/gift_list_bql@2x.png"},{"id":1334,"wares_id":4,"type":2,"addtime":"2019-09-06","is_play":1,"num":1,"name":"冰激凌","show_img":"http://47.92.85.75/upload/gifts/png/gift_list_bql@2x.png"},{"id":1335,"wares_id":2,"type":2,"addtime":"2019-09-06","is_play":1,"num":1,"name":"呲水枪","show_img":"http://47.92.85.75/upload/gifts/png/gift_list_sq@2x.png"}]
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
         * id : 1232
         * wares_id : 9
         * type : 3
         * addtime : 2019-09-06
         * is_play : 1
         * num : 2
         * name : 头条卡
         * show_img : http://47.92.85.75/upload/emoji/kq/ttk.png
         */

        private int id;
        private int wares_id;
        private int type;
        private String addtime;
        private int is_play;
        private int num;
        private String name;
        private String show_img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getWares_id() {
            return wares_id;
        }

        public void setWares_id(int wares_id) {
            this.wares_id = wares_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public int getIs_play() {
            return is_play;
        }

        public void setIs_play(int is_play) {
            this.is_play = is_play;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
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
