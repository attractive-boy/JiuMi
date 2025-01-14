package com.miniapp.talks.bean;

public class ChaMoneyBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : {"mizuan":100,"give":30}
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
         * mizuan : 100
         * give : 30
         */

        private int mizuan;
        private int give;

        public int getMizuan() {
            return mizuan;
        }

        public void setMizuan(int mizuan) {
            this.mizuan = mizuan;
        }

        public int getGive() {
            return give;
        }

        public void setGive(int give) {
            this.give = give;
        }
    }
}
