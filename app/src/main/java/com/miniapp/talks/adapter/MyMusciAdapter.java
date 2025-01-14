package com.miniapp.talks.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.LocalMusicInfo;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * 本地音乐
 */
@ActivityScope
public class MyMusciAdapter extends BaseQuickAdapter<LocalMusicInfo, BaseViewHolder> {


    public MyMusciAdapter() {
        super(R.layout.item_my_music, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, LocalMusicInfo item) {
        double round = 0.0;
        try {
            round = toFloat(Integer.valueOf(item.size), 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        helper
                .setText(R.id.textName, item.name)
                .setText(R.id.textUser, item.adminUser);
        if(item.isNet){
            helper
                    .setText(R.id.textSize, item.size);
        }else {
            helper
                    .setText(R.id.textSize, round + "M");
        }
        ImageView img1 = helper.getView(R.id.img1);
        img1.setSelected(item.isStart);
    }

    public Double toFloat(int denominator, int numerator) {
        DecimalFormat df = new DecimalFormat("0.00");//设置保留位数
        return Double.valueOf(df.format((float) denominator / numerator));
    }
}
