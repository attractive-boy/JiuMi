package com.miniapp.talks.bean;

import java.util.List;

public class CPDetailsBean {


    /**
     * code : 1
     * message : 请求成功
     * data : {"cp":{"id":34,"exp":99,"wares_id":1,"user_id":1100119,"fromUid":1100001,"agreetime":1568684472,"cp_level":0,"user_nick":"王老头","user_head":"http://47.92.85.75/upload//avatar/20190823/15665504727568.png","from_nick":"白羊","from_head":"http://47.92.85.75/upload//cover/20190816/15659489046210.jpg","bs_img":"http://47.92.85.75/upload/emoji/bs/wdj.png","days":"3","cp_type":1,"next_cp_num":200,"next_cp_level":1},"auth":[{"id":10,"type":5,"level":1,"name":"专属CP位","title":"专属CP位","img_0":"http://47.92.85.75/upload//emoji/bs/cp_off/cp_wutq_cpw.png","img_1":"http://47.92.85.75/upload//emoji/bs/cp_on/cp_youtq_cpw.png","addtime":1564136134,"is_on":0},{"id":11,"type":5,"level":3,"name":"同房特效","title":"同房特效","img_0":"http://47.92.85.75/upload//emoji/bs/cp_off/cp_wutq_tftx.png","img_1":"http://47.92.85.75/upload//emoji/bs/cp_on/cp_youtq_tftx.png","addtime":1564136135,"is_on":0},{"id":12,"type":5,"level":5,"name":"携手上麦特效","title":"携手上麦特效","img_0":"http://47.92.85.75/upload//emoji/bs/cp_off/cp_wutq_xssm.png","img_1":"http://47.92.85.75/upload//emoji/bs/cp_on/cp_youtq_xssm.png","addtime":1564136136,"is_on":0},{"id":13,"type":5,"level":7,"name":"召唤陪伴","title":"召唤陪伴","img_0":"http://47.92.85.75/upload//emoji/bs/cp_off/cp_wutq_zhpb.png","img_1":"http://47.92.85.75/upload//emoji/bs/cp_on/cp_youtq_zhpb.png","addtime":1564136137,"is_on":0},{"id":14,"type":5,"level":9,"name":"CP头像框","title":"CP头像框","img_0":"http://47.92.85.75/upload//emoji/bs/cp_off/cp_wutq_txk.png","img_1":"http://47.92.85.75/upload//emoji/bs/cp_on/cp_youtq_txk.png","addtime":1564136138,"is_on":0}]}
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
         * cp : {"id":34,"exp":99,"wares_id":1,"user_id":1100119,"fromUid":1100001,"agreetime":1568684472,"cp_level":0,"user_nick":"王老头","user_head":"http://47.92.85.75/upload//avatar/20190823/15665504727568.png","from_nick":"白羊","from_head":"http://47.92.85.75/upload//cover/20190816/15659489046210.jpg","bs_img":"http://47.92.85.75/upload/emoji/bs/wdj.png","days":"3","cp_type":1,"next_cp_num":200,"next_cp_level":1}
         * auth : [{"id":10,"type":5,"level":1,"name":"专属CP位","title":"专属CP位","img_0":"http://47.92.85.75/upload//emoji/bs/cp_off/cp_wutq_cpw.png","img_1":"http://47.92.85.75/upload//emoji/bs/cp_on/cp_youtq_cpw.png","addtime":1564136134,"is_on":0},{"id":11,"type":5,"level":3,"name":"同房特效","title":"同房特效","img_0":"http://47.92.85.75/upload//emoji/bs/cp_off/cp_wutq_tftx.png","img_1":"http://47.92.85.75/upload//emoji/bs/cp_on/cp_youtq_tftx.png","addtime":1564136135,"is_on":0},{"id":12,"type":5,"level":5,"name":"携手上麦特效","title":"携手上麦特效","img_0":"http://47.92.85.75/upload//emoji/bs/cp_off/cp_wutq_xssm.png","img_1":"http://47.92.85.75/upload//emoji/bs/cp_on/cp_youtq_xssm.png","addtime":1564136136,"is_on":0},{"id":13,"type":5,"level":7,"name":"召唤陪伴","title":"召唤陪伴","img_0":"http://47.92.85.75/upload//emoji/bs/cp_off/cp_wutq_zhpb.png","img_1":"http://47.92.85.75/upload//emoji/bs/cp_on/cp_youtq_zhpb.png","addtime":1564136137,"is_on":0},{"id":14,"type":5,"level":9,"name":"CP头像框","title":"CP头像框","img_0":"http://47.92.85.75/upload//emoji/bs/cp_off/cp_wutq_txk.png","img_1":"http://47.92.85.75/upload//emoji/bs/cp_on/cp_youtq_txk.png","addtime":1564136138,"is_on":0}]
         */

