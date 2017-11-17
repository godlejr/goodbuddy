package com.demand.goodbuddy.user.edit.presenter;

import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.util.APIErrorUtil;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public interface UserEditPresenter {
    void init(User user);

    void onClickEditProfile(String name, String birth, int gender);

    void showUserBirth(String message);

    void onClickEditPassword(String password, String passwordConfirm);

    void onNetworkError(APIErrorUtil apiErrorUtil);

    void onClickBack();

    void onSuccessUpdateUser(User user);

    void onSuccessUpdatePassword();

    void onClickBirth();
}
