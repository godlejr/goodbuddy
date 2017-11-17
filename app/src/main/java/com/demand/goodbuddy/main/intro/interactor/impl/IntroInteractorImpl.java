package com.demand.goodbuddy.main.intro.interactor.impl;

import android.util.Log;

import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.LogFlag;
import com.demand.goodbuddy.main.intro.interactor.IntroInteractor;
import com.demand.goodbuddy.main.intro.presenter.IntroPresenter;
import com.demand.goodbuddy.repository.UserController;
import com.demand.goodbuddy.util.ErrorUtil;
import com.demand.goodbuddy.repository.MainController;
import com.demand.goodbuddy.repository.interceptor.HeaderInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ㅇㅇ on 2017-04-24.
 */

public class IntroInteractorImpl implements IntroInteractor {
    private IntroPresenter introPresenter;

    private static final Logger logger = LoggerFactory.getLogger(IntroInteractorImpl.class);
    private UserController userController;

    public IntroInteractorImpl(IntroPresenter introPresenter) {
        this.introPresenter = introPresenter;
    }


    @Override
    public void getMultipleUserAccessValidation(User user) {
        int userId = user.getId();
        String deviceId = user.getDeviceId();
        String accessToken = user.getAccessToken();

        Log.e("deviceId", deviceId);

        HashMap<String,String> map =new HashMap<>();
        map.put("deviceId",deviceId);
        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<Integer> call_device_check = userController.checkDeviceId(userId, map);
        call_device_check.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(retrofit2.Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    int check = response.body();
                    introPresenter.onSuccessMultipleUserAccessValidation(check);
                } else {
                    introPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Integer> call, Throwable t) {
                log(t);
                introPresenter.onNetworkError(null);
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
