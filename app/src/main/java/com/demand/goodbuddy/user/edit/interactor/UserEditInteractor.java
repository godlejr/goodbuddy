package com.demand.goodbuddy.user.edit.interactor;

import com.demand.goodbuddy.dto.User;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public interface UserEditInteractor {
    void setMainController();

    User getUser();

    void setUser(User user);

    void setUserController();

    void setUserController(String accessToken);

    void updateUser(int userId, String name, String birth, int gender);


    void updatePassword(int userId, String password);

    void setMainController(String accessToken);
}
