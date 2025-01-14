package com.miniapp.talks.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.UserFriend;

import java.util.ArrayList;

public class BlackListAdapter extends BaseQuickAdapter<UserFriend.DataBean, BaseViewHolder> {
    public BlackListAdapter() {
        super(R.layout.blacklist_adapter_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserFriend.DataBean item) {
        helper.addOnClickListener(R.id.cleanUp);
        //头像
        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getHeadimgurl())
                        .placeholder(R.mipmap.no_tu)
                        .imageView(helper.getView(R.id.blacklist_head_img))
                        .errorPic(R.mipmap.no_tu)
                        .build());
        //昵称
        helper.setText(R.id.blacklist_nickname, item.getNickname());
        //id
        helper.setText(R.id.blacklist_id, "ID：" + item.getId());
    }
}
