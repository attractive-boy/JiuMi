package com.miniapp.talks.adapter;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.BoxExchangeBean;

import java.util.ArrayList;

public class BoxExchangeAdapter extends BaseQuickAdapter<BoxExchangeBean.DataBean, BaseViewHolder> {
    public BoxExchangeAdapter() {
        super(R.layout.box_exchange_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BoxExchangeBean.DataBean item) {
        GlideArms.with(mContext).load(item.getShow_img()).into((ImageView) helper.getView(R.id.box_exchange_item));
    }
}
