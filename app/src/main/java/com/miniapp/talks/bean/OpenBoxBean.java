package com.miniapp.talks.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenBoxBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"awardList":[{"id":824,"type":3,"wares_id":9,"num":2,"class":1,"name":"头条卡","show_img":""},{"id":342,"type":2,"wares_id":2,"num":2,"class":1,"name":"呲水枪","show_img":"http://tp5.miniyuyin.cn/upload/box/83cb17ffd0374e03b7fcd5769be74e61.png"},{"id":481,"type":2,"wares_id":3,"num":4,"class":1,"name":"小黄瓜","show_img":"http://tp5.miniyuyin.cn/upload/box/52818473781a8e33d6d90dced4873580.png"},{"id":180,"type":2,"wares_id":1,"num":1,"class":1,"name":"么么哒","show_img":"http://tp5.miniyuyin.cn/upload/box/a9fdc621cd0624f8deaa0901b8d7d217.png"},{"id":666,"type":2,"wares_id":4,"num":1,"class":1,"name":"冰激凌","show_img":"http://tp5.miniyuyin.cn/upload/box/39bebf5a604494481be991f44e0bb04c.png"}],"ispointsfirst":0,"box_class":1}
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
         * awardList : [{"id":824,"type":3,"wares_id":9,"num":2,"class":1,"name":"头条卡","show_img":""},{"id":342,"type":2,"wares_id":2,"num":2,"class":1,"name":"呲水枪","show_img":"http://tp5.miniyuyin.cn/upload/box/83cb17ffd0374e03b7fcd5769be74e61.png"},{"id":481,"type":2,"wares_id":3,"num":4,"class":1,"name":"小黄瓜","show_img":"http://tp5.miniyuyin.cn/upload/box/52818473781a8e33d6d90dced4873580.png"},{"id":180,"type":2,"wares_id":1,"num":1,"class":1,"name":"么么哒","show_img":"http://tp5.miniyuyin.cn/upload/box/a9fdc621cd0624f8deaa0901b8d7d217.png"},{"id":666,"type":2,"wares_id":4,"num":1,"class":1,"name":"冰激凌","show_img":"http://tp5.miniyuyin.cn/upload/box/39bebf5a604494481be991f44e0bb04c.png"}]
         * ispointsfirst : 0
         * box_class : 1
         */

        private int ispointsfirst;
        private int box_class;//1 普通宝箱，2 守护宝箱
        private String award_tips = "";
        private String user_name;
        private String user_id;


        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        private List<AwardListBean> awardList;

        public String getAward_tips() {
            return award_tips;
        }

        public void setAward_tips(String award_tips) {
            this.award_tips = award_tips;
        }

        public int getIspointsfirst() {
            return ispointsfirst;
        }

        public void setIspointsfirst(int ispointsfirst) {
            this.ispointsfirst = ispointsfirst;
        }

        public int getBox_class() {
            return box_class;
        }

        public void setBox_class(int box_class) {
            this.box_class = box_class;
        }

        public List<AwardListBean> getAwardList() {
            return awardList;
        }

        public void setAwardList(List<AwardListBean> awardList) {
            this.awardList = awardList;
        }

        public static class AwardListBean {
            /**
             * id : 824
             * type : 3
             * wares_id : 9
             * num : 2
             * class : 1
             * name : 头条卡
             * show_img :
             */

            private int id;
            private int type;
            private int wares_id;
            private int num;
            @SerializedName("class")
            private int classX;
            private String name;
            private String show_img;
            private String price="";

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getWares_id() {
                return wares_id;
            }

            public void setWares_id(int wares_id) {
                this.wares_id = wares_id;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getClassX() {
                return classX;
            }

            public void setClassX(int classX) {
                this.classX = classX;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShow_img() {
                return show_img;
            }

            public void setShow_img(String show_img) {
                this.show_img = show_img;
            }
        }
    }
}
