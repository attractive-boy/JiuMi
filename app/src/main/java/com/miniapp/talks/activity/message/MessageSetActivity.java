package com.miniapp.talks.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.FollowBean;
import com.miniapp.talks.bean.OtherUser;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.popup.PuTongWindow;
import com.miniapp.talks.popup.ReportWindow;
import com.miniapp.talks.popup.RoomDialog;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.Constant;
import com.miniapp.talks.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class MessageSetActivity extends MyBaseArmActivity {


    @BindView(R.id.imgTop)
    ImageView imgTop;
    @BindView(R.id.llTop)
    LinearLayout llTop;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;
    @BindView(R.id.textHei)
    TextView textHei;
    @BindView(R.id.textReport)
    TextView textReport;

    @Inject
    CommonModel commonModel;
    private String targetId = "";

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
        return R.layout.activity_message_set;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        targetId = getIntent().getStringExtra("targetId");
        RongIM.getInstance().getConversation(Conversation.ConversationType.PRIVATE, targetId, new RongIMClient.ResultCallback<Conversation>() {
            @Override
            public void onSuccess(Conversation conversation) {
                if (conversation != null) {
                    if (conversation.isTop()) {
                        imgTop.setSelected(true);
                    } else {
                        imgTop.setSelected(false);
                    }
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });

        RxUtils.loading(commonModel.is_follow(
                String.valueOf(UserManager.getUser().getUserId()),targetId), this)
                .subscribe(new ErrorHandleSubscriber<FollowBean>(mErrorHandler) {
                    @Override
                    public void onNext(FollowBean otherUser) {
                        if(otherUser.getData().getIs_follow() == 1){
                            btnOk.setText("已关注");
                        }else {
                            btnOk.setText("关注");
                        }
                    }
                });
    }


    @OnClick({R.id.llTop, R.id.btn_ok, R.id.textHei, R.id.textReport})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llTop:
                if (imgTop.isSelected()) {
                    RongIM.getInstance().setConversationToTop(Conversation.ConversationType.PRIVATE, targetId, false,
                            new RongIMClient.ResultCallback<Boolean>() {
                                @Override
                                public void onSuccess(Boolean aBoolean) {
                                    imgTop.setSelected(false);
                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode errorCode) {

                                }
                            });
                } else {
                    RongIM.getInstance().setConversationToTop(Conversation.ConversationType.PRIVATE, targetId, true,
                            new RongIMClient.ResultCallback<Boolean>() {
                                @Override
                                public void onSuccess(Boolean aBoolean) {
                                    toast("置顶成功");
                                    imgTop.setSelected(true);
                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode errorCode) {

                                }
                            });
                }
                break;
            case R.id.btn_ok:
                if (btnOk.getText().equals("关注")) {
                    RxUtils.loading(commonModel.follow(String.valueOf(UserManager.getUser().getUserId()), targetId), this)
                            .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                @Override
                                public void onNext(BaseBean microphone) {
                                    btnOk.setText("已关注");
                                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.SHUAXINGUANZHU));
                                }
                            });
                } else {
                    RxUtils.loading(commonModel.cancel_follow(String.valueOf(UserManager.getUser().getUserId()), targetId), this)
                            .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                @Override
                                public void onNext(BaseBean microphone) {
                                    btnOk.setText("关注");
                                    EventBus.getDefault().post(new FirstEvent("指定发送", Constant.SHUAXINGUANZHU));
                                }
                            });
                }
                break;
            case R.id.textHei:
                PuTongWindow puTongWindow = new PuTongWindow(MessageSetActivity.this);
                puTongWindow.showAtLocation(textHei, Gravity.CENTER, 0, 0);
                puTongWindow.getTitText().setText("是否拉黑此人");
                puTongWindow.getCancel().setOnClickListener(v -> {
                    puTongWindow.dismiss();
                });
                puTongWindow.getSure().setOnClickListener(v -> {
                    RxUtils.loading(commonModel.pull_black(String.valueOf(UserManager.getUser().getUserId()), targetId), this)
                            .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                @Override
                                public void onNext(BaseBean microphone) {
                                    toast("拉黑成功");
                                    puTongWindow.dismiss();
                                }
                            });
                });

                break;
            case R.id.textReport:
                Intent intent = new Intent(this, ReportActivity.class);
                intent.putExtra("targetId", targetId);
                intent.putExtra("type", "1");
                ArmsUtils.startActivity(intent);
                break;
        }
    }
}
