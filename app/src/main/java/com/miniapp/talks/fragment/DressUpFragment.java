package com.miniapp.talks.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.Interface.MyPackBaoShiInter;
import com.miniapp.talks.R;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.MyPackBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.view.HXLinePagerIndicator;
import com.miniapp.talks.view.ShapeTextView;

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
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.QIEHUAN;
import static com.miniapp.talks.utils.Constant.QIEHUANTWO;
import static com.miniapp.talks.utils.Constant.QUXIAOHUANYINGKUANG;
import static com.miniapp.talks.utils.Constant.QUXIAOLIAOTIANKUANG;
import static com.miniapp.talks.utils.Constant.QUXIAOYUYINKUANG;
import static com.miniapp.talks.utils.Constant.QUXIAOZHUANGBANTOUXIANG;
import static com.miniapp.talks.utils.Constant.TOUXIANGKUANGXIAOSHI;
import static com.miniapp.talks.utils.Constant.XIANYUXIAO;
import static com.miniapp.talks.utils.Constant.ZHUANGBANCHENGGONGTOUXIANG;
import static com.miniapp.talks.utils.Constant.ZHUANGBANHUANYINGBIAOQIAN;
import static com.miniapp.talks.utils.Constant.ZHUANGBANLIAOTIANKUANG;
import static com.miniapp.talks.utils.Constant.ZHUANGBANYUYINKUANG;

/**
 * 我的背包-装扮
 */
