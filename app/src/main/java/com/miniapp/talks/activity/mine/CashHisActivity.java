package com.miniapp.talks.activity.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.RankPagerAdapter;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.fragment.CashHisFragment;
import com.miniapp.talks.fragment.MessageFansFragment;
import com.miniapp.talks.fragment.MessageFragment;
import com.miniapp.talks.fragment.MessageFriendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CashHisActivity extends MyBaseArmActivity {

    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<String> titleList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private RankPagerAdapter mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_cash_his;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        titleList.add("兑换");
        titleList.add("提现");
        CashHisFragment messageFansFragment1 = CashHisFragment.getInstance(0);
        CashHisFragment messageFansFragment2 = CashHisFragment.getInstance(1);
        mFragments.add(messageFansFragment1);
        mFragments.add(messageFansFragment2);

        mAdapter = new RankPagerAdapter(getSupportFragmentManager(), mFragments, titleList);

        viewPager.setAdapter(mAdapter);
        tabLayout.setViewPager(viewPager);
        tabLayout.setCurrentTab(0);
        tabLayout.setSnapOnTabClick(true);
        viewPager.setOffscreenPageLimit(mFragments.size());

    }

}
