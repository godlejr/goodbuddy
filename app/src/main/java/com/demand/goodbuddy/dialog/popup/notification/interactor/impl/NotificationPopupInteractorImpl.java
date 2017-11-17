package com.demand.goodbuddy.dialog.popup.notification.interactor.impl;


import com.demand.goodbuddy.dialog.popup.notification.interactor.NotificationPopupInteractor;
import com.demand.goodbuddy.dialog.popup.notification.presenter.NotificationPopupPresenter;

/**
 * Created by ㅇㅇ on 2017-04-19.
 */

public class NotificationPopupInteractorImpl implements NotificationPopupInteractor {
    private NotificationPopupPresenter notificationPopupPresenter;

    public NotificationPopupInteractorImpl(NotificationPopupPresenter notificationPopupPresenter) {
        this.notificationPopupPresenter = notificationPopupPresenter;
    }
}
