package com.miniapp.talks.bean;

import java.util.List;

/**
 * Created by yxb on 2019/6/5.
 */
public class RecommenRoomBean {


    /**
     * code : 1
     * data : [{"name":"开黑","nickname":"田中华(Mythe)","numid":"113114","openid":"88E868BE19E5BC536E0CEDACB12474AE","room_cover":"http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg","room_name":"田中华(Mythe)","room_type":"3","sex":1,"uid":113114,"week_star":1},{"name":"文坛","nickname":"竹玉柱","numid":"113126","openid":"C7B715C3314A28C57A73D8604F8AD69C","room_cover":"http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg","room_name":"竹玉柱","room_type":"2","sex":1,"uid":113126,"week_star":1},{"name":"FM","numid":"113127","openid":"mobile_reg6FCE6444-DBC3-161C-FFE4-354BC404841A","room_cover":"http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg","room_name":"似曾相识","room_type":"6","uid":113127,"week_star":1},{"name":"语圈","nickname":"晨静","numid":"113128","openid":"18454B6B8D3C3621EE94B55B96338FCE","room_cover":"http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg","room_name":"晨静","room_type":"1","sex":1,"uid":113128,"week_star":2},{"name":"娱乐","nickname":"master","numid":"1141412","openid":"F5563480F043E9E77FE68A8EA458A312","room_cover":"http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg","room_name":"master的房间","room_type":"4","sex":2,"uid":1141412,"week_star":2},{"name":"语圈","nickname":"長吥夶","numid":"1151673","openid":"oo1FM1PSAZjjrNfDIcqItFBRAFJM","room_cover":"http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg","room_name":"听歌","room_type":"1","sex":1,"uid":1151673,"week_star":2}]
     * message : 获取成功
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
         * name : 开黑
         * nickname : 田中华(Mythe)
         * numid : 113114
         * openid : 88E868BE19E5BC536E0CEDACB12474AE
         * room_cover : http://59.110.169.251/upload/cover/20190612/25220_155153_7357.jpg
         * room_name : 田中华(Mythe)
         * room_type : 3
         * sex : 1
         * uid : 113114
         * week_star : 1
         */

        private String name;
        private String nickname;
        private String numid;
        private String openid;
        private String room_cover;
        private String room_name;
        private String room_type;
        private int sex;
        private String uid;
        private int week_star;
        private int hot;

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNumid() {
            return numid;
        }

        public void setNumid(String numid) {
            this.numid = numid;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getRoom_cover() {
            return room_cover;
        }

        public void setRoom_cover(String room_cover) {
            this.room_cover = room_cover;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getRoom_type() {
            return room_type;
        }

        public void setRoom_type(String room_type) {
            this.room_type = room_type;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public int getWeek_star() {
            return week_star;
        }

        public void setWeek_star(int week_star) {
            this.week_star = week_star;
        }
    }
}
