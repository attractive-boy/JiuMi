package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class JinSheng {

    /**
     * code : 1
     * message : 加入禁声单成功
     * data : [[{"id":1151726,"nickname":"孙先生.","headimgurl":"http://59.110.169.251/upload//avatar/20190613/25220_155229_4875.jpg"}],[{"id":1151723,"nickname":"北栀女孩","headimgurl":"http://59.110.169.251/upload//avatar/20190613/25220_155229_4875.jpg"}]]
     */

    private int code;
    private String message;
    private List<List<DataBean>> data;

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

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1151726
         * nickname : 孙先生.
         * headimgurl : http://59.110.169.251/upload//avatar/20190613/25220_155229_4875.jpg
         */

        private int id;
        private String nickname;
        private String headimgurl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }
    }
}
