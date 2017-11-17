package com.demand.goodbuddy.login.base.interactor;

import com.demand.goodbuddy.dto.User;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public interface LoginInteractor {
    void setLogin(String email, String password);

    void setDeviceIdAndFcmToken(User user, String deviceId, String fcmToken);


}
