package com.miniapp.talks.bean;

/**
 * 作者:sgm
 * 描述:
 */
public class UserBean {


    /**
     * code : 1
     * message : 请求成功!
     * data : {"id":1151842,"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"哈哈","sex":1,"birthday":"1992-05-07","constellation":"金牛座","city":"月球","mizuan":"79905419.00","is_idcard":1,"ry_uid":"test1151842","vip_level":8,"follows_num":13,"fans_num":13}
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
        /**
         * id : 1151842
         * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
         * nickname : 哈哈
         * sex : 1
         * birthday : 1992-05-07
         * constellation : 金牛座
         * city : 月球
         * mizuan : 79905419.00
         * is_idcard : 1
         * ry_uid : test1151842
         * vip_level : 8
         * follows_num : 13
         * fans_num : 13
         */

        private int id;
        private String headimgurl;
        private String nickname;
        private int sex;
        private String birthday;
        private String constellation;
        private String city;
        private String mizuan;
        private int is_idcard;
        private String ry_uid;
        private int vip_level;
        private int follows_num;
        private int fans_num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getMizuan() {
            return mizuan;
        }

        public void setMizuan(String mizuan) {
            this.mizuan = mizuan;
        }

        public int getIs_idcard() {
            return is_idcard;
        }

        public void setIs_idcard(int is_idcard) {
            this.is_idcard = is_idcard;
        }

        public String getRy_uid() {
            return ry_uid;
        }

        public void setRy_uid(String ry_uid) {
            this.ry_uid = ry_uid;
        }

        public int getVip_level() {
            return vip_level;
        }

        public void setVip_level(int vip_level) {
            this.vip_level = vip_level;
        }

        public int getFollows_num() {
            return follows_num;
        }

        public void setFollows_num(int follows_num) {
            this.follows_num = follows_num;
        }

        public int getFans_num() {
            return fans_num;
        }

        public void setFans_num(int fans_num) {
            this.fans_num = fans_num;
        }
    }
}
