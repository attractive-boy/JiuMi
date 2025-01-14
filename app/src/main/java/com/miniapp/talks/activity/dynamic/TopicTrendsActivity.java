package com.miniapp.talks.activity.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.lzy.widget.HeaderViewPager;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.MyPagerAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.HeaderViewPagerFragment;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.TopicDynamicBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.fragment.RecomFragment;
import com.miniapp.talks.fragment.TopicFragment;
import com.miniapp.talks.service.CommonModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 作者：老王
 * 描述：某一话题详情页
 */

public class TopicTrendsActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.head_back)
    ImageView headBack;
    @BindView(R.id.read_num)
    TextView readNum;
    @BindView(R.id.discuss_num)
    TextView discussNum;
//    @BindView(R.id.topic_tit)
//    TextView topicTit;
    @BindView(R.id.tab_layout)
SlidingTabLayout tabLayout;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
//    @BindView(R.id.scrollableLayout)
//    HeaderViewPager scrollableLayout;

    private List<String> tabList;
    private List<HeaderViewPagerFragment> fragmentList;
    private MyPagerAdapter myPagerAdapter;
    private String tags, tagsName;
    private LoginData user;
    private int page = 1;

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
        return R.layout.activity_topic_trends;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        tags = intent.getStringExtra("tags");
        tagsName = intent.getStringExtra("tagsName");

        user = UserManager.getUser();
        setTab();

        setHeadBackground(0, "hot");

        initRefreshLayout();
    }

    private void initRefreshLayout() {
        //设置viewpager的页面刷新
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            if(fragmentList !=null && viewPager !=null){
                ((TopicFragment)fragmentList.get(viewPager.getCurrentItem())).onLoadMore(refreshLayout);
            }

        });
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            ((TopicFragment)fragmentList.get(viewPager.getCurrentItem())).onRefresh(refreshLayout);
        });
    }

    //设置TabLayout
    private void setTab() {
        tabList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        tabList.add("热门");
        tabList.add("最新");

        TopicFragment topicFragment1 = TopicFragment.getInstance(1,tags);
        TopicFragment topicFragment2 = TopicFragment.getInstance(2,tags);
        fragmentList.add(topicFragment1);
        fragmentList.add(topicFragment2);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, tabList);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setViewPager(viewPager);
//        tabLayout.setTextBold(0);
//        tabLayout.setCurrentTab(0);
        tabLayout.setSnapOnTabClick(true);
        viewPager.setOffscreenPageLimit(fragmentList.size());

//        scrollableLayout.setCurrentScrollableContainer(fragmentList.get(0));
//        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                scrollableLayout.setCurrentScrollableContainer(fragmentList.get(position));
//            }
//        });
    }

    //设置头部
    private void setHeadBackground(int page, String type) {
        RxUtils.loading(commonModel.topic_dynamic(tags, user.getUserId() + "", page + "", type), this)
                .subscribe(new ErrorHandleSubscriber<TopicDynamicBean>(mErrorHandler) {
                    @Override
                    public void onNext(TopicDynamicBean topicDynamicBean) {
                        TopicDynamicBean.DataBean data = topicDynamicBean.getData();
                        if (!data.getImg().isEmpty() && data.getImg().length() != 0 && data.getImg() != null) {
                            ArmsUtils.obtainAppComponentFromContext(TopicTrendsActivity.this)
                                    .imageLoader()
                                    .loadImage(TopicTrendsActivity.this, ImageConfigImpl
                                            .builder()
                                            .url(topicDynamicBean.getData().getImg())
                                            .placeholder(R.mipmap.default_home)
                                            .imageView(headBack)
                                            .errorPic(R.mipmap.default_home)
                                            .build());
                        }
//                        topicTit.setText(tagsName);
                        readNum.setText("阅读：" + data.getRead_num() + "");
                        discussNum.setText("讨论：" + data.getTalk_num() + "");
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle(tagsName,true);
    }
}
