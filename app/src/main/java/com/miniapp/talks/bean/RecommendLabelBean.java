package com.miniapp.talks.bean;

import java.util.List;

public class RecommendLabelBean {


    /**
     * code : 1
     * message : 请求成功!
     * data : {"top":[{"id":1,"name":"#囧事"},{"id":2,"name":"#毒鸡汤"},{"id":3,"name":"#晒出与众不同的你"},{"id":4,"name":"#Diss mini"},{"id":5,"name":"#旅游"}],"all":[{"id":1,"name":"#囧事"},{"id":2,"name":"#毒鸡汤"},{"id":3,"name":"#晒出与众不同的你"},{"id":4,"name":"#Diss mini"},{"id":5,"name":"#旅游"}]}
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
        private List<TopBean> top;
        private List<AllBean> all;

        public List<TopBean> getTop() {
            return top;
        }

        public void setTop(List<TopBean> top) {
            this.top = top;
        }

        public List<AllBean> getAll() {
            return all;
        }

        public void setAll(List<AllBean> all) {
            this.all = all;
        }

        public static class TopBean {
            /**
             * id : 1
             * name : #囧事
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class AllBean {
            /**
             * id : 1
             * name : #囧事
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
