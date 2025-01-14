package com.miniapp.talks.adapter;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.OpenBoxBean;

import java.util.ArrayList;

public class BXAdapter extends BaseQuickAdapter<OpenBoxBean.DataBean.AwardListBean, BaseViewHolder> {


    public BXAdapter() {
        super(R.layout.bx_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OpenBoxBean.DataBean.AwardListBean item) {
        if (!item.getShow_img().isEmpty()) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getShow_img())
                            .placeholder(R.mipmap.default_home)
                            .imageView(helper.getView(R.id.liwu))
                            .errorPic(R.mipmap.default_home)
                            .build());
        }
        helper.setText(R.id.num, "x" + item.getNum());
        helper.setText(R.id.name, item.getName());
    }
}
