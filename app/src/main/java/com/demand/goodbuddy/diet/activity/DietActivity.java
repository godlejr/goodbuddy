package com.demand.goodbuddy.diet.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.diet.adapter.DietAdapter;
import com.demand.goodbuddy.diet.presenter.DietPresenter;
import com.demand.goodbuddy.diet.presenter.impl.DietPresenterImpl;
import com.demand.goodbuddy.diet.view.DietView;
import com.demand.goodbuddy.dto.DiagnosisCategory;
import com.demand.goodbuddy.dto.DietDiagnosisCategory;

import java.util.ArrayList;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public class DietActivity extends Activity implements DietView, View.OnClickListener {
    private DietPresenter dietPresenter;

    private RecyclerView recyclerView;
    private View decorView;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView toolbarBack;

    private DietAdapter dietAdapter;
    private Button diagnosisSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        dietPresenter = new DietPresenterImpl(this);
        dietPresenter.onCreate();
    }

    @Override
    public void init() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_diet);
        recyclerView.setLayoutManager(new LinearLayoutManager(DietActivity.this, LinearLayoutManager.VERTICAL, false));
        dietPresenter.setDietDiagnosisCategoryList();

        diagnosisSubmitBtn = (Button) findViewById(R.id.btn_diet_submit);
        diagnosisSubmitBtn.setOnClickListener(this);
    }

    @Override
    public void setToolbar(View decorView) {
        toolbar = (Toolbar) decorView.findViewById(R.id.toolBar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarBack = (ImageView) toolbar.findViewById(R.id.toolbar_back);
        toolbarBack.setOnClickListener(this);
    }

    @Override
    public View getDecorView() {
        if (decorView == null) {
            decorView = this.getWindow().getDecorView();
        }
        return decorView;
    }

    @Override
    public void showToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(DietActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDietDiagnosisCategoryList(ArrayList<DietDiagnosisCategory> dietDiagnosisCategoryList) {
        dietAdapter = new DietAdapter(dietDiagnosisCategoryList, DietActivity.this, dietPresenter);
        dietPresenter.setDiagnosisCategoryList();
    }

    @Override
    public void setDiagnosisCategoryList(ArrayList<DiagnosisCategory> diagnosisCategory) {
        dietAdapter.setDiagnosisCategoryList(diagnosisCategory);
    }

    @Override
    public void showDiagnosisCategoryList(DietAdapter.DietViewHolder holder, int position) {
        dietAdapter.setDiagnosisCategory(holder, position);
    }

    @Override
    public void setDietAdapter() {
        recyclerView.setAdapter(dietAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.btn_diet_submit:
                dietPresenter.onClickDiagnosisSubmit();
                break;
        }
    }

    @Override
    public void navigateToBack() {
        finish();
    }
}
