package com.miniapp.talks.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.lzy.widget.HeaderViewPager;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.CPAdapter;
import com.miniapp.talks.adapter.MyPagerAdapter;
import com.miniapp.talks.adapter.PagerAdapter;
import com.miniapp.talks.adapter.RongYuAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.HeaderViewPagerFragment;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.MyPersonalCebterBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.fragment.CPAFragment;
import com.miniapp.talks.fragment.CPBFragment;
import com.miniapp.talks.fragment.CPCFragment;
import com.miniapp.talks.fragment.MyDongTaiFragment;
import com.miniapp.talks.fragment.MyGiftFragment;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.SpaceItemDecoration;
import com.miniapp.talks.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.util.SmartUtil;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.relex.circleindicator.CircleIndicator;

import static com.miniapp.talks.utils.Constant.JIECHUCP;
import static com.miniapp.talks.utils.Constant.KAIQICPWEI;
import static com.miniapp.talks.utils.Constant.XIUGAIGERENZILIAOCHENGGONG;

/**
 * 个人中心
 * 老王
 */
public class MyPersonalCenterActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.tv_back_account_manager)
    TextView tvBackAccountManager;
    @BindView(R.id.tv_title_account_manager)
    TextView tvTitleAccountManager;
    @BindView(R.id.xiugai)
    ImageView xiuGai;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.head_image)
    CircularImage headImage;
    @BindView(R.id.nackname_text)
    TextView nacknameText;
    @BindView(R.id.grade_image_one)
    ImageView gradeImageOne;
    @BindView(R.id.grade_image_two)
    ImageView gradeImageTwo;
    @BindView(R.id.id_text)
    TextView idText;
    @BindView(R.id.fensi_text)
    TextView fensiText;
    @BindView(R.id.room_btn)
    TextView roomBtn;
    @BindView(R.id.sex_image)
    ImageView sexImage;
    @BindView(R.id.age_text)
    TextView ageText;
    @BindView(R.id.constellation_text)
    TextView constellationText;
    @BindView(R.id.address_text)
    TextView addressText;
    @BindView(R.id.text)
    CardView text;
    @BindView(R.id.tabs_promotlist)
    SlidingTabLayout tabsPromotlist;
    @BindView(R.id.aaaaa)
    CardView aaaaa;
    @BindView(R.id.viewpager_promotlist)
    ViewPager viewpagerPromotlist;
    @BindView(R.id.scrollableLayout)
    HeaderViewPager scrollableLayout;
    @BindView(R.id.room_master_head_image)
    CircularImage roomMasterHeadImage;
    @BindView(R.id.room_name)
    TextView roomName;
    @BindView(R.id.room_hot)
    TextView roomHot;
    @BindView(R.id.jinru)
    TextView jinru;
    @BindView(R.id.suo_zai_room)
    RelativeLayout suoZaiRoom;
    @BindView(R.id.rongyu)
    LinearLayout rongyu;
    @BindView(R.id.juti_rongyu)
    CardView jutiRongyu;
    @BindView(R.id.rongyu_recyc)
    RecyclerView rongyuRecyc;
    @BindView(R.id.tou)
    RelativeLayout touLayout;
    @BindView(R.id.buttom_btn)
    LinearLayout buttomBtn;
    @BindView(R.id.guanzhu)
    LinearLayout guanzhu;
    @BindView(R.id.liaotian)
    LinearLayout liaotian;
    @BindView(R.id.guanzhu_text)
    TextView guanzhuText;
    @BindView(R.id.layout_head_bg)
    RelativeLayout layoutHeadBg;
    @BindView(R.id.no_rongyu)
    LinearLayout noRongYu;
    @BindView(R.id.cp_help)
    ImageView cpHelp;
    @BindView(R.id.cp_viewpager)
    ViewPager cpViewpager;
    @BindView(R.id.vp_magicindicator)
    CircleIndicator vpMagicindicator;

    private List<String> tabList; //tabLayout的数据源
    private List<HeaderViewPagerFragment> fragmentList; //fragment的数据源
    private MyPagerAdapter myPagerAdapter;
    private RongYuAdapter rongYuAdapter;
    private int sign, follow, user_id;
    private String fromId;
    private MyPersonalCebterBean.DataBean data;

    private int mScrollY = 0;
    private int mOffset = 0;
    private int mImgScrollY = 0;
    private boolean isRoom;//是否房间进入

    private List<Fragment> viewpagerFragment;
    private CPAdapter mPagerAdapter;
    private CPAFragment cpaFragment;
    private CPBFragment cpbFragment;
    private CPCFragment cpcFragment;

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
        return R.layout.activity_my_personal_center;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        StatusBarUtils.immersive(this);

        StatusBarUtils.setPaddingSmart(this, layoutHeadBg);

        int statusBarHeight = StatusBarUtils.getStatusBarHeight(this);

