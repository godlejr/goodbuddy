package com.demand.goodbuddy.login.base.presenter;

import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.util.APIErrorUtil;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public interface LoginPresenter {
    void onCreate();

    void onClickLogin(String email, String password);

    void onNetworkError(APIErrorUtil apiErrorUtil);

    void onSuccessLogin(User user);

    void onSuccessSetDeviceIdAndFcmToken(User user,String deviceId, String firebaseToken, String accessToken);

    void onClickJoin();
}
