package com.miniapp.talks.bean;

import java.util.List;

public class RoomRankBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"top":[{"img":"http://47.92.85.75/upload//cover/20190823/15665453025628.png","sort":1,"name":"向佳军吉祥物","id":"1100042","mizuan":2734107,"sex":2},{"img":"http://47.92.85.75/upload/cover/default.png","sort":2,"name":"Mil粒","id":"1100031","mizuan":1314689,"sex":1},{"img":"http://47.92.85.75/upload//cover/20190828/15669739565015.png","sort":3,"name":"我不要你觉得我要我觉得","id":"1100024","mizuan":1146914,"sex":1}],"other":[{"img":"http://47.92.85.75/upload//cover/20190825/15667344416670.png","sort":4,"name":"倾心娱乐🌟听歌交友","id":"20010","mizuan":749809,"sex":1},{"img":"http://47.92.85.75/upload//cover/20190907/15678469835929.jpg","sort":5,"name":"余声女神陪玩75套现踩","id":"10012","mizuan":279061,"sex":2},{"img":"http://47.92.85.75/upload//cover/20190904/15675678578413.png","sort":6,"name":"魅惑砸蛋爆奖率最高75收⭐⭐⭐","id":"1100587","mizuan":220891,"sex":1},{"img":"http://47.92.85.75/upload//cover/20190904/15675718605223.png","sort":7,"name":"ZZ优质男神","id":"1100049","mizuan":144286,"sex":2},{"img":"http://47.92.85.75/upload//cover/20190905/15676668756129.png","sort":8,"name":"红人阁电台陪玩陪聊","id":"1100085","mizuan":126853,"sex":1},{"img":"http://47.92.85.75/upload//cover/20190911/15681852931351.jpg","sort":9,"name":"星光娱乐✨75无限收礼物","id":"1100614","mizuan":122687,"sex":2},{"img":"http://47.92.85.75/upload//cover/20190910/15680957596052.png","sort":10,"name":"李软","id":"1100026","mizuan":59443,"sex":2},{"img":"http://47.92.85.75/upload/cover/default/8.jpg","sort":11,"name":"可口猫","id":"1100005","mizuan":13826,"sex":2},{"img":"http://47.92.85.75/upload//cover/20190816/15659518187528.jpg","sort":12,"name":"飛飛","id":"1100000","mizuan":6224,"sex":2},{"img":"http://47.92.85.75/upload//cover/20190911/15681851682323.png","sort":13,"name":"奇迹娱乐75秒礼物?欢迎大佬","id":"1101045","mizuan":5858,"sex":1},{"img":"http://47.92.85.75/upload//cover/20190823/15665256853821.png","sort":14,"name":"cf","id":"1100025","mizuan":3300,"sex":2},{"img":"http://47.92.85.75/upload//cover/20190816/15659496671308.jpg","sort":15,"name":"帅气阿呆","id":"1100001","mizuan":2882,"sex":2},{"img":"http://47.92.85.75/upload//cover/20190910/15681254809809.png","sort":16,"name":"优乐美女神团","id":"1100826","mizuan":1519,"sex":1},{"img":"http://47.92.85.75/upload//cover/20190822/15664773542592.png","sort":17,"name":"凤凰娱乐城","id":"1100040","mizuan":1432,"sex":1},{"img":"http://47.92.85.75/upload//cover/20190911/15681952053217.png","sort":18,"name":"耳语FM/新厅开业欢迎大家","id":"1100779","mizuan":1266,"sex":1},{"img":"http://tp5_erqi.miniyuyin.cn/upload/cover/20190916/15686252774578.png","sort":19,"name":"啾咪新人接待咨询厅","id":"1100032","mizuan":542,"sex":1},{"img":"http://47.92.85.75/upload//cover/20190910/15681025312714.png","sort":20,"name":"D8娱乐75%无限收礼物","id":"1100814","mizuan":490,"sex":1},{"img":"http://47.92.85.75/upload//cover/20190910/15681278775075.png","sort":21,"name":"国际娱乐✨收礼物祝大家中全服","id":"20011","mizuan":350,"sex":1},{"img":"http://47.92.85.75/upload/cover/default.png","sort":22,"name":"用户1100199的房间","id":"1100199","mizuan":285,"sex":1},{"img":"http://47.92.85.75/upload//cover/20190911/15681734467908.png","sort":23,"name":"As娱乐75无限收礼物","id":"1100125","mizuan":268,"sex":1},{"img":"http://47.92.85.75/upload//cover/20190906/15677523745274.png","sort":24,"name":"用户1100722的房间","id":"1100722","mizuan":157,"sex":2},{"img":"http://tp5_erqi.miniyuyin.cn/upload/cover/20190912/15682954093493.png","sort":25,"name":"小紫","id":"1100139","mizuan":35,"sex":2},{"img":"http://47.92.85.75/upload//cover/20190827/15669149108273.png","sort":26,"name":"\u2026\u2026","id":"1100290","mizuan":22,"sex":2},{"img":"http://47.92.85.75/upload//cover/20190903/15674939817199.png","sort":27,"name":"才艺男神：我再等风也再等你","id":"1100551","mizuan":16,"sex":1},{"img":"http://47.92.85.75/upload//cover/20190825/15667102659655.png","sort":28,"name":"梦想娱乐","id":"1100057","mizuan":14,"sex":2},{"img":"http://47.92.85.75/upload/cover/default.png","sort":29,"name":"用户1100754的房间","id":"1100754","mizuan":12,"sex":2},{"img":"http://47.92.85.75/upload//cover/20190902/15673906137195.png","sort":30,"name":"沒有人的房間？","id":"1100466","mizuan":3,"sex":2}]}
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
        private List<TopBean> top;
        private List<OtherBean> other;

        public List<TopBean> getTop() {
            return top;
        }

        public void setTop(List<TopBean> top) {
            this.top = top;
        }

        public List<OtherBean> getOther() {
            return other;
        }

        public void setOther(List<OtherBean> other) {
            this.other = other;
        }

        public static class TopBean {
            /**
             * img : http://47.92.85.75/upload//cover/20190823/15665453025628.png
             * sort : 1
             * name : 向佳军吉祥物
             * id : 1100042
             * mizuan : 2734107
             * sex : 2
             */

            private String img;
            private int sort;
            private String name;
            private String id;
            private String mizuan;
            private int sex;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMizuan() {
                return mizuan;
            }

            public void setMizuan(String mizuan) {
                this.mizuan = mizuan;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }
        }

        public static class OtherBean {
            /**
             * img : http://47.92.85.75/upload//cover/20190825/15667344416670.png
             * sort : 4
             * name : 倾心娱乐🌟听歌交友
             * id : 20010
             * mizuan : 749809
             * sex : 1
             */

            private String img;
            private int sort;
            private String name;
            private String id;
            private String mizuan;
            private int sex;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMizuan() {
                return mizuan;
            }

            public void setMizuan(String mizuan) {
                this.mizuan = mizuan;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }
        }
    }
}