public class DressUpFragment extends MyBaseArmFragment implements MyPackBaoShiInter.onListenerTwo {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.tablayout)
    MagicIndicator tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.monomer_tips)
    RelativeLayout monomerTips;
    @BindView(R.id.pack_name_text)
    TextView packName;
    @BindView(R.id.pack_time_text)
    TextView packTime;
    @BindView(R.id.pack_num_text)
    TextView packNum;
    @BindView(R.id.pack_money_text)
    TextView packMoney;
    @BindView(R.id.ok_btn)
    ShapeTextView okBtn;

    private static int[] AAA = new int[]{R.mipmap.bag_zs_wxztxk, R.mipmap.bag_zs_wxzltk, R.mipmap.bag_zs_wxzjctx, R.mipmap.bag_zs_wxzmsgq};
    private ExamplePagerAdapter mExamplePagerAdapter;
    private MyPackBean.DataBean dataBean; //传过来的值
    private int idf; //记录从那个页面传的值

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dressup, null);
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    public static DressUpFragment getInstance() {
        DressUpFragment fragment = new DressUpFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", "DressUpFragment");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mExamplePagerAdapter = new ExamplePagerAdapter(getChildFragmentManager());
        viewpager.setAdapter(mExamplePagerAdapter);
        tablayout.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
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
                View customLayout = LayoutInflater.from(context).inflate(R.layout.simple_pager_title_layout, null);
                final ImageView titleImg = customLayout.findViewById(R.id.imageview);
                titleImg.setImageResource(AAA[index]);
                commonPagerTitleView.setContentView(customLayout);

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int index, int totalCount) {
                        if (monomerTips.getVisibility() == View.VISIBLE) {
                            monomerTips.setVisibility(View.GONE);
                        }
                        EventBus.getDefault().post(new FirstEvent("头像框消失", TOUXIANGKUANGXIAOSHI));
                        switch (index) {
                            case 0:
                                titleImg.setImageResource(R.mipmap.bag_zs_xztxk);
                                EventBus.getDefault().post(new FirstEvent("1", QIEHUANTWO));
                                break;
                            case 1:
                                titleImg.setImageResource(R.mipmap.bag_zs_xzltk);
                                EventBus.getDefault().post(new FirstEvent("2", QIEHUANTWO));
                                break;
                            case 2:
                                titleImg.setImageResource(R.mipmap.bag_zs_xzjctx);
                                EventBus.getDefault().post(new FirstEvent("3", QIEHUANTWO));
                                break;
                            case 3:
                                titleImg.setImageResource(R.mipmap.bag_zs_xzmsgq);
                                EventBus.getDefault().post(new FirstEvent("4", QIEHUANTWO));
                                break;
                        }
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        switch (index) {
                            case 0:
                                titleImg.setImageResource(R.mipmap.bag_zs_wxztxk);
                                break;
                            case 1:
                                titleImg.setImageResource(R.mipmap.bag_zs_wxzltk);
                                break;
                            case 2:
                                titleImg.setImageResource(R.mipmap.bag_zs_wxzjctx);
                                break;
                            case 3:
                                titleImg.setImageResource(R.mipmap.bag_zs_wxzmsgq);
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
                        viewpager.setCurrentItem(index);
                    }
                });

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                HXLinePagerIndicator indicator = new HXLinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 25));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));

                return indicator;
            }
        });

        tablayout.setNavigator(commonNavigator);

        ViewPagerHelper.bind(tablayout, viewpager);
        viewpager.setOffscreenPageLimit(4);

        okBtn.setOnClickListener(v -> {
            LogUtils.debugInfo("====是否已装扮", dataBean.getIs_dress() + "");
            //装扮
            if (dataBean.getIs_dress() == 0) {
                setZhuangBan(String.valueOf(dataBean.getTarget_id()));
            } else if (dataBean.getIs_dress() == 1) {
                setQuXiaoZB(String.valueOf(dataBean.getTarget_id()));
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

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
                    BeiBaoHeadKuangFragment beiBaoHeadKuangFragment = new BeiBaoHeadKuangFragment();
                    beiBaoHeadKuangFragment.setmOnPageChangeLister(DressUpFragment.this);
                    fragment = beiBaoHeadKuangFragment;
                    break;
                case 1:
                    BeiBaoTalkKuangFragment beiBaoTalkKuangFragment = new BeiBaoTalkKuangFragment();
                    beiBaoTalkKuangFragment.setmOnPageChangeLister(DressUpFragment.this);
                    fragment = beiBaoTalkKuangFragment;
                    break;
                case 2:
                    BeiBaoIntoSEFragment beiBaoIntoSEFragment = new BeiBaoIntoSEFragment();
                    beiBaoIntoSEFragment.setmOnPageChangeLister(DressUpFragment.this);
                    fragment = beiBaoIntoSEFragment;
                    break;
                case 3:
                    BeiBaoTalkapertureFragment beiBaoTalkapertureFragment = new BeiBaoTalkapertureFragment();
                    beiBaoTalkapertureFragment.setmOnPageChangeLister(DressUpFragment.this);
                    fragment = beiBaoTalkapertureFragment;
                    break;
            }
            return fragment;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }


    }

    //懒加载
    @Override
    protected void visibleToLoadData() {
        super.visibleToLoadData();
    }

    @Override
    public void OnListener(MyPackBean.DataBean bean, int tag, int type) {
        idf = type;
        dataBean = bean;
        monomerTips.setVisibility(View.VISIBLE);
        okBtn.setVisibility(View.VISIBLE);
        packNum.setVisibility(View.GONE);
        packMoney.setVisibility(View.GONE);
        packTime.setVisibility(View.VISIBLE);
        packName.setText(bean.getName());
        packTime.setText(bean.getTitle());
        if (bean.getIs_dress() == 0) {
            okBtn.setText("装扮");
        } else {
            okBtn.setText("取消装扮");
        }

    }

    private void setQuXiaoZB(String id) {
        RxUtils.loading(commonModel.dress_up(id, "2"), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        showToast(commentBean.getMessage());
                        okBtn.setText("装扮");
                        dataBean.setIs_dress(0);
                        if (idf == 1) {
                            EventBus.getDefault().post(new FirstEvent(dataBean, QUXIAOZHUANGBANTOUXIANG));
                        } else if (idf == 2) {
                            EventBus.getDefault().post(new FirstEvent(dataBean, QUXIAOLIAOTIANKUANG));
                        } else if (idf == 3) {
                            EventBus.getDefault().post(new FirstEvent(dataBean, QUXIAOHUANYINGKUANG));
                        } else if (idf == 4) {
                            EventBus.getDefault().post(new FirstEvent(dataBean, QUXIAOYUYINKUANG));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        showToast(t.getMessage());
                    }
                });
    }

    //设置装扮
    private void setZhuangBan(String id) {
        RxUtils.loading(commonModel.dress_up(id, "1"), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        showToast(commentBean.getMessage());
                        okBtn.setText("取消装扮");
                        dataBean.setIs_dress(1);
                        if (idf == 1) {
                            EventBus.getDefault().post(new FirstEvent(dataBean, ZHUANGBANCHENGGONGTOUXIANG));
                        } else if (idf == 2) {
                            EventBus.getDefault().post(new FirstEvent(dataBean, ZHUANGBANLIAOTIANKUANG));
                        } else if (idf == 3) {
                            EventBus.getDefault().post(new FirstEvent(dataBean, ZHUANGBANHUANYINGBIAOQIAN));
                        } else if (idf == 4) {
                            EventBus.getDefault().post(new FirstEvent(dataBean, ZHUANGBANYUYINKUANG));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        showToast("装扮失败");
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (XIANYUXIAO.equals(tag)) {
            monomerTips.setVisibility(View.GONE);
        }
    }
}