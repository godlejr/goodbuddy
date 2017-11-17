package com.demand.goodbuddy.diet.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.demand.goodbuddy.diet.adapter.DietAdapter;
import com.demand.goodbuddy.dto.DiagnosisCategory;
import com.demand.goodbuddy.dto.DietDiagnosisCategory;

import java.util.ArrayList;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public interface DietView {
    void init();

    void setToolbar(View decorView);

    View getDecorView();

    void showToolbarTitle(String title);

    void showMessage(String message);

    void setDietDiagnosisCategoryList(ArrayList<DietDiagnosisCategory> dietDiagnosisCategory);

    void setDiagnosisCategoryList(ArrayList<DiagnosisCategory> diagnosisCategory);

    void setDietAdapter();

    void showDiagnosisCategoryList(DietAdapter.DietViewHolder holder, int position);

    void navigateToBack();
}
