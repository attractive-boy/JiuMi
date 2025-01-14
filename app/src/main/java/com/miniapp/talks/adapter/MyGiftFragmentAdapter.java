package com.miniapp.talks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.MyPersonalCebterBean;

public class MyGiftFragmentAdapter extends MyBaseAdapter<MyPersonalCebterBean.DataBean.GiftsBean> {
    private Context context;

    public MyGiftFragmentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.my_gift_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //礼物的名称
        viewHolder.nametextView.setText(list_adapter.get(position).getGiftName());
        //礼物的数量
        viewHolder.numtextView.setText(list_adapter.get(position).getSum());
        //礼物的图片
        ArmsUtils.obtainAppComponentFromContext(context)
                .imageLoader()
                .loadImage(context, ImageConfigImpl
                        .builder()
                        .url(list_adapter.get(position).getImg())
                        .placeholder(R.mipmap.no_tu)
                        .imageView(viewHolder.imageView)
                        .errorPic(R.mipmap.no_tu)
                        .build());
        return convertView;
    }

    public static class ViewHolder {
        TextView numtextView, nametextView;
        ImageView imageView;

        public ViewHolder(View view) {
            numtextView = view.findViewById(R.id.gift_num);
            nametextView = view.findViewById(R.id.gift_name);
            imageView = view.findViewById(R.id.gift_image);
        }
    }

}
