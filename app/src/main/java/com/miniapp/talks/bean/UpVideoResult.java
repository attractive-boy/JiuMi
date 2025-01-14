package com.miniapp.talks.bean;

import java.util.List;

public class UpVideoResult {

    /**
     * code : 1
     * message : 上麦成功
     * data : {"cp":[{"cp_level":0,"nick_color":"#ffde00","id":"1151896","nickname":"春天花","exp":99,"headimgurl":"http://47.92.85.75/upload//avatar/20190816/15659281014158.png","cp_xssm":""}],"user":{"id":1151918,"nickname":"小手冰凉","headimgurl":"http://47.92.85.75/upload//avatar/20190826/15667982851592.png","nick_color":"#ffde00"}}
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
         * cp : [{"cp_level":0,"nick_color":"#ffde00","id":"1151896","nickname":"春天花","exp":99,"headimgurl":"http://47.92.85.75/upload//avatar/20190816/15659281014158.png","cp_xssm":""}]
         * user : {"id":1151918,"nickname":"小手冰凉","headimgurl":"http://47.92.85.75/upload//avatar/20190826/15667982851592.png","nick_color":"#ffde00"}
         */

        private UserBean user;
        private List<CpBean> cp;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<CpBean> getCp() {
            return cp;
        }

        public void setCp(List<CpBean> cp) {
            this.cp = cp;
        }

        public static class UserBean {
            /**
             * id : 1151918
             * nickname : 小手冰凉
             * headimgurl : http://47.92.85.75/upload//avatar/20190826/15667982851592.png
             * nick_color : #ffde00
             */

            private String id;
            private String nickname;
            private String headimgurl;
            private String nick_color;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getNick_color() {
                return nick_color;
            }

            public void setNick_color(String nick_color) {
                this.nick_color = nick_color;
            }
        }

        public static class CpBean {
            /**
             * cp_level : 0
             * nick_color : #ffde00
             * id : 1151896
             * nickname : 春天花
             * exp : 99
             * headimgurl : http://47.92.85.75/upload//avatar/20190816/15659281014158.png
             * cp_xssm :
             */

            private int cp_level;
            private String nick_color;
            private String id;
            private String nickname;
            private String exp;
            private String headimgurl;
            private String cp_xssm;

            public int getCp_level() {
                return cp_level;
            }

            public void setCp_level(int cp_level) {
                this.cp_level = cp_level;
            }

            public String getNick_color() {
                return nick_color;
            }

            public void setNick_color(String nick_color) {
                this.nick_color = nick_color;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getExp() {
                return exp;
            }

            public void setExp(String exp) {
                this.exp = exp;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public String getCp_xssm() {
                return cp_xssm;
            }

            public void setCp_xssm(String cp_xssm) {
                this.cp_xssm = cp_xssm;
            }
        }
    }
}
