package com.miniapp.talks.adapter;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.RecommendedDynamicBean;
import com.miniapp.talks.bean.Search;
import com.miniapp.talks.utils.FullScreenUtil;
import com.miniapp.talks.utils.MediaManager;
import com.miniapp.talks.utils.TimeUtil;
import com.miniapp.talks.view.MyGridView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;


public class SearchDynamicAdapter extends BaseQuickAdapter<Search.DataBean.DynamicsBean, BaseViewHolder> {
    private ArrayList<String> oneImageList = new ArrayList<>(); //单图的数据源
    private ArrayList<String> tagsList = new ArrayList<>(); //标签的数据源

    public SearchDynamicAdapter() {
        super(R.layout.comm_dy_item, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, Search.DataBean.DynamicsBean item) {
        helper.addOnClickListener(R.id.dianzan)
                .addOnClickListener(R.id.dy_collection)
                .addOnClickListener(R.id.pinglun)
                .addOnClickListener(R.id.zhuanfa)
                .addOnClickListener(R.id.dy_head_image);

        //先让单图，多图，音频的布局显示
        helper.getView(R.id.dy_oneimage_iv).setVisibility(View.VISIBLE);
        helper.getView(R.id.dy_image_recyc).setVisibility(View.VISIBLE);
        helper.getView(R.id.dy_voice).setVisibility(View.VISIBLE);

        //昵称
        helper.setText(R.id.dy_name_text, item.getNickname());

        //头像
        if (!TextUtils.isEmpty(item.getHeadimgurl())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getHeadimgurl())
                            .placeholder(R.mipmap.no_tou)
                            .imageView(helper.getView(R.id.dy_head_image))
                            .errorPic(R.mipmap.no_tou)
                            .build());
        }

