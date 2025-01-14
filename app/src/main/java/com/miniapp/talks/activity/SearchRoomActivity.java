package com.miniapp.talks.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.HomeRecommendAdapter;
import com.miniapp.talks.adapter.MyConcernAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.RecommenRoomBean;
import com.miniapp.talks.bean.UserFriend;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 相关房间
 */
public class SearchRoomActivity extends MyBaseArmActivity {


    @BindView(R.id.recyclerview)
    ListView recyclerview;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;

    @Inject
    CommonModel commonModel;
    private int page = 0;
    private String name;

    private HomeRecommendAdapter homeRecommendAdapter;

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
        return R.layout.activity_search_room;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        name = getIntent().getStringExtra("name");
        homeRecommendAdapter = new HomeRecommendAdapter(this);
        recyclerview.setAdapter(homeRecommendAdapter);

        loadData();

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 0;
            loadData();
        });
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            page++;
            loadData();
        });

        recyclerview.setOnItemClickListener((parent, view, position, id) -> {
            enterData(homeRecommendAdapter.getList_adapter().get(position).getUid(),
                    "", commonModel,1,homeRecommendAdapter.getList_adapter().get(position).getRoom_cover());
        });
    }

    private void loadData() {
        RxUtils.loading(commonModel.search_all_room(UserManager.getUser() + "", name,
                "room", page + ""), this)
                .subscribe(new ErrorHandleSubscriber<RecommenRoomBean>(mErrorHandler) {
                    @Override
                    public void onNext(RecommenRoomBean search) {
                        if (page == 0) {
                            homeRecommendAdapter.getList_adapter().clear();
                            homeRecommendAdapter.getList_adapter().addAll(search.getData());
                            homeRecommendAdapter.notifyDataSetChanged();
                            refreshLayout.finishRefresh();
                        } else {
                            homeRecommendAdapter.getList_adapter().addAll(search.getData());
                            homeRecommendAdapter.notifyDataSetChanged();
                            refreshLayout.finishLoadMore();
                        }

                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        LogUtils.debugInfo("====onRestart");
        if (AdminHomeActivity.isStart && AdminHomeActivity.isTop) {
            startActivity(new Intent(this, AdminHomeActivity.class));
        }
    }
}
