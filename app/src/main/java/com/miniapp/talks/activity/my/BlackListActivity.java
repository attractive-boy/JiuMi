package com.miniapp.talks.activity.my;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.BlackListAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.UserFriend;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.ItemDecorationUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 黑名单列表页面
 * 老王
 */
public class BlackListActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.heat_topic_recyc)
    RecyclerView heatTopicRecyc;
    @BindView(R.id.heat_topic_smat)
    SmartRefreshLayout heatTopicSmat;

    private BlackListAdapter blackListAdapter;
    private int pos;
    private int page = 0;

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
        blackListAdapter = new BlackListAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        ItemDecorationUtil did = new ItemDecorationUtil(this, ItemDecorationUtil.VERTICAL);
        heatTopicRecyc.addItemDecoration(did);
        heatTopicRecyc.setLayoutManager(llm);
        heatTopicRecyc.setAdapter(blackListAdapter);

        getBlackList(page);

        blackListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            setRemoveBlackList(String.valueOf(UserManager.getUser().getUserId()), String.valueOf(blackListAdapter.getData().get(position).getId()));
            pos = position;
        });
        heatTopicSmat.setOnRefreshListener(refreshlayout -> {
            page = 0;
            getBlackList(page);
        });
        heatTopicSmat.setOnLoadMoreListener(refreshlayout -> {
            page++;
            getBlackList(page);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("黑名单", true);
    }

    //获取黑名单列表
    private void getBlackList(int pagee) {
        RxUtils.loading(commonModel.blackList(String.valueOf(UserManager.getUser().getUserId()), String.valueOf(pagee)), this)
                .subscribe(new ErrorHandleSubscriber<UserFriend>(mErrorHandler) {
                    @Override
                    public void onNext(UserFriend userFriend) {
                        if (pagee == 0) {
                            heatTopicSmat.finishRefresh();
                            blackListAdapter.setNewData(userFriend.getData());
                        } else {
                            heatTopicSmat.finishLoadMore();
                            blackListAdapter.addData(userFriend.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        heatTopicSmat.finishRefresh();
                        heatTopicSmat.finishLoadMore();
                    }
                });
    }

    private void setRemoveBlackList(String userId, String fromId) {
        RxUtils.loading(commonModel.cancel_black(userId, fromId), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        toast(baseBean.getMessage());
                        blackListAdapter.remove(pos);
                        blackListAdapter.notifyDataSetChanged();
                    }
                });
    }
}
