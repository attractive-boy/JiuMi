package com.miniapp.talks.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.SearchDynamicActivity;
import com.miniapp.talks.activity.SearchHisActivity;
import com.miniapp.talks.activity.dynamic.DynamicNewsActivity;
import com.miniapp.talks.activity.dynamic.SocialReleaseActivity;
import com.miniapp.talks.adapter.RankPagerAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.UnreadBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.FABUCHENGGONG;
import static com.miniapp.talks.utils.Constant.XIUGAIGERENZILIAOCHENGGONG;

/**
 * 作者:sgm
 * 描述:动态首页
 */
public class MainCommunityFragment extends MyBaseArmFragment {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.community_search)
    ImageView communitySearch;
    @BindView(R.id.community_tab_layout)
    TabLayout communityTabLayout;
    @BindView(R.id.community_news)
    ImageView communityNews;
    @BindView(R.id.community_iew_pager)
    ViewPager communityIewPager;
    @BindView(R.id.float_button)
    FloatingActionButton floatButton;
    @BindView(R.id.tishi)
    CircularImage tishi;

    private List<String> titleList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private RankPagerAdapter mAdapter;
    private int tag = 0;
    private LoginData user;
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
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_community);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

//        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.white), 0);

        user = UserManager.getUser();

        initTabLayout();

        titleList.add("最新");
        titleList.add("推荐");
        titleList.add("关注");

        NewestDynamicFragment commFragment1 = NewestDynamicFragment.getInstance();
        FollowDynamicFragment commFragment3 = FollowDynamicFragment.getInstance();
        CommFragment commFragment2 = CommFragment.getInstance();
        mFragments.add(commFragment1);
        mFragments.add(commFragment2);
        mFragments.add(commFragment3);
        mAdapter = new RankPagerAdapter(getChildFragmentManager(), mFragments, titleList);
        communityIewPager.setAdapter(mAdapter);
        communityTabLayout.setupWithViewPager(communityIewPager);
//        communityIewPager.setOffscreenPageLimit(mFragments.size());
        communityIewPager.setCurrentItem(1);
        communityTabLayout.getTabAt(1).select();
        communityIewPager.setOffscreenPageLimit(mFragments.size());


    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden && getActivity() != null) {
//            StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.white), 0);
//        }
//    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @OnClick({R.id.community_search, R.id.community_news, R.id.float_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.community_search:
                ArmsUtils.startActivity(SearchHisActivity.class);
                break;
            case R.id.community_news:
                ArmsUtils.startActivity(DynamicNewsActivity.class);
                break;
            case R.id.float_button:
                ArmsUtils.startActivity(SocialReleaseActivity.class);
                break;
        }
    }

    private void initTabLayout() {
        communityTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.title_text_layout, null);
        if (boo) {
            textView.setSelected(true);
            textView.setText(tab.getText());
            tab.setCustomView(textView);
        } else {
            tab.setCustomView(null);
        }
    }

    //获取未读消息 total 0 没有未读 其他有未读，并是未读的信息条数
    private void getUnreadMessage() {
        RxUtils.loading(commonModel.getUnreadMessage(String.valueOf(user.getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<UnreadBean>(mErrorHandler) {
                    @Override
                    public void onNext(UnreadBean unreadBean) {
                        UnreadBean.DataBean data = unreadBean.getData();
                        if (data.getTotal() == 0) {
                            tishi.setVisibility(View.GONE);
                        } else {
                            tishi.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getUnreadMessage();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (FABUCHENGGONG.equals(tag)) {
            communityTabLayout.getTabAt(0).select();
            communityIewPager.setCurrentItem(1);
        }
    }
}
