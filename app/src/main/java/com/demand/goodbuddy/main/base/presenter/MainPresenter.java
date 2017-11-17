package com.demand.goodbuddy.main.base.presenter;

import com.demand.goodbuddy.util.APIErrorUtil;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public interface MainPresenter {
    void onCreate();

    void onNetworkError(APIErrorUtil apiErrorUtil);

    void setAvatar();

    void setActiveMassChartData();

    void setActiveMassChartLabels();

    void setBloodPressureChartData();

    void setBloodPressureChartLabels();

    void setAverageMinBloodPressure();

    void setAverageMaxBloodPressure();

    void setBloodSugarChartData();

    void setBloodSugarChartLabels();

    void setAverageBloodSugar();


    void setDietScoreChartData();

    void setDietScoreChartLabels();

    void setAverageDietScore();

    void setBmiChartData();

    void setBmiChartLabels();

    void setAverageBmiScore();


    void onSuccessGetActiveMassChartLabels(ArrayList<String> activityChartLabelList);

    void onSuccessGetActiveMassData(ArrayList<BarEntry> activityDataList);

    void onSuccessGetAverageActiveMass(Float averageActiveMass);


    void onSuccessGetBloodPressureData(ArrayList<Entry> minBloodPressureDataList, ArrayList<Entry> maxBloodPressureDataList);

    void onSuccessGetBloodPressureChartLabels(ArrayList<String> bloodPressureLabelList);

    void onSuccessGetAverageMinBloodPressure(Float averageMinBloodPressure);

    void onSuccessGetAverageMaxBloodPressure(Float averageMaxBloodPressure);


    void onSuccessGetBloodSugarData(ArrayList<Entry> bloodSugarData);

    void onSuccessGetBloodSugarChartLabels(ArrayList<String> bloodPressureLabelList);

    void onSuccessGetAverageBloodSugar(Float averageBloodSugar);


    void onSuccessGetDietScoreData(ArrayList<Entry> dietScoreDataList);

    void onSuccessGetDietScoreChartLabels(ArrayList<String> dietScoreLabelList);

    void onSuccessGetAverageDietDiagnosisScore(Float averageDietDiagnosisScore);


    void onSuccessGetBmiData(ArrayList<Entry> bmiDataList);

    void onSuccessGetBmiChartLabels(ArrayList<String> bmiLabelList);

    void onSuccessGetAverageBmiScore(Float averageBmiScore);


    void onClickActivityTab(BarChart chart);

    void onClickBloodPressureTab(LineChart chart);

    void onClickBloodSugarTab(LineChart chart);

    void onClickDietScoreTab(LineChart chart);

    void onClickBmiTab(LineChart chart);

    void onClickLogout();

    void onBackPressed();

    String getFormattedValue(int value, ArrayList<String> labelList);

    void onClickMessage();

    void onClickPedometer();

    void onClickRecord();

    void onClickDiet();

    void onClickNote();

    void onClickRecommend();

    void onResume();
}
