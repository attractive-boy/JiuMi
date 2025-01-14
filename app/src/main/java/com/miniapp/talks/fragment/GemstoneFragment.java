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
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.v3.MessageDialog;
import com.miniapp.talks.Interface.MyPackBaoShiInter;
import com.miniapp.talks.R;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.MyPackBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.popup.PuTongWindow;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.AToast;
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
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.DUIHUANCHENGGONG;
import static com.miniapp.talks.utils.Constant.KBXTUISONG;
import static com.miniapp.talks.utils.Constant.QIEHUAN;
import static com.miniapp.talks.utils.Constant.QIEHUANTWO;
import static com.miniapp.talks.utils.Constant.XIANYUXIAO;

/**
 * 我的背包-宝石礼物
 */
public class GemstoneFragment extends MyBaseArmFragment implements MyPackBaoShiInter.onListener {
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
    @BindView(R.id.ok_btn_two)
    ShapeTextView okBtnTwo;

    private static int[] AAA = new int[]{R.mipmap.bag_bxlw_wxzbs, R.mipmap.bag_bxlw_wxzlw, R.mipmap.bag_bxlw_wxzkq};
    private ExamplePagerAdapter mExamplePagerAdapter;

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gemstone, null);
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    public static GemstoneFragment getInstance() {
        GemstoneFragment fragment = new GemstoneFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", "GemstoneFragment");
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
                        switch (index) {
                            case 0:
                                titleImg.setImageResource(R.mipmap.bag_bxlw_xzbs);
                                EventBus.getDefault().post(new FirstEvent("1", QIEHUAN));
                                break;
                            case 1:
                                titleImg.setImageResource(R.mipmap.bag_bxlw_xzlw);
                                EventBus.getDefault().post(new FirstEvent("2", QIEHUAN));
                                break;
                            case 2:
                                titleImg.setImageResource(R.mipmap.bag_bxlw_xzkq);
                                EventBus.getDefault().post(new FirstEvent("3", QIEHUAN));
                                break;
                        }
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        switch (index) {
                            case 0:
                                titleImg.setImageResource(R.mipmap.bag_bxlw_wxzbs);
                                break;
                            case 1:
                                titleImg.setImageResource(R.mipmap.bag_bxlw_wxzlw);
                                break;
                            case 2:
                                titleImg.setImageResource(R.mipmap.bag_bxlw_wxzkq);
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
        viewpager.setOffscreenPageLimit(3);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    protected void visibleToLoadData() {
        super.visibleToLoadData();
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
                    GemFragment gemFragment = new GemFragment();
                    gemFragment.setmOnPageChangeLister(GemstoneFragment.this);
                    fragment = gemFragment;
                    break;
                case 1:
                    PresentFragment presentFragment = new PresentFragment();
                    presentFragment.setmOnPageChangeLister(GemstoneFragment.this);
                    fragment = presentFragment;
                    break;
                case 2:
                    CardFragment cardFragment = new CardFragment();
                    cardFragment.setmOnPageChangeLister(GemstoneFragment.this);
                    fragment = cardFragment;
                    break;
            }
            return fragment;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }
    }

    @Override
    public void OnListener(MyPackBean.DataBean bean) {
        monomerTips.setVisibility(View.VISIBLE);
        packName.setText(bean.getName());
        packNum.setText(bean.getTitle());
        if (bean.getColor().equals("mizuan")) {
            okBtnTwo.setVisibility(View.VISIBLE);
        } else {
            okBtnTwo.setVisibility(View.GONE);
        }
        okBtnTwo.setOnClickListener(v -> {
            PuTongWindow puTongWindow = new PuTongWindow(getActivity());
            puTongWindow.showAtLocation(viewpager, Gravity.CENTER, 0, 0);
            puTongWindow.getTitText().setText("是否确认兑换，兑换后米钻将自动发放至你的钱包中。");
            puTongWindow.getSure().setOnClickListener(v1 -> {
                puTongWindow.dismiss();
                exchangeMizuanCard(String.valueOf(bean.getTarget_id()));
            });
            puTongWindow.getCancel().setOnClickListener(v1 -> {
                puTongWindow.dismiss();
            });
        });
    }

    private void exchangeMizuanCard(String id) {
        RxUtils.loading(commonModel.exchangeMizuanCard(id), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        AToast.showText(mContext, commentBean.getMessage());
                        if (commentBean.getCode() == 1) {
                            monomerTips.setVisibility(View.GONE);
                            EventBus.getDefault().post(new FirstEvent("兑换成功", DUIHUANCHENGGONG));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        AToast.showText(mContext, t.getMessage());
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
