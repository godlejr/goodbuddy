package com.demand.goodbuddy.login.base.interactor.impl;

import android.util.Log;

import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.LogFlag;
import com.demand.goodbuddy.login.base.presenter.LoginPresenter;
import com.demand.goodbuddy.login.base.interactor.LoginInteractor;
import com.demand.goodbuddy.repository.MainController;
import com.demand.goodbuddy.repository.UserController;
import com.demand.goodbuddy.repository.interceptor.HeaderInterceptor;
import com.demand.goodbuddy.util.ErrorUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInteractorImpl.class);
    private LoginPresenter loginPresenter;
    private MainController mainController;
    private UserController userController;


    public LoginInteractorImpl(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void setLogin(String email, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);

        mainController = new HeaderInterceptor().getClientForMainServer().create(MainController.class);
        Call<User> call_login = mainController.login(map);
        call_login.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User user = response.body();
                    loginPresenter.onSuccessLogin(user);
                }else {
                    loginPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                log(t);
                loginPresenter.onNetworkError(null);
            }
        });

    }


    @Override
    public void setDeviceIdAndFcmToken(final User user, final String deviceId, final String fcmToken) {
        final String accessToken = user.getAccessToken();
        Log.e("accessToken", accessToken);

        HashMap<String, String> map = new HashMap<>();
        map.put("deviceId", deviceId);
        map.put("fcmToken", fcmToken);

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<ResponseBody> call_update_deviceId_token = userController.updateDeviceIdToken(user.getId(), map);
        call_update_deviceId_token.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loginPresenter.onSuccessSetDeviceIdAndFcmToken(user, deviceId, fcmToken, accessToken);
                } else {
                    loginPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                log(t);
                loginPresenter.onNetworkError(null);
            }
        });
    }

    private static void log(Throwable throwable) {
        StackTraceElement[] ste = throwable.getStackTrace();
        String className = ste[0].getClassName();
        String methodName = ste[0].getMethodName();
        int lineNumber = ste[0].getLineNumber();
        String fileName = ste[0].getFileName();

        if (LogFlag.printFlag) {
            if (logger.isInfoEnabled()) {
                logger.info("Exception: " + throwable.getMessage());
                logger.info(className + "." + methodName + " " + fileName + " " + lineNumber + " " + "line");
            }
        }
    }
}
