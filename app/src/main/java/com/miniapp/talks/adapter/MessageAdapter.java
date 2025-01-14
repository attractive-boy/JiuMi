package com.miniapp.talks.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.MessageYoBean;
import com.miniapp.talks.view.ZhengFangImageView;
import com.noober.background.view.BLImageView;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends BaseQuickAdapter<MessageYoBean.DataBean.CommentBean, BaseViewHolder> {

    public MessageAdapter() {
        super(R.layout.message_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MessageYoBean.DataBean.CommentBean item) {
        helper.addOnClickListener(R.id.head_image);

        //头像
        if (!TextUtils.isEmpty(item.getHeadimgurl())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getHeadimgurl())
                            .placeholder(R.mipmap.no_tou)
                            .imageView(helper.getView(R.id.head_image))
                            .errorPic(R.mipmap.no_tou)
                            .build());
        }

        //昵称以及评论内容
        helper.setText(R.id.name_text, item.getNickname());
        helper.setText(R.id.content_text, item.getContent());
        if (item.getIs_reply() == 0) {
            helper.setText(R.id.reply_text, "评论了：");
        } else {
            helper.setText(R.id.reply_text, "回复了：");
        }

        //评论的时间
        helper.setText(R.id.time_text, item.getCreated_at());

        //有图片的情况下
        if (!item.getImage().isEmpty()) {
            QMUIRadiusImageView view = helper.getView(R.id.image_message);
            view.setVisibility(View.VISIBLE);
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getImage())
                            .placeholder(R.mipmap.no_tu)
                            .imageView(helper.getView(R.id.image_message))
                            .errorPic(R.mipmap.no_tu)
                            .build());
        } else {
            helper.getView(R.id.image_message).setVisibility(View.GONE);
        }
        //音频的时长
        helper.setText(R.id.dy_voice_time, item.getAudio_time());
        //有音频的情况下
        if (!item.getAudio().isEmpty()) {
            helper.getView(R.id.dy_voice).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.dy_voice).setVisibility(View.GONE);
        }

    }
}
