package com.miniapp.talks.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.AdminUser;

import java.util.ArrayList;

/**
 * 社区
 */
@ActivityScope
public class DemoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public DemoAdapter() {
        super(R.layout.item_rank, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
//        ImageView imageView = helper.getView(R.id.img);
//        ArmsUtils.obtainAppComponentFromContext(mContext)
//                .imageLoader()
//                .loadImage(mContext, ImageConfigImpl
//                        .builder()
//                        .url(item.getThumbnail())
//                        .placeholder(R.mipmap.default_home)
//                        .imageView(imageView)
//                        .errorPic(R.mipmap.default_home)
//                        .build());
        helper.setText(R.id.text1, item);
//                .setText(R.id.tv_userid, item.getId());
    }
}
