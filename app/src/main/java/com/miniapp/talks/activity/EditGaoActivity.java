package com.miniapp.talks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.miniapp.talks.utils.Constant.GOONGGAO;


public class EditGaoActivity extends MyBaseArmActivity {


    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rightConfirm)
    ShapeTextView rightConfirm;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editName)
    EditText editName;

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
        return R.layout.activity_edit_gao;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();

        if(intent != null && intent.getExtras() != null){
            String notice = intent.getStringExtra("notice_str");
            if(!TextUtils.isEmpty(notice)){
                editName.setText(notice);
            }
        }
        rightConfirm.setOnClickListener(v -> {
            String str = editName.getText().toString();
            if(TextUtils.isEmpty(str)){
                showToast("请输入内容！");
            }else {
                EventBus.getDefault().post(new FirstEvent(GOONGGAO,str));
                hideKeyboard(editName);
                finish();
            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
