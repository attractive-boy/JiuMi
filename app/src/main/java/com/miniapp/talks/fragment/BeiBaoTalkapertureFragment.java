package com.miniapp.talks.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.Interface.MyPackBaoShiInter;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.BeiBaoRecycAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.MyPackBean;
import com.miniapp.talks.bean.PullRefreshBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.DealRefreshHelper;
import com.miniapp.talks.view.ShapeTextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.QIEHUANTWO;
import static com.miniapp.talks.utils.Constant.QUXIAOYUYINKUANG;
import static com.miniapp.talks.utils.Constant.QUXIAOZHUANGBANTOUXIANG;
import static com.miniapp.talks.utils.Constant.YULANTOUXIANGKUANG;
import static com.miniapp.talks.utils.Constant.ZHUANGBANCHENGGONGTOUXIANG;
import static com.miniapp.talks.utils.Constant.ZHUANGBANYUYINKUANG;

/**
 * 我的背包----麦上光圈
 * 老王
 */
public class BeiBaoTalkapertureFragment extends MyBaseArmFragment {
    @Inject
    CommonModel commonModel;

    @BindView(R.id.beibao_recyc)
    RecyclerView recyclerView;
    @BindView(R.id.beibao_smart)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.no_data)
    LinearLayout noData;

    private BeiBaoRecycAdapter mAdapter; //适配器
    private List<MyPackBean.DataBean> mDataList = new ArrayList<>();
    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();

    MyPackBaoShiInter.onListenerTwo mOnPageChangeLister;

    public void setmOnPageChangeLister(MyPackBaoShiInter.onListenerTwo lister) {
        this.mOnPageChangeLister = lister;
    }

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gem, container, false);
        return view;
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

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    //懒加载
    @Override
    protected void visibleToLoadData() {
        super.visibleToLoadData();
        //recyclerview 关联adapter
        mAdapter = new BeiBaoRecycAdapter(R.layout.beibao_recyclerview_item_two, mDataList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);
        //获取数据
        getMyPackBaoShi();
        //禁止上拉加载
        smartRefreshLayout.setEnableLoadMore(false);
        mPullRefreshBean.setDisableLoadMore(true);
        //下拉刷新
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPullRefreshBean.setRefresh(mPullRefreshBean, smartRefreshLayout);//下拉刷新时 的处理
            getMyPackBaoShi();
        });
        //adapter item点击事件
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            for (int a = 0; a < mDataList.size(); a++) {
                mDataList.get(a).setSelect(false);
            }
            mDataList.get(position).setSelect(true);
            mAdapter.notifyDataSetChanged();
            if (mOnPageChangeLister != null) {
                mOnPageChangeLister.OnListener(mAdapter.getData().get(position), 1, 4);
            }
            EventBus.getDefault().post(new FirstEvent(mAdapter.getData().get(position), YULANTOUXIANGKUANG));
        });
    }

    //获取数据
    private void getMyPackBaoShi() {
        RxUtils.loading(commonModel.my_pack("7"), this)
                .subscribe(new ErrorHandleSubscriber<MyPackBean>(mErrorHandler) {
                    @Override
                    public void onNext(MyPackBean myPackBean) {
                        List<MyPackBean.DataBean> list = myPackBean.getData();
                        //设置数据，包括加载更多，下拉刷新，无数据的情况都处理过了
                        new DealRefreshHelper<MyPackBean.DataBean>().dealDataToUI(smartRefreshLayout, mAdapter, noData, list, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<MyPackBean.DataBean>().dealDataToUI(smartRefreshLayout, mAdapter, noData, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (ZHUANGBANYUYINKUANG.equals(tag)) {
            mPullRefreshBean.setRefresh(mPullRefreshBean, smartRefreshLayout);
            getMyPackBaoShi();
        } else if (QUXIAOYUYINKUANG.equals(tag)) {
            mPullRefreshBean.setRefresh(mPullRefreshBean, smartRefreshLayout);
            getMyPackBaoShi();
        } else if (QIEHUANTWO.equals(tag)) {
            if ("4".equals(event.getMsg())) {
                for (int a = 0; a < mDataList.size(); a++) {
                    mDataList.get(a).setSelect(false);
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
