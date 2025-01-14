package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class RoomType {

    /**
     * code : 1
     * message : 获取成功
     * data : [{"id":1,"name":"推荐"},{"id":2,"name":"语圈"},{"id":3,"name":"文坛"},{"id":4,"name":"开黑"},{"id":5,"name":"娱乐"},{"id":6,"name":"二次元"}]
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
         * name : 推荐
         */

        private int id;
        private String name;
        public boolean isSelect;
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
