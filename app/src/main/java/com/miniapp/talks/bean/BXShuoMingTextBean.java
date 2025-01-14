package com.miniapp.talks.bean;

public class BXShuoMingTextBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"remark":"<p>1、开宝箱需要优先购买钥匙，钥匙10米钻/把；<\/p><p>2、可以单次开锁宝箱，也可以10次、100次；<\/p><p>3、宝箱分为两种普通宝箱和守护宝箱，守护宝箱每天定时定点开启；<\/p><p>4、宝箱中的神秘礼物定期会更新，赠送和收到神秘礼物的双方若已经开通守护点数，则会提升两人的守护值；<\/p><p>5、宝箱中开到的礼物会自动加入背包列表，可以随时使用；<\/p><p>6、可以在中奖记录中查看最近一周的开奖记录；<\/p><p>7、如有问题，请截图保存并与mini官方客服联系；<\/p>"}
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
         * remark : <p>1、开宝箱需要优先购买钥匙，钥匙10米钻/把；</p><p>2、可以单次开锁宝箱，也可以10次、100次；</p><p>3、宝箱分为两种普通宝箱和守护宝箱，守护宝箱每天定时定点开启；</p><p>4、宝箱中的神秘礼物定期会更新，赠送和收到神秘礼物的双方若已经开通守护点数，则会提升两人的守护值；</p><p>5、宝箱中开到的礼物会自动加入背包列表，可以随时使用；</p><p>6、可以在中奖记录中查看最近一周的开奖记录；</p><p>7、如有问题，请截图保存并与mini官方客服联系；</p>
         */

        private String remark;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
