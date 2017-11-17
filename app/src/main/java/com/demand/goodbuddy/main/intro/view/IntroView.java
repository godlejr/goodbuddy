package com.demand.goodbuddy.main.intro.view;

/**
 * Created by ㅇㅇ on 2017-04-24.
 */

public interface IntroView {
    void init();
    void navigateToLoginActivity();

    void showMessage(String message);
    void navigateToMainActivity();

    void setPermission();
}
