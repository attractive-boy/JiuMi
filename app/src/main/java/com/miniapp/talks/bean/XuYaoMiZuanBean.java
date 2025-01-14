package com.miniapp.talks.bean;

public class XuYaoMiZuanBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"needMizuan":"20"}
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
         * needMizuan : 20
         */

        private String needMizuan;

        public String getNeedMizuan() {
            return needMizuan;
        }

        public void setNeedMizuan(String needMizuan) {
            this.needMizuan = needMizuan;
        }
    }
}
