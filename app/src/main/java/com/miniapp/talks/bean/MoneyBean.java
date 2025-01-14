package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class MoneyBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : [{"id":1151842,"ali_user_id":"2088502344891503","ali_avatar":"https://tfs.alipayobjects.com/images/partner/TB11Ou2alhDDuNjm2FfXXai4pXa","ali_nick_name":"花太香","mizuan":"461.00","mibi":1287.64,"r_mibi":"44.44","is_ali":1}]
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
         * id : 1151842
         * ali_user_id : 2088502344891503
         * ali_avatar : https://tfs.alipayobjects.com/images/partner/TB11Ou2alhDDuNjm2FfXXai4pXa
         * ali_nick_name : 花太香
         * mizuan : 461.00
         * mibi : 1287.64
         * r_mibi : 44.44
         * is_ali : 1
         */

        private int id;
        private String ali_user_id;
        private String ali_avatar;
        private String ali_nick_name;
        private String mizuan;
        private String mibi;
        private String r_mibi;
        private int is_ali;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAli_user_id() {
            return ali_user_id;
        }

        public void setAli_user_id(String ali_user_id) {
            this.ali_user_id = ali_user_id;
        }

        public String getAli_avatar() {
            return ali_avatar;
        }

        public void setAli_avatar(String ali_avatar) {
            this.ali_avatar = ali_avatar;
        }

        public String getAli_nick_name() {
            return ali_nick_name;
        }

        public void setAli_nick_name(String ali_nick_name) {
            this.ali_nick_name = ali_nick_name;
        }

        public String getMizuan() {
            return mizuan;
        }

        public void setMizuan(String mizuan) {
            this.mizuan = mizuan;
        }

        public String getMibi() {
            return mibi;
        }

        public void setMibi(String mibi) {
            this.mibi = mibi;
        }

        public String getR_mibi() {
            return r_mibi;
        }

        public void setR_mibi(String r_mibi) {
            this.r_mibi = r_mibi;
        }

        public int getIs_ali() {
            return is_ali;
        }

        public void setIs_ali(int is_ali) {
            this.is_ali = is_ali;
        }
    }
}
