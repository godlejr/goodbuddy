package com.demand.goodbuddy.record.view;

import android.view.View;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public interface RecordView {
    void init();

    void showToolbarTitle(String title);

    void setToolbar(View decorView);

    View getDecorView();

    void showMessage(String message);

    void setBloodPressureInsertSuccessButton();

    void setBloodSugarInsertSuccessButton();

    void setBmiInsertSuccessButton();

    void setBloodPressure();

    void setBloodSugar();

    void setBmi();
}
