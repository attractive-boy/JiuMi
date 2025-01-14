package com.miniapp.talks.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
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
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.RoomRankAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.bean.RoomRankBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomRankFragment extends MyBaseArmFragment {
    @Inject
    CommonModel commonModel;

    @BindView(R.id.room_rank_ri)
    TextView roomRankRi;
    @BindView(R.id.room_rank_zhou)
    TextView roomRankZhou;
    @BindView(R.id.room_rank_yue)
    TextView roomRankYue;
    @BindView(R.id.img2)
    CircularImage img2;
    @BindView(R.id.head_image_kuang2)
    ImageView headImageKuang2;
    @BindView(R.id.tou2)
    ConstraintLayout tou2;
    @BindView(R.id.room_rank_name2)
    TextView roomRankName2;
    @BindView(R.id.room_rank_id2)
    TextView roomRankId2;
    @BindView(R.id.room_rank_zuan2)
    TextView roomRankZuan2;
    @BindView(R.id.two)
    RelativeLayout two;
    @BindView(R.id.img1)
    CircularImage img1;
    @BindView(R.id.head_image_kuang)
    ImageView headImageKuang;
    @BindView(R.id.tou1)
    ConstraintLayout tou1;
    @BindView(R.id.room_rank_name1)
    TextView roomRankName1;
    @BindView(R.id.room_rank_id1)
    TextView roomRankId1;
    @BindView(R.id.room_rank_zuan1)
    TextView roomRankZuan1;
    @BindView(R.id.one)
    RelativeLayout one;
    @BindView(R.id.img3)
    CircularImage img3;
    @BindView(R.id.head_image_kuang3)
    ImageView headImageKuang3;
    @BindView(R.id.tou3)
    ConstraintLayout tou3;
    @BindView(R.id.room_rank_name3)
    TextView roomRankName3;
    @BindView(R.id.room_rank_id3)
    TextView roomRankId3;
    @BindView(R.id.room_rank_zuan3)
    TextView roomRankZuan3;
    @BindView(R.id.three)
    RelativeLayout three;
    @BindView(R.id.room_rank_rv)
    RecyclerView recyclerView;
    @BindView(R.id.room_rank_zuan_tit1)
    TextView roomRankZuanTit1;
    @BindView(R.id.room_rank_zuan_tit2)
    TextView roomRankZuanTit2;
    @BindView(R.id.room_rank_zuan_tit3)
    TextView roomRankZuanTit3;
    @BindView(R.id.no_data)
    LinearLayout noData;

    private int id, type; //  ，1贡献榜2房间榜3星锐榜 type
    private String data = "1"; //1日榜2周榜3月榜
    private String uid; // 房间Id
    private RoomRankAdapter mAdapter; //适配器

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_room_rank);
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
    public void initData(@Nullable Bundle savedInstanceState) {
        id = getArguments().getInt("id");
        type = getArguments().getInt("type");
        uid = getArguments().getString("uid");

        //设置日周月榜单的默认
        roomRankRi.setSelected(true);
        roomRankZhou.setSelected(false);
        roomRankYue.setSelected(false);

        //设置适配器
        mAdapter = new RoomRankAdapter(type);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mAdapter);

        loadData(data);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    public static RoomRankFragment getInstance(int tag, int type, String uid) {
        RoomRankFragment fragment = new RoomRankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", tag);
        bundle.putInt("type", type);
        bundle.putString("uid", uid);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void loadData(String date) {
        showDialogLoding();
        if(id==3){
            RxUtils.loading(commonModel.room_rankingc(uid, date), this)
                    .subscribe(new ErrorHandleSubscriber<RoomRankBean>(mErrorHandler) {
                        @Override
                        public void onNext(RoomRankBean roomRankBean) {
                            disDialogLoding();
                            if (roomRankBean.getData().getOther().size() != 0) {
                                recyclerView.setVisibility(View.VISIBLE);
                                noData.setVisibility(View.GONE);
                                mAdapter.setNewData(roomRankBean.getData().getOther());
                            } else {
                                recyclerView.setVisibility(View.GONE);
                                noData.setVisibility(View.VISIBLE);
                            }

                            setTop(roomRankBean.getData().getTop());
                        }

                        @Override
                        public void onError(Throwable t) {
                            super.onError(t);
                            disDialogLoding();
                        }
                    });
        }else{
            RxUtils.loading(commonModel.room_ranking(uid, String.valueOf(id), date), this)
                    .subscribe(new ErrorHandleSubscriber<RoomRankBean>(mErrorHandler) {
                        @Override
                        public void onNext(RoomRankBean roomRankBean) {
                            disDialogLoding();
                            if (roomRankBean.getData().getOther().size() != 0) {
                                recyclerView.setVisibility(View.VISIBLE);
                                noData.setVisibility(View.GONE);
                                mAdapter.setNewData(roomRankBean.getData().getOther());
                            } else {
                                recyclerView.setVisibility(View.GONE);
                                noData.setVisibility(View.VISIBLE);
                            }

                            setTop(roomRankBean.getData().getTop());
                        }

                        @Override
                        public void onError(Throwable t) {
                            super.onError(t);
                            disDialogLoding();
                        }
                    });
        }
    }

    //设置前三的数据
    private void setTop(List<RoomRankBean.DataBean.TopBean> topBeanList) {

        if ("".equals(topBeanList.get(0).getName())) {
            roomRankName1.setText("虚位以待");
            roomRankZuanTit1.setText("");
            loadImage(img1, topBeanList.get(0).getImg(), R.mipmap.no_tou);
            roomRankId1.setText("");
            roomRankZuan1.setText("");
        } else {
            roomRankName1.setText(topBeanList.get(0).getName());
            roomRankZuanTit1.setText(id==3?"星锐值":"米钻:");
            loadImage(img1, topBeanList.get(0).getImg(), 0);
            roomRankId1.setText("ID:" + topBeanList.get(0).getId());
            roomRankZuan1.setText(String.valueOf(topBeanList.get(0).getMizuan()));

            if (type == 1) {
                roomRankZuan1.setTextColor(getActivity().getResources().getColor(R.color.color_FFBA1C));
            } else {
                roomRankZuan1.setTextColor(getActivity().getResources().getColor(R.color.font_ff3e6d));
            }
        }

        if ("".equals(topBeanList.get(1).getName())) {
            roomRankName2.setText("虚位以待");
            roomRankZuanTit2.setText("");
            loadImage(img2, topBeanList.get(1).getImg(), R.mipmap.no_tou);
            roomRankId2.setText("");
            roomRankZuan2.setText("");
        } else {
            roomRankName2.setText(topBeanList.get(1).getName());
            roomRankZuanTit2.setText(id==3?"星锐值":"米钻:");
            loadImage(img2, topBeanList.get(1).getImg(), 0);
            roomRankId2.setText("ID:" + topBeanList.get(1).getId());
            roomRankZuan2.setText(String.valueOf(topBeanList.get(1).getMizuan()));

            if (type == 1) {
                roomRankZuan2.setTextColor(getActivity().getResources().getColor(R.color.color_FFBA1C));
            } else {
                roomRankZuan2.setTextColor(getActivity().getResources().getColor(R.color.font_ff3e6d));
            }
        }

        if ("".equals(topBeanList.get(2).getName())) {
            roomRankName3.setText("虚位以待");
            roomRankZuanTit3.setText("");
            loadImage(img3, topBeanList.get(2).getImg(), R.mipmap.no_tou);
            roomRankId3.setText("");
            roomRankZuan3.setText("");
        } else {
            roomRankName3.setText(topBeanList.get(2).getName());
            roomRankZuanTit3.setText(id==3?"星锐值":"米钻:");
            loadImage(img3, topBeanList.get(2).getImg(), 0);
            roomRankId3.setText("ID:" + topBeanList.get(2).getId());
            roomRankZuan3.setText(String.valueOf(topBeanList.get(2).getMizuan()));

            if (type == 1) {
                roomRankZuan3.setTextColor(getActivity().getResources().getColor(R.color.color_FFBA1C));
            } else {
                roomRankZuan3.setTextColor(getActivity().getResources().getColor(R.color.font_ff3e6d));
            }
        }
    }


    @OnClick({R.id.room_rank_ri, R.id.room_rank_zhou, R.id.room_rank_yue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.room_rank_ri:
                roomRankRi.setSelected(true);
                roomRankZhou.setSelected(false);
                roomRankYue.setSelected(false);
                data = "1";
                loadData(data);
                break;
            case R.id.room_rank_zhou:
                roomRankRi.setSelected(false);
                roomRankZhou.setSelected(true);
                roomRankYue.setSelected(false);
                data = "2";
                loadData(data);
                break;
            case R.id.room_rank_yue:
                roomRankRi.setSelected(false);
                roomRankZhou.setSelected(false);
                roomRankYue.setSelected(true);
                data = "3";
                loadData(data);
                break;
        }
    }
}
