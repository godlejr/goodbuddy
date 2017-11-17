package com.demand.goodbuddy.pedometer.presenter.impl;

import android.app.ActivityManager;
import android.content.Context;
import android.view.View;

import com.demand.goodbuddy.etc.manbogiflag;
import com.demand.goodbuddy.pedometer.interactor.PedometerInteractor;
import com.demand.goodbuddy.pedometer.interactor.impl.PedometerInteractorImpl;
import com.demand.goodbuddy.pedometer.presenter.PedometerPresenter;
import com.demand.goodbuddy.pedometer.view.PedometerView;
import com.demand.goodbuddy.util.PreferenceUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public class PedometerPresenterImpl implements PedometerPresenter {
    private PedometerInteractor pedometerInteractor;
    private PedometerView pedometerView;
    private PreferenceUtil preferenceUtil;


    public PedometerPresenterImpl(Context context) {
        this.pedometerInteractor = new PedometerInteractorImpl(this);
        this.pedometerView = (PedometerView) context;
        this.preferenceUtil = new PreferenceUtil(context);
    }

    @Override
    public void onCreate() {
        pedometerView.init();

        View decorView = pedometerView.getDecorView();
        pedometerView.setToolbar(decorView);
        pedometerView.showToolbarTitle("만보기 연동");

        int level = preferenceUtil.getPedometerLevel();
        pedometerView.showPedometerSpeed(level + 1);
        pedometerView.showSeekBarProgress(level);
    }

    @Override
    public void setPedometerService(ActivityManager manager) {
        if (preferenceUtil.getPedometerisFirst()) {
            pedometerView.setServiceStart();

            preferenceUtil.setPedometerFirst(false);
            manbogiflag.setIsPlaying(true);
            preferenceUtil.setPedometerPlay(true);
        } else {
            if (manbogiflag.isPlaying()) {
                pedometerView.showStopButton();
                pedometerView.goneStartButton();
            } else {
                pedometerView.showStartButton();
                pedometerView.goneStopButton();
            }
        }
    }

    @Override
    public void setCountReset() {
        preferenceUtil.setActiveMass(0);
    }

    @Override
    public void onStopService() {
        pedometerView.setServiceTerminated();
        preferenceUtil.setPedometerPlay(false);
        pedometerView.showStartButton();
        pedometerView.goneStopButton();
    }

    @Override
    public void onStartService() {
        if (preferenceUtil.getPedometerisFirst()) {
            pedometerView.setServiceStart();
            preferenceUtil.setPedometerFirst(false);
        } else {
            manbogiflag.setIsPlaying(true);
            preferenceUtil.setPedometerPlay(true);
        }

        pedometerView.showStopButton();
        pedometerView.goneStartButton();

    }

    @Override
    public void onProgressChanged(int progress) {
        preferenceUtil.setPedometerSpeed(progress);
        pedometerView.showPedometerSpeed(progress + 1);
    }

}
