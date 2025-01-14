package com.miniapp.talks.bean;

public class BaoXiangBean {


    /**
     * code : 1
     * message : 请求成功
     * data : {"boxstartdate":1567731600,"boxenddate":1567743600,"boxclass":1,"keys_num":"0","points":"0"}
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
         * boxstartdate : 1567731600
         * boxenddate : 1567743600
         * boxclass : 1
         * keys_num : 0
         * points : 0
         */

        private String boxstartdate;
        private String boxenddate;
        private int boxclass;
        private String keys_num;
        private String points;

        public String getBoxstartdate() {
            return boxstartdate;
        }

        public void setBoxstartdate(String boxstartdate) {
            this.boxstartdate = boxstartdate;
        }

        public String getBoxenddate() {
            return boxenddate;
        }

        public void setBoxenddate(String boxenddate) {
            this.boxenddate = boxenddate;
        }

        public int getBoxclass() {
            return boxclass;
        }

        public void setBoxclass(int boxclass) {
            this.boxclass = boxclass;
        }

        public String getKeys_num() {
            return keys_num;
        }

        public void setKeys_num(String keys_num) {
            this.keys_num = keys_num;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }
    }
}
