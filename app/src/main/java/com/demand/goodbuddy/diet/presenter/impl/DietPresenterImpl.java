package com.demand.goodbuddy.diet.presenter.impl;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.demand.goodbuddy.diet.adapter.DietAdapter;
import com.demand.goodbuddy.diet.interactor.DietInteractor;
import com.demand.goodbuddy.diet.interactor.impl.DietInteractorImpl;
import com.demand.goodbuddy.diet.presenter.DietPresenter;
import com.demand.goodbuddy.diet.view.DietView;
import com.demand.goodbuddy.dto.DiagnosisCategory;
import com.demand.goodbuddy.dto.DietDiagnosisCategory;
import com.demand.goodbuddy.dto.DietSelfDiagnosis;
import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.InsertDataFlag;
import com.demand.goodbuddy.util.APIErrorUtil;
import com.demand.goodbuddy.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public class DietPresenterImpl implements DietPresenter {
    private DietView dietView;
    private DietInteractor dietInteractor;
    private PreferenceUtil preferenceUtil;

    public DietPresenterImpl(Context context) {
        this.dietView = (DietView) context;
        this.dietInteractor = new DietInteractorImpl(this);
        this.preferenceUtil = new PreferenceUtil(context);
    }

    @Override
    public void onCreate() {
        dietView.init();

        View decorView = dietView.getDecorView();
        dietView.setToolbar(decorView);
        dietView.showToolbarTitle("식습관 자가진단");
    }

    @Override
    public void onNetworkError(APIErrorUtil apiErrorUtil) {
        if (apiErrorUtil == null) {
            dietView.showMessage("네트워크 불안정합니다. 다시 시도하세요.");
        } else {
            dietView.showMessage(apiErrorUtil.message());
        }
    }

    @Override
    public void setDietDiagnosisCategoryList() {
        User user = preferenceUtil.getUserInfo();
        dietInteractor.getDietDiagnosisCategory(user);
    }

    @Override
    public void setDiagnosisCategoryList() {
        User user = preferenceUtil.getUserInfo();
        dietInteractor.getDiagnosisCategory(user);
    }


    @Override
    public void onClickDiagnosisSubmit() {
        User user = preferenceUtil.getUserInfo();
        dietInteractor.getDate(user, InsertDataFlag.DIET_DIAGNOSIS);
    }

    @Override
    public void onSuccessGetDietDiagnosisCategoryList(ArrayList<DietDiagnosisCategory> dietDiagnosisCategories) {
        dietInteractor.setDietDiagnosisList(dietDiagnosisCategories);
        dietView.setDietDiagnosisCategoryList(dietDiagnosisCategories);

        int dietDiagnosisCategoryListSize = dietDiagnosisCategories.size();
        for (int i = 0; i < dietDiagnosisCategoryListSize; i++) {
            dietInteractor.setDietSelfDiagnosisListAdd(null);
        }
    }

    @Override
    public void onSuccessSetDiagnosisData() {
        dietView.showMessage("식습관 자가진단 정보가 입력되었습니다.");
        dietView.navigateToBack();
    }

    @Override
    public void onSuccessGetDate(int checkedDate) {
        if(checkedDate < 1){
            setDietDiagnosis();
        } else {
            dietView.showMessage("하루에 한번만 등록이 가능합니다.");
        }
    }

    @Override
    public void onSuccessGetDiagnosisCategoryList(ArrayList<DiagnosisCategory> diagnosisCategoryList) {
        dietView.setDiagnosisCategoryList(diagnosisCategoryList);
        dietInteractor.setDiagnosisList(diagnosisCategoryList);
        dietView.setDietAdapter();
    }

    @Override
    public void setDiagnosisCategoryList(DietAdapter.DietViewHolder holder) {
        ArrayList<DiagnosisCategory> diagnosisCategoryList = dietInteractor.getDiagnosisCategoryList();
        int dietDiagnosisCategoryListSize = diagnosisCategoryList.size();

        for (int i = 0; i < dietDiagnosisCategoryListSize; i++) {
            dietView.showDiagnosisCategoryList(holder, i);
        }
    }

    @Override
    public void setDietDiagnosis() {
        ArrayList<DietDiagnosisCategory> dietDiagnosisCategoryList = dietInteractor.getDietDiagnosisCategoryList();
        int dietDiagnosisCategoryListSize = dietDiagnosisCategoryList.size();

        ArrayList<DietSelfDiagnosis> dietSelfDiagnosisList = dietInteractor.getDietSelfDiagnosisList();
        int dietSelfDiagnosisListSize = 0;
        for(int i=0; i< dietDiagnosisCategoryListSize; i++){
            if(dietSelfDiagnosisList.get(i) != null){
                dietSelfDiagnosisListSize++;
            }
        }

        if (dietSelfDiagnosisListSize != dietDiagnosisCategoryListSize) {
            dietView.showMessage("모든 문항에 답해주세요.");
        } else {
            User user = preferenceUtil.getUserInfo();
            for (int i = 0; i < dietSelfDiagnosisListSize; i++) {
                dietInteractor.setDietDiagnosisData(dietSelfDiagnosisList.get(i), user);
            }
        }
    }


    @Override
    public void onCheckedDiagnosisCategory(DietSelfDiagnosis dietSelfDiagnosis) {
        User user = preferenceUtil.getUserInfo();
        int userId = user.getId();

        ArrayList<DietSelfDiagnosis> dietSelfDiagnosisList = dietInteractor.getDietSelfDiagnosisList();
        int dietDiagnosisCategoryId = dietSelfDiagnosis.getDietDiagnosisCategoryId();

        if (dietSelfDiagnosisList.get(dietDiagnosisCategoryId - 1) == null) {
            dietSelfDiagnosis.setUserId(userId);
            dietInteractor.setDietSelfDiagnosisListAdd(dietSelfDiagnosis);
        } else {
            dietInteractor.setDietSelfDiagnosisListChangeDiagnosisCategoryId(dietSelfDiagnosis);
        }
    }

}
