package com.miniapp.talks.activity.my;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.MyPackBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.fragment.DressUpFragment;
import com.miniapp.talks.fragment.GemstoneFragment;
import com.miniapp.talks.utils.ActivityUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.miniapp.talks.utils.Constant.PACKFANHUI;
import static com.miniapp.talks.utils.Constant.TOUXIANGKUANGXIAOSHI;
import static com.miniapp.talks.utils.Constant.XIANYUXIAO;
import static com.miniapp.talks.utils.Constant.YULANTOUXIANGKUANG;

/**
 * 我的背包
 */
public class MyPackageActivity extends MyBaseArmActivity {

    @BindView(R.id.layout_content_package)
    FrameLayout mLayoutContentPackage;
    @BindView(R.id.box_gift)
    TextView boxGift;
    @BindView(R.id.decorate)
    TextView decorate;
    @BindView(R.id.top_btn)
    RelativeLayout topBtn;
    @BindView(R.id.head_image)
    CircularImage headImage;
    @BindView(R.id.head_image_kuang)
    ImageView headImageKuang;

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
        return R.layout.activity_my_package;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("我的背包");
        loadImage(headImage, getIntent().getStringExtra("url"),
                R.mipmap.no_tou);
        boxGift.setSelected(true);
        ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(), GemstoneFragment.getInstance(), R.id.layout_content_package);
    }


    @OnClick({R.id.box_gift, R.id.decorate, R.id.top_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.box_gift:
                boxGift.setSelected(true);
                decorate.setSelected(false);
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(), GemstoneFragment.getInstance(), R.id.layout_content_package);
                headImageKuang.setVisibility(View.INVISIBLE);
                break;
            case R.id.decorate:
                boxGift.setSelected(false);
                decorate.setSelected(true);
                ActivityUtils.addOrShowFragmentToActivity(getSupportFragmentManager(), DressUpFragment.getInstance(), R.id.layout_content_package);
                headImageKuang.setVisibility(View.INVISIBLE);
                break;
            case R.id.top_btn:
                EventBus.getDefault().post(new FirstEvent("点击成功", XIANYUXIAO));
                headImageKuang.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (YULANTOUXIANGKUANG.equals(tag)) {
            headImageKuang.setVisibility(View.VISIBLE);
            ArmsUtils.obtainAppComponentFromContext(this)
                    .imageLoader()
                    .loadImage(this, ImageConfigImpl
                            .builder()
                            .url(event.getDataBean().getShow_img())
                            .placeholder(R.mipmap.no_tu)
                            .imageView(headImageKuang)
                            .errorPic(R.mipmap.no_tu)
                            .build());
        } else if (TOUXIANGKUANGXIAOSHI.equals(tag)) {
            headImageKuang.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().post(new FirstEvent("从背包返回",PACKFANHUI));
        super.onDestroy();
    }
}
