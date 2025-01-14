package com.miniapp.talks.bean;

/**
 * 作者:sgm
 * 类描述:
 */
public class FirstEvent {
    /**
     *
     */
    private String mMsg;//消息内容
    private String tag; //消息类型
    private EnterRoom enterRoom;

    private PushBean pushBean;

    public PushBean getPushBean() {
        return pushBean;
    }

    public FirstEvent(PushBean pushBean, String tag) {
        this.pushBean = pushBean;
        this.tag = tag;
    }

    public EnterRoom getEnterRoom() {
        return enterRoom;
    }

    public FirstEvent(String mMsg, String tag) {
        super();
        this.mMsg = mMsg;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public FirstEvent(String msg) {
        mMsg = msg;
    }

    public String getMsg() {
        return mMsg;
    }


    public FirstEvent(String mMsg, String tag, EnterRoom enterRoom) {
        super();
        this.mMsg = mMsg;
        this.tag = tag;
        this.enterRoom = enterRoom;
    }

    private MessageBean messageBean;

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public FirstEvent(MessageBean messageBean, String tag) {
        super();
        this.messageBean = messageBean;
        this.tag = tag;
    }

    private MyPackBean.DataBean dataBean;

    public MyPackBean.DataBean getDataBean() {
        return dataBean;
    }

    public FirstEvent(MyPackBean.DataBean dataBean, String tag) {
        super();
        this.dataBean = dataBean;
        this.tag = tag;
    }
}
