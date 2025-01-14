package com.miniapp.talks.activity.message;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.ReportMessageAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.Register;
import com.miniapp.talks.bean.ReportBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.BaseUtils;
import com.miniapp.talks.utils.PhotoWindow;
import com.miniapp.talks.utils.SdcardTools;
import com.miniapp.talks.view.ShapeTextView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.REGISTER;

/**
 * 举报
 */
public class ReportActivity extends MyBaseArmActivity {


    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rightTitle)
    TextView rightTitle;
    @BindView(R.id.rightConfirm)
    ShapeTextView rightConfirm;
    @BindView(R.id.toolbar_right)
    RelativeLayout toolbarRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.imgAdd)
    ImageView imgAdd;

    @Inject
    CommonModel commonModel;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;
    private ReportMessageAdapter reportMessageAdapter;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ArrayList<ImageItem> selImageList = new ArrayList<>();
    private String targetId = "";
    private String type = "";//1用户2房间3动态
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_report;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        targetId = getIntent().getStringExtra("targetId");
        type = getIntent().getStringExtra("type");
        reportMessageAdapter = new ReportMessageAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(reportMessageAdapter);
        loadData();

        reportMessageAdapter.setOnItemClickListener((adapter, view, position) -> {
            if(reportMessageAdapter.getData().get(position).isSelct){
                reportMessageAdapter.getData().get(position).isSelct = false;
                reportMessageAdapter.notifyItemChanged(position);
            }else {
                reportMessageAdapter.getData().get(position).isSelct = true;
                reportMessageAdapter.notifyItemChanged(position);
            }
        });
    }

    private void loadData() {
        RxUtils.loading(commonModel.report(null), this)
                .subscribe(new ErrorHandleSubscriber<ReportBean>(mErrorHandler) {
                    @Override
                    public void onNext(ReportBean reportBean) {
                        reportMessageAdapter.setNewData(reportBean.getData());
                    }
                });
    }

    @OnClick({R.id.imgAdd,R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgAdd:
                showPop();
                break;
            case R.id.btn_ok:
                StringBuilder stringBuilder = new StringBuilder();
                List<ReportBean.DataBean> data = reportMessageAdapter.getData();
                List<ReportBean.DataBean> listNew = new ArrayList<>();
                for (ReportBean.DataBean list :data){
                    if (list.isSelct){
                        stringBuilder.append(list.getId() + ",");
                    }
                }
                if(TextUtils.isEmpty(stringBuilder.toString())){
                    showToast("请选择举报类型！");
                    return;
                }
                String str = stringBuilder.substring(0,stringBuilder.length() - 1);
                if(selImageList.size() == 0){
                    showToast("请选择图片！");
                }else {
                    RxUtils.loading(commonModel.send_report(String.valueOf(UserManager.getUser().getUserId()),
                            "data:image/png;base64," + BaseUtils.file2Base64(selImageList.get(0).path),
                            type,targetId,str), this)
                            .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                @Override
                                public void onNext(BaseBean codeBean) {
                                    finish();
                                    toast("举报成功！");
                                }

                            });
                }
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void showPop() {
        PhotoWindow photoWindow = new PhotoWindow(this);
        photoWindow.showAtLocation(imgAdd, Gravity.BOTTOM, 0, 0);
        photoWindow.getTake_photo().setOnClickListener(v -> {
            photoWindow.dismiss();
            if (SdcardTools.sdcard()) {
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions
                        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                //相机
                                Intent intent = new Intent(ReportActivity.this, ImageGridActivity.class);
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
                            Intent intent = new Intent(ReportActivity.this, ImageGridActivity.class);
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
                    GlideArms.with(ReportActivity.this)
                            .load(selImageList.get(0).path)
                            .placeholder(R.drawable.ic_default_image)
                            .error(R.drawable.ic_default_image)
                            .into(imgAdd);
                }
            }
        }
    }

}
