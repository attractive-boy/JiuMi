package com.miniapp.talks.utils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.miniapp.talks.R;

import java.io.IOException;

/**
 * Created by Administrator on 2017/11/28.
 * 播放录音类
 */

public class MediaManager {
    private static MediaPlayer mMediaPlayer;

    private static boolean isPause;

    //播放录音
    public static void playSound(String filePath, MediaPlayer.OnCompletionListener onCompletionListener) {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            //播放错误 防止崩溃
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mMediaPlayer.reset();
                    return false;
                }
            });
        } else {
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(onCompletionListener);
            mMediaPlayer.setDataSource(filePath);
            mMediaPlayer.prepare();
            mMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //播放录音
    public static void playSoundAsync(String filePath, MediaPlayer.OnCompletionListener onCompletionListener,MediaPlayer.OnPreparedListener onPreparedListener) {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            //播放错误 防止崩溃
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mMediaPlayer.reset();
                    return false;
                }
            });
        } else {
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(onCompletionListener);
            mMediaPlayer.setDataSource(filePath);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(onPreparedListener);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 如果 播放时间过长,如30秒
     * 用户突然来电话了,则需要暂停
     */
    public static void pause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            isPause = true;
        }
    }

    /**
     * 播放
     */
    public static void resume() {
        if (mMediaPlayer != null && isPause) {
            mMediaPlayer.start();
            isPause = false;
        }
    }

    /**
     * activity 被销毁  释放
     */
    public static void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public static void zheng(ImageView imageButton) {
        if (mMediaPlayer.isPlaying()) {
            imageButton.setImageResource(R.mipmap.shequ_yuyin_bofang);
        } else {
            imageButton.setImageResource(R.mipmap.shequ_yuyin_zanting);
        }
    }
    //获取当前播放进度
    public static  int getMusicCurrentPosition()
    {
        int rtn = 0;
        if (mMediaPlayer != null)
        {
            rtn = mMediaPlayer.getCurrentPosition();

        }

        return rtn;
    }
}
