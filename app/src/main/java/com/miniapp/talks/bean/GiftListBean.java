package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class GiftListBean {
    /**
     * code : 1
     * message : 获取成功
     * data : {"gifts":[{"id":6,"name":"水果拼盘","price":"166米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_sgpp02@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_sgpp@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/sgpp.svga","is_check":1,"wares_type":2},{"id":7,"name":"香水","price":"520米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_xs01@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_xs@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/xs.svga","is_check":0,"wares_type":2},{"id":19,"name":"许愿灯","price":"1314米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_kmd02@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_kmd@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/kmd.svga","is_check":0,"wares_type":2},{"id":25,"name":"纸飞机","price":"2666米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_zfj@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_zfj@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/zfj.svga","is_check":0,"wares_type":2},{"id":27,"name":"真爱超跑","price":"13140米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_zacp@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_zacp@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/zacp.svga","is_check":0,"wares_type":2},{"id":13,"name":"人气女王","price":"666米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_rqnw01@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_rqnw@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/rqnw.svga","is_check":0,"wares_type":2},{"id":17,"name":"烛光晚餐","price":"36888米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_zgwc01@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_zgwc@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/zgwc.svga","is_check":0,"wares_type":2},{"id":22,"name":"大红包","price":"8888米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_hbbz01@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_hbbz@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/jbbz.svga","is_check":0,"wares_type":2},{"id":24,"name":"金麦克","price":"266米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_jmk@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_jmk@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/jmk.svga","is_check":0,"wares_type":2},{"id":26,"name":"天使之心","price":"6666米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_tszx@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_tszx@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/tszx.svga","is_check":0,"wares_type":2},{"id":28,"name":"守护天使","price":"36888米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_shts@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_shts@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/shts.svga","is_check":0,"wares_type":2},{"id":29,"name":"水晶鞋","price":"888米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_sjx@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_sjx@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/sjx.svga","is_check":0,"wares_type":2},{"id":1,"name":"么么哒","price":"1米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_mmd@2x.png","type":1,"show_img":"http://47.92.85.75/upload/gifts/png/gift_mmd@2x.png","show_img2":"","is_check":0,"wares_type":2},{"id":2,"name":"呲水枪","price":"6米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_sq@2x.png","type":1,"show_img":"http://47.92.85.75/upload/gifts/png/gift_sq@2x.png","show_img2":"","is_check":0,"wares_type":2},{"id":3,"name":"小黄瓜","price":"10米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_xhg@2x.png","type":1,"show_img":"http://47.92.85.75/upload/gifts/png/gift_xhg@2x.png","show_img2":"","is_check":0,"wares_type":2},{"id":4,"name":"冰激凌","price":"66米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_bql@2x.png","type":1,"show_img":"http://47.92.85.75/upload/gifts/png/gift_bql@2x.png","show_img2":"","is_check":0,"wares_type":2},{"id":5,"name":"心动手链","price":"100米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_xdsl@2x.png","type":1,"show_img":"http://47.92.85.75/upload/gifts/png/gift_xdsl@2x.png","show_img2":"","is_check":0,"wares_type":2}],"baoshi":[{"id":1,"name":"守护之心","price":"99米币","img1":"emoji/bs/shzx.png","show_img":"http://47.92.85.75/upload/emoji/bs/shzx.png","get_type":4,"type":1,"is_check":1,"img":"http://47.92.85.75/upload/emoji/bs/shzx.png","wares_type":1},{"id":2,"name":"守护之钻","price":"宝箱开出","img1":"","show_img":"","get_type":3,"type":1,"is_check":0,"img":"","wares_type":1},{"id":3,"name":"守护之翼","price":"宝箱开出","img1":"","show_img":"","get_type":3,"type":1,"is_check":0,"img":"","wares_type":1},{"id":4,"name":"守护之冠","price":"宝箱开出","img1":"emoji/bs/shzg.png","show_img":"http://47.92.85.75/upload/emoji/bs/shzg.png","get_type":3,"type":1,"is_check":0,"img":"http://47.92.85.75/upload/emoji/bs/shzg.png","wares_type":1},{"id":5,"name":"守护天使","price":"限时购买","img1":"emoji/bs/shts.png","show_img":"http://47.92.85.75/upload/emoji/bs/shts.png","get_type":6,"type":1,"is_check":0,"img":"http://47.92.85.75/upload/emoji/bs/shts.png","wares_type":1}],"my_wares":[{"id":1,"num":2,"target_id":1,"get_type":3,"expire":0,"name":"守护之心","price":"x2","img1":"emoji/bs/shzx.png","show_img":"http://47.92.85.75/upload/emoji/bs/shzx.png","type":1,"img":"http://47.92.85.75/upload/emoji/bs/shzx.png","show_img2":"","wares_type":1,"is_check":1},{"id":2,"num":3,"target_id":2,"get_type":3,"expire":0,"name":"守护之钻","price":"x3","img1":"","show_img":"","type":1,"img":"","show_img2":"","wares_type":1,"is_check":0},{"id":6,"num":1,"target_id":6,"get_type":3,"expire":0,"name":"爆音卡","price":"x1","img1":"emoji/kq/byk.png","show_img":"http://47.92.85.75/upload/emoji/kq/byk.png","type":1,"img":"http://47.92.85.75/upload/emoji/kq/byk.png","show_img2":"","wares_type":3,"is_check":0},{"id":10,"num":2,"target_id":10,"get_type":3,"name":"心动钻戒","price":"x2","img":"http://47.92.85.75/upload/gifts/png/gift_list_xdzj@2x.png","show_img":"http://47.92.85.75/upload/gifts/png/gift_xdzj@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/xdzj.svga","type":2,"wares_type":2,"is_check":0},{"id":12,"num":1,"target_id":12,"get_type":3,"name":"心动婚纱","price":"x1","img":"http://47.92.85.75/upload/gifts/png/gift_list_xdhs02@2x.png","show_img":"http://47.92.85.75/upload/gifts/png/gift_xdhs@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/xdhs.svga","type":2,"wares_type":2,"is_check":0},{"id":6,"num":20,"target_id":6,"get_type":3,"name":"水果拼盘","price":"x20","img":"http://47.92.85.75/upload/gifts/png/gift_list_sgpp02@2x.png","show_img":"http://47.92.85.75/upload/gifts/png/gift_sgpp@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/sgpp.svga","type":2,"wares_type":2,"is_check":0}],"mizuan":"450.00"}
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
         * gifts : [{"id":6,"name":"水果拼盘","price":"166米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_sgpp02@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_sgpp@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/sgpp.svga","is_check":1,"wares_type":2},{"id":7,"name":"香水","price":"520米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_xs01@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_xs@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/xs.svga","is_check":0,"wares_type":2},{"id":19,"name":"许愿灯","price":"1314米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_kmd02@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_kmd@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/kmd.svga","is_check":0,"wares_type":2},{"id":25,"name":"纸飞机","price":"2666米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_zfj@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_zfj@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/zfj.svga","is_check":0,"wares_type":2},{"id":27,"name":"真爱超跑","price":"13140米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_zacp@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_zacp@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/zacp.svga","is_check":0,"wares_type":2},{"id":13,"name":"人气女王","price":"666米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_rqnw01@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_rqnw@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/rqnw.svga","is_check":0,"wares_type":2},{"id":17,"name":"烛光晚餐","price":"36888米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_zgwc01@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_zgwc@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/zgwc.svga","is_check":0,"wares_type":2},{"id":22,"name":"大红包","price":"8888米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_hbbz01@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_hbbz@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/jbbz.svga","is_check":0,"wares_type":2},{"id":24,"name":"金麦克","price":"266米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_jmk@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_jmk@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/jmk.svga","is_check":0,"wares_type":2},{"id":26,"name":"天使之心","price":"6666米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_tszx@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_tszx@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/tszx.svga","is_check":0,"wares_type":2},{"id":28,"name":"守护天使","price":"36888米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_shts@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_shts@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/shts.svga","is_check":0,"wares_type":2},{"id":29,"name":"水晶鞋","price":"888米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_sjx@2x.png","type":2,"show_img":"http://47.92.85.75/upload/gifts/png/gift_sjx@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/sjx.svga","is_check":0,"wares_type":2},{"id":1,"name":"么么哒","price":"1米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_mmd@2x.png","type":1,"show_img":"http://47.92.85.75/upload/gifts/png/gift_mmd@2x.png","show_img2":"","is_check":0,"wares_type":2},{"id":2,"name":"呲水枪","price":"6米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_sq@2x.png","type":1,"show_img":"http://47.92.85.75/upload/gifts/png/gift_sq@2x.png","show_img2":"","is_check":0,"wares_type":2},{"id":3,"name":"小黄瓜","price":"10米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_xhg@2x.png","type":1,"show_img":"http://47.92.85.75/upload/gifts/png/gift_xhg@2x.png","show_img2":"","is_check":0,"wares_type":2},{"id":4,"name":"冰激凌","price":"66米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_bql@2x.png","type":1,"show_img":"http://47.92.85.75/upload/gifts/png/gift_bql@2x.png","show_img2":"","is_check":0,"wares_type":2},{"id":5,"name":"心动手链","price":"100米币","img":"http://47.92.85.75/upload/gifts/png/gift_list_xdsl@2x.png","type":1,"show_img":"http://47.92.85.75/upload/gifts/png/gift_xdsl@2x.png","show_img2":"","is_check":0,"wares_type":2}]
         * baoshi : [{"id":1,"name":"守护之心","price":"99米币","img1":"emoji/bs/shzx.png","show_img":"http://47.92.85.75/upload/emoji/bs/shzx.png","get_type":4,"type":1,"is_check":1,"img":"http://47.92.85.75/upload/emoji/bs/shzx.png","wares_type":1},{"id":2,"name":"守护之钻","price":"宝箱开出","img1":"","show_img":"","get_type":3,"type":1,"is_check":0,"img":"","wares_type":1},{"id":3,"name":"守护之翼","price":"宝箱开出","img1":"","show_img":"","get_type":3,"type":1,"is_check":0,"img":"","wares_type":1},{"id":4,"name":"守护之冠","price":"宝箱开出","img1":"emoji/bs/shzg.png","show_img":"http://47.92.85.75/upload/emoji/bs/shzg.png","get_type":3,"type":1,"is_check":0,"img":"http://47.92.85.75/upload/emoji/bs/shzg.png","wares_type":1},{"id":5,"name":"守护天使","price":"限时购买","img1":"emoji/bs/shts.png","show_img":"http://47.92.85.75/upload/emoji/bs/shts.png","get_type":6,"type":1,"is_check":0,"img":"http://47.92.85.75/upload/emoji/bs/shts.png","wares_type":1}]
         * my_wares : [{"id":1,"num":2,"target_id":1,"get_type":3,"expire":0,"name":"守护之心","price":"x2","img1":"emoji/bs/shzx.png","show_img":"http://47.92.85.75/upload/emoji/bs/shzx.png","type":1,"img":"http://47.92.85.75/upload/emoji/bs/shzx.png","show_img2":"","wares_type":1,"is_check":1},{"id":2,"num":3,"target_id":2,"get_type":3,"expire":0,"name":"守护之钻","price":"x3","img1":"","show_img":"","type":1,"img":"","show_img2":"","wares_type":1,"is_check":0},{"id":6,"num":1,"target_id":6,"get_type":3,"expire":0,"name":"爆音卡","price":"x1","img1":"emoji/kq/byk.png","show_img":"http://47.92.85.75/upload/emoji/kq/byk.png","type":1,"img":"http://47.92.85.75/upload/emoji/kq/byk.png","show_img2":"","wares_type":3,"is_check":0},{"id":10,"num":2,"target_id":10,"get_type":3,"name":"心动钻戒","price":"x2","img":"http://47.92.85.75/upload/gifts/png/gift_list_xdzj@2x.png","show_img":"http://47.92.85.75/upload/gifts/png/gift_xdzj@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/xdzj.svga","type":2,"wares_type":2,"is_check":0},{"id":12,"num":1,"target_id":12,"get_type":3,"name":"心动婚纱","price":"x1","img":"http://47.92.85.75/upload/gifts/png/gift_list_xdhs02@2x.png","show_img":"http://47.92.85.75/upload/gifts/png/gift_xdhs@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/xdhs.svga","type":2,"wares_type":2,"is_check":0},{"id":6,"num":20,"target_id":6,"get_type":3,"name":"水果拼盘","price":"x20","img":"http://47.92.85.75/upload/gifts/png/gift_list_sgpp02@2x.png","show_img":"http://47.92.85.75/upload/gifts/png/gift_sgpp@2x.png","show_img2":"http://47.92.85.75/upload/gifts/svga/sgpp.svga","type":2,"wares_type":2,"is_check":0}]
         * mizuan : 450.00
         */

        private String mizuan;
        private List<GiftsBean> gifts;
        private List<BaoshiBean> baoshi;
        private List<MyWaresBean> my_wares;

        public String getMizuan() {
            return mizuan;
        }

        public void setMizuan(String mizuan) {
            this.mizuan = mizuan;
        }

        public List<GiftsBean> getGifts() {
            return gifts;
        }

        public void setGifts(List<GiftsBean> gifts) {
            this.gifts = gifts;
        }

        public List<BaoshiBean> getBaoshi() {
            return baoshi;
        }

        public void setBaoshi(List<BaoshiBean> baoshi) {
            this.baoshi = baoshi;
        }

        public List<MyWaresBean> getMy_wares() {
            return my_wares;
        }

        public void setMy_wares(List<MyWaresBean> my_wares) {
            this.my_wares = my_wares;
        }

        public static class GiftsBean {
            /**
             * id : 6
             * name : 水果拼盘
             * price : 166米币
             * img : http://47.92.85.75/upload/gifts/png/gift_list_sgpp02@2x.png
             * type : 2
             * show_img : http://47.92.85.75/upload/gifts/png/gift_sgpp@2x.png
             * show_img2 : http://47.92.85.75/upload/gifts/svga/sgpp.svga
             * is_check : 1
             * wares_type : 2
             */

            private String id;
            private String name;
            private String price;
            private String img;
            private String type;
            private String show_img;
            private String show_img2;
            public String e_name;
            private int is_check;
            private int wares_type=2;//2 礼物 1 宝石 3 卡片
            private String price_004;

            public String getPrice_004() {
                return price_004;
            }

            public void setPrice_004(String price_004) {
                this.price_004 = price_004;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getShow_img() {
                return show_img;
            }

            public void setShow_img(String show_img) {
                this.show_img = show_img;
            }

            public String getShow_img2() {
                return show_img2;
            }

            public void setShow_img2(String show_img2) {
                this.show_img2 = show_img2;
            }

            public int getIs_check() {
                return is_check;
            }

            public void setIs_check(int is_check) {
                this.is_check = is_check;
            }

            public int getWares_type() {
                return wares_type;
            }

            public void setWares_type(int wares_type) {
                this.wares_type = wares_type;
            }
        }

        public static class BaoshiBean {
            /**
             * id : 1
             * name : 守护之心
             * price : 99米币
             * img1 : emoji/bs/shzx.png
             * show_img : http://47.92.85.75/upload/emoji/bs/shzx.png
             * get_type : 4
             * type : 1
             * is_check : 1
             * img : http://47.92.85.75/upload/emoji/bs/shzx.png
             * wares_type : 1
             */

            private String id;
            private String name;
            private String price;
            private String img1;
            private String show_img;
            private String show_img2;
            private int get_type;
            private int type;
            private int is_check;
            private String img;
            private int wares_type=1;
            private String price_004;

            public String getPrice_004() {
                return price_004;
            }

            public void setPrice_004(String price_004) {
                this.price_004 = price_004;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getShow_img() {
                return show_img;
            }

            public void setShow_img(String show_img) {
                this.show_img = show_img;
            }

            public int getGet_type() {
                return get_type;
            }

            public void setGet_type(int get_type) {
                this.get_type = get_type;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getIs_check() {
                return is_check;
            }

            public void setIs_check(int is_check) {
                this.is_check = is_check;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getWares_type() {
                return wares_type;
            }

            public void setWares_type(int wares_type) {
                this.wares_type = wares_type;
            }

            public String getShow_img2() {
                return show_img2;
            }

            public void setShow_img2(String show_img2) {
                this.show_img2 = show_img2;
            }
        }

        public static class MyWaresBean {
            /**
             * id : 1
             * num : 2
             * target_id : 1
             * get_type : 3
             * expire : 0
             * name : 守护之心
             * price : x2
             * img1 : emoji/bs/shzx.png
             * show_img : http://47.92.85.75/upload/emoji/bs/shzx.png
             * type : 1
             * img : http://47.92.85.75/upload/emoji/bs/shzx.png
             * show_img2 :
             * wares_type : 1
             * is_check : 1
             */

            private String id;
            private int num;
            private int target_id;
            private int get_type;
            private int expire;
            private String name;
            private String price;
            private String img1;
            private String show_img;
            private int type;
            private String img;
            private String show_img2;
            private int wares_type;
            private int is_check;
            private String price_004;

            public String getPrice_004() {
                return price_004;
            }

            public void setPrice_004(String price_004) {
                this.price_004 = price_004;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getTarget_id() {
                return target_id;
            }

            public void setTarget_id(int target_id) {
                this.target_id = target_id;
            }

            public int getGet_type() {
                return get_type;
            }

            public void setGet_type(int get_type) {
                this.get_type = get_type;
            }

            public int getExpire() {
                return expire;
            }

            public void setExpire(int expire) {
                this.expire = expire;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getShow_img() {
                return show_img;
            }

            public void setShow_img(String show_img) {
                this.show_img = show_img;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getShow_img2() {
                return show_img2;
            }

            public void setShow_img2(String show_img2) {
                this.show_img2 = show_img2;
            }

            public int getWares_type() {
                return wares_type;
            }

            public void setWares_type(int wares_type) {
                this.wares_type = wares_type;
            }

            public int getIs_check() {
                return is_check;
            }

            public void setIs_check(int is_check) {
                this.is_check = is_check;
            }
        }
    }


//    /**
//     * code : 1
//     * message : 获取成功
//     * data : {"gifts":[{"id":19,"name":"猪头","price":5000,"img":"http://47.92.85.75/upload//emoji/png/023.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/534.gif","is_check":1},{"id":18,"name":"心碎","price":1000,"img":"http://47.92.85.75/upload//emoji/png/022.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/533.gif","is_check":0},{"id":17,"name":"吐舌","price":950,"img":"http://47.92.85.75/upload//emoji/png/021.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/532.gif","is_check":0},{"id":16,"name":"思考","price":900,"img":"http://47.92.85.75/upload//emoji/png/019.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/531.gif","is_check":0},{"id":15,"name":"睡觉","price":850,"img":"http://47.92.85.75/upload//emoji/png/018.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/530.gif","is_check":0},{"id":14,"name":"生气","price":800,"img":"http://47.92.85.75/upload//emoji/png/017.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/529.gif","is_check":0},{"id":13,"name":"魔鬼","price":750,"img":"http://47.92.85.75/upload//emoji/png/015.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/526.gif","is_check":0},{"id":12,"name":"右亲亲","price":700,"img":"http://47.92.85.75/upload//emoji/png/014.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/525.gif","is_check":0},{"id":11,"name":"快哭了","price":650,"img":"http://47.92.85.75/upload//emoji/png/013.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/524.gif","is_check":0},{"id":10,"name":"开���","price":600,"img":"http://47.92.85.75/upload//emoji/png/012.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/523.gif","is_check":0},{"id":9,"name":"举手","price":550,"img":"http://47.92.85.75/upload//emoji/png/011.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/011.png","is_check":0},{"id":8,"name":"惊恐","price":500,"img":"http://47.92.85.75/upload//emoji/png/010.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/010.png","is_check":0},{"id":7,"name":"尴尬","price":450,"img":"http://47.92.85.75/upload//emoji/png/009.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/009.png","is_check":0},{"id":6,"name":"倒计时","price":400,"img":"http://47.92.85.75/upload//emoji/png/008.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/008.png","is_check":0},{"id":5,"name":"大哭","price":300,"img":"http://47.92.85.75/upload//emoji/png/007.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/007.png","is_check":0},{"id":4,"name":"不开心","price":200,"img":"http://47.92.85.75/upload//emoji/png/004.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/004.png","is_check":0},{"id":3,"name":"闭嘴","price":100,"img":"http://47.92.85.75/upload//emoji/png/003.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/003.png","is_check":0},{"id":2,"name":"爆灯","price":10,"img":"http://47.92.85.75/upload//emoji/png/002.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/002.png","is_check":0},{"id":1,"name":"白眼","price":1,"img":"http://47.92.85.75/upload//emoji/png/001.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/001.png","is_check":0}],"mizuan":"1000.00"}
//     */
//
//    private int code;
//    private String message;
//    private DataBean data;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * gifts : [{"id":19,"name":"猪头","price":5000,"img":"http://47.92.85.75/upload//emoji/png/023.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/534.gif","is_check":1},{"id":18,"name":"心碎","price":1000,"img":"http://47.92.85.75/upload//emoji/png/022.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/533.gif","is_check":0},{"id":17,"name":"吐舌","price":950,"img":"http://47.92.85.75/upload//emoji/png/021.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/532.gif","is_check":0},{"id":16,"name":"思考","price":900,"img":"http://47.92.85.75/upload//emoji/png/019.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/531.gif","is_check":0},{"id":15,"name":"睡觉","price":850,"img":"http://47.92.85.75/upload//emoji/png/018.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/530.gif","is_check":0},{"id":14,"name":"生气","price":800,"img":"http://47.92.85.75/upload//emoji/png/017.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/529.gif","is_check":0},{"id":13,"name":"魔鬼","price":750,"img":"http://47.92.85.75/upload//emoji/png/015.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/526.gif","is_check":0},{"id":12,"name":"右亲亲","price":700,"img":"http://47.92.85.75/upload//emoji/png/014.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/525.gif","is_check":0},{"id":11,"name":"快哭了","price":650,"img":"http://47.92.85.75/upload//emoji/png/013.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/524.gif","is_check":0},{"id":10,"name":"开���","price":600,"img":"http://47.92.85.75/upload//emoji/png/012.png","type":2,"show_img":"http://47.92.85.75/upload//emoji/gif/523.gif","is_check":0},{"id":9,"name":"举手","price":550,"img":"http://47.92.85.75/upload//emoji/png/011.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/011.png","is_check":0},{"id":8,"name":"惊恐","price":500,"img":"http://47.92.85.75/upload//emoji/png/010.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/010.png","is_check":0},{"id":7,"name":"尴尬","price":450,"img":"http://47.92.85.75/upload//emoji/png/009.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/009.png","is_check":0},{"id":6,"name":"倒计时","price":400,"img":"http://47.92.85.75/upload//emoji/png/008.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/008.png","is_check":0},{"id":5,"name":"大哭","price":300,"img":"http://47.92.85.75/upload//emoji/png/007.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/007.png","is_check":0},{"id":4,"name":"不开心","price":200,"img":"http://47.92.85.75/upload//emoji/png/004.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/004.png","is_check":0},{"id":3,"name":"闭嘴","price":100,"img":"http://47.92.85.75/upload//emoji/png/003.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/003.png","is_check":0},{"id":2,"name":"爆灯","price":10,"img":"http://47.92.85.75/upload//emoji/png/002.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/002.png","is_check":0},{"id":1,"name":"白眼","price":1,"img":"http://47.92.85.75/upload//emoji/png/001.png","type":1,"show_img":"http://47.92.85.75/upload//emoji/png/001.png","is_check":0}]
//         * mizuan : 1000.00
//         */
//
//        private String mizuan;
//        private List<GiftsBean> gifts;
//
//        public String getMizuan() {
//            return mizuan;
//        }
//
//        public void setMizuan(String mizuan) {
//            this.mizuan = mizuan;
//        }
//
//        public List<GiftsBean> getGifts() {
//            return gifts;
//        }
//
//        public void setGifts(List<GiftsBean> gifts) {
//            this.gifts = gifts;
//        }
//
//        public static class GiftsBean {
//            public boolean isSelect;
//            /**
//             * id : 19
//             * name : 猪头
//             * price : 5000
//             * img : http://47.92.85.75/upload//emoji/png/023.png
//             * type : 2
//             * show_img : http://47.92.85.75/upload//emoji/gif/534.gif
//             * is_check : 1
//             */
//
//            private int id;
//            private String name;
//            private String price;
//            private String img;
//            private String type;
//            private String show_img;
//            public String show_img2;
//            private int is_check;
//            public String e_name;
//            private int wares_type;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getPrice() {
//                return price;
//            }
//
//            public void setPrice(String price) {
//                this.price = price;
//            }
//
//            public String getImg() {
//                return img;
//            }
//
//            public void setImg(String img) {
//                this.img = img;
//            }
//
//            public String getType() {
//                return type;
//            }
//
//            public void setType(String type) {
//                this.type = type;
//            }
//
//            public String getShow_img() {
//                return show_img;
//            }
//
//            public void setShow_img(String show_img) {
//                this.show_img = show_img;
//            }
//
//            public int getIs_check() {
//                return is_check;
//            }
//
//            public void setIs_check(int is_check) {
//                this.is_check = is_check;
//            }
//
//            public int getWares_type() {
//                return wares_type;
//            }
//
//            public void setWares_type(int wares_type) {
//                this.wares_type = wares_type;
//            }
//        }
//    }
}
