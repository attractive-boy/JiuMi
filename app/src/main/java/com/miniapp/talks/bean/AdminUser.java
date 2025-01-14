package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class AdminUser {

    /**
     * code : 1
     * data : {"admin":[{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":113114,"is_admin":1,"nickname":"田中华(Mythe)"}],"room_id":"1151842","visitor":[{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":113114,"is_admin":1,"nickname":"田中华(Mythe)"}]}
     * message : 请求成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * admin : [{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":113114,"is_admin":1,"nickname":"田中华(Mythe)"}]
         * room_id : 1151842
         * visitor : [{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":113114,"is_admin":1,"nickname":"田中华(Mythe)"}]
         */

        private String room_id;
        private List<AdminBean> admin;
        private List<VisitorBean> visitor;

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public List<AdminBean> getAdmin() {
            return admin;
        }

        public void setAdmin(List<AdminBean> admin) {
            this.admin = admin;
        }

        public List<VisitorBean> getVisitor() {
            return visitor;
        }

        public void setVisitor(List<VisitorBean> visitor) {
            this.visitor = visitor;
        }

        public static class AdminBean {
            /**
             * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
             * id : 113114
             * is_admin : 1
             * nickname : 田中华(Mythe)
             */

            private String headimgurl;
            private int id;
            private int is_admin;
            private String nickname;

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIs_admin() {
                return is_admin;
            }

            public void setIs_admin(int is_admin) {
                this.is_admin = is_admin;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }

        public static class VisitorBean {
            /**
             * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
             * id : 113114
             * is_admin : 1
             * nickname : 田中华(Mythe)
             */

            private String headimgurl;
            private int id;
            private int is_admin;
            private String nickname;

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIs_admin() {
                return is_admin;
            }

            public void setIs_admin(int is_admin) {
                this.is_admin = is_admin;
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
