package com.miniapp.talks.app.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.PagerSnapHelper;
import android.util.Log;

import com.miniapp.talks.R;
import com.miniapp.talks.activity.MainActivity;

public class RoomPlayService extends Service {

    private NotificationManager notificationManager;

    private String notificationId = "room_play_channelId";

    private String notificationName = "room_play_channelName";

    //通知的唯一标识号。
    private static final int NOTIFICATION_ID = 112106;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //创建NotificationChannel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(notificationId, notificationName, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        startForeground(1,getNotification());
    }

    private Notification getNotification() {

        // PendingIntent如果用户选择此通知，则启动我们的活动
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(this, MainActivity.class),0);

        Notification.Builder builder = new Notification.Builder(this)

                .setTicker("正在播放")

                .setSmallIcon(R.mipmap.ic_launcher_app)

                .setContentIntent(pendingIntent)

                .setContentTitle("啾咪")

                .setContentText("播放中");

        //设置Notification的ChannelID,否则不能正常显示
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(notificationId);
        }
        Notification notification = builder.build();

        //发送通知
//        notificationManager.notify(NOTIFICATION_ID,notification);
        return notification;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(notificationManager !=null){
            notificationManager.cancel(NOTIFICATION_ID);
        }
    }
}
