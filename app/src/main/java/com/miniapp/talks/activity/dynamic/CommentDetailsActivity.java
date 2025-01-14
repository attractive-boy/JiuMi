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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.adapter.AlllllAdapter;
import com.miniapp.talks.adapter.LookCommentAdapter;
import com.miniapp.talks.adapter.OneImageYuanJiaoAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.AllCommentBean;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.CommentBean;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.LookCommentBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.FullScreenUtil;
import com.miniapp.talks.utils.MediaManager;
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
 * 评论页面
 */

public class CommentDetailsActivity extends MyBaseArmActivity {
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
    @BindView(R.id.zhengge_item)
    LinearLayout zhenggeItem;
    @BindView(R.id.xiaoxi_list)
    MyListView xiaoxiList;
    @BindView(R.id.more_text)
    TextView moreText;
    @BindView(R.id.haha)
    LinearLayout haha;
    @BindView(R.id.all_comment)
    MyListView allComment;
    @BindView(R.id.scrollview)
    ScrollView mScrollView;
//    @BindView(R.id.snart)
//    SmartRefreshLayout snart;


    private int commentId, plrId, userId, follow, dtId; //commentId 该条评论Id  plrId 评论人Id userId 该条动态人的ID
    private LoginData user;
    private ArrayList<String> imageList = new ArrayList<>(); // 图片数据源
    private ArrayList<String> labelList; //标签的数据源
    private LookCommentAdapter allCommentAdapter; //评论的适配器
    private AlllllAdapter alllllAdapter; //所有评论的适配器
    //    private LinearLayout one;
//    private SmartRefreshLayout ss;
    private boolean play = true;//判断播放还是暂停
    private CountDownTimer timer; // 倒计时

    //评论
    private PopupWindow popupWindow;
    private View popupView = null;
    private EditText inputComment;
    private String nInputContentText;
    private TextView btn_submit;
    private RelativeLayout rl_input_container;
    private InputMethodManager mInputManager;
    private int page = 1;

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
        return R.layout.activity_comment_details;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        labelList = new ArrayList<>();
//        one = findViewById(R.id.haha);
//        ss = findViewById(R.id.snart);
        user = UserManager.getUser();

//        snart.setVisibility(View.GONE);
        allComment.setVisibility(View.GONE);
        Intent intent = getIntent();
        commentId = intent.getIntExtra("id", 0);
        plrId = intent.getIntExtra("userId", 0);

        allCommentAdapter = new LookCommentAdapter(this);
        xiaoxiList.setAdapter(allCommentAdapter);

        alllllAdapter = new AlllllAdapter(this);
        allComment.setAdapter(alllllAdapter);

        allCommentAdapter.setOnOneClick(pos -> {
            if (allCommentAdapter.getList_adapter().get(pos).getUser_id() != UserManager.getUser().getUserId()) {
                showPopupcomment(2, allCommentAdapter.getList_adapter().get(pos).getId() + "");
            }
        });

        allCommentAdapter.setOnThreeClick(pos -> {
            int id = allCommentAdapter.getList_adapter().get(pos).getId();
            int is_praise = allCommentAdapter.getList_adapter().get(pos).getIs_praise();

            if (is_praise == 1) {
                allCommentAdapter.getList_adapter().get(pos).setIs_praise(0);
                allCommentAdapter.getList_adapter().get(pos).setPraise(allCommentAdapter.getList_adapter().get(pos).getPraise() - 1);
                cancelDynamic(user.getUserId() + "", id + "", 4 + "", "del", 2, pos);
            } else {
                allCommentAdapter.getList_adapter().get(pos).setPraise(allCommentAdapter.getList_adapter().get(pos).getPraise() + 1);
                allCommentAdapter.getList_adapter().get(pos).setIs_praise(1);
                fbDynamic(user.getUserId() + "", id + "", 4 + "", "add", 2, pos);
            }
        });

