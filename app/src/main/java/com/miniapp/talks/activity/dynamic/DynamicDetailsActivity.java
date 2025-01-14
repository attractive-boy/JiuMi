package com.miniapp.talks.activity.dynamic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.message.ReportActivity;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.adapter.AllCommentAdapter;
import com.miniapp.talks.adapter.CommentAdapter;
import com.miniapp.talks.adapter.OneImageYuanJiaoAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.bean.DynamicDetailsBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.popup.KeybordWindow;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.FullScreenUtil;
import com.miniapp.talks.utils.JudgeImageUtil;
import com.miniapp.talks.utils.MediaManager;
import com.miniapp.talks.utils.MyUtil;
import com.miniapp.talks.utils.TimeUtil;
import com.miniapp.talks.view.MyGridView;
import com.miniapp.talks.view.MyListView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 作者：老王
 * 描述：某条动态的详情页
 */

public class DynamicDetailsActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;
    @BindView(R.id.dy_head_image)
    CircularImage dyHeadImage;
    @BindView(R.id.dy_name_text)
    TextView dyNameText;
    @BindView(R.id.dy_sex_image)
    ImageView dySexImage;
    @BindView(R.id.dy_time_text)
    TextView dyTimeText;
    @BindView(R.id.dy_rank_image)
    ImageView dyRankImage;
    @BindView(R.id.dy_top_text)
    TextView dyTopText;
    //    @BindView(R.id.dy_more_image)
