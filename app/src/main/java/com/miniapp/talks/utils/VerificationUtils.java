package com.miniapp.talks.utils;

/**
 * Created by wyd on 2016/04/18.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificationUtils {
    /**
     * 验证手机号是否合f法
     *
     * @param mobileNumber 要验证的手机号
     * @return true代表验证成功, false代表验证失败
     */
    public static boolean VildateMobile(String mobileNumber) {
        String regex = "^1[3456789]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();

    }
    /**
     * 判断邮箱是否合法
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if (null==email || "".equals(email))
            return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 验证输入的身份证号是否合法
     */
    public static boolean isLegalId(String id){
        if (id.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 验证连号
     */
    public static boolean isContinue (String id){
        if (id.toUpperCase().matches("(?:(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){5}|(?:9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){5})\\d")){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 验证炸弹号
     */
    public static boolean isSame (String id){
        if (id.toUpperCase().matches("^(?:([0-9])\\1{5})$")){
            return true;
        }else {
            return false;
        }
    }
}
