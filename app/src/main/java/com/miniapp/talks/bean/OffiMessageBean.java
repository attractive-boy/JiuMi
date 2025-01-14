package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class OffiMessageBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : [{"id":5,"user_id":0,"title":"啾咪上线啦","img":"http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg","type":2,"content":"<p>啾咪今日出道,还望大家多多关照<\/p>","url":"","created_at":"2019-06-29 13:38:54","is_read":0},{"id":4,"user_id":0,"title":"啾咪充值活动开启啦","img":"http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg","type":2,"content":"<p>即日起凡充值1000元以上即可享受30%返利优惠,多充多送<br/><\/p>","url":"","created_at":"2019-06-29 13:34:27","is_read":0}]
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
         * id : 5
         * user_id : 0
         * title : 啾咪上线啦
         * img : http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg
         * type : 2
         * content : <p>啾咪今日出道,还望大家多多关照</p>
         * url :
         * created_at : 2019-06-29 13:38:54
         * is_read : 0
         */

        private int id;
        private int user_id;
        private String title;
        private String img;
        private int type;
        private String content;
        private String url;
        private String created_at;
        private int is_read;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getIs_read() {
            return is_read;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }
    }
}
