package com.demand.goodbuddy.record.interactor.impl;

import com.demand.goodbuddy.dto.BloodPressure;
import com.demand.goodbuddy.dto.BloodSugar;
import com.demand.goodbuddy.dto.Bmi;
import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.LogFlag;
import com.demand.goodbuddy.record.interactor.RecordInteractor;
import com.demand.goodbuddy.record.presenter.RecordPresenter;
import com.demand.goodbuddy.repository.UserController;
import com.demand.goodbuddy.repository.interceptor.HeaderInterceptor;
import com.demand.goodbuddy.util.ErrorUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public class RecordInteractorImpl implements RecordInteractor {
    private static final Logger logger = LoggerFactory.getLogger(RecordInteractorImpl.class);
    private RecordPresenter recordPresenter;

    private UserController userController;

    public RecordInteractorImpl(RecordPresenter recordPresenter) {
        this.recordPresenter = recordPresenter;
    }

    @Override
    public void setBloodPressure(User user, BloodPressure bloodPressure) {
        int userId = user.getId();
        String accessToken = user.getAccessToken();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<ResponseBody> callInsertBloodPressure = userController.insertBloodPressure(userId, bloodPressure);
        callInsertBloodPressure.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    recordPresenter.onSuccessSetBloodPressure();
                } else {
                    recordPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                log(t);
                recordPresenter.onNetworkError(null);
            }
        });

    }

    @Override
    public void setBloodSugar(User user, BloodSugar bloodSugar) {
        int userId = user.getId();
        String accessToken = user.getAccessToken();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<ResponseBody> callInsertBloodPressure = userController.insertBloodSugar(userId, bloodSugar);
        callInsertBloodPressure.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    recordPresenter.onSuccessSetBloodSugar();
                } else {
                    recordPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                log(t);
                recordPresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public void setBmi(User user, Bmi bmi) {
        int userId = user.getId();
        String accessToken = user.getAccessToken();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<ResponseBody> callInsertBloodPressure = userController.insertBmi(userId, bmi);
        callInsertBloodPressure.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    recordPresenter.onSuccessSetBloodBmi();
                } else {
                    recordPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                log(t);
                recordPresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public void getDate(User user, final int flag) {
        int userId = user.getId();
        String accessToken = user.getAccessToken();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<Integer> callSelectDateCheck = userController.selectDateCheck(userId, flag);
        callSelectDateCheck.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    int checkedDate = response.body();
                    recordPresenter.onSuccessGetDate(flag, checkedDate);
                } else {
                    recordPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                log(t);
                recordPresenter.onNetworkError(null);
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
