package com.demand.goodbuddy.record.interactor;

import com.demand.goodbuddy.dto.BloodPressure;
import com.demand.goodbuddy.dto.BloodSugar;
import com.demand.goodbuddy.dto.Bmi;
import com.demand.goodbuddy.dto.User;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public interface RecordInteractor {
    void setBloodPressure(User user, BloodPressure bloodPressure);

    void setBloodSugar(User user, BloodSugar bloodSugar);

    void setBmi(User user, Bmi bmi);

    void getDate(User user,int flag);

}
