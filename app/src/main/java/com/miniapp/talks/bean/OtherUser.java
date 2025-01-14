package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class OtherUser {

    /**
     * code : 1
     * message : 获取成功
     * data : [{"id":1151706,"nickname":"晴川","headimgurl":"http://59.110.169.251/upload//avatar/20190613/25220_155229_4875.jpg","sex":1,"birthday":"1992-05-07","age":27,"user_type":2,"is_speak":1,"is_sound":1}]
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
         * id : 1151706
         * nickname : 晴川
         * headimgurl : http://59.110.169.251/upload//avatar/20190613/25220_155229_4875.jpg
         * sex : 1
         * birthday : 1992-05-07
         * age : 27
         * user_type : 2
         * is_speak : 1
         * is_sound : 1
         */

        private int id;
        private String nickname;
        private String headimgurl;
        private int sex;
        private String birthday;
        private int age;
        private int user_type;
        private int is_speak;
        private int is_sound;
        public int is_follows;
        public String vip_img;
        public String star_img;
        public String gold_img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public int getIs_speak() {
            return is_speak;
        }

        public void setIs_speak(int is_speak) {
            this.is_speak = is_speak;
        }

        public int getIs_sound() {
            return is_sound;
        }

        public void setIs_sound(int is_sound) {
            this.is_sound = is_sound;
        }
    }
}
