package com.miniapp.talks.bean;


public class MessageEvent {

    private StateMessage stateMessage;

    private Object object;

    public StateMessage getStateMessage() {
        return stateMessage;
    }

    public void setStateMessage(StateMessage stateMessage) {
        this.stateMessage = stateMessage;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
