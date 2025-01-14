package com.miniapp.talks.bean;

import java.util.List;

public class GuanFangLianXiBean {

    /**
     * code : 1
     * message : 获取成功
     * data : [{"id":1,"img":"http://test.miniyuyin.cn/upload//emoji/lx/lx1.png","type":"官方QQ群","content":"619215718","updated_at":"2019-08-15 15:56:40","created_at":"2019-06-05 13:51:46"},{"id":2,"img":"http://test.miniyuyin.cn/upload//emoji/lx/lx2.png","type":"官方客服微信","content":"miniyuyin","updated_at":"2019-08-15 15:57:00","created_at":"2019-06-05 13:52:45"},{"id":3,"img":"http://test.miniyuyin.cn/upload//emoji/lx/lx3.png","type":"官方公众号","content":"啾咪","updated_at":"2019-08-15 15:57:08","created_at":"2019-06-05 13:53:01"}]
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
         * id : 1
         * img : http://test.miniyuyin.cn/upload//emoji/lx/lx1.png
         * type : 官方QQ群
         * content : 619215718
         * updated_at : 2019-08-15 15:56:40
         * created_at : 2019-06-05 13:51:46
         */

        private int id;
        private String img;
        private String type;
        private String content;
        private String updated_at;
        private String created_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
