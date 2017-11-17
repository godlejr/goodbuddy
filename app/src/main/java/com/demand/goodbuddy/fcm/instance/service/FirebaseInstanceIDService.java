package com.demand.goodbuddy.fcm.instance.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.demand.goodbuddy.flag.LogFlag;
import com.demand.goodbuddy.repository.UserController;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private UserController userController;
    private SharedPreferences loginInfo;

    private static final Logger logger = LoggerFactory.getLogger(FirebaseInstanceIdService.class);

    //user info
    private int userId;
    private String userEmail;
    private String userName;
    private String userBirth;
    private int userLevel;
    private String userAvatar;
    private String deviceId;
    private String accessToken;
    private int userGender;

    private Context context;

    @Override
    public void onCreate() {
        context = this;

        loginInfo = getSharedPreferences("loginInfo", Activity.MODE_PRIVATE);
        userId = loginInfo.getInt("id", 0);
        userLevel = loginInfo.getInt("level", 0);
        userName = loginInfo.getString("name", null);
        userEmail = loginInfo.getString("email", null);
        userBirth = loginInfo.getString("birth", null);
        userAvatar = loginInfo.getString("avatar", null);
        deviceId = loginInfo.getString("deviceId", null);
        accessToken = loginInfo.getString("accessToken", null);
        userGender = loginInfo.getInt("gender", 0);

        Log.e("service user", userId + "");
        Log.e("service", userId + "");
    }

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();

        Log.e("service", token);
//         sendRegistrationToServer(token);
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
