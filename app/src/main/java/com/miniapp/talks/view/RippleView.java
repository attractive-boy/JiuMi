package com.miniapp.talks.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;
import com.miniapp.talks.bean.CircleBean;
import com.miniapp.talks.bean.CircleBeanEvaluator;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RippleView extends View {

    private Context mContext;

    // 画笔对象
    private Paint mPaint;

    // View宽
    private float mWidth;

    // View高
    private float mHeight;

    // 声波的圆圈集合
    private List<CircleBean> mRipples;

    private int sqrtNumber;

    // 圆圈扩散的速度
    private int mSpeed;

    // 圆圈之间的密度,即间距
//    private int mDensity;

    // 圆圈的颜色
    private int mColor;

    // 圆圈是否为填充模式
    private boolean mIsFill = true;

    // 圆圈是否为渐变模式
    private boolean mIsAlpha = true;

    private int mId = 0;

    public RippleView(Context context) {
        this(context, null);
    }

    public RippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取用户配置属性
        TypedArray tya = context.obtainStyledAttributes(attrs, R.styleable.mRippleView);
        mColor = tya.getColor(R.styleable.mRippleView_cColor, Color.WHITE);
        mSpeed = tya.getInt(R.styleable.mRippleView_cSpeed, 1);
//        mDensity = tya.getInt(R.styleable.mRippleView_cDensity, 10);
        mIsFill = tya.getBoolean(R.styleable.mRippleView_cIsFill, false);
        mIsAlpha = tya.getBoolean(R.styleable.mRippleView_cIsAlpha, false);
        tya.recycle();

        init();
    }

    private void init() {
        mContext = getContext();

        // 设置画笔样式
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(QMUIDisplayHelper.dp2px(mContext, 1));
        if (mIsFill) {
            mPaint.setStyle(Paint.Style.FILL);
        } else {
            mPaint.setStyle(Paint.Style.STROKE);
        }
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);

        // 添加第一个圆圈
        mRipples = new ArrayList<>();
//        Circle c = new Circle(0, 255);
//        mRipples.add(c);

//        mDensity = QMUIDisplayHelper.dp2px(mContext, mDensity);

        // 设置View的圆为半透明
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 内切正方形
        drawInCircle(canvas);

        // 外切正方形
        // drawOutCircle(canvas);
    }

    public void setColor(int color){
        mColor = color;
    }

    public void addCircle(float maxWidth){
//        LogUtils.debugInfo("viewWidth====",mWidth+"");
        int limitW = QMUIDisplayHelper.dpToPx(10);
        if(maxWidth<mWidth/2-limitW){
            Random random = new Random();
            int tem = random.nextInt(5)+1;
            maxWidth = mWidth/2-limitW + QMUIDisplayHelper.dpToPx(tem);
            LogUtils.debugInfo("小于半径了=====");
        }
        if(maxWidth>=mWidth/2){
            maxWidth = mWidth/2-10;
        }
        if(mId>=999999999){
            mId = 0;
        }
        mId++;
        CircleBean c = new CircleBean(String.valueOf(mId),0, 255, maxWidth);
        mRipples.add(c);
        invalidate();
    }

    /**
     * 圆到宽度
     *
     * @param canvas
     */
    private void drawInCircle(Canvas canvas) {

//        canvas.restore();
//        canvas.save();

        // 处理每个圆的宽度和透明度
        for (int i = 0; i < mRipples.size(); i++) {
            CircleBean c = mRipples.get(i);

            if(!c.isStartAnimate){//第一次绘制

//                LogUtils.debugInfo("第一次绘制======");

                c.isStartAnimate = true;

                drawCircle(canvas,c,i);

                startViewAnimation(c);
            } else {
                drawCircle(canvas,c,i);
            }

//            mPaint.setAlpha((int) c.alpha);// （透明）0~255（不透明）
//            canvas.drawCircle(mWidth / 2, mHeight / 2, c.width - mPaint.getStrokeWidth(), mPaint);
//
//            // 当圆超出View的宽度后删除
//            if (c.width > mWidth / 2) {
//                mRipples.remove(i);
//            } else {
//                // 计算不透明的数值，如果不加上double的话，255除以一个任意比它大的数都将是0
//                if (mIsAlpha) {
//                    double alpha = 255 - c.width * (255 / ((double) mWidth / 2));
//                    c.alpha = (int) alpha;
//                }
//                // 修改这个值控制速度
//                c.width += mSpeed;
//            }
        }


//        // 里面添加圆
//        if (mRipples.size() > 0) {
//            // 控制第二个圆出来的间距
//            if (mRipples.get(mRipples.size() - 1).width > QMUIDisplayHelper.dp2px(mContext, mDensity)) {
//                mRipples.add(new Circle(0, 255));
//            }
//        }

//
//        invalidate();
//
//        canvas.restore();
    }

    //画圆圈
    private void drawCircle(Canvas canvas,CircleBean circleBean,int positioin){
        Paint paint = new Paint();
        paint.setColor(mColor);
        paint.setStrokeWidth(QMUIDisplayHelper.dp2px(mContext, 1));
//        if (mIsFill) {
            paint.setStyle(Paint.Style.FILL);
//        } else {
//            paint.setStyle(Paint.Style.STROKE);
//        }
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paint.setAlpha((int) circleBean.alpha);// （透明）0~255（不透明）
        canvas.drawCircle(mWidth / 2, mHeight / 2, circleBean.width - paint.getStrokeWidth(), paint);

//        // 当圆超出View的宽度后删除
//        if (circleBean.width > mWidth / 2) {
//            mRipples.remove(positioin);
//        } else {
//            // 计算不透明的数值，如果不加上double的话，255除以一个任意比它大的数都将是0
//            if (mIsAlpha) {
//                double alpha = 255 - circleBean.width * (255 / ((double) mWidth / 2));
//                circleBean.alpha = (int) alpha;
//            }
//            // 修改这个值控制速度
//            circleBean.width += mSpeed;
//        }
    }

    private void startViewAnimation(CircleBean c) {
        CircleBean startBean = c;
        CircleBean endBean = new CircleBean(c.id,startBean.maxWidth, 0, startBean.maxWidth);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CircleBeanEvaluator(),startBean,endBean);
        valueAnimator.setInterpolator(new GuangQuanInterpolator());
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                CircleBean curr = (CircleBean) valueAnimator.getAnimatedValue();

