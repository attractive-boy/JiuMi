package com.miniapp.talks.activity.my;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.VipCenterAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.BaseWebActivity;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.AgreementBean;
import com.miniapp.talks.bean.VipCenterBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.popup.VipCenterWindow;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.JudgeImageUtil;
import com.miniapp.talks.view.MyGridView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 会员中心页面
 * 老王
 */

public class MemberCoreActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.head_image)
    CircularImage headImage;
    @BindView(R.id.grade_text)
    TextView gradeText;
    @BindView(R.id.now_grade_image)
    ImageView nowGradeImage;
    @BindView(R.id.next_grade_image)
    ImageView nextGradeImage;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.next_grade_text)
    TextView nextGradeText;
    @BindView(R.id.mygridview)
    MyGridView mygridview;
    @BindView(R.id.qunbu)
    LinearLayout qunbu;

    private VipCenterAdapter mAdapter;
    private TextView nameText, titText;
    private ImageView imageView;

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
        return R.layout.activity_member_core;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mAdapter = new VipCenterAdapter(this);
        mygridview.setAdapter(mAdapter);
        getVipCenter();

        mygridview.setOnItemClickListener((parent, view, position, id) -> {
            Dialog dialog = new Dialog(this, R.style.Transparent);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setWindowAnimations(R.style.pop_anim);
            View view1 = View.inflate(this, R.layout.vip_center_window, null);
            nameText = view1.findViewById(R.id.popu_text);
            imageView = view1.findViewById(R.id.popu_image);
            titText = view1.findViewById(R.id.ohuo);
            window.setContentView(view1);
            window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
            dialog.show();

            nameText.setText(mAdapter.getList_adapter().get(position).getName() + "(" + "LV" + mAdapter.getList_adapter().get(position).getLevel() + "开启" + ")");
            titText.setText(mAdapter.getList_adapter().get(position).getTitle());
            GlideArms.with(this)
                    .load(mAdapter.getList_adapter().get(position).getImg_1())
                    .placeholder(R.mipmap.no_tou)
                    .error(R.mipmap.no_tou)
                    .circleCrop()
                    .into(imageView);

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("会员等级", true);
        setToolbarRightText("等级说明", v -> {
            Intent intent = new Intent(this, DengJiShuoMingActivity.class);
            intent.putExtra("tag", "0");
            ArmsUtils.startActivity(intent);
        }, R.color.textcentercolor);
    }

    private void getVipCenter() {
        RxUtils.loading(commonModel.getVipCenter(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<VipCenterBean>(mErrorHandler) {
                    @Override
                    public void onNext(VipCenterBean vipCenterBean) {
                        ArmsUtils.obtainAppComponentFromContext(MemberCoreActivity.this)
                                .imageLoader()
                                .loadImage(MemberCoreActivity.this, ImageConfigImpl
                                        .builder()
                                        .url(vipCenterBean.getData().getUser().get(0).getHeadimgurl())
                                        .placeholder(R.mipmap.no_tou)
                                        .imageView(headImage)
                                        .errorPic(R.mipmap.no_tou)
                                        .build());
                        int vipNum = (int) Double.parseDouble(vipCenterBean.getData().getUser().get(0).getVip_num());
                        int next_vip_num = vipCenterBean.getData().getUser().get(0).getNext_vip_num();
//                        LogUtils.debugInfo("====数1", vipNum + "");
//                        LogUtils.debugInfo("====数2", next_vip_num + "");
                        gradeText.setText("V值：" + vipNum);
                        JudgeImageUtil.vip(vipCenterBean.getData().getUser().get(0).getVip_level(), nowGradeImage);
                        JudgeImageUtil.vip(vipCenterBean.getData().getUser().get(0).getNext_vip_level(), nextGradeImage);
                        nextGradeText.setText("距离下一级还需要" + (next_vip_num - vipNum) + "V值");
                        progressBar.setMax(vipCenterBean.getData().getUser().get(0).getNext_vip_num());
                        progressBar.setProgress(vipNum);

                        mAdapter.getList_adapter().addAll(vipCenterBean.getData().getAuth());
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void getAgr() {
        RxUtils.loading(commonModel.getAgreement(String.valueOf(2)), this)
                .subscribe(new ErrorHandleSubscriber<AgreementBean>(mErrorHandler) {
                    @Override
                    public void onNext(AgreementBean agreementBean) {
                        Intent intent = new Intent(MemberCoreActivity.this, BaseWebActivity.class);
                        intent.putExtra("url", agreementBean.getData().get(0).getUrl());
                        intent.putExtra("title", agreementBean.getData().get(0).getName());
                        ArmsUtils.startActivity(intent);
                    }
                });
    }

}