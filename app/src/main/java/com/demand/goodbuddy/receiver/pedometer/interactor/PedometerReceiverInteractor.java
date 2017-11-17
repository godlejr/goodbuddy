package com.demand.goodbuddy.receiver.pedometer.interactor;

import android.content.Context;

import com.demand.goodbuddy.dto.User;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public interface PedometerReceiverInteractor {
    void setActiveMass(User user, int activeMass);

    Context getContext();
    void setContext(Context context);
}
