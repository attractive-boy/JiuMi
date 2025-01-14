package com.miniapp.talks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.Rank;
import com.miniapp.talks.bean.RecommenRoomBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 排行榜
 */
@ActivityScope
public class RankAdapter extends BaseQuickAdapter<Rank.DataBean.OtherBean, BaseViewHolder> {
    private int type;
    boolean mIsFirstTransion = true;
    BaseViewHolder mHelper;

    public RankAdapter(int type) {
        super(R.layout.item_rank, new ArrayList<>());
        this.type = type;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Rank.DataBean.OtherBean item) {
        mHelper = helper;
        helper.addOnClickListener(R.id.ci_head);
        if (type == 1) {
            helper.setTextColor(R.id.textNum, mContext.getResources().getColor(R.color.color_FFBA1C));
            helper.setText(R.id.tit, "金锐值");
            if (!TextUtils.isEmpty(item.getGold_img())) {
                ArmsUtils.obtainAppComponentFromContext(mContext)
                        .imageLoader()
                        .loadImage(mContext, ImageConfigImpl
                                .builder()
                                .url(item.getGold_img())
                                .imageView(helper.getView(R.id.image_one))
                                .build());
            }
        } else {
            helper.setTextColor(R.id.textNum, mContext.getResources().getColor(R.color.font_ff3e6d));
            helper.setText(R.id.tit, "星锐值");
            if (!TextUtils.isEmpty(item.getStars_img())) {
                ArmsUtils.obtainAppComponentFromContext(mContext)
                        .imageLoader()
                        .loadImage(mContext, ImageConfigImpl
                                .builder()
                                .url(item.getStars_img())
                                .imageView(helper.getView(R.id.image_one))
                                .build());
            }
        }
        helper.setText(R.id.text1, String.valueOf(helper.getPosition() + 3))
                .setText(R.id.tv_title, item.getNickname())
                .setText(R.id.textNum, String.valueOf(item.getExp()));

        if (!TextUtils.isEmpty(item.getVip_img())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getVip_img())
                            .imageView(helper.getView(R.id.image_two))
                            .build());
        }
        if (!TextUtils.isEmpty(item.getHeadimgurl())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getHeadimgurl())
                            .placeholder(R.mipmap.no_tou)
                            .imageView(helper.getView(R.id.ci_head))
                            .errorPic(R.mipmap.no_tou)
                            .build());
        }
        if (item.getSex().equals("1")) {
            helper.setImageResource(R.id.sex, R.mipmap.gender_boy);
        } else if (item.getSex().equals("2")) {
            helper.setImageResource(R.id.sex, R.mipmap.gender_girl);
        }
        if (helper.getPosition() == 1) {
            if (!item.isBg()){
                helper.setBackgroundRes(R.id.item_back,R.drawable.transion_corner_shape);
            }else {
                helper.setBackgroundRes(R.id.item_back, R.drawable.white_corner_shape);
            }
        } else {
            helper.setBackgroundRes(R.id.item_back, R.drawable.white_corner_shape_two);
        }
    }

    public void setFirstItemBg(boolean isTransion) {
        LogUtils.debugInfo("方法进来了==3==");
        if (mHelper != null) {
            if (mHelper.getPosition() == 1) {
                if (isTransion) {
                    if (mIsFirstTransion) {
                        mIsFirstTransion = false;
                        mHelper.setBackgroundRes(R.id.item_back, R.drawable.transion_corner_shape);
                        LogUtils.debugInfo("变成透明了");
                        notifyItemChanged(1);
                    }
                } else {
                    if (!mIsFirstTransion) {
                        mIsFirstTransion = true;
                        mHelper.setBackgroundRes(R.id.item_back, R.drawable.white_corner_shape);
                        LogUtils.debugInfo("变成白色了");
                        notifyItemChanged(1);
                    }
                }


            } else {
                mHelper.setBackgroundRes(R.id.item_back, R.drawable.white_corner_shape_two);
            }
        }
    }


//    public static class ViewHolder {
//        TextView tv_title, textNum, text1;
//        CircularImage ci_head;
//
//        public ViewHolder(View convertView) {
//
//            tv_title = (TextView) convertView.findViewById(R.id.tv_title);
//            textNum = (TextView) convertView.findViewById(R.id.textNum);
//            ci_head = convertView.findViewById(R.id.ci_head);
//        }
//    }

}