package com.miniapp.talks.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.GiftListBean;
import com.miniapp.talks.view.MiniCircleRecyclerView;

import java.util.List;

import me.khrystal.library.widget.CircleRecyclerView;

/**
 * 作者:sgm
 * 描述:礼物的列表
 */
public class StoneGiftAdapter extends RecyclerView.Adapter<StoneGiftAdapter.VH> {

    public interface OnItemClickListener{
        public void onItemClick(int position);
    }


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        notifyDataSetChanged();
    }

    private Context context;
    private List<GiftListBean.DataBean.BaoshiBean> data;
    private int selectPosition=-1;

    public List<GiftListBean.DataBean.BaoshiBean> getData() {
        return data;
    }


    boolean mNeedLoop = true;

    public StoneGiftAdapter(Context context, List<GiftListBean.DataBean.BaoshiBean> data,boolean needLoop) {
        this.context = context;
        this.data = data;
        this.mNeedLoop = needLoop;
    }

    public void setNeedLoop(boolean needLoop){
        this.mNeedLoop = needLoop;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH h = null;
        h = new VH(LayoutInflater.from(context)
                .inflate(R.layout.item_h, parent, false));
        return h;
    }


    @Override
    public void onBindViewHolder(VH holder, int position) {
//        LogUtils.debugInfo("====刷新列表" + data.get(position % data.size()).getName());
        holder.tv.setText(data.get(position % data.size()).getName());
        holder.tvPrice.setText(String.valueOf(data.get(position % data.size()).getPrice()));

        GlideArms.with(context)
                .load(data.get(position % data.size()).getImg())
//                .circleCrop()
                .into(holder.iv);

        holder.itemView.setTag(R.string.item_position, position);
        holder.itemView.setOnClickListener(v -> {
            if(onItemClickListener!=null){
                onItemClickListener.onItemClick(position);
            }
        });
        holder.itemView.setOnClickListener(v -> {
            if(onItemClickListener!=null){
                selectPosition=position;
                notifyDataSetChanged();
                onItemClickListener.onItemClick(position);
            }
        });
        if(selectPosition==position){
            holder.llRoot.setBackgroundResource(R.mipmap.border_gift);
        }else{
            holder.llRoot.setBackgroundColor(0xff000000);
        }
//        if (data.get(position % data.size()).isSelect) {
//            holder.img.setVisibility(View.VISIBLE);
//        }else {
//            holder.img.setVisibility(View.GONE);
//        }

//        View viewAtCenter = recyclerView.findViewAtCenter();
    }

    @Override
    public int getItemCount() {
        if(mNeedLoop){
            return Integer.MAX_VALUE;
        } else {
            return data==null?0:data.size();
        }
    }

    public static class VH extends RecyclerView.ViewHolder {

        TextView tv, tvPrice;
        ImageView iv;
        LinearLayout llRoot;

        public VH(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.item_img);
            tv = (TextView) itemView.findViewById(R.id.tv);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            llRoot=itemView.findViewById(R.id.ll_root);
        }
    }
}