//                LogUtils.debugInfo("动画进度====",curr.width+"");

                for(CircleBean circleBean:mRipples){
                    if(circleBean.id.equals(curr.id)){
                        circleBean.setAlpha(curr.alpha);
                        circleBean.setWidth(curr.width);
                        break;
                    }
                }

                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                LogUtils.debugInfo("动画完了=====mRipple长度====-------"+mRipples.size());
                for(int i = 0;i<mRipples.size();i++){
                    CircleBean circleBean = mRipples.get(i);
                    if(circleBean.id.equals(startBean.id)){
                        mRipples.remove(i);
                        break;
                    }
                }
            }
        });
        valueAnimator.start();
    }


    /**
     * 圆到对角线
     *
     */
//    private void drawOutCircle(Canvas canvas) {
//        canvas.save();
//
//        // 使用勾股定律求得一个外切正方形中心点离角的距离
//        sqrtNumber = (int) (Math.sqrt(mWidth * mWidth + mHeight * mHeight) / 2);
//
//        // 变大
//        for (int i = 0; i < mRipples.size(); i++) {
//
//            // 启动圆圈
//            Circle c = mRipples.get(i);
//            mPaint.setAlpha(c.alpha);// （透明）0~255（不透明）
//            canvas.drawCircle(mWidth / 2, mHeight / 2, c.width - mPaint.getStrokeWidth(), mPaint);
//
//            // 当圆超出对角线后删掉
//            if (c.width > sqrtNumber) {
//                mRipples.remove(i);
//            } else {
//                // 计算不透明的度数
//                double degree = 255 - c.width * (255 / (double) sqrtNumber);
//                c.alpha = (int) degree;
//                c.width += 1;
//            }
//        }
//
//        // 里面添加圆
//        if (mRipples.size() > 0) {
//            // 控制第二个圆出来的间距
//            if (mRipples.get(mRipples.size() - 1).width == 50) {
//                mRipples.add(new Circle(0, 255));
//            }
//        }
//        invalidate();
//        canvas.restore();
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int myWidthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int myWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int myHeightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int myHeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        // 获取宽度
        if (myWidthSpecMode == MeasureSpec.EXACTLY) {
            // match_parent
            mWidth = myWidthSpecSize;
        } else {
            // wrap_content
            mWidth = QMUIDisplayHelper.dp2px(mContext, 120);
        }

        // 获取高度
        if (myHeightSpecMode == MeasureSpec.EXACTLY) {
            mHeight = myHeightSpecSize;
        } else {
            // wrap_content
            mHeight = QMUIDisplayHelper.dp2px(mContext, 120);
        }

        // 设置该view的宽高
        setMeasuredDimension((int) mWidth, (int) mHeight);
    }

}
