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
import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.base.MyBaseAdapter;
import com.miniapp.talks.bean.CategorRoomBean;
import com.miniapp.talks.bean.MusicYinxiao;
import com.miniapp.talks.bean.StartLoftBean;
import com.miniapp.talks.bean.Yinxiao;

import io.agora.rtc.RtcEngine;

/**
 * 首页男孩推荐
 */
@ActivityScope
public class YinxiaoAdapter extends MyBaseAdapter<MusicYinxiao.DataBean.YinxiaoBean> {

    private Context context;
    private RtcEngine mRtcEngine;
    public YinxiaoAdapter(Context context, RtcEngine mRtcEngine) {
        this.context = context;
        this.mRtcEngine = mRtcEngine;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder VH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_yinxiao, null);
            VH = new ViewHolder(convertView);
            convertView.setTag(VH);
        } else {
            VH = (ViewHolder) convertView.getTag();
        }
        VH.text1.setText(list_adapter.get(position).getMusic_name());

        VH.text1.setOnClickListener(v -> {
            if(mRtcEngine!=null) {
                mRtcEngine.getAudioEffectManager()
                        .playEffect(list_adapter.get(position).getId(),
                                list_adapter.get(position).getMusic_url(), 0, 1.0,
                                0.0, 50.0, true);
            }
        });
        return convertView;
    }


    public static class ViewHolder {
        TextView text1;

        public ViewHolder(View convertView) {
            text1 = (TextView) convertView.findViewById(R.id.text1);
        }
    }

}