        allCommentAdapter.setOnTouXiang(pos -> {
            Intent intent1 = new Intent(CommentDetailsActivity.this, MyPersonalCenterActivity.class);
            if (allCommentAdapter.getList_adapter().get(pos).getUser_id() == UserManager.getUser().getUserId()) {
                intent1.putExtra("sign", 0);
                intent1.putExtra("id", "");
            } else {
                intent1.putExtra("sign", 1);
                intent1.putExtra("id", allCommentAdapter.getList_adapter().get(pos).getUser_id() + "");
            }
            ArmsUtils.startActivity(intent1);
        });
        getLookComment();

//        snart.setOnRefreshListener(refreshLayout -> {
//            page = 0;
//            getAllComment(page);
//        });
//        snart.setOnLoadMoreListener(refreshLayout -> {
//            page++;
//            getAllComment(page);
//        });
    }

    @OnClick({R.id.more_text, R.id.guanzhu_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_text:
//                one.setVisibility(View.GONE);
//                ss.setVisibility(View.VISIBLE);
//                snart.setVisibility(View.VISIBLE);
                haha.setVisibility(View.GONE);
                allComment.setVisibility(View.VISIBLE);

                getAllComment(page, 1);

                alllllAdapter.setOnOneClick(pos -> {
                    if (alllllAdapter.getList_adapter().get(pos).getUser_id() != UserManager.getUser().getUserId()) {
                        showPopupcomment(2, alllllAdapter.getList_adapter().get(pos).getId() + "");
                    }
                });
                alllllAdapter.setOnThreeClick(pos -> {
                    int id = alllllAdapter.getList_adapter().get(pos).getId();
                    int is_praise = alllllAdapter.getList_adapter().get(pos).getIs_praise();

                    if (is_praise == 1) {
                        alllllAdapter.getList_adapter().get(pos).setIs_praise(0);
                        alllllAdapter.getList_adapter().get(pos).setPraise(alllllAdapter.getList_adapter().get(pos).getPraise() - 1);
                        cancelDynamic(user.getUserId() + "", id + "", 4 + "", "del", 2, pos);
                    } else {
                        alllllAdapter.getList_adapter().get(pos).setPraise(alllllAdapter.getList_adapter().get(pos).getPraise() + 1);
                        alllllAdapter.getList_adapter().get(pos).setIs_praise(1);
                        fbDynamic(user.getUserId() + "", id + "", 4 + "", "add", 2, pos);
                    }
                });

                alllllAdapter.setOnTouXiang(pos -> {
                    Intent intent1 = new Intent(CommentDetailsActivity.this, MyPersonalCenterActivity.class);
                    if (alllllAdapter.getList_adapter().get(pos).getUser_id() == UserManager.getUser().getUserId()) {
                        intent1.putExtra("sign", 0);
                        intent1.putExtra("id", "");
                    } else {
                        intent1.putExtra("sign", 1);
                        intent1.putExtra("id", alllllAdapter.getList_adapter().get(pos).getUser_id() + "");
                    }
                    ArmsUtils.startActivity(intent1);
                });
                break;
            case R.id.guanzhu_btn:
                if (follow == 1) {
                    setTakeOff();
                } else {
                    setFollow();
                }
                break;
        }
    }

    //查看评论
    private void getLookComment() {
        RxUtils.loading(commonModel.getLookComment(user.getUserId() + "", plrId + "", commentId + ""), this)
                .subscribe(new ErrorHandleSubscriber<LookCommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(LookCommentBean lookCommentBean) {
                        LookCommentBean.DataBean data = lookCommentBean.getData();
                        List<LookCommentBean.DataBean.DetailsBean> details = data.getDetails();
                        for (int a = 0; a < details.size(); a++) {
                            LookCommentBean.DataBean.DetailsBean detailsBean = details.get(a);
                            userId = detailsBean.getUser_id();
                            dtId = detailsBean.getId();
                            //头像
                            if (!TextUtils.isEmpty(detailsBean.getHeadimgurl())) {
                                ArmsUtils.obtainAppComponentFromContext(CommentDetailsActivity.this)
                                        .imageLoader()
                                        .loadImage(CommentDetailsActivity.this, ImageConfigImpl
                                                .builder()
                                                .url(detailsBean.getHeadimgurl())
                                                .placeholder(R.mipmap.no_tou)
                                                .imageView(dyHeadImage)
                                                .errorPic(R.mipmap.no_tou)
                                                .build());
                            }

                            //昵称
                            dyNameText.setText(detailsBean.getNickname());

                            //富文本展示内容
                            String content = detailsBean.getContent();
                            if (content == null || content.length() == 0) {
                                dyLookmoreTv.setVisibility(View.GONE);
                            } else {
                                dyLookmoreTv.setVisibility(View.VISIBLE);
                            }
                            dyLookmoreTv.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                                @Override
                                public boolean onPreDraw() {
                                    //这个回调会调用多次，获取完行数记得注销监听
                                    int lineCount = dyContentTv.getLineCount();
                                    if (lineCount >= 7) {
                                        dyLookmoreTv.setVisibility(View.VISIBLE);
                                        dyContentTv.getViewTreeObserver().removeOnPreDrawListener(this);//销毁
                                    } else {
                                        dyLookmoreTv.setVisibility(View.GONE);
                                        dyContentTv.getViewTreeObserver().removeOnPreDrawListener(this);//销毁
                                    }
                                    return true;
                                }
                            });
                            dyContentTv.setText(content);

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

                            //音频时长
                            dyVoiceTime.setText(detailsBean.getAudio_time() + "s");
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
                            //音频
                            String audio = detailsBean.getAudio();
                            if (!audio.isEmpty()) {
                                dyVoice.setVisibility(View.VISIBLE);
                                dyOneimageIv.setVisibility(View.GONE);
                                dyImageRecyc.setVisibility(View.GONE);
                                dyVoicePlay.setOnClickListener(v -> {
                                    dyVoicePlay.setImageResource(R.mipmap.shequ_yuyin_zanting);
                                    MediaManager.playSound(detailsBean.getAudio(), new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mediaPlayer) {
                                            dyVoicePlay.setImageResource(R.mipmap.shequ_yuyin_bofang);
                                        }
                                    });
                                });
                            } else {
                                dyVoice.setVisibility(View.GONE);
                                dyOneimageIv.setVisibility(View.GONE);
                                dyImageRecyc.setVisibility(View.GONE);
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

                                    int screenWidth = QMUIDisplayHelper.getScreenWidth(CommentDetailsActivity.this) - QMUIDisplayHelper.dp2px(CommentDetailsActivity.this, 24);

                                    int imgWidth = screenWidth * 2 / 3;

                                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) dyOneimageIv.getLayoutParams();

                                    params.width = imgWidth;

                                    dyOneimageIv.setLayoutParams(params);

                                    ArmsUtils.obtainAppComponentFromContext(CommentDetailsActivity.this)
                                            .imageLoader()
                                            .loadImage(CommentDetailsActivity.this, ImageConfigImpl
                                                    .builder()
                                                    .url(oneImage)
                                                    .placeholder(R.mipmap.no_tu)
                                                    .imageView(dyOneimageIv)
                                                    .errorPic(R.mipmap.no_tu)
                                                    .build());
                                    dyOneimageIv.setOnClickListener(v -> {
                                        FullScreenUtil.showFullScreenDialog(CommentDetailsActivity.this, 0, imageList);
                                    });

                                } else {
                                    dyImageRecyc.setVisibility(View.VISIBLE);
                                    dyOneimageIv.setVisibility(View.GONE);
                                    dyVoice.setVisibility(View.GONE);
                                    if (lenght == 4) {

                                        OneImageYuanJiaoAdapter oneImageYuanJiaoAdapter = new OneImageYuanJiaoAdapter(CommentDetailsActivity.this);
                                        dyImageRecyc.setAdapter(oneImageYuanJiaoAdapter);
                                        int screenWidth = QMUIDisplayHelper.getScreenWidth(CommentDetailsActivity.this) - QMUIDisplayHelper.dp2px(CommentDetailsActivity.this, 24);
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
                                            FullScreenUtil.showFullScreenDialog(CommentDetailsActivity.this, position, oneImageYuanJiaoAdapter.getList_adapter());
                                        });

                                    } else {

                                        OneImageYuanJiaoAdapter oneImageYuanJiaoAdapter = new OneImageYuanJiaoAdapter(CommentDetailsActivity.this);
                                        dyImageRecyc.setAdapter(oneImageYuanJiaoAdapter);

                                        for (int i = 0; i < split.length; i++) {
                                            oneImageYuanJiaoAdapter.getList_adapter().add(split[i]);
                                        }
                                        oneImageYuanJiaoAdapter.notifyDataSetChanged();

                                        dyImageRecyc.setOnItemClickListener((parent, view, position, id) -> {
                                            FullScreenUtil.showFullScreenDialog(CommentDetailsActivity.this, position, oneImageYuanJiaoAdapter.getList_adapter());
                                        });

                                    }

                                }
                            }

                            //点赞的监听
                            dyFabulous.setOnClickListener(v -> {
                                if (detailsBean.getIs_praise() == 1) {
                                    cancelDynamic(user.getUserId() + "", commentId + "", 1 + "", "del", 0, 0);
                                } else {
                                    fbDynamic(user.getUserId() + "", commentId + "", 1 + "", "add", 0, 0);
                                }
                            });

                            //收藏的监听
                            dyCollection.setOnClickListener(v -> {
                                if (detailsBean.getIs_collect() == 1) {
                                    cancelDynamic(user.getUserId() + "", commentId + "", 2 + "", "del", 1, 0);
                                } else {
                                    fbDynamic(user.getUserId() + "", commentId + "", 2 + "", "add", 1, 0);
                                }
                            });

                            //评论的监听
                            pinglun.setOnClickListener(v -> {
                                showPopupcomment(1, 0 + "");
                            });

                            //转发
                            zhuanfa.setOnClickListener(v -> {
                                UMWeb web = new UMWeb("https://fir.im/q973");
                                web.setTitle("啾咪");//标题
                                web.setDescription("快来加入啾咪直播啦！");//描述
                                new ShareAction(CommentDetailsActivity.this)
                                        .withMedia(web)
                                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                                                SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN)
                                        .setCallback(shareListener)
                                        .open();
                            });

                            //关注
                            if (detailsBean.getIs_follow() == 1) {
                                guanzhuBtn.setText("已关注");
                            } else {
                                guanzhuBtn.setText("关注");
                            }

                            //标签
                            String tags_str = detailsBean.getTags_str();
                            if (!tags_str.isEmpty()) {
                                String[] split = tags_str.split(",");
                                for (int aa = 0; aa < split.length; aa++) {
                                    labelList.add(split[aa]);
                                }
                                dyLabel.setAdapter(new TagAdapter<String>(labelList) {
                                    @Override
                                    public View getView(FlowLayout parent, int position, String string) {
                                        TextView tv = LayoutInflater.from(CommentDetailsActivity.this).inflate(R.layout.bule_lable_item, null).findViewById(R.id.label_text_item);
                                        tv.setText(string);
                                        return tv;
                                    }
                                });
                            }
                        }
                        allCommentAdapter.getList_adapter().clear();
                        allCommentAdapter.getList_adapter().addAll(lookCommentBean.getData().getComments());
                        allCommentAdapter.notifyDataSetChanged();
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
                            allCommentAdapter.notifyDataSetChanged();
                        } else {
                            alllllAdapter.notifyDataSetChanged();
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
                            allCommentAdapter.notifyDataSetChanged();
                        } else {
                            alllllAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    //关注
    private void setFollow() {
        RxUtils.loading(commonModel.follow(user.getUserId() + "", userId + ""), this)
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
        RxUtils.loading(commonModel.cancel_follow(user.getUserId() + "", userId + ""), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        follow = 0;
                        guanzhuBtn.setText("关注");
                    }
                });
    }

    //获取所有评论
    private void getAllComment(int paga, int type) {
        RxUtils.loading(commonModel.getAlComment(dtId + "", user.getUserId() + "", paga + ""), this)
                .subscribe(new ErrorHandleSubscriber<AllCommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(AllCommentBean allCommentBean) {
//                        if (paga == 0) {
//                            snart.finishRefresh();
                        if (allCommentBean.getData().size() != 0) {
//                                alllllAdapter.getList_adapter().clear();
                            alllllAdapter.getList_adapter().addAll(allCommentBean.getData());
                            alllllAdapter.notifyDataSetChanged();
                            if (type == 0) {
//                                allComment.setSelection(alllllAdapter.getCount() - 1);
                                mScrollView.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                                    }
                                },700);
                            }
                        }

