package com.demand.goodbuddy.record.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.dto.BloodPressure;
import com.demand.goodbuddy.dto.BloodSugar;
import com.demand.goodbuddy.dto.Bmi;
import com.demand.goodbuddy.record.presenter.RecordPresenter;
import com.demand.goodbuddy.record.presenter.impl.RecordPresenterImpl;
import com.demand.goodbuddy.record.view.RecordView;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public class RecordActivity extends Activity implements RecordView, View.OnClickListener {
    private RecordPresenter recordPresenter;

    private EditText et_record_minbloodpressure;
    private EditText et_record_maxbloodpressure;
    private EditText et_record_bloodsugar;
    private EditText et_record_height;
    private EditText et_record_weight;
    private Button btn_record_submitpressure;
    private Button btn_record_submitbloodsugar;
    private Button btn_record_submitbmi;

    //toolbar
    private View decorView;
    private Toolbar toolbar;
    private ImageView toolbar_back;
    private TextView toolbar_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        recordPresenter = new RecordPresenterImpl(this);
        recordPresenter.onCreate();
    }

    @Override
    public void init() {
        et_record_minbloodpressure = (EditText) findViewById(R.id.et_record_minbloodpressure);
        et_record_maxbloodpressure = (EditText) findViewById(R.id.et_record_maxbloodpressure);
        et_record_bloodsugar = (EditText) findViewById(R.id.et_record_bloodsugar);
        et_record_height = (EditText) findViewById(R.id.et_record_height);
        et_record_weight = (EditText) findViewById(R.id.et_record_weight);

        btn_record_submitpressure = (Button) findViewById(R.id.btn_record_submitpressure);
        btn_record_submitbloodsugar = (Button) findViewById(R.id.btn_record_submitbloodsugar);
        btn_record_submitbmi = (Button) findViewById(R.id.btn_record_submitbmi);

        btn_record_submitpressure.setOnClickListener(this);
        btn_record_submitbloodsugar.setOnClickListener(this);
        btn_record_submitbmi.setOnClickListener(this);
    }

    @Override
    public void showToolbarTitle(String title) {
        toolbar_title.setText(title);
    }

    @Override
    public void setToolbar(View decorView) {
        toolbar = (Toolbar) decorView.findViewById(R.id.toolBar);
        toolbar_back = (ImageView) toolbar.findViewById(R.id.toolbar_back);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_back.setOnClickListener(this);
    }

    @Override
    public View getDecorView() {
        if (decorView == null) {
            decorView = this.getWindow().getDecorView();
        }
        return decorView;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;

            case R.id.btn_record_submitpressure:
                recordPresenter.onClickSetBloodPressure();
                break;

            case R.id.btn_record_submitbloodsugar:
                recordPresenter.onClickSetBloodSugar();
                break;

            case R.id.btn_record_submitbmi:
                recordPresenter.onClickSetBmi();
                break;
        }
    }

    @Override
    public void setBloodPressureInsertSuccessButton() {
        btn_record_submitpressure.setText("완료");
        btn_record_submitpressure.setClickable(false);
        et_record_minbloodpressure.setFocusable(false);
        et_record_maxbloodpressure.setFocusable(false);
    }

    @Override
    public void setBloodSugarInsertSuccessButton() {
        btn_record_submitbloodsugar.setText("완료");
        btn_record_submitbloodsugar.setClickable(false);
        et_record_bloodsugar.setFocusable(false);
    }

    @Override
    public void setBmiInsertSuccessButton() {
        btn_record_submitbmi.setText("완료");
        btn_record_submitbmi.setClickable(false);
        et_record_weight.setFocusable(false);
        et_record_height.setFocusable(false);
    }

    @Override
    public void setBloodPressure() {
        String minData = et_record_minbloodpressure.getText().toString();
        String maxData = et_record_maxbloodpressure.getText().toString();
        recordPresenter.setBloodPressure(minData, maxData);

    }

    @Override
    public void setBloodSugar() {
        String data = et_record_bloodsugar.getText().toString();
        recordPresenter.setBloodSugar(data);
    }

    @Override
    public void setBmi() {
        String height = et_record_height.getText().toString();
        String weight = et_record_weight.getText().toString();
        recordPresenter.setBmi(height, weight);
    }


}
