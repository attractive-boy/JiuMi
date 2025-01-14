package com.miniapp.talks.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;

import com.miniapp.talks.bean.PullRefreshBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 处理下拉刷新和上拉加载时的数据,已经处理了有无数据显示的view，加载更多时列表的刷新，下拉时列表的刷新
 * @param <T>
 */
public class DealRefreshHelper<T> {

    /**
     *
     * @param stfLayout 下拉刷新的布局，smartRefresh
     * @param adapter 适配器
     * @param lyViewNoData 暂无数据的view
     * @param serverData 服务器返回的数据
     * @param localData 本地数据
     * @param pullRefreshBean 下拉刷新的页码页数控制
     */
    public void dealDataToUI(@NotNull SmartRefreshLayout stfLayout, @NotNull RecyclerView.Adapter adapter, View lyViewNoData, List<T> serverData, @NotNull List<T> localData, PullRefreshBean pullRefreshBean) {

        if(stfLayout == null || adapter == null || localData == null){
            return;
        }

        stfLayout.finishLoadMore();

        stfLayout.finishRefresh();

        if(serverData != null){

            if(serverData.size()==0){

                stfLayout.setEnableLoadMore(false);

            }
                if(pullRefreshBean.isRefreshing){

                    localData.clear();

                }

                localData.addAll(serverData);

                adapter.notifyDataSetChanged();

                if(serverData.size()<pullRefreshBean.pageSize){//这次返回的数据小于pagesize,没有更多了

                    stfLayout.setEnableLoadMore(false);
                }
        } else {

            if(pullRefreshBean.isLoadMoreing){

                pullRefreshBean.pageIndex--;

            }
        }

        if(localData.size() ==0){//显示无数据的view

//            localData.clear();
//
//            adapter.notifyDataSetChanged();

            if(lyViewNoData != null){
                lyViewNoData.setVisibility(View.VISIBLE);
            }

        } else {//显示有数据的view
            if(lyViewNoData != null){
                lyViewNoData.setVisibility(View.GONE);
            }
        }

        pullRefreshBean.isLoadMoreing = false;

        pullRefreshBean.isRefreshing = false;

    }

    /**
     *
     * @param stfLayout 下拉刷新的布局，smartRefresh
     * @param adapter 适配器
     * @param lyViewNoData 暂无数据的view
     * @param serverData 服务器返回的数据
     * @param localData 本地数据
     * @param pullRefreshBean 下拉刷新的页码页数控制
     */
    public void dealDataToUI(@NotNull SmartRefreshLayout stfLayout, @NotNull BaseAdapter adapter, View lyViewNoData, List<T> serverData, @NotNull List<T> localData, PullRefreshBean pullRefreshBean) {

        if(stfLayout == null || adapter == null || localData == null){
            return;
        }

        stfLayout.finishLoadMore();

        stfLayout.finishRefresh();

        if(serverData != null){

            if(serverData.size()==0){

                stfLayout.setEnableLoadMore(false);

            }
                if(pullRefreshBean.isRefreshing){

                    localData.clear();

                }

                localData.addAll(serverData);

                adapter.notifyDataSetChanged();

                if(serverData.size()<pullRefreshBean.pageSize){//这次返回的数据小于pagesize,没有更多了

                    stfLayout.setEnableLoadMore(false);

                }
        } else {

            if(pullRefreshBean.isLoadMoreing){

                pullRefreshBean.pageIndex--;

            }
        }

        if(localData.size() ==0){//显示无数据的view

//            localData.clear();
//
//            adapter.notifyDataSetChanged();

            if(lyViewNoData != null){
                lyViewNoData.setVisibility(View.VISIBLE);
            }

        } else {//显示有数据的view
            if(lyViewNoData != null){
                lyViewNoData.setVisibility(View.GONE);
            }
        }

        pullRefreshBean.isLoadMoreing = false;

        pullRefreshBean.isRefreshing = false;

    }

}
