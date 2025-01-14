package com.miniapp.talks.bean;

import java.util.List;

public class CollectionRoomListBean {


    /**
     * code : 1
     * message : 请求成功!
     * data : {"on":[{"numid":"113114","uid":113114,"room_name":"过客，笑谈人生的房间","room_cover":"http://47.92.85.75/upload/cover/20190612/25220_155153_7357.jpg","hot":9999,"is_afk":1,"sex":1,"nickname":"田中华(Mythe)"}],"off":[{"numid":"1151834","uid":1151834,"room_name":"1","room_cover":"http://47.92.85.75/upload/cover/20190712/1562913647.png","hot":1031,"is_afk":0,"sex":1,"nickname":"褚"}]}
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

    public static class DataBean {
        private List<OnBean> on;
        private List<OffBean> off;

        public List<OnBean> getOn() {
            return on;
        }

        public void setOn(List<OnBean> on) {
            this.on = on;
        }

        public List<OffBean> getOff() {
            return off;
        }

        public void setOff(List<OffBean> off) {
            this.off = off;
        }

        public static class OnBean {
            /**
             * numid : 113114
             * uid : 113114
             * room_name : 过客，笑谈人生的房间
             * room_cover : http://47.92.85.75/upload/cover/20190612/25220_155153_7357.jpg
             * hot : 9999
             * is_afk : 1
             * sex : 1
             * nickname : 田中华(Mythe)
             */

            private String numid;
            private int uid;
            private String room_name;
            private String room_cover;
            private int hot;
            private int is_afk;
            private int sex;
            private String nickname;

            public String getNumid() {
                return numid;
            }

            public void setNumid(String numid) {
                this.numid = numid;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getRoom_name() {
                return room_name;
            }

            public void setRoom_name(String room_name) {
                this.room_name = room_name;
            }

            public String getRoom_cover() {
                return room_cover;
            }

            public void setRoom_cover(String room_cover) {
                this.room_cover = room_cover;
            }

            public int getHot() {
                return hot;
            }

            public void setHot(int hot) {
                this.hot = hot;
            }

            public int getIs_afk() {
                return is_afk;
            }

            public void setIs_afk(int is_afk) {
                this.is_afk = is_afk;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }

        public static class OffBean {
            /**
             * numid : 1151834
             * uid : 1151834
             * room_name : 1
             * room_cover : http://47.92.85.75/upload/cover/20190712/1562913647.png
             * hot : 1031
             * is_afk : 0
             * sex : 1
             * nickname : 褚
             */

            private String numid;
            private int uid;
            private String room_name;
            private String room_cover;
            private int hot;
            private int is_afk;
            private int sex;
            private String nickname;

            public String getNumid() {
                return numid;
            }

            public void setNumid(String numid) {
                this.numid = numid;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getRoom_name() {
                return room_name;
            }

            public void setRoom_name(String room_name) {
                this.room_name = room_name;
            }

            public String getRoom_cover() {
                return room_cover;
            }

            public void setRoom_cover(String room_cover) {
                this.room_cover = room_cover;
            }

            public int getHot() {
                return hot;
            }

            public void setHot(int hot) {
                this.hot = hot;
            }

            public int getIs_afk() {
                return is_afk;
            }

            public void setIs_afk(int is_afk) {
                this.is_afk = is_afk;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
