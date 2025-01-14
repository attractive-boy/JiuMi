package com.miniapp.talks.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 作者: sgm
 */
public class Wxmodel {


    /**
     * code : 1
     * message : 请求成功!
     * data : {"appid":"wx23a0e7e411a9b40d","noncestr":"2wlfVjstSGo1NMoLoWfkQJAKA9eTRGeN","package":"Sign=WXPay","partnerid":"1519734231","prepayid":"wx301407370563301047d058991001529000","timestamp":1564466857,"sign":"51521540C12171E509DD976BD073C6A9"}
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
         * appid : wx23a0e7e411a9b40d
         * noncestr : 2wlfVjstSGo1NMoLoWfkQJAKA9eTRGeN
         * package : Sign=WXPay
         * partnerid : 1519734231
         * prepayid : wx301407370563301047d058991001529000
         * timestamp : 1564466857
         * sign : 51521540C12171E509DD976BD073C6A9
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
