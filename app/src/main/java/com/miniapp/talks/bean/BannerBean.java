package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class BannerBean
{

    /**
     * code : 1
     * message : 获取成功
     * data : [{"id":3,"img":"images/3a898fd9999dd787f3cd6952ed8e72bd.jpg","contents":"ss","updated_at":"2019-06-03 13:58:00","created_at":"2019-06-03 13:58:00"}]
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
         * id : 3
         * img : images/3a898fd9999dd787f3cd6952ed8e72bd.jpg
         * contents : ss
         * updated_at : 2019-06-03 13:58:00
         * created_at : 2019-06-03 13:58:00
         */

        private int id;
        private String img;
        private String contents;
        private String updated_at;
        private String created_at;
        public String url;

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

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
