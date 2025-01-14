package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class Yinxiao {


    /**
     * code : 1
     * message : 请求成功!
     * data : [{"id":1,"music_url":"http://47.92.85.75/upload/files/83319cc48ffa554004eceff4653eda6b.mp3","enable":1,"updated_at":"2019-07-15 15:09:34","created_at":"2019-05-30 07:08:26","music_name":"故梦1","upload_user":"后台","type":1,"singer":"匿名","is_default":1},{"id":2,"music_url":"http://47.92.85.75/upload/files/6b7e587621621bedce0c91fec3b40d53.mp3","enable":1,"updated_at":"2019-07-15 15:09:36","created_at":"2019-05-30 07:28:18","music_name":"故梦2","upload_user":"后台","type":1,"singer":"匿名","is_default":1},{"id":3,"music_url":"http://47.92.85.75/upload//music/20190611/25220_155152_1389.mp3","enable":1,"updated_at":"2019-07-15 15:10:00","created_at":"2019-06-11 15:51:52","music_name":"测试音乐1","upload_user":"test","type":1,"singer":"222","is_default":1},{"id":5,"music_url":"http://47.92.85.75/upload/files/83319cc48ffa554004eceff4653eda6b.mp3","enable":1,"updated_at":"2019-07-15 15:09:39","created_at":"2019-05-30 07:08:26","music_name":"故梦3","upload_user":"后台","type":1,"singer":"匿名","is_default":1},{"id":6,"music_url":"http://47.92.85.75/upload/files/6b7e587621621bedce0c91fec3b40d53.mp3","enable":1,"updated_at":"2019-07-15 15:09:41","created_at":"2019-05-30 07:28:18","music_name":"故梦4","upload_user":"后台","type":1,"singer":"匿名","is_default":1},{"id":7,"music_url":"http://47.92.85.75/upload//music/20190611/25220_155152_1389.mp3","enable":1,"updated_at":"2019-07-15 15:09:57","created_at":"2019-06-11 15:51:52","music_name":"测试音乐2","upload_user":"test","type":1,"singer":"222","is_default":1},{"id":8,"music_url":"http://47.92.85.75/upload/files/83319cc48ffa554004eceff4653eda6b.mp3","enable":1,"updated_at":"2019-07-15 15:09:43","created_at":"2019-05-30 07:08:26","music_name":"故梦5","upload_user":"后台","type":1,"singer":"匿名","is_default":1},{"id":9,"music_url":"http://47.92.85.75/upload/files/6b7e587621621bedce0c91fec3b40d53.mp3","enable":1,"updated_at":"2019-07-15 15:09:45","created_at":"2019-05-30 07:28:18","music_name":"故梦6","upload_user":"后台","type":1,"singer":"匿名","is_default":1},{"id":10,"music_url":"http://47.92.85.75/upload//music/20190611/25220_155152_1389.mp3","enable":1,"updated_at":"2019-07-15 15:10:02","created_at":"2019-06-11 15:51:52","music_name":"测试音乐3","upload_user":"test","type":1,"singer":"222","is_default":1}]
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
         * music_url : http://47.92.85.75/upload/files/83319cc48ffa554004eceff4653eda6b.mp3
         * enable : 1
         * updated_at : 2019-07-15 15:09:34
         * created_at : 2019-05-30 07:08:26
         * music_name : 故梦1
         * upload_user : 后台
         * type : 1
         * singer : 匿名
         * is_default : 1
         */

        private int id;
        private String music_url;
        private int enable;
        private String updated_at;
        private String created_at;
        private String music_name;
        private String upload_user;
        private int type;
        private String singer;
        private int is_default;
        public String music_size;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getUpload_user() {
            return upload_user;
        }

        public void setUpload_user(String upload_user) {
            this.upload_user = upload_user;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getSinger() {
            return singer;
        }

        public void setSinger(String singer) {
            this.singer = singer;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }
    }
}
