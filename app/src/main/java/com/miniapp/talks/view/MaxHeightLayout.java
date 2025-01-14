package com.miniapp.talks.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.jess.arms.base.BaseApplication;
import com.miniapp.talks.R;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.zhy.autolayout.utils.ScreenUtils;


/**
 * 先判断是否设定了mMaxHeight，如果设定了mMaxHeight，则直接使用mMaxHeight的值，
 * 如果没有设定mMaxHeight，则判断是否设定了mMaxRatio，如果设定了mMaxRatio的值 则使用此值与屏幕高度的乘积作为最高高度
 *
 */

public class MaxHeightLayout extends FrameLayout {

    private static final float DEFAULT_MAX_RATIO = 0.6f;

    private static final float DEFAULT_MAX_HEIGHT = 0f;

    private float mMaxRatio = DEFAULT_MAX_RATIO;// 优先级高

    private float mMaxHeight = DEFAULT_MAX_HEIGHT;// 优先级低


    public MaxHeightLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public MaxHeightLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaxHeightLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MaxHeightLayout);

        final int count = a.getIndexCount();

        for (int i = 0; i < count; ++i) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.MaxHeightLayout_mhv_HeightRatio) {
                mMaxRatio = a.getFloat(attr, DEFAULT_MAX_RATIO);
            } else if (attr == R.styleable.MaxHeightLayout_mhv_HeightDimen) {
                mMaxHeight = a.getDimension(attr, DEFAULT_MAX_HEIGHT);
            }
        }

        a.recycle();

        if (mMaxHeight <= 0) {
            mMaxHeight = mMaxRatio * (float) QMUIDisplayHelper.getScreenHeight(BaseApplication.mApplication);
        } else {
            mMaxHeight = Math.min(mMaxHeight, mMaxRatio * (float) QMUIDisplayHelper.getScreenHeight(BaseApplication.mApplication));
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY) {
            heightSize = heightSize <= mMaxHeight ? heightSize : (int) mMaxHeight;
        }

        if (heightMode == MeasureSpec.UNSPECIFIED) {
            heightSize = heightSize <= mMaxHeight ? heightSize : (int) mMaxHeight;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = heightSize <= mMaxHeight ? heightSize : (int) mMaxHeight;
        }

        int maxHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode);

        super.onMeasure(widthMeasureSpec, maxHeightMeasureSpec);
    }
}
