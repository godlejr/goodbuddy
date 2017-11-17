package com.demand.goodbuddy.login.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.login.create.activity.JoinActivity;
import com.demand.goodbuddy.main.base.activity.MainActivity;
import com.demand.goodbuddy.login.base.presenter.LoginPresenter;
import com.demand.goodbuddy.login.base.presenter.impl.LoginPresenterImpl;
import com.demand.goodbuddy.login.base.view.LoginView;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {
    private LoginPresenter loginPresenter;
    private EditText et_login_email;
    private EditText et_login_password;
    private Button btn_login;
    private TextView tv_login_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
    }

    @Override
    public void init() {
        et_login_email = (EditText) findViewById(R.id.et_login_email);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_login_join = (TextView)findViewById(R.id.tv_login_join);

        btn_login.setOnClickListener(this);
        tv_login_join.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String email = et_login_email.getText().toString();
                String password = et_login_password.getText().toString();

                loginPresenter.onClickLogin(email, password);
                break;

            case R.id.tv_login_join:
                loginPresenter.onClickJoin();
                break;
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getDeviceId() {
        return android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID).toString();
    }

    @Override
    public String getFireBaseToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToJoinActivity() {
        Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
        startActivity(intent);
    }
}
