package com.miniapp.talks.bean;

import java.util.List;

public class MyPackBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":17,"user_id":1860,"get_type":1,"type":1,"target_id":1,"num":2,"expire":0,"addtime":1567145677,"name":"守护之心","show_img":"http://47.92.85.75/upload/emoji/bs/shzx.png","title":"","is_dress":0},{"id":18,"user_id":1860,"get_type":1,"type":1,"target_id":2,"num":3,"expire":0,"addtime":1567145677,"name":"守护之钻","show_img":"","title":"","is_dress":0}]
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
         * id : 17
         * user_id : 1860
         * get_type : 1
         * type : 1
         * target_id : 1
         * num : 2
         * expire : 0
         * addtime : 1567145677
         * name : 守护之心
         * show_img : http://47.92.85.75/upload/emoji/bs/shzx.png
         * title :
         * is_dress : 0
         */

        private int id;
        private int user_id;
        private int get_type;
        private int type;
        private int target_id;
        private int num;
        private int expire;
        private int addtime;
        private String name;
        private String show_img;
        private String title;
        private int is_dress;
        private boolean isSelect;
        private String color;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
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

        public int getGet_type() {
            return get_type;
        }

        public void setGet_type(int get_type) {
            this.get_type = get_type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getTarget_id() {
            return target_id;
        }

        public void setTarget_id(int target_id) {
            this.target_id = target_id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getExpire() {
            return expire;
        }

        public void setExpire(int expire) {
            this.expire = expire;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShow_img() {
            return show_img;
        }

        public void setShow_img(String show_img) {
            this.show_img = show_img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIs_dress() {
            return is_dress;
        }

        public void setIs_dress(int is_dress) {
            this.is_dress = is_dress;
        }
    }
}