//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutHeadBg.getLayoutParams();
        viewpagerFragment = new ArrayList<>();
        cpaFragment = new CPAFragment();
        cpbFragment = new CPBFragment();
        cpcFragment = new CPCFragment();
        viewpagerFragment.add(cpaFragment);
        viewpagerFragment.add(cpbFragment);
        viewpagerFragment.add(cpcFragment);
        mPagerAdapter = new CPAdapter(getSupportFragmentManager(), viewpagerFragment);
        cpViewpager.setAdapter(mPagerAdapter);
        vpMagicindicator.setViewPager(cpViewpager);
        cpViewpager.setOffscreenPageLimit(3);

        layoutHeadBg.setPadding(0, statusBarHeight, 0, 0);

        sign = getIntent().getIntExtra("sign", 0);
        fromId = getIntent().getStringExtra("id");
        isRoom = getIntent().getBooleanExtra("isRoom", false);

        rongYuAdapter = new RongYuAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(MyPersonalCenterActivity.this);
        SpaceItemDecoration sid = new SpaceItemDecoration(20, 0);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rongyuRecyc.addItemDecoration(sid);
        rongyuRecyc.setLayoutManager(llm);
        rongyuRecyc.setAdapter(rongYuAdapter);

        if (sign == 0) {
            xiuGai.setVisibility(View.VISIBLE);
            aaaaa.setVisibility(View.GONE);
            roomBtn.setText("我的房间");
        } else {
            more.setVisibility(View.GONE);
            aaaaa.setVisibility(View.VISIBLE);
            roomBtn.setText("TA的房间");
        }
        setData();
        scrollableLayout.setOnScrollListener(new HeaderViewPager.OnScrollListener() {
            private int lastScrollY = 0;
            private int lastImgScrollY = 0;
            private int h = SmartUtil.dp2px(48);
            private int imgHeight = SmartUtil.dp2px(102);//滑动时图片的最大滑动距离
            private int color = ContextCompat.getColor(getApplicationContext(), R.color.xindongcolor) & 0x00ffffff;

            @Override
            public void onScroll(int currentY, int maxY) {
                LogUtils.debugInfo(currentY + "");
                int titleY = currentY;
                int imgY = currentY;
                if (lastScrollY < h) {
                    titleY = Math.min(h, titleY);
                    mScrollY = titleY > h ? h : titleY;
                    touLayout.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                }
                lastScrollY = titleY;
                if (lastImgScrollY < imgHeight) {
                    imgY = Math.min(imgHeight, imgY);
                    mImgScrollY = imgY > imgHeight ? imgHeight : imgY;
                    layoutHeadBg.setTranslationY(mOffset - mImgScrollY - statusBarHeight);
                }
                lastImgScrollY = imgY;

            }
        });

    }

    //设置CP
    private void setCP(List<MyPersonalCebterBean.DataBean.CplistBean> cplistBeanList) {

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("cpbeanone", (ArrayList<? extends Parcelable>) cplistBeanList);
        if ("".equals(fromId)) {
            bundle.putString("idd", UserManager.getUser().getUserId() + "");
        } else {
            bundle.putString("idd", fromId + "");
        }
        cpaFragment.setArguments(bundle);

        Bundle bundle1 = new Bundle();
        bundle1.putParcelableArrayList("cpbeantwo", (ArrayList<? extends Parcelable>) cplistBeanList);
        if ("".equals(fromId)) {
            bundle1.putString("idd", UserManager.getUser().getUserId() + "");
        } else {
            bundle1.putString("idd", fromId + "");
        }
        cpbFragment.setArguments(bundle1);


        Bundle bundle2 = new Bundle();
        bundle2.putParcelableArrayList("cpbeanthree", (ArrayList<? extends Parcelable>) cplistBeanList);
        if ("".equals(fromId)) {
            bundle2.putString("idd", UserManager.getUser().getUserId() + "");
        } else {
            bundle2.putString("idd", fromId + "");
        }
        cpcFragment.setArguments(bundle2);

        mPagerAdapter.notifyDataSetChanged();
    }

    private void setData() {
        RxUtils.loading(commonModel.getPersonalCabter(UserManager.getUser().getUserId() + "", fromId), this)
                .subscribe(new ErrorHandleSubscriber<MyPersonalCebterBean>(mErrorHandler) {
                    @Override
                    public void onNext(MyPersonalCebterBean myPersonalCebterBean) {
                        data = myPersonalCebterBean.getData();

                        MyPersonalCebterBean.DataBean.UserInfoBean userInfo = data.getUserInfo();
                        follow = userInfo.getIs_follow();
                        user_id = userInfo.getId();
                        //头像
                        ArmsUtils.obtainAppComponentFromContext(MyPersonalCenterActivity.this)
                                .imageLoader()
                                .loadImage(MyPersonalCenterActivity.this, ImageConfigImpl
                                        .builder()
                                        .url(userInfo.getHeadimgurl())
                                        .placeholder(R.mipmap.no_tou)
                                        .imageView(headImage)
                                        .errorPic(R.mipmap.no_tou)
                                        .build());
                        //昵称
                        nacknameText.setText(userInfo.getNickname());
                        //ID
                        idText.setText(userInfo.getId() + "");
                        //性别
                        if (userInfo.getSex() == 1) {
                            sexImage.setImageResource(R.mipmap.gender_boy);
                        } else {
                            sexImage.setImageResource(R.mipmap.gender_girl);
                        }
                        //年龄
                        ageText.setText(userInfo.getAge() + "岁");
                        //星座
                        constellationText.setText(userInfo.getConstellation());
                        //城市
                        addressText.setText(userInfo.getCity());
                        //粉丝数
                        fensiText.setText("粉丝 " + userInfo.getFans_num() + "");
                        //关注
                        if (userInfo.getIs_follow() == 1) {
                            guanzhuText.setText("已关注");
                        } else {
                            guanzhuText.setText("关注");
                        }

                        //在不在房间的显示
                        if (data.getRoomInfo().getRoom_name() == null) {
                            suoZaiRoom.setVisibility(View.GONE);
                        } else {
                            suoZaiRoom.setVisibility(View.VISIBLE);
                            ArmsUtils.obtainAppComponentFromContext(MyPersonalCenterActivity.this)
                                    .imageLoader()
                                    .loadImage(MyPersonalCenterActivity.this, ImageConfigImpl
                                            .builder()
                                            .url(data.getRoomInfo().getRoom_cover())
                                            .placeholder(R.mipmap.no_tou)
                                            .imageView(roomMasterHeadImage)
                                            .errorPic(R.mipmap.no_tou)
                                            .build());
                            roomName.setText(data.getRoomInfo().getRoom_name());
                            roomHot.setText("人气" + data.getRoomInfo().getHot() + "");
                        }
                        //荣誉的显示
                        if (data.getGlory().size() == 0) {
                            noRongYu.setVisibility(View.VISIBLE);
                            rongyuRecyc.setVisibility(View.GONE);
                        } else {
                            noRongYu.setVisibility(View.GONE);
                            rongyuRecyc.setVisibility(View.VISIBLE);
                            rongYuAdapter.setNewData(data.getGlory());
                        }

                        tabList = new ArrayList<>();
                        fragmentList = new ArrayList<>();

                        tabList.add("礼物");
                        tabList.add("动态");

                        MyGiftFragment myGiftFragment = new MyGiftFragment();
                        List<MyPersonalCebterBean.DataBean.GiftsBean> gifts = data.getGifts();
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("gifts", (ArrayList<? extends Parcelable>) gifts);
                        myGiftFragment.setArguments(bundle);

                        MyDongTaiFragment myDongTaiFragment = new MyDongTaiFragment();
                        Bundle bundlee = new Bundle();
                        if ("".equals(fromId)) {
                            bundlee.putString("idd", UserManager.getUser().getUserId() + "");
                            bundlee.putString("tag", "0");
                        } else {
                            bundlee.putString("idd", fromId + "");
                            bundlee.putString("tag", "1");
                        }
                        myDongTaiFragment.setArguments(bundlee);

                        fragmentList.add(myGiftFragment);
                        fragmentList.add(myDongTaiFragment);

                        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, tabList);
                        viewpagerPromotlist.setAdapter(myPagerAdapter);
                        tabsPromotlist.setViewPager(viewpagerPromotlist);

                        tabsPromotlist.setSnapOnTabClick(true);
                        scrollableLayout.setCurrentScrollableContainer(fragmentList.get(0));
                        viewpagerPromotlist.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                            @Override
                            public void onPageSelected(int position) {
                                scrollableLayout.setCurrentScrollableContainer(fragmentList.get(position));
                            }
                        });

                        //CP
                        setCP(myPersonalCebterBean.getData().getCplist());
                    }
                });
    }

    //关注
    private void setFollow() {
        RxUtils.loading(commonModel.follow(String.valueOf(UserManager.getUser().getUserId()), user_id + ""), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        follow = 1;
                        guanzhuText.setText("已关注");
                    }
                });
    }

    //取消关注
    private void setTakeOff() {
        RxUtils.loading(commonModel.cancel_follow(String.valueOf(UserManager.getUser().getUserId()), user_id + ""), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        follow = 0;
                        guanzhuText.setText("关注");
                    }
                });
    }


    @OnClick({R.id.tv_back_account_manager, R.id.xiugai, R.id.more, R.id.room_btn, R.id.suo_zai_room, R.id.guanzhu, R.id.liaotian, R.id.cp_help})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back_account_manager:
                if (isRoom) {
                    startActivity(new Intent(this, AdminHomeActivity.class));
                    overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
                }
                finish();
                break;
            case R.id.xiugai:
                ArmsUtils.startActivity(ModifyDataActivity.class);
                break;
            case R.id.more:

                break;
            case R.id.room_btn:
                enterData(String.valueOf(data.getUserInfo().getId()), "", commonModel, 1, data.getUserInfo().getHeadimgurl());
                break;
            case R.id.suo_zai_room:
                enterData(String.valueOf(data.getRoomInfo().getUid()), "", commonModel, 1, data.getRoomInfo().getRoom_cover());
                break;
            case R.id.guanzhu:
                if (follow == 1) {
                    setTakeOff();
                } else {
                    setFollow();
                }
                break;
            case R.id.liaotian:

                RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE,
                        data.getUserInfo().getRy_uid(),
                        data.getUserInfo().getNickname());
                break;
            case R.id.cp_help:
                Intent intent = new Intent(MyPersonalCenterActivity.this, DengJiShuoMingActivity.class);
                intent.putExtra("tag", "2");
                ArmsUtils.startActivity(intent);
                break;
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (AdminHomeActivity.isStart && AdminHomeActivity.isTop) {
            startActivity(new Intent(this, AdminHomeActivity.class));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (XIUGAIGERENZILIAOCHENGGONG.equals(tag)) {
            setData();
        } else if (JIECHUCP.equals(tag)) {
//            getNewData(event.getMsg());
            setData();
        } else if (KAIQICPWEI.equals(tag)) {
            setData();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isRoom) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                startActivity(new Intent(this, AdminHomeActivity.class));
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
