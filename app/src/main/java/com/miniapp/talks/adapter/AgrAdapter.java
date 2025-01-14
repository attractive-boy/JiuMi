package com.miniapp.talks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.AgreementBean;

public class AgrAdapter extends MyBaseAdapter<AgreementBean.DataBean> {
    private Context context;

    public AgrAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AgrHolder AH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.agr_item, parent, false);
            AH = new AgrHolder(convertView);
            convertView.setTag(AH);
        } else {
            AH = (AgrHolder) convertView.getTag();
        }
        AH.agrText.setText(list_adapter.get(position).getName());
        AH.agrImg.setImageResource(R.mipmap.my_enter);
        return convertView;
    }

    static class AgrHolder {
        TextView agrText;
        ImageView agrImg;

        public AgrHolder(@NonNull View itemView) {
            agrText = itemView.findViewById(R.id.name_agr);
            agrImg = itemView.findViewById(R.id.you_img);
        }
    }
}
