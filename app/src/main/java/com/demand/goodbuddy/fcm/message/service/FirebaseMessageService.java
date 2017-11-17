package com.demand.goodbuddy.fcm.message.service;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.dialog.popup.notification.activity.NotificationPopupActivity;
import com.demand.goodbuddy.main.base.activity.MainActivity;
import com.demand.goodbuddy.message.activity.MessageActivity;
import com.demand.goodbuddy.message.presenter.MessagePresenter;
import com.demand.goodbuddy.message.presenter.impl.MessagePresenterImpl;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

/**
 * Created by Dev-0 on 2017-02-21.
 */

public class FirebaseMessageService extends com.google.firebase.messaging.FirebaseMessagingService {
    private int badge_count = 0;
    private SharedPreferences loginInfo;
    private boolean notification_flag;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().get("body") == null) {
            String content = remoteMessage.getNotification().getBody();

            Intent popup_intent = new Intent(this, NotificationPopupActivity.class);
            popup_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            popup_intent.putExtra("content", content);
            startActivity(popup_intent);

        } else {
            String message = remoteMessage.getData().get("body");

            loginInfo = getSharedPreferences("loginInfo", MODE_PRIVATE);
            notification_flag = loginInfo.getBoolean("notification", true);

            set_badge();


            ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(5);
            int runningTaskInfosSize = runningTaskInfos.size();

            if (runningTaskInfosSize > 1){ // 앱 실행중
                for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTaskInfos) {
                    ComponentName componentName = runningTaskInfo.topActivity;
                    String className = componentName.getClassName();
                    if(className.equals("com.demand.goodbuddy.message.activity.MessageActivity")){
                        Intent intent = new Intent(this, MessageActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("message", message);
                        startActivity(intent);
                        return;
                    }
                }
                if (notification_flag) {
                    sendNotification(message);
                }
            } else {
                if (notification_flag) {
                    sendNotification(message);
                }
            }

        }

    }



    private void sendNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("GoodBuddy")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void set_badge() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("badge", Activity.MODE_PRIVATE);
        badge_count = pref.getInt("badge_count", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("badge_count", badge_count + 1);
        editor.apply();
        badge_count = pref.getInt("badge_count", badge_count);


        Intent intent = new Intent();
        intent.setAction("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count_package_name", "com.demand.well_family.well_family");
        intent.putExtra("badge_count_class_name", "com.demand.well_family.well_family.intro.IntroActivity");
        intent.putExtra("badge_count", badge_count);
        getApplicationContext().sendBroadcast(intent);
        Log.e("뱃지2", badge_count + "");

    }

}
