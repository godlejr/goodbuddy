package com.demand.goodbuddy.main.base.interactor;

import com.demand.goodbuddy.dto.User;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public interface MainInteractor {
    User getUser();

    void setUser(User user);

    void getActiveMassData(User user);

    void getActiveMassChartLabels(User user);

    void getAverageActiveMass(User user);

    void getBloodPressureData(User user);

    void getBloodPressureChartLabels(User user);

    void getAverageMinBloodPressure(User user);

    void getAverageMaxBloodPressure(User user);

    void getBloodSugarData(User user);

    void getBloodSugarChartLabels(User user);

    void getAverageBloodSugar(User user);


    void getDietScoreData(User user);

    void getDietScoreChartLabels(User user);

    void getAverageDietScore(User user);


    void getBmiData(User user);

    void getBmiChartLabels(User user);

    void getAverageBmiScore(User user);


    void setAverageActiveMass(Float averageActiveMass);

    void setAverageBloodSugar(Float averageBloodSugar);

    void setAverageMaxBloodPressure(Float averageMaxBloodPressure);

    void setAverageMinBloodPressure(Float averageMinBloodPressure);


    void setAverageDietSelfDiagnosisScore(Float averageDietSelfDiagnosisScore);

    void setAverageBmiScore(Float averageBmiScore);

    Float getAverageActiveMass();
    Float getAverageBloodSugar();
    Float getAverageMaxBloodPressure();
    Float getAverageMinBloodPressure();
    Float getAverageDietSelfDiagnosisScore();
    Float getAverageBmiScore();

}
