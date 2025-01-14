package com.miniapp.talks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.RecommendedDynamicBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.miniapp.talks.app.Api.APP_DOMAIN;

public class DyImageAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<String> datalist;

    public DyImageAdapter(Context context, ArrayList<String> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    public interface OnItemClick {
        void itemClick(int pos);
    }

    public OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dy_iv, viewGroup, false);
        OneIvHolder oneIvHolder = new OneIvHolder(view);
        return oneIvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        OneIvHolder oneIvHolder = (OneIvHolder) viewHolder;
        if (datalist.size() == 1) {
            oneIvHolder.soiIv.setVisibility(View.GONE);
            ArmsUtils.obtainAppComponentFromContext(context)
                    .imageLoader()
                    .loadImage(context, ImageConfigImpl
                            .builder()
                            .url(datalist.get(i))
                            .placeholder(R.mipmap.no_tu)
                            .imageView(oneIvHolder.soiIv)
                            .errorPic(R.mipmap.no_tu)
                            .build());
        }
//        else {
//            ArmsUtils.obtainAppComponentFromContext(context)
//                    .imageLoader()
//                    .loadImage(context, ImageConfigImpl
//                            .builder()
//                            .url(datalist.get(i))
//                            .placeholder(R.mipmap.touxiang_ziliao_boy)
//                            .imageView(oneIvHolder.oneImage)
//                            .errorPic(R.mipmap.touxiang_ziliao_boy)
//                            .build());
//        }


    }

    @Override
    public int getItemCount() {
        return datalist == null ? 0 : datalist.size();
    }

    static class OneIvHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.soi_iv)
        ImageView soiIv;
//        @BindView(R.id.dy_oneimage_iv)
//        ImageView oneImage;

        OneIvHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
