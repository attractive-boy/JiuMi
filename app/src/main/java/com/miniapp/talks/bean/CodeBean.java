package com.miniapp.talks.bean;

/**
 * 作者:sgm
 * 描述:
 */
public class CodeBean {

    /**
     * Message : 触发分钟级流控Permits:1
     * RequestId : 0205152C-91A8-4F77-A819-A1D6BAEF 9299
     * Code : isv.BUSINESS_LIMIT_CONTROL
     */

    private String Message;
    private String RequestId;
    private String Code;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String RequestId) {
        this.RequestId = RequestId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }
}
