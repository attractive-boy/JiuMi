package com.miniapp.talks.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.miniapp.talks.activity.MainActivity;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class MyUtil {

    private static Toast toast;
    private static long lastTime;
    private CountDownTimer countDownTimer;
    private static Timer timer;


    /**
     * toast 单例
     *
     * @param context
     * @param strings
     */
    public static void mytoast(Context context, String strings) {
        if (toast == null) {
            toast = Toast.makeText(context, strings, Toast.LENGTH_SHORT);
        } else {
            toast.setText(strings);
        }
        toast.show();

    }

    /**
     * 防快速点击
     *
     * @return
     */
    public static Boolean isFastClick() {
        if ((System.currentTimeMillis() - lastTime) < 1000) {
            lastTime = System.currentTimeMillis();
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据手机分辨率从DP转成PX
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率PX(像素)转成DP
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 沉浸状态栏
     *
     * @param window
     * @param context
     * @return 状态栏高度
     */
    public static int setStatusBar(Context context, Window window, Boolean isDark, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (colorId != 0) {//是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
                window.setStatusBarColor(context.getResources().getColor(colorId));//设置状态栏背景色
            } else {
                window.setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = window.getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {//低于4.4
            Log.d("setStatusBar", "低于4.4的android系统版本不存在沉浸式状态栏");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isDark) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            //    window.setStatusBarColor(Color.TRANSPARENT);//透明
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //   window.setStatusBarColor(Color.TRANSPARENT);//透明
        }
        return getStatusBarHeight(context);
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
        );
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public static String getMD(Long times) {
        String originalMd = "" + times;

        if (TextUtils.isEmpty(originalMd)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(originalMd.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String getMD(String password) {

        if (TextUtils.isEmpty(password)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(password.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    public static byte[] readStream(String imagepath) throws Exception {
        FileInputStream fs = new FileInputStream(imagepath);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while (-1 != (len = fs.read(buffer))) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        fs.close();
        return outStream.toByteArray();
    }

    // 二进制转字符串
    public static String byte2hex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        String tmp = "";
        for (int i = 0; i < b.length; i++) {
            tmp = Integer.toHexString(b[i] & 0XFF);
            if (tmp.length() == 1) {
                sb.append("0" + tmp);
            } else {
                sb.append(tmp);
            }

        }
        return sb.toString();
    }


    /**
     * 获取是否存在NavigationBar
     *
     * @param context
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }
        return hasNavigationBar;
    }

    /**
     * 获取虚拟功能键高度
     *
     * @param context
     * @return
     */
    public static int getVirtualBarHeigh(Context context) {
        int vh = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }

    //数组转String

    public static String stringarrToString(ArrayList<String> mylist) {
        String myback = "";
        for (int i = 0; i < mylist.size(); i++) {
            if (i == mylist.size() - 1) {
                myback += mylist.get(i);
            } else {
                myback += mylist.get(i);
                myback += ",";
            }
        }
        return myback;
    }

    public static String intarrToString(ArrayList<Integer> mylist) {
        String myback = "";
        for (int i = 0; i < mylist.size(); i++) {
            if (i == mylist.size() - 1) {
                myback += mylist.get(i);
            } else {
                myback += mylist.get(i);
                myback += ",";
            }
        }
        return myback;
    }


    public static boolean isDestroy(Activity activity) {
        if (activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 得到网页中图片的地址
     *
     * @param //sets html字符串
     */
    public static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    public static void getVersionCode() {
    }


    /**
     * split
     * 得到网页中图片的地址
     *
     * @param //sets html字符串
     */
    private ArrayList<String> htmltoarr(String html) {
        if (html != null) {
            ArrayList<String> list = new ArrayList<>();
            String[] split = html.split("\"");
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                if ((i - 1) % 4 == 0) {
                    //                  list.add(MyUrl.PREFIX + split[i]);
                }
            }
            return list;
        }
        return null;
    }


    /**
     * 使用Matrix将Bitmap压缩到指定大小
     *
     * @param bitmap
     * @param w
     * @param h
     * @return
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float scaleWidth = ((float) w) / width;
        float scaleHeight = ((float) h) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

     /*   WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width00 = dm.widthPixels;    */     // 屏幕宽度（像素）

        return Bitmap.createBitmap(bitmap, 0, 0, width,
                height, matrix, true);


    }


    public static void toWeb(Context context, String link) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(link);
        intent.setData(content_url);
        context.startActivity(intent);
    }


    /**
     * 城市去“市”
     *
     * @param city0
     * @return
     */
    public static String cityNoshi(String city0) {
        String city = "";
        if (city0 != null) {
            String ci = city0.charAt(city0.length() - 1) + "";
            if ("市".equals(ci)) {
                city = city0.substring(0, city0.length() - 1);
            } else {
                city = city0;
            }
            return city;
        }
        return "";
    }


    /**
     * 强制两位小数
     *
     * @param num
     * @return
     */
    public static String doubleTo2String(double num) {
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }

    public static String hidePhone(String phone) {
        if (phone == null || phone.length() != 11) {
            return phone;
        }
        String back = phone.substring(0, 3) + "****" + phone.substring(7, 11);
        return back;
    }

    public static String hideName(String name) {
        if (name == null || name.length() < 1) {
            return name;
        }
        String back = name.substring(0, 1) + "**";
        return back;
    }

    public static String hideBankCard(String card) {
        if (card == null || card.length() < 9) {
            return card;
        }
        int length = card.length();
        String back = card.substring(0, 4) + "***********" + card.substring(length - 5, length - 1);
        return back;
    }


    public static boolean checkApkExist(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);

            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    //金额验证

    public static boolean isMoney(String str) {

        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式

        Matcher match = pattern.matcher(str);
        Log.e("&&&&&&", "isMoney: " + ".".equals(str.charAt(str.length() - 1)));
        //  Log.e("&&&&&&", "isMoney222: "+ ".".equals(str.charAt(str.length())));
        if (str.length() > 1 && ".".equals(str.charAt(str.length() - 1) + "")) {
            return false;
        }

        return match.matches();
       /* if(match.matches()==false){
            return false;
        }else{
            return true;
        }
*/
    }

    /*
     * 获取当前网络时间
     */
    public static String getNetworkTime() {
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection conn = url.openConnection();
            conn.connect();
            long dateL = conn.getDate();
            Date date = new Date(dateL);
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.CHINA);
            return dateFormat.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "null";
    }


    /**
     * 倒计时
     */
    public static void startCodeTime(TextView tv) {

        CountDownTimer countDownTimer = new CountDownTimer(1000 * 60, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv.setEnabled(false);
                tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                tv.setText("已发送(" + millisUntilFinished / 1000 + ")");
            }

            @Override
            public void onFinish() {
                tv.setEnabled(true);
                tv.setText("重新获取");
            }
        }.start();
    }


    /**
     * 获取当前本地apk的版本
     *
     * @param mContext
     * @return
     */
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     * PopuWindow的背景阴影
     *
     * @param activity
     * @param bgAlpha
     */
    public static void setBackgroundAlpha(MainActivity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        (activity).getWindow().setAttributes(lp);
    }


    //
    public static boolean vd(String str) {
        char[] chars = str.toCharArray();
        boolean isGB2312 = false;
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = ("" + chars[i]).getBytes();
            if (bytes.length == 2) {
                int[] ints = new int[2];
                ints[0] = bytes[0] & 0xff;
                ints[1] = bytes[1] & 0xff;
                if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {
                    isGB2312 = true;
                    break;
                }
            }
        }
        return isGB2312;

    }


    public static void CopyToClipboard(Context context, String text) {
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //clip.getText(); // 粘贴
        clip.setText(text); // 复制
    }

    public static void errorQMUITip(Context mContext, String string) {
        QMUITipDialog tipDialog = new QMUITipDialog.Builder(mContext)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord(string)
                .create();
        tipDialog.show();

    }

    public static void hideStatusNavigationBar( Window window) {
        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN //hide statusBar
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION; //hide navigationBar
        window.getDecorView().setSystemUiVisibility(uiFlags);
    }

    /* 根据月份和日期判断星座 */
    public static String XingZuo(int year, int month, int day) {
        String xz = null;
        if ((month == 3 && day >= 21 && day <= 31) || (month == 4 && day >= 1 && day <= 19)) {
            xz = "白羊座";
        }
        if ((month == 4 && day >= 20 && day <= 30) || (month == 5 && day >= 1 && day <= 20)) {
            xz = "金牛座";
        }
        if ((month == 5 && day >= 21 && day <= 31) || (month == 6 && day >= 1 && day <= 21)) {
            xz = "双子座";
        }
        if ((month == 6 && day >= 22 && day <= 30) || (month == 7 && day >= 1 && day <= 22)) {
            xz = "巨蟹座";
        }
        if ((month == 7 && day >= 23 && day <= 31) || (month == 8 && day >= 1 && day <= 22)) {
            xz = "狮子座";
        }
        if ((month == 8 && day >= 23 && day <= 31) || (month == 9 && day >= 1 && day <= 22)) {
            xz = "处女座";
        }
        if ((month == 9 && day >= 23 && day <= 30) || (month == 10 && day >= 1 && day <= 23)) {
            xz = "天秤座";
        }
        if ((month == 10 && day >= 24 && day <= 31) || (month == 11 && day >= 1 && day <= 22)) {
            xz = "天蝎座";
        }
        if ((month == 11 && day >= 23 && day <= 30) || (month == 12 && day >= 1 && day <= 21)) {
            xz = "射手座";
        }
        if ((month == 12 && day >= 22 && day <= 31) || (month == 1 && day >= 1 && day <= 19)) {
            xz = "摩羯座";
        }
        if ((month == 1 && day >= 20 && day <= 31) || (month == 2 && day >= 1 && day <= 18)) {
            xz = "水瓶座";
        }
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) { // 闰年2月29天
            if ((month == 2 && day >= 19 && day <= 29) || (month == 3 && day >= 1 && day <= 20)) {
                xz = "双鱼座";
            }
        } else { // 平年2月28天
            if ((month == 2 && day >= 19 && day <= 28) || (month == 3 && day >= 1 && day <= 20)) {
                xz = "双鱼座";
            }
        }
        return xz;
    }

    /**
     * service是否正在运行
     * @param context
     * @param className
     * @return
     */
    public static boolean isServiceExisted(Context context, String className) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(Integer.MAX_VALUE);

        if (!(serviceList.size() > 0)) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            ActivityManager.RunningServiceInfo serviceInfo = serviceList.get(i);
            ComponentName serviceName = serviceInfo.service;

            String name = serviceName.getClassName();
            if (name.equals(className)) {
                return true;
            }
        }
        return false;
    }

    //设置VIew的margin
    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
