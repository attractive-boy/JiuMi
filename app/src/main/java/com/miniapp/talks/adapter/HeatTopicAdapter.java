package com.miniapp.talks.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.TopicBean;

import java.util.ArrayList;

public class HeatTopicAdapter extends BaseQuickAdapter<TopicBean.DataBean, BaseViewHolder> {
    public HeatTopicAdapter() {
        super(R.layout.more_topic_item, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, TopicBean.DataBean item) {
        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getTopic_img())
                        .placeholder(R.mipmap.default_home)
                        .imageView(helper.getView(R.id.topic_image))
                        .errorPic(R.mipmap.default_home)
                        .build());
    }
}
