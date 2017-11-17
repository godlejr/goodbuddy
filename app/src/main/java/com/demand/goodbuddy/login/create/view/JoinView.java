package com.demand.goodbuddy.login.create.view;

import java.util.Calendar;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public interface JoinView {
    void init();

    void showDatePicker();

    void showUserBirth(String message);

    void showToolbarTitle(String message);

    void setToolbar();

    void showMessage(String message);

    void showEmailText(String message);

    void navigateToLoginActivity();

    void navigateToBack();
}
