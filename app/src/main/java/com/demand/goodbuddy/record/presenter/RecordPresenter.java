package com.demand.goodbuddy.record.presenter;

import com.demand.goodbuddy.util.APIErrorUtil;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public interface RecordPresenter {
    void onCreate();

    void setBloodPressure(String minData, String maxData);

    void setBloodSugar(String data);

    void setBmi(String height, String weight);

    void onNetworkError(APIErrorUtil apiErrorUtil);

    void onSuccessSetBloodPressure();

    void onSuccessSetBloodSugar();

    void onSuccessSetBloodBmi();

    void onSuccessGetDate(int flag, int checkedDate);

    void onClickSetBloodPressure();

    void onClickSetBloodSugar();

    void onClickSetBmi();

//    void setRecord();
}
