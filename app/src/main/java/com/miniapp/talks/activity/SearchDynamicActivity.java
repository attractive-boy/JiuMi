package com.miniapp.talks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.dynamic.DynamicDetailsActivity;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.adapter.CommDynamicAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.RecommenRoomBean;
import com.miniapp.talks.bean.RecommendedDynamicBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class SearchDynamicActivity extends MyBaseArmActivity {


    @Inject
    CommonModel commonModel;
    @BindView(R.id.recyclerview)
    RecyclerView myList1;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;

    private LoginData user;
    private CommDynamicAdapter commDynamicAdapter;
    private int page;
    private String name;

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
        return R.layout.activity_search_dynamic;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        name = getIntent().getStringExtra("name");
        user = UserManager.getUser();

        commDynamicAdapter = new CommDynamicAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration did = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        did.setDrawable(getResources().getDrawable(R.drawable.did_gray_dp8));
        myList1.addItemDecoration(did);
        myList1.setLayoutManager(linearLayoutManager);
        myList1.setAdapter(commDynamicAdapter);

        getNewDynamic();
        setonClick();

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 0;
            getNewDynamic();
        });
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            page++;
            getNewDynamic();
        });
    }


    //获取最新动态
    private void getNewDynamic() {
        RxUtils.loading(commonModel.search_all_dyni(UserManager.getUser() + "", name,
                "dynamic", page + ""), this)
                .subscribe(new ErrorHandleSubscriber<RecommendedDynamicBean>(mErrorHandler) {
                    @Override
                    public void onNext(RecommendedDynamicBean search) {
                        if (page == 0) {
                            commDynamicAdapter.setNewData(search.getData());
                            refreshLayout.finishRefresh();
                        } else {
                            commDynamicAdapter.addData(search.getData());
                            refreshLayout.finishLoadMore();
                        }

                    }
                });
    }

    /**
     * 点赞
     *
     * @param userId
     * @param targetId
     * @param type
     * @param hand
     * @param pos
     * @param a        a=1 点赞 a=2 收藏
     */
    private void fbDynamic(String userId, String targetId, String type, String hand, int pos, int a) {

        RxUtils.loading(commonModel.dynamic_praise(userId, targetId, type, hand), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (a == 1) {
                            commDynamicAdapter.notifyItemChanged(pos, "like");
                        } else if (a == 2) {
                            commDynamicAdapter.notifyItemChanged(pos, "likeSC");
                        }

                    }
                });
    }

    /**
     * @param userId
     * @param targetId
     * @param type
     * @param hand
     * @param pos
     * @param a        a=1 取消点赞, a=2 取消收藏
     */
    private void cancelDynamic(String userId, String targetId, String type, String hand, int pos, int a) {
        RxUtils.loading(commonModel.dynamic_praise(userId, targetId, type, hand), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (a == 1) {
                            commDynamicAdapter.notifyItemChanged(pos, "unlike");
                        } else if (a == 2) {
                            commDynamicAdapter.notifyItemChanged(pos, "unlikeSC");
                        }
                    }
                });
    }

    //adapter的点击事件
    private void setonClick() {
        commDynamicAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(this, DynamicDetailsActivity.class);
            intent.putExtra("id", commDynamicAdapter.getData().get(position).getId());
            startActivity(intent);
        });
        commDynamicAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.dianzan:
                    List<RecommendedDynamicBean.DataBean> data = commDynamicAdapter.getData();
                    RecommendedDynamicBean.DataBean dataBean = data.get(position);
                    int id = dataBean.getId();
                    int is_praise = dataBean.getIs_praise();
                    if (is_praise == 1) {
                        cancelDynamic(user.getUserId() + "", id + "", 1 + "", "del", position, 1);
                    } else {
                        fbDynamic(user.getUserId() + "", id + "", 1 + "", "add", position, 1);
                    }
                    break;
                case R.id.dy_collection:
                    List<RecommendedDynamicBean.DataBean> data1 = commDynamicAdapter.getData();
                    RecommendedDynamicBean.DataBean dataBean1 = data1.get(position);
                    int id1 = dataBean1.getId();
                    int is_collect = dataBean1.getIs_collect();
                    if (is_collect == 1) {
                        cancelDynamic(user.getUserId() + "", id1 + "", 2 + "", "del", position, 2);
                    } else {
                        fbDynamic(user.getUserId() + "", id1 + "", 2 + "", "add", position, 2);
                    }
                    break;
                case R.id.pinglun:
                    Intent intent = new Intent(this, DynamicDetailsActivity.class);
                    intent.putExtra("id", commDynamicAdapter.getData().get(position).getId());
                    startActivity(intent);
                    break;
                case R.id.zhuanfa:
                    break;
                case R.id.dy_head_image:
                    Intent intent1 = new Intent(this, MyPersonalCenterActivity.class);
                    if (commDynamicAdapter.getData().get(position).getUser_id() == UserManager.getUser().getUserId()) {
                        intent1.putExtra("sign", 0);
                        intent1.putExtra("id", "");
                    } else {
                        intent1.putExtra("sign", 1);
                        intent1.putExtra("id", commDynamicAdapter.getData().get(position).getUser_id() + "");
                    }
                    ArmsUtils.startActivity(intent1);
                    break;
            }
        });
    }

}
