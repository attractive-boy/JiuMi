package com.miniapp.talks.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.MyPackBean;

import java.util.List;

public class BeiBaoRecycAdapterTwo extends BaseQuickAdapter<MyPackBean.DataBean, BaseViewHolder> {
    public BeiBaoRecycAdapterTwo(int layoutResId, @Nullable List<MyPackBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyPackBean.DataBean item) {
        //加载图片
        if (!TextUtils.isEmpty(item.getShow_img())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getShow_img())
                            .placeholder(R.mipmap.no_tu)
                            .imageView(helper.getView(R.id.beibao_recyc_image))
                            .errorPic(R.mipmap.no_tu)
                            .build());
        }
        //设置名称
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.name, item.getName());
        }
        //设置背景以及物品名称的背景
        if ((helper.getPosition() + 1) % 4 == 1) {
            helper.getView(R.id.beibao_recyc_back).setBackgroundResource(R.mipmap.bag_bgl_duanzs);
            helper.getView(R.id.name).setBackgroundResource(R.mipmap.bag_label_zi);
        } else if ((helper.getPosition() + 1) % 4 == 2) {
            helper.getView(R.id.beibao_recyc_back).setBackgroundResource(R.mipmap.bag_bgl_duanfs);
            helper.getView(R.id.name).setBackgroundResource(R.mipmap.bag_label_fen);
        } else if ((helper.getPosition() + 1) % 4 == 3) {
            helper.getView(R.id.beibao_recyc_back).setBackgroundResource(R.mipmap.bag_bgl_duanls);
            helper.getView(R.id.name).setBackgroundResource(R.mipmap.bag_label_lan);
        } else if ((helper.getPosition() + 1) % 4 == 0) {
            helper.getView(R.id.beibao_recyc_back).setBackgroundResource(R.mipmap.bag_bgl_duanhs);
            helper.getView(R.id.name).setBackgroundResource(R.mipmap.bag_label_huang);
        }
        if (item.getIs_dress() == 0) {
            helper.getView(R.id.sign).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.sign).setVisibility(View.VISIBLE);
        }
        if (item.isSelect()) {
            helper.getView(R.id.beijing).setBackgroundResource(R.mipmap.bag_xz);
        } else {
            helper.getView(R.id.beijing).setBackgroundResource(0);
        }
    }

    @Override
    protected void convertPayloads(@NonNull BaseViewHolder helper, MyPackBean.DataBean item, @NonNull List<Object> payloads) {
        super.convertPayloads(helper, item, payloads);
        if (payloads.isEmpty()) {
            convert(helper, item);
        } else {
            String payload = (String) payloads.get(0);
            if (payload.equals("chenggong")) {
                helper.getView(R.id.sign).setVisibility(View.VISIBLE);
            } else if (payload.equals("quxiao")) {
                helper.getView(R.id.sign).setVisibility(View.GONE);
            }
        }
    }
}
