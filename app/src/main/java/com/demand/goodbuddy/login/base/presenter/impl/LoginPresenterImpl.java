package com.demand.goodbuddy.login.base.presenter.impl;

import android.content.Context;

import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.login.base.view.LoginView;
import com.demand.goodbuddy.login.base.interactor.LoginInteractor;
import com.demand.goodbuddy.login.base.interactor.impl.LoginInteractorImpl;
import com.demand.goodbuddy.login.base.presenter.LoginPresenter;
import com.demand.goodbuddy.util.APIErrorUtil;
import com.demand.goodbuddy.util.PreferenceUtil;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private LoginInteractor loginInteractor;
    private LoginView loginView;
    private PreferenceUtil preferenceUtil;

    public LoginPresenterImpl(Context context) {
        this.loginView = (LoginView)context;
        this.loginInteractor = new LoginInteractorImpl(this);
        this.preferenceUtil = new PreferenceUtil(context);
    }

    @Override
    public void onCreate() {
        loginView.init();

    }

    @Override
    public void onClickLogin(String email, String password) {
        loginInteractor.setLogin(email, password);
    }

    @Override
    public void onNetworkError(APIErrorUtil apiErrorUtil) {
        if (apiErrorUtil == null) {
            loginView.showMessage("네트워크 불안정합니다. 다시 시도하세요.");
        } else {
            loginView.showMessage(apiErrorUtil.message());
        }
    }

    @Override
    public void onSuccessLogin(User user) {
        if (user == null) {
            loginView.showMessage("ID / 패스워드를 확인해주세요.");
        } else {
            String deviceId = loginView.getDeviceId();
            String firebaseToken = loginView.getFireBaseToken();

            loginInteractor.setDeviceIdAndFcmToken(user, deviceId, firebaseToken);
        }
    }
    @Override
    public void onSuccessSetDeviceIdAndFcmToken(User user, String deviceId, String fcmToken,String accessToken) {
        preferenceUtil.setUserInfo(user, deviceId, fcmToken,accessToken);
        loginView.navigateToMainActivity();
    }

    @Override
    public void onClickJoin() {
        loginView.navigateToJoinActivity();
    }


}
