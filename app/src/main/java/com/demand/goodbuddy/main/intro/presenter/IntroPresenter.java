package com.demand.goodbuddy.main.intro.presenter;

import com.demand.goodbuddy.util.APIErrorUtil;

/**
 * Created by ㅇㅇ on 2017-04-24.
 */

public interface IntroPresenter {
    void onCreate();
    void validateUserExist();
    void onNetworkError(APIErrorUtil apiErrorUtil);
    void onSuccessMultipleUserAccessValidation(int check);


}
