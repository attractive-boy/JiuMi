package com.miniapp.talks.view.voiceripple;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaRecorder;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.jess.arms.utils.LogUtils;
import com.miniapp.talks.R;

import java.io.IOException;

/**
 * Created by jihyokkim on 2017. 8. 24..
 * https://github.com/wotomas/VoiceRipple
 */

public class VoiceRippleView extends View implements TimerCircleRippleRenderer.TimerRendererListener {
  private static final String TAG = "VoiceRippleView";
  private static final double AMPLITUDE_REFERENCE = 32767.0;
  private static int MIN_RADIUS;
  private static int MIN_ICON_SIZE;
  private static int MIN_FIRST_RIPPLE_RADIUS;
  private static final int INVALID_PARAMETER = -1;

  private int buttonRadius;
  private int rippleRadius;
  private int backgroundRadius;
  private int iconSize;
  private boolean isRecording;
  private boolean isPrepared;

  private int rippleDecayRate = INVALID_PARAMETER;
  private int thresholdRate = INVALID_PARAMETER;
  private double backgroundRippleRatio = INVALID_PARAMETER;
  private int audioSource = INVALID_PARAMETER;
  private int outputFormat = INVALID_PARAMETER;
  private int audioEncoder = INVALID_PARAMETER;

//  private MediaRecorder recorder;
//  private Drawable recordIcon;
//  private Drawable recordingIcon;
//  private OnClickListener listener;
  private Handler handler;  // Handler for updating ripple effect
//  private RecordingListener recordingListener;
  private Renderer currentRenderer;
  private int currentRecordedTime = 0;

  OnGetVoiceDbListener mOnGetVoiceDbListener;

  int mViewWidth;

  int mMaxDB = 200;//设置声音的最大量

  float mScale = 0;//viewWidth减去minWidth比上声音的最大值等于当前距离比上当前db，

  int mRippleColor;

  public void setRenderer(Renderer currentRenderer) {
    this.currentRenderer = currentRenderer;
    if (currentRenderer instanceof TimerCircleRippleRenderer) {
      ((TimerCircleRippleRenderer) currentRenderer).setTimerRendererListener(this);
    }
    invalidate();
  }

  public void setVoiceDbListener(OnGetVoiceDbListener listener){
    this.mOnGetVoiceDbListener = listener;
  }

  public VoiceRippleView(Context context) {
    super(context);
    init(context, null);
  }

  public VoiceRippleView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public VoiceRippleView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private int minFirstRadius;

  private void init(Context context, AttributeSet attrs) {
    MIN_RADIUS = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
    MIN_ICON_SIZE = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
    MIN_FIRST_RIPPLE_RADIUS = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
    TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.VoiceRippleView, 0, 0);
    try {
      rippleRadius = a.getInt(R.styleable.VoiceRippleView_VoiceRippleView_rippleRadius, MIN_RADIUS);
      iconSize = a.getInt(R.styleable.VoiceRippleView_VoiceRippleView_iconSize, MIN_ICON_SIZE);
    } finally {
      a.recycle();
    }

    backgroundRadius = rippleRadius;
    buttonRadius = backgroundRadius;
    minFirstRadius =  MIN_FIRST_RIPPLE_RADIUS;
    handler = new Handler();

    this.setClickable(true);
    this.setEnabled(true);
    this.setFocusable(true);
    this.setFocusableInTouchMode(true);

    setBackgroundRippleRatio(1.2);
    setRippleDecayRate(Rate.LOW);
    setRippleSampleRate(Rate.LOW);
  }

  public void setMediaRecorder(MediaRecorder recorder) {
//    this.recorder = recorder;
  }

  public void onStop() throws IllegalStateException {
//    if (isPrepared && recorder != null) {
//      recorder.stop();
//    }
  }

  public void onDestroy() {
//    if (isPrepared && recorder != null) {
//      recorder.release();
//    }
  }

//  @Override
//  public boolean dispatchTouchEvent(MotionEvent event) {
//    if(event.getAction() == MotionEvent.ACTION_UP) {
//      if(listener != null) listener.onClick(this);
//    }
//    return super.dispatchTouchEvent(event);
//  }


