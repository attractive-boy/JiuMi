package com.miniapp.talks.bean;

import java.util.List;

public class AgreementBean {

    /**
     * code : 1
     * message : 请求成功!
     * data : [{"id":3,"type":3,"name":"《用户协议》","url":"http://www.baidu.com","addtime":1564191157},{"id":4,"type":3,"name":"《用户规范协议》","url":"http://www.baidu.com","addtime":1564191157},{"id":5,"type":3,"name":"《隐私协议》","url":"http://www.baidu.com","addtime":1564191157},{"id":6,"type":3,"name":"《充值协议》","url":"http://www.baidu.com","addtime":1564191157}]
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
         * id : 3
         * type : 3
         * name : 《用户协议》
         * url : http://www.baidu.com
         * addtime : 1564191157
         */

        private int id;
        private int type;
        private String name;
        private String url;
        private int addtime;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }
    }
}
