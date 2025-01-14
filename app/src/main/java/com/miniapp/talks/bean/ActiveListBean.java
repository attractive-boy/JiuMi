package com.miniapp.talks.bean;

import java.util.List;

public class ActiveListBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : [{"id":4,"title":"活动4","img":"http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg","url":"http://ww.baidu.com","enable":1,"addtime":"2019-08-04 21:27:19"},{"id":3,"title":"活动3","img":"http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg","url":"http://ww.baidu.com","enable":1,"addtime":"2019-08-02 13:53:59"},{"id":2,"title":"活动2","img":"http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg","url":"http://ww.baidu.com","enable":1,"addtime":"2019-07-31 06:20:39"},{"id":1,"title":"活动1","img":"http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg","url":"http://ww.baidu.com","enable":1,"addtime":"2019-07-25 11:27:19"}]
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
         * id : 4
         * title : 活动4
         * img : http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg
         * url : http://ww.baidu.com
         * enable : 1
         * addtime : 2019-08-04 21:27:19
         */

        private int id;
        private String title;
        private String img;
        private String url;
        private int enable;
        private String addtime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}
