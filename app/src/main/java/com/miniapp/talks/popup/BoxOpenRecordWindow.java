package com.miniapp.talks.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.miniapp.talks.R;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.BoxOpenRecordAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.BoxExchangeBean;
import com.miniapp.talks.bean.BoxOpenRecordBean;
import com.miniapp.talks.bean.MyPackBean;
import com.miniapp.talks.bean.PullRefreshBean;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.DealRefreshHelper;
import com.miniapp.talks.utils.MyUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
//开奖记录
public class BoxOpenRecordWindow extends PopupWindow {
    private AdminHomeActivity mContext;
    private RecyclerView recyclerView;
    private SmartRefreshLayout mSm;
    private CommonModel commonModel;
    private RxErrorHandler mRxErrorHandler;
    private BoxOpenRecordAdapter mAdapter;
    private List<BoxOpenRecordBean.DataBean> mDataList = new ArrayList<>();
    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();

    public BoxOpenRecordWindow(Activity context, ConstraintLayout view, CommonModel commonModel, RxErrorHandler rxErrorHandler) {
        super(context);
        this.mContext = (AdminHomeActivity) context;
        this.commonModel = commonModel;
        this.mRxErrorHandler = rxErrorHandler;
        View view1 = LayoutInflater.from(context).inflate(R.layout.box_open_record_window, null);
        recyclerView = view1.findViewById(R.id.recyclerview);
        mSm = view1.findViewById(R.id.sm);
        // 设置SelectPicPopupWindow的View
        this.setContentView(view1);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
//        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        //设置透明
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = 0.7f;
        this.setWidth(view.getMeasuredWidth() - MyUtil.dip2px(context, 100));
        this.setHeight(view.getMeasuredHeight() - 100);
        context.getWindow().setAttributes(params);

        mAdapter = new BoxOpenRecordAdapter(R.layout.box_open_record_item, mDataList);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mAdapter);
        actionAwardExchange();
        mSm.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, mSm);//加载更多时的处理
                actionAwardExchange();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, mSm);//加载更多时的处理
                actionAwardExchange();
            }
        });
    }

    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = mContext;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
    }

    //获取纪录
    private void actionAwardExchange() {
        RxUtils.loading(commonModel.getAwardRecordList(mPullRefreshBean.pageIndex), mContext)
                .subscribe(new ErrorHandleSubscriber<BoxOpenRecordBean>(mContext.mErrorHandler) {
                    @Override
                    public void onNext(BoxOpenRecordBean boxOpenRecordBean) {
                        List<BoxOpenRecordBean.DataBean> data = boxOpenRecordBean.getData();
                        new DealRefreshHelper<BoxOpenRecordBean.DataBean>().dealDataToUI(mSm, mAdapter, null, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<BoxOpenRecordBean.DataBean>().dealDataToUI(mSm, mAdapter, null, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }
}
