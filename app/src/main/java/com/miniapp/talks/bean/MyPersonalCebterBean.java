package com.miniapp.talks.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MyPersonalCebterBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"userInfo":{"id":1860,"headimgurl":"http://47.92.85.75/upload//avatar/20190727/15642090646712.png","nickname":"小丸子","sex":1,"birthday":"2019-01-01","constellation":"摩羯座","city":"月球","ry_uid":"1860","age":0,"fabu":3,"follows_num":2,"fans_num":0,"star_level":17,"gold_level":5,"vip_level":5,"hz_level":3,"is_follow":0},"glory":[{"type":4,"level":3,"name":"VIP3级","img":"http://47.92.85.75/upload/emoji/vip_ico/hz/huizhang_2.png"}],"roomInfo":{},"gifts":[{"giftId":10,"giftName":"心动钻戒","show_img":"gifts/png/gift_xdzj@2x.png","sum":"1337","img":"http://47.92.85.75/upload/gifts/png/gift_xdzj@2x.png"},{"giftId":13,"giftName":"人气女王","show_img":"gifts/png/gift_rqnw@2x.png","sum":"1321","img":"http://47.92.85.75/upload/gifts/png/gift_rqnw@2x.png"},{"giftId":22,"giftName":"金币爆炸","show_img":"gifts/png/gift_hbbz@2x.png","sum":"1320","img":"http://47.92.85.75/upload/gifts/png/gift_hbbz@2x.png"},{"giftId":1,"giftName":"么么哒","show_img":"gifts/png/gift_mmd@2x.png","sum":"1319","img":"http://47.92.85.75/upload/gifts/png/gift_mmd@2x.png"},{"giftId":2,"giftName":"呲水枪","show_img":"gifts/png/gift_sq@2x.png","sum":"1319","img":"http://47.92.85.75/upload/gifts/png/gift_sq@2x.png"},{"giftId":21,"giftName":"火箭","show_img":"gifts/png/gift_hj@2x.png","sum":"26","img":"http://47.92.85.75/upload/gifts/png/gift_hj@2x.png"},{"giftId":19,"giftName":"孔明灯","show_img":"gifts/png/gift_kmd@2x.png","sum":"15","img":"http://47.92.85.75/upload/gifts/png/gift_kmd@2x.png"},{"giftId":16,"giftName":"梦幻乐园","show_img":"gifts/png/gift_mhly@2x.png","sum":"13","img":"http://47.92.85.75/upload/gifts/png/gift_mhly@2x.png"},{"giftId":12,"giftName":"心动婚纱","show_img":"gifts/png/gift_xdhs@2x.png","sum":"12","img":"http://47.92.85.75/upload/gifts/png/gift_xdhs@2x.png"},{"giftId":18,"giftName":"干杯","show_img":"gifts/png/gift_gb@2x.png","sum":"11","img":"http://47.92.85.75/upload/gifts/png/gift_gb@2x.png"},{"giftId":11,"giftName":"流星雨","show_img":"gifts/png/gift_lxy@2x.png","sum":"10","img":"http://47.92.85.75/upload/gifts/png/gift_lxy@2x.png"},{"giftId":4,"giftName":"冰激凌","show_img":"gifts/png/gift_bql@2x.png","sum":"10","img":"http://47.92.85.75/upload/gifts/png/gift_bql@2x.png"},{"giftId":14,"giftName":"大红包","show_img":"gifts/png/gift_dhb@2x.png","sum":"10","img":"http://47.92.85.75/upload/gifts/png/gift_dhb@2x.png"},{"giftId":7,"giftName":"香水","show_img":"gifts/png/gift_xs@2x.png","sum":"7","img":"http://47.92.85.75/upload/gifts/png/gift_xs@2x.png"},{"giftId":17,"giftName":"烛光晚餐","show_img":"gifts/png/gift_zgwc@2x.png","sum":"6","img":"http://47.92.85.75/upload/gifts/png/gift_zgwc@2x.png"},{"giftId":6,"giftName":"水果拼盘","show_img":"gifts/png/gift_sgpp@2x.png","sum":"6","img":"http://47.92.85.75/upload/gifts/png/gift_sgpp@2x.png"},{"giftId":8,"giftName":"沙滩比基尼","show_img":"gifts/png/gift_stbjn@2x.png","sum":"6","img":"http://47.92.85.75/upload/gifts/png/gift_stbjn@2x.png"},{"giftId":3,"giftName":"小黄瓜","show_img":"gifts/png/gift_xhg@2x.png","sum":"5","img":"http://47.92.85.75/upload/gifts/png/gift_xhg@2x.png"},{"giftId":9,"giftName":"魅力头冠","show_img":"gifts/png/gift_mltg@2x.png","sum":"5","img":"http://47.92.85.75/upload/gifts/png/gift_mltg@2x.png"},{"giftId":15,"giftName":"超跑","show_img":"gifts/png/gift_cp@2x.png","sum":"4","img":"http://47.92.85.75/upload/gifts/png/gift_cp@2x.png"},{"giftId":5,"giftName":"心动手链","show_img":"gifts/png/gift_xdsl@2x.png","sum":"3","img":"http://47.92.85.75/upload/gifts/png/gift_xdsl@2x.png"},{"giftId":20,"giftName":"心动","show_img":"gifts/png/gift_xd@2x.png","sum":"2","img":"http://47.92.85.75/upload/gifts/png/gift_xd@2x.png"},{"giftId":24,"giftName":"金麦克","show_img":"gifts/png/gift_jmk@2x.png","sum":"1","img":"http://47.92.85.75/upload/gifts/png/gift_jmk@2x.png"}],"cplist":[{"id":26,"wares_id":1,"user_id":1860,"fromUid":1151828,"agreetime":1564592400,"cp_level":3,"user_nick":"小丸子","user_head":"http://47.92.85.75/upload//avatar/20190727/15642090646712.png","from_nick":"白羊","from_head":"http://test.miniyuyin.cn/upload//cover/20190803/15648038638044.jpg","bs_img":"http://47.92.85.75/upload/emoji/bs/shzx.png","days":35,"cp_type":1},{"cp_type":2,"days":"暂无CP"},{"cp_type":2,"days":"暂无CP"}]}
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
         * userInfo : {"id":1860,"headimgurl":"http://47.92.85.75/upload//avatar/20190727/15642090646712.png","nickname":"小丸子","sex":1,"birthday":"2019-01-01","constellation":"摩羯座","city":"月球","ry_uid":"1860","age":0,"fabu":3,"follows_num":2,"fans_num":0,"star_level":17,"gold_level":5,"vip_level":5,"hz_level":3,"is_follow":0}
         * glory : [{"type":4,"level":3,"name":"VIP3级","img":"http://47.92.85.75/upload/emoji/vip_ico/hz/huizhang_2.png"}]
         * roomInfo : {}
         * gifts : [{"giftId":10,"giftName":"心动钻戒","show_img":"gifts/png/gift_xdzj@2x.png","sum":"1337","img":"http://47.92.85.75/upload/gifts/png/gift_xdzj@2x.png"},{"giftId":13,"giftName":"人气女王","show_img":"gifts/png/gift_rqnw@2x.png","sum":"1321","img":"http://47.92.85.75/upload/gifts/png/gift_rqnw@2x.png"},{"giftId":22,"giftName":"金币爆炸","show_img":"gifts/png/gift_hbbz@2x.png","sum":"1320","img":"http://47.92.85.75/upload/gifts/png/gift_hbbz@2x.png"},{"giftId":1,"giftName":"么么哒","show_img":"gifts/png/gift_mmd@2x.png","sum":"1319","img":"http://47.92.85.75/upload/gifts/png/gift_mmd@2x.png"},{"giftId":2,"giftName":"呲水枪","show_img":"gifts/png/gift_sq@2x.png","sum":"1319","img":"http://47.92.85.75/upload/gifts/png/gift_sq@2x.png"},{"giftId":21,"giftName":"火箭","show_img":"gifts/png/gift_hj@2x.png","sum":"26","img":"http://47.92.85.75/upload/gifts/png/gift_hj@2x.png"},{"giftId":19,"giftName":"孔明灯","show_img":"gifts/png/gift_kmd@2x.png","sum":"15","img":"http://47.92.85.75/upload/gifts/png/gift_kmd@2x.png"},{"giftId":16,"giftName":"梦幻乐园","show_img":"gifts/png/gift_mhly@2x.png","sum":"13","img":"http://47.92.85.75/upload/gifts/png/gift_mhly@2x.png"},{"giftId":12,"giftName":"心动婚纱","show_img":"gifts/png/gift_xdhs@2x.png","sum":"12","img":"http://47.92.85.75/upload/gifts/png/gift_xdhs@2x.png"},{"giftId":18,"giftName":"干杯","show_img":"gifts/png/gift_gb@2x.png","sum":"11","img":"http://47.92.85.75/upload/gifts/png/gift_gb@2x.png"},{"giftId":11,"giftName":"流星雨","show_img":"gifts/png/gift_lxy@2x.png","sum":"10","img":"http://47.92.85.75/upload/gifts/png/gift_lxy@2x.png"},{"giftId":4,"giftName":"冰激凌","show_img":"gifts/png/gift_bql@2x.png","sum":"10","img":"http://47.92.85.75/upload/gifts/png/gift_bql@2x.png"},{"giftId":14,"giftName":"大红包","show_img":"gifts/png/gift_dhb@2x.png","sum":"10","img":"http://47.92.85.75/upload/gifts/png/gift_dhb@2x.png"},{"giftId":7,"giftName":"香水","show_img":"gifts/png/gift_xs@2x.png","sum":"7","img":"http://47.92.85.75/upload/gifts/png/gift_xs@2x.png"},{"giftId":17,"giftName":"烛光晚餐","show_img":"gifts/png/gift_zgwc@2x.png","sum":"6","img":"http://47.92.85.75/upload/gifts/png/gift_zgwc@2x.png"},{"giftId":6,"giftName":"水果拼盘","show_img":"gifts/png/gift_sgpp@2x.png","sum":"6","img":"http://47.92.85.75/upload/gifts/png/gift_sgpp@2x.png"},{"giftId":8,"giftName":"沙滩比基尼","show_img":"gifts/png/gift_stbjn@2x.png","sum":"6","img":"http://47.92.85.75/upload/gifts/png/gift_stbjn@2x.png"},{"giftId":3,"giftName":"小黄瓜","show_img":"gifts/png/gift_xhg@2x.png","sum":"5","img":"http://47.92.85.75/upload/gifts/png/gift_xhg@2x.png"},{"giftId":9,"giftName":"魅力头冠","show_img":"gifts/png/gift_mltg@2x.png","sum":"5","img":"http://47.92.85.75/upload/gifts/png/gift_mltg@2x.png"},{"giftId":15,"giftName":"超跑","show_img":"gifts/png/gift_cp@2x.png","sum":"4","img":"http://47.92.85.75/upload/gifts/png/gift_cp@2x.png"},{"giftId":5,"giftName":"心动手链","show_img":"gifts/png/gift_xdsl@2x.png","sum":"3","img":"http://47.92.85.75/upload/gifts/png/gift_xdsl@2x.png"},{"giftId":20,"giftName":"心动","show_img":"gifts/png/gift_xd@2x.png","sum":"2","img":"http://47.92.85.75/upload/gifts/png/gift_xd@2x.png"},{"giftId":24,"giftName":"金麦克","show_img":"gifts/png/gift_jmk@2x.png","sum":"1","img":"http://47.92.85.75/upload/gifts/png/gift_jmk@2x.png"}]
         * cplist : [{"id":26,"wares_id":1,"user_id":1860,"fromUid":1151828,"agreetime":1564592400,"cp_level":3,"user_nick":"小丸子","user_head":"http://47.92.85.75/upload//avatar/20190727/15642090646712.png","from_nick":"白羊","from_head":"http://test.miniyuyin.cn/upload//cover/20190803/15648038638044.jpg","bs_img":"http://47.92.85.75/upload/emoji/bs/shzx.png","days":35,"cp_type":1},{"cp_type":2,"days":"暂无CP"},{"cp_type":2,"days":"暂无CP"}]
         */

        private UserInfoBean userInfo;
        private RoomInfoBean roomInfo;
        private List<GloryBean> glory;
        private List<GiftsBean> gifts;
        private List<CplistBean> cplist;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public RoomInfoBean getRoomInfo() {
            return roomInfo;
        }

        public void setRoomInfo(RoomInfoBean roomInfo) {
            this.roomInfo = roomInfo;
        }

        public List<GloryBean> getGlory() {
            return glory;
        }

        public void setGlory(List<GloryBean> glory) {
            this.glory = glory;
        }

        public List<GiftsBean> getGifts() {
            return gifts;
        }

        public void setGifts(List<GiftsBean> gifts) {
            this.gifts = gifts;
        }

        public List<CplistBean> getCplist() {
            return cplist;
        }

        public void setCplist(List<CplistBean> cplist) {
            this.cplist = cplist;
        }

        public static class UserInfoBean {
            /**
             * id : 1766
             * headimgurl : http://47.92.85.75/upload//avatar/20190726/15641335799444.jpg
             * nickname : 把寂寞当晚餐
             * sex : 1
             * birthday : 1996-04-11
             * constellation : 白羊座
             * city : 火星
             * ry_uid : 1766
             * age : 23
             * fabu : 24
             * follows_num : 7
             * fans_num : 4
             * star_level : 12
             * gold_level : 5
             * vip_level : 5
             * hz_level : 3
             * is_follow : 0
             */

            private int id;
            private String headimgurl;
            private String nickname;
            private int sex;
            private String birthday;
            private String constellation;
            private String city;
            private String ry_uid;
            private int age;
            private int fabu;
            private int follows_num;
            private int fans_num;
            private int star_level;
            private int gold_level;
            private int vip_level;
            private int hz_level;
            private int is_follow;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getConstellation() {
                return constellation;
            }

            public void setConstellation(String constellation) {
                this.constellation = constellation;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getRy_uid() {
                return ry_uid;
            }

            public void setRy_uid(String ry_uid) {
                this.ry_uid = ry_uid;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getFabu() {
                return fabu;
            }

            public void setFabu(int fabu) {
                this.fabu = fabu;
            }

            public int getFollows_num() {
                return follows_num;
            }

            public void setFollows_num(int follows_num) {
                this.follows_num = follows_num;
            }

            public int getFans_num() {
                return fans_num;
            }

            public void setFans_num(int fans_num) {
                this.fans_num = fans_num;
            }

            public int getStar_level() {
                return star_level;
            }

            public void setStar_level(int star_level) {
                this.star_level = star_level;
            }

            public int getGold_level() {
                return gold_level;
            }

            public void setGold_level(int gold_level) {
                this.gold_level = gold_level;
            }

            public int getVip_level() {
                return vip_level;
            }

            public void setVip_level(int vip_level) {
                this.vip_level = vip_level;
            }

            public int getHz_level() {
                return hz_level;
            }

            public void setHz_level(int hz_level) {
                this.hz_level = hz_level;
            }

            public int getIs_follow() {
                return is_follow;
            }

            public void setIs_follow(int is_follow) {
                this.is_follow = is_follow;
            }
        }

        public static class RoomInfoBean {
            /**
             * uid : 113114
             * room_name : 我男神李现
             * hot : 9999
             * room_cover : http://47.92.85.75/upload/cover/cover/6.jpg
             */

            private int uid;
            private String room_name;
            private int hot;
            private String room_cover;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getRoom_name() {
                return room_name;
            }

            public void setRoom_name(String room_name) {
                this.room_name = room_name;
            }

            public int getHot() {
                return hot;
            }

            public void setHot(int hot) {
                this.hot = hot;
            }

            public String getRoom_cover() {
                return room_cover;
            }

            public void setRoom_cover(String room_cover) {
                this.room_cover = room_cover;
            }
        }

        public static class GloryBean {
            /**
             * type : 4
             * level : 3
             * name : VIP3级
             * img : http://47.92.85.75/upload/emoji/vip_ico/hz/huizhang_2.png
             */

            private int type;
            private int level;
            private String name;
            private String img;

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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }


        public static class GiftsBean implements Parcelable {
            /**
             * giftId : 5
             * giftName : 大哭
             * img : http://47.92.85.75/upload/gifts/png/b_sl@2x.png
             * sum : 1315
             */

            private int giftId;
            private String giftName;
            private String img;
            private String sum;

            public int getGiftId() {
                return giftId;
            }

            public void setGiftId(int giftId) {
                this.giftId = giftId;
            }

            public String getGiftName() {
                return giftName;
            }

            public void setGiftName(String giftName) {
                this.giftName = giftName;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getSum() {
                return sum;
            }

            public void setSum(String sum) {
                this.sum = sum;
            }

            protected GiftsBean(Parcel in) {
                this.giftId = in.readInt();
                this.giftName = in.readString();
                this.img = in.readString();
                this.sum = in.readString();
            }

            public static final Parcelable.Creator<MyPersonalCebterBean.DataBean.GiftsBean> CREATOR = new Parcelable.Creator<MyPersonalCebterBean.DataBean.GiftsBean>() {
                @Override
                public MyPersonalCebterBean.DataBean.GiftsBean createFromParcel(Parcel in) {
                    return new MyPersonalCebterBean.DataBean.GiftsBean(in);
                }

                @Override
                public MyPersonalCebterBean.DataBean.GiftsBean[] newArray(int size) {
                    return new MyPersonalCebterBean.DataBean.GiftsBean[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.giftId);
                dest.writeString(this.giftName);
                dest.writeString(this.img);
                dest.writeString(this.sum);
            }
        }

        public static class CplistBean implements Parcelable {
            /**
             * id : 26
             * wares_id : 1
             * user_id : 1860
             * fromUid : 1151828
             * agreetime : 1564592400
             * cp_level : 3
             * user_nick : 小丸子
             * user_head : http://47.92.85.75/upload//avatar/20190727/15642090646712.png
             * from_nick : 白羊
             * from_head : http://test.miniyuyin.cn/upload//cover/20190803/15648038638044.jpg
             * bs_img : http://47.92.85.75/upload/emoji/bs/shzx.png
             * days : 35
             * cp_type : 1
             */

            private int id;
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

            protected CplistBean(Parcel in) {
                this.id = in.readInt();
                this.wares_id = in.readInt();
                this.user_id = in.readInt();
                this.fromUid = in.readInt();
                this.agreetime = in.readInt();
                this.cp_level = in.readInt();
                this.user_nick = in.readString();
                this.user_head = in.readString();
                this.from_nick = in.readString();
                this.from_head = in.readString();
                this.bs_img = in.readString();
                this.days = in.readString();
                this.cp_type = in.readInt();
            }

            public static final Parcelable.Creator<MyPersonalCebterBean.DataBean.CplistBean> CREATOR = new Parcelable.Creator<MyPersonalCebterBean.DataBean.CplistBean>() {
                @Override
                public MyPersonalCebterBean.DataBean.CplistBean createFromParcel(Parcel in) {
                    return new MyPersonalCebterBean.DataBean.CplistBean(in);
                }

                @Override
                public MyPersonalCebterBean.DataBean.CplistBean[] newArray(int size) {
                    return new MyPersonalCebterBean.DataBean.CplistBean[size];
                }
            };

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeInt(wares_id);
                dest.writeInt(user_id);
                dest.writeInt(fromUid);
                dest.writeInt(agreetime);
                dest.writeInt(cp_level);
                dest.writeString(user_nick);
                dest.writeString(user_head);
                dest.writeString(from_nick);
                dest.writeString(from_head);
                dest.writeString(bs_img);
                dest.writeString(days);
                dest.writeInt(cp_type);
            }
        }
    }
}
