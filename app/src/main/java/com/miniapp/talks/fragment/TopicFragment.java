package com.miniapp.talks.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.dynamic.CommentDetailsActivity;
import com.miniapp.talks.activity.dynamic.DynamicDetailsActivity;
import com.miniapp.talks.activity.dynamic.TopicTrendsActivity;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.adapter.CommDynamicAdapter;
import com.miniapp.talks.adapter.TopicAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.HeaderViewPagerFragment;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.MeYiDuiBean;
import com.miniapp.talks.bean.RecommendedDynamicBean;
import com.miniapp.talks.bean.TopicDynamicBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 话题内的动态
 */
public class TopicFragment extends HeaderViewPagerFragment {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
//    @BindView(R.id.smart)
//    SmartRefreshLayout smart;

    int page = 1;


    private TopicAdapter topicAdapter;
    private LoginData user;
    private String tags;
    public int id, sharePos;
    private List<TopicDynamicBean.DataBean.DynamicsBean> dynamicsList;

    public static TopicFragment getInstance(int tag, String tags) {
        TopicFragment myFragmentFuYong = new TopicFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", tag);
        bundle.putString("tags", tags);
        myFragmentFuYong.setArguments(bundle);
        return myFragmentFuYong;
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
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View scrollView = ArmsUtils.inflate(getActivity(), R.layout.fragment_topic);
        return scrollView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        dynamicsList = new ArrayList<>();

        id = getArguments().getInt("id");
        tags = getArguments().getString("tags");
        user = UserManager.getUser();

        topicAdapter = new TopicAdapter(getActivity());
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration did = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        did.setDrawable(getResources().getDrawable(R.drawable.did_gray_dp8));
        recyclerview.addItemDecoration(did);
        recyclerview.setLayoutManager(lm);
        recyclerview.setAdapter(topicAdapter);

        if (id == 1) {
            setHeadBackgroundTui(0, "hot",null);
        } else if (id == 2) {
            dynamicsList.clear();
            setHeadBackgroundTui(0, "new",null);
        }

        itemClick();
    }

    public void onRefresh(final SmartRefreshLayout refreshLayout) {
        page = 1;
        if (id == 1) {
            setHeadBackgroundTui(page, "hot",refreshLayout);
        } else if (id == 2) {
            dynamicsList.clear();
            setHeadBackgroundTui(page, "new",refreshLayout);
        }
    }