//    ImageView dyMoreImage;
    @BindView(R.id.guanzhu_btn)
    TextView guanzhuBtn;
    @BindView(R.id.dy_content_tv)
    TextView dyContentTv;
    @BindView(R.id.dy_lookmore_tv)
    TextView dyLookmoreTv;
    @BindView(R.id.dy_image_recyc)
    MyGridView dyImageRecyc;
    @BindView(R.id.dy_voice_back)
    ImageView dyVoiceBack;
    @BindView(R.id.dy_voice_play)
    ImageView dyVoicePlay;
    @BindView(R.id.dy_voice_time)
    TextView dyVoiceTime;
    @BindView(R.id.dy_voice)
    RelativeLayout dyVoice;
    @BindView(R.id.dy_oneimage_iv)
    ImageView dyOneimageIv;
    @BindView(R.id.aaa)
    ConstraintLayout aaa;
    @BindView(R.id.dy_label)
    TagFlowLayout dyLabel;
    @BindView(R.id.dy_collection)
    ImageView dyCollection;
    @BindView(R.id.pinglun_image)
    ImageView pinglunImage;
    @BindView(R.id.dy_comment)
    TextView dyComment;
    @BindView(R.id.pinglun)
    LinearLayout pinglun;
    @BindView(R.id.dianzan_image)
    ImageView dianzanImage;
    @BindView(R.id.dy_fabulous)
    TextView dyFabulous;
    @BindView(R.id.dianzan)
    LinearLayout dianzan;
    @BindView(R.id.zhuanfa_image)
    ImageView zhuanfaImage;
    @BindView(R.id.dy_share)
    TextView dyShare;
    @BindView(R.id.zhuanfa)
    LinearLayout zhuanfa;
    @BindView(R.id.no_date)
    TextView noDate;
    @BindView(R.id.hot_comment)
    MyListView hotComment;
    @BindView(R.id.sort_button)
    CheckBox sortButton;
    @BindView(R.id.all_comment)
    MyListView allComment;
    @BindView(R.id.smart_two)
    SmartRefreshLayout smart;
    @BindView(R.id.remen)
    TextView remen;
    @BindView(R.id.all)
    RelativeLayout all;
    @BindView(R.id.imgSearch)
    ImageView imgSearch;
    @BindView(R.id.shoucang_room)
    ImageView shoucangRoom;


    private int id, user_id, follow;
    private int page = 1;
    private String ss = "desc";
    private LoginData user;
    private List<DynamicDetailsBean.DataBean.DetailsBean> detailsList; //详情数据源
    private ArrayList<String> imageList = new ArrayList<>(); // 图片数据源
    private ArrayList<String> labelList; //标签的数据源
    private CommentAdapter commentAdapter; //热门评论的适配器
    private AllCommentAdapter allCommentAdapter; //所有评论的适配器

    //评论
    private PopupWindow popupWindow;
    private View popupView = null;
    private EditText inputComment;
    private String nInputContentText;
    private TextView btn_submit;
    private RelativeLayout rl_input_container;
    private InputMethodManager mInputManager;

    private boolean play = true;//判断播放还是暂停
    private CountDownTimer timer; // 倒计时

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
        return R.layout.activity_dynamic_details;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        detailsList = new ArrayList<>();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        user = UserManager.getUser();

        guanzhuBtn.setVisibility(View.VISIBLE);

        setPopularComment();
        setAllComment();

        getDetails("desc", 1, false);

        sortButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ss = "desc";
                    getDetails(ss, 1, false);
                } else {
                    ss = "asc";
                    getDetails(ss, 1, false);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        smart.setOnRefreshListener(refreshlayout -> {
            page = 1;
            getDetails(ss, page, false);
        });
        smart.setOnLoadMoreListener(refreshlayout -> {
            page++;
            getDetails(ss, page, false);
        });

    }

    //获取某条动态详情
    private void getDetails(String sort, int pagea, boolean isPingLun) {
        RxUtils.loading(commonModel.dynamic_details(user.getUserId() + "", sort, pagea + "", id + ""), this)
                .subscribe(new ErrorHandleSubscriber<DynamicDetailsBean>(mErrorHandler) {
                    @Override
                    public void onNext(DynamicDetailsBean dynamicDetailsBean) {
                        if (pagea == 1) {
                            smart.finishRefresh();
                            if (dynamicDetailsBean.getData().getComments().size() == 0) {
                                hotComment.setVisibility(View.GONE);
                                remen.setVisibility(View.GONE);
                                all.setVisibility(View.GONE);
                                allComment.setVisibility(View.GONE);
                                noDate.setVisibility(View.VISIBLE);
                            } else {
                                hotComment.setVisibility(View.VISIBLE);
                                remen.setVisibility(View.VISIBLE);
                                all.setVisibility(View.VISIBLE);
                                allComment.setVisibility(View.VISIBLE);
                                noDate.setVisibility(View.GONE);
                                allCommentAdapter.getList_adapter().clear();
                                allCommentAdapter.getList_adapter().addAll(dynamicDetailsBean.getData().getComments());
                                allCommentAdapter.notifyDataSetChanged();
                            }
                            if (dynamicDetailsBean.getData().getHot().size() == 0) {
                                hotComment.setVisibility(View.GONE);
                                remen.setVisibility(View.GONE);
                            } else {
                                hotComment.setVisibility(View.VISIBLE);
                                remen.setVisibility(View.VISIBLE);
                                commentAdapter.getList_adapter().clear();
                                commentAdapter.getList_adapter().addAll(dynamicDetailsBean.getData().getHot());
                                commentAdapter.notifyDataSetChanged();
                            }
                        } else {
                            smart.finishLoadMore();
                            if (dynamicDetailsBean.getData().getHot().size() != 0) {
                                commentAdapter.getList_adapter().addAll(dynamicDetailsBean.getData().getHot());
                                commentAdapter.notifyDataSetChanged();
                            }
                            if (dynamicDetailsBean.getData().getComments().size() != 0) {
                                allCommentAdapter.getList_adapter().addAll(dynamicDetailsBean.getData().getComments());
                                allCommentAdapter.notifyDataSetChanged();
                            }
                        }

                        DynamicDetailsBean.DataBean data = dynamicDetailsBean.getData();
                        detailsList = data.getDetails();

                        setDetails();
                        if (isPingLun) {
                            EventBus.getDefault().post(new FirstEvent(id + "," + data.getDetails().get(0).getTalk_num(), "pinglunchenggong"));
                            LogUtils.debugInfo("====发送", id + "," + data.getDetails().get(0).getTalk_num());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        smart.finishRefresh();
                        smart.finishLoadMore();
                    }
                });
    }

    //设置某条动态的详细
    private void setDetails() {
        if (detailsList != null && detailsList.size() != 0) {
            labelList = new ArrayList<>();
            for (int a = 0; a < detailsList.size(); a++) {
                DynamicDetailsBean.DataBean.DetailsBean detailsBean = detailsList.get(a);
                follow = detailsBean.getIs_follow();
                user_id = detailsBean.getUser_id();
                //头像
                if (!TextUtils.isEmpty(detailsBean.getHeadimgurl())) {
                    ArmsUtils.obtainAppComponentFromContext(this)
                            .imageLoader()
                            .loadImage(this, ImageConfigImpl
                                    .builder()
                                    .url(detailsBean.getHeadimgurl())
                                    .placeholder(R.mipmap.no_tou)
                                    .imageView(dyHeadImage)
                                    .errorPic(R.mipmap.no_tou)
                                    .build());
                }
                //昵称
                dyNameText.setText(detailsBean.getNickname());

                //动态文字内容
                dyContentTv.setText(detailsBean.getContent());

                //数据请求后的点赞显示
                dyFabulous.setText(detailsBean.getPraise_num() + "");
                if (detailsBean.getIs_praise() == 1) {
                    dianzanImage.setImageResource(R.drawable.dongtai_hudong_yidianzan);
                } else {
                    dianzanImage.setImageResource(R.drawable.dongtai_hudong_dianzan);
                }

                //数据请求后的分享数
                dyShare.setText(detailsBean.getForward_num() + "");

                //数据请求后评论数
                dyComment.setText(detailsBean.getTalk_num() + "");

                //数据请求后的收藏显示
                if (detailsBean.getIs_collect() == 1) {
                    dyCollection.setImageResource(R.drawable.dongtai_hudong_yishoucang);
                } else {
                    dyCollection.setImageResource(R.drawable.dongtai_hudong_shoucang);
                }

                //男女 1 男 2 女
                if (detailsBean.getSex() == 1) {
                    dySexImage.setImageResource(R.mipmap.gender_boy);
                } else {
                    dySexImage.setImageResource(R.mipmap.gender_girl);
                }


                //vip等级
                JudgeImageUtil.noZeroVIP(detailsBean.getVip_level(), dyRankImage);

                //音频时长
                dyVoiceTime.setText(detailsBean.getAudio_time() + "s");

                //音频
                String audio = detailsBean.getAudio();
                if (!audio.isEmpty()) {
                    dyVoice.setVisibility(View.VISIBLE);
                    dyOneimageIv.setVisibility(View.GONE);
                    dyImageRecyc.setVisibility(View.GONE);
                } else {
                    dyVoice.setVisibility(View.GONE);
                }

//                //时间
                if (!detailsBean.getAddtime().isEmpty()) {
                    dyTimeText.setText(TimeUtil.chatTimee(detailsBean.getAddtime()));
                }

                //图片
                String image = detailsBean.getImage();
                if (!image.isEmpty()) {
                    String[] split = image.split(",");
                    int lenght = split.length;
                    if (lenght == 1) {
                        String oneImage = split[0];
                        imageList.add(oneImage);
                        dyOneimageIv.setVisibility(View.VISIBLE);
                        dyImageRecyc.setVisibility(View.GONE);
                        dyVoice.setVisibility(View.GONE);


                        int screenWidth = QMUIDisplayHelper.getScreenWidth(this) - QMUIDisplayHelper.dp2px(this, 24);

                        int imgWidth = screenWidth * 2 / 3;

                        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) dyOneimageIv.getLayoutParams();

                        params.width = imgWidth;

                        dyOneimageIv.setLayoutParams(params);

                        ArmsUtils.obtainAppComponentFromContext(this)
                                .imageLoader()
                                .loadImage(this, ImageConfigImpl
                                        .builder()
                                        .url(oneImage)
                                        .placeholder(R.mipmap.no_tu)
                                        .imageView(dyOneimageIv)
                                        .errorPic(R.mipmap.no_tu)
                                        .build());
                        dyOneimageIv.setOnClickListener(v -> {
                            FullScreenUtil.showFullScreenDialog(this, 0, imageList);
                        });
                    } else {
                        dyImageRecyc.setVisibility(View.VISIBLE);
                        dyOneimageIv.setVisibility(View.GONE);
                        dyVoice.setVisibility(View.GONE);

                        if (lenght == 4) {

                            OneImageYuanJiaoAdapter oneImageYuanJiaoAdapter = new OneImageYuanJiaoAdapter(this);
                            dyImageRecyc.setAdapter(oneImageYuanJiaoAdapter);
                            int screenWidth = QMUIDisplayHelper.getScreenWidth(this) - QMUIDisplayHelper.dp2px(this, 24);
                            int imgWidth = screenWidth * 2 / 3;
                            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) dyImageRecyc.getLayoutParams();
                            params.width = imgWidth;
                            dyImageRecyc.setLayoutParams(params);
                            dyImageRecyc.setNumColumns(2);
                            dyImageRecyc.setAdapter(oneImageYuanJiaoAdapter);
                            oneImageYuanJiaoAdapter.getList_adapter().clear();

                            for (int i = 0; i < split.length; i++) {
                                oneImageYuanJiaoAdapter.getList_adapter().add(split[i]);
                            }
                            oneImageYuanJiaoAdapter.notifyDataSetChanged();

                            dyImageRecyc.setOnItemClickListener((parent, view, position, id) -> {
                                FullScreenUtil.showFullScreenDialog(this, position, oneImageYuanJiaoAdapter.getList_adapter());
                            });

                        } else {

                            OneImageYuanJiaoAdapter oneImageYuanJiaoAdapter = new OneImageYuanJiaoAdapter(this);
                            dyImageRecyc.setAdapter(oneImageYuanJiaoAdapter);

                            for (int i = 0; i < split.length; i++) {
                                oneImageYuanJiaoAdapter.getList_adapter().add(split[i]);
                            }
                            oneImageYuanJiaoAdapter.notifyDataSetChanged();

                            dyImageRecyc.setOnItemClickListener((parent, view, position, id) -> {
                                FullScreenUtil.showFullScreenDialog(this, position, oneImageYuanJiaoAdapter.getList_adapter());
                            });

                        }

                    }
                }
                //点赞的监听
                dianzan.setOnClickListener(v -> {
                    if (detailsBean.getIs_praise() == 1) {
                        cancelDynamic(user.getUserId() + "", id + "", 1 + "", "del", 0, 0);
                    } else {
                        fbDynamic(user.getUserId() + "", id + "", 1 + "", "add", 0, 0);
                    }
                });
                dyHeadImage.setOnClickListener(v -> {
                    Intent intent1 = new Intent(DynamicDetailsActivity.this, MyPersonalCenterActivity.class);
                    if (detailsBean.getUser_id() == UserManager.getUser().getUserId()) {
                        intent1.putExtra("sign", 0);
                        intent1.putExtra("id", "");
                    } else {
                        intent1.putExtra("sign", 1);
                        intent1.putExtra("id", detailsBean.getUser_id() + "");
                    }
                    ArmsUtils.startActivity(intent1);
                });
                //收藏的监听
                dyCollection.setOnClickListener(v -> {
                    if (detailsBean.getIs_collect() == 1) {
                        cancelDynamic(user.getUserId() + "", id + "", 2 + "", "del", 1, 0);
                    } else {
                        fbDynamic(user.getUserId() + "", id + "", 2 + "", "add", 1, 0);
                    }
                });

                //评论的监听
                pinglun.setOnClickListener(v -> {
//                    showPopupcomment(1, 0 + "");
                    PingLun(1, 0 + "");
                });

                //转发
                zhuanfa.setOnClickListener(v -> {
                    UMWeb web = new UMWeb("https://fir.im/q973");
                    web.setTitle("啾咪");//标题
                    web.setDescription("快来加入啾咪直播啦！");//描述
                    new ShareAction(DynamicDetailsActivity.this)
                            .withMedia(web)
                            .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                                    SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN)
                            .setCallback(shareListener)
                            .open();
                });
                //播放音频
                dyVoicePlay.setOnClickListener(v -> {
                    if (play) {
                        play = false;
                        dyVoicePlay.setImageResource(R.mipmap.shequ_yuyin_zanting);
                        MediaManager.playSound(detailsBean.getAudio(), new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                dyVoicePlay.setImageResource(R.mipmap.shequ_yuyin_bofang);
                            }
                        });
                        int t = Integer.parseInt(detailsBean.getAudio_time().replace("s", "").trim());
                        long time = (long) t * 1000;
                        timer = new CountDownTimer(time, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                int i = (int) (millisUntilFinished / 1000);
                                dyVoiceTime.setText(i + "s");
                            }

                            @Override
                            public void onFinish() {
                                dyVoiceTime.setText(detailsBean.getAudio_time());
                                dyVoicePlay.setImageResource(R.mipmap.shequ_yuyin_bofang);
                                MediaManager.pause();
                                MediaManager.release();
                                play = false;
                            }
                        }.start();
                    } else {
                        play = true;
                        dyVoicePlay.setImageResource(R.mipmap.shequ_yuyin_bofang);
                        timer.cancel();
                        dyVoiceTime.setText(detailsBean.getAudio_time());
                        MediaManager.pause();
                        MediaManager.release();
                    }
                });


                //关注
                if (user_id == UserManager.getUser().getUserId()) {
                    guanzhuBtn.setVisibility(View.GONE);
                } else {
                    guanzhuBtn.setVisibility(View.VISIBLE);
                }
                if (detailsBean.getIs_follow() == 1) {
                    guanzhuBtn.setText("已关注");
                } else {
                    guanzhuBtn.setText("关注");
                }

                //标签
                String tags_str = detailsBean.getTags_str();
                if (!tags_str.isEmpty()) {
                    labelList.clear();
                    String[] split = tags_str.split(",");
                    for (int b = 0; b < split.length; b++) {
                        labelList.add(split[b]);
                    }
                    dyLabel.setAdapter(new TagAdapter<String>(labelList) {
                        @Override
                        public View getView(FlowLayout parent, int position, String string) {
                            TextView tv = LayoutInflater.from(DynamicDetailsActivity.this).inflate(R.layout.bule_lable_item, null).findViewById(R.id.label_text_item);
                            tv.setText(string);
                            return tv;
                        }
                    });
                }
            }
        }
    }

    //设置某条动态的热门评论
    private void setPopularComment() {
        commentAdapter = new CommentAdapter(this);
        hotComment.setAdapter(commentAdapter);

        commentAdapter.setOnThreeClick(pos -> {
            if (MyUtil.isFastClick()) {
                int id = commentAdapter.getList_adapter().get(pos).getId();
                int is_praise = commentAdapter.getList_adapter().get(pos).getIs_praise();

                if (is_praise == 1) {
                    commentAdapter.getList_adapter().get(pos).setIs_praise(0);
                    commentAdapter.getList_adapter().get(pos).setPraise(commentAdapter.getList_adapter().get(pos).getPraise() - 1);
                    cancelDynamic(user.getUserId() + "", id + "", 4 + "", "del", 2, pos);
                } else {
                    commentAdapter.getList_adapter().get(pos).setPraise(commentAdapter.getList_adapter().get(pos).getPraise() + 1);
                    commentAdapter.getList_adapter().get(pos).setIs_praise(1);
                    fbDynamic(user.getUserId() + "", id + "", 4 + "", "add", 2, pos);
                }
            }
        });

        commentAdapter.setOnOneClick(pos -> {
            if (MyUtil.isFastClick()) {
                if (commentAdapter.getList_adapter().get(pos).getUser_id() != UserManager.getUser().getUserId()) {
                    int id = commentAdapter.getList_adapter().get(pos).getId();
//                    showPopupcomment(2, id + "");
                    PingLun(2, id + "");
                }
            }
        });
    }

    //设置某条动态的所有评论
    private void setAllComment() {
        allCommentAdapter = new AllCommentAdapter(this);
        allComment.setAdapter(allCommentAdapter);
        allCommentAdapter.setOnThreeClick(pos -> {
            if (MyUtil.isFastClick()) {
                int allId = allCommentAdapter.getList_adapter().get(pos).getId();
                int is_praise = allCommentAdapter.getList_adapter().get(pos).getIs_praise();
                if (is_praise == 1) {
                    allCommentAdapter.getList_adapter().get(pos).setPraise(allCommentAdapter.getList_adapter().get(pos).getPraise() - 1);
                    allCommentAdapter.getList_adapter().get(pos).setIs_praise(0);
                    cancelDynamic(user.getUserId() + "", allId + "", 4 + "", "del", 3, pos);
                } else {
                    allCommentAdapter.getList_adapter().get(pos).setPraise(allCommentAdapter.getList_adapter().get(pos).getPraise() + 1);
                    allCommentAdapter.getList_adapter().get(pos).setIs_praise(1);
                    fbDynamic(user.getUserId() + "", allId + "", 4 + "", "add", 3, pos);
                }
            }
        });

        allCommentAdapter.setOnOneClick(new AllCommentAdapter.OnOneClick() {
            @Override
            public void oneClick(int pos) {
                if (MyUtil.isFastClick()) {
                    if (allCommentAdapter.getList_adapter().get(pos).getUser_id() != UserManager.getUser().getUserId()) {
                        int id = allCommentAdapter.getList_adapter().get(pos).getId();
//                    showPopupcomment(2, id + "");
                        PingLun(2, id + "");
                    }
                }
            }

            @Override
            public void onHeadClick(int pos) {
                if (MyUtil.isFastClick()) {
                    if (allCommentAdapter.getList_adapter().get(pos).getUser_id() != UserManager.getUser().getUserId()) {
                        int id = allCommentAdapter.getList_adapter().get(pos).getUser_id();

                        Intent intent1 = new Intent(DynamicDetailsActivity.this, MyPersonalCenterActivity.class);
                        intent1.putExtra("sign", 1);
                        intent1.putExtra("id", id + "");
                        intent1.putExtra("isRoom", false);
                        ArmsUtils.startActivity(intent1);
                        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                    }
                }
            }
        });
    }

    //点赞
    private void fbDynamic(String userId, String targetId, String type, String hand, int acc, int poss) {

        RxUtils.loading(commonModel.dynamic_praise(userId, targetId, type, hand), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (acc == 0) {
                            int num = detailsList.get(0).getPraise_num();
                            num += 1;
                            detailsList.get(0).setPraise_num(num);

                            dyFabulous.setText(num + "");
                            detailsList.get(0).setIs_praise(1);
                            dianzanImage.setImageResource(R.drawable.dongtai_hudong_yidianzan);
                        } else if (acc == 1) {
                            detailsList.get(0).setIs_collect(1);
                            dyCollection.setImageResource(R.drawable.dongtai_hudong_yishoucang);
                        } else if (acc == 2) {
                            commentAdapter.notifyDataSetChanged();
                        } else {
                            allCommentAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    //取消点赞
    private void cancelDynamic(String userId, String targetId, String type, String hand, int acc, int poss) {
        RxUtils.loading(commonModel.dynamic_praise(userId, targetId, type, hand), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (acc == 0) {
                            int num = detailsList.get(0).getPraise_num();
                            num -= 1;
                            detailsList.get(0).setPraise_num(num);
                            dyFabulous.setText(num + "");
                            detailsList.get(0).setIs_praise(0);
                            dianzanImage.setImageResource(R.drawable.dongtai_hudong_dianzan);
                        } else if (acc == 1) {
                            detailsList.get(0).setIs_collect(0);
                            dyCollection.setImageResource(R.drawable.dongtai_hudong_shoucang);
                        } else if (acc == 2) {
                            commentAdapter.notifyDataSetChanged();
                        } else {
                            allCommentAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    //关注
    private void setFollow() {
        RxUtils.loading(commonModel.follow(user.getUserId() + "", user_id + ""), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        follow = 1;
                        guanzhuBtn.setText("已关注");
                    }
                });
    }

    //取消关注
    private void setTakeOff() {
        RxUtils.loading(commonModel.cancel_follow(user.getUserId() + "", user_id + ""), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        follow = 0;
                        guanzhuBtn.setText("关注");
                    }
                });
    }

    //发布评论
    private void setComment(String pid, String content) {
        RxUtils.loading(commonModel.setComment(id + "", pid + "", user.getUserId() + "", content), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        toast("评论成功");
                        getDetails(ss, page, true);
                    }
                });
    }

    @OnClick({R.id.guanzhu_btn, R.id.dy_share, R.id.imgSearch, R.id.shoucang_room})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guanzhu_btn:
                if (follow == 1) {
                    setTakeOff();
                } else {
                    setFollow();
                }
                break;
            case R.id.dy_share:
                break;
            case R.id.imgSearch:
                finish();
                break;
            case R.id.shoucang_room:
                Intent intent = new Intent(DynamicDetailsActivity.this, ReportActivity.class);
                intent.putExtra("type", "3");
                intent.putExtra("targetId", String.valueOf(detailsList.get(0).getId()));
                ArmsUtils.startActivity(intent);
                break;
        }
    }

    //评论功能
