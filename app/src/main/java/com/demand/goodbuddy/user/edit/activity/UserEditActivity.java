package com.demand.goodbuddy.user.edit.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.user.edit.presenter.UserEditPresenter;
import com.demand.goodbuddy.user.edit.presenter.impl.UserEditPresenterImpl;
import com.demand.goodbuddy.user.edit.view.UserEditView;
import com.demand.goodbuddy.util.PreferenceUtil;

import java.util.Calendar;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public class UserEditActivity extends Activity implements UserEditView, View.OnClickListener {
    private UserEditPresenter userEditPresenter;
    private PreferenceUtil preferenceUtil;

    private EditText et_edituser_name;
    private EditText et_edituser_password;
    private EditText et_edituser_passwordconfirm;
    private EditText et_edituser_birth;

    private RadioGroup rg_edituser_gender;
    private RadioButton rb_edituser_man;
    private RadioButton rb_edituser_female;

    private Button btn_edituser_submit;
    private Button btn_edituser_password;

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView toolbarBack;
    private View decorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituser);

        this.preferenceUtil= new PreferenceUtil(this);
        User user = preferenceUtil.getUserInfo();

        this.userEditPresenter = new UserEditPresenterImpl(this);
        this.userEditPresenter.init(user);
    }

    @Override
    public void init(){
        et_edituser_name = (EditText)findViewById(R.id.et_edituser_name);
        et_edituser_password = (EditText)findViewById(R.id.et_edituser_password);
        et_edituser_passwordconfirm = (EditText)findViewById(R.id.et_edituser_passwordconfirm);
        et_edituser_birth = (EditText)findViewById(R.id.et_edituser_birth);
        rg_edituser_gender = (RadioGroup)findViewById(R.id.rg_edituser_gender);
        rb_edituser_man = (RadioButton)findViewById(R.id.rb_edituser_man);
        rb_edituser_female = (RadioButton)findViewById(R.id.rb_edituser_female);
        btn_edituser_submit= (Button)findViewById(R.id.btn_edituser_submit);
        btn_edituser_password = (Button)findViewById(R.id.btn_edituser_password);

        btn_edituser_submit.setOnClickListener(this);
        btn_edituser_password.setOnClickListener(this);
    }

    @Override
    public void showUserName(String message){
        et_edituser_name.setText(message);
    }

    @Override
    public void showUserBirth(String message){
        et_edituser_birth.setText(message);
    }

    @Override
    public void setRadioButtonCheckForMan(){
        rb_edituser_man.setChecked(true);
    }

    @Override
    public void setRadioButtonCheckForFemale(){
        rb_edituser_female.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_edituser_birth:
                userEditPresenter.onClickBirth();
                break;

            case R.id.btn_edituser_submit:
                String name = et_edituser_name.getText().toString();
                String birth = et_edituser_birth.getText().toString();

                int radioButtonId = rg_edituser_gender.getCheckedRadioButtonId();
                View radioButton = rg_edituser_gender.findViewById(radioButtonId);
                int gender = rg_edituser_gender.indexOfChild(radioButton);

                userEditPresenter.onClickEditProfile(name, birth, gender);

                break;
            case R.id.btn_edituser_password:
                String password = et_edituser_password.getText().toString();
                String passwordConfirm = et_edituser_passwordconfirm.getText().toString();

                userEditPresenter.onClickEditPassword(password, passwordConfirm);
                break;

            case R.id.toolbar_back:
                userEditPresenter.onClickBack();
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
                userEditPresenter.showUserBirth(birth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH));

        datePicker.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        datePicker.show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
    public void navigateToBack() {
        finish();
    }

    @Override
    public void setUser(User user) {
        preferenceUtil.setUserInfo(user, user.getDeviceId(), user.getFcmToken(), user.getAccessToken());
    }
}
