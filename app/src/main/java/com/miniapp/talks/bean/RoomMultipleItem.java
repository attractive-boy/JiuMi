package com.miniapp.talks.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class RoomMultipleItem implements MultiItemEntity {

    public static final int TITLE_MIC_UP = 1;
    public static final int MIC_UP = 2;
    public static final int TITLE_MIC_DOWN = 3;
    public static final int MIC_DOWN = 4;

    private int itemType;

    private MicUserBean micUserBean;

    public RoomMultipleItem(int itemType, MicUserBean data) {
        this.itemType = itemType;
        this.micUserBean = data;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public MicUserBean getData(){
        return micUserBean;
    }
}
