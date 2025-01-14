package com.miniapp.talks.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.BoxOpenRecordBean;

import java.util.ArrayList;
import java.util.List;

public class BoxOpenRecordAdapter extends BaseQuickAdapter<BoxOpenRecordBean.DataBean, BaseViewHolder> {


    public BoxOpenRecordAdapter(int layoutResId, @Nullable List<BoxOpenRecordBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BoxOpenRecordBean.DataBean item) {
        helper.setText(R.id.record, item.getAddtime() + "开出" + item.getName());
        helper.setText(R.id.num, "x" + item.getNum());
    }
}
