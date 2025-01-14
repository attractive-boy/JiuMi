package com.miniapp.talks.bean;

/**
 * 作者: sgm
 */
public class WxEndBean {

    /**
     * errCode : 0
     * prepayId : wx051511283947998e1564a75d0710032098
     * returnKey :
     * type : 5
     */

    private String errCode;
    private String prepayId;
    private String returnKey;
    private int type;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getReturnKey() {
        return returnKey;
    }

    public void setReturnKey(String returnKey) {
        this.returnKey = returnKey;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
