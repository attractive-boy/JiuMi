package com.miniapp.talks.bean;

import android.os.SystemClock;

import java.util.List;

public class TopicDynamicBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : {"img":"http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg","talk_num":1,"read_num":1276,"dynamics":[{"id":5,"user_id":113114,"image":"http://47.92.85.75/upload//dynamic_image/9c685d32c46bfdaebfe2f35a10f0eff0.jpg,http://47.92.85.75/upload//dynamic_image/7ff12a001551f6454a368e9f27dd5d68.jpg","audio":"","video":"http://47.92.85.75/upload/video/123.mp4","content":"哈哈哈哈","praise":102,"is_top":1,"tags":"1,2","addtime":"2019-07-15 16:37:03","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"田中华(Mythe)","sex":1,"tags_str":"#风景,#美食","talk_num":0,"praise_num":2,"forward_num":0,"is_praise":1,"is_collect":0,"vip_level":1,"is_follow":0},{"id":13,"user_id":1151834,"image":"http://47.92.85.75/upload//dynamic_image/20190713/15630104974795.png,http://47.92.85.75/upload//dynamic_image/20190713/15630104974147.jpg","audio":"","video":"","content":"我的动态该","praise":51,"is_top":2,"tags":"1","addtime":"2019-07-15 16:37:10","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"褚","sex":1,"tags_str":"#风景","talk_num":1,"praise_num":1,"forward_num":1,"is_praise":1,"is_collect":0,"vip_level":2,"is_follow":1}]}
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
         * img : http://47.92.85.75/upload/topic_img/ce49db6cb59a8c907a6fce09711acb73.jpg
         * talk_num : 1
         * read_num : 1276
         * dynamics : [{"id":5,"user_id":113114,"image":"http://47.92.85.75/upload//dynamic_image/9c685d32c46bfdaebfe2f35a10f0eff0.jpg,http://47.92.85.75/upload//dynamic_image/7ff12a001551f6454a368e9f27dd5d68.jpg","audio":"","video":"http://47.92.85.75/upload/video/123.mp4","content":"哈哈哈哈","praise":102,"is_top":1,"tags":"1,2","addtime":"2019-07-15 16:37:03","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"田中华(Mythe)","sex":1,"tags_str":"#风景,#美食","talk_num":0,"praise_num":2,"forward_num":0,"is_praise":1,"is_collect":0,"vip_level":1,"is_follow":0},{"id":13,"user_id":1151834,"image":"http://47.92.85.75/upload//dynamic_image/20190713/15630104974795.png,http://47.92.85.75/upload//dynamic_image/20190713/15630104974147.jpg","audio":"","video":"","content":"我的动态该","praise":51,"is_top":2,"tags":"1","addtime":"2019-07-15 16:37:10","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"褚","sex":1,"tags_str":"#风景","talk_num":1,"praise_num":1,"forward_num":1,"is_praise":1,"is_collect":0,"vip_level":2,"is_follow":1}]
         */

        private String img;
        private int talk_num;
        private int read_num;
        private List<DynamicsBean> dynamics;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getTalk_num() {
            return talk_num;
        }

        public void setTalk_num(int talk_num) {
            this.talk_num = talk_num;
        }

        public int getRead_num() {
            return read_num;
        }

        public void setRead_num(int read_num) {
            this.read_num = read_num;
        }

        public List<DynamicsBean> getDynamics() {
            return dynamics;
        }

        public void setDynamics(List<DynamicsBean> dynamics) {
            this.dynamics = dynamics;
        }

        public static class DynamicsBean {
            /**
             * id : 5
             * user_id : 113114
             * image : http://47.92.85.75/upload//dynamic_image/9c685d32c46bfdaebfe2f35a10f0eff0.jpg,http://47.92.85.75/upload//dynamic_image/7ff12a001551f6454a368e9f27dd5d68.jpg
             * audio :
             * video : http://47.92.85.75/upload/video/123.mp4
             * content : 哈哈哈哈
             * praise : 102
             * is_top : 1
             * tags : 1,2
             * addtime : 2019-07-15 16:37:03
             * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
             * nickname : 田中华(Mythe)
             * sex : 1
             * tags_str : #风景,#美食
             * talk_num : 0
             * praise_num : 2
             * forward_num : 0
             * is_praise : 1
             * is_collect : 0
             * vip_level : 1
             * is_follow : 0
             */

            private int id;
            private int user_id;
            private String audio_time;
            private String image;
            private String audio;
            private String video;
            private String content;
            private int praise;
            private int is_top;
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
            private boolean play;
            private String currentTime;//当前播放倒计时时间

            public String getCurrentTime() {
                return currentTime;
            }

            public void setCurrentTime(String currentTime) {
                this.currentTime = currentTime;
            }
            public boolean isPlay() {
                return play;
            }

            public void setPlay(boolean play) {
                this.play = play;
            }

            public String getAudio_time() {
                return audio_time;
            }

            public void setAudio_time(String audio_time) {
                this.audio_time = audio_time;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getIs_top() {
                return is_top;
            }

            public void setIs_top(int is_top) {
                this.is_top = is_top;
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
