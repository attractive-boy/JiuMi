package com.miniapp.talks.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.UserFriend;

import java.util.ArrayList;

/**
 * 社区
 */
@ActivityScope
public class FriendAdapter extends BaseQuickAdapter<UserFriend.DataBean, BaseViewHolder> {


    public FriendAdapter() {
        super(R.layout.item_message_friend, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, UserFriend.DataBean item) {
        ImageView imageView = helper.getView(R.id.ci_head);
        GlideArms.with(mContext)
                .load(item.getHeadimgurl())
                .placeholder(R.mipmap.no_tou)
                .error(R.mipmap.no_tou)
                .circleCrop()
                .into(imageView);
        helper
                .setText(R.id.tv_title, item.getNickname())
                .addOnClickListener(R.id.btn_ok)
                .addOnClickListener(R.id.ci_head)
                .setText(R.id.tv_userid, "ID:" + item.getId());

        int sex = item.getSex();
        TextView textView = helper.getView(R.id.tv_userid);
        if(sex  == 1){
            textView.setSelected(true);
        }else {
            textView.setSelected(false);
        }


    }
}
