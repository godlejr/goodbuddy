package com.demand.goodbuddy.receiver.pedometer.presenter.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.receiver.pedometer.helper.SQLiteOpenHelper;
import com.demand.goodbuddy.receiver.pedometer.interactor.PedometerReceiverInteractor;
import com.demand.goodbuddy.receiver.pedometer.interactor.impl.PedometerReceiverInteractorImpl;
import com.demand.goodbuddy.receiver.pedometer.presenter.PedometerReceiverPresenter;
import com.demand.goodbuddy.receiver.pedometer.view.PedometerReceiverView;
import com.demand.goodbuddy.util.APIErrorUtil;
import com.demand.goodbuddy.util.PreferenceUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public class PedometerReceiverPresenterImpl implements PedometerReceiverPresenter {
    private PedometerReceiverInteractor pedometerReceiverInteractor;
    private PedometerReceiverView pedometerReceiverView;
    private PreferenceUtil preferenceUtil;

//    private SQLiteOpenHelper sqLiteOpenHelper;
//    private SQLiteDatabase sqLiteDatabase;

    public PedometerReceiverPresenterImpl(PedometerReceiverView pedometerReceiverView, Context context) {
        this.pedometerReceiverInteractor = new PedometerReceiverInteractorImpl(this);
        this.pedometerReceiverView = pedometerReceiverView;
        this.preferenceUtil = new PreferenceUtil(context);
    }

    @Override
    public void onCreate(Context context) {
        pedometerReceiverInteractor.setContext(context);
        setDBInit();
        setActiveMass();
    }

    @Override
    public void onNetworkError(APIErrorUtil apiErrorUtil) {
        Context context = pedometerReceiverInteractor.getContext();
        if (apiErrorUtil == null) {
            pedometerReceiverView.showMessage("네트워크 불안정합니다. 다시 시도하세요.", context);
        } else {
            pedometerReceiverView.showMessage(apiErrorUtil.message(), context);
        }
    }

    @Override
    public void setActiveMass() {
        Context context = pedometerReceiverInteractor.getContext();
//        int activeMass = 0;
//        Cursor cursor = sqLiteDatabase.rawQuery("select * from pedometer", null);
//        if (cursor.moveToFirst()) {
//            activeMass = cursor.getInt(1);
//        }

        int count = preferenceUtil.getPedometer();


        User user = preferenceUtil.getUserInfoForService(context);
        pedometerReceiverInteractor.setActiveMass(user, count);
    }


    @Override
    public void onSuccessSetActiveMass() {
        setCountReset();
    }

    @Override
    public void setCountReset() {
        this.preferenceUtil.setPedometer(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        String tomorrow = simpleDateFormat.format(calendar.getTime());
        preferenceUtil.setPedometerDate(tomorrow);
        Log.e("pedometer service","만보기 데이터 넣음");
    }

    @Override
    public void setDBInit() {
//        Context context = pedometerReceiverInteractor.getContext();

//        sqLiteOpenHelper = new SQLiteOpenHelper(context);
//        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();

        Context context = pedometerReceiverInteractor.getContext();
        this.preferenceUtil = new PreferenceUtil(context);
    }

}
