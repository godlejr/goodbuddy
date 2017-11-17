package com.demand.goodbuddy.recommend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.dto.Recommend;

/**
 * Created by ㅇㅇ on 2017-08-03.
 */

public class RecommendDetailActivity extends Activity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView toolbarBack;

    private TextView tv_subTitle;
    private TextView tv_detail;

    private String title;
    private String detail;
    private String subTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommenddetail);

        init();
    }

    public void init() {
        tv_subTitle = (TextView) findViewById(R.id.subtitle);
        tv_detail = (TextView) findViewById(R.id.detail);

        Intent intent = getIntent();
//        title = intent.getStringExtra("title");
//        detail = intent.getStringExtra("detail");
//        subTitle = intent.getStringExtra("subtitle");
        Recommend recommend = (Recommend)intent.getSerializableExtra("recommend");
        title =recommend.getTitle();
        subTitle=recommend.getSubTitle();
        detail = recommend.getContent();

        tv_subTitle.setText(subTitle);
        tv_detail.setText(detail);
        setToolbar();
    }

    public void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarBack = (ImageView) toolbar.findViewById(R.id.toolbar_back);
        toolbarTitle.setText(title);
        toolbarBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
