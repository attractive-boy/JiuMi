package com.miniapp.talks.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.CategorRoomBean;
import com.miniapp.talks.bean.StartLoftBean;

/**
 * 首页男孩推荐
 */
@ActivityScope
public class HomeBoyAdapter extends MyBaseAdapter<StartLoftBean.DataBean> {

    private Context context;
    private CategorRoomBean categorRoomBean;
    public HomeBoyAdapter(Context context,CategorRoomBean categorRoomBean) {
        this.context = context;
        this.categorRoomBean = categorRoomBean;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder VH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_boy, null);
            VH = new ViewHolder(convertView);
            convertView.setTag(VH);
        } else {
            VH = (ViewHolder) convertView.getTag();
        }
        VH.tv_title.setText(list_adapter.get(position).getRoom_name());
        VH.textNick.setText(list_adapter.get(position).getNickname());
        if (!TextUtils.isEmpty(list_adapter.get(position).getRoom_cover())) {
            ArmsUtils.obtainAppComponentFromContext(context)
                    .imageLoader()
                    .loadImage(context, ImageConfigImpl
                            .builder()
                            .url(list_adapter.get(position).getRoom_cover())
                            .placeholder(R.mipmap.default_home)
                            .imageView(VH.ci_head)
                            .errorPic(R.mipmap.default_home)
                            .build());
        }
        return convertView;
    }


    public static class ViewHolder {
        TextView tv_title,textNick;
        ImageView ci_head;

        public ViewHolder(View convertView) {
            tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            textNick = (TextView) convertView.findViewById(R.id.textNick);
            ci_head = convertView.findViewById(R.id.ci_head);
        }
    }

}