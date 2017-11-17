package com.demand.goodbuddy.login.base.view;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public interface LoginView {
    void init();

    void showMessage(String message);

    String getDeviceId();

    String getFireBaseToken();

    void navigateToMainActivity();

    void navigateToJoinActivity();
}
