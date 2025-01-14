package com.miniapp.talks.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;

/**
 * Created by gavinandre on 17-2-17.
 */
public class CircleProgressImageView extends View {

    private static final String TAG = CircleProgressImageView.class.getSimpleName();

    //表示坐标系中的一块矩形区域
    private RectF mRectF;

    //画笔
    private Paint mPaint;

    //画笔宽度
    private int mCircleStoreWidth = 5;

    //最大进度值
    private int mMaxProcessValue = 100;

    //进度值
    private int mProcessValue;

    private int width;

    private int height;

    //播放器按钮id值
    private int bitmapPlay;
    private int bitmapStop;
    private int bitmapEnd;
    //播放器按钮Bitmap对象
    private Bitmap drawBitmapPlay;
    private Bitmap drawBitmapStop;
    private Bitmap drawBitmapEnd;
    private Context context;

    //标记是否正在播放中
    private boolean isPlay;
    private boolean end = false;

    private boolean pause = false;
    private RectF mtooRectF;
    private RectF mendRectF;
    private RectF textRectF;

    private int mPlayValue = 0;//播放时的总时长
    private long mDrawTime = 0;//画界面需要的时间

    public CircleProgressImageView(Context context) {
        this(context, null);
    }

    public CircleProgressImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.circle_progress_image_attrs);
        bitmapPlay = a.getResourceId(R.styleable.circle_progress_image_attrs_play_image, R.mipmap.stop_button);
        bitmapStop = a.getResourceId(R.styleable.circle_progress_image_attrs_stop_image, R.mipmap.end_button);
        bitmapEnd = a.getResourceId(R.styleable.circle_progress_image_attrs_end_image, R.mipmap.play_button);
        a.recycle();
        drawBitmapPlay = BitmapFactory.decodeResource(context.getResources(), bitmapPlay);
        drawBitmapStop = BitmapFactory.decodeResource(context.getResources(), bitmapStop);
        drawBitmapEnd = BitmapFactory.decodeResource(context.getResources(), bitmapEnd);
        mRectF = new RectF();
        mtooRectF = new RectF();
        mendRectF = new RectF();
        textRectF = new RectF();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = measureWidth(widthMeasureSpec);
        height = measureWidth(heightMeasureSpec);
        mRectF.left = width / 2 - drawBitmapPlay.getWidth() / 2;
        mRectF.top = height / 2 - drawBitmapPlay.getHeight() / 2;
        mRectF.right = width / 2 + drawBitmapPlay.getWidth() / 2;
        mRectF.bottom = height / 2 + drawBitmapPlay.getHeight() / 2;


        textRectF.left = (float) (width / 2 - drawBitmapPlay.getWidth() / 2 * 1.2);
        textRectF.top = (float) (height / 2 - drawBitmapPlay.getHeight() / 2 * 1.2);
        textRectF.right = (float) (width / 2 + drawBitmapPlay.getWidth() / 2 * 1.2);
        textRectF.bottom = (float) (height / 2 + drawBitmapPlay.getHeight() / 2 * 1.2);

        mtooRectF.left = width / 2 - drawBitmapStop.getWidth() / 2;
        mtooRectF.top = height / 2 - drawBitmapStop.getHeight() / 2;
        mtooRectF.right = width / 2 + drawBitmapStop.getWidth() / 2;
        mtooRectF.bottom = height / 2 + drawBitmapStop.getHeight() / 2;

        mendRectF.left = width / 2 - drawBitmapEnd.getWidth() / 2;
        mendRectF.top = height / 2 - drawBitmapEnd.getHeight() / 2;
        mendRectF.right = width / 2 + drawBitmapEnd.getWidth() / 2;
        mendRectF.bottom = height / 2 + drawBitmapEnd.getHeight() / 2;

    }

    public int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(specSize, result);
            }
        }
        return result;
    }

    private void drawNormalRing(Canvas canvas) {
        Paint ringNormalPaint = new Paint(mPaint);
        ringNormalPaint.setStyle(Paint.Style.STROKE);
        ringNormalPaint.setStrokeWidth(8);
        ringNormalPaint.setColor(getResources().getColor(R.color.gray));//圆环默认颜色为灰色

        canvas.drawArc(textRectF, 360, 360, false, ringNormalPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDrawTime = 0;
        long startDrawTime = System.currentTimeMillis();
        canvas.drawColor(Color.TRANSPARENT);
        //画圆
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleStoreWidth);
        mPaint.setColor(ContextCompat.getColor(context, R.color.red));


        drawNormalRing(canvas);
//        Log.d(TAG, ((float) mProcessValue / mMaxProcessValue) * 360 + "");
        float imageLeft = width / 2 - drawBitmapPlay.getWidth() / 2;
        float imageTop = height / 2 - drawBitmapPlay.getHeight() / 2;

        float imageLeftto = width / 2 - drawBitmapStop.getWidth() / 2;
        float imageTopto = height / 2 - drawBitmapStop.getHeight() / 2;

        float imageleftpend = width / 2 - drawBitmapEnd.getWidth() / 3;
        float imageTopend = height / 2 - drawBitmapEnd.getHeight() / 2;

        if (isPlay) {
            canvas.drawArc(textRectF, -90, ((float) mProcessValue / mMaxProcessValue) * 360, false, mPaint);

            canvas.drawBitmap(drawBitmapStop, imageLeftto, imageTopto, mPaint);

        } else {

            if (end) {
                canvas.drawArc(textRectF, 90, ((float) mProcessValue / mMaxProcessValue) * 360, false, mPaint);

                canvas.drawBitmap(drawBitmapEnd, imageleftpend, imageTopend, mPaint);

            } else {
                if (pause) {
                    canvas.drawArc(textRectF, -90, ((float) mProcessValue / mMaxProcessValue) * 360, false, mPaint);
                    canvas.drawBitmap(drawBitmapEnd, imageleftpend, imageTopend, mPaint);
                } else {
                    canvas.drawArc(textRectF, -90, ((float) mProcessValue / mMaxProcessValue) * 360, false, mPaint);
                    canvas.drawBitmap(drawBitmapPlay, imageLeft, imageTop, mPaint);
                }
            }
        }
        long endTime = System.currentTimeMillis();

        long times = endTime-startDrawTime;

        mDrawTime = times;
        LogUtils.debugInfo("画时间===="+mDrawTime);
    }
