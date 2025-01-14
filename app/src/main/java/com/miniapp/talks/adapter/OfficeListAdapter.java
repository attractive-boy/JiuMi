package com.miniapp.talks.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.OffiMessageBean;

import java.util.ArrayList;

/**
 * 官方消息列表
 */
@ActivityScope
public class OfficeListAdapter extends BaseQuickAdapter<OffiMessageBean.DataBean, BaseViewHolder> {


    public OfficeListAdapter() {
        super(R.layout.item_message_office, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, OffiMessageBean.DataBean item) {
        ImageView imageView = helper.getView(R.id.ci_head);
        if(TextUtils.isEmpty(item.getImg())){
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            GlideArms.with(mContext)
                    .load(item.getImg())
                    .placeholder(R.mipmap.default_home)
                    .error(R.mipmap.default_home)
                    .circleCrop()
                    .into(imageView);
        }
        helper
                .setText(R.id.textTime, item.getCreated_at())
                .setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_userid, item.getContent());
        if(TextUtils.isEmpty(item.getUrl())){
            helper.setGone(R.id.layout_look_detail, false);
            helper.setGone(R.id.view_line, false);
        } else {
            helper.setGone(R.id.layout_look_detail, true);
            helper.setGone(R.id.view_line, true);
        }

    }
}
