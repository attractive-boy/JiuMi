package com.miniapp.talks.bean;

import java.util.List;

public class VipCenterBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : {"user":[{"id":1766,"nickname":"把寂寞当晚餐","headimgurl":"http://47.92.85.75/upload//avatar/20190726/15641335799444.jpg","vip_num":"7783.00","vip_level":4,"next_vip_num":15000,"next_vip_level":5}],"auth":[{"id":1,"level":1,"name":"专属徽章","title":"专属徽章","img_0":"http://47.92.85.75/upload//emoji/vip_off/zshz.png","img_1":"http://47.92.85.75/upload//emoji/vip_on/zshz.png","addtime":1564136125,"is_on":1},{"id":2,"level":3,"name":"名称高亮","title":"名称高亮","img_0":"http://47.92.85.75/upload//emoji/vip_off/mcgl.png","img_1":"http://47.92.85.75/upload//emoji/vip_on/mcgl.png","addtime":1564136126,"is_on":1},{"id":3,"level":5,"name":"专属进场特效","title":"专属进场特效","img_0":"http://47.92.85.75/upload//emoji/vip_off/jctx.png","img_1":"http://47.92.85.75/upload//emoji/vip_on/jctx.png","addtime":1564136125,"is_on":0},{"id":4,"level":9,"name":"专属聊天文字气泡框","title":"专属聊天文字气泡框","img_0":"http://47.92.85.75/upload//emoji/vip_off/wzqp.png","img_1":"http://47.92.85.75/upload//emoji/vip_on/wzqp.png","addtime":1564136125,"is_on":0},{"id":5,"level":11,"name":"VIP专属礼物解锁","title":"VIP专属礼物解锁","img_0":"http://47.92.85.75/upload//emoji/vip_off/zslw.png","img_1":"http://47.92.85.75/upload//emoji/vip_on/zslw.png","addtime":1564136125,"is_on":0},{"id":6,"level":13,"name":"专属麦上光圈","title":"专属麦上光圈","img_0":"http://47.92.85.75/upload//emoji/vip_off/msgh.png","img_1":"http://47.92.85.75/upload//emoji/vip_on/msgh.png","addtime":1564136125,"is_on":0},{"id":7,"level":15,"name":"专属头像框","title":"专属头像框","img_0":"http://47.92.85.75/upload//emoji/vip_off/zstx.png","img_1":"http://47.92.85.75/upload//emoji/vip_on/zstx.png","addtime":1564136125,"is_on":0},{"id":8,"level":17,"name":"防禁言","title":"防禁言","img_0":"http://47.92.85.75/upload//emoji/vip_off/fzjy.png","img_1":"http://47.92.85.75/upload//emoji/vip_on/fzjy.png","addtime":1564136125,"is_on":0},{"id":9,"level":19,"name":"4位ID","title":"4位ID","img_0":"http://47.92.85.75/upload//emoji/vip_off/4wid.png","img_1":"http://47.92.85.75/upload//emoji/vip_on/4wid.png","addtime":1564136125,"is_on":0}]}
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
        private List<AuthBean> auth;

        public List<UserBean> getUser() {
            return user;
        }

        public void setUser(List<UserBean> user) {
            this.user = user;
        }

        public List<AuthBean> getAuth() {
            return auth;
        }

        public void setAuth(List<AuthBean> auth) {
            this.auth = auth;
        }

        public static class UserBean {
            /**
             * id : 1766
             * nickname : 把寂寞当晚餐
             * headimgurl : http://47.92.85.75/upload//avatar/20190726/15641335799444.jpg
             * vip_num : 7783.00
             * vip_level : 4
             * next_vip_num : 15000
             * next_vip_level : 5
             */

            private int id;
            private String nickname;
            private String headimgurl;
            private String vip_num;
            private int vip_level;
            private int next_vip_num;
            private int next_vip_level;

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

            public String getVip_num() {
                return vip_num;
            }

            public void setVip_num(String vip_num) {
                this.vip_num = vip_num;
            }

            public int getVip_level() {
                return vip_level;
            }

            public void setVip_level(int vip_level) {
                this.vip_level = vip_level;
            }

            public int getNext_vip_num() {
                return next_vip_num;
            }

            public void setNext_vip_num(int next_vip_num) {
                this.next_vip_num = next_vip_num;
            }

            public int getNext_vip_level() {
                return next_vip_level;
            }

            public void setNext_vip_level(int next_vip_level) {
                this.next_vip_level = next_vip_level;
            }
        }

        public static class AuthBean {
            /**
             * id : 1
             * level : 1
             * name : 专属徽章
             * title : 专属徽章
             * img_0 : http://47.92.85.75/upload//emoji/vip_off/zshz.png
             * img_1 : http://47.92.85.75/upload//emoji/vip_on/zshz.png
             * addtime : 1564136125
             * is_on : 1
             */

            private int id;
            private int level;
            private String name;
            private String title;
            private String img_0;
            private String img_1;
            private int addtime;
            private int is_on;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg_0() {
                return img_0;
            }

            public void setImg_0(String img_0) {
                this.img_0 = img_0;
            }

            public String getImg_1() {
                return img_1;
            }

            public void setImg_1(String img_1) {
                this.img_1 = img_1;
            }

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
                this.addtime = addtime;
            }

            public int getIs_on() {
                return is_on;
            }

            public void setIs_on(int is_on) {
                this.is_on = is_on;
            }
        }
    }
}
