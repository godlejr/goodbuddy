package com.demand.goodbuddy.service.pedometer.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.etc.manbogiflag;
import com.demand.goodbuddy.service.pedometer.presenter.PedometerServicePresenter;
import com.demand.goodbuddy.service.pedometer.presenter.impl.PedometerServicePresenterImpl;
import com.demand.goodbuddy.service.pedometer.view.PedometerServiceView;
import com.demand.goodbuddy.util.PreferenceUtil;


/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public class PedometerService extends Service implements SensorEventListener, PedometerServiceView {
    private PedometerServicePresenter pedometerServicePresenter;
    private SensorManager sensorManager;
    private Sensor sensor;
    private PreferenceUtil preferenceUtil;

    @Override
    public void onCreate() {
        super.onCreate();

        this.preferenceUtil = new PreferenceUtil(getApplicationContext());
        pedometerServicePresenter = new PedometerServicePresenterImpl(this);
        pedometerServicePresenter.onCreate(PedometerService.this);

        Log.e("ㅇㅇㅇ", "서비스 onCreate");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        pedometerServicePresenter.setSensorListener(sensor);

        preferenceUtil.setPedometerFirst(false);
        if (preferenceUtil.getPedometerPlay()) {

            manbogiflag.setIsPlaying(true);
            startForeground(1, new Notification());

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification notification = new Notification.Builder(getApplicationContext())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("GoodBuddy 만보기가 실행중입니다.")
                    .setContentText("")
                    .setAutoCancel(false)
                    .build();

            notificationManager.notify(startId, notification);
            notificationManager.cancel(startId);
        }


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void init() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        setListenerRegistered();

        Log.e("ㅇㅇㅇ", "서비스 onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        pedometerServicePresenter.onSensorChanged(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void setListenerRegistered() {
        sensorManager.unregisterListener(this);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void setListenerUnregistered() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(PedometerService.this, message, Toast.LENGTH_SHORT).show();
    }

}
