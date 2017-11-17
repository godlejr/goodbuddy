package com.demand.goodbuddy.service.pedometer.view;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public interface PedometerServiceView {
    void init();

    void setListenerRegistered();
    void setListenerUnregistered();
    void showMessage(String message);


}
