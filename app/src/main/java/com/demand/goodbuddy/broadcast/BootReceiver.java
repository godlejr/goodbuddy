package com.demand.goodbuddy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.demand.goodbuddy.etc.manbogiflag;
import com.demand.goodbuddy.service.pedometer.service.PedometerService;
import com.demand.goodbuddy.util.PreferenceUtil;

/**
 * Created by ㅇㅇ on 2017-10-11.
 */

public class BootReceiver extends BroadcastReceiver {

    private PreferenceUtil preferenceUtil;

    @Override
    public void onReceive(Context context, Intent intent) {
        preferenceUtil = new PreferenceUtil(context);

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

            preferenceUtil.setPedometerFirst(false);

            if(preferenceUtil.getPedometerPlay()){
                manbogiflag.setIsPlaying(true);
            }

            Log.e("ㅇㅇㅇ", "부트리시버");

            Intent i = new Intent(context, PedometerService.class);
            context.startService(i);

        }
    }
}

