package com.demand.goodbuddy.main.agreement;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.demand.goodbuddy.R;

/**
 * Created by ㅇㅇ on 2017-11-17.
 */

public class AgreementActivity extends Activity {
    private ImageView toolbar_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);

        toolbar_back = (ImageView)findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
