package com.miniapp.talks.bean;

import com.orient.tea.barragephoto.model.DataSource;

public class PushtwoBean implements DataSource {

    private PushBean.DataBean data;
    public String type;

    public PushBean.DataBean getData() {
        return data;
    }

    public void setData(PushBean.DataBean data) {
        this.data = data;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getType() {
        return 1;
    }

    public static class DataBean{

        private String img;
        private String user_name;
        private int num;
        private String gift_name;
        private String from_name;
        private String boxclass;

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
