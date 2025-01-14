package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class MusicList {

    /**
     * code : 1
     * message : 请求成功!
     * data : [{"id":5,"user_id":1151673,"music_url":"http://47.92.85.75/upload/files/fba8fa4e0e31d72296e1b9b696c5e69e.mp3","enable":1,"updated_at":"2019-07-11 17:25:25","created_at":"2019-07-11 17:25:25","music_name":"音效","singer":"赵六"}]
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
         * user_id : 1151673
         * music_url : http://47.92.85.75/upload/files/fba8fa4e0e31d72296e1b9b696c5e69e.mp3
         * enable : 1
         * updated_at : 2019-07-11 17:25:25
         * created_at : 2019-07-11 17:25:25
         * music_name : 音效
         * singer : 赵六
         */

        private int id;
        private int user_id;
        private String music_url;
        private int enable;
        private String updated_at;
        private String created_at;
        private String music_name;
        private String singer;

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

        public String getMusic_url() {
            return music_url;
        }

        public void setMusic_url(String music_url) {
            this.music_url = music_url;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
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

        public String getMusic_name() {
            return music_name;
        }

        public void setMusic_name(String music_name) {
            this.music_name = music_name;
        }

        public String getSinger() {
            return singer;
        }

        public void setSinger(String singer) {
            this.singer = singer;
        }
    }
}
