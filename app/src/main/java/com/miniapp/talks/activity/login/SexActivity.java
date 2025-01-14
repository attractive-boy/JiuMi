package com.miniapp.talks.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

import static com.miniapp.talks.utils.Constant.REGISTER;

public class SexActivity extends MyBaseArmActivity {


    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.img3)
    ImageView img3;

    String phone;
    String password;
    String type;
    String uid;
    String tag;
    String coad;

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
        return R.layout.activity_sex;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        phone = getIntent().getStringExtra("phone");
        password = getIntent().getStringExtra("password");
        type = getIntent().getStringExtra("type");
        uid = getIntent().getStringExtra("uid");
        tag = getIntent().getStringExtra("tag");
        coad = getIntent().getStringExtra("coad");
    }


    @OnClick({R.id.img1, R.id.img3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img1:
                new MaterialDialog.Builder(this)
                        .title("")
                        .content("性别一经确认无法修改哦~")
                        .positiveText("确认")
                        .negativeText("我再想想")
                        .onPositive((dialog, which) -> {
                            Intent intent = new Intent(SexActivity.this, UploadActivity.class);
                            intent.putExtra("sex", "1");
                            intent.putExtra("phone", phone);
                            intent.putExtra("password", password);
                            intent.putExtra("type", type);
                            intent.putExtra("tag", tag);
                            intent.putExtra("uid", uid);
                            intent.putExtra("coad",coad);
                            ArmsUtils.startActivity(SexActivity.this, intent);
                        })
                        .show();
                if (img2.getVisibility() == View.VISIBLE) {

                } else {
                    img2.setVisibility(View.VISIBLE);
                    img4.setVisibility(View.GONE);
                }
                break;
            case R.id.img3:
                new MaterialDialog.Builder(this)
                        .title("")
                        .content("性别一经确认无法修改哦~")
                        .positiveText("确认")
                        .negativeText("我再想想")
                        .onPositive((dialog, which) -> {
                            Intent intent = new Intent(SexActivity.this, UploadActivity.class);
                            intent.putExtra("sex", "2");
                            intent.putExtra("phone", phone);
                            intent.putExtra("password", password);
                            intent.putExtra("type", type);
                            intent.putExtra("tag", tag);
                            intent.putExtra("uid", uid);
                            intent.putExtra("coad",coad);
                            ArmsUtils.startActivity(SexActivity.this, intent);
                        })
                        .show();
                if (img4.getVisibility() == View.VISIBLE) {

                } else {
                    img2.setVisibility(View.GONE);
                    img4.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (REGISTER.equals(tag)) {
            finish();
        }
    }
}