//  @Override
//  public boolean dispatchKeyEvent(KeyEvent event) {
//    if(event.getAction() == KeyEvent.ACTION_UP && (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER || event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
//      if(listener != null) listener.onClick(this);
//    }
//    return super.dispatchKeyEvent(event);
//  }
//
//  @Override
//  public void setOnClickListener(OnClickListener listener) {
//    this.listener = listener;
//  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int viewWidthHalf = this.getMeasuredWidth() / 2;
    int viewHeightHalf = this.getMeasuredHeight() / 2;

    currentRenderer.render(canvas, viewWidthHalf, viewHeightHalf, buttonRadius, rippleRadius, backgroundRadius);

//    if (isRecording) {
//      recordingIcon.setBounds(viewWidthHalf - iconSize, viewHeightHalf - iconSize, viewWidthHalf + iconSize, viewHeightHalf + iconSize);
//      recordingIcon.draw(canvas);
//    } else {
//      recordIcon.setBounds(viewWidthHalf - iconSize, viewHeightHalf - iconSize, viewWidthHalf + iconSize, viewHeightHalf + iconSize);
//      recordIcon.draw(canvas);
//    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int minw =  getPaddingLeft() + getPaddingRight();
    int w = resolveSizeAndState(minw, widthMeasureSpec, 0);

    int minh =  getPaddingBottom() + getPaddingTop();
    int h = resolveSizeAndState(minh, heightMeasureSpec, 0);

    setMeasuredDimension(w, h);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mViewWidth = w;
    mScale = (mViewWidth-MIN_RADIUS)*1f/mMaxDB;
    LogUtils.debugInfo("声音mScale==="+mScale);
  }

  public boolean isRecording() {
    return isRecording;
  }

  public void setOutputFile(String absolutePath) {
//    recorder.setOutputFile(absolutePath);
  }

  public void setAudioSource(int audioSource) {
    this.audioSource = audioSource;
  }

  public void setOutputFormat(int outputFormat) {
    this.outputFormat = outputFormat;
  }

  public void setAudioEncoder(int audioEncoder) {
    this.audioEncoder = audioEncoder;
  }

  public void setIconSize(int dpSize) {
    this.iconSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize / 2, getResources().getDisplayMetrics());
    invalidate();
  }

  public void setRippleRadius(int rdiu){
    rippleRadius = rdiu;
  }

  public void setRippleColor(int color) {
    mRippleColor = color;
    currentRenderer.changeColor(color);
    invalidate();
  }
  public int getRippleColor(){
    return mRippleColor;
  }

  public void setTranslateColor(){
    currentRenderer.changeColor(getResources().getColor(R.color.translant));
    invalidate();
  }

  public void setRippleSampleRate(Rate rate) {
    switch (rate) {
      case LOW:
        this.thresholdRate = 5;
        break;
      case MEDIUM:
        this.thresholdRate = 10;
        break;
      case HIGH:
        this.thresholdRate = 20;
        break;
    }
    invalidate();
  }

  public void setRippleDecayRate(Rate rate) {
    switch (rate) {
      case LOW:
        this.rippleDecayRate = 20;
        break;
      case MEDIUM:
        this.rippleDecayRate = 10;
        break;
      case HIGH:
        this.rippleDecayRate = 5;
        break;
    }
    invalidate();
  }

  public void reset() {
    rippleRadius = 0;
    backgroundRadius = 0;
    if (currentRenderer instanceof TimerCircleRippleRenderer) {
      ((TimerCircleRippleRenderer) currentRenderer).setCurrentTimeMilliseconds(0);
    }
    stopRecording();
  }


  public void setBackgroundRippleRatio(double ratio) {
    this.backgroundRippleRatio = ratio;
    minFirstRadius = (int) (MIN_FIRST_RIPPLE_RADIUS + (MIN_FIRST_RIPPLE_RADIUS * backgroundRippleRatio));
    invalidate();
  }

  /**
   * Calculating decibels from amplitude requires the following: power_db = 20 * log10(amp / amp_ref);
   * 0db is the maximum, and everything else is negative
   * @param amplitude
   */
  private void drop(int amplitude) {
    int powerDb = (int)(20.0 * Math.log10((double) amplitude / AMPLITUDE_REFERENCE));

    // clip if change is below threshold
     int THRESHOLD = (-1 * powerDb) / thresholdRate;

    LogUtils.debugInfo("THRESHOLD====",THRESHOLD+"==");

//    THRESHOLD = (int) (THRESHOLD*mScale);

    LogUtils.debugInfo("THRESHOLD==after==",THRESHOLD+"==");

    if (THRESHOLD >= 0) {
      if (rippleRadius - THRESHOLD >= powerDb + MIN_RADIUS + minFirstRadius || powerDb + MIN_RADIUS + minFirstRadius >= rippleRadius + THRESHOLD) {
        rippleRadius = powerDb + MIN_RADIUS + minFirstRadius;
        backgroundRadius = (int) (rippleRadius * backgroundRippleRatio);
      } else {
        // if decreasing velocity reached 0, it should simply match with ripple radius
        if (((backgroundRadius - rippleRadius) / rippleDecayRate) == 0) {
          backgroundRadius = rippleRadius;
          rippleRadius = buttonRadius;
        } else {
          backgroundRadius = backgroundRadius - ((backgroundRadius - rippleRadius) / rippleDecayRate);
          rippleRadius = rippleRadius - ((rippleRadius - buttonRadius) / rippleDecayRate);
        }
      }

      invalidate();
    }
  }

  @Override
  public void stopRecording() {
    isRecording = false;
    if (isPrepared) {
//      recorder.stop();
//      recorder.reset();
      isPrepared = false;
      handler.removeCallbacks(updateRipple);
      currentRecordedTime = 0;
      invalidate();
//      if (recordingListener != null) {
//        recordingListener.onRecordingStopped();
//      }
    }
  }

  @Override
  public void startRecording() {
    checkValidState();
    try {
      prepareRecord();
      handler.removeCallbacks(updateRipple);
//      recorder.start();
      isRecording = true;
      isPrepared = true;
      handler.post(updateRipple);
      invalidate();
//      if (recordingListener != null) {
//        recordingListener.onRecordingStarted();
//      }
    } catch (Exception e) {
      Log.e(TAG, "startRecording(): ", e);
    }
  }

  private void checkValidState() {
    if (thresholdRate == INVALID_PARAMETER || backgroundRippleRatio == INVALID_PARAMETER || rippleDecayRate == INVALID_PARAMETER) {
      throw new IllegalStateException("Set rippleSampleRate, backgroundRippleRatio and rippleDecayRate before starting to record!");
    }

//    if (audioSource == INVALID_PARAMETER || outputFormat == INVALID_PARAMETER || audioEncoder == INVALID_PARAMETER) {
//      throw new IllegalStateException("You have to set audioSource, outputFormat, and audioEncoder before starting to record!");
//    }
  }


  private void prepareRecord() throws IOException {
//    recorder.setAudioSource(audioSource);
//    recorder.setOutputFormat(outputFormat);
//    recorder.setAudioEncoder(audioEncoder);
//    recorder.prepare();
  }

  private Runnable updateRipple = new Runnable() {
    @Override
    public void run() {
      if (isRecording) {
        int voiceDb = 0;
        if(mOnGetVoiceDbListener != null){
          voiceDb = mOnGetVoiceDbListener.getVoiceDb();
//          voiceDb = voiceDb/3;//太敏感了，除以一个值
//          if(voiceDb<10){
//            voiceDb = 1;
//          }
          Log.e("声音大小====", voiceDb + "");
        }
//        if(voiceDb<=1){
//          setTranslateColor();//没有声音时设置透明色
//        } else {
//          setRippleColor(mRippleColor);
//        }

//        drop(recorder.getMaxAmplitude());
        drop(voiceDb);
        currentRecordedTime = currentRecordedTime + 50;
        if (currentRenderer instanceof TimerCircleRippleRenderer) {
          ((TimerCircleRippleRenderer)currentRenderer).setCurrentTimeMilliseconds(currentRecordedTime);
        }
        handler.postDelayed(this, 200);  // updates the visualizer every 50 milliseconds
      }
    }
  };

//  public void setRecordDrawable(Drawable recordIcon, Drawable recordingIcon) {
//    this.recordIcon = recordIcon;
//    this.recordingIcon = recordingIcon;
//
//    invalidate();
//  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    if(mOnGetVoiceDbListener != null){
      mOnGetVoiceDbListener = null;
    }
    isRecording = false;
    if (isPrepared) {
//      recorder.stop();
//      recorder.reset();
      isPrepared = false;
      handler.removeCallbacks(updateRipple);
      currentRecordedTime = 0;
//      if (recordingListener != null) {
//        recordingListener.onRecordingStopped();
//      }
    }
  }

  /**
   * 声音监听，updateRipple会50毫秒调取一次
   */
  public interface OnGetVoiceDbListener{
    int getVoiceDb();
  }
}
