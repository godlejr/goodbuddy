package com.demand.goodbuddy.pedometer.view;

import android.view.View;

import com.demand.goodbuddy.receiver.pedometer.receiver.PedometerReceiver;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public interface PedometerView {
    void init();

    View getDecorView();
    void setToolbar(View decorView);
    void showToolbarTitle(String title);

    void pedometerMessageSender(int count);

    void showSeekBarProgress(int progress);

    void showStartButton();

    void showStopButton();

    void goneStartButton();

    void goneStopButton();

    void setServiceStart();
    void setAlarmManagerCanceled();
    void setServiceTerminated();

    void showMessage(String message);

    void showCount(String count);

    void showPedometerSpeed(int level);
}
