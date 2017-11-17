package com.demand.goodbuddy.receiver.pedometer.interactor.impl;

import android.content.Context;

import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.LogFlag;
import com.demand.goodbuddy.receiver.pedometer.interactor.PedometerReceiverInteractor;
import com.demand.goodbuddy.receiver.pedometer.presenter.PedometerReceiverPresenter;
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
 * Created by ㅇㅇ on 2017-05-04.
 */

public class PedometerReceiverInteractorImpl implements PedometerReceiverInteractor {
    private PedometerReceiverPresenter pedometerReceiverPresenter;

    private UserController userController;
    private static final Logger logger = LoggerFactory.getLogger(PedometerReceiverInteractorImpl.class);

    private Context context;

    public PedometerReceiverInteractorImpl(PedometerReceiverPresenter pedometerReceiverPresenter) {
        this.pedometerReceiverPresenter = pedometerReceiverPresenter;
    }

    @Override
    public void setActiveMass(User user, int activeMass) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        HashMap<String, String> map = new HashMap<>();
        map.put("activeMass", String.valueOf(activeMass));

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<ResponseBody> callInsertActiveMass = userController.insertActiveMass(userId, map);
        callInsertActiveMass.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // success
                    pedometerReceiverPresenter.onSuccessSetActiveMass();
                } else {
                    pedometerReceiverPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                log(t);
                pedometerReceiverPresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
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
