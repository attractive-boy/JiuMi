package com.miniapp.talks.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.GlideArms;
import com.makeramen.roundedimageview.RoundedImageView;
import com.miniapp.talks.R;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.Microphone;

import java.util.List;

public class LiWuUserAdapter extends RecyclerView.Adapter<LiWuUserAdapter.VH> {
    private Context context;
    private RecyclerView recyclerView;
    private List<Microphone.DataBean.MicrophoneBean> data;

    public List<Microphone.DataBean.MicrophoneBean> getData() {
        return data;
    }

    public LiWuUserAdapter(Context context, RecyclerView recyclerView,
                           List<Microphone.DataBean.MicrophoneBean> data) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.data = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH h = null;
        h = new VH(LayoutInflater.from(context)
                .inflate(R.layout.item_h_user, parent, false));
        return h;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

        GlideArms.with(context)
                .load(data.get(position).getHeadimgurl())
                .error(R.mipmap.room_kazuo_kong)
                .placeholder(R.mipmap.room_kazuo_kong)
                .circleCrop()
                .into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(data.get(position).getHeadimgurl())) {
                    if (data.get(position).getUser_id().equals(UserManager.getUser().getUserId() + "")) {
                        return;
                    }
                    if (holder.imgBg.getVisibility() == View.VISIBLE) {
                        data.get(position).isSelect = true;
                        notifyDataSetChanged();
                    } else {
                        data.get(position).isSelect = false;
                        notifyDataSetChanged();
                    }
                }
            }
        });


        if (data.get(position).isIs_zhuchi()){
            holder.tv.setText("主持");
        }else {
            holder.tv.setText(String.valueOf(data.get(position).getIndexl()));
        }
        if (data.get(position).getSex() == 1) {
            holder.tv.setBackground(context.getResources().getDrawable(R.mipmap.room_xuhao_boy));
        } else if (data.get(position).getSex() == 2) {
            holder.tv.setBackground(context.getResources().getDrawable(R.mipmap.room_xuhao_girl));
        } else {
            holder.tv.setBackground(context.getResources().getDrawable(R.mipmap.room_xuhao_weizhi));
        }


        if (data.get(position).isSelect) {
            holder.imgBg.setVisibility(View.GONE);
            holder.iv.setBorderColor(context.getResources().getColor(R.color.font_ff3e6d));
        } else {
            holder.imgBg.setVisibility(View.VISIBLE);
            holder.iv.setBorderColor(context.getResources().getColor(R.color.touming));

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class VH extends RecyclerView.ViewHolder {

        TextView tv;
        RoundedImageView iv;
        RoundedImageView imgBg;

        public VH(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item_img);
            tv = (TextView) itemView.findViewById(R.id.tv);
            imgBg = itemView.findViewById(R.id.imgBg);
        }
    }
}