//                        } else {
//                            snart.finishLoadMore();
//                            if (allCommentBean.getData().size() != 0) {
//                                alllllAdapter.getList_adapter().addAll(allCommentBean.getData());
//                                alllllAdapter.notifyDataSetChanged();
//                            }
//                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
//                        snart.finishRefresh();
//                        snart.finishLoadMore();
                    }
                });
    }

    //发布评论
    private void setComment(String id, String pid, String content) {
        RxUtils.loading(commonModel.setComment(id + "", pid + "", user.getUserId() + "", content), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        haha.setVisibility(View.GONE);
//                        ss.setVisibility(View.VISIBLE);
                        allComment.setVisibility(View.VISIBLE);
//                        allComment.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
//                        ScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                        getAllComment(page, 0);
                        toast("评论成功");
                    }
                });
    }

    //    评论功能
    @SuppressLint("WrongConstant")
    private void showPopupcomment(int a, String b) {
        if (popupView == null) {
            //加载评论框的资源文件
            popupView = LayoutInflater.from(this).inflate(R.layout.comment_popupwindow, null);
        }
        inputComment = (EditText) popupView.findViewById(R.id.et_discuss);
        btn_submit = (Button) popupView.findViewById(R.id.btn_confirm);
        rl_input_container = (RelativeLayout) popupView.findViewById(R.id.rl_input_container);
        //利用Timer这个Api设置延迟显示软键盘，这里时间为200毫秒
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputManager.showSoftInput(inputComment, 0);
            }

        }, 200);
        if (popupWindow == null) {
            popupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT, false);

        }
        //popupWindow的常规设置，设置点击外部事件，背景色
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
                    popupWindow.dismiss();
                return false;
            }
        });

        // 设置弹出窗体需要软键盘，放在setSoftInputMode之前
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        // 再设置模式，和Activity的一样，覆盖，调整大小。
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //设置popupwindow的显示位置，这里应该是显示在底部，即Bottom
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);

        popupWindow.update();

        //设置监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            public void onDismiss() {
                mInputManager.hideSoftInputFromWindow(inputComment.getWindowToken(), 0); //强制隐藏键盘
            }
        });
        //外部点击事件
        rl_input_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputManager.hideSoftInputFromWindow(inputComment.getWindowToken(), 0); //强制隐藏键盘
                popupWindow.dismiss();
            }
        });
        //评论框内的发送按钮设置点击事件
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nInputContentText = inputComment.getText().toString().trim();
                if (nInputContentText == null || "".equals(nInputContentText)) {
                    toast("请输入评论内容");
                    return;
                } else {
                    if (a == 1) {
                        setComment(dtId + "", b, nInputContentText);

                    } else if (a == 2) {
                        setComment(dtId + "", b, nInputContentText);
                    }
                }
                mInputManager.hideSoftInputFromWindow(inputComment.getWindowToken(), 0);
                popupWindow.dismiss();
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
            Toast.makeText(CommentDetailsActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(CommentDetailsActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    private void fenxiang() {
        RxUtils.loading(commonModel.fenxiang(String.valueOf(UserManager.getUser().getUserId()), String.valueOf(dtId), "add"), this)
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
    public void onResume() {
        super.onResume();
        setToolbarTitle("动态详情", true);
    }
}
