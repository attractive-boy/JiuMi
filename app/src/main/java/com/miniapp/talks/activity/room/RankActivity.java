package com.miniapp.talks.activity.room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.LogUtils;
import com.lzy.widget.HeaderViewPager;
import com.miniapp.talks.Interface.MyPackBaoShiInter;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.RankPagerAdapter;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.Rank;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.fragment.CardFragment;
import com.miniapp.talks.fragment.GemFragment;
import com.miniapp.talks.fragment.GemstoneFragment;
import com.miniapp.talks.fragment.PresentFragment;
import com.miniapp.talks.fragment.RankFragment;
import com.miniapp.talks.utils.MyUtil;
import com.miniapp.talks.utils.StatusBarUtils;
import com.miniapp.talks.view.HXLinePagerIndicator;
import com.miniapp.talks.view.HXLinePagerIndicatorTwo;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.scwang.smartrefresh.layout.util.SmartUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.miniapp.talks.utils.Constant.QIEHUAN;
import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * 排行榜
 * implements MyPackBaoShiInter.onRankInter
 */
public class RankActivity extends MyBaseArmActivity implements MyPackBaoShiInter.onRankInter {

    //    @BindView(R.id.tab_layout)
//    MagicIndicator tabLayout;
    //    @BindView(R.id.view_pager)
//    ViewPager viewPager;
//    @BindView(R.id.imgBack)
//    ImageView imgBack;
    //    @BindView(R.id.lin)
//    LinearLayout lin;
    @BindView(R.id.scrollableLayout)
    HeaderViewPager scrollableLayout;

    private RelativeLayout bj;
    private LinearLayout lin;
    private ViewPager viewPager;
    private MagicIndicator tabLayout;
    private ImageView imgBack;
    public LinearLayout viewTouMing;
    private static String[] AAA = new String[]{"金锐榜", "星锐榜"};
    private ExamplePagerAdapter mExamplePagerAdapter;
    private int mScrollY = 0;
    private int mOffset = 0;
    private int mImgScrollY = 0;
    private int statusBarHeight;
    private int lastScrollY = 0;
    private int lastImgScrollY = 0;
    private int h = SmartUtil.dp2px(48);
    private int imgHeight = 0;//滑动时图片的最大滑动距离
    private int color = 0;

    RankFragment mRankFragment1, mRankFragment2;


    int mTitleHeight = 0;

    int mCurrentPage = 0;

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
        return R.layout.activity_rank;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        lin = findViewById(R.id.lin);
        bj = findViewById(R.id.layout_bj);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tablayout);
        imgBack = findViewById(R.id.imgBack);
        viewTouMing = findViewById(R.id.view_corner_touming);

        viewTouMing.setFocusableInTouchMode(false);
        statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
        MyUtil.setMargins(lin, 0, statusBarHeight, 0, 0);
        QMUIStatusBarHelper.translucent(this);
        color = ContextCompat.getColor(getApplicationContext(), R.color.color_daa1e1) & 0x00ffffff;

        initFragment();

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

                                mCurrentPage = 0;
                                mTitleHeight = mRankFragment1.mTitleLayoutHeight;
                                titleText.setText("金锐榜");
                                titleText.setTextSize(24);
                                break;
                            case 1:

                                mCurrentPage = 1;
                                mTitleHeight = mRankFragment2.mTitleLayoutHeight;
                                titleText.setText("星锐榜");
                                titleText.setTextSize(24);
                                break;
                        }
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        switch (index) {
                            case 0:
                                titleText.setText("金锐榜");
                                titleText.setTextSize(15);
                                break;
                            case 1:
                                titleText.setText("星锐榜");
                                titleText.setTextSize(15);
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
                HXLinePagerIndicatorTwo indicator = new HXLinePagerIndicatorTwo(context);
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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                viewTouMing.setVisibility(View.GONE);
                if (i == 0) {
                    mRankFragment1.myList.smoothScrollToPosition(0);
                    lin.setBackgroundColor((0) | color);
                    bj.setBackgroundResource(R.mipmap.home_phb_bg);
                    bj.setTranslationY(-statusBarHeight);
                } else if (i == 1) {
                    mRankFragment2.myList.smoothScrollToPosition(0);
                    lin.setBackgroundColor((0) | color);
                    bj.setBackgroundResource(R.mipmap.home_phb_bg);
                    bj.setTranslationY(-statusBarHeight);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        imgBack.setOnClickListener(v -> {
//            startActivity(new Intent(this, AdminHomeActivity.class));
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
            finish();
        });

        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        bj.measure(w, h);
        imgHeight = bj.getMeasuredHeight();
    }

    private void initFragment() {

        mRankFragment1 = RankFragment.getInstance(2, 1, statusBarHeight);

        mRankFragment1.setmOnPageChangeLister(RankActivity.this);

        mRankFragment2 = RankFragment.getInstance(1, 2, statusBarHeight);

        mRankFragment2.setmOnPageChangeLister(RankActivity.this);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            startActivity(new Intent(this, AdminHomeActivity.class));
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void OnRankInter(int distance) {
//        LogUtils.debugInfo("distance======"+distance);
        int titleY = distance;
        int imgY = distance;
        if (lastScrollY < h) {
            titleY = Math.min(h, titleY);
            mScrollY = titleY > h ? h : titleY;
            lin.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
            setThemeColor(((255 * mScrollY / h) << 24) | color);
        }
        lastScrollY = titleY;
        if (lastImgScrollY < imgHeight) {
            imgY = Math.min(imgHeight, imgY);
            mImgScrollY = imgY > imgHeight ? imgHeight : imgY;
            if (bj != null) {
                int scrollY = -(mOffset - mImgScrollY - statusBarHeight);
//                int headHeight = mTitleHeight + statusBarHeight + MyUtil.dip2px(this, 46);
//                if (scrollY >= (imgHeight - headHeight)) {
                if (scrollY >= (mRankFragment1.shengHeight + statusBarHeight - MyUtil.dip2px(this, 5))) {
                    bj.setTranslationY(-(mRankFragment1.shengHeight + statusBarHeight + MyUtil.dip2px(this, 15)));
//                        bj.setTranslationY(-(imgHeight - headHeight));
                    viewTouMing.setVisibility(View.VISIBLE);
                    if (mCurrentPage == 0) {
                        mRankFragment1.setItemBg(false);
                    } else {
                        mRankFragment2.setItemBg(false);
                    }
                } else {
                    bj.setTranslationY(-scrollY);
                    viewTouMing.setVisibility(View.GONE);
                    if (mCurrentPage == 0) {
                        mRankFragment1.setItemBg(true);
                    } else {
                        mRankFragment2.setItemBg(true);
                    }
                }
//                xiabu.setTranslationY(mOffset - mImgScrollY - statusBarHeight);
            } else {
            }
        }
        lastImgScrollY = imgY;
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
//                    RankFragment fragment1 = RankFragment.getInstance(2, 1);
//                    fragment1.setmOnPageChangeLister(RankActivity.this);
                    fragment = mRankFragment1;
                    break;
                case 1:
//                    RankFragment fragment2 = RankFragment.getInstance(1, 2);
//                    fragment2.setmOnPageChangeLister(RankActivity.this);
                    fragment = mRankFragment2;
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
