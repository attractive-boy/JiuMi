package com.miniapp.talks.activity.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.adapter.MeZanAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.MeYiDuiBean;
import com.miniapp.talks.bean.RecommendedDynamicBean;
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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 我赞过的动态，我评论过的动态，我收藏过的动态
 */

public class MeZanActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recycleryview)
    RecyclerView recycleryview;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    @BindView(R.id.no_data_image)
    ImageView noDataImage;
    @BindView(R.id.no_data_text)
    TextView noDataText;
    @BindView(R.id.no_data)
    LinearLayout noData;

    private String ttt;
    private LoginData user;
    private MeZanAdapter meZanAdapter; //适配器
    private int pagea = 1;
    private int sharePos;

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
        return R.layout.activity_me_zan;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        Intent intent = getIntent();
        ttt = intent.getStringExtra("type");

        user = UserManager.getUser();

        meZanAdapter = new MeZanAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleryview.setLayoutManager(linearLayoutManager);
        DividerItemDecoration did = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        did.setDrawable(getResources().getDrawable(R.drawable.did_gray_dp8));
        recycleryview.addItemDecoration(did);
        recycleryview.setAdapter(meZanAdapter);

        switch (ttt) {
            case "1":
                noDataImage.setImageResource(R.mipmap.no_zan);
                noDataText.setText("还没有赞哦，快去互动吧~");
                getZan(1);

                smartrefreshlayout.setOnRefreshListener(refreshlayout -> {
                    getZan(1);
                });
                smartrefreshlayout.setOnLoadMoreListener(refreshlayout -> {
                    pagea++;
                    getZan(pagea);
                });
                break;
            case "2":
                noDataImage.setImageResource(R.mipmap.no_pl);
                noDataText.setText("还没有评论哦，快去互动吧~");
                getPingLun(1);
                smartrefreshlayout.setOnRefreshListener(refreshlayout -> {
                    getPingLun(1);
                });
                smartrefreshlayout.setOnLoadMoreListener(refreshlayout -> {
                    pagea++;
                    getPingLun(pagea);
                });
                break;
            case "3":
                noDataImage.setImageResource(R.mipmap.no_sc);
                noDataText.setText("还没有收藏哦，快去互动吧~");
                getSC(0);
                smartrefreshlayout.setOnRefreshListener(refreshlayout -> {
                    getSC(0);
                });
                smartrefreshlayout.setOnLoadMoreListener(refreshlayout -> {
                    pagea++;
                    getSC(pagea);
                });
                break;
        }
        itemClick();
    }

    @Override
    public void onResume() {
        super.onResume();
        switch (ttt) {
            case "1":
                setToolbarTitle("我赞过的动态", true);
                break;
            case "2":
                setToolbarTitle("我评论过的动态", true);
                break;
            case "3":
                setToolbarTitle("我收藏的动态", true);
                break;
        }
    }

    //适配器的点击事件
    private void itemClick() {
        meZanAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.dianzan:
                    List<MeYiDuiBean.DataBean> data = meZanAdapter.getData();
                    MeYiDuiBean.DataBean dataBean = data.get(position);
                    int id = dataBean.getId();
                    int is_praise = dataBean.getIs_praise();
                    if (is_praise == 1) {
                        cancelDynamic(user.getUserId() + "", id + "", 1 + "", "del", position, 1);
                    } else {
                        fbDynamic(user.getUserId() + "", id + "", 1 + "", "add", position, 1);
                    }
                    break;
                case R.id.dy_collection:
                    List<MeYiDuiBean.DataBean> data1 = meZanAdapter.getData();
                    MeYiDuiBean.DataBean dataBean1 = data1.get(position);
                    int id1 = dataBean1.getId();
                    int is_collect = dataBean1.getIs_collect();
                    if (is_collect == 1) {
                        cancelDynamic(user.getUserId() + "", id1 + "", 2 + "", "del", position, 2);
                    } else {
                        fbDynamic(user.getUserId() + "", id1 + "", 2 + "", "add", position, 2);
                    }
                    break;
                case R.id.pinglun:
                    Intent intent = new Intent(MeZanActivity.this, DynamicDetailsActivity.class);
                    intent.putExtra("id", meZanAdapter.getData().get(position).getId());
                    startActivity(intent);
                    break;
                case R.id.zhuanfa:
                    sharePos = position;
                    UMWeb web = new UMWeb("https://fir.im/q973");
                    web.setTitle("啾咪");//标题
                    web.setDescription("快来加入啾咪直播啦！");//描述
                    new ShareAction(MeZanActivity.this)
                            .withMedia(web)
                            .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                                    SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN)
                            .setCallback(shareListener)
                            .open();
                    break;
                case R.id.dy_head_image:
                    Intent intent1 = new Intent(MeZanActivity.this, MyPersonalCenterActivity.class);
                    if (meZanAdapter.getData().get(position).getUser_id() == UserManager.getUser().getUserId()) {
                        intent1.putExtra("sign", 0);
                        intent1.putExtra("id", "");
                    } else {
                        intent1.putExtra("sign", 1);
                        intent1.putExtra("id", meZanAdapter.getData().get(position).getUser_id() + "");
                    }
                    ArmsUtils.startActivity(intent1);
                    break;
            }
        });

        meZanAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<MeYiDuiBean.DataBean> data = meZanAdapter.getData();
            MeYiDuiBean.DataBean dataBean = data.get(position);
            Intent intent = new Intent(this, DynamicDetailsActivity.class);
            intent.putExtra("id", dataBean.getId());
            startActivity(intent);
        });
    }

    /**
     * 获取我赞过的动态
     */
    private void getZan(int page) {
        RxUtils.loading(commonModel.getMeYiDui(user.getUserId() + "", "", 1 + "", page + ""), this)
                .subscribe(new ErrorHandleSubscriber<MeYiDuiBean>(mErrorHandler) {
                    @Override
                    public void onNext(MeYiDuiBean meYiDuiBean) {
                        List<MeYiDuiBean.DataBean> data = meYiDuiBean.getData();
                        if (page == 1) {
                            smartrefreshlayout.finishRefresh();
                            if (data.size() == 0 || data == null) {
                                noData.setVisibility(View.VISIBLE);
                                smartrefreshlayout.setVisibility(View.GONE);
                            } else {
                                noData.setVisibility(View.GONE);
                                smartrefreshlayout.setVisibility(View.VISIBLE);
                                meZanAdapter.setNewData(data);
                            }
                        } else {
                            smartrefreshlayout.finishLoadMore();
                            if (data.size() != 0) {
                                meZanAdapter.addData(data);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        smartrefreshlayout.finishRefresh();
                        smartrefreshlayout.finishLoadMore();
                        noData.setVisibility(View.VISIBLE);
                        smartrefreshlayout.setVisibility(View.GONE);
                    }
                });
    }

    /**
     * 获取我评论过的动态
     */
    private void getPingLun(int page) {
        RxUtils.loading(commonModel.getMeYiDui(user.getUserId() + "", "", 4 + "", page + ""), this)
                .subscribe(new ErrorHandleSubscriber<MeYiDuiBean>(mErrorHandler) {
                    @Override
                    public void onNext(MeYiDuiBean meYiDuiBean) {
                        List<MeYiDuiBean.DataBean> data = meYiDuiBean.getData();
                        if (page == 1) {
                            smartrefreshlayout.finishRefresh();
                            if (data.size() == 0) {
                                noData.setVisibility(View.VISIBLE);
                                smartrefreshlayout.setVisibility(View.GONE);
                                recycleryview.setVisibility(View.GONE);
                            } else {
                                noData.setVisibility(View.GONE);
                                smartrefreshlayout.setVisibility(View.VISIBLE);
                                meZanAdapter.setNewData(data);

                            }
                        } else {
                            smartrefreshlayout.finishLoadMore();
                            if (data.size() != 0) {
                                meZanAdapter.addData(data);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        smartrefreshlayout.finishRefresh();
                        smartrefreshlayout.finishLoadMore();
                        noData.setVisibility(View.GONE);
                        smartrefreshlayout.setVisibility(View.VISIBLE);
                    }
                });
    }

    /**
     * 获取我收藏的状态
     */
    private void getSC(int page) {
        RxUtils.loading(commonModel.getMeYiDui(user.getUserId() + "", "", 2 + "", page + ""), this)
                .subscribe(new ErrorHandleSubscriber<MeYiDuiBean>(mErrorHandler) {
                    @Override
                    public void onNext(MeYiDuiBean meYiDuiBean) {
                        List<MeYiDuiBean.DataBean> data = meYiDuiBean.getData();
                        if (page == 1) {
                            smartrefreshlayout.finishRefresh();
                            if (data.size() == 0) {
                                noData.setVisibility(View.VISIBLE);
                                smartrefreshlayout.setVisibility(View.GONE);
                                recycleryview.setVisibility(View.GONE);
                            } else {
                                meZanAdapter.setNewData(data);
                                noData.setVisibility(View.GONE);
                                smartrefreshlayout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            smartrefreshlayout.finishLoadMore();
                            if (data.size() != 0) {
                                meZanAdapter.addData(data);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        smartrefreshlayout.finishRefresh();
                        smartrefreshlayout.finishLoadMore();
                        noData.setVisibility(View.VISIBLE);
                        smartrefreshlayout.setVisibility(View.GONE);
                    }
                });
    }

    /**
     * 点赞
     *
     * @param userId
     * @param targetId
     * @param type
     * @param hand
     * @param pos
     * @param a        a=1 点赞 a=2 收藏
     */
    private void fbDynamic(String userId, String targetId, String type, String hand, int pos, int a) {

        RxUtils.loading(commonModel.dynamic_praise(userId, targetId, type, hand), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (a == 1) {
                            meZanAdapter.notifyItemChanged(pos, "like");
                        } else if (a == 2) {
                            meZanAdapter.notifyItemChanged(pos, "likeSC");
                        }

                    }
                });
    }

    /**
     * @param userId
     * @param targetId
     * @param type
     * @param hand
     * @param pos
     * @param a        a=1 取消点赞, a=2 取消收藏
     */
    private void cancelDynamic(String userId, String targetId, String type, String hand, int pos, int a) {
        RxUtils.loading(commonModel.dynamic_praise(userId, targetId, type, hand), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (a == 1) {
//                            List<MeYiDuiBean.DataBean> data = meZanAdapter.getData();
//                            MeYiDuiBean.DataBean dataBean = data.get(pos);
//                            dataBean.setIs_praise(0);
//                            dataBean.setPraise_num(dataBean.getPraise_num() - 1);
                            meZanAdapter.notifyItemChanged(pos, "unlike");
                        } else if (a == 2) {
//                            List<MeYiDuiBean.DataBean> data = meZanAdapter.getData();
//                            MeYiDuiBean.DataBean dataBean = data.get(pos);
//                            dataBean.setIs_collect(0);
                            meZanAdapter.notifyItemChanged(pos, "unlikeSC");
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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
            Toast.makeText(MeZanActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MeZanActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    private void fenxiang() {
        RxUtils.loading(commonModel.fenxiang(String.valueOf(UserManager.getUser().getUserId()), String.valueOf(meZanAdapter.getData().get(sharePos).getId()), "add"), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        if (commentBean.getCode() == 1) {
                            showToast(commentBean.getMessage());
                            LogUtils.debugInfo("====成功没有", sharePos + "");
                            meZanAdapter.getData().get(sharePos).setForward_num(meZanAdapter.getData().get(sharePos).getForward_num() + 1);
//                            commDynamicAdapter.notifyItemChanged(sharePos, "share");
//                            commDynamicAdapter.notifyDataSetChanged();
                            recycleryview.setAdapter(meZanAdapter);
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
            if (meZanAdapter != null && meZanAdapter.getData() != null) {
                List<MeYiDuiBean.DataBean> data = meZanAdapter.getData();
                for (int a = 0; a < data.size(); a++) {
                    if (split[0].equals(String.valueOf(data.get(a).getId()))) {
                        data.get(a).setTalk_num(Integer.parseInt(split[1]));
                        meZanAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }
        }
    }
}
