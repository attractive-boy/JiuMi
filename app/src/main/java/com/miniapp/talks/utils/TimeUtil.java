package com.miniapp.talks.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import timber.log.Timber;

public class TimeUtil {

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getTime() {
        Date date = new Date();// 创建一个时间对象，获取到当前的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 设置时间显示格式
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String CurrentTime = sdf.format(date);
        return CurrentTime;
    }

    public static String getTime2() {
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return format.format(timeMillis);
    }


    public static String getLongTime() {
        long timeMillis = System.currentTimeMillis();
        return timeMillis + "";
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getTimeYmd() {
        Date date = new Date();// 创建一个时间对象，获取到当前的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// 设置时间显示格式
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String CurrentTime = sdf.format(date);

        return CurrentTime;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getTimeYm() {
        Date date = new Date();// 创建一个时间对象，获取到当前的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM", Locale.CHINA);// 设置时间显示格式
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String CurrentTime = sdf.format(date);

        return CurrentTime;
    }


    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br/>false
     */
    public static boolean isDate2Bigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = false;
        } else if (dt1.getTime() <= dt2.getTime()) {
            isBigger = true;
        }
        return isBigger;
    }


    private static final long SS = 1000;

    private static final long MS = SS * 60;

    private static final long HourS = MS * 60;

    private static final long DayS = HourS * 24;

    private static final long MonthS = DayS * 30;

    private static final long YearS = MonthS * 12;


    /**
     * 时间戳转
     *
     * @param timestamp 时间戳
     * @return
     */
    public static String sendTime(long timestamp) {
        long timeMillis = System.currentTimeMillis();
        long timeKK = timeMillis - timestamp;
        if (timeKK > YearS) {
            int time = (int) (timeKK / YearS);
            return time + "年前";
        } else if (timeKK > MonthS) {
            int time = (int) (timeKK / MonthS);
            return time + "个月前";
        } else if (timeKK > DayS) {
            int time = (int) (timeKK / DayS);
            return time + "天前";
        } else if (timeKK > HourS) {
            int time = (int) (timeKK / HourS);
            return time + "小时前";
        } else if (timeKK > MS) {
            int time = (int) (timeKK / MS);
            return time + "分钟前";
        } else if (timeKK > SS) {
            int time = (int) (timeKK / SS);
            return time + "秒前";
        } else {
            return "刚刚";
        }
    }

    /**
     * yy_MM_DD HH:MM:SS 转
     *
     * @param timeyy
     * @return
     */
    public static String sendTime(String timeyy) {
        long timestamp = TimeUtil.dataOne(timeyy);//转时间戳

        long timeMillis = System.currentTimeMillis();
        long timeKK = timeMillis - timestamp;
        if (timeKK > YearS) {
            int time = (int) (timeKK / YearS);
            return time + "年前";
        } else if (timeKK > MonthS) {
            int time = (int) (timeKK / MonthS);
            return time + "个月前";
        } else if (timeKK > DayS) {
            int time = (int) (timeKK / DayS);
            return time + "天前";
        } else if (timeKK > HourS) {
            int time = (int) (timeKK / HourS);
            return time + "小时前";
        } else if (timeKK > MS) {
            int time = (int) (timeKK / MS);
            return time + "分钟前";
        } else if (timeKK > SS) {
            int time = (int) (timeKK / SS);
            return time + "秒前";
        } else {
            return "刚刚";
        }
    }


    /**
     * yy_MM_DD HH:MM:SS 转
     *
     * @param timeyy
     * @return
     */
    public static String chatTime(String timeyy) {
        long timestamp = TimeUtil.dataOne(timeyy);//转时间戳

        long timeMillis = System.currentTimeMillis();
        long timeKK = timeMillis - timestamp;
        if (timeKK > 3 * DayS) {
            return timeyy;
        } else if (timeKK > 2 * DayS) {
            return "前天" + toDateHhmm(dataOne(timeyy));
        } else if (timeKK > DayS) {
            return "昨天" + toDateHhmm(dataOne(timeyy));
        } else if (timeKK > 5 * MS) {
            return toDateHhmm(dataOne(timeyy));
        } else if (timeKK > MS) {
            int time = (int) (timeKK / MS);
            return time + "分钟前";
        } else if (timeKK > SS) {
            int time = (int) (timeKK / SS);
            return time + "秒前";
        } else {
            return "刚刚";
        }
    }

    /**
     * yy_MM_DD HH:MM:SS 转
     *
     * @param timeyy
     * @return
     */
    public static String chatTimee(String timeyy) {
        long timestamp = TimeUtil.dataOne(timeyy);//转时间戳

        long timeMillis = System.currentTimeMillis();
        long timeKK = timeMillis - timestamp;
        if (timeKK > DayS) {
            return timeyy;
        } else if (timeKK > HourS) {
            int time = (int) (timeKK / HourS);
            return time + "小时前";
        } else if (timeKK > MS) {
            int time = (int) (timeKK / MS);
            return time + "分钟前";
        } else {
            return "刚刚";
        }
    }


