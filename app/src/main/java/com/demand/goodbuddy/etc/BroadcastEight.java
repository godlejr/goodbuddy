package com.demand.goodbuddy.etc;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.main.intro.activity.IntroActivity;


/**
 * Created by Dean on 6/1/2016.
 */
public class BroadcastEight extends BroadcastReceiver {

    String INTENT_ACTION = Intent.ACTION_BOOT_COMPLETED;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, IntroActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.push_icon).setTicker("굿버디").setWhen(System.currentTimeMillis())
                .setNumber(1).setContentTitle("굿버디 알림").setContentText("오늘의 혈압과 혈당을 측정하세요!")
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingIntent).setAutoCancel(true);
        //.setDefaults(Notification.DEFAULT_SOUND).setContentIntent(pendingIntent).setAutoCancel(true);
        //.setVibrate(new long[]{0l}).setSound(null).setContentIntent(pendingIntent).setAutoCancel(true);


        notificationmanager.notify(1, builder.getNotification());
    }
}