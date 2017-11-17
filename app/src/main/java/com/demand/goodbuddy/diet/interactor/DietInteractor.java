package com.demand.goodbuddy.diet.interactor;

import com.demand.goodbuddy.dto.DiagnosisCategory;
import com.demand.goodbuddy.dto.DietDiagnosisCategory;
import com.demand.goodbuddy.dto.DietSelfDiagnosis;
import com.demand.goodbuddy.dto.User;

import java.util.ArrayList;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public interface DietInteractor {
    ArrayList<DietSelfDiagnosis> getDietSelfDiagnosisList();

    void setDietSelfDiagnosisListAdd(DietSelfDiagnosis dietSelfDiagnosis);

    ArrayList<DiagnosisCategory> getDiagnosisCategoryList();

    void setDiagnosisList(ArrayList<DiagnosisCategory> diagnosisCategoryList);

    ArrayList<DietDiagnosisCategory> getDietDiagnosisCategoryList();

    void setDietDiagnosisList(ArrayList<DietDiagnosisCategory> dietDiagnosisCategoryList);

    void setDietSelfDiagnosisListChangeDiagnosisCategoryId(DietSelfDiagnosis dietSelfDiagnosis);

    void getDietDiagnosisCategory(User user);

    void getDiagnosisCategory(User user);

    void setDietDiagnosisData(DietSelfDiagnosis dietSelfDiagnosis, User user);

    void getDate(User user, int flag);
}
