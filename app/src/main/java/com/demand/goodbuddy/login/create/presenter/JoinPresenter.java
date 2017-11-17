package com.demand.goodbuddy.login.create.presenter;

import com.demand.goodbuddy.util.APIErrorUtil;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public interface JoinPresenter {

    void init();

    void showUserBirth(String birth);

    void onClickBirth();

    void onClickEmailCheck(String email);

    void onClickJoin(String email, String password, String passwordConfirm, String name, String birth, int gender);

    void onNetworkError(APIErrorUtil apiErrorUtil);

    void onSuccessGetEmailCheck(Boolean check);

    void onSuccessSetJoin();

    void onClickBack();
}
