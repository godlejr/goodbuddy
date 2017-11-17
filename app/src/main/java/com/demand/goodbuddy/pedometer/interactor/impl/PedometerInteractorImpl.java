package com.demand.goodbuddy.pedometer.interactor.impl;

import com.demand.goodbuddy.pedometer.interactor.PedometerInteractor;
import com.demand.goodbuddy.pedometer.presenter.PedometerPresenter;
import com.demand.goodbuddy.receiver.pedometer.receiver.PedometerReceiver;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public class PedometerInteractorImpl implements PedometerInteractor {
    private PedometerPresenter pedometerPresenter;

    private PedometerReceiver pedometerReceiver;

    private Boolean status;

    public PedometerInteractorImpl(PedometerPresenter pedometerPresenter) {
        this.pedometerPresenter = pedometerPresenter;
    }

    @Override
    public PedometerReceiver getPedometerReceiver() {
        return this.pedometerReceiver;
    }

    @Override
    public void setPedometerReceiver(PedometerReceiver pedometerReceiver) {
        this.pedometerReceiver = pedometerReceiver;
    }

    @Override
    public boolean isStatus() {
        return status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }
}
