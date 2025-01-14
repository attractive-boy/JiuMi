package com.miniapp.talks.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.RoomRankBean;

import java.util.ArrayList;
import java.util.List;

public class RoomRankAdapter extends BaseQuickAdapter<RoomRankBean.DataBean.OtherBean, BaseViewHolder> {
    private int type;

    public RoomRankAdapter(int type) {
        super(R.layout.room_rank_item, new ArrayList<>());
        this.type = type;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RoomRankBean.DataBean.OtherBean item) {
        helper.setText(R.id.tit, type==3?"星锐值":"米钻:");
        if (type == 1) {
            helper.setTextColor(R.id.miNum, mContext.getResources().getColor(R.color.color_FFBA1C));
            helper.setTextColor(R.id.ranking, mContext.getResources().getColor(R.color.color_FFBA1C));
        } else {
            helper.setTextColor(R.id.miNum, mContext.getResources().getColor(R.color.font_ff3e6d));
            helper.setTextColor(R.id.ranking, mContext.getResources().getColor(R.color.font_ff3e6d));
        }
        helper.setText(R.id.ranking, String.valueOf(helper.getPosition() + 4))
                .setText(R.id.name, item.getName())
                .setText(R.id.miNum, item.getMizuan());
        if (!TextUtils.isEmpty(item.getImg())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getImg())
                            .placeholder(R.mipmap.no_tou)
                            .imageView(helper.getView(R.id.ci_head))
                            .errorPic(R.mipmap.no_tou)
                            .build());
        }
        if (item.getSex() == 1) {
            helper.setImageResource(R.id.sex, R.mipmap.gender_boy);
        } else if (item.getSex() == 2) {
            helper.setImageResource(R.id.sex, R.mipmap.gender_girl);
        }
    }
}
