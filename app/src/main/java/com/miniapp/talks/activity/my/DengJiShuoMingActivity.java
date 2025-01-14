package com.miniapp.talks.activity.my;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseArmActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DengJiShuoMingActivity extends MyBaseArmActivity {
    @BindView(R.id.image_one)
    ImageView imageOne;
    @BindView(R.id.image_two)
    ImageView imageTwo;

    private String tag;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_deng_ji_shuo_ming;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        tag = getIntent().getStringExtra("tag");
        if (tag.equals("0")) {
            imageTwo.setVisibility(View.GONE);
            setTitle("等级说明");
            setDengjiOne();
        } else if (tag.equals("1")) {
            setTitle("等级说明");
            setDengjiTwo();
        } else if (tag.equals("2")) {
            imageTwo.setVisibility(View.GONE);
            setTitle("守护CP规则");
            setCPGuiZe();
        }
    }

    private void setDengjiOne() {
        imageOne.setImageResource(R.mipmap.vip);
    }

    private void setDengjiTwo() {
        imageOne.setImageResource(R.mipmap.jrxr);
//        imageTwo.setImageResource(R.mipmap.xr);
    }

    private void setCPGuiZe() {
        imageOne.setImageResource(R.mipmap.cp_guize);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        setToolbarTitle("等级说明", true);
//    }
}