        private CpBean cp;
        private List<AuthBean> auth;

        public CpBean getCp() {
            return cp;
        }

        public void setCp(CpBean cp) {
            this.cp = cp;
        }

        public List<AuthBean> getAuth() {
            return auth;
        }

        public void setAuth(List<AuthBean> auth) {
            this.auth = auth;
        }

        public static class CpBean {
            /**
             * id : 34
             * exp : 99
             * wares_id : 1
             * user_id : 1100119
             * fromUid : 1100001
             * agreetime : 1568684472
             * cp_level : 0
             * user_nick : 王老头
             * user_head : http://47.92.85.75/upload//avatar/20190823/15665504727568.png
             * from_nick : 白羊
             * from_head : http://47.92.85.75/upload//cover/20190816/15659489046210.jpg
             * bs_img : http://47.92.85.75/upload/emoji/bs/wdj.png
             * days : 3
             * cp_type : 1
             * next_cp_num : 200
             * next_cp_level : 1
             */

            private int id;
            private int exp;
            private int wares_id;
            private int user_id;
            private int fromUid;
            private int agreetime;
            private int cp_level;
            private String user_nick;
            private String user_head;
            private String from_nick;
            private String from_head;
            private String bs_img;
            private String days;
            private int cp_type;
            private int next_cp_num;
            private int next_cp_level;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getExp() {
                return exp;
            }

            public void setExp(int exp) {
                this.exp = exp;
            }

            public int getWares_id() {
                return wares_id;
            }

            public void setWares_id(int wares_id) {
                this.wares_id = wares_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getFromUid() {
                return fromUid;
            }

            public void setFromUid(int fromUid) {
                this.fromUid = fromUid;
            }

            public int getAgreetime() {
                return agreetime;
            }

            public void setAgreetime(int agreetime) {
                this.agreetime = agreetime;
            }

            public int getCp_level() {
                return cp_level;
            }

            public void setCp_level(int cp_level) {
                this.cp_level = cp_level;
            }

            public String getUser_nick() {
                return user_nick;
            }

            public void setUser_nick(String user_nick) {
                this.user_nick = user_nick;
            }

            public String getUser_head() {
                return user_head;
            }

            public void setUser_head(String user_head) {
                this.user_head = user_head;
            }

            public String getFrom_nick() {
                return from_nick;
            }

            public void setFrom_nick(String from_nick) {
                this.from_nick = from_nick;
            }

            public String getFrom_head() {
                return from_head;
            }

            public void setFrom_head(String from_head) {
                this.from_head = from_head;
            }

            public String getBs_img() {
                return bs_img;
            }

            public void setBs_img(String bs_img) {
                this.bs_img = bs_img;
            }

            public String getDays() {
                return days;
            }

            public void setDays(String days) {
                this.days = days;
            }

            public int getCp_type() {
                return cp_type;
            }

            public void setCp_type(int cp_type) {
                this.cp_type = cp_type;
            }

            public int getNext_cp_num() {
                return next_cp_num;
            }

            public void setNext_cp_num(int next_cp_num) {
                this.next_cp_num = next_cp_num;
            }

            public int getNext_cp_level() {
                return next_cp_level;
            }

            public void setNext_cp_level(int next_cp_level) {
                this.next_cp_level = next_cp_level;
            }
        }

        public static class AuthBean {
            /**
             * id : 10
             * type : 5
             * level : 1
             * name : 专属CP位
             * title : 专属CP位
             * img_0 : http://47.92.85.75/upload//emoji/bs/cp_off/cp_wutq_cpw.png
             * img_1 : http://47.92.85.75/upload//emoji/bs/cp_on/cp_youtq_cpw.png
             * addtime : 1564136134
             * is_on : 0
             */

            private int id;
            private int type;
            private int level;
            private String name;
            private String title;
            private String img_0;
            private String img_1;
            private int addtime;
            private int is_on;

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

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg_0() {
                return img_0;
            }

            public void setImg_0(String img_0) {
                this.img_0 = img_0;
            }

            public String getImg_1() {
                return img_1;
            }

            public void setImg_1(String img_1) {
                this.img_1 = img_1;
            }

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
                this.addtime = addtime;
            }

            public int getIs_on() {
                return is_on;
            }

            public void setIs_on(int is_on) {
                this.is_on = is_on;
            }
        }
    }
}
