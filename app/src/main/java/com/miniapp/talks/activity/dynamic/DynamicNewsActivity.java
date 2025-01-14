package com.miniapp.talks.activity.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.adapter.MessageAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.MessageYoBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 动态模块内的消息
 */

public class DynamicNewsActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.me_zan)
    RelativeLayout meZan;
    @BindView(R.id.me_pl)
    RelativeLayout mePl;
    @BindView(R.id.me_sc)
    RelativeLayout meSc;
    @BindView(R.id.interaction_zan)
    RelativeLayout interactionZan;
    @BindView(R.id.interaction_sc)
    RelativeLayout interactionSc;
    @BindView(R.id.interaction_fx)
    RelativeLayout interactionFx;
    @BindView(R.id.mylist)
    RecyclerView myList;
    @BindView(R.id.have_zan)
    TextView haveZan;
    @BindView(R.id.have_sc)
    TextView haveSc;
    @BindView(R.id.have_fx)
    TextView haveFx;

    private Intent intent;
    private LoginData user;
    private MessageAdapter messageAdapter;

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
        return R.layout.activity_dynamic_news;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        user = UserManager.getUser();

        messageAdapter = new MessageAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myList.setLayoutManager(linearLayoutManager);
        myList.setAdapter(messageAdapter);

        getMessageDetails();
        getItemClick();
    }

    //点击事件
    @OnClick({R.id.me_zan, R.id.me_pl, R.id.me_sc, R.id.gf_news})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_zan:
                intent = new Intent(DynamicNewsActivity.this, MeZanActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.me_pl:
                intent = new Intent(DynamicNewsActivity.this, MeZanActivity.class);
                intent.putExtra("type", "2");
                startActivity(intent);
                break;
            case R.id.me_sc:
                intent = new Intent(DynamicNewsActivity.this, MeZanActivity.class);
                intent.putExtra("type", "3");
                startActivity(intent);
                break;
            case R.id.gf_news:
                Intent intent = new Intent(DynamicNewsActivity.this, HeatTopicActivity.class);
                intent.putExtra("tag", "1");
                ArmsUtils.startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("消息", true);
    }

    //消息
    private void getMessageDetails() {
        RxUtils.loading(commonModel.getMessageYo(user.getUserId() + ""), this)
                .subscribe(new ErrorHandleSubscriber<MessageYoBean>(mErrorHandler) {
                    @Override
                    public void onNext(MessageYoBean messageYoBean) {
                        MessageYoBean.DataBean data = messageYoBean.getData();
                        haveZan.setText("有" + data.getSign() + "人赞了您的动态~");
                        haveSc.setText("有" + data.getColl() + "人收藏了您的动态~");
                        haveFx.setText("有" + data.getShare() + "人分享了您的动态~");
                        List<MessageYoBean.DataBean.CommentBean> comment = data.getComment();
                        messageAdapter.addData(comment);
                    }
                });
    }

    private void getItemClick() {
        messageAdapter.setOnItemClickListener((adapter, view, position) -> {
            MessageYoBean.DataBean.CommentBean commentBean = messageAdapter.getData().get(position);
            Intent intent = new Intent(DynamicNewsActivity.this, CommentDetailsActivity.class);
            intent.putExtra("id", commentBean.getB_dynamic_id());
            intent.putExtra("userId", commentBean.getUser_id());
            startActivity(intent);
        });
        messageAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Intent intent1 = new Intent(DynamicNewsActivity.this, MyPersonalCenterActivity.class);
            if (messageAdapter.getData().get(position).getUser_id() == UserManager.getUser().getUserId()) {
                intent1.putExtra("sign", 0);
                intent1.putExtra("id", "");
            } else {
                intent1.putExtra("sign", 1);
                intent1.putExtra("id", messageAdapter.getData().get(position).getUser_id() + "");
            }
            ArmsUtils.startActivity(intent1);
        });
    }
}
