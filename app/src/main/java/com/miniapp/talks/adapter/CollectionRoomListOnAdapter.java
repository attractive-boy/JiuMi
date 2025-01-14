package com.miniapp.talks.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.CollectionRoomListBean;

public class CollectionRoomListOnAdapter extends MyBaseAdapter<CollectionRoomListBean.DataBean.OnBean> {
    private Context context;

    public CollectionRoomListOnAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OnViewHolder OVH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.collection_room_item, null);
            OVH = new OnViewHolder(convertView);
            convertView.setTag(OVH);
        } else {
            OVH = (OnViewHolder) convertView.getTag();
        }
        OVH.tv_title.setText(list_adapter.get(position).getRoom_name());
        OVH.textCount.setText(String.valueOf(list_adapter.get(position).getHot()));
        OVH.tv_userid.setText(list_adapter.get(position).getNickname()+" ID：" + list_adapter.get(position).getUid());
        int sex = list_adapter.get(position).getSex();
        if (sex == 1) {
            OVH.tv_userid.setSelected(true);
        } else {
            OVH.tv_userid.setSelected(false);
        }
        if (!TextUtils.isEmpty(list_adapter.get(position).getRoom_cover())) {
            ArmsUtils.obtainAppComponentFromContext(context)
                    .imageLoader()
                    .loadImage(context, ImageConfigImpl
                            .builder()
                            .url(list_adapter.get(position).getRoom_cover())
                            .placeholder(R.mipmap.no_tou)
                            .imageView(OVH.ci_head)
                            .errorPic(R.mipmap.no_tou)
                            .build());
        }

        return convertView;
    }

    public static class OnViewHolder {
        TextView tv_title, tv_userid, textCount;
        ImageView imgBg;
        CircularImage ci_head;
        RelativeLayout relativeLayout;

        public OnViewHolder(View convertView) {
            tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            tv_userid = (TextView) convertView.findViewById(R.id.tv_userid);
            textCount = (TextView) convertView.findViewById(R.id.textCount);
            imgBg = convertView.findViewById(R.id.imgBg);
            ci_head = convertView.findViewById(R.id.ci_head);
            relativeLayout = convertView.findViewById(R.id.relative);
        }
    }
}
