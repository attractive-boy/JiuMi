package com.miniapp.talks.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 加减乘除的精确运算，不丢失精度，保留指定小数位数
 */

public class Arith {

    //运算精度
    private static final int DEF_DIV_SCALE = 8;

    //这个类不能实例化
    private Arith() {

    }

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static double add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1 被減数
     * @param v2 減数
     * @return两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除非运算，当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入
     *
     * @param v1 被除數
     * @param v2 除數
     * @return 兩個參數的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     *
     * @param v1    被除數
     * @param v2    除數
     * @param scale 表示表示需要精確到小數點以後位数。
     * @return 兩個參數的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static double div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * 提供精確的小數位四捨五入處理。
     * 提供精确的小数位四舍五入处理
     *
     * @param v     需要四捨五入的數位
     * @param scale 小數點後保留幾位
     * @return 四捨五入後的結果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * doubleTo str 不使用科学计数显示
     *
     * @param money
     * @return
     */
    public static String doubleToStr(double money, int scal) {

        if (scal == 0) {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            return nf.format(money);
        }


        BigDecimal d1 = new BigDecimal(Double.toString(money));

        BigDecimal d2 = new BigDecimal(Integer.toString(1));

        String s = d1.divide(d2, scal, BigDecimal.ROUND_DOWN).toString();

        if (s.contains("E") || s.contains("e")) {

            double dd = d1.divide(d2, scal, BigDecimal.ROUND_DOWN).doubleValue();

            StringBuilder builder = new StringBuilder("#0.");

            for (int i = 0; i < scal; i++) {
                builder.append("0");
            }

            DecimalFormat df = new DecimalFormat(builder.toString());

            s = df.format(dd);
        }

        return s;

    }

    //str 转 float
    public static float strToFloat(String str) {

        try {

            return Float.parseFloat(str);

        } catch (Exception e) {

            return 0;

        }

    }
    //str 转 double
    public static double strToDouble(String str) {

        try {

            return Double.parseDouble(str);

        } catch (Exception e) {

            return 0;

        }

    }

    //str 转 int
    public static int strToInt(String str) {

        try {

            return Integer.parseInt(str);

        } catch (Exception e) {

            return 0;

        }

    }

    //str 转 long
    public static long strToLong(String str) {

        try {

            return Long.parseLong(str);

        } catch (Exception e) {

            return 0;

        }

    }

    private static String big2(double d) {
        BigDecimal d1 = new BigDecimal(Double.toString(d));
        BigDecimal d2 = new BigDecimal(Integer.toString(1));
        // 四舍五入,保留2位小数
        return d1.divide(d2, 2, BigDecimal.ROUND_DOWN).toString();
    }

}
