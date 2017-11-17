package com.demand.goodbuddy.note.base.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.etc.functions;
import com.demand.goodbuddy.main.base.activity.MainActivity;
import com.demand.goodbuddy.note.detail.exercise1.NoteExercise1Activity;
import com.demand.goodbuddy.note.detail.exercise2.NoteExercise2Activity;
import com.demand.goodbuddy.note.detail.fat.NoteFatActivity;
import com.demand.goodbuddy.note.detail.food.NoteFoodActivity;
import com.demand.goodbuddy.note.detail.nonsmoking.NoteNonSmokingActivity;
import com.demand.goodbuddy.note.detail.pressure.NotePressureActivity;
import com.demand.goodbuddy.note.detail.stress.NoteStressActivity;
import com.demand.goodbuddy.note.detail.sugar1.NoteSugar1Activity;
import com.demand.goodbuddy.note.detail.sugar2.NoteSugar2Activity;

public class NoteActivity_prev extends Activity {
    /**
     * Called when the activity is first created.
     */

    Context context;
    Activity activity = this;
    public static Activity formerActivity_;
    Intent intent_record;
    Handler hd = new Handler();
    //
    ImageView img_slidemenu;
    TextView tv_content_title;
    //
    int length = 9;
    //
    TextView tv_explain_note;
    ScrollView sv_note;
    //
    TextView[] tv_note = new TextView[length];
    ImageView[] img_icon = new ImageView[length];
    ImageView[] img_go = new ImageView[length];
    LinearLayout[] note_menu = new LinearLayout[length];
    //
    Intent[] intent = new Intent[length];
    //
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_prev);
        //etc.functions.setStatusBarColor(activity, Color.parseColor("#333666"));

        id = getIntent().getStringExtra("id");

        formerActivity_ = this;

        reformView();
        //clickView(note_menu[0], intent[0]);
        applyClickEvent(note_menu, intent);
        //

        intent_record = new Intent(this, MainActivity.class);
        img_slidemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_record.putExtra("id", id);
                startActivity(intent_record);
                overridePendingTransition(R.anim.appear_from_left, R.anim.disappear_to_right);
                finish();
            }
        });

    }


    public void reformView(){
        context = getApplicationContext();

        img_slidemenu = (ImageView)findViewById(R.id.img_slidemenu);
        tv_content_title = (TextView)findViewById(R.id.tv_content_title);
        tv_explain_note = (TextView)findViewById(R.id.tv_explain_note);
        sv_note = (ScrollView)findViewById(R.id.sv_note);
        //
        functions.resize_view(img_slidemenu, "linear", .1388, 1, 0, 0, 0, 0);
        functions.textForm(tv_content_title, .0537, "bold", context);
        img_slidemenu.setImageResource(R.drawable.back);
        tv_content_title.setText("건강관리 수첩");
        //
        functions.formTextView("tv_note_", tv_note, length, .0398, "regular", activity, context);
        functions.resizeViewAll(tv_note, length, "linear", .7277, .2035, 0, 0, 0, 0);
        functions.formView("img_icon_", img_icon, length, activity, "linear", .0833, 1, .0370, 0, .0370, 0);
        functions.formView("img_go_", img_go, length, activity, "linear", .0222, 1.6670,0, 0, .0462, 0);
        //
        functions.textForm(tv_explain_note, .0388, "bold", context);
        functions.viewPadding(tv_explain_note, .0462, .0925, .0462, .0925);
        functions.resize_view(tv_explain_note, "linear", 0, 0, 0, 0, 0, .0555);
        //
        functions.findView("note_menu_", note_menu, length, activity);
        //
        intent[0] = new Intent(this, NoteFatActivity.class);
        intent[1] = new Intent(this, NoteFoodActivity.class);
        intent[2] = new Intent(this, NoteExercise1Activity.class);
        intent[3] = new Intent(this, NoteExercise2Activity.class);
        intent[4] = new Intent(this, NoteStressActivity.class);
        intent[5] = new Intent(this, NoteNonSmokingActivity.class);
        intent[6] = new Intent(this, NotePressureActivity.class);
        intent[7] = new Intent(this, NoteSugar1Activity.class);
        //
        intent[8] = new Intent(this, NoteSugar2Activity.class);
        // 이것도 나중에 함수화

    }

    public void clickView(LinearLayout ll, final Intent intent){

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id", id);
                formerActivity_.startActivity(intent);
                formerActivity_.overridePendingTransition(R.anim.appear_from_right, R.anim.disappear_to_left);
                formerActivity_.finish();
            }
        });

    }

    public void applyClickEvent(LinearLayout[] ll, Intent[] intent){
        for(int i = 0; i < length; i++) { //2는 length로
            clickView(ll[i], intent[i]);
        }
    }


}
