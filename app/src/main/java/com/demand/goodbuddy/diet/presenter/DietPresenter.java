package com.demand.goodbuddy.diet.presenter;

import com.demand.goodbuddy.diet.adapter.DietAdapter;
import com.demand.goodbuddy.dto.DiagnosisCategory;
import com.demand.goodbuddy.dto.DietDiagnosisCategory;
import com.demand.goodbuddy.dto.DietSelfDiagnosis;
import com.demand.goodbuddy.util.APIErrorUtil;

import java.util.ArrayList;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public interface DietPresenter {
    void onCreate();

    void onNetworkError(APIErrorUtil apiErrorUtil);

    void setDietDiagnosisCategoryList();

    void setDiagnosisCategoryList();

    void setDiagnosisCategoryList(DietAdapter.DietViewHolder holder);

    void setDietDiagnosis();

    void onClickDiagnosisSubmit();

    void onCheckedDiagnosisCategory(DietSelfDiagnosis dietSelfDiagnosis);

    void onSuccessGetDietDiagnosisCategoryList(ArrayList<DietDiagnosisCategory> dietDiagnosisCategories);

    void onSuccessGetDiagnosisCategoryList(ArrayList<DiagnosisCategory> diagnosisCategoryList);

    void onSuccessSetDiagnosisData();

    void onSuccessGetDate(int checkedDate);
}
