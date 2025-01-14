package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class SearchHis {

    /**
     * code : 1
     * message : 请求成功!
     * data : {"hot":[{"id":1,"search":"#风景"},{"id":2,"search":"#端午节"},{"id":3,"search":"啾咪"},{"id":4,"search":"#今天你微笑了吗"},{"id":5,"search":"20190527"}],"histor":[{"id":10,"search":"#运动"}]}
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
        private List<HotBean> hot;
        private List<HistorBean> histor;

        public List<HotBean> getHot() {
            return hot;
        }

        public void setHot(List<HotBean> hot) {
            this.hot = hot;
        }

        public List<HistorBean> getHistor() {
            return histor;
        }

        public void setHistor(List<HistorBean> histor) {
            this.histor = histor;
        }

        public static class HotBean {
            /**
             * id : 1
             * search : #风景
             */

            private int id;
            private String search;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }
        }

        public static class HistorBean {
            /**
             * id : 10
             * search : #运动
             */

            private int id;
            private String search;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }
        }
    }
}
