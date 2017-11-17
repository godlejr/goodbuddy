package com.demand.goodbuddy.receiver.pedometer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.demand.goodbuddy.receiver.pedometer.presenter.PedometerReceiverPresenter;
import com.demand.goodbuddy.receiver.pedometer.presenter.impl.PedometerReceiverPresenterImpl;
import com.demand.goodbuddy.receiver.pedometer.view.PedometerReceiverView;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public class PedometerReceiver extends BroadcastReceiver implements PedometerReceiverView {
    private PedometerReceiverPresenter pedometerReceiverPresenter;

    @Override
    public void onReceive(Context context, Intent intent) {
        pedometerReceiverPresenter = new PedometerReceiverPresenterImpl(this, context);
        pedometerReceiverPresenter.onCreate(context);

        Log.e("ㅇㅇㅇㅇ", "리시버");
    }

    @Override
    public void showMessage(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
