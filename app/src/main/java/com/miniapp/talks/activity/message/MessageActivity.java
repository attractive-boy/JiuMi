package com.miniapp.talks.activity.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.SetActivity;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * 会话页面
 */
public class MessageActivity extends MyBaseArmActivity {

    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_right)
    RelativeLayout toolbarRight;
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
        return R.layout.activity_message;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        targetId = getIntent().getData().getQueryParameter("targetId");
//        LogUtils.debugInfo("====targetId:" + targetId);
//        FragmentManager fragmentManage = getSupportFragmentManager();
//        ConversationFragment fragement = (ConversationFragment) fragmentManage.findFragmentById(R.id.conversation);
//        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
//                .appendPath("conversation").appendPath(Conversation.ConversationType.PRIVATE.getName().toLowerCase())
//                .appendQueryParameter("targetId", ta).build();
//
//        fragement.setUri(uri);

//        RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.PRIVATE , targetId, title)
//        RongIM.getInstance().
        RongIM.setConversationBehaviorListener(new RongIM.ConversationBehaviorListener() {

            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {

                //在这里处理你想要跳转的activity，示例代码为YourAcitivy
//                Intent in = new Intent(context, SetActivity.class);
//                context.startActivity(in);
                Intent intent = new Intent(MessageActivity.this, MyPersonalCenterActivity.class);
                intent.putExtra("sign", 1);
                intent.putExtra("id", userInfo.getUserId());
                LogUtils.debugInfo("====要跳了哟");
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
                return false;
            }

            @Override
            public boolean onMessageClick(Context context, View view, Message message) {
                return false;
            }

            @Override
            public boolean onMessageLinkClick(Context context, String s) {
                return false;
            }

            @Override
            public boolean onMessageLongClick(Context context, View view, Message message) {
                return false;
            }
        });
        toolbarRight.setVisibility(View.VISIBLE);
        toolbarRight.setOnClickListener(v -> {
            Intent intent = new Intent(this, MessageSetActivity.class);
            intent.putExtra("targetId", targetId);
            ArmsUtils.startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String title = getIntent().getData().getQueryParameter("title");
        toolbarTitle.setText(title);
    }

}
