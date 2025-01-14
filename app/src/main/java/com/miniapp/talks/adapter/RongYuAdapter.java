package com.miniapp.talks.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.MyPersonalCebterBean;

import java.util.ArrayList;

public class RongYuAdapter extends BaseQuickAdapter<MyPersonalCebterBean.DataBean.GloryBean, BaseViewHolder> {
    public RongYuAdapter() {
        super(R.layout.rongyu_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyPersonalCebterBean.DataBean.GloryBean item) {
        GlideArms.with(mContext).load(item.getImg()).error(R.drawable.shibai).into((ImageView) helper.getView(R.id.image_a));
        helper.setText(R.id.text_a, item.getName());
    }
}
