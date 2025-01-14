package com.miniapp.talks.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.MyBaseArmFragment;

/**
 * 修改个人资料
 */
public class ChangeUserActivity extends MyBaseArmActivity {


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_change_user;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

}
