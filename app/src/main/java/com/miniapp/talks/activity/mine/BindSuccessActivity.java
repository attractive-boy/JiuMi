package com.miniapp.talks.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.view.ShapeTextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.miniapp.talks.utils.Constant.JIEBANGALI;
import static com.miniapp.talks.utils.Constant.REGISTER;

/**
 * 绑定成功
 * 老王
 */

public class BindSuccessActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.head_image)
    CircularImage headImage;
    @BindView(R.id.nack_text)
    TextView nackText;
    @BindView(R.id.untying)
    ShapeTextView untying;

    private String head;
    private String name;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_bind_success;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        head = intent.getStringExtra("head");
        name = intent.getStringExtra("name");
        if (!TextUtils.isEmpty(head)) {
            ArmsUtils.obtainAppComponentFromContext(this)
                    .imageLoader()
                    .loadImage(this, ImageConfigImpl
                            .builder()
                            .url(head)
                            .placeholder(R.mipmap.no_tou)
                            .imageView(headImage)
                            .errorPic(R.mipmap.no_tou)
                            .build());
        }
        if (!TextUtils.isEmpty(name)) {
            nackText.setText(name);
        }
    }

    @OnClick(R.id.untying)
    public void onClick() {
        Intent intent = new Intent(this, BingCancelActivity.class);
        intent.putExtra("head", head);
        intent.putExtra("name", name);
        ArmsUtils.startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (JIEBANGALI.equals(tag)) {
            finish();
        }
    }
}
