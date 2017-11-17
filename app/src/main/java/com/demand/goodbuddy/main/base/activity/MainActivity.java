package com.demand.goodbuddy.main.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.demand.goodbuddy.R;
import com.demand.goodbuddy.diet.activity.DietActivity;
import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.etc.manbogiflag;
import com.demand.goodbuddy.flag.UserFlag;
import com.demand.goodbuddy.main.agreement.AgreementActivity;
import com.demand.goodbuddy.main.base.presenter.MainPresenter;
import com.demand.goodbuddy.main.base.presenter.impl.MainPresenterImpl;
import com.demand.goodbuddy.main.base.view.MainView;
import com.demand.goodbuddy.login.base.activity.LoginActivity;
import com.demand.goodbuddy.message.activity.MessageActivity;
import com.demand.goodbuddy.note.base.activity.NoteActivity;
import com.demand.goodbuddy.pedometer.activity.PedometerActivity;
import com.demand.goodbuddy.recommend.RecommendActivity;
import com.demand.goodbuddy.record.activity.RecordActivity;
import com.demand.goodbuddy.service.pedometer.service.PedometerService;
import com.demand.goodbuddy.user.edit.activity.UserEditActivity;
import com.demand.goodbuddy.util.PreferenceUtil;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class MainActivity extends Activity implements MainView, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, FloatingActionsMenu.OnFloatingActionsMenuUpdateListener, View.OnTouchListener {
    private MainPresenter mainPresenter;
    private PreferenceUtil preferenceUtil;

    private Toolbar toolbar;
    private View decorView;
    private TextView toolbarTitle;
    private ImageView toolbarMenu;
    private ImageView toolbar_manbogi;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private View headerView;
    private TextView tvHeaderViewName;
    private ImageView ivHeaderViewAvatar;
    private ImageView iv_headerview_avatar;

    //avatar
    private ImageView ivAvatarBackground;
    private ImageView ivAvatarRainbow;
    private ImageView ivAvatarSun;
    private ImageView ivAvatarRainLeft;
    private ImageView ivAvatarRainRight;

    private ImageView ivAvatarCloudLeft;
    private ImageView ivAvatarCloudRight;

    private ImageView ivAvatarThunderboltLeft;
    private ImageView ivAvatarThunderboltRight;

    private ImageView ivAvatarFace;
    private ImageView ivAvatarEmotion;
    private ImageView ivAvatarBody;
    private ImageView ivAvatarLeg;

    private AnimationDrawable animSun;
    private AnimationDrawable animRainbow;
    private AnimationDrawable animCloudLeft;
    private AnimationDrawable animCloudRight;
    private AnimationDrawable animRainLeft;
    private AnimationDrawable animRainRight;
    private AnimationDrawable animThunderboltLeft;
    private AnimationDrawable animThunderboltRight;


    //fab
    private FloatingActionsMenu fabMenu;
    private FloatingActionButton fabPedometer;
    private FloatingActionButton fabRecord;
    private FloatingActionButton fabDiet;
    private FrameLayout flOverlay;

    //chart tab
    private LinearLayout activityChartTab;
    private LinearLayout bloodPressureChartTab;
    private LinearLayout bloodSugarChartTab;
    private LinearLayout dietTab;
    private LinearLayout bmiTab;

    //chart
    private BarChart activeMassChart;
    private LineChart bloodPressureChart;
    private LineChart bloodSugarChart;
    private LineChart dietChart;
    private LineChart bmiChart;

    //chart average
    private TextView tvAverageActivity;
    private TextView tvAverageMinBloodPressure;
    private TextView tvAverageMaxBloodPressure;
    private TextView tvAverageBloodSugar;
    private TextView tvAverageDiet;
    private TextView tvAverageBmi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.preferenceUtil = new PreferenceUtil(this);
        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.onCreate();

    }

    @Override
    public void init() {
        fabMenu = (FloatingActionsMenu) findViewById(R.id.fab_main_menu);
        fabPedometer = (FloatingActionButton) findViewById(R.id.fab_main_pedometer);
        fabRecord = (FloatingActionButton) findViewById(R.id.fab_main_record);
        fabDiet = (FloatingActionButton) findViewById(R.id.fab_main_diet);
        flOverlay = (FrameLayout) findViewById(R.id.fl_main_overlay);

        activityChartTab = (LinearLayout) findViewById(R.id.ll_main_activitytab);
        bloodPressureChartTab = (LinearLayout) findViewById(R.id.ll_main_bloodpressuretab);
        bloodSugarChartTab = (LinearLayout) findViewById(R.id.ll_main_bloodsugartab);
        dietTab = (LinearLayout) findViewById(R.id.ll_main_diettab);
        bmiTab = (LinearLayout) findViewById(R.id.ll_main_bmitab);

        activeMassChart = (BarChart) findViewById(R.id.chart_main_activity);
        bloodPressureChart = (LineChart) findViewById(R.id.chart_main_bloodpressure);
        bloodSugarChart = (LineChart) findViewById(R.id.chart_main_bloodsugar);
        dietChart = (LineChart) findViewById(R.id.chart_main_diet);
        bmiChart = (LineChart) findViewById(R.id.chart_main_bmi);

        tvAverageActivity = (TextView) findViewById(R.id.tv_main_averageactivity);
        tvAverageMinBloodPressure = (TextView) findViewById(R.id.tv_main_averageminbloodpressure);
        tvAverageMaxBloodPressure = (TextView) findViewById(R.id.tv_main_averagemaxbloodpressure);

        tvAverageBloodSugar = (TextView) findViewById(R.id.tv_main_averagebloodsugar);
        tvAverageDiet = (TextView) findViewById(R.id.tv_main_averagediet);
        tvAverageBmi = (TextView) findViewById(R.id.tv_main_averagebmi);

        ivAvatarBackground = (ImageView) findViewById(R.id.iv_main_bg);
        ivAvatarThunderboltLeft = (ImageView) findViewById(R.id.iv_main_thunderbolt_left);
        ivAvatarThunderboltRight = (ImageView) findViewById(R.id.iv_main_thunderbolt_right);

        ivAvatarRainbow = (ImageView) findViewById(R.id.iv_main_rainbow);
        ivAvatarCloudLeft = (ImageView) findViewById(R.id.iv_main_cloud_left);
        ivAvatarCloudRight = (ImageView) findViewById(R.id.iv_main_cloud_right);

        ivAvatarRainLeft = (ImageView) findViewById(R.id.iv_main_rain_left);
        ivAvatarRainRight = (ImageView) findViewById(R.id.iv_main_rain_right);

        ivAvatarSun = (ImageView) findViewById(R.id.iv_main_sun);
        ivAvatarBody = (ImageView) findViewById(R.id.iv_main_body);
        ivAvatarEmotion = (ImageView) findViewById(R.id.iv_main_emotion);
        ivAvatarFace = (ImageView) findViewById(R.id.iv_main_face);
        ivAvatarLeg = (ImageView) findViewById(R.id.iv_main_leg);

        ivAvatarSun.setBackgroundResource(R.drawable.anim_sun);
        ivAvatarCloudLeft.setBackgroundResource(R.drawable.anim_cloud);
        ivAvatarCloudRight.setBackgroundResource(R.drawable.anim_cloud);
        ivAvatarRainLeft.setBackgroundResource(R.drawable.anim_rain);
        ivAvatarRainRight.setBackgroundResource(R.drawable.anim_rain);
        ivAvatarRainbow.setBackgroundResource(R.drawable.anim_rainbow);
        ivAvatarThunderboltLeft.setBackgroundResource(R.drawable.anim_thunderbolt);
        ivAvatarThunderboltRight.setBackgroundResource(R.drawable.anim_thunderbolt);

        animSun = (AnimationDrawable) ivAvatarSun.getBackground();
        animCloudLeft = (AnimationDrawable) ivAvatarCloudLeft.getBackground();
        animCloudRight = (AnimationDrawable) ivAvatarCloudRight.getBackground();
        animRainLeft = (AnimationDrawable) ivAvatarRainLeft.getBackground();
        animRainRight = (AnimationDrawable) ivAvatarRainRight.getBackground();
        animRainbow = (AnimationDrawable) ivAvatarRainbow.getBackground();
        animThunderboltLeft = (AnimationDrawable) ivAvatarThunderboltLeft.getBackground();
        animThunderboltRight = (AnimationDrawable) ivAvatarThunderboltRight.getBackground();

        flOverlay.getBackground().setAlpha(0);

        setBarChartNoData(activeMassChart);
        setLineChartNoData(bloodPressureChart);
        setLineChartNoData(bloodSugarChart);
        setLineChartNoData(dietChart);
        setLineChartNoData(bmiChart);

        fabMenu.setOnFloatingActionsMenuUpdateListener(this);
        fabPedometer.setOnClickListener(this);
        fabRecord.setOnClickListener(this);
        fabDiet.setOnClickListener(this);

        activityChartTab.setOnClickListener(this);
        bloodPressureChartTab.setOnClickListener(this);
        bloodSugarChartTab.setOnClickListener(this);
        dietTab.setOnClickListener(this);
        bmiTab.setOnClickListener(this);


    }

    @Override
    public View getDecorView() {
        if (decorView == null) {
            decorView = this.getWindow().getDecorView();
        }
        return decorView;
    }

    @Override
    protected void onResume() {
        super.onResume();

        mainPresenter.onResume();

        if (manbogiflag.isPlaying) {
            toolbar_manbogi.setColorFilter(getResources().getColor(R.color.green));
        } else {
            toolbar_manbogi.setColorFilter(getResources().getColor(R.color.red));
        }

        if (preferenceUtil != null) {
            User user = preferenceUtil.getUserInfo();
            if (user.getGender() == UserFlag.MAN) {
                iv_headerview_avatar.setImageResource(R.drawable.man);
            } else {
                iv_headerview_avatar.setImageResource(R.drawable.female);
            }

            tvHeaderViewName.setText(user.getName());
        }

    }

    @Override
    public void setToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolBar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarMenu = (ImageView) toolbar.findViewById(R.id.toolbar_menu);
        toolbar_manbogi = (ImageView) toolbar.findViewById(R.id.toolbar_manbogi);

        toolbarMenu.setOnClickListener(this);

        if (manbogiflag.isPlaying) {
            toolbar_manbogi.setColorFilter(getResources().getColor(R.color.green));
        } else {
            toolbar_manbogi.setColorFilter(getResources().getColor(R.color.red));
        }
    }

    @Override
    public void setMenu(User user) {
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        headerView = navigationView.getHeaderView(0);
        tvHeaderViewName = (TextView) headerView.findViewById(R.id.tv_headerview_name);
        ivHeaderViewAvatar = (ImageView) headerView.findViewById(R.id.iv_headerview_avatar);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        Menu menu = navigationView.getMenu();
        MenuItem menuAll = menu.findItem(R.id.menu_all);
        SpannableString spannableString = new SpannableString(menuAll.getTitle());
        spannableString.setSpan(new TextAppearanceSpan(MainActivity.this, R.style.NavigationDrawer), 0, spannableString.length(), 0);
        menuAll.setTitle(spannableString);

        MenuItem menuSetting = menu.findItem(R.id.menu_setting);
        spannableString = new SpannableString(menuSetting.getTitle());
        spannableString.setSpan(new TextAppearanceSpan(MainActivity.this, R.style.NavigationDrawer), 0, spannableString.length(), 0);
        menuSetting.setTitle(spannableString);

        tvHeaderViewName.setText(user.getName());
        Glide.with(this).load(getString(R.string.cloud_front_user_avatar) + user.getAvatar()).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivHeaderViewAvatar);

        int gender = preferenceUtil.getUserInfo().getGender();
        iv_headerview_avatar = (ImageView) headerView.findViewById(R.id.iv_headerview_avatar);

        if (gender == UserFlag.MAN) {
            iv_headerview_avatar.setImageResource(R.drawable.man);
        } else {
            iv_headerview_avatar.setImageResource(R.drawable.female);
        }

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserEditActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setActivityChartData(ArrayList<BarEntry> activityList) {
        showActiveMassChart();

        BarDataSet barDataSet = new BarDataSet(activityList, "");
        barDataSet.setColors(Color.parseColor("#4DFFFFFF"));
        barDataSet.setHighLightAlpha(0);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.3f);
        barData.setValueTextColor(Color.WHITE);
        barData.setValueTextSize(12);

        activeMassChart.setData(barData);
        activeMassChart.moveViewToX(activeMassChart.getXChartMax());

        activeMassChart.setVisibleXRangeMaximum(7);

        mainPresenter.setActiveMassChartLabels();
    }

    @Override
    public void setActivityChartLabels(final ArrayList<String> labelList) {
        activeMassChart.animateXY(2000, 2000);
//        activityChart.zoomIn();
        activeMassChart.getLegend().setEnabled(false);
        activeMassChart.invalidate();

        Description desc = activeMassChart.getDescription();
        desc.setEnabled(false);

        XAxis xAxis = activeMassChart.getXAxis();
        xAxis.setTextColor(Color.WHITE);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(12);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String formattedValue = mainPresenter.getFormattedValue((int) value, labelList);
                return formattedValue;
            }
        });

        YAxis yAxisLeft = activeMassChart.getAxisLeft();
        YAxis yAxisRight = activeMassChart.getAxisRight();

        yAxisLeft.setEnabled(false);
        yAxisRight.setEnabled(false);
    }

    @Override
    public void setAverageActiveMass(Float averageActiveMass) {
        String message = "평균 " + averageActiveMass + " 걸음";
        tvAverageActivity.setText(message);
    }


    @Override
    public void setBloodPressureChartData(ArrayList<Entry> minBloodPressureDataList, ArrayList<Entry> maxBloodPressureDataList) {
//        showBloodPressureChart();

        ArrayList<ILineDataSet> lineDataSetList = new ArrayList<>();

        LineDataSet minLineDataSet = new LineDataSet(minBloodPressureDataList, "");
        LineDataSet maxLineDataSet = new LineDataSet(maxBloodPressureDataList, "");

        minLineDataSet.setColor(Color.WHITE);
        maxLineDataSet.setColor(Color.WHITE);

        minLineDataSet.setDrawFilled(true);
        maxLineDataSet.setDrawFilled(true);

        minLineDataSet.setValueTextColor(Color.WHITE);
        maxLineDataSet.setValueTextColor(Color.WHITE);

        minLineDataSet.setValueTextSize(12);
        maxLineDataSet.setValueTextSize(12);

        lineDataSetList.add(minLineDataSet);
        lineDataSetList.add(maxLineDataSet);

        LineData lineData = new LineData(lineDataSetList);
        bloodPressureChart.setData(lineData);
        bloodPressureChart.moveViewToX(bloodPressureChart.getXChartMax());

        bloodPressureChart.setVisibleXRangeMaximum(7);
        mainPresenter.setBloodPressureChartLabels();
    }


    @Override
    public void setBloodPressureChartLabels(ArrayList<String> labelList) {
        setLineChartXAxisAndYAxis(bloodPressureChart, labelList);
    }

    @Override
    public void setAverageMinBloodPressure(Float averageMinBloodPressure) {
        tvAverageMinBloodPressure.setText(String.valueOf(averageMinBloodPressure));
    }

    @Override
    public void setAverageMaxBloodPressure(Float averageMaxBloodPressure) {
        tvAverageMaxBloodPressure.setText(String.valueOf(averageMaxBloodPressure));
    }


    @Override
    public void setBloodSugarChartData(ArrayList<Entry> bloodSugarDataList) {
        LineDataSet lineDataSet = new LineDataSet(bloodSugarDataList, "");
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setColor(Color.WHITE);
        lineDataSet.setDrawFilled(true);

        LineData lineData = new LineData(lineDataSet);
        bloodSugarChart.setData(lineData);
        bloodSugarChart.moveViewToX(bloodSugarChart.getXChartMax());

        bloodSugarChart.setVisibleXRangeMaximum(7);

        mainPresenter.setBloodSugarChartLabels();
    }

    @Override
    public void setBloodSugarChartLabels(ArrayList<String> labelList) {
        setLineChartXAxisAndYAxis(bloodSugarChart, labelList);
    }

    @Override
    public void setAverageBloodSugar(Float averageBloodSugar) {
        String message = "평균 " + averageBloodSugar + "mmHg";
        tvAverageBloodSugar.setText(message);
    }

    @Override
    public void setDietChartData(ArrayList<Entry> dietChartDataList) {
        LineDataSet lineDataSet = new LineDataSet(dietChartDataList, "");
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setColor(Color.WHITE);
        lineDataSet.setDrawFilled(true);

        LineData lineData = new LineData(lineDataSet);
        dietChart.setData(lineData);
        dietChart.moveViewToX(dietChart.getXChartMax());
        dietChart.setVisibleXRangeMaximum(7);

        mainPresenter.setDietScoreChartLabels();
    }

    @Override
    public void setDietChartLabels(ArrayList<String> labelList) {
        setLineChartXAxisAndYAxis(dietChart, labelList);
    }

    @Override
    public void setAverageDietScore(Float averageDietScore) {
        String message = "평균 " + averageDietScore + "점";
        tvAverageDiet.setText(message);
    }

    @Override
    public void setBmiChartData(ArrayList<Entry> bmiChartDataList) {
        LineDataSet lineDataSet = new LineDataSet(bmiChartDataList, "");
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setColor(Color.WHITE);
        lineDataSet.setDrawFilled(true);

        LineData lineData = new LineData(lineDataSet);
        bmiChart.setData(lineData);
        bmiChart.moveViewToX(bmiChart.getXChartMax());

        bmiChart.setVisibleXRangeMaximum(7);

        mainPresenter.setBmiChartLabels();
    }

    @Override
    public void setBmiChartLabels(ArrayList<String> labelList) {
        setLineChartXAxisAndYAxis(bmiChart, labelList);
    }

    @Override
    public void setAverageBmi(Float averageBmi) {
        String message = "평균 " + averageBmi + "점";
        tvAverageBmi.setText(message);

        mainPresenter.setAvatar();
    }

    @Override
    public void setLineChartXAxisAndYAxis(LineChart chart, final ArrayList<String> labelList) {
        chart.getLegend().setEnabled(false);

        Description desc = chart.getDescription();
        desc.setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(12);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String formattedValue = mainPresenter.getFormattedValue((int) value, labelList);
                return formattedValue;
            }
        });

        YAxis yAxisLeft = chart.getAxisLeft();
        YAxis yAxisRight = chart.getAxisRight();

        yAxisRight.setEnabled(false);
        yAxisLeft.setEnabled(false);
    }

    @Override
    public void showToolbarTitle(String message) {
        toolbarTitle.setText(message);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // toolbar
            case R.id.toolbar_menu:
                drawerLayout.openDrawer(Gravity.START);
                break;


            // fab
            case R.id.fab_main_pedometer:
                mainPresenter.onClickPedometer();
                break;
            case R.id.fab_main_record:
                mainPresenter.onClickRecord();
                break;
            case R.id.fab_main_diet:
                mainPresenter.onClickDiet();
                break;

            // chart tab
            case R.id.ll_main_activitytab:
                mainPresenter.onClickActivityTab(activeMassChart);
                break;
            case R.id.ll_main_bloodpressuretab:
                mainPresenter.onClickBloodPressureTab(bloodPressureChart);
                break;
            case R.id.ll_main_bloodsugartab:
                mainPresenter.onClickBloodSugarTab(bloodSugarChart);
                break;
            case R.id.ll_main_diettab:
                mainPresenter.onClickDietScoreTab(dietChart);
                break;
            case R.id.ll_main_bmitab:
                mainPresenter.onClickBmiTab(bmiChart);
        }
    }

    @Override
    public void goneFab() {
        fabMenu.collapse();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        drawerLayout.closeDrawers();

        switch (item.getItemId()) {
            case R.id.menu_home:
                break;
            case R.id.menu_logout:
                mainPresenter.onClickLogout();
                break;
            case R.id.menu_pedometer:
                mainPresenter.onClickPedometer();
                break;
            case R.id.menu_note:
                mainPresenter.onClickNote();
                break;
            case R.id.menu_recommend:
                mainPresenter.onClickRecommend();
                break;
            case R.id.menu_message:
                mainPresenter.onClickMessage();
                break;

            case R.id.menu_agreement:
                Intent intent = new Intent(this, AgreementActivity.class);
                startActivity(intent);
                break;

        }

        return true;
    }

    @Override
    public void navigateToMessageActivity() {
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToRecommendActivity() {
        Intent intent = new Intent(MainActivity.this, RecommendActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToNoteActivity() {
        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToRecordActivity() {
        Intent intent = new Intent(MainActivity.this, RecordActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToDietActivity() {
        Intent intent = new Intent(MainActivity.this, DietActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToPedometerActivity() {
        Intent intent = new Intent(MainActivity.this, PedometerActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToBack() {
        finish();
    }

    @Override
    public void setBmiAvatarGoodForWoman() {
        Glide.with(this).load(R.drawable.main_body_w_fat_good).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarBody);
    }

    @Override
    public void setBmiAvatarNormalForWoman() {
        Glide.with(this).load(R.drawable.main_body_w_fat_normal).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarBody);
    }

    @Override
    public void setBmiAvatarBadForWoman() {
        Glide.with(this).load(R.drawable.main_body_w_fat_bad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarBody);
    }

    @Override
    public void setBmiAvatarTooBadForWoman() {
        Glide.with(this).load(R.drawable.main_body_w_fat_toobad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarBody);
    }

    @Override
    public void setBmiAvatarGoodForMan() {
        Glide.with(this).load(R.drawable.main_body_m_fat_good).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarBody);
    }

    @Override
    public void setBmiAvatarNormalForMan() {
        Glide.with(this).load(R.drawable.main_body_m_fat_normal).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarBody);
    }

    @Override
    public void setBmiAvatarBadForMan() {
        Glide.with(this).load(R.drawable.main_body_m_fat_bad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarBody);
    }

    @Override
    public void setBmiAvatarTooBadForMan() {
        Glide.with(this).load(R.drawable.main_body_m_fat_toobad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarBody);
    }

    @Override
    public void setBloodPressureAvatarGoodForWoman() {
        Glide.with(this).load(R.drawable.main_face_w_hypertension_good).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarFace);
    }

    @Override
    public void setBloodPressureAvatarNormalForWoman() {
        Glide.with(this).load(R.drawable.main_face_w_hypertension_normal).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarFace);
    }

    @Override
    public void setBloodPressureAvatarBadForWoman() {
        Glide.with(this).load(R.drawable.main_face_w_hypertension_bad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarFace);
    }

    @Override
    public void setBloodPressureAvatarTooBadForWoman() {
        Glide.with(this).load(R.drawable.main_face_w_hypertension_toobad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarFace);
    }

    @Override
    public void setBloodPressureAvatarGoodForMan() {
        Glide.with(this).load(R.drawable.main_face_m_hypertension_good).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarFace);
    }

    @Override
    public void setBloodPressureAvatarNormalForMan() {
        Glide.with(this).load(R.drawable.main_face_m_hypertension_normal).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarFace);
    }

    @Override
    public void setBloodPressureAvatarBadForMan() {
        Glide.with(this).load(R.drawable.main_face_m_hypertension_bad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarFace);
    }

    @Override
    public void setBloodPressureAvatarTooBadForMan() {
        Glide.with(this).load(R.drawable.main_face_m_hypertension_toobad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarFace);
    }

    @Override
    public void setBloodSugarAvatarGoodForWoman() {
        Glide.with(this).load(R.drawable.main_leg_w_diabetes_good).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarLeg);
    }

    @Override
    public void setBloodSugarAvatarNormalForWoman() {
        Glide.with(this).load(R.drawable.main_leg_w_diabetes_normal).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarLeg);
    }

    @Override
    public void setBloodSugarAvatarBadForWoman() {
        Glide.with(this).load(R.drawable.main_leg_w_diabetes_bad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarLeg);
    }

    @Override
    public void setBloodSugarAvatarTooBadForWoman() {
        Glide.with(this).load(R.drawable.main_leg_w_diabetes_toobad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarLeg);
    }

    @Override
    public void setBloodSugarAvatarGoodForMan() {
        Glide.with(this).load(R.drawable.main_leg_m_diabetes_good).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarLeg);
    }

    @Override
    public void setBloodSugarAvatarNormalForMan() {
        Glide.with(this).load(R.drawable.main_leg_m_diabetes_normal).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarLeg);
    }

    @Override
    public void setBloodSugarAvatarBadForMan() {
        Glide.with(this).load(R.drawable.main_leg_m_diabetes_bad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarLeg);
    }

    @Override
    public void setBloodSugarAvatarTooBadForMan() {
        Glide.with(this).load(R.drawable.main_leg_m_diabetes_toobad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarLeg);
    }


    @Override
    public void setActiveMassAvatarVeryGood() {
        Glide.with(this).load(R.drawable.main_bg_verygood).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarBackground);
    }

    @Override
    public void setActiveMassAvatarGood() {
        Glide.with(this).load(R.drawable.main_bg_good).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarBackground);
    }

    @Override
    public void setActiveMassAvatarNormal() {
        Glide.with(this).load(R.drawable.main_bg_normal).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarBackground);
    }

    @Override
    public void setActiveMassAvatarBad() {
        Glide.with(this).load(R.drawable.main_bg_bad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarBackground);
    }

    @Override
    public void setDietSelfDiagnosisAvatarVeryGood() {
        Glide.with(this).load(R.drawable.main_emotion_verygood).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarEmotion);
    }

    @Override
    public void setDietSelfDiagnosisAvatarGood() {
        Glide.with(this).load(R.drawable.main_emotion_good).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarEmotion);
    }

    @Override
    public void setDietSelfDiagnosisAvatarNormal() {
        Glide.with(this).load(R.drawable.main_emotion_normal).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarEmotion);
    }

    @Override
    public void setDietSelfDiagnosisAvatarBad() {
        Glide.with(this).load(R.drawable.main_emotion_bad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarEmotion);
    }

    @Override
    public void setDietSelfDiagnosisAvatarTooBad() {
        Glide.with(this).load(R.drawable.main_emotion_toobad).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivAvatarEmotion);
    }

    @Override
    public void showRainbow() {
        ivAvatarRainbow.setVisibility(View.VISIBLE);
        animRainbow.start();
    }

    @Override
    public void goneRainbow() {
        ivAvatarRainbow.setVisibility(View.GONE);
        animRainbow.stop();
    }

    @Override
    public void showCloud() {
        ivAvatarCloudLeft.setVisibility(View.VISIBLE);
        ivAvatarCloudRight.setVisibility(View.VISIBLE);

        animCloudLeft.start();
        animCloudRight.start();
    }

    @Override
    public void goneCloud() {
        ivAvatarCloudLeft.setVisibility(View.GONE);
        ivAvatarCloudRight.setVisibility(View.GONE);

        animCloudLeft.stop();
        animCloudRight.stop();
    }

    @Override
    public void showRain() {
        ivAvatarRainLeft.setVisibility(View.VISIBLE);
        ivAvatarRainRight.setVisibility(View.VISIBLE);

        animRainLeft.start();
        animRainRight.start();
    }

    @Override
    public void goneRain() {
        ivAvatarRainLeft.setVisibility(View.GONE);
        ivAvatarRainRight.setVisibility(View.GONE);

        animRainLeft.stop();
        animRainRight.stop();
    }

    @Override
    public void showThunderbolt() {
        ivAvatarThunderboltLeft.setVisibility(View.VISIBLE);
        ivAvatarThunderboltRight.setVisibility(View.VISIBLE);

        animThunderboltLeft.start();
        animThunderboltRight.start();

    }

    @Override
    public void goneThunderbolt() {
        ivAvatarThunderboltLeft.setVisibility(View.GONE);
        ivAvatarThunderboltRight.setVisibility(View.GONE);

        animThunderboltLeft.stop();
        animThunderboltRight.stop();
    }

    @Override
    public void showSun() {
        ivAvatarSun.setVisibility(View.VISIBLE);
        animSun.start();
    }

    @Override
    public void goneSun() {
        ivAvatarSun.setVisibility(View.GONE);
        animSun.stop();
    }

    @Override
    public void onMenuExpanded() {
        flOverlay.getBackground().setAlpha(150);
        flOverlay.setOnTouchListener(this);
    }

    @Override
    public void onMenuCollapsed() {
        flOverlay.getBackground().setAlpha(0);
        flOverlay.setOnTouchListener(null);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.fl_main_overlay:
                fabMenu.collapse();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        mainPresenter.onBackPressed();
    }

    @Override
    public void showActiveMassChart() {
        activeMassChart.setVisibility(View.VISIBLE);
    }

    @Override
    public void goneActiveMassChart() {
        activeMassChart.setVisibility(View.GONE);
    }

    @Override
    public void showBloodPressureChart() {
        bloodPressureChart.setVisibility(View.VISIBLE);
    }

    @Override
    public void goneBloodPressureChart() {
        bloodPressureChart.setVisibility(View.GONE);
    }

    @Override
    public void showBloodSugarChart() {
        bloodSugarChart.setVisibility(View.VISIBLE);
    }

    @Override
    public void goneBloodSugarChart() {
        bloodSugarChart.setVisibility(View.GONE);

    }

    @Override
    public void showDietScoreChart() {
        dietChart.setVisibility(View.VISIBLE);
    }

    @Override
    public void goneDietScoreChart() {
        dietChart.setVisibility(View.GONE);

    }

    @Override
    public void showBmiChart() {
        bmiChart.setVisibility(View.VISIBLE);
    }

    @Override
    public void goneBmiChart() {
        bmiChart.setVisibility(View.GONE);
    }

    @Override
    public void navigateToLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setLineChartNoData(LineChart chart) {
        chart.setNoDataText("저장된 기록이 없습니다.");
        chart.setNoDataTextColor(Color.WHITE);
    }

    @Override
    public void setBarChartNoData(BarChart chart) {
        chart.setNoDataText("저장된 기록이 없습니다.");
        chart.setNoDataTextColor(Color.WHITE);
    }

    @Override
    public void setServiceStart() {
        if (preferenceUtil.getPedometerisFirst()) {
            Intent pedometerIntent = new Intent(this, PedometerService.class);
            startService(pedometerIntent);
            preferenceUtil.setPedometerFirst(false);
        } else {
            manbogiflag.setIsPlaying(true);
            preferenceUtil.setPedometerPlay(true);
        }

    }

}
