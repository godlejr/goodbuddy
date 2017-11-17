package com.demand.goodbuddy.service.pedometer.presenter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public interface PedometerServicePresenter {
    void onCreate(Context context);

    void setSensorListener(Sensor sensor);

    void onSensorChanged(SensorEvent event);

    void setCountUpdated( );

    void setDBInit();

    void onSuccessSetActiveMass();

    void setAlarmManager();


}
