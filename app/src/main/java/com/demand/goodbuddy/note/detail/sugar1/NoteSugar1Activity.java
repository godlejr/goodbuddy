package com.demand.goodbuddy.note.detail.sugar1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.etc.events;
import com.demand.goodbuddy.etc.functions;
import com.demand.goodbuddy.note.base.activity.NoteActivity_prev;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class NoteSugar1Activity extends Activity {
    /**
     * Called when the activity is first created.
     */

    Context context;
    Activity activity = this;
    public static Activity formerActivity_;
    Intent intent;
    Handler hd = new Handler();
    //
    ImageView img_slidemenu;
    TextView tv_content_title;
    //
    int length_1 = 6;
    int length_2 = 27;
    int length_3 = 19;
    int length_4 = 7;
    //
    ScrollView sv_note;
    //
    LinearLayout[] layout_content = new LinearLayout[length_1];
    TextView[] tv_note_list = new TextView[length_1];
    ImageView[] img_go_list = new ImageView[length_1];
    LinearLayout[] content = new LinearLayout[length_1];
    //
    TextView[] tv_note_content = new TextView[length_2];
    ImageView[] img_dash = new ImageView[length_2];
    ImageView[] line_sub = new ImageView[length_3];
    ImageView[] round = new ImageView[length_4];
    TextView[] tv_round = new TextView[length_4];
    //
    AtomicIntegerArray isDropped= new AtomicIntegerArray(length_1);
    //
    int sPointArr_1 = 48;
    int sPointArr_2 = 111;
    int sPointArr_3 = 358;

    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_sugar1);
        //etc.functions.setStatusBarColor(activity, Color.parseColor("#333666"));

        formerActivity_ = this;

        reformView();
        events.backClick(activity, img_slidemenu);
        //
        events.assignBool(isDropped, length_1);
        events.applyDropContentAll(layout_content, content, img_go_list, isDropped, length_1);
        //

        intent = new Intent(this, NoteActivity_prev.class);
        img_slidemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent.putExtra("id", getIntent().getStringExtra("id"));
//                startActivity(intent);
                overridePendingTransition(R.anim.appear_from_left, R.anim.disappear_to_right);
                finish();
            }
        });

        functions.allocateText(tv_note_list, tv_round, tv_note_content, length_1, length_4, length_2, sPointArr_1, sPointArr_2, sPointArr_3);
    }


    public void reformView(){
        context = getApplicationContext();

        img_slidemenu = (ImageView)findViewById(R.id.img_slidemenu);
        tv_content_title = (TextView)findViewById(R.id.tv_content_title);
        sv_note = (ScrollView)findViewById(R.id.sv_note_7_1);
        //
        functions.resize_view(img_slidemenu, "linear", .1388, 1, 0, 0, 0, 0);
        functions.textForm(tv_content_title, .0537, "bold", context);
        img_slidemenu.setImageResource(R.drawable.back);
        tv_content_title.setText("혈당 관리 I");
        //
        functions.resize_view(sv_note, "linear", .9444, 0, 0,  0, 0, 0);
        //
        functions.findView("layout_content_", layout_content, length_1, activity);
        //
        functions.formTextView("tv_note_list_", tv_note_list, length_1, .0398, "regular", activity, context);
        functions.resizeViewAll(tv_note_list, length_1, "linear", .8296, .1746, .0462, 0, 0, 0);
        functions.formView("img_go_list_", img_go_list, length_1, activity, "linear", .0324, .6857, 0, 0, .0462, 0);
        //
        functions.findView("content_", content, length_1, activity);
        //
        functions.formTextView("tv_note_content_", tv_note_content, length_2, .0324, "regular", activity, context);
        functions.resizeViewAll(tv_note_content, length_2, "linear", .8148, 0, 0, .0277, 0, .0277);
        //
        functions.formView("img_dash_", img_dash, length_2, activity, "linear", .0129, .2142, .0462, .0444, .0240, 0);
        //
        functions.formView("line_sub_", line_sub, length_3, activity, "linear", .8518, 0, 0, 0, 0, 0);
        //
        functions.formView("round_", round, length_4, activity, "linear", .0129, 1, .0462, 0, .0240, 0);
        //
        functions.formTextView("tv_round_", tv_round, length_4, .0388, "regular", activity, context);
        functions.resizeViewAll(tv_round, length_4, "linear", .9444, .1, 0, 0, 0, 0);
    }

    /*
    public void allocateText(){
        for(int i = 0; i < length_1; i++){
            tv_note_list[i].setText(etc.notes.category_1(sPointArr_1+i));
        }
        for(int j = 0; j < length_4; j++){
            tv_round[j].setText(etc.notes.category_2(sPointArr_2+j));
        }
        for(int k = 0; k < length_2; k++){
            tv_note_content[k].setText(etc.notes.category_3(sPointArr_3+k));
        }
    }*/

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event){
//        switch(keyCode){
//            case KeyEvent.KEYCODE_BACK:
//                Intent intent = new Intent(this, NoteActivity_prev.class);
//                intent.putExtra("id", id);
//                events.keyBackEvent(intent, formerActivity_);
//        }
//        return true;
//    }


}
