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
import com.miniapp.talks.bean.EmojiBean;
import com.miniapp.talks.bean.StartLoftBean;

/**
 * 表情
 */
@ActivityScope
public class EmojiAdapter extends MyBaseAdapter<EmojiBean.DataBean> {

    private Context context;
    public EmojiAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder VH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_emoji, null);
            VH = new ViewHolder(convertView);
            convertView.setTag(VH);
        } else {
            VH = (ViewHolder) convertView.getTag();
        }
        VH.tv_title.setText(list_adapter.get(position).getName());
        if (!TextUtils.isEmpty(list_adapter.get(position).getEmoji())) {
            ArmsUtils.obtainAppComponentFromContext(context)
                    .imageLoader()
                    .loadImage(context, ImageConfigImpl
                            .builder()
                            .url(list_adapter.get(position).getEmoji())
                            .placeholder(R.mipmap.music_tianjia)
                            .imageView(VH.ci_head)
                            .errorPic(R.mipmap.music_tianjia)
                            .build());
        }
        return convertView;
    }


    public static class ViewHolder {
        TextView tv_title;
        ImageView ci_head;

        public ViewHolder(View convertView) {
            tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            ci_head = convertView.findViewById(R.id.ci_head);
        }
    }

}