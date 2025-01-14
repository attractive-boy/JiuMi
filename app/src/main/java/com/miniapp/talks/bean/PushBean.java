package com.miniapp.talks.bean;

import com.orient.tea.barragephoto.model.DataSource;

/**
 * 作者:sgm
 * 描述:
 */
public class PushBean implements DataSource {


    /**
     * data : {"uid":1135549,"img":"http://47.92.85.75/upload//emoji/png/019.png","user_name":"糟老头","num":1,"gift_name":"梦幻乐园","from_name":"搜索用户"}
     * type : gift
     */

    private DataBean data;
    public String type;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

//    public String getType() {
//        return type;
//    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getType() {
        return 1;
    }

    public static class DataBean {
        /**
         * uid : 1135549
         * img : http://47.92.85.75/upload//emoji/png/019.png
         * user_name : 糟老头
         * num : 1
         * gift_name : 梦幻乐园
         * from_name : 搜索用户
         */

        private int uid;
        private String img;
        private String user_name;
        private int num;
        private String gift_name;
        private String from_name;
        private String boxclass;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getGift_name() {
            return gift_name;
        }

        public void setGift_name(String gift_name) {
            this.gift_name = gift_name;
        }

        public String getFrom_name() {
            return from_name;
        }

        public void setFrom_name(String from_name) {
            this.from_name = from_name;
        }

        public String getBoxclass() {
            return boxclass;
        }

        public void setBoxclass(String boxclass) {
            this.boxclass = boxclass;
        }
    }
}
