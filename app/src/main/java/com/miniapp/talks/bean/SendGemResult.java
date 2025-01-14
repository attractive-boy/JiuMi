package com.miniapp.talks.bean;

import java.util.List;

public class SendGemResult {


    /**
     * code : 1
     * message : 请求成功
     * data : [{"is_first":1,"userId":1151828,"nickname":"白羊","headimgurl":"http://test.miniyuyin.cn/upload//cover/20190803/15648038638044.jpg"},{"is_first":1,"userId":1151854,"nickname":"高冷飞很假哈的吧","headimgurl":"http://47.92.85.75/upload//cover/20190810/15654022455481.jpg"},{"is_first":1,"userId":1151871,"nickname":"飞飞","headimgurl":"http://47.92.85.75/upload//avatar/20190809/15653325932113.jpg"},{"is_first":1,"userId":1151873,"nickname":"飛飛","headimgurl":"http://test.miniyuyin.cn/upload//cover/20190809/15653366471076.jpg"},{"is_first":1,"userId":1151878,"nickname":"stoner","headimgurl":"https://thirdqq.qlogo.cn/g?b=oidb&k=TmBwI1LzwGbateO5BGwDFg&s=100&t=1483374897"},{"is_first":1,"userId":1151889,"nickname":"1.","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/4WFBCHqe458OREvojs6fTpl3ibdNCKJ2uA0tGQ3r4XZqJEoLA7AnhKYKtaxbzJsZWumvJRWuT5THvU13rEqtcyQ/132"},{"is_first":1,"userId":1766,"nickname":"把寂寞当晚餐","headimgurl":"http://47.92.85.75/upload//avatar/20190726/15641335799444.jpg"}]
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
         * is_first : 1
         * userId : 1151828
         * nickname : 白羊
         * headimgurl : http://test.miniyuyin.cn/upload//cover/20190803/15648038638044.jpg
         */

        private int is_first;
        private String userId;
        private String nickname;
        private String headimgurl;
        private String nick_color;

        public int getIs_first() {
            return is_first;
        }

        public void setIs_first(int is_first) {
            this.is_first = is_first;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

        public String getNick_color() {
            return nick_color;
        }

        public void setNick_color(String nick_color) {
            this.nick_color = nick_color;
        }
    }
}
