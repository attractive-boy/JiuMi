package com.miniapp.talks.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.message.MessageOfficeActivity;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmFragment;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.MiniOfficBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imlib.model.Conversation;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.miniapp.talks.utils.Constant.SHUAXINGUANFANGXIAOXI;

/**
 * 作者:sgm
 * 描述:聊天列表
 */
public class MessageFragment extends MyBaseArmFragment{


    @BindView(R.id.ci_head)
    ImageView ciHead;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_userid)
    TextView tvUserid;
    @BindView(R.id.textTime)
    TextView textTime;
    @BindView(R.id.textNum)
    TextView textNum;
    @BindView(R.id.llRight)
    LinearLayout llRight;
    @BindView(R.id.relativeLayout_main)
    LinearLayout relativeLayoutMain;
    @BindView(R.id.llTop)
    LinearLayout llTop;

    @Inject
    CommonModel commonModel;
    private MessageHeaderFrament mConversationListFragment;

    MyReceiver mMyReceiver;

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
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_item_message);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mMyReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("has_read_office");
        mContext.registerReceiver(mMyReceiver, filter);

        loadData();
    }

    private void loadData() {
        RxUtils.loading(commonModel.mini_official(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<MiniOfficBean>(mErrorHandler) {
                    @Override
                    public void onNext(MiniOfficBean miniOfficBean) {
                        initHeaderData(miniOfficBean);
                        mConversationListFragment = new MessageHeaderFrament();
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("miniOfficBean",miniOfficBean);
//                        mConversationListFragment.setArguments(bundle);
                        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                                .appendPath("conversationlist")
                                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")
//                                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")
                                .build();
                        mConversationListFragment.setUri(uri);
                        FragmentManager manager = getChildFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.relativeLayout_main, mConversationListFragment);
                        transaction.commitAllowingStateLoss();

                    }
                });
    }

    private void initHeaderData(MiniOfficBean miniOfficBean) {
        GlideArms.with(getActivity())
                .load(miniOfficBean.getData().getImg())
                .placeholder(R.mipmap.default_home)
                .error(R.mipmap.default_home)
                .circleCrop()
                .into(ciHead);
        tvTitle.setText(miniOfficBean.getData().getName());
        tvUserid.setText(miniOfficBean.getData().getTitle());
        int unread = miniOfficBean.getData().getUnread();
        if (unread > 0) {
            llRight.setVisibility(View.VISIBLE);
            textTime.setText(miniOfficBean.getData().getCreated_at());
            textNum.setText(miniOfficBean.getData().getUnread() + "");
        } else {
            llRight.setVisibility(View.GONE);
        }
        llTop.setOnClickListener(v -> {
            ArmsUtils.startActivity(MessageOfficeActivity.class);
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public boolean useEventBus() {
        return false;
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void receiveMsg(FirstEvent event) {
//        String tag = event.getTag();
//        if (SHUAXINGUANFANGXIAOXI.equals(tag)) {
//            RxUtils.loading(commonModel.mini_official(String.valueOf(UserManager.getUser().getUserId())), this)
//                    .subscribe(new ErrorHandleSubscriber<MiniOfficBean>(mErrorHandler) {
//                        @Override
//                        public void onNext(MiniOfficBean miniOfficBean) {
////                            mConversationListFragment.refresh(miniOfficBean);
//                            initHeaderData(miniOfficBean);
//                        }
//                    });
//
//        }
//    }

    public void hasRead() {
        RxUtils.loading(commonModel.mini_official(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<MiniOfficBean>(mErrorHandler) {
                    @Override
                    public void onNext(MiniOfficBean miniOfficBean) {
//                            mConversationListFragment.refresh(miniOfficBean);
                        initHeaderData(miniOfficBean);
                    }
                });
    }

    //已读了官方通知
    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtils.debugInfo("收到广播了=======");
            hasRead();
        }
    }


}
