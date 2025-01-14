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
import com.miniapp.talks.bean.VipCenterBean;

public class VipCenterAdapter extends MyBaseAdapter<VipCenterBean.DataBean.AuthBean> {
    private Context context;

    public VipCenterAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VipCenterHodler VH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.vip_center_item, parent, false);
            VH = new VipCenterHodler(convertView);
            convertView.setTag(VH);
        } else {
            VH = (VipCenterHodler) convertView.getTag();
        }
        VH.vipText.setText(list_adapter.get(position).getName());
        if (list_adapter.get(position).getIs_on() == 0) {
            GlideArms.with(context)
                    .load(list_adapter.get(position).getImg_0())
                    .placeholder(R.mipmap.no_tou)
                    .error(R.mipmap.no_tou)
                    .circleCrop()
                    .into(VH.vipImage);
            VH.vipText.setTextColor(context.getResources().getColor(R.color.font_dddddd));
        } else if (list_adapter.get(position).getIs_on() == 1) {
            GlideArms.with(context)
                    .load(list_adapter.get(position).getImg_1())
                    .placeholder(R.mipmap.no_tou)
                    .error(R.mipmap.no_tou)
                    .circleCrop()
                    .into(VH.vipImage);
            VH.vipText.setTextColor(context.getResources().getColor(R.color.font_333333));
        }
        return convertView;
    }

    static class VipCenterHodler {
        private TextView vipText;
        private ImageView vipImage;

        public VipCenterHodler(@NonNull View itemView) {
            vipText = itemView.findViewById(R.id.vip_text);
            vipImage = itemView.findViewById(R.id.vip_image);
        }
    }
}
