package com.miniapp.talks.activity.my;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.login.UploadActivity;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.bean.ConstellationBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.JsonBean;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.OtherBean;
import com.miniapp.talks.bean.UserBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.BaseUtils;
import com.miniapp.talks.utils.GetJsonDataUtil;
import com.miniapp.talks.utils.MyUtil;
import com.miniapp.talks.utils.PhotoWindow;
import com.miniapp.talks.utils.SdcardTools;
import com.miniapp.talks.utils.TextNumUtil;
import com.miniapp.talks.utils.TimeUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.REGISTER;
import static com.miniapp.talks.utils.Constant.XIUGAIGERENZILIAOCHENGGONG;

public class ModifyDataActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;

    @BindView(R.id.head_image)
    CircularImage headImage;
    @BindView(R.id.textSend)
    EditText textSend;
    @BindView(R.id.nick_num)
    TextView nickNum;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.sex)
    ImageView sex;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.birthday_btn)
    RelativeLayout birthdayBtn;
    @BindView(R.id.constellation)
    TextView constellation;
    @BindView(R.id.consteation_btn)
    RelativeLayout consteationBtn;
    @BindView(R.id.region)
    TextView region;
    @BindView(R.id.region_btn)
    RelativeLayout regionBtn;

    private String nowDate;
    private String sexStr;

    //图片
    public static final int REQUEST_CODE_SELECT = 100;
    private ArrayList<ImageItem> selImageList = new ArrayList<>();
    private String imgString;

    //地址选择器所用
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private static boolean isLoaded = false;

    private Calendar startDate, endDate, seleteDate;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
