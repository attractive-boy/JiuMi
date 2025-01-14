package com.miniapp.talks.activity.room;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.EditGaoActivity;
import com.miniapp.talks.adapter.RoomCategoryAdapter;
import com.miniapp.talks.adapter.RoomImgyAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.EnterRoom;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.Register;
import com.miniapp.talks.bean.RoomBg;
import com.miniapp.talks.bean.RoomType;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.BaseUtils;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.utils.PhotoWindow;
import com.miniapp.talks.utils.SdcardTools;
import com.miniapp.talks.view.ClearEditText;
import com.miniapp.talks.view.MyGridView;
import com.miniapp.talks.view.ShapeTextView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.GOONGGAO;

/**
 * 房间设置
 */
public class RoomSettingActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.edt_login_name)
    ClearEditText edtLoginName;
    @BindView(R.id.myGrid)
    MyGridView myGrid;
    @BindView(R.id.edt_pass)
    ClearEditText edtPass;
    @BindView(R.id.imgUser)
    ImageView imgUser;
    @BindView(R.id.llUser)
    LinearLayout llUser;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rightTitle)
    TextView rightTitle;
    @BindView(R.id.rightConfirm)
    ShapeTextView rightConfirm;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.llgao)
    LinearLayout llgao;
    @BindView(R.id.llzhaohu)
    LinearLayout llzhaohu;

    private RoomCategoryAdapter roomCategoryAdapter;
    private RoomImgyAdapter roomImgyAdapter;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ArrayList<ImageItem> selImageList = new ArrayList<>();
    private String strGao = "";
    private String imgUrl = "";
    ;
    private String isHome;

    EnterRoom mEnterRoom;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(isHome)) {
            toolbarBack.setOnClickListener(v -> {
                startActivity(new Intent(this, AdminHomeActivity.class));
                finish();
            });
        }
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_room_setting;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        isHome = getIntent().getStringExtra("isHome");

        mEnterRoom = (EnterRoom) getIntent().getSerializableExtra("enterRoom");

        roomCategoryAdapter = new RoomCategoryAdapter(this);
        myGrid.setAdapter(roomCategoryAdapter);
        rightConfirm.setVisibility(View.VISIBLE);
        roomImgyAdapter = new RoomImgyAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(roomImgyAdapter);
