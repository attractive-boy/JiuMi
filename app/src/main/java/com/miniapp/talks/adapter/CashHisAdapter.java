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
import com.miniapp.talks.bean.CashHis;
import com.miniapp.talks.bean.UserFriend;
import com.noober.background.drawable.DrawableCreator;

import java.util.ArrayList;

/**
 * 粉丝，关注
 */
@ActivityScope
public class CashHisAdapter extends BaseQuickAdapter<CashHis.DataBean, BaseViewHolder> {

    private int tag;
    public CashHisAdapter(int tag) {
        super(R.layout.item_cash_his, new ArrayList<>());
        this.tag = tag;
    }

    @Override
    protected void convert(BaseViewHolder helper, CashHis.DataBean item) {


        if(tag == 0){
            helper
                    .setText(R.id.tv_title, "米币兑换米钻")
                    .setText(R.id.tv_userid,  item.getAddtime())
                    .setText(R.id.btn_ok, "-" + item.mibi + "米币");
        }else {
            helper
                    .setText(R.id.tv_title, item.getTitle())
                    .setText(R.id.tv_userid,  item.getAddtime())
                    .setText(R.id.btn_ok, "-" + item.getMoney() + "米币");
        }

    }
}
