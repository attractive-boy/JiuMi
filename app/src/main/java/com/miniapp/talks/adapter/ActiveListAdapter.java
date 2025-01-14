package com.miniapp.talks.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.ActiveListBean;

import java.util.ArrayList;
import java.util.List;

public class ActiveListAdapter extends BaseQuickAdapter<ActiveListBean.DataBean, BaseViewHolder> {
    public ActiveListAdapter() {
        super(R.layout.more_topic_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ActiveListBean.DataBean item) {
        helper.addOnClickListener(R.id.topic_image);
        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getImg())
                        .placeholder(R.mipmap.default_home)
                        .imageView(helper.getView(R.id.topic_image))
                        .errorPic(R.mipmap.default_home)
                        .build());
    }
}
