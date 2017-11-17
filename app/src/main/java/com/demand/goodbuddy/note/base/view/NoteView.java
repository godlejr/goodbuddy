package com.demand.goodbuddy.note.base.view;

import android.view.View;

/**
 * Created by ㅇㅇ on 2017-08-03.
 */

public interface NoteView {
    void init();

    void setToolbar(View view);

    void navigateToFatActivity();

    void showToolbarTitle(String title);

    View getDecorView();
}
