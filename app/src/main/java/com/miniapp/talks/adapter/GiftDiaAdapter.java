package com.miniapp.talks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.DialogBean;

/**
 * 首页头部
 */
@ActivityScope
public class GiftDiaAdapter extends MyBaseAdapter<String> {

    private Context context;

    public GiftDiaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder VH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_room_dialog_gift, null);
            VH = new ViewHolder(convertView);
            convertView.setTag(VH);
        } else {
            VH = (ViewHolder) convertView.getTag();
        }
        VH.text1.setText(list_adapter.get(position));
        return convertView;
    }


    public static class ViewHolder {
        TextView text1;

        public ViewHolder(View convertView) {
            text1 = (TextView) convertView.findViewById(R.id.text1);
        }
    }

}