    public void onLoadMore(final SmartRefreshLayout refreshLayout) {
        page++;
        if (id == 1) {
            setHeadBackgroundTui(page, "hot",refreshLayout);
        } else if (id == 2) {
            dynamicsList.clear();
            setHeadBackgroundTui(page, "new",refreshLayout);
        }
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public View getScrollableView() {
        return recyclerview;
    }

    //获取话题内动态(推荐)
    private void setHeadBackgroundTui(int page, String type,SmartRefreshLayout refreshLayout) {
        RxUtils.loading(commonModel.topic_dynamic(tags, user.getUserId() + "", page + "", type), this)
                .subscribe(new ErrorHandleSubscriber<TopicDynamicBean>(mErrorHandler) {
                    @Override
                    public void onNext(TopicDynamicBean topicDynamicBean) {
                        if(page == 1){
                            if (refreshLayout != null) {
                                refreshLayout.finishRefresh();
                            }
                            topicAdapter.getData().clear();
                            topicAdapter.setNewData(topicDynamicBean.getData().getDynamics());
                            topicAdapter.notifyDataSetChanged();
                        } else {
                            if (refreshLayout != null) {
                                refreshLayout.finishLoadMore();
                            }
                            topicAdapter.addData(topicDynamicBean.getData().getDynamics());
                            topicAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    //点赞 收藏 a=1 点赞 a=2 收藏
    private void fbDynamic(String userId, String targetId, String type, String hand, int pos, int a) {

        RxUtils.loading(commonModel.dynamic_praise(userId, targetId, type, hand), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (a == 1) {
                            topicAdapter.notifyItemChanged(pos, "like");
                        } else if (a == 2) {
                            topicAdapter.notifyItemChanged(pos, "collect");
                        }

                    }
                });
    }

    //取消点赞,收藏 a=1 取消点赞 a=2 取消收藏
    private void cancelDynamic(String userId, String targetId, String type, String hand, int pos, int a) {
        RxUtils.loading(commonModel.dynamic_praise(userId, targetId, type, hand), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (a == 1) {
                            topicAdapter.notifyItemChanged(pos, "unlike");
                        } else if (a == 2) {
                            topicAdapter.notifyItemChanged(pos, "uncollect");
                        }
                    }
                });
    }

    //适配器的点击事件
    private void itemClick() {
        topicAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.dianzan:
                    List<TopicDynamicBean.DataBean.DynamicsBean> data = topicAdapter.getData();
                    TopicDynamicBean.DataBean.DynamicsBean dataBean = data.get(position);
                    int id = dataBean.getId();
                    int is_praise = dataBean.getIs_praise();
                    if (is_praise == 1) {
                        cancelDynamic(user.getUserId() + "", id + "", 1 + "", "del", position, 1);
                    } else {
                        fbDynamic(user.getUserId() + "", id + "", 1 + "", "add", position, 1);
                    }
                    break;
                case R.id.dy_collection:
                    List<TopicDynamicBean.DataBean.DynamicsBean> data1 = topicAdapter.getData();
                    TopicDynamicBean.DataBean.DynamicsBean dataBean1 = data1.get(position);
                    int id1 = dataBean1.getId();
                    int is_collect = dataBean1.getIs_collect();
                    if (is_collect == 1) {
                        cancelDynamic(user.getUserId() + "", id1 + "", 2 + "", "del", position, 2);
                    } else {
                        fbDynamic(user.getUserId() + "", id1 + "", 2 + "", "add", position, 2);
                    }
                    break;
                case R.id.pinglun:
                    Intent intent = new Intent(getContext(), DynamicDetailsActivity.class);
                    intent.putExtra("id", topicAdapter.getData().get(position).getId());
                    startActivity(intent);
                    break;
                case R.id.zhuanfa:
                    sharePos = position;
                    UMWeb web = new UMWeb("https://fir.im/q973");
                    web.setTitle("啾咪");//标题
                    web.setDescription("快来加入啾咪直播啦！");//描述
                    new ShareAction(getActivity())
                            .withMedia(web)
                            .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                                    SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN)
                            .setCallback(shareListener)
                            .open();
                    break;
                case R.id.dy_head_image:
                    Intent intent1 = new Intent(getContext(), MyPersonalCenterActivity.class);
                    if (topicAdapter.getData().get(position).getUser_id() == UserManager.getUser().getUserId()) {
                        intent1.putExtra("sign", 0);
                        intent1.putExtra("id", "");
                    } else {
                        intent1.putExtra("sign", 1);
                        intent1.putExtra("id", topicAdapter.getData().get(position).getUser_id() + "");
                    }
                    ArmsUtils.startActivity(intent1);
                    break;
            }
        });

        topicAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<TopicDynamicBean.DataBean.DynamicsBean> data = topicAdapter.getData();
            TopicDynamicBean.DataBean.DynamicsBean dynamicsBean = data.get(position);
            Intent intent = new Intent(getContext(), DynamicDetailsActivity.class);
            intent.putExtra("id", dynamicsBean.getId());
            startActivity(intent);
        });
    }


    private UMShareListener shareListener = new UMShareListener() {

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            fenxiang();
        }


        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();

        }
    };

    private void fenxiang() {
        RxUtils.loading(commonModel.fenxiang(String.valueOf(UserManager.getUser().getUserId()), String.valueOf(topicAdapter.getData().get(sharePos).getId()), "add"), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        if (commentBean.getCode() == 1) {
                            showToast(commentBean.getMessage());
//                            topicAdapter.getData().get(sharePos).setForward_num(topicAdapter.getData().get(sharePos).getForward_num() + 1);
                            topicAdapter.notifyItemChanged(sharePos, "share");
//                            commDynamicAdapter.notifyDataSetChanged();
//                            recyclerview.setAdapter(topicAdapter);
                        }
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();

        if ("pinglunchenggong".equals(tag)) {
            String msg = event.getMsg();
            LogUtils.debugInfo("====接收", msg);
            String[] split = msg.split(",");
            if (split.length != 2) {
                return;
            }
            if (topicAdapter != null && topicAdapter.getData() != null) {
                List<TopicDynamicBean.DataBean.DynamicsBean> data = topicAdapter.getData();
                for (int a = 0; a < data.size(); a++) {
                    if (split[0].equals(String.valueOf(data.get(a).getId()))) {
                        data.get(a).setTalk_num(Integer.parseInt(split[1]));
                        topicAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }
        }
    }
}