//        loadData();
        roomImgyAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<RoomBg.DataBean> data = roomImgyAdapter.getData();
            for (RoomBg.DataBean list : data) {
                list.isSlect = false;
            }
            data.get(position).isSlect = true;
            roomImgyAdapter.notifyDataSetChanged();
        });

        if(mEnterRoom != null){
            edtLoginName.setText(mEnterRoom.getRoom_info().get(0).getRoom_name());
            strGao = mEnterRoom.getRoom_info().get(0).getRoom_intro();
            loadImage(imgUser, mEnterRoom.getRoom_info().get(0).getRoom_cover(), R.drawable.touxiang_ziliao_boy);
            loadOtherData(mEnterRoom);
        }
    }

    private void loadData() {
//        LogUtils.debugInfo("sgm","====走的这个@111");
//        //获取之前的信息
//        RxUtils.loading(commonModel.getRoomInfo(isHome), this)
//                .subscribe(new ErrorHandleSubscriber<EnterRoom>(mErrorHandler) {
//                    @Override
//                    public void onNext(EnterRoom enterRoom) {
//                        LogUtils.debugInfo("sgm","====走的这个@222");
//                        edtLoginName.setText(enterRoom.getRoom_info().get(0).getRoom_name());
//                        strGao = enterRoom.getRoom_info().get(0).getRoom_intro();
//                        loadImage(imgUser, enterRoom.getRoom_info().get(0).getRoom_cover(), R.drawable.touxiang_ziliao_boy);
////                        File file = new File(enterRoom.getRoom_info().get(0).getRoom_cover());
////                        imgUrl = "data:image/png;base64," + BaseUtils.imageToBase64(file.getPath());
//                        loadOtherData(enterRoom);
//                    }
//                });
    }

    private void loadOtherData(EnterRoom enterRoom) {

        //获取类型
        RxUtils.loading(commonModel.room_type(""), this)
                .subscribe(new ErrorHandleSubscriber<RoomType>(mErrorHandler) {
                    @Override
                    public void onNext(RoomType roomType) {
//                        if (enterRoom != null) {
                            String room_type = enterRoom.getRoom_info().get(0).getRoom_type();
                            roomCategoryAdapter.getList_adapter().clear();
                            List<RoomType.DataBean> data = roomType.getData();
                            for (RoomType.DataBean list : data) {
                                if (TextUtils.equals(String.valueOf(list.getId()), room_type)) {
                                    list.isSelect = true;
                                } else {
                                    list.isSelect = false;
                                }
                            }
                            roomCategoryAdapter.getList_adapter().addAll(data);
                            roomCategoryAdapter.notifyDataSetChanged();
                        LogUtils.debugInfo("更新类型");

                        loadBg();

//                        } else {
//                            List<RoomType.DataBean> data = roomType.getData();
//                            roomCategoryAdapter.getList_adapter().addAll(data);
//                            roomCategoryAdapter.notifyDataSetChanged();
//                        }
                    }
                });

//        roomImgyAdapter.setOnItemClickListener((adapter, view, position) -> {
//            List<RoomBg.DataBean> data = roomImgyAdapter.getData();
//            for (RoomBg.DataBean list : data) {
//                list.isSlect = false;
//            }
//            data.get(position).isSlect = true;
//            roomImgyAdapter.notifyDataSetChanged();
//        });
    }

    private void loadBg() {
        //获取背景图
        RxUtils.loading(commonModel.room_background(""), this)
                .subscribe(new ErrorHandleSubscriber<RoomBg>(mErrorHandler) {
                    @Override
                    public void onNext(RoomBg roomBg) {
                        if (mEnterRoom != null) {
//                            LogUtils.debugInfo("sgm","====之前的type:" + );
                            String back_img = mEnterRoom.getRoom_info().get(0).back_img;
                            List<RoomBg.DataBean> data = roomBg.getData();
                            for (RoomBg.DataBean list : data) {
                                if (TextUtils.equals(String.valueOf(list.getImg()), back_img)) {
                                    list.isSlect = true;
                                } else {
                                    list.isSlect = false;
                                }
                            }
                            roomImgyAdapter.setNewData(roomBg.getData());
                        } else {
                            roomImgyAdapter.setNewData(roomBg.getData());
                        }
                    }
                });
    }


    @OnClick({R.id.llUser, R.id.llgao, R.id.llzhaohu, R.id.rightConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llUser:
                showPop();
                hideKeyboard(llUser);
                break;
            case R.id.llgao:
                String text = "欢迎来到我的房间~,希望你玩的开心~";
                String notice = mEnterRoom.getRoom_info().get(0).getRoom_intro();
                if(!TextUtils.isEmpty(notice)){
                    text = notice;
                }
                Intent intent = new Intent(this, EditGaoActivity.class);
                intent.putExtra("notice_str", text);
                startActivity(intent);
//                ArmsUtils.startActivity(EditGaoActivity.class);
                break;
            case R.id.rightConfirm:
                String name = edtLoginName.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    toast("房间名字不能为空");
                    return;
                }
                String pass = edtPass.getText().toString().trim();
                String roomType = "";
                String room_background = "";
                List<RoomType.DataBean> list_adapter = roomCategoryAdapter.getList_adapter();

                if(list_adapter == null || list_adapter.size() ==0){
                    toast("请退出重新获取数据");
                    return;
                }
                for (RoomType.DataBean list : list_adapter) {
                    if (list.isSelect) {
                        roomType = String.valueOf(list.getId());
                    }
                }

                if(TextUtils.isEmpty(roomType)){
                    toast("请选择房间类型");
                    return;
                }

                List<RoomBg.DataBean> data = roomImgyAdapter.getData();
                for (RoomBg.DataBean list : data) {
                    if (list.isSlect) {
                        room_background = list.getId() + "";
                    }
                }
                LogUtils.debugInfo("====beijing" + room_background);
                if (selImageList.size() > 0) {
                    imgUrl = "data:image/png;base64," + BaseUtils.file2Base64(selImageList.get(0).path);
                } else if (TextUtils.isEmpty(imgUrl)) {
                    imgUrl = "";
                }

                if (TextUtils.isEmpty(name)) {
                    name = "";
                } else if (TextUtils.isEmpty(roomType)) {
                    roomType = "";
                } else if (TextUtils.isEmpty(room_background)) {
//                    showToast("请选择背景！");
                    room_background = "";
                } else if (TextUtils.isEmpty(strGao)) {
//                    showToast("请填写公告！");
                    strGao = "";
                }
                showDialogLoding();
                RxUtils.loading(commonModel.create_or_edit_room(pass, name, roomType,
                        strGao, room_background,
                        isHome, imgUrl), this)
                        .subscribe(new ErrorHandleSubscriber<Register>(mErrorHandler) {
                            @Override
                            public void onNext(Register roomBg) {
                                disDialogLoding();
                                loadRoomData();
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                disDialogLoding();
                            }
                        });
                break;
        }
    }

    /**
     * 重新获取数据给房间
     */
    private void loadRoomData() {
        RxUtils.loading(commonModel.getRoomInfo(isHome), this)
                .subscribe(new ErrorHandleSubscriber<EnterRoom>(mErrorHandler) {
                    @Override
                    public void onNext(EnterRoom enterRoom) {
                        toast("修改成功！");
                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.FANGJIANSHEZHI, enterRoom));
                        if (!TextUtils.isEmpty(isHome)) {
                            startActivity(new Intent(RoomSettingActivity.this, AdminHomeActivity.class));
                            finish();
                        } else {
                            finish();
                        }
                    }
                });
    }


    private void showPop() {
        PhotoWindow photoWindow = new PhotoWindow(this);
        photoWindow.showAtLocation(llUser, Gravity.BOTTOM, 0, 0);
        photoWindow.getTake_photo().setOnClickListener(v -> {
            photoWindow.dismiss();
            if (SdcardTools.sdcard()) {
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions
                        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                //相机
                                Intent intent = new Intent(RoomSettingActivity.this, ImageGridActivity.class);
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
                            Intent intent = new Intent(RoomSettingActivity.this, ImageGridActivity.class);
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
                    GlideArms.with(RoomSettingActivity.this)
                            .load(selImageList.get(0).path)
                            .placeholder(R.drawable.ic_default_image)
                            .error(R.drawable.ic_default_image)
                            .circleCrop()
                            .into(imgUser);
                }
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String msg = event.getMsg();
        if (GOONGGAO.equals(msg)) {
            String tag = event.getTag();
            strGao = tag;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!TextUtils.isEmpty(isHome)) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                startActivity(new Intent(this, AdminHomeActivity.class));
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
