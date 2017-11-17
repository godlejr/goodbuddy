package com.demand.goodbuddy.main.base.view;

import android.view.View;

import com.demand.goodbuddy.dto.User;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public interface MainView {
    void init();

    // toolbar, menu
    void setToolbar(View decorView);

    View getDecorView();

    void showToolbarTitle(String title);

    void setMenu(User user);

    void showMessage(String message);


    // activity chart


    void setActivityChartData(ArrayList<BarEntry> activityList);

    void setActivityChartLabels(ArrayList<String> labelList);

    void setAverageActiveMass(Float averageActiveMass);


    // bloodPressure chart


    void setBloodPressureChartData(ArrayList<Entry> minBloodPressureDataList, ArrayList<Entry> maxBloodPressureDataList);

    void setBloodPressureChartLabels(ArrayList<String> labelList);

    void setAverageMinBloodPressure(Float averageMinBloodPressure);

    void setAverageMaxBloodPressure(Float averageMaxBloodPressure);

    // bloodSugar chart


    void setBloodSugarChartData(ArrayList<Entry> bloodSugarChartDataList);

    void setBloodSugarChartLabels(ArrayList<String> labelList);

    void setAverageBloodSugar(Float averageBloodSugar);

    // diet chart


    void setDietChartData(ArrayList<Entry> dietChartDataList);

    void setDietChartLabels(ArrayList<String> labelList);

    void setAverageDietScore(Float averageDietScore);

    // bmi chart


    void setBmiChartData(ArrayList<Entry> bmiChartDataList);

    void setBmiChartLabels(ArrayList<String> labelList);

    void setAverageBmi(Float averageBmi);

    // all chart
    void setLineChartXAxisAndYAxis(LineChart chart, final ArrayList<String> labelList);

    void setLineChartNoData(LineChart chart);

    void setBarChartNoData(BarChart chart);


    // gone, show
    void showActiveMassChart();

    void goneActiveMassChart();

    void showBloodPressureChart();

    void goneBloodPressureChart();

    void showBloodSugarChart();

    void goneBloodSugarChart();

    void showDietScoreChart();

    void goneDietScoreChart();

    void showBmiChart();

    void goneBmiChart();


    // navigate
    void navigateToLoginActivity();

    void navigateToRecommendActivity();

    void navigateToNoteActivity();

    void navigateToRecordActivity();

    void navigateToDietActivity();

    void navigateToPedometerActivity();


    void navigateToBack();

    // avatar
    //bmi
    void setBmiAvatarGoodForWoman();
    void setBmiAvatarNormalForWoman();
    void setBmiAvatarBadForWoman();
    void setBmiAvatarTooBadForWoman();

    void setBmiAvatarGoodForMan();
    void setBmiAvatarNormalForMan();
    void setBmiAvatarBadForMan();
    void setBmiAvatarTooBadForMan();


    // bloog pressure
    void setBloodPressureAvatarGoodForWoman();
    void setBloodPressureAvatarNormalForWoman();
    void setBloodPressureAvatarBadForWoman();
    void setBloodPressureAvatarTooBadForWoman();

    void setBloodPressureAvatarGoodForMan();
    void setBloodPressureAvatarNormalForMan();
    void setBloodPressureAvatarBadForMan();
    void setBloodPressureAvatarTooBadForMan();


    // blood sugar
    void setBloodSugarAvatarGoodForWoman();
    void setBloodSugarAvatarNormalForWoman();
    void setBloodSugarAvatarBadForWoman();
    void setBloodSugarAvatarTooBadForWoman();

    void setBloodSugarAvatarGoodForMan();
    void setBloodSugarAvatarNormalForMan();
    void setBloodSugarAvatarBadForMan();
    void setBloodSugarAvatarTooBadForMan();



    // active mass
    void setActiveMassAvatarVeryGood();
    void setActiveMassAvatarGood();
    void setActiveMassAvatarNormal();
    void setActiveMassAvatarBad();



    // diet diagnosis
    void setDietSelfDiagnosisAvatarVeryGood();
    void setDietSelfDiagnosisAvatarGood();
    void setDietSelfDiagnosisAvatarNormal();
    void setDietSelfDiagnosisAvatarBad();
    void setDietSelfDiagnosisAvatarTooBad();


    // rainbow
    void showRainbow();
    void goneRainbow();

    // cloud
    void showCloud();
    void goneCloud();

    // rain
    void showRain();
    void goneRain();

    // thunder
    void showThunderbolt();
    void goneThunderbolt();

    // sun
    void showSun();
    void goneSun();

    void goneFab();

    void navigateToMessageActivity();

    void setServiceStart();
}