//    public void changetoPlayStatue(){
//        isPlay = true;
//        invalidate();
//    }

  /*  public void changePalyStatue(){
        pause = true;
        invalidate();
    }*/
  public void setProcessValue(int value){
      mProcessValue = value;
  }

    public void changetoEndStatue(){
        isPlay = false;
        end = true;
        invalidate();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //定时更新界面
                    if (isPlay) {
                        mProcessValue += 150;
                        mProcessValue = mProcessValue + (int) mDrawTime;
                        LogUtils.debugInfo("之前的画时间====="+mDrawTime);
                        if (mProcessValue == mMaxProcessValue) {
                            isPlay = false;
                        }
                        invalidate();
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 150);
                    }
                    if (mProcessValue >= 60000) {
                        mProcessValue = 60000;
                        end = true;
                        getContext().sendBroadcast(new Intent("SendNotify"));
                    }
                    break;
                case 2://播放录音

                    if (isPlay) {
                        mProcessValue -= 150;
//                        Log.e("收到消息================", "mProcessValue" + mProcessValue);
                        if (mProcessValue <= 0) {
                            isPlay = false;
                        }
                        invalidate();
                        Message message = handler.obtainMessage(2);
                        handler.sendMessageDelayed(message, 150);
                    }
                    if (mProcessValue <= 0) {
                        mProcessValue=0;
                        end = true;
                        getContext().sendBroadcast(new Intent("play_recorder_complete"));
                    }
                    break;

            }
            super.handleMessage(msg);
        }
    };

    public void play() {
        isPlay = true;
        end = false;
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 150);
    }

    public void setDuration(int duration) {
        this.mMaxProcessValue = duration;
    }

    public int getDuration() {
        return mProcessValue;
    }

    public void clearDuration() {
        this.mMaxProcessValue = 0;
        this.mProcessValue = 0;
    }

    /**
     * 播放录音
     */
    public void playRecorder(){
        isPlay = true;
        end = false;
        LogUtils.debugInfo("before_processValue"+"："+mProcessValue);
        if(mPlayValue>0){
            mProcessValue = mPlayValue;
            LogUtils.debugInfo("after_processValue"+"："+mProcessValue);
            invalidate();
        }
        Message message = handler.obtainMessage(2);
        handler.sendMessageDelayed(message, 150);
    }

    /**
     * 停止播放录音
     */
    public void stopPlayRecorder(){
        isPlay = false;
        pause = true;
        if(mPlayValue>0){
            mProcessValue = mPlayValue;
            invalidate();
        }
    }

    /**
     * 重置播放录音
     */
    public void resetPlayRecorder(){
        isPlay = false;
        pause = true;
        end =false;
        if(mPlayValue>0){
            mProcessValue = mPlayValue;
            invalidate();
        }
    }

    public void pause() {
        isPlay = false;
        pause = true;
        if(mProcessValue>0){
            mPlayValue = mProcessValue;
        }
        invalidate();
    }

    public void stop() {

        //  end = false;
        pause = false;
        isPlay = false;
        this.mMaxProcessValue = 0;
        this.mProcessValue = 0;

        invalidate();
    }
}
