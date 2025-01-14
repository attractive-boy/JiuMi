package com.miniapp.talks.bean;

public class OtherBean {

    /**
     * code : 1
     * message : 登录成功
     * data : {"id":1151864,"nickname":"飛飛","headimgurl":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKia5VyC4PYGIsUvoIiaia3ibLdECk8LXhcMPR6uYDmVYroOYlvqvYn1bjEVS1j65gW7N70c7uu8TvA0Q/132","ry_uid":"1151864","ry_token":"sRPg3sayrpqv0GP3U8JOPm61bgrxUB7r2paFSyNI18dpJfx7zc/Kv+VWplzWqGLVuw/QTx2YVpTGYLNmvLmZXkkbNFRnLEMw","phone":"123456"}
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
         * id : 1151864
         * nickname : 飛飛
         * headimgurl : https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKia5VyC4PYGIsUvoIiaia3ibLdECk8LXhcMPR6uYDmVYroOYlvqvYn1bjEVS1j65gW7N70c7uu8TvA0Q/132
         * ry_uid : 1151864
         * ry_token : sRPg3sayrpqv0GP3U8JOPm61bgrxUB7r2paFSyNI18dpJfx7zc/Kv+VWplzWqGLVuw/QTx2YVpTGYLNmvLmZXkkbNFRnLEMw
         * phone : 123456
         */

        private int id;
        private String nickname;
        private String headimgurl;
        private String ry_uid;
        private String ry_token;
        private String phone;
        private String token = "";

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

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

        public String getRy_uid() {
            return ry_uid;
        }

        public void setRy_uid(String ry_uid) {
            this.ry_uid = ry_uid;
        }

        public String getRy_token() {
            return ry_token;
        }

        public void setRy_token(String ry_token) {
            this.ry_token = ry_token;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
