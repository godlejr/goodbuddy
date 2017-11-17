package com.demand.goodbuddy.login.create.interactor.impl;

import com.demand.goodbuddy.flag.LogFlag;
import com.demand.goodbuddy.login.create.interactor.JoinInteractor;
import com.demand.goodbuddy.login.create.presenter.JoinPresenter;
import com.demand.goodbuddy.repository.MainController;
import com.demand.goodbuddy.repository.interceptor.HeaderInterceptor;
import com.demand.goodbuddy.util.ErrorUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public class JoinInteractorImpl implements JoinInteractor {
    private JoinPresenter joinPresenter;
    private MainController mainController;
    private boolean isEmailChecked;

    private static final Logger logger = LoggerFactory.getLogger(JoinInteractorImpl.class);

    public JoinInteractorImpl(JoinPresenter joinPresenter) {
        this.joinPresenter = joinPresenter;
    }

    @Override
    public void setMainController() {
        this.mainController = new HeaderInterceptor().getClientForMainServer().create(MainController.class);
        this.isEmailChecked = false;
    }

    @Override
    public boolean isEmailChecked() {
        return isEmailChecked;
    }

    @Override
    public void setEmailChecked(boolean emailChecked) {
        isEmailChecked = emailChecked;
    }

    @Override
    public void getEmailCheck(String email) {
        Call<Boolean> callEmailCheck = mainController.emailCheck(email);
        callEmailCheck.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    joinPresenter.onSuccessGetEmailCheck(response.body());
                } else {
                    joinPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                log(t);
                joinPresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public boolean getEmailValidation(String email) {
        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean check = matcher.matches();
        return check;
    }

    @Override
    public void setJoin(String email, String password, String name, String birth, int gender) {
        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        map.put("name", name);
        map.put("birth", birth);
        map.put("gender", String.valueOf(gender));

        Call<ResponseBody> callSetJoin = mainController.join(map);
        callSetJoin.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    joinPresenter.onSuccessSetJoin();
                } else {
                    joinPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                log(t);
                joinPresenter.onNetworkError(null);
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
