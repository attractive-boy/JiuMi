package com.miniapp.talks.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import com.alibaba.fastjson.JSON;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.bean.MessageBean;
import com.miniapp.talks.bean.VipBean;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者:sgm
 * 描述:
 */
public class BaseUtils {
    public static String getNowDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 文件转Base64.
     *
     * @param filePath
     * @return
     */
    public static String file2Base64(String filePath) {
        FileInputStream objFileIS = null;
        try {
            objFileIS = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream objByteArrayOS = new ByteArrayOutputStream();
        byte[] byteBufferString = new byte[1024];
        try {
            for (int readNum; (readNum = objFileIS.read(byteBufferString)) != -1; ) {
                objByteArrayOS.write(byteBufferString, 0, readNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String videodata = Base64.encodeToString(objByteArrayOS.toByteArray(), Base64.DEFAULT);
        return videodata;
    }

    /**
     * 将图片转换成Base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    public static String getJson(String type, String message, String nickname, String id) {
        MessageBean msg = new MessageBean();
        msg.setMessageType(type);
        msg.setMessage(message);
        msg.setNickName(nickname);
        msg.setUser_id(id);
        String str = JSON.toJSONString(msg);
        return str;
    }

    public static String getJson(String type, String message, String nickname, String id,String vip_img,String hz_img) {
        MessageBean msg = new MessageBean();
        msg.setMessageType(type);
        msg.setMessage(message);
        msg.setNickName(nickname);
        msg.setUser_id(id);
        if(TextUtils.isEmpty(vip_img)){
            msg.vip_img = "";
        }else {
            msg.vip_img = vip_img;
        }
        if(TextUtils.isEmpty(hz_img)){
            msg.hz_img = "";
        }else {
            msg.hz_img = hz_img;
        }
        String str = JSON.toJSONString(msg);
        return str;
    }
    public static String getJson(String type, String message, String nickname, String id,String vip_img,String hz_img,String nick_color) {
        MessageBean msg = new MessageBean();
        msg.setMessageType(type);
        msg.setMessage(message);
        msg.setNickName(nickname);
        msg.setUser_id(id);
        msg.nick_color = nick_color;
        if(TextUtils.isEmpty(vip_img)){
            msg.vip_img = "";
        }else {
            msg.vip_img = vip_img;
        }
        if(TextUtils.isEmpty(hz_img)){
            msg.hz_img = "";
        }else {
            msg.hz_img = hz_img;
        }
        String str = JSON.toJSONString(msg);
        return str;
    }
    public static String getJson(String type, String message,String nickName,String userId, VipBean.DataBean dataBean) {
        MessageBean msg = new MessageBean();
        msg.setMessageType(type);
        msg.setMessage(message);
        msg.setNickName(nickName);
        msg.setUser_id(userId);
        msg.nick_color = dataBean.getNick_color();
        if(TextUtils.isEmpty(dataBean.getVip_img())){
            msg.vip_img = "";
        }else {
            msg.vip_img = dataBean.getVip_img();
        }
        if(TextUtils.isEmpty(dataBean.getHz_img())){
            msg.hz_img = "";
        }else {
            msg.hz_img = dataBean.getHz_img();
        }
        if(TextUtils.isEmpty(dataBean.getVip_tx())){
            msg.vip_tx = "";
        }else {
            msg.vip_tx = dataBean.getVip_tx();
        }
        String str = JSON.toJSONString(msg);
        return str;
    }
    public static String getJson(String type, String message, String nickname, String id, String vip_img, String hz_img, String nick_color, VipBean.DataBean dataBean) {
        MessageBean msg = new MessageBean();
        msg.setMessageType(type);
        msg.setMessage(message);
        msg.setNickName(nickname);
        msg.setUser_id(id);
        msg.nick_color = nick_color;
        if(TextUtils.isEmpty(vip_img)){
            msg.vip_img = "";
        }else {
            msg.vip_img = vip_img;
        }
        if(TextUtils.isEmpty(hz_img)){
            msg.hz_img = "";
        }else {
            msg.hz_img = hz_img;
        }
        if(dataBean !=null){
            msg.ltk = dataBean.getLtk();
            msg.ltk_left = dataBean.getLtk_left();
            msg.ltk_right = dataBean.getLtk_right();
        }
        String str = JSON.toJSONString(msg);
        return str;
    }

    /**
     * 房间设置
     * @param type
     * @param message
     * @param nickname
     * @param id
     * @return
     */
    public static String getJson(String type, String message,
                                 String nickname, String id, String room_name,
                                 String room_type, String room_background,
                                          String room_intro) {
        MessageBean msg = new MessageBean();
        msg.setMessageType(type);
        msg.setMessage(message);
        msg.setNickName(nickname);
        msg.setUser_id(id);
        msg.setRoom_name(room_name);
        msg.setRoom_type(room_type);
        msg.setRoom_background(room_background);
        msg.setRoom_intro(room_intro);
        String str = JSON.toJSONString(msg);
        return str;
    }

    /**
     * 表情
     */
    public static String getJson(String type, String message,
                                 String nickname, String id, String is_answer,
                                         String emoji, String t_length,String vip_img,String hz_img,String nick_color) {
        MessageBean msg = new MessageBean();
        msg.setMessageType(type);
        msg.setMessage(message);
        msg.setNickName(nickname);
        msg.setUser_id(id);
        msg.setIs_answer(is_answer);
        msg.setEmoji(emoji);
        msg.setT_length(t_length);
        msg.nick_color = nick_color;
        if(TextUtils.isEmpty(vip_img)){
            msg.vip_img = "";
        }else {
            msg.vip_img = vip_img;
        }
        if(TextUtils.isEmpty(hz_img)){
            msg.hz_img = "";
        }else {
            msg.hz_img = hz_img;
        }
        String str = JSON.toJSONString(msg);
        return str;
    }

    public static MessageBean getMessageBean(String json) {

        MessageBean data = JSON.parseObject(json, MessageBean.class);
//        LogUtils.debugInfo("sgm","====得到的对象：" + data.toString());
        return data;
    }

    public static int getRandom(int size) {
        int min = 0;
        int max = size;
        Random random = new Random();
        int num = random.nextInt(max) % (max - min + 1) + min;
        LogUtils.debugInfo("====随机数：" + num);
        return num;
    }


    /***
     * 将指定路径的图片转uri
     * @param context
     * @param path ，指定图片(或文件)的路径
     * @return
     */
    public static Uri getMediaUriFromPath(Context context, String path) {
        Uri mediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = context.getContentResolver().query(mediaUri,
                null,
                MediaStore.Images.Media.DISPLAY_NAME + "= ?",
                new String[] {path.substring(path.lastIndexOf("/") + 1)},
                null);

        Uri uri = null;
        if(cursor.moveToFirst()) {
            uri = ContentUris.withAppendedId(mediaUri,
                    cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID)));
        }
        cursor.close();
        return uri;
    }

    public static String getNumber(String str){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    public static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SimpleArrayMap && ((SimpleArrayMap) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
                return true;
            }
        }
        if (obj instanceof LongSparseArray && ((LongSparseArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (obj instanceof android.util.LongSparseArray
                    && ((android.util.LongSparseArray) obj).size() == 0) {
                return true;
            }
        }
        return false;
    }
}
