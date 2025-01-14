package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class UserFriend {


    /**
     * code : 1
     * message : 请求成功!
     * data : [{"id":1151828,"followed_user_id":1766,"headimgurl":"http://47.92.85.75/upload//cover/20190803/15648038638044.jpg","nickname":"白羊","sex":1,"ry_uid":"1151828","type":"3","is_follow":0},{"id":1151722,"followed_user_id":1766,"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"FD.P","sex":1,"ry_uid":"","type":"3","is_follow":0},{"id":1151723,"followed_user_id":1766,"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"北栀女孩","sex":1,"ry_uid":"","type":"3","is_follow":0},{"id":113124,"followed_user_id":1766,"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"新新贝比 新零售","sex":1,"ry_uid":"","type":"3","is_follow":0},{"id":113126,"followed_user_id":1766,"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"竹玉柱","sex":1,"ry_uid":"","type":"3","is_follow":0}]
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
         * id : 1151828
         * followed_user_id : 1766
         * headimgurl : http://47.92.85.75/upload//cover/20190803/15648038638044.jpg
         * nickname : 白羊
         * sex : 1
         * ry_uid : 1151828
         * type : 3
         * is_follow : 0
         */

        private int id;
        private int followed_user_id;
        private String headimgurl;
        private String nickname;
        private int sex;
        private String ry_uid;
        private String type;
        private int is_follow;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFollowed_user_id() {
            return followed_user_id;
        }

        public void setFollowed_user_id(int followed_user_id) {
            this.followed_user_id = followed_user_id;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getRy_uid() {
            return ry_uid;
        }

        public void setRy_uid(String ry_uid) {
            this.ry_uid = ry_uid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(int is_follow) {
            this.is_follow = is_follow;
        }
    }
}