    /**
     * 调此方法输入所要转换的时间输入例如（"2014-06-14-16-09-00"）返回时间戳
     *
     * @param time
     * @return
     */
    public static long dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",

                Locale.CHINA);
        sdr.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date;
        long ltime = 0;
        try {
            date = sdr.parse(time);
            ltime = date.getTime();
           /* String stf = String.valueOf(l);
            times = stf.substring(0, 10);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ltime;
    }

    /**
     * 时间戳 返回 HH:MM
     *
     * @param times
     * @return
     */
    public static String toDateHhmm(long times) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm",
                Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return format.format(times);
    }

    /**
     * 时间戳 返回 MM:ss
     *
     * @param times
     * @return
     */
    public static String toDateMMss(long times) {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss",
                Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return format.format(times);
    }

    /**
     * 时间戳 返回 yyyy-MM-dd
     *
     * @param times
     * @return
     */
    public static String toDateYmd(long times) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
                Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return format.format(times);
    }

    /**
     * 时间戳 返回 yyyy-MM-dd
     *
     * @param times
     * @return
     */
    public static String toDateYmdHan(long times) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss",
                Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return format.format(times);
    }

    public static String toDateYmdHan2(long times) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日",
                Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return format.format(times);
    }


    public static String longtoHM(long times) {
        int lhour = (int) times / 60;
        int lMin = (int) times % 60;
        String strMIn;
        if (lMin < 10) {
            strMIn = "0" + lMin;
        } else {
            strMIn = lMin + "";
        }

        return lhour + ":" + strMIn;

    }


    /**
     * 获取剩余天数
     *
     * @param serverTime 时间
     * @param serverTime 样式
     * @return
     * @throws ParseException
     */
    public static int getLeftDay(String serverTime) {
        long timestamp = TimeUtil.dataOne(serverTime);//转时间戳
        long timeMillis = System.currentTimeMillis();
        long leftMillins = timestamp - timeMillis;
        int leftDay = (int) (leftMillins / (3600 * 24 * 1000));
        return leftDay;
    }


    // 根据年月日计算年龄,birthTimeString:"1994-11-14"
    public static int getAgeByDay(Date date) {
        // 得到当前时间的年、月、日
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayNow = cal.get(Calendar.DATE);
            //得到输入时间的年，月，日
            cal.setTime(date);
            int selectYear = cal.get(Calendar.YEAR);
            int selectMonth = cal.get(Calendar.MONTH) + 1;
            int selectDay = cal.get(Calendar.DATE);
            // 用当前年月日减去生日年月日
            int yearMinus = yearNow - selectYear;
            int monthMinus = monthNow - selectMonth;
            int dayMinus = dayNow - selectDay;
            int age = yearMinus;// 先大致赋值
            if (yearMinus <= 0) {
                age = 0;
            }
            if (monthMinus < 0) {
                age = age - 1;
            } else if (monthMinus == 0) {
                if (dayMinus < 0) {
                    age = age - 1;
                }
            }
            return age;
        }
        return 0;
    }


    /**
     * 日期转Date
     *
     * @param serverTime
     * @param format
     * @return
     */
    public static Date parseServerTime(String serverTime, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(serverTime);
        } catch (Exception e) {
            Timber.e(e, "");
        }
        return date;
    }

    /**
     * 时间戳 返回 HH:MM:ss
     *
     * @param times
     * @return
     */
    public static String toDateHhmmss(long times) {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss",
                Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return format.format(times);
    }

    /**
     * 时间戳 返回 yyyy-MM-dd
     *
     * @param times
     * @return
     */
    public static String toDatee(long times) {
        SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss秒",
                Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return format.format(times);
    }

    public static String toDateee(long times) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss",
                Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return format.format(times);
    }

    public static String toDateeee(long times) {
        SimpleDateFormat format = new SimpleDateFormat("ss",
                Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        String afterTime = format.format(times);

        if(!TextUtils.isEmpty(afterTime)){
            if (afterTime.startsWith("0")) {
                afterTime = afterTime.substring(1);
            }
        }
        return afterTime;
    }


    /**
     * 获取指定单位数字
     */
    public static int toMeFormat(String formatoo, String beginTime) {
        SimpleDateFormat format = new SimpleDateFormat(formatoo, Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String format1 = format.format(beginTime);
        return Integer.parseInt(format1);
    }

    private static String add00(int oldInt) {
        if (oldInt < 10) {
            return "0" + oldInt;
        } else {
            return oldInt + "";
        }

    }

    /**
     * 时间戳 返回 yyyy-MM-dd
     *
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String getReachDay(String beginTime, int months, int days) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        int mm_start = Integer.parseInt(beginTime.substring(5, 7));
        int day_start = Integer.parseInt(beginTime.substring(8, 10));

        StringBuilder beginTime00 = new StringBuilder(beginTime);
        StringBuilder replace0 = beginTime00.replace(5, 7, TimeUtil.add00(mm_start + months));
        StringBuilder replace2 = replace0.replace(8, 10, TimeUtil.add00(day_start + days));
        try {
            return format.format(format.parse(replace2.toString()));
        } catch (Exception e) {
            Timber.e(e, "");
        }
        return "";
    }


}


