package com.miniapp.talks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.activity.my.MyPersonalCenterActivity;
import com.miniapp.talks.activity.room.AdminHomeActivity;
import com.miniapp.talks.bean.GiftListBean;
import com.miniapp.talks.bean.OnlineUser;
import com.miniapp.talks.view.MiniCircleRecyclerView;

import java.util.List;

import me.khrystal.library.widget.CircleRecyclerView;

public class OnlineUserAdapter extends RecyclerView.Adapter<OnlineUserAdapter.VH> {

    private Context context;
    private List<OnlineUser.DataBeanX.DataBean> data;

    public List<OnlineUser.DataBeanX.DataBean> getData() {
        return data;
    }


    boolean mNeedLoop = false;

    public OnlineUserAdapter(Context context, List<OnlineUser.DataBeanX.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH h = null;
        h = new VH(LayoutInflater.from(context)
                .inflate(R.layout.item_online_user, parent, false));
        return h;
    }


    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.tvName.setText(data.get(position).getNickname());
        holder.tvId.setText("ID:"+data.get(position).getId());
        Glide.with(holder.itemView.getContext()).load(data.get(position).getHeadimgurl()).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(holder.itemView.getContext(), MyPersonalCenterActivity.class);
                intent1.putExtra("sign", 0);
                intent1.putExtra("id", data.get(position).getId()+"");
                intent1.putExtra("isRoom", true);
                ArmsUtils.startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public static class VH extends RecyclerView.ViewHolder {

        TextView tvName, tvId;
        ImageView iv;

        public VH(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvId = (TextView) itemView.findViewById(R.id.tv_id);
        }
    }
}
