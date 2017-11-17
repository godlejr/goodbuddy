package com.demand.goodbuddy.service.pedometer.interactor.impl;

import android.content.Context;

import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.LogFlag;
import com.demand.goodbuddy.receiver.pedometer.interactor.impl.PedometerReceiverInteractorImpl;
import com.demand.goodbuddy.repository.UserController;
import com.demand.goodbuddy.repository.interceptor.HeaderInterceptor;
import com.demand.goodbuddy.service.pedometer.interactor.PedometerServiceInteractor;
import com.demand.goodbuddy.service.pedometer.presenter.PedometerServicePresenter;
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

public class PedometerServiceInteractorImpl implements PedometerServiceInteractor {
    private PedometerServicePresenter pedometerServicePresenter;
    private UserController userController;
    private static final Logger logger = LoggerFactory.getLogger(PedometerReceiverInteractorImpl.class);

    private Context context;
    private boolean status;


    public PedometerServiceInteractorImpl(PedometerServicePresenter pedometerServicePresenter) {
        this.pedometerServicePresenter = pedometerServicePresenter;
        this.status = false;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public boolean isStatus() {
        return status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void setActiveMass(User user, int activeMass) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        HashMap<String, String> map = new HashMap<>();
        map.put("activeMass", String.valueOf(activeMass));
        map.put("flag","flag");

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<ResponseBody> callInsertActiveMass = userController.insertActiveMass(userId, map);
        callInsertActiveMass.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // success
                    pedometerServicePresenter.onSuccessSetActiveMass();
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                log(t);

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
