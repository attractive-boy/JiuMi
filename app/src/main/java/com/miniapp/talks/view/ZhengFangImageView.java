package com.miniapp.talks.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ZhengFangImageView extends android.support.v7.widget.AppCompatImageView {
    public ZhengFangImageView(Context context) {
        super(context);
    }

    public ZhengFangImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZhengFangImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
