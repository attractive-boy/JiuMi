package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class WaitList {


    /**
     * code : 1
     * data : {"data":[{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":3,"nickname":"空空儿","uid":113114,"user_id":1151735},{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":7,"nickname":"master","uid":113114,"user_id":1141412},{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":11,"nickname":"Ω","uid":113114,"user_id":1151728}],"sort":0,"total":3,"user_id":1151735}
     * message : 请求成功!
     */

    private int code;
    private DataBeanX data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBeanX {
        /**
         * data : [{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":3,"nickname":"空空儿","uid":113114,"user_id":1151735},{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":7,"nickname":"master","uid":113114,"user_id":1141412},{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":11,"nickname":"Ω","uid":113114,"user_id":1151728}]
         * sort : 0
         * total : 3
         * user_id : 1151735
         */

        private String sort;
        private String total;
        private String user_id;
        private List<DataBean> data;

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
             * id : 3
             * nickname : 空空儿
             * uid : 113114
             * user_id : 1151735
             */

            private String headimgurl;
            private String id;
            private String nickname;
            private String uid;
            private String user_id;

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
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

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }
    }
}
