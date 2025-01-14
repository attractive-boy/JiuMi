package com.miniapp.talks.bean;

import org.litepal.crud.LitePalSupport;


/**
 * 面向用户的音频信息
 */
public class LocalMusicInfo extends LitePalSupport{

    public String name;
    public String adminUser;
    public String songUrl;
    public boolean isSelect;//是否选中
    public boolean isStart;//是否播放
    public String size;//音乐大小
    public boolean isNet;//是否是网络音乐
}
