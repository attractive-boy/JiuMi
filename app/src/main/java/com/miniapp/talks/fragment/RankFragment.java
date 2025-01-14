package com.miniapp.talks.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.miniapp.talks.Interface.MyPackBaoShiInter;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.activity.room.RankActivity;
import com.miniapp.talks.adapter.RankAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.HeaderViewPagerFragment;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.Rank;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.MyUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.util.SmartUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 作者:sgm
 * 描述:排行榜
 */
public class RankFragment extends HeaderViewPagerFragment {

    @BindView(R.id.textRi)
    TextView textRi;
    @BindView(R.id.textZhou)
    TextView textZhou;
    @BindView(R.id.textYue)
    TextView textYue;
    @BindView(R.id.myList)
    public RecyclerView myList;
    @BindView(R.id.ohuo)
    LinearLayout ohuo;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.ci_head)
    CircularImage ciHead;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.textNum)
    TextView textNum;
    @BindView(R.id.bottom)
    LinearLayout bottom;
    @BindView(R.id.my_one)
    ImageView myOne;
    @BindView(R.id.my_two)
    ImageView myTwo;
    @BindView(R.id.me_cf_tit)
    TextView meCfTit;

    private int id, type, statusBarHeight;
    public int shengHeight;
    private String date = "1";
    @Inject
    CommonModel commonModel;

    private CircularImage img1, img2, img3;
    private ImageView sex1, sex2, sex3;
    private RelativeLayout one, two, three;
    private TextView textName1, textName2, textName3;
    private TextView textDec1, textDec2, textDec3;
