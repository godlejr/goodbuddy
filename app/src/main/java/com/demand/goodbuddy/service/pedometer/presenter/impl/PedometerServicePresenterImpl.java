package com.demand.goodbuddy.service.pedometer.presenter.impl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;

import com.demand.goodbuddy.etc.manbogiflag;
import com.demand.goodbuddy.receiver.pedometer.receiver.PedometerReceiver;
import com.demand.goodbuddy.service.pedometer.interactor.PedometerServiceInteractor;
import com.demand.goodbuddy.service.pedometer.interactor.impl.PedometerServiceInteractorImpl;
import com.demand.goodbuddy.service.pedometer.presenter.PedometerServicePresenter;
import com.demand.goodbuddy.service.pedometer.view.PedometerServiceView;
import com.demand.goodbuddy.util.APIErrorUtil;
import com.demand.goodbuddy.util.PreferenceUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.demand.goodbuddy.service.pedometer.flag.PedometerFlag.SHAKE_THRESHOLD;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public class PedometerServicePresenterImpl implements PedometerServicePresenter {
    private PedometerServiceInteractor pedometerServiceInteractor;
    private PedometerServiceView pedometerServiceView;
    private PreferenceUtil preferenceUtil;
    private long lastTime;
    private float speed;
    private float x, y, z;

//    private SQLiteOpenHelper sqLiteOpenHelper;
//    private SQLiteDatabase sqLiteDatabase;

    private AlarmManager alarmManager;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;

    private float lastX;
    private float lastY;
    private float lastZ;

    public PedometerServicePresenterImpl(Context context) {
        this.pedometerServiceInteractor = new PedometerServiceInteractorImpl(this);
        this.pedometerServiceView = (PedometerServiceView) context;
        this.preferenceUtil = new PreferenceUtil(context);

    }

    @Override
    public void onCreate(Context context) {
        pedometerServiceView.init();
        pedometerServiceInteractor.setContext(context);

        setAlarmManager();
        setPreferenceInit();

    }

    @Override
    public void setSensorListener(Sensor sensor) {
        if (sensor != null) {
            pedometerServiceView.setListenerRegistered();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (manbogiflag.isPlaying()) {
            switch (event.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    long currentTime = System.currentTimeMillis();
                    long gabOfTime = (currentTime - lastTime);

                    if (gabOfTime > 100) {
                        lastTime = currentTime;
                        x = event.values[SensorManager.DATA_X];
                        y = event.values[SensorManager.DATA_Y];
                        z = event.values[SensorManager.DATA_Z];


                        speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                        if (speed > preferenceUtil.getPedometerSpeed()) {

                            Log.e("x: ", x +"");
                            Log.e("y: ", y +"");
                            Log.e("z: ", z +"");
                            Log.e("lastX: ",lastX +"");
                            Log.e("lastY: ", lastY +"");
                            Log.e("lastZ: ", lastZ +"");
                            Log.e("speed: ",speed +"");


                            int date = Integer.valueOf(preferenceUtil.getPedometerDate());
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
                            Calendar calendar = Calendar.getInstance();
                            int today = Integer.valueOf(simpleDateFormat.format(calendar.getTime()));
                            Log.e("날짜", date + " / " + today);

                            if (date < today) {
                                pedometerServiceInteractor.setActiveMass(preferenceUtil.getUserInfo(), preferenceUtil.getPedometer());
                            }

                            setCountUpdated();
                        }

                        lastX = event.values[DATA_X];
                        lastY = event.values[DATA_Y];
                        lastZ = event.values[DATA_Z];
                    }
                    break;
            }
        }
    }

    @Override
    public void setCountUpdated() {
        int count = preferenceUtil.getPedometer();
        preferenceUtil.setPedometer(count + 1);

        //Log.e("만보기", preferenceUtil.getPedometer()+"");
//        sqLiteDatabase.execSQL("update pedometer set count = count + 1");
    }

    @Override
    public void setDBInit() {

    }

    @Override
    public void onSuccessSetActiveMass() {
        setCountReset();
    }

    public void setCountReset() {
        this.preferenceUtil.setPedometer(0);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        String today = simpleDateFormat.format(calendar.getTime());
        preferenceUtil.setPedometerDate(today);
        Log.e("dsafasdf", "만보기 데이터 넣음3232");
    }


    public void setPreferenceInit() {
        Context context = pedometerServiceInteractor.getContext();
        this.preferenceUtil = new PreferenceUtil(context);


//        sqLiteOpenHelper = new SQLiteOpenHelper(context);
//        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    @Override
    public void setAlarmManager() {
        Log.e("ㅎㅎㅎ", "setAlarmManager");
        Context context = pedometerServiceInteractor.getContext();
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, PedometerReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 58);
        calendar.set(Calendar.SECOND, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
        }

    }


}
