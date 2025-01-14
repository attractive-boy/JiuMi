package com.miniapp.talks.activity.dynamic;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.kongzue.dialog.v3.TipDialog;
import com.lzx.starrysky.manager.MusicManager;
import com.lzx.starrysky.model.SongInfo;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.miniapp.talks.R;
import com.miniapp.talks.adapter.GridImageAdapter;
import com.miniapp.talks.app.utils.RxUtils;
import com.miniapp.talks.base.MyBaseArmActivity;
import com.miniapp.talks.base.UserManager;
import com.miniapp.talks.bean.BaseBean;
import com.miniapp.talks.bean.FirstEvent;
import com.miniapp.talks.bean.LoginData;
import com.miniapp.talks.bean.RecommendLabelBean;
import com.miniapp.talks.bean.SearchLabelBean;
import com.miniapp.talks.bean.XuanzeLabelBean;
import com.miniapp.talks.di.CommonModule;
import com.miniapp.talks.di.DaggerCommonComponent;
import com.miniapp.talks.di.bCallBack;
import com.miniapp.talks.popup.PuTongWindow;
import com.miniapp.talks.service.CommonModel;
import com.miniapp.talks.utils.BaseUtils;
import com.miniapp.talks.utils.MediaManager;
import com.miniapp.talks.utils.MyUtil;
import com.miniapp.talks.utils.PhotoWindow;
import com.miniapp.talks.utils.SdcardTools;
import com.miniapp.talks.utils.TextNumUtil;
import com.miniapp.talks.utils.TimeUtil;
import com.miniapp.talks.view.CircleProgressImageView;
import com.miniapp.talks.view.FullyGridLayoutManager;
import com.miniapp.talks.view.SearchView;
import com.miniapp.talks.view.ShapeTextView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zlw.main.recorderlib.RecordManager;
import com.zlw.main.recorderlib.recorder.RecordConfig;
import com.zlw.main.recorderlib.recorder.listener.RecordResultListener;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.miniapp.talks.utils.Constant.FABUCHENGGONG;
import static com.miniapp.talks.utils.Constant.XIUGAIGERENZILIAOCHENGGONG;


/**
 * 发布动态页面
 */

public class SocialReleaseActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;

    @BindView(R.id.sor_et)
    EditText sorEt;
    @BindView(R.id.sor_release_rv)
    RecyclerView sorReleaseRv;
    @BindView(R.id.lable_recyc)
    TagFlowLayout lableRecyc;
    @BindView(R.id.social_release_voice)
    ImageView socialReleaseVoice;
    @BindView(R.id.social_release_image)
    ImageView socialReleaseImage;
    //    @BindView(R.id.social_release_ep)