//    @SuppressLint("WrongConstant")
//    private void showPopupcomment(int a, String b) {
//        if (popupView == null) {
//            //加载评论框的资源文件
//            popupView = LayoutInflater.from(this).inflate(R.layout.comment_popupwindow, null);
//        }
//        inputComment = (EditText) popupView.findViewById(R.id.et_discuss);
//        btn_submit = (Button) popupView.findViewById(R.id.btn_confirm);
//        rl_input_container = (RelativeLayout) popupView.findViewById(R.id.rl_input_container);
//        //利用Timer这个Api设置延迟显示软键盘，这里时间为200毫秒
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//
//            public void run() {
//                mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                mInputManager.showSoftInput(inputComment, 0);
//            }
//
//        }, 200);
//        if (popupWindow == null) {
//            popupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.MATCH_PARENT,
//                    RelativeLayout.LayoutParams.WRAP_CONTENT, false);
//
//        }
//        //popupWindow的常规设置，设置点击外部事件，背景色
//        popupWindow.setTouchable(true);
//        popupWindow.setFocusable(true);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
//        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
//                    popupWindow.dismiss();
//                return false;
//
//            }
//        });
//
//        // 设置弹出窗体需要软键盘，放在setSoftInputMode之前
//        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
//        // 再设置模式，和Activity的一样，覆盖，调整大小。
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        //设置popupwindow的显示位置，这里应该是显示在底部，即Bottom
//        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
//
//        popupWindow.update();
//
//        //设置监听
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//            // 在dismiss中恢复透明度
//            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
//            public void onDismiss() {
//                mInputManager.hideSoftInputFromWindow(inputComment.getWindowToken(), 0); //强制隐藏键盘
//            }
//        });
//        //外部点击事件
//        rl_input_container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mInputManager.hideSoftInputFromWindow(inputComment.getWindowToken(), 0); //强制隐藏键盘
//                popupWindow.dismiss();
//            }
//        });
//        //评论框内的发送按钮设置点击事件
//        btn_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                nInputContentText = inputComment.getText().toString().trim();
//                if (nInputContentText == null || "".equals(nInputContentText)) {
//                    toast("请输入评论内容");
//                    return;
//                } else {
//                    if (a == 1) {
//                        setComment(b, nInputContentText);
//                        inputComment.setText("");
//                    } else if (a == 2) {
//                        setComment(b, nInputContentText);
////                        getDetails(ss, page);
//                        inputComment.setText("");
//                    }
//
//                }
//                mInputManager.hideSoftInputFromWindow(inputComment.getWindowToken(), 0);
//                popupWindow.dismiss();
//            }
//        });
//    }

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
            Toast.makeText(DynamicDetailsActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(DynamicDetailsActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    private void fenxiang() {
        RxUtils.loading(commonModel.fenxiang(String.valueOf(UserManager.getUser().getUserId()), String.valueOf(detailsList.get(0).getId()), "add"), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        if (commentBean.getCode() == 1) {
                            showToast(commentBean.getMessage());
                            String shareStr = dyShare.getText().toString();
                            dyShare.setText(String.valueOf(Integer.parseInt(shareStr) + 1));
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        MediaManager.pause();
        MediaManager.release();
    }

    private void PingLun(int a, String b) {
        KeybordWindow payWindow = new KeybordWindow(DynamicDetailsActivity.this);
        payWindow.showAtLocation(remen, Gravity.BOTTOM, 0, 0);
        payWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 1f;
            getWindow().setAttributes(params);
        });
        payWindow.getBtn_ok().setOnClickListener(v1 -> {
            String string = payWindow.getEditMessage().getText().toString();
            if (!TextUtils.isEmpty(string)) {
                payWindow.dismiss();
                if (a == 1) {
                    setComment(b, string);
                } else if (a == 2) {
                    setComment(b, string);
                }
            } else {
                showToast("请输入内容！");
            }
        });
    }
}