        //动态内容以富文本展示
        String content = item.getContent();
        if (content == null || content.length() == 0) {
            helper.getView(R.id.dy_lookmore_tv).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.dy_lookmore_tv).setVisibility(View.VISIBLE);
        }
        helper.getView(R.id.dy_lookmore_tv).getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                //这个回调会调用多次，获取完行数记得注销监听
                TextView view = helper.getView(R.id.dy_content_tv);
                int lineCount = view.getLineCount();
                if (lineCount >= 7) {
                    helper.getView(R.id.dy_lookmore_tv).setVisibility(View.VISIBLE);
                    helper.getView(R.id.dy_content_tv).getViewTreeObserver().removeOnPreDrawListener(this);//销毁
                } else {
                    helper.getView(R.id.dy_lookmore_tv).setVisibility(View.GONE);
                    helper.getView(R.id.dy_content_tv).getViewTreeObserver().removeOnPreDrawListener(this);//销毁
                }
                return true;
            }
        });
        helper.setText(R.id.dy_content_tv, content);

        //点赞
        helper.setText(R.id.dy_fabulous, item.getPraise_num() + "");
        if (item.getIs_praise() == 1) {
            helper.setImageResource(R.id.dianzan_image, R.drawable.dongtai_hudong_yidianzan);
        } else {
            helper.setImageResource(R.id.dianzan_image, R.drawable.dongtai_hudong_dianzan);
        }

        //分享数
        helper.setText(R.id.dy_share, item.getForward_num() + "");

        //评论数
        helper.setText(R.id.dy_comment, item.getTalk_num() + "");

        //收藏
        if (item.getIs_collect() == 1) {
            helper.setImageResource(R.id.dy_collection, R.drawable.dongtai_hudong_yishoucang);
        } else {
            helper.setImageResource(R.id.dy_collection, R.drawable.dongtai_hudong_shoucang);
        }

        //男女 1 男 2 女
        if (item.getSex() == 1) {
            helper.setImageResource(R.id.dy_sex_image, R.mipmap.gender_boy);
        } else {
            helper.setImageResource(R.id.dy_sex_image, R.mipmap.gender_girl);
        }

        //时间

        if (!item.getAddtime().isEmpty()) {
            helper.setText(R.id.dy_time_text, TimeUtil.chatTimee(item.getAddtime()));
        }

        String audio = item.getAudio(); //音频
        String image = item.getImage(); //图片
        if (image != null && image.length() != 0) {
            String[] arrIv = image.split(",");
            int length = arrIv.length;
            helper.getView(R.id.dy_voice).setVisibility(View.GONE);
            if (length == 1) {  //单图
                oneImageList.clear();
                String img1Iv = arrIv[0];
                oneImageList.add(img1Iv);
                helper.getView(R.id.dy_image_recyc).setVisibility(View.GONE);

                ImageView imgSingle = helper.getView(R.id.dy_oneimage_iv);

                int screenWidth = QMUIDisplayHelper.getScreenWidth(mContext) - QMUIDisplayHelper.dp2px(mContext, 24);

                int imgWidth = screenWidth*2/3;

                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imgSingle.getLayoutParams();

                params.width = imgWidth;

                imgSingle.setLayoutParams(params);

                ArmsUtils.obtainAppComponentFromContext(mContext)
                        .imageLoader()
                        .loadImage(mContext, ImageConfigImpl
                                .builder()
                                .url(img1Iv)
                                .placeholder(R.mipmap.no_tu)
                                .imageView(helper.getView(R.id.dy_oneimage_iv))
                                .errorPic(R.mipmap.no_tu)
                                .build());
                helper.getView(R.id.dy_oneimage_iv).setOnClickListener(v -> {
                    FullScreenUtil.showFullScreenDialog(mContext, 0, oneImageList);
                });
            } else {   //多图
                if(length == 4){

                    OneImageYuanJiaoAdapter oneImageYuanJiaoAdapter = new OneImageYuanJiaoAdapter(mContext);
                    MyGridView recyclerView = helper.getView(R.id.dy_image_recyc);
                    int screenWidth = QMUIDisplayHelper.getScreenWidth(mContext) - QMUIDisplayHelper.dp2px(mContext, 24);
                    int imgWidth = screenWidth*2/3;
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();
                    params.width = imgWidth;
                    recyclerView.setLayoutParams(params);
                    recyclerView.setNumColumns(2);
                    recyclerView.setAdapter(oneImageYuanJiaoAdapter);
                    oneImageYuanJiaoAdapter.getList_adapter().clear();

                    for (int j = 0; j < arrIv.length; j++) {
                        oneImageYuanJiaoAdapter.getList_adapter().add(arrIv[j]);
                    }
                    helper.getView(R.id.dy_oneimage_iv).setVisibility(View.GONE);

                    oneImageYuanJiaoAdapter.notifyDataSetChanged();

                    recyclerView.setOnItemClickListener((parent, view, position, id) -> {
                        FullScreenUtil.showFullScreenDialog(mContext, position, oneImageYuanJiaoAdapter.getList_adapter());
                    });

                } else {
                    OneImageYuanJiaoAdapter oneImageYuanJiaoAdapter = new OneImageYuanJiaoAdapter(mContext);
                    MyGridView recyclerView = helper.getView(R.id.dy_image_recyc);
                    recyclerView.setAdapter(oneImageYuanJiaoAdapter);
                    oneImageYuanJiaoAdapter.getList_adapter().clear();

                    for (int j = 0; j < arrIv.length; j++) {
                        oneImageYuanJiaoAdapter.getList_adapter().add(arrIv[j]);
                    }
                    helper.getView(R.id.dy_oneimage_iv).setVisibility(View.GONE);

                    oneImageYuanJiaoAdapter.notifyDataSetChanged();

                    recyclerView.setOnItemClickListener((parent, view, position, id) -> {
                        FullScreenUtil.showFullScreenDialog(mContext, position, oneImageYuanJiaoAdapter.getList_adapter());
                    });
                }
            }
        } else if (audio != null && audio.length() != 0 && !"".equals(audio)) { //音频模式
            helper.getView(R.id.dy_oneimage_iv).setVisibility(View.GONE);
            helper.getView(R.id.dy_image_recyc).setVisibility(View.GONE);
            helper.getView(R.id.dy_voice_play).setOnClickListener(v -> {
                helper.setImageResource(R.id.dy_voice_play, R.mipmap.shequ_yuyin_zanting);
                MediaManager.playSound(item.getAudio(), new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        helper.setImageResource(R.id.dy_voice_play, R.mipmap.shequ_yuyin_bofang);
                    }
                });
            });
        } else {
            helper.getView(R.id.dy_oneimage_iv).setVisibility(View.GONE);
            helper.getView(R.id.dy_image_recyc).setVisibility(View.GONE);
            helper.getView(R.id.dy_voice).setVisibility(View.GONE);
        }

        //音频时长
        helper.setText(R.id.dy_voice_time, item.getAudio_time() + "s");

        ArrayList<String> tagsList = new ArrayList<>(); //标签的数据源
        String tags_str = item.getTags_str();
        if (!tags_str.isEmpty()) {
            tagsList.clear();
            String[] split = tags_str.split(",");
            for (int a = 0; a < split.length; a++) {
                tagsList.add(split[a]);
            }
            TagFlowLayout tagFlowLayout = helper.getView(R.id.dy_label);
            tagFlowLayout.setAdapter(new TagAdapter<String>(tagsList) {
                @Override
                public View getView(FlowLayout parent, int position, String string) {
                    TextView tv = LayoutInflater.from(mContext).inflate(R.layout.bule_lable_item, null).findViewById(R.id.label_text_item);
                    tv.setText(string);
                    return tv;
                }
            });


//            ArrayAdapter arrayAdapter = new ArrayAdapter(mContext, R.layout.bule_lable_item, tagsList);
//            GridView labelView = helper.getView(R.id.dy_label);
//            labelView.setAdapter(arrayAdapter);
        } else {
            helper.getView(R.id.dy_label).setVisibility(View.INVISIBLE);
        }

