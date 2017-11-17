package com.demand.goodbuddy.pedometer.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.etc.manbogiflag;
import com.demand.goodbuddy.pedometer.presenter.PedometerPresenter;
import com.demand.goodbuddy.pedometer.presenter.impl.PedometerPresenterImpl;
import com.demand.goodbuddy.pedometer.view.PedometerView;
import com.demand.goodbuddy.receiver.pedometer.receiver.PedometerReceiver;
import com.demand.goodbuddy.service.pedometer.service.PedometerService;
import com.demand.goodbuddy.util.PreferenceUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ㅇㅇ on 2017-05-04.
 */

public class PedometerActivity extends Activity implements PedometerView, View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private PedometerPresenter pedometerPresenter;
    private PreferenceUtil preferenceUtil;

    //toolbar
    private View decorView;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private TextView tv_pedometer_count;
    private ImageView toolbarBack;

    private PedometerHandler pedometerHandler;

    private Button btnDisconnection;
    private Button btnConnection;
    private SeekBar sb_pedometer_sensitivity;
    private TextView tv_pedometer_sensitivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);

        this.preferenceUtil = new PreferenceUtil(this);
        this.pedometerHandler = new PedometerHandler(this);
        pedometerPresenter = new PedometerPresenterImpl(this);
        pedometerPresenter.onCreate();
    }

    @Override
    public void init() {
        btnDisconnection = (Button) findViewById(R.id.btn_pedometer_disconnection);
        btnConnection = (Button) findViewById(R.id.btn_pedometer_connection);
        tv_pedometer_sensitivity = (TextView) findViewById(R.id.tv_pedometer_sensitivity);
        sb_pedometer_sensitivity = (SeekBar) findViewById(R.id.sb_pedometer_sensitivity);

        btnDisconnection.setOnClickListener(this);
        btnConnection.setOnClickListener(this);
        sb_pedometer_sensitivity.setOnSeekBarChangeListener(this);

        pedometerPresenter.setPedometerService((ActivityManager) PedometerActivity.this.getSystemService(Activity.ACTIVITY_SERVICE));

    }

    @Override
    public View getDecorView() {
        if (decorView == null) {
            decorView = this.getWindow().getDecorView();
        }
        return decorView;
    }

    @Override
    public void setToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolBar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarBack = (ImageView) toolbar.findViewById(R.id.toolbar_back);
        toolbarBack.setOnClickListener(this);
    }

    @Override
    public void showToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(PedometerActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCount(String count) {
        tv_pedometer_count.setText(count);
    }

    @Override
    public void pedometerMessageSender(int count) {
        Message message = new Message();
        Bundle bundle = new Bundle();

        bundle.putString("count", String.valueOf(count));

        message.setData(bundle);

        pedometerHandler.sendMessage(message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pedometer_connection:
                pedometerPresenter.onStartService();
                break;

            case R.id.btn_pedometer_disconnection:
                pedometerPresenter.onStopService();
                break;

            case R.id.toolbar_back:
                finish();
                break;
        }
    }

    @Override
    public void showSeekBarProgress(int progress) {
        sb_pedometer_sensitivity.setProgress(progress);
    }

    @Override
    public void showStartButton() {
        btnConnection.setVisibility(View.VISIBLE);
    }

    @Override
    public void showStopButton() {
        btnDisconnection.setVisibility(View.VISIBLE);
    }

    @Override
    public void goneStartButton() {
        btnConnection.setVisibility(View.GONE);
    }

    @Override
    public void goneStopButton() {
        btnDisconnection.setVisibility(View.GONE);
    }

    @Override
    public void setServiceStart() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        String today = simpleDateFormat.format(calendar.getTime());
        preferenceUtil.setPedometerDate(today);
        Log.e("dsafasdf", "만보기 시작");

        Intent pedometerIntent = new Intent(PedometerActivity.this, PedometerService.class);
        startService(pedometerIntent);
    }

    @Override
    public void setAlarmManagerCanceled() {
        Intent pedometerIntent = new Intent(PedometerActivity.this, PedometerService.class);
        AlarmManager alarmManager = (AlarmManager) PedometerActivity.this.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pIntent = PendingIntent.getBroadcast(PedometerActivity.this, 0, pedometerIntent, 0);
        alarmManager.cancel(pIntent);
    }

    @Override
    public void setServiceTerminated() {
        manbogiflag.setIsPlaying(false);
        preferenceUtil.setPedometerPlay(false);
        showMessage("만보기가 종료되었습니다.");
//
//        Intent pedometerIntent = new Intent(PedometerActivity.this, PedometerService.class);
//       pedometerIntent.putExtra("status", false);
//
//        stopService(pedometerIntent);
//        setAlarmManagerCanceled();
//        finish();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        pedometerPresenter.onProgressChanged(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void showPedometerSpeed(int level) {
        tv_pedometer_sensitivity.setText(String.valueOf(level));
    }

    private class PedometerHandler extends Handler {
        private PedometerView pedometerView;

        public PedometerHandler(PedometerView pedometerView) {
            this.pedometerView = pedometerView;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String count = bundle.getString("count");

            pedometerView.showCount(count);
        }
    }

}