//    ImageView socialReleaseEp;
    @BindView(R.id.social_release_type)
    LinearLayout socialReleaseType;
    @BindView(R.id.dy_voice_back)
    ImageView dyVoiceBack;
    @BindView(R.id.dy_voice_play)
    ImageView dyVoicePlay;
    @BindView(R.id.dy_voice_time)
    TextView dyVoiceTime;
    @BindView(R.id.dy_voice)
    RelativeLayout dyVoice;
    @BindView(R.id.LineScale)
    LinearLayout LineScale;
    @BindView(R.id.dy_close)
    ImageButton dyClose;
    @BindView(R.id.my_label_listview)
    TagFlowLayout myLabelListview;
    @BindView(R.id.sr_text_num)
    TextView srTextNum;
    @BindView(R.id.jubu)
    RelativeLayout jubu;
    @BindView(R.id.fabu)
    LinearLayout fabu;

    public static SocialReleaseActivity socialReleaseActivity;
    public static final int REQUEST_CODE_SELECT = 100;
    @BindView(R.id.balk)
    ImageView balk;
    @BindView(R.id.rightConfirm)
    ShapeTextView rightConfirm;

    private int screenHeight;//屏幕高度
    public String path = "";
    private String imagePath1 = "", imagePath2 = "", imagePath3 = "", imagePath4 = "", imagePath5 = "", imagePath6 = "";
    private MultipartBody.Part audio, img1, img2, img3, img4, img5, img6;


    private GridImageAdapter adapter;//图片的适配器
    private int maxSelectNum = 6;//最多显示的图片数量
    private ArrayList<ImageItem> selImageList = new ArrayList<>();
    private LoginData user;
    private PopupWindow popupWindow;

    //录音
    private static MediaPlayer mediaPlayer;
    private static CircleProgressImageView soundBtn;
    private ImageButton soundCancel, soundComplete;
    private TextView soundTips;
    private static Chronometer soundTime;
    private boolean isPlay;
    private boolean isComplete = false;
    private long time;
    private static CountDownTimer timer;
    private static boolean isPlaying;
    private int duration;

    //标签
    private int imageSize = 0;
    private String labelStr; //已经贴上的标签
    private TextView popuTextOne, popuTextTwo;
    private TagFlowLayout gridViewOne, gridViewTwo, gridViewThree;
    private RelativeLayout popuRelaOne, popuRelaTwo;
    private SearchView mSearchView;
    private PhotoWindow photoWindow;
    private ArrayList<ImageItem> tempList;

    private List<RecommendLabelBean.DataBean.TopBean> topLabelList; //贴上标签数据源
    private List<RecommendLabelBean.DataBean.AllBean> allLabelList; //弹窗所有标签数据源
    private List<XuanzeLabelBean> myLabelList; //已选中标签数据源
    private List<SearchLabelBean.DataBean> searchLabelList; //搜索结果出现的标签数据源

    private PuTongWindow puTongWindow;

    private boolean mIsPlayRecorder;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_social_release;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        socialReleaseActivity = this;
        mediaPlayer = new MediaPlayer();

        topLabelList = new ArrayList<>();
        allLabelList = new ArrayList<>();
        myLabelList = new ArrayList<>();
        searchLabelList = new ArrayList<>();

        RecordManager.getInstance().init(getApplication(), false);
        RecordManager.getInstance().changeFormat(RecordConfig.RecordFormat.MP3);


        user = UserManager.getUser();
        //获得屏幕高度
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();

        getLabel();

        adapter = new GridImageAdapter(this, onAddPicClickListener);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        adapter.setSelectMax(maxSelectNum);
        sorReleaseRv.setLayoutManager(manager);
        sorReleaseRv.setAdapter(adapter);

        TextNumUtil.initTextNum(SocialReleaseActivity.this, sorEt, srTextNum);

        myLabelListview.setOnTagClickListener((view, position, parent) -> {
            myLabelList.remove(position);
            myLabelListview.setAdapter(new TagAdapter<XuanzeLabelBean>(myLabelList) {
                @Override
                public View getView(FlowLayout parent, int position, XuanzeLabelBean o) {
                    TextView tv = (TextView) LayoutInflater.from(SocialReleaseActivity.this)
                            .inflate(R.layout.label_item, myLabelListview, false);
                    tv.setText(o.getName());
                    return tv;
                }
            });
            return true;
        });
    }


    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            RxPermissions rxPermissions = new RxPermissions(SocialReleaseActivity.this);
            rxPermissions
                    .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) { // Always true pre-M
                            // 跳转到相册
                            ImagePicker.getInstance().setSelectLimit(maxSelectNum - imageSize);
                            ImagePicker.getInstance().setMultiMode(true);
                            Intent intent = new Intent(SocialReleaseActivity.this, ImageGridActivity.class);
                            //显示选中的图片
                            startActivityForResult(intent, REQUEST_CODE_SELECT);
                        }
                    });
        }
    };

    //弹出选择拍照还是手机相册的弹窗
    private void showPop() {
        if (null == photoWindow) {
            photoWindow = new PhotoWindow(this);
        }
        int popuHeight = MyUtil.dip2px(this, Float.parseFloat("152"));
        socialReleaseType.animate().translationY(-(popuHeight));
        photoWindow.showAtLocation(socialReleaseImage, Gravity.BOTTOM, 0, 0);
        photoWindow.getTake_photo().setOnClickListener(v -> {
            photoWindow.dismiss();
            if (SdcardTools.sdcard()) {
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions
                        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                //相机
                                Intent intent = new Intent(SocialReleaseActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                            }
                        });
            } else {
                Toast.makeText(this, "sd卡不可用", Toast.LENGTH_SHORT).show();
            }
        });
        photoWindow.getChose_pic().setOnClickListener(v -> {
            photoWindow.dismiss();
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions
                    .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) { // Always true pre-M
                            // 跳转到相册
                            ImagePicker.getInstance().setSelectLimit(maxSelectNum - imageSize);
                            ImagePicker.getInstance().setMultiMode(true);
                            Intent intent = new Intent(SocialReleaseActivity.this, ImageGridActivity.class);
                            //显示选中的图片
                            startActivityForResult(intent, REQUEST_CODE_SELECT);
                        }
                    });
        });
        photoWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 1f;
            getWindow().setAttributes(params);
            socialReleaseType.animate().translationY(0);
        });
    }

    /*------图片选择回调------*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }


        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (requestCode == REQUEST_CODE_SELECT) {
                tempList = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                imageSize = tempList.size();
                if (tempList == null) {
                    return;
                }
                selImageList.addAll(tempList);
                adapter.setList(selImageList);
                adapter.notifyDataSetChanged();
                sorReleaseRv.setVisibility(View.VISIBLE);
            }
        }
    }

    //弹出录音的POPU
    private void initPopu() {
        View layout = getLayoutInflater().inflate(R.layout.sra_popu, null);
        soundBtn = layout.findViewById(R.id.sound_btn);
        soundCancel = layout.findViewById(R.id.sound_cancel);
        soundComplete = layout.findViewById(R.id.sound_complete);
        soundTips = layout.findViewById(R.id.sound_tips);
        soundTime = layout.findViewById(R.id.sound_time);

        popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, screenHeight / 3 * 1);
        socialReleaseType.animate().translationY(-(screenHeight / 3 * 1));
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                socialReleaseType.animate().translationY(0);
                isComplete = false;
                RecordManager.getInstance().stop();
                MediaManager.release();
            }
        });
        popupWindow.showAtLocation(LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_social_release, null), Gravity.BOTTOM, 0, 0);

        soundBtn.setOnClickListener(view -> {
            if (!isPlay) {
                playAudio();
            } else {
                pauseAudio();
            }
        });

        soundCancel.setOnClickListener(view -> {
//            soundBtn.setEnabled(true);
//            soundBtn.setImageResource(R.mipmap.fabu_luyin);
            soundCancel.setVisibility(View.GONE);
            soundComplete.setVisibility(View.GONE);
            soundTime.setVisibility(View.GONE);
            soundTime.setBase(SystemClock.elapsedRealtime());
            isPlay = false;
            isComplete = false;
            soundBtn.stop();
            soundBtn.clearDuration();
            MediaManager.release();
        });

        soundComplete.setOnClickListener(view -> {
            dyVoice.setVisibility(View.VISIBLE);
            dyVoiceTime.setText(TimeUtil.toDateeee(duration) + "s");
            popupWindow.dismiss();
        });
    }

    //开始录音
    private void playAudio() {

        if (isComplete) {
            if (isPlaying) {
                mediaPlayer.stop();
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(path);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                timer.cancel();
                isPlaying = false;
//                soundBtn.changetoEndStatue();
                soundBtn.stopPlayRecorder();
                if (timer != null) {
                    timer.cancel();
                }
                soundTips.setText("录音完成");
                soundTime.setText(TimeUtil.toDateeee(duration) + "s");
                mediaPlayer.setOnCompletionListener(null);
            } else {
                isPlaying = true;
                soundBtn.playRecorder();
                soundTips.setText("正在播放");

//                MusicManager.getInstance().getDuration();
                try {
                    mediaPlayer.start();
                } catch (Exception e) {
                    e.printStackTrace();
//                    Log.e("播放异常======", "播放异常啦");
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    mediaPlayer.start();
                }


                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
//                        soundBtn.clearDuration();
                        mediaPlayer.setOnCompletionListener(null);
                        soundTips.setText("录音完成");
//                        Log.e("播放完成回调======", "播放完成");
//                        soundBtn.changetoEndStatue();
//                        soundBtn.stopPlayRecorder();
//                        mp.getCurrentPosition()
                        isPlaying = false;
                        soundTime.setText(TimeUtil.toDateeee(duration) + "s");
                        if (timer != null) {
                            timer.cancel();
                        }
//                        soundBtn.setDuration(duration);
                        mediaPlayer.stop();
                        try {
                            mediaPlayer.reset();
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            mediaPlayer.setDataSource(path);
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        soundBtn.resetPlayRecorder();
                    }
                });

                timer = new CountDownTimer(time, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int i = (int) (millisUntilFinished / 1000);
                        soundTime.setText(i + "s");
                    }

                    @Override
                    public void onFinish() {
                        soundTime.setText(TimeUtil.toDateeee(duration) + "s");
                        mediaPlayer.stop();
                    }
                }.start();
            }
        } else {
            soundBtn.setDuration(60000);
            soundBtn.play();
            soundTime.setBase(SystemClock.elapsedRealtime());
            soundTime.setVisibility(View.VISIBLE);
            soundTime.start();
            soundTime.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
//                    long base = chronometer.getBase();
//                    String format = chronometer.getFormat();
                    long ss = SystemClock.elapsedRealtime() - chronometer.getBase();
//                    LogUtils.debugInfo("base===="+base);
//                    LogUtils.debugInfo("format===="+format);
                    LogUtils.debugInfo("last===="+ss);
                    soundBtn.setProcessValue((int) ss);
                }
            });
            RecordManager.getInstance().start();
            soundTips.setText("录音中");
            isPlay = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    //定时更新界面
//                    int position = (int) msg.obj;
//                    LogUtils.debugInfo("sgm", "====播放进度" + position);
//                    soundBtn.setDuration(position);
//            }
//            super.handleMessage(msg);
//        }
//    };

    //暂停录音
    private void pauseAudio() {
        try {
            soundBtn.pause();
            isPlay = false;
            RecordManager.getInstance().stop();
            soundTips.setText("录音完成");
            soundCancel.setVisibility(View.VISIBLE);
            soundComplete.setVisibility(View.VISIBLE);
            soundTime.stop();
            getLuYinHou();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //停止录音
    private void stopAudio() {
        soundBtn.stop();
        isPlay = false;
        soundTips.setText("录音完成");
        RecordManager.getInstance().stop();
        getLuYinHou();
    }

    //录音成功后
    public void getLuYinHou() {
        RecordManager.getInstance().setRecordResultListener(new RecordResultListener() {
            @Override
            public void onResult(File result) {
                path = result.getPath();

                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                    //播放错误 防止崩溃
                    mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            mediaPlayer.reset();
                            return false;
                        }
                    });
                } else {
                    mediaPlayer.reset();
                }
                try {
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(path);
                    mediaPlayer.prepare();
                    duration = mediaPlayer.getDuration();
                    time = (long) duration;
                    isComplete = true;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick({R.id.social_release_voice, R.id.social_release_image, R.id.dy_voice_back, R.id.dy_close, R.id.balk, R.id.rightConfirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.social_release_voice:
                hideKeyboard(socialReleaseImage);
                selImageList.clear();
                adapter.setList(selImageList);
                adapter.notifyDataSetChanged();
                imagePath1 = "";
                imagePath2 = "";
                imagePath3 = "";
                imagePath4 = "";
                imagePath5 = "";
                imagePath6 = "";
                sorReleaseRv.setVisibility(View.GONE);
                initPopu();
                break;
            case R.id.social_release_image:
                showPop();
                hideKeyboard(socialReleaseImage);
                path = "";
                dyVoice.setVisibility(View.GONE);
                dyVoiceTime.setText("");
                break;
            case R.id.dy_voice_back:
                if (mIsPlayRecorder) {
                    return;
                }
                dyVoicePlay.setImageResource(R.mipmap.shequ_yuyin_zanting);
                LogUtils.debugInfo("====录音的地址", path);
                try {
                    mediaPlayer.start();
                } catch (Exception e) {
                    e.printStackTrace();
//                    Log.e("播放异常======", "播放异常啦");
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    mediaPlayer.start();
                }
                mIsPlayRecorder = true;

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.setOnCompletionListener(null);
                        if (timer != null) {
                            timer.cancel();
                        }
                        mIsPlayRecorder = false;
                        dyVoicePlay.setImageResource(R.mipmap.shequ_yuyin_bofang);
                        dyVoiceTime.setText(TimeUtil.toDateeee(duration) + "s");
                        mediaPlayer.stop();
                        try {
                            mediaPlayer.reset();
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            mediaPlayer.setDataSource(path);
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                timer = new CountDownTimer(time, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int i = (int) (millisUntilFinished / 1000);
                        dyVoiceTime.setText(i + "s");
                    }

                    @Override
                    public void onFinish() {
//                        dyVoiceTime.setText(TimeUtil.toDateeee(duration) + "s");
//                        mediaPlayer.stop();
                    }
                }.start();
                break;
            case R.id.dy_close:
                dyVoice.setVisibility(View.GONE);
                dyVoiceTime.setText(null);
                MediaManager.release();
                break;
            case R.id.balk:
                if (puTongWindow == null) {
                    puTongWindow = new PuTongWindow(this);
                }
                if (TextUtils.isEmpty(sorEt.getText()) && TextUtils.isEmpty(labelStr) && TextUtils.isEmpty(dyVoiceTime.getText().toString().trim()) && audio == null && imageSize == 0) {
                    finish();
                } else {
                    puTongWindow.showAtLocation(fabu, Gravity.CENTER, 0, 0);
                    puTongWindow.getTitText().setText("退出将无法保存");
                    puTongWindow.getSure().setOnClickListener(v -> {
                        finish();
                        puTongWindow.dismiss();
                    });
                    puTongWindow.getCancel().setOnClickListener(v -> {
                        puTongWindow.dismiss();
                    });
                }
                break;
            case R.id.rightConfirm:
                if (TextUtils.isEmpty(sorEt.getText()) && TextUtils.isEmpty(labelStr) && TextUtils.isEmpty(dyVoiceTime.getText().toString().trim()) && audio == null && imageSize == 0) {
                    toast("请输入您要发表的内容哟");
                } else {
                    if (MyUtil.isFastClick()) {
                        setFaBu(sorEt.getText().toString());
                    }
                }
                break;
        }
    }

    //标签弹窗
    private void initLabelPopu() {

        Dialog dialog = new Dialog(this, R.style.Transparent);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.pop_anim);
        View view1 = View.inflate(this, R.layout.more_label_popu, null);
        window.setContentView(view1);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
        dialog.show();

        popuTextOne = dialog.findViewById(R.id.one);
        gridViewOne = dialog.findViewById(R.id.more_label_recycler_one);
        gridViewTwo = dialog.findViewById(R.id.more_label_recycler_two);
        gridViewThree = dialog.findViewById(R.id.gridview_three);
        popuTextTwo = dialog.findViewById(R.id.no_label_tips);
        popuRelaOne = dialog.findViewById(R.id.xiaobeijing);
        popuRelaTwo = dialog.findViewById(R.id.xiaobeijingtwo);
        mSearchView = dialog.findViewById(R.id.more_label_SearchView);
        TextView sureText = dialog.findViewById(R.id.more_label_sure);

        gridViewOne.setAdapter(new TagAdapter<XuanzeLabelBean>(myLabelList) {
            @Override
            public View getView(FlowLayout parent, int position, XuanzeLabelBean zeBean) {
                TextView tv = (TextView) LayoutInflater.from(SocialReleaseActivity.this)
                        .inflate(R.layout.label_item, myLabelListview, false);
                tv.setText(zeBean.getName());
                return tv;
            }
        });
        gridViewOne.setOnTagClickListener((view, position, parent) -> {

            myLabelList.remove(position);
            gridViewOne.setAdapter(new TagAdapter<XuanzeLabelBean>(myLabelList) {
                @Override
                public View getView(FlowLayout parent, int position, XuanzeLabelBean zeBean) {
                    TextView tv = (TextView) LayoutInflater.from(SocialReleaseActivity.this)
                            .inflate(R.layout.label_item, myLabelListview, false);
                    tv.setText(zeBean.getName());
                    return tv;
                }
            });
            return true;
        });

        gridViewTwo.setAdapter(new TagAdapter<RecommendLabelBean.DataBean.AllBean>(allLabelList) {
            @Override
            public View getView(FlowLayout parent, int position, RecommendLabelBean.DataBean.AllBean allBean) {
                TextView tv = (TextView) LayoutInflater.from(SocialReleaseActivity.this)
                        .inflate(R.layout.label_item, gridViewTwo, false);
                tv.setText(allBean.getName());
                return tv;
            }
        });
        gridViewTwo.setOnTagClickListener((view, position, parent) -> {
            if (myLabelList.size() == 0) {
                XuanzeLabelBean xuanzeLabelBean = new XuanzeLabelBean();
                xuanzeLabelBean.setName(allLabelList.get(position).getName());
                xuanzeLabelBean.setId(allLabelList.get(position).getId());
                myLabelList.add(xuanzeLabelBean);
            } else if (myLabelList.size() <= 5) {
                boolean isDelete = false;
                for (int b = 0; b < myLabelList.size(); b++) {
                    if (allLabelList.get(position).getName().equals(myLabelList.get(b).getName())) {
                        myLabelList.remove(b);
                        isDelete = true;
                    }
                }
                if (!isDelete) {
                    XuanzeLabelBean xuanzeLabelBean = new XuanzeLabelBean();
                    xuanzeLabelBean.setName(allLabelList.get(position).getName());
                    xuanzeLabelBean.setId(allLabelList.get(position).getId());
                    myLabelList.add(xuanzeLabelBean);
                }
            } else {
                ArmsUtils.makeText(SocialReleaseActivity.this, "最多只能选择五个标签");
            }
            gridViewOne.setAdapter(new TagAdapter<XuanzeLabelBean>(myLabelList) {
                @Override
                public View getView(FlowLayout parent, int position, XuanzeLabelBean o) {
                    TextView tv = (TextView) LayoutInflater.from(SocialReleaseActivity.this)
                            .inflate(R.layout.label_item, gridViewOne, false);
                    tv.setText(o.getName());
                    return tv;
                }
            });
            return true;
        });

        sureText.setOnClickListener(v -> {
            myLabelListview.setAdapter(new TagAdapter<XuanzeLabelBean>(myLabelList) {
                @Override
                public View getView(FlowLayout parent, int position, XuanzeLabelBean o) {
                    TextView tv = (TextView) LayoutInflater.from(SocialReleaseActivity.this)
                            .inflate(R.layout.label_item, myLabelListview, false);
                    tv.setText(o.getName());
                    return tv;
                }
            });
            dialog.dismiss();
        });
        mSearchView.setOnClickSearch(string -> {
            getSearchLabel(string);
        });


        mSearchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                popuTextOne.setText("已选标签");
                popuRelaOne.setVisibility(View.VISIBLE);
                popuRelaTwo.setVisibility(View.GONE);
            }
        });
        if (!dialog.isShowing()) {
            myLabelListview.setAdapter(new TagAdapter<XuanzeLabelBean>(myLabelList) {
                @Override
                public View getView(FlowLayout parent, int position, XuanzeLabelBean xuanzeLabelBean) {
                    TextView tv = (TextView) LayoutInflater.from(SocialReleaseActivity.this)
                            .inflate(R.layout.label_item, lableRecyc, false);
                    tv.setText(xuanzeLabelBean.getName());
                    return tv;
                }
            });
        }
    }


    //获取标签
    private void getLabel() {
        RxUtils.loading(commonModel.getLabel(""), this)
                .subscribe(new ErrorHandleSubscriber<RecommendLabelBean>(mErrorHandler) {
                    @Override
                    public void onNext(RecommendLabelBean recommendLabelBean) {
                        topLabelList = recommendLabelBean.getData().getTop();
                        RecommendLabelBean.DataBean.TopBean topBean = new RecommendLabelBean.DataBean.TopBean();
                        topBean.setName("···");
                        topLabelList.add(topBean);
                        lableRecyc.setAdapter(new TagAdapter<RecommendLabelBean.DataBean.TopBean>(topLabelList) {
                            @Override
                            public View getView(FlowLayout parent, int position, RecommendLabelBean.DataBean.TopBean topBean) {
                                TextView tv = (TextView) LayoutInflater.from(SocialReleaseActivity.this)
                                        .inflate(R.layout.label_item, lableRecyc, false);
                                tv.setText(topBean.getName());
                                return tv;
                            }
                        });
                        allLabelList = recommendLabelBean.getData().getAll();

                        lableRecyc.setOnTagClickListener((view, position, parent) -> {
                            if ("···".equals(topLabelList.get(position).getName())) {
                                initLabelPopu();
                            } else {
                                if (myLabelList.size() == 0) {
                                    XuanzeLabelBean xuanzeLabelBean = new XuanzeLabelBean();
                                    xuanzeLabelBean.setName(topLabelList.get(position).getName());
                                    xuanzeLabelBean.setId(topLabelList.get(position).getId());
                                    myLabelList.add(xuanzeLabelBean);
                                } else if (myLabelList.size() <= 5) {
                                    boolean isDelete = false;
                                    for (int b = 0; b < myLabelList.size(); b++) {
                                        if (topLabelList.get(position).getName().equals(myLabelList.get(b).getName())) {
                                            myLabelList.remove(b);
                                            isDelete = true;
                                        }
                                    }
                                    if (!isDelete) {
                                        XuanzeLabelBean xuanzeLabelBean = new XuanzeLabelBean();
                                        xuanzeLabelBean.setName(topLabelList.get(position).getName());
                                        xuanzeLabelBean.setId(topLabelList.get(position).getId());
                                        myLabelList.add(xuanzeLabelBean);
                                    }
                                } else {
                                    ArmsUtils.makeText(SocialReleaseActivity.this, "最多只能选择五个标签");
                                }
                                myLabelListview.setAdapter(new TagAdapter<XuanzeLabelBean>(myLabelList) {
                                    @Override
                                    public View getView(FlowLayout parent, int position, XuanzeLabelBean o) {
                                        TextView tv = (TextView) LayoutInflater.from(SocialReleaseActivity.this)
                                                .inflate(R.layout.label_item, myLabelListview, false);
                                        tv.setText(o.getName());
                                        return tv;
                                    }
                                });
                            }
                            return true;
                        });
                    }
                });
    }

    //搜索标签
    private void getSearchLabel(String keyWord) {
        RxUtils.loading(commonModel.getSouSuoLabel(keyWord), this)
                .subscribe(new ErrorHandleSubscriber<SearchLabelBean>(mErrorHandler) {
                    @Override
                    public void onNext(SearchLabelBean searchLabelBean) {
                        popuTextOne.setText("相关标签");
                        popuRelaOne.setVisibility(View.GONE);
                        popuRelaTwo.setVisibility(View.VISIBLE);
                        searchLabelList = searchLabelBean.getData();
                        if (searchLabelBean.getData().size() != 0) {
                            gridViewThree.setVisibility(View.VISIBLE);
                            popuTextTwo.setVisibility(View.GONE);
                            gridViewThree.setAdapter(new TagAdapter<SearchLabelBean.DataBean>(searchLabelList) {
                                @Override
                                public View getView(FlowLayout parent, int position, SearchLabelBean.DataBean searchBean) {
                                    TextView tv = (TextView) LayoutInflater.from(SocialReleaseActivity.this)
                                            .inflate(R.layout.label_item, gridViewThree, false);
                                    tv.setText(searchBean.getName());
                                    return tv;
                                }
                            });
                            gridViewThree.setOnTagClickListener((view, position, parent) -> {
                                XuanzeLabelBean xuanzeLabelBean = new XuanzeLabelBean();
                                xuanzeLabelBean.setId(searchLabelBean.getData().get(position).getId());
                                xuanzeLabelBean.setName(searchLabelBean.getData().get(position).getName());
                                myLabelList.add(xuanzeLabelBean);
                                return true;
                            });
                        } else {
                            gridViewThree.setVisibility(View.GONE);
                            popuTextTwo.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    //发布
    private void setFaBu(String str) {

        if (path.isEmpty()) {
            audio = null;
        } else {
            File audioFile = new File(path);
            RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), audioFile);
            audio = MultipartBody.Part.createFormData("audio", audioFile.getName(), requestBody1);
        }

        if (adapter.getList().size() == 1) {
            ImageItem imageItem = tempList.get(0);
            imagePath1 = imageItem.path;
        } else if (adapter.getList().size() == 2) {
            ImageItem imageItem = tempList.get(0);
            imagePath1 = imageItem.path;
            ImageItem imageItem1 = tempList.get(1);
            imagePath2 = imageItem1.path;
        } else if (adapter.getList().size() == 3) {
            ImageItem imageItem = tempList.get(0);
            imagePath1 = imageItem.path;
            ImageItem imageItem1 = tempList.get(1);
            imagePath2 = imageItem1.path;
            ImageItem imageItem2 = tempList.get(2);
            imagePath3 = imageItem2.path;
        } else if (adapter.getList().size() == 4) {
            ImageItem imageItem = tempList.get(0);
            imagePath1 = imageItem.path;
            ImageItem imageItem1 = tempList.get(1);
            imagePath2 = imageItem1.path;
            ImageItem imageItem2 = tempList.get(2);
            imagePath3 = imageItem2.path;
            ImageItem imageItem3 = tempList.get(3);
            imagePath4 = imageItem3.path;
        } else if (adapter.getList().size() == 5) {
            ImageItem imageItem = tempList.get(0);
            imagePath1 = imageItem.path;
            ImageItem imageItem1 = tempList.get(1);
            imagePath2 = imageItem1.path;
            ImageItem imageItem2 = tempList.get(2);
            imagePath3 = imageItem2.path;
            ImageItem imageItem3 = tempList.get(3);
            imagePath4 = imageItem3.path;
            ImageItem imageItem4 = tempList.get(4);
            imagePath5 = imageItem4.path;
        } else if (adapter.getList().size() == 6) {
            ImageItem imageItem = tempList.get(0);
            imagePath1 = imageItem.path;
            ImageItem imageItem1 = tempList.get(1);
            imagePath2 = imageItem1.path;
            ImageItem imageItem2 = tempList.get(2);
            imagePath3 = imageItem2.path;
            ImageItem imageItem3 = tempList.get(3);
            imagePath4 = imageItem3.path;
            ImageItem imageItem4 = tempList.get(4);
            imagePath5 = imageItem4.path;
            ImageItem imageItem5 = tempList.get(5);
            imagePath6 = imageItem5.path;
        }


        if (imagePath1.isEmpty()) {
            img1 = null;
        } else {
            File img1File = new File(imagePath1);
            RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), img1File);
            img1 = MultipartBody.Part.createFormData("img1", img1File.getName(), requestBody2);
        }

        if (imagePath2.isEmpty()) {
            img2 = null;
        } else {
            File img2File = new File(imagePath2);
            RequestBody requestBody3 = RequestBody.create(MediaType.parse("multipart/form-data"), img2File);
            img2 = MultipartBody.Part.createFormData("img2", img2File.getName(), requestBody3);
        }

        if (imagePath3.isEmpty()) {
            img3 = null;
        } else {
            File img3File = new File(imagePath3);
            RequestBody requestBody4 = RequestBody.create(MediaType.parse("multipart/form-data"), img3File);
            img3 = MultipartBody.Part.createFormData("img3", img3File.getName(), requestBody4);
        }

        if (imagePath4.isEmpty()) {
            img4 = null;
        } else {
            File img4File = new File(imagePath4);
            RequestBody requestBody5 = RequestBody.create(MediaType.parse("multipart/form-data"), img4File);
            img4 = MultipartBody.Part.createFormData("img4", img4File.getName(), requestBody5);
        }

        if (imagePath5.isEmpty()) {
            img5 = null;
        } else {
            File img5File = new File(imagePath5);
            RequestBody requestBody6 = RequestBody.create(MediaType.parse("multipart/form-data"), img5File);
            img5 = MultipartBody.Part.createFormData("img5", img5File.getName(), requestBody6);
        }

        if (imagePath6.isEmpty()) {
            img6 = null;
        } else {
            File img6File = new File(imagePath6);
            RequestBody requestBody7 = RequestBody.create(MediaType.parse("multipart/form-data"), img6File);
            img6 = MultipartBody.Part.createFormData("img6", img6File.getName(), requestBody7);
        }

        if (myLabelList.size() == 1) {
            labelStr = myLabelList.get(0).getId() + "";
        } else if (myLabelList.size() == 2) {
            labelStr = myLabelList.get(0).getId() + "," + myLabelList.get(1).getId();
        } else if (myLabelList.size() == 3) {
            labelStr = myLabelList.get(0).getId() + "," + myLabelList.get(1).getId() + "," + myLabelList.get(2).getId();
        } else if (myLabelList.size() == 4) {
            labelStr = myLabelList.get(0).getId() + "," + myLabelList.get(1).getId() + "," + myLabelList.get(2).getId() + "," + myLabelList.get(3).getId();
        } else if (myLabelList.size() == 5) {
            labelStr = myLabelList.get(0).getId() + "," + myLabelList.get(1).getId() + "," + myLabelList.get(2).getId() + "," + myLabelList.get(3).getId() + "," + myLabelList.get(4).getId();
        } else {
            labelStr = "";
        }


        Map<String, Object> map = new HashMap<>();
        map.put("tags", labelStr);
        map.put("content", str);
        map.put("audio_time", TimeUtil.toDateeee(duration));
        showDialogLoding();
        RxUtils.loading(commonModel.send_dynamic(user.getUserId(), map, audio, null, img1, img2, img3, img4, img5, img6), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getCode() == 1) {
                            LogUtils.debugInfo("====标签", labelStr);
                            selImageList.clear();
                            adapter.setList(selImageList);
                            adapter.notifyDataSetChanged();
                            sorReleaseRv.setVisibility(View.GONE);
                            dyVoice.setVisibility(View.GONE);
                            sorEt.setText(null);
                            setResult(0x147);

//                            TipDialog.show(SocialReleaseActivity.this, baseBean.ge tMessage(), TipDialog.TYPE.SUCCESS);
                            EventBus.getDefault().post(new FirstEvent("修改成功", FABUCHENGGONG));
                            disDialogLoding();
                            finish();
                        } else {
                            TipDialog.show(SocialReleaseActivity.this, baseBean.getMessage(), TipDialog.TYPE.WARNING);
                        }

                    }
                });
    }

    public static class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("SendNotify".equals(intent.getAction())) {
                if (socialReleaseActivity != null && !socialReleaseActivity.isFinishing()) {
                    socialReleaseActivity.pauseAudio();
                    soundTime.stop();
                }
            } else if ("play_recorder_complete".equals(intent.getAction())) {
                if (socialReleaseActivity != null && !socialReleaseActivity.isFinishing()) {

//                    Log.e("收到完成广播", "收到完成广播");
                    try {
                        mediaPlayer.stop();
                        mediaPlayer.prepare();
                        isPlaying = false;
                        if (timer != null) {
                            timer.cancel();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (puTongWindow == null) {
                puTongWindow = new PuTongWindow(this);
            }
            if (TextUtils.isEmpty(sorEt.getText()) && TextUtils.isEmpty(labelStr) && TextUtils.isEmpty(dyVoiceTime.getText().toString().trim()) && audio == null && imageSize == 0) {
                finish();
            } else {
                puTongWindow.showAtLocation(fabu, Gravity.CENTER, 0, 0);
                puTongWindow.getTitText().setText("退出将无法保存");
                puTongWindow.getSure().setOnClickListener(v -> {
                    finish();
                    puTongWindow.dismiss();
                });
                puTongWindow.getCancel().setOnClickListener(v -> {
                    puTongWindow.dismiss();
                });
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