//        helper.getView(R.id.dy_more_image).setOnClickListener(v -> {
//            DynamicWindow dynamicWindow = new DynamicWindow((Activity) mContext);
//            dynamicWindow.showAsDropDown(helper.getView(R.id.dy_more_image));
//            dynamicWindow.getJuBao().setOnClickListener(v1 -> {
//                ArmsUtils.makeText(mContext, "已举报");
//            });
//        });
    }

    @Override
    protected void convertPayloads(@NonNull BaseViewHolder helper, Search.DataBean.DynamicsBean item, @NonNull List<Object> payloads) {
        super.convertPayloads(helper, item, payloads);
        if (payloads.isEmpty()) {
            convert(helper, item);
        } else {
            String payload = (String) payloads.get(0);
            int praise_num = item.getPraise_num();
            if ("like".equals(payload)) {
                item.setIs_praise(1);
                item.setPraise_num(praise_num + 1);
                helper.setText(R.id.dy_fabulous, praise_num + 1 + "");
                helper.setImageResource(R.id.dianzan_image, R.drawable.dongtai_hudong_yidianzan);
            } else if ("unlike".equals(payload)) {
                item.setIs_praise(0);
                item.setPraise_num(praise_num - 1);
                helper.setText(R.id.dy_fabulous, praise_num - 1 + "");
                helper.setImageResource(R.id.dianzan_image, R.drawable.dongtai_hudong_dianzan);
            } else if ("likeSC".equals(payload)) {
                item.setIs_collect(1);
                helper.setImageResource(R.id.dy_collection, R.drawable.dongtai_hudong_yishoucang);
            } else if ("unlikeSC".equals(payload)) {
                item.setIs_collect(1);
                helper.setImageResource(R.id.dy_collection, R.drawable.dongtai_hudong_shoucang);
            }
        }
    }
}
