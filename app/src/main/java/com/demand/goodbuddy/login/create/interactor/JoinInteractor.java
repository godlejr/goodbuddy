package com.demand.goodbuddy.login.create.interactor;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public interface JoinInteractor {
    void setMainController();

    boolean isEmailChecked();

    void setEmailChecked(boolean emailChecked);

    void getEmailCheck(String email);

    boolean getEmailValidation(String email);

    void setJoin(String email, String password, String name, String birth, int gender);
}
