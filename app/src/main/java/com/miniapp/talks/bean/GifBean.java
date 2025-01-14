package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class GifBean {


    /**
     * code : 1
     * message : 请求成功!
     * data : [{"id":28,"pid":5,"name":"石头","emoji":"http://47.92.85.75/upload//emoji/gif/505.gif","t_length":"2.00","sort":0,"enable":1,"addtime":1563003157,"is_answer":2}]
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
         * id : 28
         * pid : 5
         * name : 石头
         * emoji : http://47.92.85.75/upload//emoji/gif/505.gif
         * t_length : 2.00
         * sort : 0
         * enable : 1
         * addtime : 1563003157
         * is_answer : 2
         */

        private int id;
        private int pid;
        private String name;
        private String emoji;
        private String t_length;
        private int sort;
        private int enable;
        private int addtime;
        private String is_answer;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmoji() {
            return emoji;
        }

        public void setEmoji(String emoji) {
            this.emoji = emoji;
        }

        public String getT_length() {
            return t_length;
        }

        public void setT_length(String t_length) {
            this.t_length = t_length;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public String getIs_answer() {
            return is_answer;
        }

        public void setIs_answer(String is_answer) {
            this.is_answer = is_answer;
        }
    }
}
