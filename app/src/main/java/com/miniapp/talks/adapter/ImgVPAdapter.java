package com.miniapp.talks.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.miniapp.talks.R;

import java.util.List;


public class ImgVPAdapter extends PagerAdapter {
    private Context context;
    private List<String> paths;

    public ImgVPAdapter(Context context, List<String> paths) {
        this.context = context;
        this.paths = paths;
    }

    @Override
    public int getCount() {
        return paths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView iv_img = (ImageView) LayoutInflater.from(context).inflate(R.layout.item_img_pv, null);
      //  iv_img.setScaleType(ImageView.ScaleType.CENTER);
        GlideArms.with(context).load( paths.get(position)).error(R.drawable.shibai).into(iv_img);
        iv_img.setScaleType(ImageView.ScaleType.CENTER);
        iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allClickListener != null) {
                    allClickListener.allclick(position);
                }
            }
        });
        container.addView(iv_img);
        return iv_img;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface AllClickListener {
        void allclick(int pos);
    }

    private AllClickListener allClickListener;

    public void setAllClickListener(AllClickListener allClickListener) {
        this.allClickListener = allClickListener;
    }
}
