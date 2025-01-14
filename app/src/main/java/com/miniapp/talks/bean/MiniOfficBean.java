package com.miniapp.talks.bean;

import java.io.Serializable;

/**
 * 作者:sgm
 * 描述:
 */
public class MiniOfficBean implements Serializable {

    /**
     * code : 1
     * message : 请求成功!
     * data : {"img":"http://47.92.85.75/upload//avatar/config/logo.png","name":"Mini 官方","unread":3,"title":"考核变动通知","created_at":"2019-07-25 09:54:04"}
     */

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

    public static class DataBean implements Serializable {
        /**
         * img : http://47.92.85.75/upload//avatar/config/logo.png
         * name : Mini 官方
         * unread : 3
         * title : 考核变动通知
         * created_at : 2019-07-25 09:54:04
         */

        private String img;
        private String name;
        private int unread;
        private String title;
        private String created_at;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUnread() {
            return unread;
        }

        public void setUnread(int unread) {
            this.unread = unread;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
