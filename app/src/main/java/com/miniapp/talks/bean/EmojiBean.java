package com.miniapp.talks.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class EmojiBean implements Serializable {

    /**
     * code : 1
     * message : 请求成功!
     * data : [{"id":1,"name":"白眼","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/001.png"},{"id":2,"name":"爆灯","t_length":"1.30","emoji":"http://47.92.85.75/upload//emoji/png/002.png"},{"id":3,"name":"闭嘴","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/003.png"},{"id":4,"name":"不开心","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/004.png"},{"id":5,"name":"猜拳","t_length":"2.00","emoji":"http://47.92.85.75/upload//emoji/png/005.png"},{"id":6,"name":"抽号机","t_length":"3.10","emoji":"http://47.92.85.75/upload//emoji/png/006.png"},{"id":7,"name":"大哭","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/007.png"},{"id":8,"name":"倒计时","t_length":"5.00","emoji":"http://47.92.85.75/upload//emoji/png/008.png"},{"id":9,"name":"尴尬","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/009.png"},{"id":10,"name":"惊恐","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/010.png"},{"id":11,"name":"举手","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/011.png"},{"id":12,"name":"开心","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/012.png"},{"id":13,"name":"快哭了","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/013.png"},{"id":14,"name":"亲亲","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/014.png"},{"id":15,"name":"魔鬼","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/015.png"},{"id":16,"name":"抛硬币","t_length":"2.00","emoji":"http://47.92.85.75/upload//emoji/png/016.png"},{"id":17,"name":"生气","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/017.png"},{"id":18,"name":"睡觉","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/018.png"},{"id":19,"name":"思考","t_length":"1.00","emoji":"http://47.92.85.75/upload//emoji/png/019.png"},{"id":20,"name":"骰子","t_length":"0.00","emoji":"http://47.92.85.75/upload//emoji/png/020.png"},{"id":21,"name":"吐舌","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/021.png"},{"id":22,"name":"心碎","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/022.png"},{"id":23,"name":"猪头","t_length":"0.80","emoji":"http://47.92.85.75/upload//emoji/png/023.png"}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * name : 白眼
         * t_length : 0.80
         * emoji : http://47.92.85.75/upload//emoji/png/001.png
         */

        private int id;
        private String name;
        private String t_length;
        private String emoji;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getT_length() {
            return t_length;
        }

        public void setT_length(String t_length) {
            this.t_length = t_length;
        }

        public String getEmoji() {
            return emoji;
        }

        public void setEmoji(String emoji) {
            this.emoji = emoji;
        }
    }
}
