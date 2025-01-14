package com.miniapp.talks.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.LocalMusicInfo;
import com.miniapp.talks.bean.Yinxiao;

import java.util.ArrayList;

/**
 * 本地音乐
 */
@ActivityScope
public class NetMusciAdapter extends BaseQuickAdapter<Yinxiao.DataBean, BaseViewHolder> {


    public NetMusciAdapter() {
        super(R.layout.item_net_music, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, Yinxiao.DataBean item) {

        helper
                .setText(R.id.textName, item.getMusic_name())
                .setText(R.id.textCreate, "用户" + item.getUpload_user() + "上传    " + item.music_size)
                .setText(R.id.textUser, item.getSinger());
        ImageView img1 = helper.getView(R.id.img1);
    }
}
