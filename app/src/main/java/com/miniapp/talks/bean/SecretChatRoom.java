package com.miniapp.talks.bean;

import java.util.List;

/**
 * Created by yxb on 2019/6/5.
 */
public class SecretChatRoom {


    /**
     * code : 1
     * message : 获取成功
     * data : [{"roomName":"过客，笑谈人生的房间","uid":1141415,"roomCover":"http://thirdwx.qlogo.cn/mmopen/vi_32/vIL1Sy6Zw96RHxbzDKlySibNoCq2uxcJCm9FP6aXFnnicyhIhGgK8icKEHchWXEPssbQYGfMB9NpjUzkqyiceGG7bg/132","roomType":"5","nickname":"过客，笑谈人生"},{"roomName":"地先生的房间","uid":1141416,"roomCover":"http://thirdwx.qlogo.cn/mmopen/vi_32/DvgtCchQSEN6bSUfZfsHjoXecrAkBzLI7palPWB4IuSCXIcgTvqVa78Z60oQP5VemcHJzuWtkKgax525eopdhg/132","roomType":"1","nickname":null},{"roomName":"娅娅的房间","uid":1141419,"roomCover":"http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEKibibCDic2qviah114Y1FpBmv44pEystG8LJI8su1wpwk6CRWtNQWxibUc0xKHSibMFIU1icqAWL4B7fBGw/132","roomType":"1","nickname":"娅娅"},{"roomName":"晨静的房间","uid":1141420,"roomCover":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIcbGt5TmvotOSntHVaTm9Fr5sOeq0moTTYlga0L7xJCkI5HYQch82ibPgJEuZpkU5WGLXlZMzXTaw/132","roomType":"5","nickname":null}]
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
         * roomName : 过客，笑谈人生的房间
         * uid : 1141415
         * roomCover : http://thirdwx.qlogo.cn/mmopen/vi_32/vIL1Sy6Zw96RHxbzDKlySibNoCq2uxcJCm9FP6aXFnnicyhIhGgK8icKEHchWXEPssbQYGfMB9NpjUzkqyiceGG7bg/132
         * roomType : 5
         * nickname : 过客，笑谈人生
         */

        private String roomName;
        private int uid;
        private String roomCover;
        private String roomType;
        private String nickname;

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getRoomCover() {
            return roomCover;
        }

        public void setRoomCover(String roomCover) {
            this.roomCover = roomCover;
        }

        public String getRoomType() {
            return roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
