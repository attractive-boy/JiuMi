package com.miniapp.talks.bean;

import java.util.List;

public class TopicBean {


    /**
     * code : 1
     * message : 请求成功!
     * data : [{"topic_img":"http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg","tags":"2","tag_name":"美食","num":9,"talk_num":9},{"topic_img":"http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg","tags":"3","tag_name":"旅游","num":9,"talk_num":4},{"topic_img":"http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg","tags":"5","tag_name":"运动","num":4,"talk_num":1},{"topic_img":"http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg","tags":"1","tag_name":"风景","num":3,"talk_num":1}]
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
         * topic_img : http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg
         * tags : 2
         * tag_name : 美食
         * num : 9
         * talk_num : 9
         */

        private String topic_img;
        private String tags;
        private String tag_name;
        private int num;
        private int talk_num;

        public String getTopic_img() {
            return topic_img;
        }

        public void setTopic_img(String topic_img) {
            this.topic_img = topic_img;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getTalk_num() {
            return talk_num;
        }

        public void setTalk_num(int talk_num) {
            this.talk_num = talk_num;
        }
    }
}
