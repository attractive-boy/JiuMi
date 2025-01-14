package com.miniapp.talks.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.jess.arms.di.component.AppComponent;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.AgrAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.BaseWebActivity;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.bean.AgreementBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.view.MyListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 平台协议页面
 */
public class AgreementActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.agr_list)
    MyListView agrList;
    private AgrAdapter mAdapter;

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
        return R.layout.activity_agreement;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mAdapter = new AgrAdapter(this);
        agrList.setAdapter(mAdapter);
        getAgr();
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("平台协议", true);
    }

    private void getAgr() {
        RxUtils.loading(commonModel.getAgreement(String.valueOf(3)), this)
                .subscribe(new ErrorHandleSubscriber<AgreementBean>(mErrorHandler) {
                    @Override
                    public void onNext(AgreementBean agreementBean) {
                        mAdapter.getList_adapter().addAll(agreementBean.getData());
                        mAdapter.notifyDataSetChanged();
                    }
                });

        agrList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(AgreementActivity.this, BaseWebActivity.class);
            intent.putExtra("url", mAdapter.getList_adapter().get(position).getUrl());
            startActivity(intent);
        });
    }
}
