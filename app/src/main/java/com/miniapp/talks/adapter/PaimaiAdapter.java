package com.miniapp.talks.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.LocalMusicInfo;
import com.miniapp.talks.bean.WaitList;

import java.util.ArrayList;

/**
 * 排麦
 */
@ActivityScope
public class PaimaiAdapter extends BaseQuickAdapter<WaitList.DataBeanX.DataBean, BaseViewHolder> {


    public PaimaiAdapter() {
        super(R.layout.item_paimai, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, WaitList.DataBeanX.DataBean item) {

        helper.setText(R.id.textName, item.getNickname())
                .setText(R.id.textUser, "ID:  " + item.getUser_id());
        ImageView imgUser = helper.getView(R.id.imgUser);
        GlideArms.with(mContext)
                .load(item.getHeadimgurl())
                .placeholder(R.mipmap.no_tou)
                .error(R.mipmap.no_tou)
                .circleCrop()
                .into(imgUser);
    }
}
