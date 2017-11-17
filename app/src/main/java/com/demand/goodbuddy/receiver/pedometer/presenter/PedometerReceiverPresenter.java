package com.demand.goodbuddy.receiver.pedometer.presenter;

import android.content.Context;

import com.demand.goodbuddy.util.APIErrorUtil;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public interface PedometerReceiverPresenter {
    void onCreate(Context context);

    void onNetworkError(APIErrorUtil apiErrorUtil);

    void setActiveMass();

    void onSuccessSetActiveMass();

    void setCountReset();

    void setDBInit();
}
