package com.demand.goodbuddy.pedometer.presenter;

import android.app.ActivityManager;

import com.demand.goodbuddy.util.APIErrorUtil;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public interface PedometerPresenter {
    void onCreate();

    void setPedometerService(ActivityManager manager);

    void setCountReset();

    void onStopService();

    void onStartService();

    void onProgressChanged(int progress);
}
