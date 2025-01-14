package com.miniapp.talks.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.UserFriend;

import java.util.ArrayList;
import java.util.List;

public class MyConcernAdapter extends BaseQuickAdapter<UserFriend.DataBean, BaseViewHolder> {


    public MyConcernAdapter(int layoutResId, @Nullable List<UserFriend.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserFriend.DataBean item) {
        helper.addOnClickListener(R.id.btn_ok)
                .addOnClickListener(R.id.btn_no_ok)
                .addOnClickListener(R.id.ci_head);

        ImageView imageView = helper.getView(R.id.ci_head);
        GlideArms.with(mContext)
                .load(item.getHeadimgurl())
                .placeholder(R.mipmap.no_tu)
                .error(R.mipmap.no_tu)
                .circleCrop()
                .into(imageView);

        helper.setText(R.id.tv_title, item.getNickname())
                .setText(R.id.tv_userid, "ID:" + item.getId());

        if (item.getSex() == 1) {
            helper.setImageResource(R.id.sex_imag, R.mipmap.gender_boy);
        } else {
            helper.setImageResource(R.id.sex_imag, R.mipmap.gender_girl);
        }
        if (item.getIs_follow() == 0) {
            helper.getView(R.id.btn_ok).setVisibility(View.VISIBLE);
            helper.getView(R.id.btn_no_ok).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.btn_ok).setVisibility(View.GONE);
            helper.getView(R.id.btn_no_ok).setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void convertPayloads(@NonNull BaseViewHolder helper, UserFriend.DataBean item, @NonNull List<Object> payloads) {
        super.convertPayloads(helper, item, payloads);
        if (payloads.isEmpty()) {
            convert(helper, item);
        } else {
            String payload = (String) payloads.get(0);
            if (payload.equals("follow")) {
                item.setIs_follow(1);
                helper.getView(R.id.btn_no_ok).setVisibility(View.VISIBLE);
                helper.getView(R.id.btn_ok).setVisibility(View.GONE);
            } else if (payload.equals("cancelFollow")) {
                item.setIs_follow(0);
                helper.getView(R.id.btn_no_ok).setVisibility(View.GONE);
                helper.getView(R.id.btn_ok).setVisibility(View.VISIBLE);
            }
        }
    }
}
