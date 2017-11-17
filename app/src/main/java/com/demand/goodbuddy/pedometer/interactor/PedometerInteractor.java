package com.demand.goodbuddy.pedometer.interactor;

import com.demand.goodbuddy.receiver.pedometer.receiver.PedometerReceiver;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public interface PedometerInteractor {
    PedometerReceiver getPedometerReceiver();

    void setPedometerReceiver(PedometerReceiver pedometerReceiver);

    boolean isStatus();

    void setStatus(boolean status);
}
