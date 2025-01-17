package com.miniapp.talks.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.miniapp.talks.R;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

public class VoiceView extends View {

    private static final String TAG = VoiceView.class.getName();

    private static final int STATE_NORMAL = 0;
    private static final int STATE_PRESSED = 1;
    private static final int STATE_RECORDING = 2;

    //    private Bitmap mNormalBitmap;
//    private Bitmap mPressedBitmap;
//    private Bitmap mRecordingBitmap;
    private Paint mPaint;
    private AnimatorSet mAnimatorSet = new AnimatorSet();
//    private OnRecordListener mOnRecordListener;

    private int mState = STATE_NORMAL;
    private boolean mIsRecording = false;
    private float mMinRadius;
    private float mMaxRadius;
    private float mCurrentRadius;

    OnGetVoiceDbListener mOnGetVoiceDbListener;

    private Handler handler;

    int mRippleColor;

    boolean mIsBig;

    public void setVoiceDbListener(OnGetVoiceDbListener listener) {
        this.mOnGetVoiceDbListener = listener;
    }

    public VoiceView(Context context) {
        super(context);
        init();
    }

    public VoiceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
//        mNormalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vs_micbtn_off);
//        mPressedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vs_micbtn_pressed);
//        mRecordingBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vs_micbtn_on);

        handler = new Handler();

        mRippleColor = getResources().getColor(R.color.translant);

        mPaint = new Paint();

        mPaint.setAntiAlias(true);
//        mPaint.setColor(Color.argb(255, 219, 219, 219));
        mPaint.setColor(mRippleColor);

        mMinRadius = QMUIDisplayHelper.dpToPx(25);
        mCurrentRadius = mMinRadius;
    }

    public void setMinRadius(int minRadius,boolean isBig){
        this.mMinRadius = minRadius;
        mCurrentRadius = mMinRadius;
        this.mIsBig = isBig;
    }

    public void setRippleColor(int color) {
        mRippleColor = color;
        mPaint.setColor((mRippleColor & 0x00FFFFFF) | 0x40000000);
//        invalidate();
    }
    public int getRippleColor(){
        return mRippleColor;
    }

    public void setTranslateColor(){
        mRippleColor = getResources().getColor(R.color.translant);
        mPaint.setColor(mRippleColor);
//        currentRenderer.changeColor(getResources().getColor(R.color.translant));
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMaxRadius = Math.min(w, h) / 2;
        Log.d(TAG, "MaxRadius: " + mMaxRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();

        int height = canvas.getHeight();

        if (mCurrentRadius > mMinRadius) {
            canvas.drawCircle(width / 2, height / 2, mCurrentRadius, mPaint);
        }

//        switch (mState){
//            case STATE_NORMAL:
//                canvas.drawBitmap(mNormalBitmap, width / 2 - mMinRadius,  height / 2 - mMinRadius, mPaint);
//                break;
//            case STATE_PRESSED:
//                canvas.drawBitmap(mPressedBitmap, width / 2 - mMinRadius,  height / 2 - mMinRadius, mPaint);
//                break;
//            case STATE_RECORDING:
//                canvas.drawBitmap(mRecordingBitmap, width / 2 - mMinRadius,  height / 2 - mMinRadius, mPaint);
//                break;
//        }
    }

    public void startRecording(){
        handler.removeCallbacks(updateRipple);
        handler.postDelayed(updateRipple, 100);
    }

    public void stopRecording(){
        handler.removeCallbacks(updateRipple);
    }

    private Runnable updateRipple = new Runnable() {
        @Override
        public void run() {
            int voiceDb = 0;
            if (mOnGetVoiceDbListener != null) {
                voiceDb = mOnGetVoiceDbListener.getVoiceDb();
//                Log.e("声音大小====", voiceDb + "");
            }
//            float radius = (float) Math.log10(Math.max(1, voiceDb - 500)) * QMUIDisplayHelper.dpToPx(20);
            float radius = (float) Math.log10(Math.max(1, voiceDb+(mIsBig?400:80))) * QMUIDisplayHelper.dpToPx(20);
            Log.e("声音大小====", voiceDb + "：半径="+radius);

            animateRadius(radius);
            handler.postDelayed(this, 100);  // updates the visualizer every 50 milliseconds
        }
    };

    public void animateRadius(float radius) {
        Log.e("mCurrentRadius====", "===="+mCurrentRadius);
        if (radius <= mCurrentRadius) {
            return;
        }
        if (radius > mMaxRadius) {
            radius = mMaxRadius;
        } else if (radius < mMinRadius) {
            radius = mMinRadius;
        }
        if (radius == mCurrentRadius) {
            return;
        }
        if (mAnimatorSet.isRunning()) {
            mAnimatorSet.cancel();
        }
        mAnimatorSet.playSequentially(
                ObjectAnimator.ofFloat(this, "CurrentRadius", getCurrentRadius(), radius).setDuration(350),
                ObjectAnimator.ofFloat(this, "CurrentRadius", radius, mMinRadius).setDuration(600),
                ObjectAnimator.ofFloat(this, "alpha", 1, 0).setDuration(350)
        );
        mAnimatorSet.setInterpolator(new DecelerateInterpolator());
        mAnimatorSet.start();
//        Log.e("动画走了====", "zoule");
    }

    public float getCurrentRadius() {
        return mCurrentRadius;
    }

    public void setCurrentRadius(float currentRadius) {
        mCurrentRadius = currentRadius;
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mOnGetVoiceDbListener != null){
            mOnGetVoiceDbListener = null;
        }
            handler.removeCallbacks(updateRipple);
    }

    /**
     * 声音监听，updateRipple会50毫秒调取一次
     */
    public interface OnGetVoiceDbListener {
        int getVoiceDb();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getActionMasked()){
//            case MotionEvent.ACTION_DOWN:
//                Log.d(TAG, "ACTION_DOWN");
//                mState = STATE_PRESSED;
//                invalidate();
//                return true;
//            case MotionEvent.ACTION_UP:
//                Log.d(TAG, "ACTION_UP");
//                if(mIsRecording){
//                    mState = STATE_NORMAL;
//                    if(mOnRecordListener != null){
//                        mOnRecordListener.onRecordFinish();
//                    }
//                }else{
//                    mState = STATE_RECORDING;
//                    if(mOnRecordListener != null){
//                        mOnRecordListener.onRecordStart();
//                    }
//                }
//                mIsRecording = !mIsRecording;
//                invalidate();
//                return true;
//            default:
//                return super.onTouchEvent(event);
//        }
//    }

//    public void setOnRecordListener(OnRecordListener onRecordListener) {
//        mOnRecordListener = onRecordListener;
//    }
//
//    public static interface OnRecordListener{
//        public void onRecordStart();
//        public void onRecordFinish();
//    }
}
