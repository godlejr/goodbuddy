package com.demand.goodbuddy.main.base.presenter.impl;

import android.content.Context;
import android.view.View;

import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.main.base.interactor.MainInteractor;
import com.demand.goodbuddy.main.base.interactor.impl.MainInteractorImpl;
import com.demand.goodbuddy.main.base.presenter.MainPresenter;
import com.demand.goodbuddy.main.base.view.MainView;
import com.demand.goodbuddy.util.APIErrorUtil;
import com.demand.goodbuddy.util.PreferenceUtil;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private MainInteractor mainInteractor;
    private PreferenceUtil preferenceUtil;

    long backPressedTime = 0;
    long currentTime;

    public MainPresenterImpl(Context context) {
        this.mainView = (MainView) context;
        this.mainInteractor = new MainInteractorImpl(this);
        this.preferenceUtil = new PreferenceUtil(context);
    }

    @Override
    public void onCreate() {
        mainView.init();

        // toolbar
        View decorView = mainView.getDecorView();
        mainView.setToolbar(decorView);
        mainView.showToolbarTitle("나의 기록");

        User user = preferenceUtil.getUserInfo();
        mainInteractor.setUser(user);
        mainView.setMenu(user);

        //  mainView.setServiceStart();

        // set chart
        setActiveMassChartData();
        setBloodPressureChartData();
        setBloodSugarChartData();
        setDietScoreChartData();
        setBmiChartData();

    }

    @Override
    public void setActiveMassChartData() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getActiveMassData(user);
    }

    @Override
    public void setActiveMassChartLabels() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getActiveMassChartLabels(user);
    }

    @Override
    public void setBloodPressureChartData() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getBloodPressureData(user);
    }

    @Override
    public void setBloodPressureChartLabels() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getBloodPressureChartLabels(user);
    }

    @Override
    public void setAverageMinBloodPressure() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getAverageMinBloodPressure(user);
    }

    @Override
    public void setAverageMaxBloodPressure() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getAverageMaxBloodPressure(user);
    }

    @Override
    public void setBloodSugarChartData() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getBloodSugarData(user);
    }

    @Override
    public void setBloodSugarChartLabels() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getBloodSugarChartLabels(user);
    }

    @Override
    public void setAverageBloodSugar() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getAverageBloodSugar(user);
    }

    @Override
    public void setDietScoreChartData() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getDietScoreData(user);
    }

    @Override
    public void setDietScoreChartLabels() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getDietScoreChartLabels(user);
    }

    @Override
    public void setAverageDietScore() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getAverageDietScore(user);
    }

    @Override
    public void setBmiChartData() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getBmiData(user);
    }

    @Override
    public void setBmiChartLabels() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getBmiChartLabels(user);
    }

    @Override
    public void setAverageBmiScore() {
        User user = preferenceUtil.getUserInfo();
        mainInteractor.getAverageBmiScore(user);
    }

    @Override
    public void onSuccessGetActiveMassData(ArrayList<BarEntry> activityDataList) {
        if (activityDataList != null) {
            mainView.setActivityChartData(activityDataList);
        }

        User user = preferenceUtil.getUserInfo();
        mainInteractor.getAverageActiveMass(user);

    }

    @Override
    public void onSuccessGetAverageActiveMass(Float averageActiveMass) {
        mainInteractor.setAverageActiveMass(averageActiveMass);
        if (averageActiveMass == null) {
            averageActiveMass = Float.valueOf(0f);
        }

        preferenceUtil.setAverageActive(averageActiveMass);

        mainView.setAverageActiveMass(averageActiveMass);

        if (averageActiveMass >= 10000) {
            mainView.setActiveMassAvatarVeryGood();
            mainView.showRainbow();
            mainView.showCloud();
            mainView.goneSun();
            mainView.goneRain();
            mainView.goneThunderbolt();
        } else if (averageActiveMass >= 8000) {
            mainView.setActiveMassAvatarGood();
            mainView.showCloud();
            mainView.showSun();
            mainView.goneRainbow();
            mainView.goneRain();
            mainView.goneThunderbolt();
        } else if (averageActiveMass >= 6000) {
            mainView.setActiveMassAvatarNormal();
            mainView.showCloud();
            mainView.goneSun();
            mainView.goneRainbow();
            mainView.goneRain();
            mainView.goneThunderbolt();
        } else if (averageActiveMass >= 4000) {
            mainView.setActiveMassAvatarBad();
            mainView.goneRain();
            mainView.goneThunderbolt();
            mainView.goneCloud();
            mainView.goneRainbow();
            mainView.goneSun();
        } else {
            mainView.setActiveMassAvatarBad();
            mainView.showRain();
            mainView.showThunderbolt();
            mainView.goneCloud();
            mainView.goneRainbow();
            mainView.goneSun();
        }


    }

    @Override
    public void onSuccessGetBloodPressureData(ArrayList<Entry> minBloodPressureDataList, ArrayList<Entry> maxBloodPressureDataList) {
        if (minBloodPressureDataList != null && maxBloodPressureDataList != null) {
            mainView.setBloodPressureChartData(minBloodPressureDataList, maxBloodPressureDataList);
        }

        setAverageMinBloodPressure();
        setAverageMaxBloodPressure();
    }

    @Override
    public void onSuccessGetBloodPressureChartLabels(ArrayList<String> bloodPressureLabelList) {
        mainView.setBloodPressureChartLabels(bloodPressureLabelList);
    }

    @Override
    public void onSuccessGetAverageMinBloodPressure(Float averageMinBloodPressure) {
        mainInteractor.setAverageMinBloodPressure(averageMinBloodPressure);


        if (averageMinBloodPressure == null) {
            averageMinBloodPressure = Float.valueOf(0f);
        }
        mainView.setAverageMinBloodPressure(averageMinBloodPressure);
        preferenceUtil.setAverageMinPressure(averageMinBloodPressure);
    }

    @Override
    public void onSuccessGetAverageMaxBloodPressure(Float averageMaxBloodPressure) {
        mainInteractor.setAverageMaxBloodPressure(averageMaxBloodPressure);
;

        if (averageMaxBloodPressure == null) {
            averageMaxBloodPressure = Float.valueOf(0f);
        }

        preferenceUtil.setAverageMaxPressure(averageMaxBloodPressure);
        mainView.setAverageMaxBloodPressure(averageMaxBloodPressure);
    }

    @Override
    public void onSuccessGetBloodSugarData(ArrayList<Entry> bloodSugarEntryList) {
        if (bloodSugarEntryList != null) {
            mainView.setBloodSugarChartData(bloodSugarEntryList);
        }

        setAverageBloodSugar();
    }

    @Override
    public void onSuccessGetBloodSugarChartLabels(ArrayList<String> bloodPressureLabelList) {
        mainView.setBloodSugarChartLabels(bloodPressureLabelList);
    }

    @Override
    public void onSuccessGetAverageBloodSugar(Float averageBloodSugar) {
        mainInteractor.setAverageBloodSugar(averageBloodSugar);


        if (averageBloodSugar == null) {
            averageBloodSugar = Float.valueOf(0f);
        }

        mainView.setAverageBloodSugar(averageBloodSugar);
        preferenceUtil.setAverageSugar(averageBloodSugar);

        User user = mainInteractor.getUser();
        int gender = user.getGender();

        if (gender == 1) {
            if (averageBloodSugar == null || averageBloodSugar < 100) {
                mainView.setBloodSugarAvatarGoodForMan();
            } else if (averageBloodSugar < 125) {
                mainView.setBloodSugarAvatarNormalForMan();
            } else if (averageBloodSugar < 140) {
                mainView.setBloodSugarAvatarBadForMan();
            } else {
                mainView.setBloodSugarAvatarTooBadForMan();
            }
        }

        if (gender == 2) {
            if (averageBloodSugar == null || averageBloodSugar < 100) {
                mainView.setBloodSugarAvatarGoodForWoman();
            } else if (averageBloodSugar < 125) {
                mainView.setBloodSugarAvatarNormalForWoman();
            } else if (averageBloodSugar < 140) {
                mainView.setBloodSugarAvatarBadForWoman();
            } else {
                mainView.setBloodSugarAvatarTooBadForWoman();
            }
        }
    }

    @Override
    public void onSuccessGetDietScoreData(ArrayList<Entry> dietScoreDataList) {
        if (dietScoreDataList != null) {
            mainView.setDietChartData(dietScoreDataList);
        }

        setAverageDietScore();

    }

    @Override
    public void onSuccessGetDietScoreChartLabels(ArrayList<String> dietScoreLabelList) {
        mainView.setDietChartLabels(dietScoreLabelList);
    }

    @Override
    public void onSuccessGetAverageDietDiagnosisScore(Float averageDietDiagnosisScore) {
        mainInteractor.setAverageDietSelfDiagnosisScore(averageDietDiagnosisScore);


        if (averageDietDiagnosisScore == null) {
            averageDietDiagnosisScore = Float.valueOf(0f);
        }

        mainView.setAverageDietScore(averageDietDiagnosisScore);
        preferenceUtil.setAverageDiet(averageDietDiagnosisScore);

        if (averageDietDiagnosisScore >= 80) {
            mainView.setDietSelfDiagnosisAvatarVeryGood();
        } else if (averageDietDiagnosisScore >= 70) {
            mainView.setDietSelfDiagnosisAvatarGood();
        } else if (averageDietDiagnosisScore >= 60) {
            mainView.setDietSelfDiagnosisAvatarNormal();
        } else if (averageDietDiagnosisScore >= 50) {
            mainView.setDietSelfDiagnosisAvatarBad();
        } else {
            mainView.setDietSelfDiagnosisAvatarTooBad();
        }
    }

    @Override
    public void onSuccessGetBmiData(ArrayList<Entry> bmiDataList) {
        if (bmiDataList != null) {
            mainView.setBmiChartData(bmiDataList);
        }

        setAverageBmiScore();
    }

    @Override
    public void onSuccessGetBmiChartLabels(ArrayList<String> bmiLabelList) {
        mainView.setBmiChartLabels(bmiLabelList);
    }

    @Override
    public void onSuccessGetAverageBmiScore(Float averageBmiScore) {
        mainInteractor.setAverageBmiScore(averageBmiScore);


        if (averageBmiScore == null) {
            averageBmiScore = Float.valueOf(0f);
        }
        mainView.setAverageBmi(averageBmiScore);
        preferenceUtil.setAverageBmi(averageBmiScore);

        User user = mainInteractor.getUser();
        int gender = user.getGender();

        if (gender == 1) {
            if (averageBmiScore == null || averageBmiScore < 23) {
                mainView.setBmiAvatarGoodForMan();
            } else if (averageBmiScore < 25) {
                mainView.setBmiAvatarNormalForMan();
            } else if (averageBmiScore < 30) {
                mainView.setBmiAvatarBadForMan();
            } else {
                mainView.setBmiAvatarTooBadForMan();
            }
        }

        if (gender == 2) {
            if (averageBmiScore == null || averageBmiScore < 23) {
                mainView.setBmiAvatarGoodForWoman();
            } else if (averageBmiScore < 25) {
                mainView.setBmiAvatarNormalForWoman();
            } else if (averageBmiScore < 30) {
                mainView.setBmiAvatarBadForWoman();
            } else {
                mainView.setBmiAvatarTooBadForWoman();
            }

        }
    }

    @Override
    public void onSuccessGetActiveMassChartLabels(ArrayList<String> activityChartLabelList) {
        mainView.setActivityChartLabels(activityChartLabelList);
    }

    @Override
    public void onClickActivityTab(BarChart chart) {
        if (chart.getVisibility() == View.GONE) {
            mainView.showActiveMassChart();
        } else {
            mainView.goneActiveMassChart();
        }
    }

    @Override
    public void onClickBloodPressureTab(LineChart chart) {
        if (chart.getVisibility() == View.GONE) {
            mainView.showBloodPressureChart();
        } else {
            mainView.goneBloodPressureChart();
        }
    }

    @Override
    public void onClickBloodSugarTab(LineChart chart) {
        if (chart.getVisibility() == View.GONE) {
            mainView.showBloodSugarChart();
        } else {
            mainView.goneBloodSugarChart();
        }
    }

    @Override
    public void onClickDietScoreTab(LineChart chart) {
        if (chart.getVisibility() == View.GONE) {
            mainView.showDietScoreChart();
        } else {
            mainView.goneDietScoreChart();
        }
    }

    @Override
    public void onClickBmiTab(LineChart chart) {
        if (chart.getVisibility() == View.GONE) {
            mainView.showBmiChart();
        } else {
            mainView.goneBmiChart();
        }
    }

    @Override
    public void onClickLogout() {
        preferenceUtil.removeUserInfo();
        mainView.showMessage("로그아웃 되었습니다.");
        mainView.navigateToLoginActivity();
    }

    @Override
    public void onBackPressed() {
        currentTime = System.currentTimeMillis();

        if (currentTime > backPressedTime + 2000) {
            backPressedTime = currentTime;
            mainView.showMessage("한번 더 누르시면 종료됩니다.");
        } else {
            mainView.navigateToBack();
        }

    }

    @Override
    public String getFormattedValue(int value, ArrayList<String> labelList) {
        String formattedValue = "";

        if (value > -1) {
            if (labelList.size() > value) {
                formattedValue = labelList.get(value);
            }
        }

        return formattedValue;
    }

    @Override
    public void onNetworkError(APIErrorUtil apiErrorUtil) {
        if (apiErrorUtil == null) {
            mainView.showMessage("네트워크 불안정합니다. 다시 시도하세요.");
        } else {
            mainView.showMessage(apiErrorUtil.message());
        }
    }

    @Override
    public void setAvatar() {
        User user = preferenceUtil.getUserInfo();
        int gender = user.getGender();

        Float averageMaxBloodPressure = mainInteractor.getAverageMaxBloodPressure();
        Float averageMinBloodPressure = mainInteractor.getAverageMinBloodPressure();

        if (gender == 1) { // 남성
            if ((averageMinBloodPressure == null || averageMaxBloodPressure == null) || (averageMaxBloodPressure < 120 && averageMinBloodPressure < 80)) {
                mainView.setBloodPressureAvatarGoodForMan();
            } else if (averageMaxBloodPressure < 140 && averageMinBloodPressure < 90) {
                mainView.setBloodPressureAvatarNormalForMan();
            } else if (averageMaxBloodPressure < 160 && averageMinBloodPressure < 100) {
                mainView.setBloodPressureAvatarBadForMan();
            } else {
                mainView.setBloodPressureAvatarTooBadForMan();
            }
        }

        if (gender == 2) { // 여성

            if ((averageMinBloodPressure == null || averageMaxBloodPressure == null) || (averageMaxBloodPressure < 120 && averageMinBloodPressure < 80)) {
                mainView.setBloodPressureAvatarGoodForWoman();
            } else if (averageMaxBloodPressure < 140 && averageMinBloodPressure < 90) {
                mainView.setBloodPressureAvatarNormalForWoman();
            } else if (averageMaxBloodPressure < 160 && averageMinBloodPressure < 100) {
                mainView.setBloodPressureAvatarBadForWoman();
            } else {
                mainView.setBloodPressureAvatarTooBadForWoman();
            }
        }


    }

    @Override
    public void onClickMessage() {
        mainView.navigateToMessageActivity();
    }

    @Override
    public void onClickPedometer() {
        mainView.navigateToPedometerActivity();
        mainView.goneFab();
    }

    @Override
    public void onClickRecord() {
        mainView.navigateToRecordActivity();
        mainView.goneFab();
    }

    @Override
    public void onClickDiet() {
        mainView.navigateToDietActivity();
        mainView.goneFab();
    }

    @Override
    public void onClickNote() {
        mainView.navigateToNoteActivity();
    }

    @Override
    public void onClickRecommend() {
        mainView.navigateToRecommendActivity();
    }

    @Override
    public void onResume() {
        User user = mainInteractor.getUser();
        if (user == null) {
            preferenceUtil.getUserInfo();
        }

        if (user != null) {
//            mainInteractor.getAverageActiveMass(user);
//            mainInteractor.getAverageMinBloodPressure(user);
//            mainInteractor.getAverageMaxBloodPressure(user);
//            mainInteractor.getAverageBloodSugar(user);
//            mainInteractor.getAverageBmiScore(user);
//            mainInteractor.getAverageDietScore(user);

            mainInteractor.getActiveMassData(user);
            mainInteractor.getBloodPressureData(user);
            mainInteractor.getBloodSugarData(user);
            mainInteractor.getBmiData(user);
            mainInteractor.getDietScoreData(user);
        }
    }
}

