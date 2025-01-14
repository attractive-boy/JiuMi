package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class Search {

    /**
     * code : 1
     * message : 请求成功!
     * data : {"user":[{"id":1151835,"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"搜索用户","sex":1,"is_follow":0}],"rooms":[{"room_name":"搜索房间","uid":1151834,"numid":"1151834","hot":1031,"room_cover":"http://47.92.85.75/upload/cover/20190712/1562913647.png","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"褚","sex":1}],"dynamics":[{"id":5,"audio_time":"","user_id":113114,"image":"http://47.92.85.75/upload//dynamic_image/9c685d32c46bfdaebfe2f35a10f0eff0.jpg, http://47.92.85.75/upload//dynamic_image/7ff12a001551f6454a368e9f27dd5d68.jpg","audio":"","video":"http://47.92.85.75/upload/video/affa627d5c2d18d67f109acb9eb92f31.mp4","content":"搜索动态","praise":104,"tags":"1","addtime":"2019-07-15 16:37:03","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"田中华(Mythe)","sex":1,"tags_str":"#风景","talk_num":2,"praise_num":4,"forward_num":0,"is_praise":0,"is_collect":0,"vip_level":0,"is_follow":0}]}
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
        private List<UserBean> user;
        private List<RoomsBean> rooms;
        private List<DynamicsBean> dynamics;

        public List<UserBean> getUser() {
            return user;
        }

        public void setUser(List<UserBean> user) {
            this.user = user;
        }

        public List<RoomsBean> getRooms() {
            return rooms;
        }

        public void setRooms(List<RoomsBean> rooms) {
            this.rooms = rooms;
        }

        public List<DynamicsBean> getDynamics() {
            return dynamics;
        }

        public void setDynamics(List<DynamicsBean> dynamics) {
            this.dynamics = dynamics;
        }

        public static class UserBean {
            /**
             * id : 1151835
             * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
             * nickname : 搜索用户
             * sex : 1
             * is_follow : 0
             */

            private int id;
            private String headimgurl;
            private String nickname;
            private int sex;
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

            public int getIs_follow() {
                return is_follow;
            }

            public void setIs_follow(int is_follow) {
                this.is_follow = is_follow;
            }
        }

        public static class RoomsBean {
            /**
             * room_name : 搜索房间
             * uid : 1151834
             * numid : 1151834
             * hot : 1031
             * room_cover : http://47.92.85.75/upload/cover/20190712/1562913647.png
             * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
             * nickname : 褚
             * sex : 1
             */

            private String room_name;
            private int uid;
            private String numid;
            private int hot;
            private String room_cover;
            private String headimgurl;
            private String nickname;
            private int sex;

            public String getRoom_name() {
                return room_name;
            }

            public void setRoom_name(String room_name) {
                this.room_name = room_name;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getNumid() {
                return numid;
            }

            public void setNumid(String numid) {
                this.numid = numid;
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
        }

        public static class DynamicsBean {
            /**
             * id : 5
             * audio_time :
             * user_id : 113114
             * image : http://47.92.85.75/upload//dynamic_image/9c685d32c46bfdaebfe2f35a10f0eff0.jpg, http://47.92.85.75/upload//dynamic_image/7ff12a001551f6454a368e9f27dd5d68.jpg
             * audio :
             * video : http://47.92.85.75/upload/video/affa627d5c2d18d67f109acb9eb92f31.mp4
             * content : 搜索动态
             * praise : 104
             * tags : 1
             * addtime : 2019-07-15 16:37:03
             * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
             * nickname : 田中华(Mythe)
             * sex : 1
             * tags_str : #风景
             * talk_num : 2
             * praise_num : 4
             * forward_num : 0
             * is_praise : 0
             * is_collect : 0
             * vip_level : 0
             * is_follow : 0
             */

            private int id;
            private String audio_time;
            private int user_id;
            private String image;
            private String audio;
            private String video;
            private String content;
            private int praise;
            private String tags;
            private String addtime;
            private String headimgurl;
            private String nickname;
            private int sex;
            private String tags_str;
            private int talk_num;
            private int praise_num;
            private int forward_num;
            private int is_praise;
            private int is_collect;
            private int vip_level;
            private int is_follow;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAudio_time() {
                return audio_time;
            }

            public void setAudio_time(String audio_time) {
                this.audio_time = audio_time;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getAudio() {
                return audio;
            }

            public void setAudio(String audio) {
                this.audio = audio;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getPraise() {
                return praise;
            }

            public void setPraise(int praise) {
                this.praise = praise;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
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

            public String getTags_str() {
                return tags_str;
            }

            public void setTags_str(String tags_str) {
                this.tags_str = tags_str;
            }

            public int getTalk_num() {
                return talk_num;
            }

            public void setTalk_num(int talk_num) {
                this.talk_num = talk_num;
            }

            public int getPraise_num() {
                return praise_num;
            }

            public void setPraise_num(int praise_num) {
                this.praise_num = praise_num;
            }

            public int getForward_num() {
                return forward_num;
            }

            public void setForward_num(int forward_num) {
                this.forward_num = forward_num;
            }

            public int getIs_praise() {
                return is_praise;
            }

            public void setIs_praise(int is_praise) {
                this.is_praise = is_praise;
            }

            public int getIs_collect() {
                return is_collect;
            }

            public void setIs_collect(int is_collect) {
                this.is_collect = is_collect;
            }

            public int getVip_level() {
                return vip_level;
            }

            public void setVip_level(int vip_level) {
                this.vip_level = vip_level;
            }

            public int getIs_follow() {
                return is_follow;
            }

            public void setIs_follow(int is_follow) {
                this.is_follow = is_follow;
            }
        }
    }
}
