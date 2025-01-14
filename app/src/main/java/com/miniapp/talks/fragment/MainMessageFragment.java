package com.miniapp.talks.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.RankPagerAdapter;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.utils.Constant;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者:sgm
 * 描述:消息
 */
public class MainMessageFragment extends MyBaseArmFragment {
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<String> titleList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private RankPagerAdapter mAdapter;
    private int tag = 0;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_message);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        titleList.add("聊天");
        titleList.add("好友");
//        titleList.add("关注");
//        titleList.add("粉丝");
        MessageFragment messageFragment = new MessageFragment();
        MessageFriendFragment messageFriendFragment = new MessageFriendFragment();
//        MessageFansFragment messageFansFragment1 = MessageFansFragment.getInstance(0);
//        MessageFansFragment messageFansFragment2 =MessageFansFragment.getInstance(1);
        mFragments.add(messageFragment);
        mFragments.add(messageFriendFragment);
//        mFragments.add(messageFansFragment1);
//        mFragments.add(messageFansFragment2);

        mAdapter = new RankPagerAdapter(getChildFragmentManager(), mFragments, titleList);

        viewPager.setAdapter(mAdapter);
        tabLayout.setViewPager(viewPager);
        tabLayout.setCurrentTab(tag);
        tabLayout.setSnapOnTabClick(true);
        viewPager.setOffscreenPageLimit(mFragments.size());

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                LogUtils.debugInfo("sgm","====切换了：" + i);
                if(i == 1){
                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.SHUAXINPENGYOU));
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

}
