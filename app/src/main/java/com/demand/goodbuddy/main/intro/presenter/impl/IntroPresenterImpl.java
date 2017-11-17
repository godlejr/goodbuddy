package com.demand.goodbuddy.main.intro.presenter.impl;

import android.Manifest;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.PermissionFlag;
import com.demand.goodbuddy.main.intro.interactor.IntroInteractor;
import com.demand.goodbuddy.main.intro.interactor.impl.IntroInteractorImpl;
import com.demand.goodbuddy.main.intro.presenter.IntroPresenter;
import com.demand.goodbuddy.main.intro.view.IntroView;
import com.demand.goodbuddy.util.APIErrorUtil;
import com.demand.goodbuddy.util.PreferenceUtil;

/**
 * Created by ㅇㅇ on 2017-04-24.
 */

public class IntroPresenterImpl implements IntroPresenter {
    private IntroView introView;
    private IntroInteractor introInteractor;
    private PreferenceUtil preferenceUtil;

    public IntroPresenterImpl(Context context) {
        this.introView = (IntroView) context;
        this.introInteractor = new IntroInteractorImpl(this);
        this.preferenceUtil = new PreferenceUtil(context);
    }

    @Override
    public void onCreate() {
        introView.init();
    }

    @Override
    public void validateUserExist() {
        User user = preferenceUtil.getUserInfo();
        int userId = user.getId();

        if (userId != 0) {
            introInteractor.getMultipleUserAccessValidation(user);
        } else {
            introView.navigateToLoginActivity();
        }

    }

    @Override
    public void onSuccessMultipleUserAccessValidation(int check) {
        if (check > 0) {
            introView.navigateToMainActivity();
        } else {
            introView.showMessage("다른 기기에서 접속중입니다. 로그인 시 다른기기에서 강제 로그아웃 됩니다.");
            preferenceUtil.removeUserInfo();
            introView.navigateToLoginActivity();
        }
    }


    @Override
    public void onNetworkError(APIErrorUtil apiErrorUtil) {
        if (apiErrorUtil == null) {
            introView.showMessage("네트워크 불안정합니다. 다시 시도하세요.");
        } else {
            introView.showMessage(apiErrorUtil.message());
        }
    }
}
