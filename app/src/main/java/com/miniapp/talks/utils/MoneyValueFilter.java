package com.miniapp.talks.utils;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;


import com.jess.arms.base.BaseApplication;

import java.util.regex.Pattern;

/**
 * 金额输入限制
 */

public class MoneyValueFilter extends DigitsKeyListener {

    private static final String TAG = "MoneyValueFilter";
    Pattern mPattern;

    //输入的最大金额
    private double MAX_VALUE = 10000.00;

    private String mTipText = "";

    public MoneyValueFilter() {
        super(false, true);
        mPattern = Pattern.compile("([0-9]|\\.)*");
        mTipText = "单词购买钥匙最多只能10000把哟！";
    }

    private int digits = 2;

    public MoneyValueFilter setDigits(int d) {
        digits = d;
        return this;
    }

    public MoneyValueFilter setMaxValue(double maxValue) {
        MAX_VALUE = maxValue;
        return this;
    }

    public MoneyValueFilter setMaxTipTxt(String tipText) {
        mTipText = tipText;
        return this;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        CharSequence out = super.filter(source, start, end, dest, dstart, dend);

        if (out != null) {
            source = out;
            start = 0;
            end = out.length();
        }

        if (MAX_VALUE == 10000000000.0) {
            MAX_VALUE = 9999999999.999999;
        }

        int len = end - start;

        // if deleting, source is empty
        // and deleting can't break anything
        if (len == 0) {
            return source;
        }

        //以点开始的时候，自动在前面添加0
        if (source.toString().equals(".") && dstart == 0) {
            return "0.";
        }
        //如果起始位置为0,且第二位跟的不是".",则无法后续输入
        if (!source.toString().equals(".") && dest.toString().equals("0")) {
            return "";
        }

        int dlen = dest.length();

        // Find the position of the decimal .
        for (int i = 0; i < dstart; i++) {
            if (dest.charAt(i) == '.') {
                // being here means, that a number has
                // been inserted after the dot
                // check if the amount of digits is right
                return (dlen - (i + 1) + len > digits) ?
                        "" :
                        new SpannableStringBuilder(source, start, end);
            }
        }

        for (int i = start; i < end; ++i) {
            if (source.charAt(i) == '.') {
                // being here means, dot has been inserted
                // check if the amount of digits is right
                if ((dlen - dend) + (end - (i + 1)) > digits)
                    return "";
                else
                    break;  // return new SpannableStringBuilder(source, start, end);
            }
        }

        String destText = dest.toString();

        StringBuilder builder = new StringBuilder(destText);//向指定位置插入

        builder.insert(dstart, source);

        destText = builder.toString();

        double sumText = Double.parseDouble(destText);

        if (sumText > MAX_VALUE) {//输入超过限额
            ToastUtil.showToast(BaseApplication.mApplication, mTipText);
            return dest.subSequence(dstart, dend);
        }

        return new SpannableStringBuilder(source, start, end);


//        String sourceText = source.toString();
//        String destText = dest.toString();
//
//        if(destText.contains(POINTER)){
//            if(dstart<dend && destText.indexOf(POINTER) == dstart){//删除小数点
//                return dest.subSequence(dstart, dstart);
//            }
//        }
//
//        //验证删除等按键
//        if (TextUtils.isEmpty(sourceText)) {
//            return "";
//        }
//
//        Matcher matcher = mPattern.matcher(source);
//        //已经输入小数点的情况下，只能输入数字
//        if (destText.contains(POINTER)) {
//            if (!matcher.matches()) {
//                return "";
//            } else {
//                if (POINTER.equals(source)) {  //只能输入一个小数点
//                    return "";
//                }
//            }
//
//            //验证小数点精度，保证小数点后只能输入两位
//            int index = destText.indexOf(POINTER);
//            int length = dend - index;
//
//            if (length > POINTER_LENGTH) {
//                return dest.subSequence(dstart, dend);
//            }
//        } else {
//            //没有输入小数点的情况下，只能输入小数点和数字，但首位不能输入小数点和0
//            if (!matcher.matches()) {
//                return "";
//            } else {
//                if ((POINTER.equals(source) || ZERO.equals(source)) && TextUtils.isEmpty(destText)) {
//                    return "";
//                }
//            }
//        }

        //验证输入金额的大小
//        if (destText.contains(POINTER)) {//包含小数点
//
//            int position = destText.indexOf(POINTER);//获取光标位置
//
//            if (dstart == destText.length()) {//光标在最后面
//
//                double sumText = Double.parseDouble(destText + sourceText);
//                if (sumText > MAX_VALUE) {
//                    return dest.subSequence(dstart, dend);
//                }
//
//            } else if (dstart - 1 >= position && dstart < destText.length()) {//光标在小数点和最后一位之间
//
//                StringBuilder builder = new StringBuilder(destText);//向指定位置插入
//
//                builder.insert(dstart, source);
//
//                destText = builder.toString();
//
//                double sumText = Double.parseDouble(destText);
//
//                //算出具体位置，最后乘以相应位数的小数
//                double orgMoney = Double.parseDouble(destText);
//
//                int zeroCount = dstart - position - 1;
//
//                double lastAmount = 0.0;
//
//                String pattern = "0.";
//
//                for (int i = 0; i < zeroCount; i++) {
//
//                    pattern += "0";
//
//                }
//
//                DecimalFormat df = new DecimalFormat(pattern);
//
//                String result = df.format(lastAmount);
//
//                double resultDouble = Double.parseDouble(result);
//
//                resultDouble = resultDouble * Double.parseDouble(sourceText);
//
//                double sumText = orgMoney + resultDouble;
//
//                if (sumText > MAX_VALUE) {
//                    return dest.subSequence(dstart, dend);
//                }
//            } else if(dstart - 1 < position && dstart -1>=0){//在小数位前面
//
//            }
//
//
//        } else {//不包含小数点
//
//            double sumText = Double.parseDouble(destText + sourceText);
//            if (sumText > MAX_VALUE) {
//                return dest.subSequence(dstart, dend);
//            }
//
//        }

//        if(destText.contains(POINTER)){
//            int position = destText.indexOf(POINTER);//获取光标位置
//            if (dstart - 1 >= position && dstart < destText.length()) {//光标在小数点和最后一位之间
//                return dest.subSequence(dstart, dend);
//            }
//        }
//
//        StringBuilder builder = new StringBuilder(destText);//向指定位置插入
//
//        builder.insert(dstart, source);
//
//        destText = builder.toString();
//
//        double sumText = Double.parseDouble(destText);
//
//        if (sumText > MAX_VALUE) {
//            return dest.subSequence(dstart, dend);
//        }
//
//        return dest.subSequence(dstart, dend) + sourceText;
    }


}
