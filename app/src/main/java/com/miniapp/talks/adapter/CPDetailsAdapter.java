package com.miniapp.talks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.CPDetailsBean;

public class CPDetailsAdapter extends MyBaseAdapter<CPDetailsBean.DataBean.AuthBean> {
    private Context context;

    public CPDetailsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CPDetailsViewHolder CH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.vip_center_item, parent, false);
            CH = new CPDetailsViewHolder(convertView);
            convertView.setTag(CH);
        }else {
            CH = (CPDetailsViewHolder) convertView.getTag();
        }
        CH.vipText.setText(list_adapter.get(position).getName());
        if (list_adapter.get(position).getIs_on() == 0) {
            GlideArms.with(context)
                    .load(list_adapter.get(position).getImg_0())
                    .placeholder(R.mipmap.no_tou)
                    .error(R.mipmap.no_tou)
                    .circleCrop()
                    .into(CH.vipImage);
        } else if (list_adapter.get(position).getIs_on() == 1) {
            GlideArms.with(context)
                    .load(list_adapter.get(position).getImg_1())
                    .placeholder(R.mipmap.no_tou)
                    .error(R.mipmap.no_tou)
                    .circleCrop()
                    .into(CH.vipImage);
        }
        return convertView;
    }

    static class CPDetailsViewHolder{
        private TextView vipText;
        private ImageView vipImage;

        public CPDetailsViewHolder(@NonNull View itemView) {
            vipText = itemView.findViewById(R.id.vip_text);
            vipImage = itemView.findViewById(R.id.vip_image);
        }
    }
}
