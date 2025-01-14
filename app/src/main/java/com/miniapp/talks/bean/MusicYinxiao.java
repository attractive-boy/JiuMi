package com.miniapp.talks.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class MusicYinxiao implements Serializable {

    /**
     * code : 1
     * data : {"id":"","is_music":0,"music_name":"","music_url":"","singer":"","size":"","upload_user":"","yinxiao":[{"id":4,"music_name":"音效","music_url":"http","singer":"音效","upload_user":"后台"}]}
     * message : 请求成功!
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean implements Serializable {
        /**
         * id :
         * is_music : 0
         * music_name :
         * music_url :
         * singer :
         * size :
         * upload_user :
         * yinxiao : [{"id":4,"music_name":"音效","music_url":"http","singer":"音效","upload_user":"后台"}]
         */

        private String id;
        private int is_music;
        private String music_name;
        private String music_url;
        private String singer;
        private String size;
        private String upload_user;
        private List<YinxiaoBean> yinxiao;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIs_music() {
            return is_music;
        }

        public void setIs_music(int is_music) {
            this.is_music = is_music;
        }

        public String getMusic_name() {
            return music_name;
        }

        public void setMusic_name(String music_name) {
            this.music_name = music_name;
        }

        public String getMusic_url() {
            return music_url;
        }

        public void setMusic_url(String music_url) {
            this.music_url = music_url;
        }

        public String getSinger() {
            return singer;
        }

        public void setSinger(String singer) {
            this.singer = singer;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getUpload_user() {
            return upload_user;
        }

        public void setUpload_user(String upload_user) {
            this.upload_user = upload_user;
        }

        public List<YinxiaoBean> getYinxiao() {
            return yinxiao;
        }

        public void setYinxiao(List<YinxiaoBean> yinxiao) {
            this.yinxiao = yinxiao;
        }

        public static class YinxiaoBean implements Serializable{
            /**
             * id : 4
             * music_name : 音效
             * music_url : http
             * singer : 音效
             * upload_user : 后台
             */

            private int id;
            private String music_name;
            private String music_url;
            private String singer;
            private String upload_user;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMusic_name() {
                return music_name;
            }

            public void setMusic_name(String music_name) {
                this.music_name = music_name;
            }

            public String getMusic_url() {
                return music_url;
            }

            public void setMusic_url(String music_url) {
                this.music_url = music_url;
            }

            public String getSinger() {
                return singer;
            }

            public void setSinger(String singer) {
                this.singer = singer;
            }

            public String getUpload_user() {
                return upload_user;
            }

            public void setUpload_user(String upload_user) {
                this.upload_user = upload_user;
            }
        }
    }
}
