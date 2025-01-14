package com.miniapp.talks.bean;

import java.util.List;

public class AllCommentBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : [{"id":7,"pid":0,"user_id":1151834,"content":"评论","hf_uid":0,"praise":100,"created_at":"2019-06-13 15:24:23","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"褚","vip_level":0,"is_praise":0,"reply":""},{"id":8,"pid":0,"user_id":1766,"content":"123123123","hf_uid":0,"praise":26,"created_at":"2019-06-13 15:26:52","headimgurl":"http://47.92.85.75/upload//avatar/20190621/25220_145124_8959.jpg","nickname":"666","vip_level":4,"is_praise":0,"reply":""},{"id":9,"pid":7,"user_id":1151828,"content":"hahaha","hf_uid":0,"praise":45,"created_at":"2019-06-13 15:27:51","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"爱情患者","vip_level":6,"is_praise":0,"reply":null},{"id":10,"pid":0,"user_id":1151734,"content":"hahaha","hf_uid":0,"praise":7857,"created_at":"2019-06-13 15:27:58","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"盖世仙女","vip_level":0,"is_praise":0,"reply":""},{"id":11,"pid":10,"user_id":1151735,"content":"hahaha","hf_uid":1151734,"praise":415,"created_at":"2019-06-13 15:28:24","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"空空儿","vip_level":0,"is_praise":0,"reply":"盖世仙女"},{"id":12,"pid":0,"user_id":1151729,"content":"hahaha","hf_uid":0,"praise":152,"created_at":"2019-06-13 15:53:22","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"沈泽","vip_level":0,"is_praise":0,"reply":""},{"id":13,"pid":0,"user_id":1151724,"content":"llllllllllll","hf_uid":0,"praise":54,"created_at":"2019-06-19 09:32:36","headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"子辰","vip_level":0,"is_praise":0,"reply":""}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 7
         * pid : 0
         * user_id : 1151834
         * content : 评论
         * hf_uid : 0
         * praise : 100
         * created_at : 2019-06-13 15:24:23
         * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
         * nickname : 褚
         * vip_level : 0
         * is_praise : 0
         * reply :
         */

        private int id;
        private int pid;
        private int user_id;
        private String content;
        private int hf_uid;
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

        public int getHf_uid() {
            return hf_uid;
        }

        public void setHf_uid(int hf_uid) {
            this.hf_uid = hf_uid;
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
