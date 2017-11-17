package com.demand.goodbuddy.user.edit.interactor.impl;

import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.LogFlag;
import com.demand.goodbuddy.login.create.interactor.impl.JoinInteractorImpl;
import com.demand.goodbuddy.repository.MainController;
import com.demand.goodbuddy.repository.UserController;
import com.demand.goodbuddy.repository.interceptor.HeaderInterceptor;
import com.demand.goodbuddy.user.edit.interactor.UserEditInteractor;
import com.demand.goodbuddy.user.edit.presenter.UserEditPresenter;
import com.demand.goodbuddy.util.ErrorUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public class UserEditInteractorImpl implements UserEditInteractor {
    private UserEditPresenter userEditPresenter;
    private User user;
    private MainController mainController;
    private UserController userController;
    private static final Logger logger = LoggerFactory.getLogger(JoinInteractorImpl.class);


    public UserEditInteractorImpl(UserEditPresenter userEditPresenter) {
        this.userEditPresenter = userEditPresenter;
    }

    @Override
    public void setMainController() {
        this.mainController = new HeaderInterceptor().getClientForMainServer().create(MainController.class);
    }

    @Override
    public void setMainController(String accessToken) {
        this.mainController = new HeaderInterceptor(accessToken).getClientForMainServer().create(MainController.class);
    }

    @Override
    public void setUserController() {
        this.userController = new HeaderInterceptor().getClientForUserServer().create(UserController.class);
    }

    @Override
    public void setUserController(String accessToken) {
        this.userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
    }

    @Override
    public void updateUser(int userId, String name, String birth, int gender) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("birth", birth);
        map.put("gender", String.valueOf(gender));

        Call<User> callUpdateUser = userController.updateUser(userId, map);
        callUpdateUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    userEditPresenter.onSuccessUpdateUser(response.body());
                }else{
                    userEditPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                log(t);
                userEditPresenter.onNetworkError(null);
            }
        });

    }

    @Override
    public void updatePassword(int userId, String password) {
        Call<ResponseBody> callUpdateUser = userController.updatePassword(userId, password);
        callUpdateUser.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    userEditPresenter.onSuccessUpdatePassword();
                }else{
                    userEditPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                log(t);
                userEditPresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
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
