package com.miniapp.talks.bean;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.jetbrains.annotations.NotNull;


/**
 * Author: mxy
 * <p>
 * ModifiedBy:
 * <p>
 * Date 2019-08-23 11:50
 * <p>
 * Description:刷新控制类
 */
public class PullRefreshBean {

    public int pageSize = 10;

    public int pageIndex = 1;

    public boolean isRefreshing = false;

    public boolean isLoadMoreing = false;

    private boolean isDisableRefresh = false;//是否禁止了下拉刷新

    private boolean isDisableLoadMore = false;//是否禁止了上拉加载

    public PullRefreshBean(){

    }

    /**
     *
     * @param disableRefresh 是否禁止了下拉刷新
     * @param disableLoadMore 是否禁止了上拉加载
     */
    public PullRefreshBean(boolean disableRefresh,boolean disableLoadMore){
        this.isDisableRefresh = disableRefresh;
        this.isDisableLoadMore = disableLoadMore;
    }

    /**
     * 加载更多时的设置
     * @param pullRefreshBean
     * @param smartRefreshLayout
     */
    public void setLoardMore(@NotNull PullRefreshBean pullRefreshBean, @NotNull SmartRefreshLayout smartRefreshLayout){

        if (pullRefreshBean.isRefreshing) {
            smartRefreshLayout.finishLoadMore();
            return;
        }

        pullRefreshBean.pageIndex++;

        pullRefreshBean.isLoadMoreing = true;

    }

    /**
     * 下拉刷新时的一些设置
     * @param pullRefreshBean
     * @param smartRefreshLayout
     */
    public void setRefresh(@NotNull PullRefreshBean pullRefreshBean, @NotNull SmartRefreshLayout smartRefreshLayout){

        if (pullRefreshBean.isLoadMoreing) {
            smartRefreshLayout.finishRefresh();
            return;
        }

        pullRefreshBean.pageIndex = 1;

        pullRefreshBean.isRefreshing = true;

        if(!isDisableLoadMore){//没有禁用加载更多
            smartRefreshLayout.setEnableLoadMore(true);
        }
    }

    /**
     * 设置是否禁用了下拉刷新
     * @param disableRefresh
     */
    public void setDisableRefresh(boolean disableRefresh){
        this.isDisableRefresh = disableRefresh;
    }

    /**
     * 设置是否禁用了上拉加载
     * @param disableLoadMore
     */
    public void setDisableLoadMore(boolean disableLoadMore){
        this.isDisableLoadMore = disableLoadMore;
    }
}
