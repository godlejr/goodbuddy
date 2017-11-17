package com.demand.goodbuddy.main.intro.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.flag.PermissionFlag;
import com.demand.goodbuddy.main.base.activity.MainActivity;
import com.demand.goodbuddy.main.intro.presenter.IntroPresenter;
import com.demand.goodbuddy.main.intro.presenter.impl.IntroPresenterImpl;
import com.demand.goodbuddy.main.intro.view.IntroView;
import com.demand.goodbuddy.login.base.activity.LoginActivity;

/**
 * Created by ㅇㅇ on 2017-04-24.
 */

public class IntroActivity extends Activity implements IntroView {
    private IntroPresenter introPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        introPresenter = new IntroPresenterImpl(this);
        introPresenter.onCreate();
    }

    @Override
    public void init() {
        setPermission();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                introPresenter.validateUserExist();
            }
        }, 2000);
    }

    @Override
    public void navigateToLoginActivity() {
        Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPermission() {
        ActivityCompat.requestPermissions(IntroActivity.this, new String[]{Manifest.permission.INTERNET}, PermissionFlag.INTERNET);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(IntroActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
