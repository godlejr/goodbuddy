package com.demand.goodbuddy.note.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.note.base.presenter.NotePresenter;
import com.demand.goodbuddy.note.base.presenter.impl.NotePresenterImpl;
import com.demand.goodbuddy.note.base.view.NoteView;
import com.demand.goodbuddy.note.detail.exercise1.NoteExercise1Activity;
import com.demand.goodbuddy.note.detail.exercise2.NoteExercise2Activity;
import com.demand.goodbuddy.note.detail.fat.NoteFatActivity;
import com.demand.goodbuddy.note.detail.food.NoteFoodActivity;
import com.demand.goodbuddy.note.detail.nonsmoking.NoteNonSmokingActivity;
import com.demand.goodbuddy.note.detail.pressure.NotePressureActivity;
import com.demand.goodbuddy.note.detail.stress.NoteStressActivity;
import com.demand.goodbuddy.note.detail.sugar1.NoteSugar1Activity;
import com.demand.goodbuddy.note.detail.sugar2.NoteSugar2Activity;

/**
 * Created by ㅇㅇ on 2017-08-03.
 */

public class NoteActivity extends Activity implements NoteView, View.OnClickListener {
    private NotePresenter notePresenter;

    private LinearLayout note_fat;
    private LinearLayout note_food;
    private LinearLayout note_exercise1;
    private LinearLayout note_exercise2;
    private LinearLayout note_stress;
    private LinearLayout note_nonsmoking;
    private LinearLayout note_pressure;
    private LinearLayout note_sugar1;

    private LinearLayout note_sugar2;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView toolbarBack;
    private View decorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        this.notePresenter = new NotePresenterImpl(this);
        this.notePresenter.init();
    }

    @Override
    public void init() {
        note_fat = (LinearLayout) findViewById(R.id.note_fat);
        note_food = (LinearLayout) findViewById(R.id.note_food);
        note_exercise1 = (LinearLayout) findViewById(R.id.note_exercise1);
        note_exercise2 = (LinearLayout) findViewById(R.id.note_exercise2);
        note_stress = (LinearLayout) findViewById(R.id.note_stress);
        note_nonsmoking = (LinearLayout) findViewById(R.id.note_nonsmoking);
        note_pressure = (LinearLayout) findViewById(R.id.note_pressure);
        note_sugar1 = (LinearLayout) findViewById(R.id.note_sugar1);
        note_sugar2 = (LinearLayout) findViewById(R.id.note_sugar2);

        note_fat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteFatActivity.class);
                startActivity(intent);
            }
        });

        note_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteFoodActivity.class);
                startActivity(intent);
            }
        });
        note_exercise1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteExercise1Activity.class);
                startActivity(intent);
            }
        });
        note_exercise2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteExercise2Activity.class);
                startActivity(intent);
            }
        });
        note_stress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteStressActivity.class);
                startActivity(intent);
            }
        });
        note_nonsmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteNonSmokingActivity.class);
                startActivity(intent);
            }
        });
        note_pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NotePressureActivity.class);
                startActivity(intent);
            }
        });
        note_sugar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteSugar1Activity.class);
                startActivity(intent);
            }
        });
        note_sugar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteSugar2Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolBar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarBack = (ImageView) toolbar.findViewById(R.id.toolbar_back);
        toolbarBack.setOnClickListener(this);
    }

    @Override
    public void navigateToFatActivity() {

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.toolbar_back:
               finish();
               break;
       }
    }
    
    @Override
    public void showToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }

    @Override
    public View getDecorView() {
        if (decorView == null) {
            decorView = this.getWindow().getDecorView();
        }
        return decorView;
    }
}
