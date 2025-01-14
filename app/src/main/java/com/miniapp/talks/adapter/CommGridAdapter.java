package com.miniapp.talks.adapter;


import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.TopicBean;

public class CommGridAdapter extends BaseQuickAdapter<TopicBean.DataBean, BaseViewHolder> {

    public CommGridAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopicBean.DataBean item) {
        helper.addOnClickListener(R.id.item);

//        helper.setText(R.id.comm_grid_topic_number_text, item.getTalk_num() + "");
//        helper.setText(R.id.comm_grid_topic_tit_text, item.getTag_name());
        if (!TextUtils.isEmpty(item.getTopic_img())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getTopic_img())
                            .placeholder(R.mipmap.no_tu)
                            .imageView(helper.getView(R.id.comm_grid_back_image))
                            .errorPic(R.mipmap.no_tu)
                            .build());
        }
    }
}
