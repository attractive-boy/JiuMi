package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class GoodsList {

    /**
     * code : 1
     * message : 请求成功!
     * data : {"mizuan":"79917349.00","goods":[{"id":1,"price":6,"mizuan":60,"ratio":30,"give":18},{"id":2,"price":18,"mizuan":180,"ratio":30,"give":54},{"id":3,"price":68,"mizuan":680,"ratio":30,"give":204},{"id":4,"price":398,"mizuan":3980,"ratio":30,"give":1194},{"id":5,"price":1298,"mizuan":12980,"ratio":30,"give":3894},{"id":6,"price":3666,"mizuan":36660,"ratio":30,"give":10998}]}
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
         * mizuan : 79917349.00
         * goods : [{"id":1,"price":6,"mizuan":60,"ratio":30,"give":18},{"id":2,"price":18,"mizuan":180,"ratio":30,"give":54},{"id":3,"price":68,"mizuan":680,"ratio":30,"give":204},{"id":4,"price":398,"mizuan":3980,"ratio":30,"give":1194},{"id":5,"price":1298,"mizuan":12980,"ratio":30,"give":3894},{"id":6,"price":3666,"mizuan":36660,"ratio":30,"give":10998}]
         */

        private String mizuan;
        private List<GoodsBean> goods;

        public String getMizuan() {
            return mizuan;
        }

        public void setMizuan(String mizuan) {
            this.mizuan = mizuan;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * id : 1
             * price : 6
             * mizuan : 60
             * ratio : 30
             * give : 18
             */

            private int id;
            private int price;
            private int mizuan;
            private int ratio;
            private int give;
            public boolean isSelect;
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getMizuan() {
                return mizuan;
            }

            public void setMizuan(int mizuan) {
                this.mizuan = mizuan;
            }

            public int getRatio() {
                return ratio;
            }

            public void setRatio(int ratio) {
                this.ratio = ratio;
            }

            public int getGive() {
                return give;
            }

            public void setGive(int give) {
                this.give = give;
            }
        }
    }
}
