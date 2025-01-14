package com.miniapp.talks.bean;

import java.util.List;

public class MessageYoBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : {"sign":"2","coll":0,"share":0,"comment":[{"id":156,"b_dynamic_id":211,"pid":0,"hf_uid":0,"user_id":1151848,"content":"好快乐快乐","praise":0,"is_read":1,"created_at":"2019-08-15 19:42:52","updated_at":"2019-08-15 19:42:52","is_reply":0,"headimgurl":"http://47.92.85.75/upload//avatar/20190629/25220_100823_8730.jpg","nickname":"L.Y","image":"","audio":"http://47.92.85.75/upload//music/20190815/15658649406014.mp3","video":"","audio_time":"04s"},{"id":142,"b_dynamic_id":106,"pid":132,"hf_uid":1151892,"user_id":1151857,"content":"回复了","praise":0,"is_read":1,"created_at":"2019-08-14 11:42:26","updated_at":"2019-08-14 11:42:26","is_reply":1,"headimgurl":"http://47.92.85.75/upload//avatar/20190810/15654286255721.png","nickname":"向佳军的佳佳","image":"http://47.92.85.75/upload//dynamic_image/20190810/15654014621476.jpg","audio":"","video":"","audio_time":""},{"id":137,"b_dynamic_id":106,"pid":132,"hf_uid":1151892,"user_id":1151857,"content":"回复你一下","praise":0,"is_read":1,"created_at":"2019-08-14 11:24:17","updated_at":"2019-08-14 11:24:17","is_reply":1,"headimgurl":"http://47.92.85.75/upload//avatar/20190810/15654286255721.png","nickname":"向佳军的佳佳","image":"http://47.92.85.75/upload//dynamic_image/20190810/15654014621476.jpg","audio":"","video":"","audio_time":""},{"id":135,"b_dynamic_id":188,"pid":0,"hf_uid":0,"user_id":1151892,"content":"哈哈","praise":0,"is_read":1,"created_at":"2019-08-13 19:02:55","updated_at":"2019-08-13 19:02:55","is_reply":0,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIiatbibYU9p7aVuib12ExXXXModc4XUOb4DDzOGXaLHfXOKiac1mDposrbNLNEKXiabW3LHMLGxiaRCibBA/132","nickname":".WTH.","image":"","audio":"","video":"","audio_time":""},{"id":133,"b_dynamic_id":106,"pid":132,"hf_uid":1151892,"user_id":1151846,"content":"ok","praise":0,"is_read":1,"created_at":"2019-08-13 16:27:14","updated_at":"2019-08-13 16:27:14","is_reply":1,"headimgurl":"http://47.92.85.75/upload//avatar/20190621/25220_145124_8959.jpg","nickname":"橙汁","image":"http://47.92.85.75/upload//dynamic_image/20190810/15654014621476.jpg","audio":"","video":"","audio_time":""}]}
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
         * sign : 2
         * coll : 0
         * share : 0
         * comment : [{"id":156,"b_dynamic_id":211,"pid":0,"hf_uid":0,"user_id":1151848,"content":"好快乐快乐","praise":0,"is_read":1,"created_at":"2019-08-15 19:42:52","updated_at":"2019-08-15 19:42:52","is_reply":0,"headimgurl":"http://47.92.85.75/upload//avatar/20190629/25220_100823_8730.jpg","nickname":"L.Y","image":"","audio":"http://47.92.85.75/upload//music/20190815/15658649406014.mp3","video":"","audio_time":"04s"},{"id":142,"b_dynamic_id":106,"pid":132,"hf_uid":1151892,"user_id":1151857,"content":"回复了","praise":0,"is_read":1,"created_at":"2019-08-14 11:42:26","updated_at":"2019-08-14 11:42:26","is_reply":1,"headimgurl":"http://47.92.85.75/upload//avatar/20190810/15654286255721.png","nickname":"向佳军的佳佳","image":"http://47.92.85.75/upload//dynamic_image/20190810/15654014621476.jpg","audio":"","video":"","audio_time":""},{"id":137,"b_dynamic_id":106,"pid":132,"hf_uid":1151892,"user_id":1151857,"content":"回复你一下","praise":0,"is_read":1,"created_at":"2019-08-14 11:24:17","updated_at":"2019-08-14 11:24:17","is_reply":1,"headimgurl":"http://47.92.85.75/upload//avatar/20190810/15654286255721.png","nickname":"向佳军的佳佳","image":"http://47.92.85.75/upload//dynamic_image/20190810/15654014621476.jpg","audio":"","video":"","audio_time":""},{"id":135,"b_dynamic_id":188,"pid":0,"hf_uid":0,"user_id":1151892,"content":"哈哈","praise":0,"is_read":1,"created_at":"2019-08-13 19:02:55","updated_at":"2019-08-13 19:02:55","is_reply":0,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIiatbibYU9p7aVuib12ExXXXModc4XUOb4DDzOGXaLHfXOKiac1mDposrbNLNEKXiabW3LHMLGxiaRCibBA/132","nickname":".WTH.","image":"","audio":"","video":"","audio_time":""},{"id":133,"b_dynamic_id":106,"pid":132,"hf_uid":1151892,"user_id":1151846,"content":"ok","praise":0,"is_read":1,"created_at":"2019-08-13 16:27:14","updated_at":"2019-08-13 16:27:14","is_reply":1,"headimgurl":"http://47.92.85.75/upload//avatar/20190621/25220_145124_8959.jpg","nickname":"橙汁","image":"http://47.92.85.75/upload//dynamic_image/20190810/15654014621476.jpg","audio":"","video":"","audio_time":""}]
         */

        private String sign;
        private int coll;
        private int share;
        private List<CommentBean> comment;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getColl() {
            return coll;
        }

        public void setColl(int coll) {
            this.coll = coll;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public static class CommentBean {
            /**
             * id : 156
             * b_dynamic_id : 211
             * pid : 0
             * hf_uid : 0
             * user_id : 1151848
             * content : 好快乐快乐
             * praise : 0
             * is_read : 1
             * created_at : 2019-08-15 19:42:52
             * updated_at : 2019-08-15 19:42:52
             * is_reply : 0
             * headimgurl : http://47.92.85.75/upload//avatar/20190629/25220_100823_8730.jpg
             * nickname : L.Y
             * image :
             * audio : http://47.92.85.75/upload//music/20190815/15658649406014.mp3
             * video :
             * audio_time : 04s
             */

            private int id;
            private int b_dynamic_id;
            private int pid;
            private int hf_uid;
            private int user_id;
            private String content;
            private int praise;
            private int is_read;
            private String created_at;
            private String updated_at;
            private int is_reply;
            private String headimgurl;
            private String nickname;
            private String image;
            private String audio;
            private String video;
            private String audio_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getB_dynamic_id() {
                return b_dynamic_id;
            }

            public void setB_dynamic_id(int b_dynamic_id) {
                this.b_dynamic_id = b_dynamic_id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getHf_uid() {
                return hf_uid;
            }

            public void setHf_uid(int hf_uid) {
                this.hf_uid = hf_uid;
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

            public int getIs_read() {
                return is_read;
            }

            public void setIs_read(int is_read) {
                this.is_read = is_read;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public int getIs_reply() {
                return is_reply;
            }

            public void setIs_reply(int is_reply) {
                this.is_reply = is_reply;
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

            public String getAudio_time() {
                return audio_time;
            }

            public void setAudio_time(String audio_time) {
                this.audio_time = audio_time;
            }
        }
    }
}
