package com.miniapp.talks.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.miniapp.talks.R;
import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.no_tu)
                .error(R.mipmap.no_tu)
                .centerCrop();
        if (context != null)
            Glide.with(context).load(path)
                    .apply(options)
                    .into(imageView);
    }

}
