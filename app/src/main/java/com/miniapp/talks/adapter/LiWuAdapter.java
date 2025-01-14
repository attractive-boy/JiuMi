package com.miniapp.talks.adapter;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.GiftListBean;

import java.util.ArrayList;

public class LiWuAdapter extends BaseQuickAdapter<GiftListBean.DataBean.GiftsBean, BaseViewHolder> {
//    private Context context;
//    private RecyclerView recyclerView;
//    private List<GiftListBean.DataBean.GiftsBean> data;

    public LiWuAdapter() {
        super(R.layout.item_g, new ArrayList<>());
    }

//    public List<GiftListBean.DataBean.GiftsBean> getData() {
//        return data;
//    }

//    public LiWuAdapter(Context context, RecyclerView recyclerView, List<GiftListBean.DataBean.GiftsBean> data){
//        this.context = context;
//        this.recyclerView = recyclerView;
//        this.data = data;
//    }

//    @Override
//    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
//        VH h = null;
//        h = new VH(LayoutInflater.from(context)
//                .inflate(R.layout.item_h, parent, false));
//        return h;
//    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GiftListBean.DataBean.GiftsBean item) {
//        helper.getView(R.id.shuliang).setVisibility(View.GONE);
        helper.setText(R.id.tv, item.getName());
        helper.setText(R.id.tvPrice, item.getPrice_004());
        GlideArms.with(mContext)
                .load(item.getImg())
//                .circleCrop()
                .into((ImageView) helper.getView(R.id.item_img));

        if (item.getIs_check()==1){
            helper.getView(R.id.beijing).setBackgroundResource(R.mipmap.room_gift_xz);
        }else {
            helper.getView(R.id.beijing).setBackgroundResource(0);
        }
    }

//    @Override
//    public void onBindViewHolder(VH holder, int position) {
////        LogUtils.debugInfo("====刷新列表" + data.get(position % data.size()).getName());
//        holder.tv.setText(data.get(position).getName());
//        holder.tvPrice.setText(String.valueOf(data.get(position).getPrice()));
//
//        GlideArms.with(context)
//                .load(data.get(position).getImg())
////                .circleCrop()
//                .into(holder.iv);
//
//
//    }

//    @Override
//    public int getItemCount() {
//        return data.size();
//    }

//    public static class VH extends RecyclerView.ViewHolder {
//
//        TextView tv, tvPrice;
//        ImageView iv;
//
//        public VH(View itemView) {
//            super(itemView);
//            iv = (ImageView) itemView.findViewById(R.id.item_img);
//            tv = (TextView) itemView.findViewById(R.id.tv);
//            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
//        }
//    }

}
