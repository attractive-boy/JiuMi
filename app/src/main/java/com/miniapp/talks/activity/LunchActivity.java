package com.miniapp.talks.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.login.LoginActivity;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.utils.SharedPreferencesUtils;

public class LunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);//恢复原有的样式

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        boolean isFirstIn = (boolean) SharedPreferencesUtils.getParam(this, "isFirstOpen", true);
        if (isFirstIn) {
            SharedPreferencesUtils.setParam(this, "isFirstOpen", false);
            ArmsUtils.startActivity(GuildActivity.class);
            LunchActivity.this.finish();

        } else {
            if (UserManager.IS_LOGIN) {
                ArmsUtils.startActivity(MainActivity.class);
                finish();
            }else{
                ArmsUtils.startActivity(LoginActivity.class);
                LunchActivity.this.finish();
            }
        }
    }
}
