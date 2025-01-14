package com.miniapp.talks.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionOwner;
import com.gyf.immersionbar.components.ImmersionProxy;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.lzy.widget.HeaderViewPager;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.SearchHisActivity;
import com.miniapp.talks.activity.room.CollectionRoomListActivity;
import com.miniapp.talks.activity.room.RankActivity;
import com.miniapp.talks.adapter.MyPagerAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.BaseWebActivity;
import com.miniapp.talks.base.HeaderViewPagerFragment;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.bean.BannerBean;
import com.miniapp.talks.bean.CategorRoomBean;
import com.miniapp.talks.bean.RecommenRoomBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.view.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 作者:sgm
 * 描述:首页
 */
public class MainHomeFragment extends MyBaseArmFragment implements ImmersionOwner {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tab_layout;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    //    @BindView(R.id.scrollableLayout)
//    HeaderViewPager scrollableLayout;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    @BindView(R.id.shoucang_room)
    ImageView shoucangRoom;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.sousuo)
    LinearLayout sousuo;

    private List<String> titleList;
    private List<HeaderViewPagerFragment> mFragments;
    private MyPagerAdapter mAdapter;
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
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_home);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.white), 0);
        //设置viewpager的页面刷新
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            if (view_pager != null && mFragments != null && mFragments.size() > 0) {
                if (view_pager.getCurrentItem() == 0) {
                    ((RecomHomeFragment) mFragments.get(view_pager.getCurrentItem())).onLoadMore(refreshLayout);
                } else {

                    ((RecomFragment) mFragments.get(view_pager.getCurrentItem())).onLoadMore(refreshLayout);
                }
            }

        });
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            if (view_pager != null && mFragments != null && mFragments.size() > 0) {
                if (view_pager.getCurrentItem() == 0) {
                    ((RecomHomeFragment) mFragments.get(view_pager.getCurrentItem())).onRefresh(refreshLayout);
                } else {
                    ((RecomFragment) mFragments.get(view_pager.getCurrentItem())).onRefresh(refreshLayout);
                }
            }
        });
        refreshLayout.setEnableLoadMore(false);
        loadBanner();
        loadCategory();
        imgSearch.setOnClickListener(v -> {
            ArmsUtils.startActivity(RankActivity.class);
//            getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        });

        sousuo.setOnClickListener(v -> {
            ArmsUtils.startActivity(SearchHisActivity.class);
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if(!hidden){
//            StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.white), 0);
//        }
//    }

    /**
     * 初始化顶部Banner图
     */
    public void loadBanner() {
        RxUtils.loading(commonModel.carousel(""), this)
                .subscribe(new ErrorHandleSubscriber<BannerBean>(mErrorHandler) {
                    @Override
                    public void onNext(BannerBean bannerBean) {
                        List<String> imgurls = new ArrayList<>();
                        List<BannerBean.DataBean> data = bannerBean.getData();
                        for (BannerBean.DataBean list : data) {
                            imgurls.add(list.getImg());
                        }
                        //设置图片加载器
                        banner.setImageLoader(new GlideImageLoader());
                        //设置图片集合
                        banner.setImages(imgurls);
                        //设置指示器位置（当banner模式中有指示器时）
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        banner.setIndicatorGravity(BannerConfig.CENTER);
                        //设置自动轮播，默认为true
                        banner.isAutoPlay(true);
                        banner.setOnBannerListener(position -> {
                            Intent intent = new Intent(getActivity(), BaseWebActivity.class);
                            intent.putExtra("url", bannerBean.getData().get(position).url);
                            intent.putExtra("name", "");
                            ArmsUtils.startActivity(intent);
                        });
                        //banner设置方法全部调用完毕时最后调用
                        banner.start();
                    }
                });
    }

    /**
     * 类别
     */
    private void loadCategory() {
        RxUtils.loading(commonModel.room_categories(), this)
                .subscribe(new ErrorHandleSubscriber<CategorRoomBean>(mErrorHandler) {
                    @Override
                    public void onNext(CategorRoomBean categorRoomBean) {
                        mFragments = new ArrayList<>();
                        titleList = new ArrayList<>();
                        List<CategorRoomBean.DataBean> data = categorRoomBean.getData();
                        titleList.add("推荐");
                        for (CategorRoomBean.DataBean list : data) {
                            titleList.add(list.getName());
                        }
                        //推荐的
                        mFragments.add(RecomHomeFragment.getInstance(0, categorRoomBean));
                        for (int i = 0; i < data.size(); i++) {
                            mFragments.add(RecomFragment.getInstance(data.get(i).getId(), categorRoomBean));//todo id改为0显示布局
                        }
                        mAdapter = new MyPagerAdapter(getChildFragmentManager(), mFragments, titleList);

                        view_pager.setAdapter(mAdapter);
                        tab_layout.setViewPager(view_pager);
                        tab_layout.setTextBold(tag);
                        tab_layout.setCurrentTab(tag);
                        tab_layout.setSnapOnTabClick(true);
                        view_pager.setOffscreenPageLimit(mFragments.size());
//                        //滑动监听  切换数据
//                        scrollableLayout.setCurrentScrollableContainer(mFragments.get(0));
                        view_pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                            @Override
                            public void onPageSelected(int position) {
                                if (position == 0) {
                                    refreshLayout.setEnableLoadMore(false);
                                    ((RecomHomeFragment) mFragments.get(position)).setDisableLoadMore(true);
                                } else {
                                    refreshLayout.setEnableLoadMore(true);
                                    ((RecomFragment) mFragments.get(position)).setDisableLoadMore(false);
                                }
//                                scrollableLayout.setCurrentScrollableContainer(mFragments.get(position));
                            }
                        });
//                        loadRecommentData();
                    }
                });
    }


    /**
     * 热门，房间
     */
//    private void loadRecommentData() {
//        //热门推荐
//        RxUtils.loading(commonModel.is_popular(), this)
//                .subscribe(new ErrorHandleSubscriber<RecommenRoomBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(RecommenRoomBean categorRoomBean) {
//                        RecomFragment recomFragment = (RecomFragment) mFragments.get(0);
//                        recomFragment.setPopularData(categorRoomBean);
//                    }
//                });
//        //密聊推荐
//        RxUtils.loading(commonModel.secret_chat(), this)
//                .subscribe(new ErrorHandleSubscriber<RecommenRoomBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(RecommenRoomBean categorRoomBean) {
//                        RecomFragment recomFragment = (RecomFragment) mFragments.get(0);
//                        recomFragment.setSecretData(categorRoomBean);
//                    }
//                });
//    }
    @OnClick({R.id.shoucang_room})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shoucang_room:
                ArmsUtils.startActivity(CollectionRoomListActivity.class);
                break;
//            case R.id.llGift:
//                if (mPushBean != null) {
//                    enterData(mPushBean.getUid() + "", "", commonModel, 1);
//                    llGift.setVisibility(View.GONE);
//                }
//                break;
        }

    }


    /**
     * ImmersionBar代理类
     */
    private ImmersionProxy mImmersionProxy = new ImmersionProxy(this);
    @Override
    public void onLazyBeforeView() {

    }

    @Override
    public void onLazyAfterView() {

    }

    @Override
    public void onVisible() {

    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void initImmersionBar() {

        ImmersionBar.with(this).statusBarColor(R.color.white)
                .autoStatusBarDarkModeEnable(true,0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
                .init();//设置状态栏白色

    }

    @Override
    public void onResume() {
        super.onResume();
        mImmersionProxy.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mImmersionProxy.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mImmersionProxy.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mImmersionProxy.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImmersionProxy.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImmersionProxy.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mImmersionProxy.onHiddenChanged(hidden);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mImmersionProxy.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean immersionBarEnabled() {//是否用沉浸式
        return true;
    }
}
