package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class ReportBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : [{"id":1,"name":"广告"},{"id":2,"name":"色情"},{"id":3,"name":"违法"},{"id":4,"name":"诈骗"},{"id":5,"name":"未成年人直播"},{"id":6,"name":"其他原因"}]
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
         * id : 1
         * name : 广告
         */

        private int id;
        private String name;
        public boolean isSelct;

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
