package com.miniapp.talks.activity.my;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.RankPagerAdapter;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.fragment.MessageFansFragment;
import com.miniapp.talks.service.CommonModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的关注页面(包含我的关注跟我的粉丝两个Fragment)
 * 老王
 */

public class MyFollowActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.my_follow_tablayout)
    TabLayout myFollowTablayout;
    @BindView(R.id.my_follow_viewpager)
    ViewPager myFollowViewpager;

    private List<String> titleList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private RankPagerAdapter mAdapter;
    private int lei=0;

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
        return R.layout.activity_my_follow;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initTabLayout();

        titleList.add("关注");
        titleList.add("粉丝");


        mFragments.add(MessageFansFragment.getInstance(0));
        mFragments.add(MessageFansFragment.getInstance(1));
        myFollowViewpager.setCurrentItem(0);
        mAdapter = new RankPagerAdapter(getSupportFragmentManager(), mFragments, titleList);
        myFollowViewpager.setAdapter(mAdapter);
        myFollowTablayout.setupWithViewPager(myFollowViewpager);
        myFollowViewpager.setOffscreenPageLimit(mFragments.size());
        myFollowTablayout.getTabAt(0).select();
    }

    private void initTabLayout() {
        myFollowTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initUpData(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                initUpData(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initUpData(TabLayout.Tab tab, boolean boo) {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.title_text_layout, null);
        if (boo) {
            textView.setSelected(true);
            textView.setTextSize(16);
            textView.setText(tab.getText());
            tab.setCustomView(textView);
        } else {
            tab.setCustomView(null);
        }
    }
}
