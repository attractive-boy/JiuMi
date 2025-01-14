package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class RoomBg {

    /**
     * code : 1
     * message : 获取成功
     * data : [{"id":8,"img":"http://59.110.169.251/upload/background/u=4218628255,555980278&fm=26&gp=0.jpg"},{"id":10,"img":"http://59.110.169.251/upload/background/25220_135926_8053.jpg"}]
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
         * id : 8
         * img : http://59.110.169.251/upload/background/u=4218628255,555980278&fm=26&gp=0.jpg
         */

        private int id;
        private String img;
        public boolean isSlect;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
