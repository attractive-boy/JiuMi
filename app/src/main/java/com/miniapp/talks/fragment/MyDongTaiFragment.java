package com.miniapp.talks.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.dynamic.DynamicDetailsActivity;
import com.miniapp.talks.adapter.MyDongTaiAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.HeaderViewPagerFragment;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.MeYiDuiBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.popup.PuTongWindow;
import com.miniapp.talks.service.CommonModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 个人主页里面  动态页面
 */
public class MyDongTaiFragment extends HeaderViewPagerFragment {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    @BindView(R.id.qunbu)
    LinearLayout qunbu;
    @BindView(R.id.no_data)
    TextView noData;

    private int pagea = 1;
    private String userId, tag;

    private MyDongTaiAdapter mAdapter;
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
        View scrollView = ArmsUtils.inflate(getActivity(), R.layout.fragment_dongtai);
        return scrollView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            userId = arguments.getString("idd");
            tag = arguments.getString("tag");
            mAdapter = new MyDongTaiAdapter(tag, getActivity());
            LinearLayoutManager lm = new LinearLayoutManager(getActivity());
            lm.setOrientation(LinearLayoutManager.VERTICAL);
            DividerItemDecoration did = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
            did.setDrawable(getResources().getDrawable(R.drawable.did_gray_dp8));
            recyclerview.addItemDecoration(did);
            recyclerview.setLayoutManager(lm);
            recyclerview.setAdapter(mAdapter);

            setData(pagea);
        }
        itemClick();

        smart.setOnRefreshListener(refreshlayout -> {
            pagea = 1;
            setData(pagea);
        });
        smart.setOnLoadMoreListener(refreshlayout -> {
            pagea++;
            setData(pagea);
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public View getScrollableView() {
        return recyclerview;
    }


    //适配器的点击事件
    private void itemClick() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), DynamicDetailsActivity.class);
                intent.putExtra("id", mAdapter.getData().get(position).getId());
                ArmsUtils.startActivity(intent);
            }
        });

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.dianzan:
                    List<MeYiDuiBean.DataBean> data = mAdapter.getData();
                    MeYiDuiBean.DataBean dataBean = data.get(position);
                    int id = dataBean.getId();
                    int is_praise = dataBean.getIs_praise();
                    if (is_praise == 0) {
                        cancelDynamic(userId + "", id + "", 1 + "", "del", position, 1);
                    } else {
                        fbDynamic(userId + "", id + "", 1 + "", "add", position, 1);
                    }
                    break;
                case R.id.dy_collection:
                    List<MeYiDuiBean.DataBean> data1 = mAdapter.getData();
                    MeYiDuiBean.DataBean dataBean1 = data1.get(position);
                    int id1 = dataBean1.getId();
                    int is_collect = dataBean1.getIs_collect();
                    if (is_collect == 1) {
                        cancelDynamic(userId + "", id1 + "", 2 + "", "del", position, 2);
                    } else {
                        fbDynamic(userId + "", id1 + "", 2 + "", "add", position, 2);
                    }
                    break;
                case R.id.pinglun:
                    Intent intent = new Intent(getActivity(), DynamicDetailsActivity.class);
                    intent.putExtra("id", mAdapter.getData().get(position).getId());
                    startActivity(intent);
                    break;
                case R.id.zhuanfa:
                    break;
                case R.id.delete:
                    PuTongWindow puTongWindow = new PuTongWindow(getActivity());
                    puTongWindow.showAtLocation(qunbu, Gravity.CENTER, 0, 0);
                    puTongWindow.getCancel().setOnClickListener(v -> {
                        puTongWindow.dismiss();
                    });
                    puTongWindow.getSure().setOnClickListener(v -> {
                        puTongWindow.dismiss();
                        RxUtils.loading(commonModel.delCommunity(String.valueOf(UserManager.getUser().getUserId()), String.valueOf(mAdapter.getData().get(position).getId())), this)
                                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                                    @Override
                                    public void onNext(CommentBean commentBean) {
                                        if (commentBean.getCode() == 1) {
                                            showMessage(commentBean.getMessage());
                                            mAdapter.remove(position);
                                            mAdapter.notifyDataSetChanged();
                                        } else {
                                            showMessage(commentBean.getMessage());
                                        }
                                    }
                                });

                    });
                    break;
            }
        });
    }

    //获取数据
    private void setData(int page) {
        RxUtils.loading(commonModel.getMeYiDui(userId, String.valueOf(UserManager.getUser().getUserId()), 6 + "", page + ""), this)
                .subscribe(new ErrorHandleSubscriber<MeYiDuiBean>(mErrorHandler) {
                    @Override
                    public void onNext(MeYiDuiBean meYiDuiBean) {
                        if (page == 1) {
                            smart.finishRefresh();
                            if (meYiDuiBean.getData().size() == 0 || meYiDuiBean.getData() == null) {
                                noData.setVisibility(View.VISIBLE);
                                smart.setVisibility(View.GONE);
                            } else {
                                noData.setVisibility(View.GONE);
                                smart.setVisibility(View.VISIBLE);
                                mAdapter.setNewData(meYiDuiBean.getData());
                            }
                        } else {
                            smart.finishLoadMore();
                            mAdapter.addData(meYiDuiBean.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        smart.finishRefresh();
                        smart.finishLoadMore();
                        noData.setVisibility(View.VISIBLE);
                        smart.setVisibility(View.GONE);
                    }
                });
    }

    //a=1 取消点赞, a=2 取消收藏
    private void cancelDynamic(String userId, String targetId, String type, String hand, int pos, int a) {
        RxUtils.loading(commonModel.dynamic_praise(userId, targetId, type, hand), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (a == 1) {
                            mAdapter.notifyItemChanged(pos, "unlike");
                        } else if (a == 2) {
                            mAdapter.notifyItemChanged(pos, "unlikeSC");
                        }
                    }
                });
    }

    //a=1 点赞 a=2 收藏
    private void fbDynamic(String userId, String targetId, String type, String hand, int pos, int a) {

        RxUtils.loading(commonModel.dynamic_praise(userId, targetId, type, hand), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (a == 1) {
                            mAdapter.notifyItemChanged(pos, "like");
                        } else if (a == 2) {
                            mAdapter.notifyItemChanged(pos, "likeSC");
                        }
                    }
                });
    }
}
