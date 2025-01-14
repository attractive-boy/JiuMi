package com.miniapp.talks.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.base.BaseApplication;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.CategorRoomBean;
import com.miniapp.talks.utils.FullScreenUtil;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;
import java.util.List;

public class OneImageYuanJiaoAdapter extends MyBaseAdapter<String> {
    private Context context;

//    public OneImageYuanJiaoAdapter(Context context) {
//        this.context = context;
//    }

    public OneImageYuanJiaoAdapter(Context context) {
        this.context = context;
    }


//    @Override
//    protected void convert(BaseViewHolder helper, String item) {
//        helper.addOnClickListener(R.id.fiv);
//
//        helper.setVisible(R.id.ll_del, false);
//        QMUIRadiusImageView image = helper.getView(R.id.fiv);
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(R.color.white)
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
//        Glide.with(mContext)
//                .load(item)
//                .apply(options)
//                .into(image);
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder VH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_filter_image, null);
            VH = new ViewHolder(convertView);
            convertView.setTag(VH);
        } else {
            VH = (ViewHolder) convertView.getTag();
        }
        VH.iv_del.setVisibility(View.GONE);

        if (!TextUtils.isEmpty(list_adapter.get(position))) {
            ArmsUtils.obtainAppComponentFromContext(context)
                    .imageLoader()
                    .loadImage(context, ImageConfigImpl
                            .builder()
                            .url(list_adapter.get(position))
                            .placeholder(R.mipmap.no_tu)
                            .imageView(VH.tv_title)
                            .errorPic(R.mipmap.no_tu)
                            .build());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.color.white)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context)
                    .load(list_adapter.get(position))
                    .apply(options)
                    .into(VH.tv_title);

        }
//
        return convertView;
    }


    public static class ViewHolder {
        QMUIRadiusImageView tv_title;
        ImageView iv_del;
        RelativeLayout layoutImg;

        public ViewHolder(View convertView) {
            tv_title = convertView.findViewById(R.id.fiv);
            iv_del = convertView.findViewById(R.id.iv_del);
            layoutImg = convertView.findViewById(R.id.layout_img);

            int screenWidth = QMUIDisplayHelper.getScreenWidth(BaseApplication.mApplication) - QMUIDisplayHelper.dp2px(BaseApplication.mApplication, 24);

            int imgWidth = screenWidth*1/3-QMUIDisplayHelper.dp2px(BaseApplication.mApplication, 10);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutImg.getLayoutParams();
            params.width = imgWidth;
            params.height = imgWidth;
            layoutImg.setLayoutParams(params);

            params = (RelativeLayout.LayoutParams) tv_title.getLayoutParams();
            params.width = imgWidth;
            params.height = imgWidth;
            tv_title.setLayoutParams(params);

        }
    }
}
