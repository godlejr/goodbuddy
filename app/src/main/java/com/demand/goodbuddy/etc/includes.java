package com.demand.goodbuddy.etc;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dean on 2/23/2016.
 */
public class includes {

    static public void inc_title(ImageView iv, TextView tv, Context context, int shape, String str){

       functions.resize_view(iv, "linear", .1388, 1, 0, 0, 0, 0);
        functions.textForm(tv, .0535, "bold", context);
        tv.setText(str);

        if(shape == 1){
            //iv.setImageResource(R.drawable.menu_hamburg);
        }
        else if(shape == 2){
            //iv.setImageResource(R.drawable.back);
        }
    }

    static public void btn_event_back(final Intent intent, ImageView iv){

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(intent);
            }
        });

    }

}
