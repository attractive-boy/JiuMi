package com.miniapp.talks.bean;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

/**
 * 作者:sgm
 * 描述:
 */
public class LoginData extends LitePalSupport {


    /**
     * birthday : 2019-06-13
     * city : 月球
     * created_at : 2019-06-13 15:52:29
     * headimgurl : http://59.110.169.251/upload/avatar/20190613/25220_155229_4875.jpg
     * id : 1151842
     * introduction : 开黑交友就在MINI
     * is_room : 2
     * level : 1
     * mizuan : 0
     * nickname : 哈哈
     * openid : mobile_reg-31581000-8db0-11e9-8ae1-63b8b7601d21
     * pass : 14e1b600b1fd579f47433b88e8d85291
     * phone : 18037511805
     * sex : 1
     * updated_at : 2019-06-13 15:52:29
     */

    private String birthday;
    private String city;
    private String created_at;
    private String headimgurl;
    @SerializedName(value = "id", alternate = "user_id")
    private int userId;
    private String introduction;
    private int is_room;
    private int level;
    private int mizuan;
    private String nickname;
    private String openid;
    private String pass;
    private String phone;
    private int sex;
    private String updated_at;
    private String ry_uid;
    private String ry_token;
    private String token = "";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRy_uid() {
        return ry_uid;
    }

    public void setRy_uid(String ry_uid) {
        this.ry_uid = ry_uid;
    }

    public String getRy_token() {
        return ry_token;
    }

    public void setRy_token(String ry_token) {
        this.ry_token = ry_token;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getIs_room() {
        return is_room;
    }

    public void setIs_room(int is_room) {
        this.is_room = is_room;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMizuan() {
        return mizuan;
    }

    public void setMizuan(int mizuan) {
        this.mizuan = mizuan;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
