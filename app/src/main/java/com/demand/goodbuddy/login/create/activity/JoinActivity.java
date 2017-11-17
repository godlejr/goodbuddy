package com.demand.goodbuddy.login.create.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.login.base.activity.LoginActivity;
import com.demand.goodbuddy.login.create.presenter.JoinPresenter;
import com.demand.goodbuddy.login.create.presenter.impl.JoinPresenterImpl;
import com.demand.goodbuddy.login.create.view.JoinView;
import com.demand.goodbuddy.main.agreement.AgreementActivity;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public class JoinActivity extends Activity implements JoinView, View.OnClickListener {
    private JoinPresenter joinPresenter;

    private EditText et_join_email;
    private EditText et_join_password;
    private EditText et_join_passwordconfirm;
    private EditText et_join_name;
    private EditText et_join_birth;
    private TextView tv_join_agreement;

    private Button btn_join_emailcheck;
    private Button btn_join_submit;
    private RadioGroup rg_join_gender;

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView toolbarBack;
    private View decorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        this.joinPresenter = new JoinPresenterImpl(this);
        this.joinPresenter.init();
    }

    @Override
    public void init() {
        et_join_email = (EditText) findViewById(R.id.et_join_email);
        et_join_password = (EditText) findViewById(R.id.et_join_password);
        et_join_passwordconfirm = (EditText) findViewById(R.id.et_join_passwordconfirm);
        et_join_name = (EditText) findViewById(R.id.et_join_name);
        et_join_birth = (EditText) findViewById(R.id.et_join_birth);

        btn_join_emailcheck = (Button)findViewById(R.id.btn_join_emailcheck);
        btn_join_submit = (Button)findViewById(R.id.btn_join_submit);
        rg_join_gender = (RadioGroup)findViewById(R.id.rg_join_gender);

        et_join_birth.setOnClickListener(this);
        btn_join_emailcheck.setOnClickListener(this);
        btn_join_submit.setOnClickListener(this);

        tv_join_agreement = (TextView)findViewById(R.id.tv_join_agreement);
        tv_join_agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AgreementActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_join_birth:
                joinPresenter.onClickBirth();
                break;

            case R.id.btn_join_emailcheck:
                String email = et_join_email.getText().toString();
                joinPresenter.onClickEmailCheck(email);
                break;

            case R.id.btn_join_submit:
                email = et_join_email.getText().toString();
                String password = et_join_password.getText().toString();
                String passwordConfirm = et_join_passwordconfirm.getText().toString();
                String name = et_join_name.getText().toString();
                String birth = et_join_birth.getText().toString();

                int radioButtonId = rg_join_gender.getCheckedRadioButtonId();
                View radioButton = rg_join_gender.findViewById(radioButtonId);
                int gender = rg_join_gender.indexOfChild(radioButton);

                joinPresenter.onClickJoin(email, password, passwordConfirm, name, birth, gender);
                break;

            case R.id.toolbar_back:
                joinPresenter.onClickBack();
                break;

        }
    }

    @Override
    public void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String birth = String.valueOf(year) + "-" + String.valueOf((month + 1)) + "-" + String.valueOf(dayOfMonth);
                joinPresenter.showUserBirth(birth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH));

        datePicker.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        datePicker.show();
    }

    @Override
    public void showUserBirth(String message) {
        et_join_birth.setText(message);
    }

    @Override
    public void showToolbarTitle(String message) {
        toolbarTitle.setText(message);
    }

    @Override
    public void setToolbar() {
        if (decorView == null) {
            decorView = this.getWindow().getDecorView();
        }

        toolbar = (Toolbar) decorView.findViewById(R.id.toolBar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarBack = (ImageView) toolbar.findViewById(R.id.toolbar_back);
        toolbarBack.setOnClickListener(this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmailText(String message) {
        et_join_email.setText(message);
    }

    @Override
    public void navigateToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToBack() {
        finish();
    }
}
