package com.miniapp.talks.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.Search;
import com.miniapp.talks.bean.UserFriend;
import com.noober.background.drawable.DrawableCreator;

import java.util.ArrayList;

/**
 * 粉丝，关注
 */
@ActivityScope
public class SearchUserAdapter extends BaseQuickAdapter<Search.DataBean.UserBean, BaseViewHolder> {

    public SearchUserAdapter() {
        super(R.layout.item_message_friend, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, Search.DataBean.UserBean item) {
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
                .setGone(R.id.btn_ok, true)
                .setText(R.id.tv_userid, "ID:" + item.getId());

        int sex = item.getSex();
        TextView textView = helper.getView(R.id.tv_userid);
        if (sex == 1) {
            textView.setSelected(true);
        } else {
            textView.setSelected(false);
        }

        TextView btn_ok = helper.getView(R.id.btn_ok);
        if (item.getIs_follow() == 1) {
            btn_ok.setText("已关注");
            Drawable drawable = new DrawableCreator.Builder()
                    .setCornersRadius(90f)
                    .setRipple(true, Color.parseColor("#dddddd"))
                    .setSolidColor(Color.parseColor("#ffffff"))
                    .setStrokeColor(Color.parseColor("#dddddd"))
                    .setStrokeWidth(1f)
                    .build();
            btn_ok.setBackground(drawable);
            btn_ok.setTextColor(mContext.getResources().getColor(R.color.font_dddddd));
        } else {
            btn_ok.setText("关注");
            Drawable drawable = new DrawableCreator.Builder()
                    .setCornersRadius(90f)
                    .setRipple(true, Color.parseColor("#71C671"))
                    .setSolidColor(Color.parseColor("#ffffff"))
                    .setStrokeColor(Color.parseColor("#FF3E6D"))
                    .setStrokeWidth(1f)
                    .build();
            btn_ok.setBackground(drawable);
            btn_ok.setTextColor(mContext.getResources().getColor(R.color.font_ff3e6d));
        }

    }
}
