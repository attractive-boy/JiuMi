package com.miniapp.talks.activity.room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.RankPagerAdapter;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.fragment.RankFragment;
import com.miniapp.talks.fragment.RoomRankFragment;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.MyUtil;
import com.miniapp.talks.utils.StatusBarUtils;
import com.miniapp.talks.view.HXLinePagerIndicatorThree;
import com.miniapp.talks.view.HXLinePagerIndicatorTwo;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.umeng.socialize.utils.ContextUtil.getContext;

public class RoomRankActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.room_rank_bj)
    ImageView roomRankBj;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tab_layout)
    MagicIndicator tabLayout;
    @BindView(R.id.room_rank_tit)
    LinearLayout roomRankTit;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private static String[] AAA = new String[]{"贡献榜", "房间榜","星锐榜"}; //tab数据源
    private ExamplePagerAdapter mExamplePagerAdapter; //适配器
    private String imatgeurl;
    private int uid;
    private int statusBarHeight; //导航栏的高度

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_room_rank;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        uid = getIntent().getIntExtra("id", 0);
        imatgeurl = getIntent().getStringExtra("image");

        statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
        MyUtil.setMargins(roomRankTit, 0, statusBarHeight, 0, 0);
        QMUIStatusBarHelper.translucent(this);

        mExamplePagerAdapter = new ExamplePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mExamplePagerAdapter);

        tabLayout.setBackgroundColor(Color.TRANSPARENT);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setRightPadding(50);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return AAA == null ? 0 : AAA.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                View customLayout = LayoutInflater.from(context).inflate(R.layout.title_text_big_layout, null);
                final TextView titleText = customLayout.findViewById(R.id.textView_tit);
                titleText.setText(AAA[index]);
                commonPagerTitleView.setContentView(customLayout);
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int index, int totalCount) {

                        switch (index) {
                            case 0:
                                titleText.setText("贡献榜");
                                titleText.setTextSize(24);
                                titleText.setTextColor(getResources().getColor(R.color.color_FFDC17));
                                break;
                            case 1:
                                titleText.setText("房间榜");
                                titleText.setTextSize(24);
                                titleText.setTextColor(getResources().getColor(R.color.color_FFDC17));
                                break;
                            case 2:
                                titleText.setText("星锐榜");
                                titleText.setTextSize(24);
                                titleText.setTextColor(getResources().getColor(R.color.color_FFDC17));
                                break;
                        }
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        switch (index) {
                            case 0:
                                titleText.setText("贡献榜");
                                titleText.setTextSize(15);
                                titleText.setTextColor(getResources().getColor(R.color.white));
                                break;
                            case 1:
                                titleText.setText("房间榜");
                                titleText.setTextSize(15);
                                titleText.setTextColor(getResources().getColor(R.color.white));
                                break;
                            case 2:
                                titleText.setText("星锐榜");
                                titleText.setTextSize(15);
                                titleText.setTextColor(getResources().getColor(R.color.white));
                                break;
                        }
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {

                    }
                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                HXLinePagerIndicatorThree indicator = new HXLinePagerIndicatorThree(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 35));
                indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));

                return indicator;
            }
        });
        tabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tabLayout, viewPager);
        viewPager.setOffscreenPageLimit(2);

        loadImage(roomRankBj, imatgeurl, 0);
        imgBack.setOnClickListener(v -> {
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
            finish();
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            startActivity(new Intent(this, AdminHomeActivity.class));
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class ExamplePagerAdapter extends FragmentPagerAdapter {

        public ExamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount() {
            return AAA == null ? 0 : AAA.length;
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            switch (i) {
                case 0:
                    RoomRankFragment roomRankFragment1 = RoomRankFragment.getInstance(1, 1, String.valueOf(uid));
                    fragment = roomRankFragment1;
                    break;
                case 1:
                    RoomRankFragment roomRankFragment2 = RoomRankFragment.getInstance(2, 2, String.valueOf(uid));
                    fragment = roomRankFragment2;
                    break;
                case 2:
                    RoomRankFragment roomRankFragment3 = RoomRankFragment.getInstance(3, 3, String.valueOf(uid));
                    fragment = roomRankFragment3;
                    break;
            }
            return fragment;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return AAA[position];
        }
    }
}