//    private View TMView;

    private int color = 0;
    private int h = SmartUtil.dp2px(48);
    private int lastScrollY = 0;
    private int aa = 0;

    public RankAdapter rankAdapter;
    private View headView;
    private MyPackBaoShiInter.onRankInter mRankInter;
    private List<Rank.DataBean.TopBean> mList;

    public int mTitleLayoutHeight = 0;

    public void setmOnPageChangeLister(MyPackBaoShiInter.onRankInter lister) {
        this.mRankInter = lister;

    }

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
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_rank);
    }


    @Override
    public void setData(@Nullable Object data) {

    }

    public void setItemBg(boolean isTrans) {
        LogUtils.debugInfo("方法进来了====");
        if (rankAdapter != null && rankAdapter.getData().size()>0) {
            rankAdapter.getData().get(0).setBg(isTrans);
            rankAdapter.notifyDataSetChanged();
        }
    }

    public static RankFragment getInstance(int tag, int type, int stateBarHeight) {
        RankFragment fragment = new RankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", tag);
        bundle.putInt("type", type);
        bundle.putInt("statusBarHeight", stateBarHeight);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        id = getArguments().getInt("id");
        type = getArguments().getInt("type");
        statusBarHeight = getArguments().getInt("statusBarHeight");
//        TMView = getActivity().findViewById(R.id.view_touming);

        color = ContextCompat.getColor(getActivity(), R.color.color_daa1e1) & 0x00ffffff;


        textRi.setSelected(true);
        textZhou.setSelected(false);
        textYue.setSelected(false);

        rankAdapter = new RankAdapter(type);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myList.setLayoutManager(llm);
        myList.setAdapter(rankAdapter);

        loadData(date);
        headView = ArmsUtils.inflate(mContext, R.layout.rank_head);


        img1 = headView.findViewById(R.id.img1);
        img2 = headView.findViewById(R.id.img2);
        img3 = headView.findViewById(R.id.img3);
        one = headView.findViewById(R.id.one);
        two = headView.findViewById(R.id.two);
        three = headView.findViewById(R.id.three);
        sex1 = headView.findViewById(R.id.sex1);
        sex2 = headView.findViewById(R.id.sex2);
        sex3 = headView.findViewById(R.id.sex3);
        textName1 = headView.findViewById(R.id.textName1);
        textName2 = headView.findViewById(R.id.textName2);
        textName3 = headView.findViewById(R.id.textName3);
        textDec1 = headView.findViewById(R.id.textDec1);
        textDec2 = headView.findViewById(R.id.textDec2);
        textDec3 = headView.findViewById(R.id.textDec3);
       View rcvHeadView = headView.findViewById(R.id.rcv_rank_head);
        //点击第一的头像跳转个人主页
        img1.setOnClickListener(v -> {
            if (!mList.get(0).getNickname().equals("")) {
                Intent intent = new Intent(getContext(), MyPersonalCenterActivity.class);
                if (mList.get(0).getUser_id() == UserManager.getUser().getUserId()) {
                    intent.putExtra("sign", 0);
                    intent.putExtra("id", "");
                } else {
                    intent.putExtra("sign", 1);
                    intent.putExtra("id", mList.get(0).getUser_id() + "");
                }
                ArmsUtils.startActivity(intent);
            }
        });
        //点击第二的头像跳转个人主页
        img2.setOnClickListener(v -> {
            if (!mList.get(1).getNickname().equals("")) {
                Intent intent = new Intent(getContext(), MyPersonalCenterActivity.class);
                if (mList.get(1).getUser_id() == UserManager.getUser().getUserId()) {
                    intent.putExtra("sign", 0);
                    intent.putExtra("id", "");
                } else {
                    intent.putExtra("sign", 1);
                    intent.putExtra("id", mList.get(1).getUser_id() + "");
                }
                ArmsUtils.startActivity(intent);
            }
        });
        //点击第三的头像跳转个人主页
        img3.setOnClickListener(v -> {
            if (!mList.get(2).getNickname().equals("")) {
                Intent intent = new Intent(getContext(), MyPersonalCenterActivity.class);
                if (mList.get(2).getUser_id() == UserManager.getUser().getUserId()) {
                    intent.putExtra("sign", 0);
                    intent.putExtra("id", "");
                } else {
                    intent.putExtra("sign", 1);
                    intent.putExtra("id", mList.get(2).getUser_id() + "");
                }
                ArmsUtils.startActivity(intent);
            }
        });

        //点击除了前三的头像跳转个人主页
        rankAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Intent intent = new Intent(getContext(), MyPersonalCenterActivity.class);
            if (rankAdapter.getData().get(position).getUser_id() == UserManager.getUser().getUserId()) {
                intent.putExtra("sign", 0);
                intent.putExtra("id", "");
            } else {
                intent.putExtra("sign", 1);
                intent.putExtra("id", rankAdapter.getData().get(position).getUser_id() + "");
            }
            ArmsUtils.startActivity(intent);
        });

        myList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int mScrollY = 0;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollY += dy;

                if (mRankInter != null) {
//                    LogUtils.debugInfo("scrollY===" + mScrollY);
                    mRankInter.OnRankInter(mScrollY);
                    int titleY = mScrollY;
                    if (lastScrollY < h) {
                        titleY = Math.min(h, titleY);
                        aa = titleY > h ? h : titleY;
                        ohuo.setBackgroundColor(((255 * aa / h) << 24) | color);
                    }
                    aa = titleY;
                }
            }
        });

        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        ohuo.measure(w, h);
        mTitleLayoutHeight = ohuo.getMeasuredHeight();
        LogUtils.debugInfo("====周月榜高度" + mTitleLayoutHeight);
        RankActivity rankActivity = (RankActivity) getActivity();
        View view = rankActivity.viewTouMing;
        MyUtil.setMargins(view, 0, mTitleLayoutHeight, 0, 0);

        shengHeight = MyUtil.dip2px(getActivity(), 418) - statusBarHeight - MyUtil.dip2px(getActivity(), 46) - MyUtil.dip2px(getActivity(), 40) - MyUtil.dip2px(getActivity(), 50);
        LogUtils.debugInfo("====head高" + statusBarHeight);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, shengHeight);
        rcvHeadView.setLayoutParams(params);
    }

    private void loadData(String date) {
        showDialogLoding();
        RxUtils.loading(commonModel.leaderboard(String.valueOf(id),
                date, String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<Rank>(mErrorHandler) {
                    @Override
                    public void onNext(Rank rank) {
                        disDialogLoding();
                        if(rank.getData().getOther().size()>0){

                            rank.getData().getOther().get(0).setBg(true);

                        }
                        rankAdapter.setNewData(rank.getData().getOther());
                        mList = rank.getData().getTop();
                        setTop(rank.getData().getTop());

                        num.setText(rank.getData().getUser().get(0).getSort());
                        tvTitle.setText(rank.getData().getUser().get(0).getNickname());
                        textNum.setText(rank.getData().getUser().get(0).getExp() + "");
                        if (type == 1) {
                            meCfTit.setText("金锐值");
                            GlideArms.with(getActivity()).load(rank.getData().getUser().get(0).getGold_img()).into(myOne);
                            textNum.setTextColor(getResources().getColor(R.color.color_FFBA1C));
                        } else {
                            meCfTit.setText("星锐值");
                            GlideArms.with(getActivity()).load(rank.getData().getUser().get(0).getStars_img()).into(myTwo);
                            textNum.setTextColor(getResources().getColor(R.color.font_ff3e6d));
                        }
                        ArmsUtils.obtainAppComponentFromContext(mContext)
                                .imageLoader()
                                .loadImage(getActivity(), ImageConfigImpl
                                        .builder()
                                        .url(rank.getData().getUser().get(0).getHeadimgurl())
                                        .placeholder(R.mipmap.no_tou)
                                        .imageView(ciHead)
                                        .errorPic(R.mipmap.no_tou)
                                        .build());
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        disDialogLoding();
                    }
                });
    }

    private void setTop(List<Rank.DataBean.TopBean> list) {
        if (list.size() != 0) {
            int headerCount = rankAdapter.getHeaderLayoutCount();
            if (headerCount > 0) {
                rankAdapter.removeAllHeaderView();
            }
            rankAdapter.addHeaderView(headView);

            if (list.get(0).getNickname().equals("")) {
                textName1.setText("虚位以待");
                textDec1.setText("");
                sex1.setImageResource(0);
                loadImage(img1, list.get(0).getHeadimgurl(), R.mipmap.no_tou);
            } else {
                textName1.setText(list.get(0).getNickname());
                loadImage(img1, list.get(0).getHeadimgurl(), R.mipmap.no_tou);
                textDec1.setText(list.get(0).getExp() + "");
                if (type == 1) {
                    textDec1.setTextColor(getActivity().getResources().getColor(R.color.color_FFBA1C));
                } else {
                    textDec1.setTextColor(getActivity().getResources().getColor(R.color.font_ff3e6d));
                }
                if (list.get(0).getSex().equals("1")) {
                    sex1.setImageResource(R.mipmap.gender_boy);
                } else if (list.get(0).getSex().equals("2")) {
                    sex1.setImageResource(R.mipmap.gender_girl);
                }
            }

            if (list.get(1).getNickname().equals("")) {
                textName2.setText("虚位以待");
                textDec2.setText("");
                sex2.setImageResource(0);
                loadImage(img2, list.get(1).getHeadimgurl(), R.mipmap.no_tou);
            } else {
                textName2.setText(list.get(1).getNickname());
                loadImage(img2, list.get(1).getHeadimgurl(), R.mipmap.no_tou);

                textDec2.setText(list.get(1).getExp() + "");
                if (type == 1) {
                    textDec2.setTextColor(getActivity().getResources().getColor(R.color.color_FFBA1C));
                } else {
                    textDec2.setTextColor(getActivity().getResources().getColor(R.color.font_ff3e6d));
                }

                if (list.get(1).getSex().equals("1")) {
                    sex2.setImageResource(R.mipmap.gender_boy);
                } else if (list.get(1).getSex().equals("2")) {
                    sex2.setImageResource(R.mipmap.gender_girl);
                }
            }
            if (list.get(2).getNickname().equals("")) {
                textName3.setText("虚位以待");
                textDec3.setText("");
                sex3.setImageResource(0);
                loadImage(img3, list.get(2).getHeadimgurl(), R.mipmap.no_tou);
            } else {
                textName3.setText(list.get(2).getNickname());
                loadImage(img3, list.get(2).getHeadimgurl(), R.mipmap.no_tou);
                textDec3.setText(list.get(2).getExp() + "");
                if (type == 1) {
                    textDec3.setTextColor(getActivity().getResources().getColor(R.color.color_FFBA1C));
                } else {
                    textDec3.setTextColor(getActivity().getResources().getColor(R.color.font_ff3e6d));
                }
                if (list.get(2).getSex().equals("1")) {
                    sex3.setImageResource(R.mipmap.gender_boy);
                } else if (list.get(2).getSex().equals("2")) {
                    sex3.setImageResource(R.mipmap.gender_girl);
                }
            }
        } else {
            rankAdapter.removeAllHeaderView();
        }

    }


    @OnClick({R.id.textRi, R.id.textZhou, R.id.textYue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textRi:
                textRi.setSelected(true);
                textZhou.setSelected(false);
                textYue.setSelected(false);
                date = "1";
                loadData(date);
                break;
            case R.id.textZhou:
                textZhou.setSelected(true);
                textRi.setSelected(false);
                textYue.setSelected(false);
                date = "2";
                loadData(date);
                break;
            case R.id.textYue:
                textRi.setSelected(false);
                textZhou.setSelected(false);
                textYue.setSelected(true);
                date = "3";
                loadData(date);
                break;
        }
    }

    @Override
    public View getScrollableView() {
        return myList;
    }
}
