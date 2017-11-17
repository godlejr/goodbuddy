package com.demand.goodbuddy.service.pedometer.interactor;

import android.content.Context;

import com.demand.goodbuddy.dto.User;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public interface PedometerServiceInteractor {

    Context getContext();
    void setContext(Context context);


    boolean isStatus();

    void setStatus(boolean status);

    void setActiveMass(User user, int activeMass);
}
