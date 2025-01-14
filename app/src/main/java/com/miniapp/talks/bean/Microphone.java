package com.miniapp.talks.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class Microphone {


    /**
     * code : 1
     * data : {"microphone":[{"shut_sound":1,"status":3},{"shut_sound":1,"status":3},{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":1151835,"is_sound":1,"nickname":"手机用户92813950","sex":1,"shut_sound":1,"status":2,"user_id":1151835},{"shut_sound":1,"status":3},{"shut_sound":1,"status":3},{"shut_sound":1,"status":3},{"shut_sound":1,"status":3},{"shut_sound":1,"status":3}],"user_id":""}
     * message : 获取成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * microphone : [{"shut_sound":1,"status":3},{"shut_sound":1,"status":3},{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":1151835,"is_sound":1,"nickname":"手机用户92813950","sex":1,"shut_sound":1,"status":2,"user_id":1151835},{"shut_sound":1,"status":3},{"shut_sound":1,"status":3},{"shut_sound":1,"status":3},{"shut_sound":1,"status":3},{"shut_sound":1,"status":3}]
         * user_id :
         */

        private String user_id;
        private List<MicrophoneBean> microphone;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public List<MicrophoneBean> getMicrophone() {
            return microphone;
        }

        public void setMicrophone(List<MicrophoneBean> microphone) {
            this.microphone = microphone;
        }

        public static class MicrophoneBean {
            public boolean isSelect = false;
            public int indexl;
            /**
             * shut_sound : 1
             * status : 3
             * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
             * id : 1151835
             * is_sound : 1
             * nickname : 手机用户92813950
             * sex : 1
             * user_id : 1151835
             */

            private int shut_sound;
            private int status;
            private String headimgurl;
            private int id;
            private int is_sound;
            private String nickname;
            private int sex;
            private String user_id = "";
            private String txk;//头像框
            private String mic_color;//麦上光圈颜色
            private String price = "";//数值
            private String is_play = "";//是否开启了数值玩法 1 开启了 0 关闭了
            private String is_master = "";//是否房主 1 是 0 不是
            private boolean is_zhuchi = false; //是否是主持
            private String remainTime; //麦位上的倒计时
            private int ml;

            public int getMl() {
                return ml;
            }

            public void setMl(int ml) {
                this.ml = ml;
            }

            public String getRemainTime() {
                return remainTime;
            }

            public void setRemainTime(String remainTime) {
                this.remainTime = remainTime;
            }

            public boolean isIs_zhuchi() {
                return is_zhuchi;
            }

            public void setIs_zhuchi(boolean is_zhuchi) {
                this.is_zhuchi = is_zhuchi;
            }

            public int getShut_sound() {
                return shut_sound;
            }

            public void setShut_sound(int shut_sound) {
                this.shut_sound = shut_sound;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIs_sound() {
                return is_sound;
            }

            public void setIs_sound(int is_sound) {
                this.is_sound = is_sound;
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

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getTxk() {
                return txk;
            }

            public void setTxk(String txk) {
                this.txk = txk;
            }

            public String getMic_color() {
                return mic_color;
            }

            public void setMic_color(String mic_color) {
                this.mic_color = mic_color;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getIs_play() {
                return is_play;
            }

            public void setIs_play(String is_play) {
                this.is_play = is_play;
            }

            public String getIs_master() {
                return is_master;
            }

            public void setIs_master(String is_master) {
                this.is_master = is_master;
            }

            public int getIndexl() {
                return indexl;
            }

            public void setIndexl(int indexl) {
                this.indexl = indexl;
            }
        }
    }
}
