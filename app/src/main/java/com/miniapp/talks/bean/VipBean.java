package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class VipBean {
    /**
     * code : 1
     * message : 请求成功
     * data : {"vip_img":"http://47.92.85.75/upload//emoji/vip_ico/vip/vip_5.png","hz_img":"http://47.92.85.75/upload//emoji/vip_ico/hz/huizhang_5.png","ltk_left":"http://47.92.85.75/upload/emoji/ltk/l9.png","ltk":"http://47.92.85.75/upload/emoji/ltk/r9.png","ltk_right":"http://47.92.85.75/upload/emoji/ltk/background.png","vip_tx":"","nick_color":"#ffde00","cp_tx":"","cp_users":[{"nick_color":"#ffde00","id":"1151828","nickname":"白羊","exp":5000,"headimgurl":"http://test.miniyuyin.cn/upload//cover/20190803/15648038638044.jpg"},{"nick_color":"#ffde00","id":"1151856","nickname":"好汉歌","exp":2304,"headimgurl":"http://47.92.85.75/upload//avatar/20190729/15643844143270.png"}]}
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
         * vip_img : http://47.92.85.75/upload//emoji/vip_ico/vip/vip_5.png
         * hz_img : http://47.92.85.75/upload//emoji/vip_ico/hz/huizhang_5.png
         * ltk_left : http://47.92.85.75/upload/emoji/ltk/l9.png
         * ltk : http://47.92.85.75/upload/emoji/ltk/r9.png
         * ltk_right : http://47.92.85.75/upload/emoji/ltk/background.png
         * vip_tx :
         * nick_color : #ffde00
         * cp_tx :
         * cp_users : [{"nick_color":"#ffde00","id":"1151828","nickname":"白羊","exp":5000,"headimgurl":"http://test.miniyuyin.cn/upload//cover/20190803/15648038638044.jpg"},{"nick_color":"#ffde00","id":"1151856","nickname":"好汉歌","exp":2304,"headimgurl":"http://47.92.85.75/upload//avatar/20190729/15643844143270.png"}]
         */

        private String vip_img;
        private String hz_img;
        private String ltk_left;
        private String ltk;
        private String ltk_right;
        private String vip_tx;
        private String nick_color;
        private List<CpUsersBean> cp_users;

        public String getVip_img() {
            return vip_img;
        }

        public void setVip_img(String vip_img) {
            this.vip_img = vip_img;
        }

        public String getHz_img() {
            return hz_img;
        }

        public void setHz_img(String hz_img) {
            this.hz_img = hz_img;
        }

        public String getLtk_left() {
            return ltk_left;
        }

        public void setLtk_left(String ltk_left) {
            this.ltk_left = ltk_left;
        }

        public String getLtk() {
            return ltk;
        }

        public void setLtk(String ltk) {
            this.ltk = ltk;
        }

        public String getLtk_right() {
            return ltk_right;
        }

        public void setLtk_right(String ltk_right) {
            this.ltk_right = ltk_right;
        }

        public String getVip_tx() {
            return vip_tx;
        }

        public void setVip_tx(String vip_tx) {
            this.vip_tx = vip_tx;
        }

        public String getNick_color() {
            return nick_color;
        }

        public void setNick_color(String nick_color) {
            this.nick_color = nick_color;
        }

        public List<CpUsersBean> getCp_users() {
            return cp_users;
        }

        public void setCp_users(List<CpUsersBean> cp_users) {
            this.cp_users = cp_users;
        }

        public static class CpUsersBean {
            /**
             * nick_color : #ffde00
             * id : 1151828
             * nickname : 白羊
             * exp : 5000
             * headimgurl : http://test.miniyuyin.cn/upload//cover/20190803/15648038638044.jpg
             */

            private String nick_color;
            private String id;
            private String nickname;
            private String exp;
            private String headimgurl;
            private String cp_level;
            private String cp_tx;

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

            public String getCp_level() {
                return cp_level;
            }

            public void setCp_level(String cp_level) {
                this.cp_level = cp_level;
            }

            public String getCp_tx() {
                return cp_tx;
            }

            public void setCp_tx(String cp_tx) {
                this.cp_tx = cp_tx;
            }
        }
    }


    /**
     * code : 1
     * message : 请求成功!
     * data : {"vip_img":"http://47.92.85.75/upload//emoji/vip_ico/vip/vip_5.png","hz_img":"http://47.92.85.75/upload//emoji/vip_ico/hz/huizhang_5.png"}
     */


//    private int code;
//    private String message;
//    private DataBean data;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * vip_img : http://47.92.85.75/upload//emoji/vip_ico/vip/vip_5.png
//         * hz_img : http://47.92.85.75/upload//emoji/vip_ico/hz/huizhang_5.png
//         */
//
//        private String vip_img;
//        private String hz_img;
//
//        public String getVip_img() {
//            return vip_img;
//        }
//
//        public void setVip_img(String vip_img) {
//            this.vip_img = vip_img;
//        }
//
//        public String getHz_img() {
//            return hz_img;
//        }
//
//        public void setHz_img(String hz_img) {
//            this.hz_img = hz_img;
//        }
//    }
}
