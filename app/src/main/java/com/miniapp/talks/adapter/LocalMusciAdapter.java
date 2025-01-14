package com.miniapp.talks.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.lzx.starrysky.model.SongInfo;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.LocalMusicInfo;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * 本地音乐
 */
@ActivityScope
public class LocalMusciAdapter extends BaseQuickAdapter<LocalMusicInfo, BaseViewHolder> {


    public LocalMusciAdapter() {
        super(R.layout.item_loacal_music, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, LocalMusicInfo item) {

//        ArmsUtils.obtainAppComponentFromContext(mContext)
//                .imageLoader()
//                .loadImage(mContext, ImageConfigImpl
//                        .builder()
//                        .url(item.getThumbnail())
//                        .placeholder(R.mipmap.default_home)
//                        .imageView(imageView)
//                        .errorPic(R.mipmap.default_home)
//                        .build());
        double round = 0.0;
        try {
            round = toFloat(Integer.valueOf(item.size) ,1024*1024);
        }catch (Exception e){
            e.printStackTrace();
        }
        helper
                .setText(R.id.textName, item.name)
                .setText(R.id.textSize, round + "M")
                .setText(R.id.textUser, item.adminUser);
        ImageView img1 = helper.getView(R.id.img1);
        img1.setSelected(item.isSelect);
    }

    public  Double toFloat(int denominator,int numerator) {
        DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
        return Double.valueOf(df.format((float)denominator/numerator));
    }

}
