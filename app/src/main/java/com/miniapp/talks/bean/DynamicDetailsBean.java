package com.miniapp.talks.bean;

import java.util.List;

public class DynamicDetailsBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : {"details":[{"id":10,"user_id":1151828,"image":"http://47.92.85.75/upload//dynamic_image/1563434040.jpg","audio":"","video":"","content":"哈哈哈哈哈哈哈哈","praise":2157,"tags":"3,4,5","addtime":"2019-07-15 16:37:07","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"爱情患者","sex":1,"tags_str":"#旅游,#自拍,#运动","talk_num":1,"praise_num":0,"forward_num":0,"is_praise":0,"is_collect":1,"vip_level":1,"is_follow":0}],"hot":[{"id":14,"pid":0,"user_id":1766,"content":"赞","praise":0,"created_at":"2019-07-16 13:56:53","headimgurl":"http://47.92.85.75/upload//avatar/20190621/25220_145124_8959.jpg","nickname":"666","vip_level":0,"is_praise":1,"reply":""}],"comments":[{"id":14,"pid":0,"user_id":1766,"content":"赞","praise":0,"created_at":"2019-07-16 13:56:53","headimgurl":"http://47.92.85.75/upload//avatar/20190621/25220_145124_8959.jpg","nickname":"666","vip_level":0,"is_praise":1,"reply":""}]}
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
        private List<DetailsBean> details;
        private List<HotBean> hot;
        private List<CommentsBean> comments;

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public List<HotBean> getHot() {
            return hot;
        }

        public void setHot(List<HotBean> hot) {
            this.hot = hot;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class DetailsBean {
            /**
             * id : 10
             * user_id : 1151828
             * image : http://47.92.85.75/upload//dynamic_image/1563434040.jpg
             * audio :
             * video :
             * content : 哈哈哈哈哈哈哈哈
             * praise : 2157
             * tags : 3,4,5
             * addtime : 2019-07-15 16:37:07
             * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
             * nickname : 爱情患者
             * sex : 1
             * tags_str : #旅游,#自拍,#运动
             * talk_num : 1
             * praise_num : 0
             * forward_num : 0
             * is_praise : 0
             * is_collect : 1
             * vip_level : 1
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

        public static class HotBean {
            /**
             * id : 14
             * pid : 0
             * user_id : 1766
             * content : 赞
             * praise : 0
             * created_at : 2019-07-16 13:56:53
             * headimgurl : http://47.92.85.75/upload//avatar/20190621/25220_145124_8959.jpg
             * nickname : 666
             * vip_level : 0
             * is_praise : 1
             * reply :
             */

            private int id;
            private int pid;
            private int user_id;
            private String content;
            private int praise;
            private String created_at;
            private String headimgurl;
            private String nickname;
            private int vip_level;
            private int is_praise;
            private String reply;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
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

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
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

            public int getVip_level() {
                return vip_level;
            }

            public void setVip_level(int vip_level) {
                this.vip_level = vip_level;
            }

            public int getIs_praise() {
                return is_praise;
            }

            public void setIs_praise(int is_praise) {
                this.is_praise = is_praise;
            }

            public String getReply() {
                return reply;
            }

            public void setReply(String reply) {
                this.reply = reply;
            }
        }

        public static class CommentsBean {
            /**
             * id : 14
             * pid : 0
             * user_id : 1766
             * content : 赞
             * praise : 0
             * created_at : 2019-07-16 13:56:53
             * headimgurl : http://47.92.85.75/upload//avatar/20190621/25220_145124_8959.jpg
             * nickname : 666
             * vip_level : 0
             * is_praise : 1
             * reply :
             */

            private int id;
            private int pid;
            private int user_id;
            private String content;
            private int praise;
            private String created_at;
            private String headimgurl;
            private String nickname;
            private int vip_level;
            private int is_praise;
            private String reply;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
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

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
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

            public int getVip_level() {
                return vip_level;
            }

            public void setVip_level(int vip_level) {
                this.vip_level = vip_level;
            }

            public int getIs_praise() {
                return is_praise;
            }

            public void setIs_praise(int is_praise) {
                this.is_praise = is_praise;
            }

            public String getReply() {
                return reply;
            }

            public void setReply(String reply) {
                this.reply = reply;
            }
        }
    }
}
