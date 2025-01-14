package com.miniapp.talks.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.app.view.CircularImage;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.RecommenRoomBean;

/**
 * 首页头部
 */
@ActivityScope
public class HomeTopAdapter extends MyBaseAdapter<RecommenRoomBean.DataBean> {

    private Context context;

    public HomeTopAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder VH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recom, null);
            VH = new ViewHolder(convertView);
            convertView.setTag(VH);
        } else {
            VH = (ViewHolder) convertView.getTag();
        }
        VH.tv_title.setText(list_adapter.get(position).getRoom_name());
        VH.tv_userid.setText(list_adapter.get(position).getNickname() + "：" + list_adapter.get(position).getNumid());
        int sex = list_adapter.get(position).getSex();
        VH.tv_userid.setSelected(sex == 1);
        if (!TextUtils.isEmpty(list_adapter.get(position).getRoom_cover())) {
            ArmsUtils.obtainAppComponentFromContext(context)
                    .imageLoader()
                    .loadImage(context, ImageConfigImpl
                            .builder()
                            .url(list_adapter.get(position).getRoom_cover())
                            .placeholder(R.mipmap.no_tu)
                            .imageView(VH.ci_head)
                            .errorPic(R.mipmap.no_tu)
                            .build());
        }
        if (position % 2 == 1) {
            GlideArms.with(context)
                    .load(R.mipmap.lv)
                    .error(R.mipmap.lv)
                    .placeholder(R.mipmap.lv)
                    .centerCrop()
                    .into(VH.imgBg);
            VH.textDec.setTextColor(context.getResources().getColor(R.color.font_75adff));
        } else {
            GlideArms.with(context)
                    .load(R.mipmap.lan)
                    .error(R.mipmap.lan)
                    .placeholder(R.mipmap.lan)
                    .centerCrop()
                    .into(VH.imgBg);
            VH.textDec.setTextColor(context.getResources().getColor(R.color.font_ff8e75));
        }
        if (!TextUtils.isEmpty(list_adapter.get(position).getRoom_type())) {
            String room_type = list_adapter.get(position).getRoom_type();
            switch (room_type) {
                case "1":
                    VH.textDec.setText("语圈");
                    VH.textDec.setBackground(context.getResources().getDrawable(R.drawable.label_lianggezi));
                    break;
                case "2":
                    VH.textDec.setText("文坛");
                    VH.textDec.setBackground(context.getResources().getDrawable(R.drawable.label_lianggezi));
                    break;
                case "3":
                    VH.textDec.setText("开黑");
                    VH.textDec.setBackground(context.getResources().getDrawable(R.drawable.label_lianggezi));
                    break;
                case "4":
                    VH.textDec.setText("娱乐");
                    VH.textDec.setBackground(context.getResources().getDrawable(R.drawable.label_lianggezi));
                    break;
                case "5":
                    VH.textDec.setText("二次元");
                    VH.textDec.setBackground(context.getResources().getDrawable(R.drawable.label_sangezi));
                    break;
                case "6":
                    VH.textDec.setText("FM");
                    VH.textDec.setBackground(context.getResources().getDrawable(R.drawable.label_lianggezi));
                    break;
            }
        }
        return convertView;
    }


    public static class ViewHolder {
        TextView tv_title, tv_userid, textDec;
        ImageView imgBg;
        CircularImage ci_head;

        public ViewHolder(View convertView) {
            tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            tv_userid = (TextView) convertView.findViewById(R.id.tv_userid);
            textDec = (TextView) convertView.findViewById(R.id.textDec);
            imgBg = convertView.findViewById(R.id.imgBg);
            ci_head = convertView.findViewById(R.id.ci_head);
        }
    }

}