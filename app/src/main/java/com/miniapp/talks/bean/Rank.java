package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class Rank {
    /**
     * code : 1
     * message : 请求成功!
     * data : {"user":[{"id":1766,"headimgurl":"http://47.92.85.75/upload//avatar/20190726/15641335799444.jpg","nickname":"把寂寞当晚餐","sort":"99","stars_img":"http://47.92.85.75/upload//emoji/vip_ico/xr/jinrui_12.png","gold_img":"http://47.92.85.75/upload//emoji/vip_ico/jr/xingrui_5.png","vip_img":"http://47.92.85.75/upload//emoji/vip_ico/vip/vip_5.png","exp":0}],"top":[{"fromUid":1151842,"exp":5917,"user_id":1151842,"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"哈哈","stars_img":"http://47.92.85.75/upload//emoji/vip_ico/xr/jinrui_13.png","gold_img":"http://47.92.85.75/upload//emoji/vip_ico/jr/xingrui_8.png","vip_img":"http://47.92.85.75/upload//emoji/vip_ico/vip/vip_9.png"},{"fromUid":1151857,"exp":4464,"user_id":1151857,"headimgurl":"http://47.92.85.75/upload//avatar/20190810/15654286255721.png","nickname":"向佳军的佳佳","stars_img":"http://47.92.85.75/upload//emoji/vip_ico/xr/jinrui_20.png","gold_img":"http://47.92.85.75/upload//emoji/vip_ico/jr/xingrui_9.png","vip_img":"http://47.92.85.75/upload//emoji/vip_ico/vip/vip_9.png"},{"fromUid":1151850,"exp":1687,"user_id":1151850,"headimgurl":"http://47.92.85.75/upload//cover/20190811/15654918528928.png","nickname":"Gg","stars_img":"http://47.92.85.75/upload//emoji/vip_ico/xr/jinrui_3.png","gold_img":"http://47.92.85.75/upload//emoji/vip_ico/jr/xingrui_2.png","vip_img":"http://47.92.85.75/upload//emoji/vip_ico/vip/vip_2.png"}],"other":[{"fromUid":1151846,"exp":220,"user_id":1151846,"headimgurl":"http://47.92.85.75/upload//avatar/20190621/25220_145124_8959.jpg","nickname":"橙汁","stars_img":"http://47.92.85.75/upload//emoji/vip_ico/xr/jinrui_15.png","gold_img":"http://47.92.85.75/upload//emoji/vip_ico/jr/xingrui_12.png","vip_img":"http://47.92.85.75/upload//emoji/vip_ico/vip/vip_13.png"},{"fromUid":1151835,"exp":26,"user_id":1151835,"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"搜索用户","stars_img":"http://47.92.85.75/upload//emoji/vip_ico/xr/jinrui_19.png","gold_img":"","vip_img":""},{"fromUid":1151885,"exp":6,"user_id":1151885,"headimgurl":"http://thirdqq.qlogo.cn/g?b=oidb&k=7El8BOtqzxy7oX1D5qEaPQ&s=100&t=1560863712","nickname":"就是混日子","stars_img":"","gold_img":"","vip_img":""}]}
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
        private List<UserBean> user;
        private List<TopBean> top;
        private List<OtherBean> other;

        public List<UserBean> getUser() {
            return user;
        }

        public void setUser(List<UserBean> user) {
            this.user = user;
        }

        public List<TopBean> getTop() {
            return top;
        }

        public void setTop(List<TopBean> top) {
            this.top = top;
        }

        public List<OtherBean> getOther() {
            return other;
        }

        public void setOther(List<OtherBean> other) {
            this.other = other;
        }

        public static class UserBean {
            /**
             * id : 1766
             * headimgurl : http://47.92.85.75/upload//avatar/20190726/15641335799444.jpg
             * nickname : 把寂寞当晚餐
             * sort : 99
             * stars_img : http://47.92.85.75/upload//emoji/vip_ico/xr/jinrui_12.png
             * gold_img : http://47.92.85.75/upload//emoji/vip_ico/jr/xingrui_5.png
             * vip_img : http://47.92.85.75/upload//emoji/vip_ico/vip/vip_5.png
             * exp : 0
             */

            private int id;
            private String headimgurl;
            private String nickname;
            private String sort;
            private String stars_img;
            private String gold_img;
            private String vip_img;
            private int exp;
            private String sex;

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

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

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getStars_img() {
                return stars_img;
            }

            public void setStars_img(String stars_img) {
                this.stars_img = stars_img;
            }

            public String getGold_img() {
                return gold_img;
            }

            public void setGold_img(String gold_img) {
                this.gold_img = gold_img;
            }

            public String getVip_img() {
                return vip_img;
            }

            public void setVip_img(String vip_img) {
                this.vip_img = vip_img;
            }

            public int getExp() {
                return exp;
            }

            public void setExp(int exp) {
                this.exp = exp;
            }
        }

        public static class TopBean {
            /**
             * fromUid : 1151842
             * exp : 5917
             * user_id : 1151842
             * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
             * nickname : 哈哈
             * stars_img : http://47.92.85.75/upload//emoji/vip_ico/xr/jinrui_13.png
             * gold_img : http://47.92.85.75/upload//emoji/vip_ico/jr/xingrui_8.png
             * vip_img : http://47.92.85.75/upload//emoji/vip_ico/vip/vip_9.png
             */

            private int fromUid;
            private int exp;
            private int user_id;
            private String headimgurl;
            private String nickname;
            private String stars_img;
            private String gold_img;
            private String vip_img;
            private String sex;

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getFromUid() {
                return fromUid;
            }

            public void setFromUid(int fromUid) {
                this.fromUid = fromUid;
            }

            public int getExp() {
                return exp;
            }

            public void setExp(int exp) {
                this.exp = exp;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
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

            public String getStars_img() {
                return stars_img;
            }

            public void setStars_img(String stars_img) {
                this.stars_img = stars_img;
            }

            public String getGold_img() {
                return gold_img;
            }

            public void setGold_img(String gold_img) {
                this.gold_img = gold_img;
            }

            public String getVip_img() {
                return vip_img;
            }

            public void setVip_img(String vip_img) {
                this.vip_img = vip_img;
            }
        }

        public static class OtherBean {
            /**
             * fromUid : 1151846
             * exp : 220
             * user_id : 1151846
             * headimgurl : http://47.92.85.75/upload//avatar/20190621/25220_145124_8959.jpg
             * nickname : 橙汁
             * stars_img : http://47.92.85.75/upload//emoji/vip_ico/xr/jinrui_15.png
             * gold_img : http://47.92.85.75/upload//emoji/vip_ico/jr/xingrui_12.png
             * vip_img : http://47.92.85.75/upload//emoji/vip_ico/vip/vip_13.png
             */

            private int fromUid;
            private int exp;
            private int user_id;
            private String headimgurl;
            private String nickname;
            private String stars_img;
            private String gold_img;
            private String vip_img;
            private String sex;
            private boolean isBg;

            public boolean isBg() {
                return isBg;
            }

            public void setBg(boolean bg) {
                isBg = bg;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getFromUid() {
                return fromUid;
            }

            public void setFromUid(int fromUid) {
                this.fromUid = fromUid;
            }

            public int getExp() {
                return exp;
            }

            public void setExp(int exp) {
                this.exp = exp;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
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

            public String getStars_img() {
                return stars_img;
            }

            public void setStars_img(String stars_img) {
                this.stars_img = stars_img;
            }

            public String getGold_img() {
                return gold_img;
            }

            public void setGold_img(String gold_img) {
                this.gold_img = gold_img;
            }

            public String getVip_img() {
                return vip_img;
            }

            public void setVip_img(String vip_img) {
                this.vip_img = vip_img;
            }
        }
    }
}
