package com.miniapp.talks.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.NetMusciAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.LocalMusicInfo;
import com.miniapp.talks.bean.Yinxiao;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.view.ShapeTextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 作者:sgm
 * 描述:网络音乐（音乐库）
 */
public class NetMuscicFragment extends MyBaseArmFragment {
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    CommonModel commonModel;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private NetMusciAdapter localMusciAdapter;
    private String keywords = "";
    private int page = 1;

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
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_net_music);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        localMusciAdapter = new NetMusciAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(localMusciAdapter);
        loadData();

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            keywords = editName.getText().toString().trim();
            page = 1;
            loadData();
        });

        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            keywords = editName.getText().toString().trim();
            page++;
            loadData();
        });
        btnOk.setOnClickListener(v -> {
            keywords = editName.getText().toString().trim();
            loadData();
        });

        localMusciAdapter.setOnItemClickListener((adapter, view, position) -> {
            LocalMusicInfo localMusicInfo = new LocalMusicInfo();
            localMusicInfo.name = localMusciAdapter.getData().get(position).getMusic_name();
            localMusicInfo.adminUser = localMusciAdapter.getData().get(position).getSinger();
            localMusicInfo.songUrl = localMusciAdapter.getData().get(position).getMusic_url();
            localMusicInfo.size = localMusciAdapter.getData().get(position).music_size;
            localMusicInfo.isNet = true;
            localMusicInfo.save();
            EventBus.getDefault().post(new FirstEvent("指定发送", Constant.YINYUESHUAXIN));
            showToast("添加成功！");
        });
    }

    private void loadData() {
        RxUtils.loading(commonModel.get_music(keywords, page + "", UserManager.getUser().getUserId()+ ""), this)
                .subscribe(new ErrorHandleSubscriber<Yinxiao>(mErrorHandler) {
                    @Override
                    public void onNext(Yinxiao yinxiao) {
                        if (page == 1) {
                            refreshLayout.finishRefresh();
                            localMusciAdapter.setNewData(yinxiao.getData());
                        } else {
                            refreshLayout.finishLoadMore();
                            localMusciAdapter.addData(yinxiao.getData());
                        }

                    }
                });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

}
