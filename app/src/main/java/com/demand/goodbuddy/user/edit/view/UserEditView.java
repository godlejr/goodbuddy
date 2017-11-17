package com.demand.goodbuddy.user.edit.view;

import com.demand.goodbuddy.dto.User;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public interface UserEditView {
    void init();

    void showUserName(String message);

    void showUserBirth(String message);

    void setRadioButtonCheckForMan();

    void setRadioButtonCheckForFemale();

    void showDatePicker();

    void showMessage(String message);

    void showToolbarTitle(String message);

    void setToolbar();

    void navigateToBack();

    void setUser(User user);
}
