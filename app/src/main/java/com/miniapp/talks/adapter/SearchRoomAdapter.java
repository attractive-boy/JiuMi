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
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.RecommenRoomBean;
import com.miniapp.talks.bean.Search;

/**
 * 首页推荐
 */
@ActivityScope
public class SearchRoomAdapter extends MyBaseAdapter<Search.DataBean.RoomsBean> {

    private Context context;

    public SearchRoomAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder VH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_recommend, null);
            VH = new ViewHolder(convertView);
            convertView.setTag(VH);
        } else {
            VH = (ViewHolder) convertView.getTag();
        }
        VH.tv_title.setText(list_adapter.get(position).getRoom_name());
        VH.textCount.setText(String.valueOf(list_adapter.get(position).getHot()));
        VH.tv_userid.setText(list_adapter.get(position).getNickname() + " ID：" + list_adapter.get(position).getUid());
        int sex = list_adapter.get(position).getSex();
        if (sex == 1) {
            VH.tv_userid.setSelected(true);
        } else {
            VH.tv_userid.setSelected(false);
        }
        if (!TextUtils.isEmpty(list_adapter.get(position).getRoom_cover())) {
            GlideArms.with(context)
                    .load(list_adapter.get(position).getRoom_cover())
                    .placeholder(R.mipmap.no_tou)
                    .error(R.mipmap.no_tou)
                    .circleCrop()
                    .into(VH.ci_head);
        }
        return convertView;
    }


    public static class ViewHolder {
        TextView tv_title, tv_userid, textCount;
        ImageView imgBg;
        ImageView ci_head;

        public ViewHolder(View convertView) {
            tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            tv_userid = (TextView) convertView.findViewById(R.id.tv_userid);
            textCount = (TextView) convertView.findViewById(R.id.textCount);
            imgBg = convertView.findViewById(R.id.imgBg);
            ci_head = convertView.findViewById(R.id.ci_head);
        }
    }

}