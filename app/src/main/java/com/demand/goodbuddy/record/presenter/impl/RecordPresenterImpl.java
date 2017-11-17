package com.demand.goodbuddy.record.presenter.impl;

import android.content.Context;
import android.view.View;

import com.demand.goodbuddy.dto.BloodPressure;
import com.demand.goodbuddy.dto.BloodSugar;
import com.demand.goodbuddy.dto.Bmi;
import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.InsertDataFlag;
import com.demand.goodbuddy.record.interactor.RecordInteractor;
import com.demand.goodbuddy.record.interactor.impl.RecordInteractorImpl;
import com.demand.goodbuddy.record.presenter.RecordPresenter;
import com.demand.goodbuddy.record.view.RecordView;
import com.demand.goodbuddy.util.APIErrorUtil;
import com.demand.goodbuddy.util.PreferenceUtil;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public class RecordPresenterImpl implements RecordPresenter {
    private RecordView recordView;
    private RecordInteractor recordInteractor;
    private PreferenceUtil preferenceUtil;

    public RecordPresenterImpl(Context context) {
        this.recordView = (RecordView) context;
        this.recordInteractor = new RecordInteractorImpl(this);
        this.preferenceUtil = new PreferenceUtil(context);
    }

    @Override
    public void onCreate() {
        recordView.init();

        View decorView = recordView.getDecorView();
        recordView.setToolbar(decorView);
        recordView.showToolbarTitle("혈압/혈당/체중 입력");

//        setRecord();
    }

    @Override
    public void setBloodPressure(String minData, String maxData) {
        if (minData.length() == 0 || maxData.length() == 0|| minData.equals(".") || maxData.equals(".")) {
            recordView.showMessage("최소 혈압과 최고 혈압을 확인해주세요.");
        } else {
            User user = preferenceUtil.getUserInfo();
            BloodPressure bloodPressure = new BloodPressure();
            bloodPressure.setMaxData(Integer.valueOf(maxData));
            bloodPressure.setMinData(Integer.valueOf(minData));
            recordInteractor.setBloodPressure(user, bloodPressure);
        }
    }

    @Override
    public void setBloodSugar(String data) {
        if (data.length() == 0 || data.equals(".")) {
            recordView.showMessage("혈당을 확인해주세요.");
        } else {
            User user = preferenceUtil.getUserInfo();
            BloodSugar bloodSugar = new BloodSugar();
            bloodSugar.setData(Integer.valueOf(data));
            recordInteractor.setBloodSugar(user, bloodSugar);
        }
    }

    @Override
    public void setBmi(String height, String weight) {
        if (height.length() == 0 || weight.length() == 0 || height.equals(".") || weight.equals(".")) {
            recordView.showMessage("키와 체중을 확인해주세요.");
        } else {
            User user = preferenceUtil.getUserInfo();
            Bmi bmi = new Bmi();
            bmi.setHeight(Float.valueOf(height));
            bmi.setWeight(Float.valueOf(weight));
            recordInteractor.setBmi(user, bmi);
        }
    }

    @Override
    public void onSuccessSetBloodPressure() {
        recordView.showMessage("혈압이 입력되었습니다.");
        recordView.setBloodPressureInsertSuccessButton();
    }

    @Override
    public void onSuccessSetBloodSugar() {
        recordView.showMessage("혈당이 입력되었습니다.");
        recordView.setBloodSugarInsertSuccessButton();
    }

    @Override
    public void onSuccessSetBloodBmi() {
        recordView.showMessage("키/체중이 입력되었습니다.");
        recordView.setBmiInsertSuccessButton();
    }

    @Override
    public void onSuccessGetDate(int flag, int checkedDate) {
        if (checkedDate < 1) {
            if (flag == InsertDataFlag.BLOOD_PRESSURE) {
                recordView.setBloodPressure();
            }
            if (flag == InsertDataFlag.BLOOD_SUGAR) {
                recordView.setBloodSugar();
            }
            if (flag == InsertDataFlag.BMI) {
                recordView.setBmi();
            }
        } else {
            recordView.showMessage("하루에 한번만 입력이 가능합니다.");
        }
    }

    @Override
    public void onClickSetBloodPressure() {
        User user = preferenceUtil.getUserInfo();
        recordInteractor.getDate(user, InsertDataFlag.BLOOD_PRESSURE);
    }

    @Override
    public void onClickSetBloodSugar() {
        User user = preferenceUtil.getUserInfo();
        recordInteractor.getDate(user, InsertDataFlag.BLOOD_SUGAR);
    }

    @Override
    public void onClickSetBmi() {
        User user = preferenceUtil.getUserInfo();
        recordInteractor.getDate(user, InsertDataFlag.BMI);
    }

//    @Override
//    public void setRecord() {
//        String maxBloodPressure = recordInteractor.getMaxBloodPressure();
//        String minBloodPressure = recordInteractor.getMinBloodPressure();
//        String bloodSugar = recordInteractor.getBloodSugar();
//        String height = recordInteractor.getHeight();
//        String weight = recordInteractor.getWeight();
//
//        if (maxBloodPressure != null) {
//            recordView.setBloodPressureRecord(minBloodPressure, maxBloodPressure);
//        }
//        if (bloodSugar != null) {
//            recordView.setBloodSugarRecord(bloodSugar);
//        }
//        if (height != null) {
//            recordView.setBmiRecord(height, weight);
//        }
//    }

    @Override
    public void onNetworkError(APIErrorUtil apiErrorUtil) {
        if (apiErrorUtil == null) {
            recordView.showMessage("네트워크 불안정합니다. 다시 시도하세요.");
        } else {
            recordView.showMessage(apiErrorUtil.message());
        }
    }
}
