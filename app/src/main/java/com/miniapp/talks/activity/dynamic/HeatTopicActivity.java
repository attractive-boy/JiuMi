package com.miniapp.talks.activity.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.ActiveListAdapter;
import com.miniapp.talks.adapter.HeatTopicAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.BaseWebActivity;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.ActiveListBean;
import com.miniapp.talks.bean.TopicBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.SpaceItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 热门话题
 * 老王
 */

public class HeatTopicActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.heat_topic_recyc)
    RecyclerView heatTopicRecyc;
    @BindView(R.id.heat_topic_smat)
    SmartRefreshLayout heatTopicSmat;
    @BindView(R.id.no_data_image)
    ImageView noDataImage;
    @BindView(R.id.no_data_text)
    TextView noDataText;
    @BindView(R.id.no_data)
    LinearLayout noData;

    private String tag;

    private HeatTopicAdapter heatTopicAdapter;
    private ActiveListAdapter activeListAdapter;

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
        return R.layout.activity_heat_topic;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        tag = getIntent().getStringExtra("tag");
        if (tag.equals("0")) {
            setRv();
            heatTopicAdapter.setOnItemClickListener((adapter, view, position) -> {
                Intent intent = new Intent(this, TopicTrendsActivity.class);
                intent.putExtra("tags", heatTopicAdapter.getData().get(position).getTags());
                intent.putExtra("tagsName", heatTopicAdapter.getData().get(position).getTag_name());
                startActivity(intent);
            });
        } else {
            setSQHuoDong();
            activeListAdapter.setOnItemClickListener((adapter, view, position) -> {
                Intent intent = new Intent(this, BaseWebActivity.class);
                intent.putExtra("url", activeListAdapter.getData().get(position).getUrl());
                ArmsUtils.startActivity(intent);
            });
        }


        //下拉刷新
        heatTopicSmat.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });

        //下拉加载setOnLoadMoreListener
        heatTopicSmat.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        if (tag.equals("0")) {
            setToolbarTitle("热门话题", true);
        } else {
            setToolbarTitle("社区活动", true);
        }

    }

    //设置热门话题的RecyclerView
    private void setRv() {
        heatTopicAdapter = new HeatTopicAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        heatTopicRecyc.setLayoutManager(linearLayoutManager);
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(0, 20);
        heatTopicRecyc.addItemDecoration(spaceItemDecoration);
        heatTopicRecyc.setAdapter(heatTopicAdapter);
        getHeatTopic();
    }

    //设置社区活动的RV
    private void setSQHuoDong() {
        activeListAdapter = new ActiveListAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        heatTopicRecyc.setLayoutManager(linearLayoutManager);
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(0, 20);
        heatTopicRecyc.addItemDecoration(spaceItemDecoration);
        heatTopicRecyc.setAdapter(heatTopicAdapter);
        getSQHD();
    }

    //获取热门话题
    private void getHeatTopic() {
        RxUtils.loading(commonModel.topic("all"), this)
                .subscribe(new ErrorHandleSubscriber<TopicBean>(mErrorHandler) {
                    @Override
                    public void onNext(TopicBean topicBean) {
                        if (topicBean.getData().size() == 0) {
                            heatTopicSmat.setVisibility(View.GONE);
                            noData.setVisibility(View.VISIBLE);
                            noDataText.setText("暂时还没有热门话题哟~");
                        } else {
                            heatTopicSmat.setVisibility(View.VISIBLE);
                            noData.setVisibility(View.GONE);
                            heatTopicAdapter.setNewData(topicBean.getData());
                        }
                    }
                });
    }

    //获取社区活动
    private void getSQHD() {
        RxUtils.loading(commonModel.activeList("xx"), this)
                .subscribe(new ErrorHandleSubscriber<ActiveListBean>(mErrorHandler) {
                    @Override
                    public void onNext(ActiveListBean activeListBean) {
                        if (activeListBean.getData().size() == 0) {
                            heatTopicSmat.setVisibility(View.GONE);
                            noData.setVisibility(View.VISIBLE);
                            noDataText.setText("暂时还没有社区活动哟~");
                        } else {
                            heatTopicSmat.setVisibility(View.VISIBLE);
                            noData.setVisibility(View.GONE);
                            activeListAdapter.setNewData(activeListBean.getData());
                        }
                    }
                });
    }
}