//                    toast("把孤单当成晚餐却难以下咽，把黑夜当做温暖却难以入眠。");
                    break;
            }
        }
    };

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_modify_data;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        TextNumUtil.initTextNum(ModifyDataActivity.this, textSend, nickNum);
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        getPerInfo();
    }


    @OnClick({R.id.birthday_btn, R.id.head_image, R.id.region_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.birthday_btn:
                String string = birthday.getText().toString();
                String timeYmd = TimeUtil.getTimeYmd();
                String[] split1 = string.split("-");
                String[] split = timeYmd.split("-");
                seleteDate = Calendar.getInstance();
                startDate = Calendar.getInstance();
                endDate = Calendar.getInstance();
                startDate.set(1900, 0, 1);
                endDate.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
                seleteDate.set(Integer.parseInt(split1[0]), Integer.parseInt(split1[1]) - 1, Integer.parseInt(split1[2]));
                TimePickerView pvTime = new TimePickerBuilder(ModifyDataActivity.this, (date, v) -> {
                    nowDate = BaseUtils.getNowDate(date);
                    birthday.setText(nowDate);
                    getConstellation(nowDate);
                })
                        .setRangDate(startDate, endDate)
                        .setDate(seleteDate)
                        .build();
                pvTime.show();
                break;
            case R.id.head_image:
                showPop();
                break;
            case R.id.region_btn:
                //条件选择器
                if (isLoaded) {
                    showPickerView();
                }
                break;
        }
    }

    // 弹出选择器
    private void showPickerView() {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String opt1tx = options1Items.size() > 0 ?
//                        options1Items.get(options1).getPickerViewText() : "";
//
                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

//                String opt3tx = options2Items.size() > 0
//                        && options3Items.get(options1).size() > 0
//                        && options3Items.get(options1).get(options2).size() > 0 ?
//                        options3Items.get(options1).get(options2).get(options3) : "";

                region.setText(opt2tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    //解析数据
    private void initJsonData() {

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    //Gson 解析
    public ArrayList<JsonBean> parseData(String result) {
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    //图片的弹窗
    private void showPop() {
        PhotoWindow photoWindow = new PhotoWindow(this);
        photoWindow.showAtLocation(headImage, Gravity.BOTTOM, 0, 0);
        photoWindow.getTake_photo().setOnClickListener(v -> {
            photoWindow.dismiss();
            if (SdcardTools.sdcard()) {
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions
                        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                //相机
                                Intent intent = new Intent(ModifyDataActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                            }
                        });
            } else {
                Toast.makeText(this, "sd卡不可用", Toast.LENGTH_SHORT).show();
            }
        });
        photoWindow.getChose_pic().setOnClickListener(v -> {
            photoWindow.dismiss();
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions
                    .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) { // Always true pre-M
                            // 跳转到相册
                            ImagePicker.getInstance().setSelectLimit(1);
                            ImagePicker.getInstance().setMultiMode(false);
                            Intent intent = new Intent(ModifyDataActivity.this, ImageGridActivity.class);
                            //显示选中的图片
                            startActivityForResult(intent, REQUEST_CODE_SELECT);
                        }
                    });
        });
        photoWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 1f;
            getWindow().setAttributes(params);
        });
    }

    /*-----------------------图片选择回调------------------------------*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        ArrayList<ImageItem> tempList;
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (requestCode == REQUEST_CODE_SELECT) {
                tempList = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (tempList == null) {
                    return;
                }
                selImageList.clear();
                selImageList.addAll(tempList);

                if (selImageList.size() > 0) {
                    GlideArms.with(ModifyDataActivity.this)
                            .load(selImageList.get(0).path)
                            .placeholder(R.drawable.ic_default_image)
                            .error(R.drawable.ic_default_image)
                            .circleCrop()
                            .into(headImage);
                }
            }
        }
    }

    //获取个人信息
    private void getPerInfo() {
        RxUtils.loading(commonModel.get_user_info(UserManager.getUser().getUserId() + ""), this)
                .subscribe(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {
                    @Override
                    public void onNext(UserBean userBean) {
                        //头像
                        ArmsUtils.obtainAppComponentFromContext(ModifyDataActivity.this)
                                .imageLoader()
                                .loadImage(ModifyDataActivity.this, ImageConfigImpl
                                        .builder()
                                        .url(userBean.getData().getHeadimgurl())
                                        .placeholder(R.mipmap.no_tou)
                                        .imageView(headImage)
                                        .errorPic(R.mipmap.no_tou)
                                        .build());
                        //昵称
                        textSend.setText(userBean.getData().getNickname());
                        //ID号
                        id.setText(userBean.getData().getId() + "");
                        //性别
                        sexStr = String.valueOf(userBean.getData().getSex());
                        if (userBean.getData().getSex() == 1) {
                            sex.setImageResource(R.mipmap.gender_boy);
                        } else {
                            sex.setImageResource(R.mipmap.gender_girl);
                        }
                        //出生日期
                        birthday.setText(userBean.getData().getBirthday());
                        //星座
                        constellation.setText(userBean.getData().getConstellation());
                        //地区
                        region.setText(userBean.getData().getCity());
                    }
                });
    }

    //获取星座
    private void getConstellation(String birthday) {
        RxUtils.loading(commonModel.getConstellation(birthday), this)
                .subscribe(new ErrorHandleSubscriber<ConstellationBean>(mErrorHandler) {
                    @Override
                    public void onNext(ConstellationBean constellationBean) {
                        constellation.setText(constellationBean.getData().getConstellation());
                    }
                });
    }

    //修改信息
    private void setUserInfo(String img, String nickname, String sex, String birthday, String constellation, String city) {
        showDialogLoding();
        RxUtils.loading(commonModel.setUserInfo(String.valueOf(UserManager.getUser().getUserId()), img, nickname, sex, birthday, constellation, city), this)
                .subscribe(new ErrorHandleSubscriber<OtherBean>(mErrorHandler) {
                    @Override
                    public void onNext(OtherBean commentBean) {

                        disDialogLoding();

                        toast("修改成功");

                        if (commentBean.getCode() == 1) {
                            String token = UserManager.getUser().getToken();
                            //用户信息存入数据库
                            LoginData loginData = new LoginData();
                            loginData.setHeadimgurl(commentBean.getData().getHeadimgurl());
                            loginData.setNickname(commentBean.getData().getNickname());
                            loginData.setUserId(commentBean.getData().getId());
                            loginData.setPhone(commentBean.getData().getPhone());
                            loginData.setRy_token(commentBean.getData().getRy_token());
                            loginData.setToken(token);
//                                    if(TextUtils.isEmpty(userInfo.getData().getProvince())){
//                                        loginData.setPr(userInfo.getData().getProvince());
//                                    }
//                                    if(TextUtils.isEmpty(userInfo.getData().getCountry())){
//                                        loginData.set(userInfo.getData().getCountry());
//                                    }
                            LitePal.deleteAll(LoginData.class);
                            loginData.save();//litepal数据库，不能随便改LoginData数据
                            UserManager.initData();//存储完，初始化
                            finish();
                            EventBus.getDefault().post(new FirstEvent("修改成功", XIUGAIGERENZILIAOCHENGGONG));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        disDialogLoding();
                        LogUtils.debugInfo("====修改个人信息错误", t.getMessage());
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("修改资料", true);
        setToolbarRightConfirmText(" 保存", v -> {
            if (TextUtils.isEmpty(textSend.getText())) {
                ArmsUtils.snackbarText("昵称为空");
            } else if (TextUtils.isEmpty(birthday.getText())) {
                ArmsUtils.snackbarText("生日为空");
            } else if (TextUtils.isEmpty(constellation.getText())) {
                ArmsUtils.snackbarText("未知星座");
            } else if (TextUtils.isEmpty(region.getText())) {
                ArmsUtils.snackbarText("你没有住的地方吗");
            } else {
                if (selImageList.size() == 0) {
                    imgString = "";
                } else {
                    imgString = "data:image/png;base64," + BaseUtils.file2Base64(selImageList.get(0).path);
                }
                setUserInfo(imgString, textSend.getText().toString().replace(" ", ""), sexStr, birthday.getText().toString().replace(" ", ""), constellation.getText().toString().replace(" ", ""), region.getText().toString().replace(" ", ""));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
