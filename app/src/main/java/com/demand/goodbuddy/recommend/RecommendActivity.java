package com.demand.goodbuddy.recommend;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.demand.goodbuddy.R;
import com.demand.goodbuddy.dto.ActiveMass;
import com.demand.goodbuddy.dto.BloodPressure;
import com.demand.goodbuddy.dto.BloodSugar;
import com.demand.goodbuddy.dto.BmiScore;
import com.demand.goodbuddy.dto.DietSelfDiagnosisScore;
import com.demand.goodbuddy.dto.Recommend;
import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.repository.UserController;
import com.demand.goodbuddy.repository.interceptor.HeaderInterceptor;
import com.demand.goodbuddy.util.PreferenceUtil;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ㅇㅇ on 2017-08-03.
 */

public class RecommendActivity extends Activity implements View.OnClickListener {
//    private FrameLayout tip_0;
//    private TextView cont_0;
//    private TextView explain_0;

    private FrameLayout app_0;
    private FrameLayout app_1;
    private FrameLayout app_2;
    private FrameLayout app_3;
    private FrameLayout app_4;
    private FrameLayout app_5;
    private FrameLayout app_6;
    private TextView view_all_1;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView toolbarBack;

    private UserController userController;
    private PreferenceUtil preferenceUtil;

    private NestedScrollView nsv_recommend;
    private RecyclerView rv_recommend;
    private RecommendAdapter recommendAdapter;
    private List<Recommend> recommends;

    private Random random;
    private int randomNumber1;
    private int randomNumber2;


    //극혐
    private String details = "";
    private String number = "";
    private String title = "";
    private String subtitle = "";
    private String str_location = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        init();

    }

    public void init() {
        random = new Random();
        recommends = new ArrayList<>();
        preferenceUtil = new PreferenceUtil(this);
//        tip_0 = (FrameLayout) findViewById(R.id.tip_0);
//        cont_0 = (TextView) findViewById(R.id.cont_0);
//        explain_0 = (TextView) findViewById(R.id.explain_0);

        app_0 = (FrameLayout) findViewById(R.id.app_0);
        app_1 = (FrameLayout) findViewById(R.id.app_1);
        app_2 = (FrameLayout) findViewById(R.id.app_2);
        app_3 = (FrameLayout) findViewById(R.id.app_3);
        app_4 = (FrameLayout) findViewById(R.id.app_4);
        app_5 = (FrameLayout) findViewById(R.id.app_5);
        app_6 = (FrameLayout) findViewById(R.id.app_6);
        view_all_1 = (TextView) findViewById(R.id.view_all_1);
        rv_recommend = (RecyclerView) findViewById(R.id.rv_recommend);
        nsv_recommend = (NestedScrollView) findViewById(R.id.nsv_recommend);

//        tip_0.setOnClickListener(this);
        app_0.setOnClickListener(this);
        app_1.setOnClickListener(this);
        app_2.setOnClickListener(this);
        app_3.setOnClickListener(this);
        app_4.setOnClickListener(this);
        app_5.setOnClickListener(this);
        app_6.setOnClickListener(this);
        view_all_1.setOnClickListener(this);

        setToolbar();
        //getRecommendedApp();
        setRecommend();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
//            case R.id.tip_0:
//                Intent intent = new Intent(v.getContext(), RecommendDetailActivity.class);
//                intent.putExtra("title", title);
//                intent.putExtra("subtitle", subtitle);
//                intent.putExtra("detail", details);
//                startActivity(intent);
//
//                break;
            case R.id.view_all_1:
                app_1.setVisibility(View.VISIBLE);
                app_2.setVisibility(View.VISIBLE);
                app_3.setVisibility(View.VISIBLE);
                app_4.setVisibility(View.VISIBLE);
                app_5.setVisibility(View.VISIBLE);
                app_6.setVisibility(View.VISIBLE);
                view_all_1.setVisibility(View.GONE);
                break;
            case R.id.app_0:
                onClickAppGames("healthcare.demand16.breath");
                break;
            case R.id.app_1:
                onClickAppGames("healthcare.demand16.breath");
                break;
            case R.id.app_2:
                onClickAppGames("healthcare.demand16.meditation");
                break;
            case R.id.app_3:
                onClickAppGames("healthcare.demand16.meditation");
                break;
            case R.id.app_4:
                onClickAppGames("demand.healthcare.recognition");
                break;
            case R.id.app_5:
                onClickAppGames("healthcare.demand16.healingsound");
                break;
            case R.id.app_6:
                onClickAppGames("healthcare.demand16.meditation");
                break;
        }
    }

    public void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarBack = (ImageView) toolbar.findViewById(R.id.toolbar_back);
        toolbarTitle.setText("추천 컨텐츠");
        toolbarBack.setOnClickListener(this);
    }

    public void setRecommend() {
        Float active = preferenceUtil.getAverageActive();
        Float sugar = preferenceUtil.getAverageSugar();
        Float maxPressure = preferenceUtil.getAverageMaxPressure();
        Float minPressure = preferenceUtil.getAverageMinPressure();
        Float bmi = preferenceUtil.getAverageBmi();
        Float diet = preferenceUtil.getAverageDiet();


        if (active != null) {
            randomNumber1 = getRandomNumber(3);
            randomNumber2 = getRandomNumber(8);

            if (active < 8000) {
                getActiveMass1(randomNumber1);
                getActiveMass2(randomNumber2);
            }
//            if (active >= 8000) {
//
//            } else if (active >= 6000) {
//                normalACTIVE(randomNumber);
//            } else {
//                veryBadACTIVE(randomNumber);
//            }
        }

        if (sugar != null) {
            randomNumber1 = getRandomNumber(7);
            randomNumber2 = getRandomNumber(5);

            if (sugar > 99) {
                getBloodSugar1(randomNumber1);
                getBloodSugar2(randomNumber2);
            }
//            if (sugar < 100) {
//
//            } else if (sugar < 140) {
//                badBS(randomNumber);
//                getSmoking(randomNumber);
//            } else {
//                veryBadBS(randomNumber);
//                getSmoking(randomNumber);
//            }
        }

        if (bmi != null) {
            randomNumber1 = getRandomNumber(7);
            randomNumber2 = getRandomNumber(4);

            if (bmi > 24) {
                getBmi(randomNumber1);
                getDiet(randomNumber2);
            }
//            if (bmi < 25) {
//
//            } else {
//                badBMI(randomNumber);
//            }
        }

        if (maxPressure != null && minPressure != null) {
            randomNumber1 = getRandomNumber(4);
            randomNumber2 = getRandomNumber(15);

            if ((maxPressure > 120 || minPressure > 80)) {
                getBloodPressure(randomNumber1);
                getSmoking(randomNumber2);
            }
//            if ((maxPressure < 120 && minPressure < 80)) {
//
//            } else if ((maxPressure < 140 && minPressure <= 90) || (maxPressure < 160 && minPressure < 100)) {
//                badBP(randomNumber);
//                getStress(randomNumber);
//            } else {
//                veryBadBP(randomNumber);
//                getStress(randomNumber);
//            }

        }

//        if (diet != null) {
//            getRandomNumbers(4);
//
//            if (diet < 80) {
//                getDiet(randomNumber1);
//                getDiet(randomNumber2);
////                veryBadMEAL(randomNumber);
//            }
//        }

        int size = recommends.size();
        if (size < 1) {
            getRandomNumbers(8);
            getStress(randomNumber1);
        }

        setRecommendAdapterItem(recommends);
        setScrollTo(0, 0);
    }

    public void setScrollTo(final int x, final int y) {
        nsv_recommend.post(new Runnable() {
            @Override
            public void run() {
                nsv_recommend.scrollTo(x, y);
            }
        });
    }

    public void setRecommendAdapterItem(List<Recommend> recommends) {

        recommendAdapter = new RecommendAdapter(this, recommends);
        rv_recommend.setAdapter(recommendAdapter);
        rv_recommend.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }


    public void onClickAppGames(String packageName) {
        Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent == null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
        } else {
            startActivity(intent);
        }
    }

    public void getRecommendedApp() {
        final int randomNum = getRandomNumber(5);

        User user = preferenceUtil.getUserInfo();
        String accessToken = user.getAccessToken();
        int userId = user.getId();
        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);

        if (randomNum == 0) {
            Call<ArrayList<BloodSugar>> callSelectBloodSugar = userController.selectBloodSugar(userId);
            callSelectBloodSugar.enqueue(new Callback<ArrayList<BloodSugar>>() {
                @Override
                public void onResponse(Call<ArrayList<BloodSugar>> call, Response<ArrayList<BloodSugar>> response) {
                    int bloodSugar = response.body().get(0).getData();
                    if (bloodSugar < 125) {
                        normalBS(randomNum);
                    } else if (bloodSugar < 140) {
                        badBS(randomNum);
                        app_2.setVisibility(View.VISIBLE);
                    } else {
                        veryBadBS(randomNum);
                        app_2.setVisibility(View.VISIBLE);
                        app_6.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<BloodSugar>> call, Throwable t) {

                }
            });
        }
        if (randomNum == 1) {
            Call<ArrayList<BmiScore>> callSelectBmi = userController.selectBmi(userId);
            callSelectBmi.enqueue(new Callback<ArrayList<BmiScore>>() {
                @Override
                public void onResponse(Call<ArrayList<BmiScore>> call, Response<ArrayList<BmiScore>> response) {
                    float bmi = response.body().get(0).getScore();
                    if (bmi < 25) {
                        normalBMI(randomNum);
                        app_0.setVisibility(View.VISIBLE);
                        app_1.setVisibility(View.VISIBLE);
                    } else if (bmi < 30) {
                        badBMI(randomNum);
                        app_2.setVisibility(View.VISIBLE);
                    } else {
                        veryBadBMI(randomNum);
                        app_2.setVisibility(View.VISIBLE);
                        app_6.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<BmiScore>> call, Throwable t) {

                }
            });
        }
        if (randomNum == 2) {
            Call<ArrayList<BloodPressure>> callSelectBloodPressure = userController.selectBloodPressure(userId);
            callSelectBloodPressure.enqueue(new Callback<ArrayList<BloodPressure>>() {
                @Override
                public void onResponse(Call<ArrayList<BloodPressure>> call, Response<ArrayList<BloodPressure>> response) {
                    BloodPressure bloodPressure = response.body().get(0);
                    int maxData = bloodPressure.getMaxData();
                    int minData = bloodPressure.getMinData();

                    if (maxData < 140 && minData <= 90) {
                        normalBP(randomNum);
                        app_0.setVisibility(View.VISIBLE);
                        app_1.setVisibility(View.VISIBLE);
                    } else if (maxData < 160 && minData < 100) {
                        badBP(randomNum);
                    } else {
                        veryBadBP(randomNum);
                        app_6.setVisibility(View.VISIBLE);
                        app_3.setVisibility(View.VISIBLE);
                        app_4.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<BloodPressure>> call, Throwable t) {

                }
            });
        }
        if (randomNum == 3) {
            Call<ArrayList<ActiveMass>> callSelectActiveMass = userController.selectActiveMass(userId);
            callSelectActiveMass.enqueue(new Callback<ArrayList<ActiveMass>>() {
                @Override
                public void onResponse(Call<ArrayList<ActiveMass>> call, Response<ArrayList<ActiveMass>> response) {
                    int activeMass = response.body().get(0).getData();
                    if (activeMass >= 6000) {
                        normalACTIVE(randomNum);
                    } else if (activeMass >= 4000) {
                        badACTIVE(randomNum);
                    } else {
                        veryBadACTIVE(randomNum);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ActiveMass>> call, Throwable t) {

                }
            });
        }
        if (randomNum == 4) {
            Call<ArrayList<DietSelfDiagnosisScore>> callSelectDiagnosis = userController.selectDiagnosis(userId);
            callSelectDiagnosis.enqueue(new Callback<ArrayList<DietSelfDiagnosisScore>>() {
                @Override
                public void onResponse(Call<ArrayList<DietSelfDiagnosisScore>> call, Response<ArrayList<DietSelfDiagnosisScore>> response) {
                    int score = response.body().get(0).getScore();

                    if (score >= 60) {
                        app_5.setVisibility(View.VISIBLE);
                        normalMEAL(randomNum);
                    } else if (score >= 50) {
                        badMEAL(randomNum);
                    } else {
                        app_4.setVisibility(View.VISIBLE);
                        veryBadMEAL(randomNum);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<DietSelfDiagnosisScore>> call, Throwable t) {

                }
            });
        }
    }

    public void getRandomNumbers(int maxNumber) {
        randomNumber1 = getRandomNumber(maxNumber);
        randomNumber2 = getRandomNumber(maxNumber);

        if (randomNumber1 == randomNumber2) {
            while (true) {
                randomNumber2 = getRandomNumber(maxNumber);
                if (randomNumber1 != randomNumber2) {
                    break;
                }
            }
        }

    }

    public int getRandomNumber(int maxNumber) {
        return random.nextInt(maxNumber);
    }

    public void getStress(int randomNumber) {
        switch (randomNumber) {
            case 0:
                title = etc.notes.category_0(3);
                subtitle = etc.notes.category_1(22);
                details = etc.notes.category_3(190) + "\n" + etc.notes.category_3(191);
                break;
            case 1:
                title = etc.notes.category_0(3);
                subtitle = etc.notes.category_1(23);
                details = etc.notes.category_3(192) + "\n" + etc.notes.category_2(54) + "\n" + etc.notes.category_3(193) + "\n" + etc.notes.category_2(55) + "\n" + etc.notes.category_3(194)
                        + "\n" + etc.notes.category_2(56) + "\n" + etc.notes.category_3(195) + "\n" + etc.notes.category_3(196) + "\n" + etc.notes.category_3(197) + "\n" + etc.notes.category_3(198);
                break;
            case 2:
                title = etc.notes.category_0(3);
                subtitle = etc.notes.category_1(24);
                details = etc.notes.category_3(199) + "\n" + etc.notes.category_3(200) + "\n" + etc.notes.category_3(201) + "\n" + etc.notes.category_3(202) + "\n" + etc.notes.category_3(203);
                break;
            case 3:
                title = etc.notes.category_0(3);
                subtitle = etc.notes.category_1(25);
                details = etc.notes.category_3(204) + "\n" + etc.notes.category_3(205) + "\n" + etc.notes.category_3(206) + "\n" + etc.notes.category_3(207) + "\n" + etc.notes.category_3(208)
                        + "\n" + etc.notes.category_3(209);
                break;
            case 4:
                title = etc.notes.category_0(3);
                subtitle = etc.notes.category_1(26);
                details = etc.notes.category_3(221) + "\n" + etc.notes.category_3(222) + "\n" + etc.notes.category_3(223) + "\n" + etc.notes.category_3(224) + "\n" + etc.notes.category_3(225)
                        + "\n" + etc.notes.category_3(226) + "\n" + etc.notes.category_3(227) + "\n" + etc.notes.category_3(228);
                break;
            case 5:
                title = etc.notes.category_0(3);
                subtitle = etc.notes.category_1(27);
                details = etc.notes.category_3(229) + "\n" + etc.notes.category_3(230) + "\n" + etc.notes.category_3(231) + "\n" + etc.notes.category_3(232) + "\n" + etc.notes.category_3(233)
                        + "\n" + etc.notes.category_3(234);
                break;

            case 6:
                title = etc.notes.category_0(3);
                subtitle = etc.notes.category_1(28);
                details = etc.notes.category_2(76) + "\n" + etc.notes.category_3(235) + "\n\n " + etc.notes.category_2(77) + "\n" + etc.notes.category_3(236);
                break;

            default:
                title = etc.notes.category_0(3);
                subtitle = etc.notes.category_1(29);
                details = etc.notes.category_2(78) + "\n" + etc.notes.category_3(237) + "\n\n " + etc.notes.category_2(79) + "\n" + etc.notes.category_3(238);
                break;
        }

        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);

    }

    public void getSmoking(int randomNumber) {
        switch (randomNumber) {
            case 0:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(30);
                details = etc.notes.category_3(239) + "\n" + etc.notes.category_3(240) + "\n" + etc.notes.category_3(241) + "\n" + etc.notes.category_3(242);
                break;
            case 1:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(31);
                details = etc.notes.category_2(80) + "\n" + etc.notes.category_3(243) + "\n" + etc.notes.category_3(244) + "\n" + etc.notes.category_3(245)
                        + "\n\n" + etc.notes.category_2(81) + "\n" + etc.notes.category_3(246) + "\n" + etc.notes.category_3(247)
                        + "\n\n" + etc.notes.category_2(82) + "\n" + etc.notes.category_3(248) + "\n" + etc.notes.category_3(249)
                        + "\n\n" + etc.notes.category_2(83) + "\n" + etc.notes.category_3(250) + "\n" + etc.notes.category_3(251) + "\n" + etc.notes.category_3(252);

                break;
            case 2:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(32);
                details = etc.notes.category_3(253);
                break;
            case 3:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(33);
                details = etc.notes.category_2(84) + "\n" + etc.notes.category_3(254)
                        + "\n\n" + etc.notes.category_2(85) + "\n" + etc.notes.category_3(255)
                        + "\n\n" + etc.notes.category_2(86) + "\n" + etc.notes.category_3(256)
                        + "\n\n" + etc.notes.category_2(87) + "\n" + etc.notes.category_3(257)
                        + "\n\n" + etc.notes.category_2(88) + "\n" + etc.notes.category_3(258);

                break;
            case 4:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(34);
                details = etc.notes.category_2(89) + "\n" + etc.notes.category_3(259) + "\n" + etc.notes.category_3(260)
                        + "\n\n" + etc.notes.category_2(90) + "\n" + etc.notes.category_3(261) + "\n" + etc.notes.category_3(262)
                        + "\n\n" + etc.notes.category_2(91) + "\n" + etc.notes.category_3(263)
                        + "\n\n" + etc.notes.category_2(92) + "\n" + etc.notes.category_3(264) + "\n" + etc.notes.category_3(265)
                        + "\n\n" + etc.notes.category_2(93) + "\n" + etc.notes.category_3(266) + "\n" + etc.notes.category_3(267)
                        + "\n\n" + etc.notes.category_2(94) + "\n" + etc.notes.category_3(268)
                        + "\n\n" + etc.notes.category_2(95) + "\n" + etc.notes.category_3(269) + "\n" + etc.notes.category_3(270)
                        + "\n\n" + etc.notes.category_2(96) + "\n" + etc.notes.category_3(271) + "\n" + etc.notes.category_3(272)
                        + "\n\n" + etc.notes.category_2(97) + "\n" + etc.notes.category_3(273);


                break;
            case 5:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(35);
                details = etc.notes.category_3(274) + "\n" + etc.notes.category_3(275) + "\n" + etc.notes.category_3(276) + "\n" + etc.notes.category_3(277) + "\n" + etc.notes.category_3(278)
                        + "\n" + etc.notes.category_3(279);
                break;

            case 6:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(36);
                details = etc.notes.category_3(280) + "\n" + etc.notes.category_3(281) + "\n" + etc.notes.category_3(282) + "\n" + etc.notes.category_3(283) + "\n" + etc.notes.category_3(284);

                break;

            case 7:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(37);
                details = etc.notes.category_2(98) + "\n" + etc.notes.category_3(285)
                        + "\n\n" + etc.notes.category_2(99) + "\n" + etc.notes.category_3(286) + "\n" + etc.notes.category_3(287) + "\n" + etc.notes.category_3(288)
                        + "\n\n" + etc.notes.category_2(100) + "\n" + etc.notes.category_3(289)
                        + "\n\n" + etc.notes.category_2(101) + "\n" + etc.notes.category_3(290)
                        + "\n\n" + etc.notes.category_2(102) + "\n" + etc.notes.category_3(291);

                break;

            case 8:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(38);
                details = etc.notes.category_2(103) + "\n" + etc.notes.category_3(292)
                        + "\n\n" + etc.notes.category_2(104) + "\n" + etc.notes.category_3(293) + "\n" + etc.notes.category_3(294);
                break;

            case 9:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(39);
                details = etc.notes.category_2(105) + "\n" + etc.notes.category_3(295)
                        + "\n\n" + etc.notes.category_2(106) + "\n" + etc.notes.category_3(296) + "\n" + etc.notes.category_3(297) + "\n" + etc.notes.category_3(298) + "\n" + etc.notes.category_3(299);
                break;

            case 10:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(40);
                details = etc.notes.category_3(300) + "\n" + etc.notes.category_3(301) + "\n" + etc.notes.category_3(302) + "\n" + etc.notes.category_3(303) + "\n" + etc.notes.category_3(304) + "\n" + etc.notes.category_3(305) + "\n" + etc.notes.category_3(306);
                break;

            case 11:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(41);
                details = etc.notes.category_3(307) + "\n" + etc.notes.category_3(308) + "\n" + etc.notes.category_3(309) + "\n" + etc.notes.category_3(310) + "\n" + etc.notes.category_3(311) + "\n" + etc.notes.category_3(312);
                break;

            case 12:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(42);
                details = "";

                for (int i = 313; i < 328; i++) {
                    details += etc.notes.category_3(i) + "\n";
                }

                break;

            case 13:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(43);
                details = etc.notes.category_2(107) + "\n" + etc.notes.category_3(328)
                        + "\n\n" + etc.notes.category_2(108) + "\n" + etc.notes.category_3(329) + "\n" + etc.notes.category_3(330) + "\n" + etc.notes.category_3(331) + "\n" + etc.notes.category_3(332);
                break;

            default:
                title = etc.notes.category_0(4);
                subtitle = etc.notes.category_1(44);
                details = etc.notes.category_3(333) + "\n" + etc.notes.category_3(334) + "\n" + etc.notes.category_3(335) + "\n" + etc.notes.category_3(336) + "\n" + etc.notes.category_3(337);
                break;
        }

        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);
    }

    public void getBmi(int randomNumber) {
        switch (randomNumber) {
            case 0:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(0);
                details = etc.notes.category_3(0) + "\n\n" + etc.notes.category_3(1) + "\n\n" + etc.notes.category_3(2);
                break;

            case 1:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(1);
                details = etc.notes.category_3(3) + "\n\n" + etc.notes.category_3(4) + "\n\n" + etc.notes.category_3(5) + "\n\n" + etc.notes.category_3(6) + "\n\n" + etc.notes.category_3(7) + "\n\n" + etc.notes.category_3(8);
                break;

            case 2:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(2);
                details = etc.notes.category_2(0) + "\n\n" + etc.notes.category_3(10) + "\n\n" + etc.notes.category_2(1) + "\n\n" + etc.notes.category_3(11);
                break;

            case 3:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(3);
                details = etc.notes.category_2(2) + "\n" + etc.notes.category_3(12) +
                        "\n\n" + etc.notes.category_2(3) + "\n" + etc.notes.category_3(13) + "\n" + etc.notes.category_2(4) + "\n" + etc.notes.category_3(14) +
                        "\n\n" + etc.notes.category_2(5) + "\n" + etc.notes.category_3(15) +
                        "\n\n" + etc.notes.category_2(6) + "\n" + etc.notes.category_3(16) + "\n" + etc.notes.category_3(17);
                break;

            case 4:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(4);
                details = etc.notes.category_2(7) + "\n" + etc.notes.category_3(18) +
                        "\n\n" + etc.notes.category_2(8) + "\n" + etc.notes.category_3(19) +
                        "\n\n" + etc.notes.category_2(9) + "\n" + etc.notes.category_3(20) +
                        "\n\n" + etc.notes.category_2(10) + "\n" + etc.notes.category_3(21) +
                        "\n\n" + etc.notes.category_3(22) + "\n" + etc.notes.category_3(23) +
                        "\n\n" + etc.notes.category_2(11) + "\n" + etc.notes.category_3(24) + "\n" + etc.notes.category_3(25) +
                        "\n\n" + etc.notes.category_2(12) + "\n" + etc.notes.category_3(26) + "\n" + etc.notes.category_3(27);
                break;

            case 5:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(5);
                details = etc.notes.category_3(28) + "\n\n" + etc.notes.category_3(29) + "\n\n" + etc.notes.category_3(30) + "\n\n" + etc.notes.category_3(31) +
                        "\n\n" + etc.notes.category_3(32) + "\n\n" + etc.notes.category_3(33) + "\n\n" + etc.notes.category_3(34);
                break;

            default:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(6);
                details = etc.notes.category_3(35);
                break;
        }

        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);
    }

    public void getDiet(int randomNumber) {
        switch (randomNumber) {
            case 0:
                title = etc.notes.category_0(1);
                subtitle = etc.notes.category_1(7);
                details = etc.notes.category_2(13) + "\n" + etc.notes.category_3(36) +
                        "\n\n" + etc.notes.category_2(14) + "\n" + etc.notes.category_3(37) +
                        "\n\n" + etc.notes.category_2(15) + "\n" + etc.notes.category_3(38);
                break;

            case 1:
                title = etc.notes.category_0(1);
                subtitle = etc.notes.category_1(8);
                details = etc.notes.category_2(16) + "\n\n" + etc.notes.category_3(39) +
                        "\n\n" + etc.notes.category_2(17) + "\n" + etc.notes.category_3(40) +
                        "\n\n" + etc.notes.category_2(18) + "\n" + etc.notes.category_3(41) +
                        "\n\n" + etc.notes.category_2(19) + "\n" + etc.notes.category_3(42) +
                        "\n\n" + etc.notes.category_2(20) + "\n" + etc.notes.category_3(43);
                break;

            case 2:
                title = etc.notes.category_0(1);
                subtitle = etc.notes.category_1(9);
                details = etc.notes.category_3(44) + "\n\n" + etc.notes.category_3(45) + "\n\n" + etc.notes.category_3(46);
                break;

            default:
                title = etc.notes.category_0(1);
                subtitle = etc.notes.category_1(10);
                details = etc.notes.category_2(21) + "\n" + etc.notes.category_3(47) +
                        "\n\n" + etc.notes.category_2(22) + "\n" + etc.notes.category_3(48) + "\n" + etc.notes.category_3(49) +
                        "\n\n" + etc.notes.category_2(23) + "\n" + etc.notes.category_3(50) +
                        "\n\n" + etc.notes.category_2(24) + "\n" + etc.notes.category_3(51) +
                        "\n\n" + etc.notes.category_2(25) + "\n" + etc.notes.category_3(52) +
                        "\n\n" + etc.notes.category_2(26) + "\n" + etc.notes.category_3(53) +
                        "\n\n" + etc.notes.category_2(27) + "\n" + etc.notes.category_3(54) +
                        "\n\n" + etc.notes.category_2(28) + "\n" + etc.notes.category_3(55) +
                        "\n\n" + etc.notes.category_2(29) + "\n" + etc.notes.category_3(56) +
                        "\n\n" + etc.notes.category_2(30) + "\n" + etc.notes.category_3(57) + "\n" + etc.notes.category_3(58);
                break;
        }

        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);
    }

    public void getBloodSugar1(int randomNumber) {
        title = etc.notes.category_0(6) +"1";

        switch (randomNumber) {
            case 0:
                subtitle = etc.notes.category_2(38);
                details = etc.notes.category_3(85) + "\n" + etc.notes.category_3(86) + "\n" + etc.notes.category_3(87) +
                        "\n\n" + etc.notes.category_2(39) + "\n" + etc.notes.category_3(88) + "\n" + etc.notes.category_3(89) + "\n" + etc.notes.category_3(90) + "\n" + etc.notes.category_3(91) +
                        "\n" + etc.notes.category_3(92) + "\n" + etc.notes.category_3(93) + "\n" + etc.notes.category_3(94) + "\n" + etc.notes.category_3(95) +
                        "\n" + etc.notes.category_3(96) + "\n" + etc.notes.category_3(97) + "\n" + etc.notes.category_3(98) + "\n" + etc.notes.category_3(99) + "\n" + etc.notes.category_3(100) +
                        "\n" + etc.notes.category_3(101) + "\n" + etc.notes.category_3(102);
                break;

            case 1:
                subtitle = etc.notes.category_1(48);
                details = etc.notes.category_3(358) + "\n\n" + etc.notes.category_3(359) + "\n\n" + etc.notes.category_3(360) + "\n\n" + etc.notes.category_3(361) + "\n\n" + etc.notes.category_3(362) + "\n\n" + etc.notes.category_3(363);
                break;

            case 2:
                subtitle = etc.notes.category_1(49);
                details = etc.notes.category_3(364) + "\n\n" + etc.notes.category_3(365) + "\n\n" + etc.notes.category_3(366);
                break;

            case 3:
                subtitle = etc.notes.category_1(50);
                details = etc.notes.category_3(367) + "\n\n" + etc.notes.category_3(368) + "\n\n" + etc.notes.category_3(369);
                break;

            case 4:
                subtitle = etc.notes.category_1(51);
                details = etc.notes.category_2(111) + "\n" + etc.notes.category_3(370) + "\n" + etc.notes.category_3(371) + "\n" + etc.notes.category_3(372) +
                        "\n\n" + etc.notes.category_2(112) + "\n" + etc.notes.category_3(373) + "\n" + etc.notes.category_3(374) + "\n" + etc.notes.category_3(375);
                break;

            case 5:
                subtitle = etc.notes.category_1(52);
                details = etc.notes.category_2(113) + "\n" + etc.notes.category_3(376) +
                        "\n\n" + etc.notes.category_2(114) + "\n" + etc.notes.category_3(377) + "\n" + etc.notes.category_3(378) + "\n" + etc.notes.category_3(379);
                break;


            default:
                subtitle = etc.notes.category_1(53);
                details = etc.notes.category_2(115) + "\n" + etc.notes.category_3(380) + "\n" + etc.notes.category_3(381) + "\n" + etc.notes.category_3(382) +
                        "\n\n" + etc.notes.category_2(116) + "\n" + etc.notes.category_3(383) +
                        "\n\n" + etc.notes.category_2(117) + "\n" + etc.notes.category_3(384);
                break;

        }

        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);
    }

    public void getBloodSugar2(int randomNumber) {
        title = etc.notes.category_0(6) +"2";

        switch (randomNumber) {
            case 0:
                subtitle = etc.notes.category_1(54);
                details = etc.notes.category_2(118) + "\n" + etc.notes.category_3(385) + "\n" + etc.notes.category_2(119) + "\n" + etc.notes.category_3(386) + "\n" + etc.notes.category_3(387) +
                        "\n\n" + etc.notes.category_2(120) + "\n" + etc.notes.category_3(388) + "\n" + etc.notes.category_3(389) + "\n" + etc.notes.category_3(390) +
                        "\n\n" + etc.notes.category_2(121) + "\n" + etc.notes.category_3(391) + "\n" + etc.notes.category_2(122) + "\n" + etc.notes.category_3(392) +
                        "\n\n" + etc.notes.category_2(123) + "\n" + etc.notes.category_3(393) + "\n" + etc.notes.category_3(394) + "\n" + etc.notes.category_2(124) + "\n" + etc.notes.category_3(395) +
                        "\n\n" + etc.notes.category_3(396) + "\n" + etc.notes.category_3(397);
                break;

            case 1:
                subtitle = etc.notes.category_1(55);
                details = etc.notes.category_3(398) + "\n\n" + etc.notes.category_3(399);
                break;

            case 2:
                subtitle = etc.notes.category_1(56);
                details = etc.notes.category_2(125) + "\n" + etc.notes.category_3(400) + "\n" + etc.notes.category_3(401) + "\n" + etc.notes.category_3(402)
                        + "\n\n" + etc.notes.category_2(126) + "\n" + etc.notes.category_3(403) + "\n" + etc.notes.category_3(404) + "\n" + etc.notes.category_3(405);
                break;

            case 3:
                subtitle = etc.notes.category_1(57);
                details = etc.notes.category_3(406) + "\n\n" + etc.notes.category_3(407) + "\n\n" + etc.notes.category_3(408);
                break;

            default:
                subtitle = etc.notes.category_1(58);
                details = etc.notes.category_3(409) + "\n\n" + etc.notes.category_3(410) + "\n\n" + etc.notes.category_3(411);
                break;
        }

        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);
    }


    public void getBloodPressure(int randomNumber) {
        switch (randomNumber) {
            case 0:
                title = etc.notes.category_0(5);
                subtitle = etc.notes.category_2(37);
                details = etc.notes.category_2(37) + "\n\n" + etc.notes.category_3(81) + "\n\n" + etc.notes.category_3(82) + "\n\n" + etc.notes.category_3(83) + "\n\n" + etc.notes.category_3(84);
                break;

            case 1:
                title = etc.notes.category_0(5);
                subtitle = etc.notes.category_1(45);
                details = etc.notes.category_3(338) + "\n\n" + etc.notes.category_3(339) + "\n\n" + etc.notes.category_3(340) + "\n\n" + etc.notes.category_3(341);
                break;

            case 2:
                title = etc.notes.category_0(5);
                subtitle = etc.notes.category_1(46);
                details = etc.notes.category_2(109) + "\n\n" + etc.notes.category_3(342) + "\n\n" + etc.notes.category_3(343) + "\n\n" + etc.notes.category_3(344) + "\n\n" + etc.notes.category_3(345) +
                        "\n" + etc.notes.category_2(110) + "\n\n" + etc.notes.category_3(346) + "\n\n" + etc.notes.category_3(347) + "\n\n" + etc.notes.category_3(348);
                break;

            default:
                title = etc.notes.category_0(5);
                subtitle = etc.notes.category_1(47);
                details = etc.notes.category_3(349) + "\n\n" + etc.notes.category_3(350) + "\n\n" + etc.notes.category_3(351) + "\n\n" + etc.notes.category_3(352) + "\n\n" + etc.notes.category_3(353)
                        + "\n\n" + etc.notes.category_3(354) + "\n\n" + etc.notes.category_3(355) + "\n\n" + etc.notes.category_3(356) + "\n\n" + etc.notes.category_3(357);
                break;

        }

        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);

    }

    public void getActiveMass1(int randomNumber) {
        title = etc.notes.category_0(2) +"1";

        switch (randomNumber) {
            case 0:
                subtitle = etc.notes.category_1(11);
                details = etc.notes.category_2(31) + "\n" + etc.notes.category_3(59) + "\n" + etc.notes.category_3(60) + "\n" + etc.notes.category_3(61) + "\n" + etc.notes.category_3(62) +
                        "\n\n" + etc.notes.category_2(32) + "\n" + etc.notes.category_3(63) + "\n" + etc.notes.category_3(64) + "\n" + etc.notes.category_3(65) + "\n" + etc.notes.category_3(66) + "\n" + etc.notes.category_3(67) +
                        "\n\n" + etc.notes.category_2(33) + "\n" + etc.notes.category_3(68) + "\n" + etc.notes.category_3(69);
                break;

            case 1:
                subtitle = etc.notes.category_1(12);
                details = etc.notes.category_2(34) + "\n" + etc.notes.category_3(70) + "\n" + etc.notes.category_3(71) +
                        "\n\n" + etc.notes.category_2(35) + "\n" + etc.notes.category_3(72) + "\n" + etc.notes.category_3(73);
                break;

            default:
                subtitle = etc.notes.category_1(13);
                details = etc.notes.category_2(36) + "\n" + etc.notes.category_3(74) + "\n" + etc.notes.category_3(75) + "\n" + etc.notes.category_3(76) + "\n" + etc.notes.category_3(77) + "\n" + etc.notes.category_3(78) + "\n" + etc.notes.category_3(79) + "\n" + etc.notes.category_3(80) +
                        "\n\n" + etc.notes.category_2(37) + "\n" + etc.notes.category_3(81) + "\n" + etc.notes.category_3(82) + "\n" + etc.notes.category_3(83) + "\n" + etc.notes.category_3(84) +
                        "\n\n" + etc.notes.category_2(40) + "\n" + etc.notes.category_3(103) + "\n" + etc.notes.category_3(104) + "\n" + etc.notes.category_3(105) + "\n" + etc.notes.category_3(106) + "\n" + etc.notes.category_3(107) + "\n" + etc.notes.category_3(108) +
                        "\n\n" + etc.notes.category_2(41) + "\n" + etc.notes.category_3(109) + "\n" + etc.notes.category_3(110) + "\n" + etc.notes.category_3(111) + "\n" + etc.notes.category_3(112) + "\n" + etc.notes.category_3(113) +
                        "\n\n" + etc.notes.category_2(42) + "\n" + etc.notes.category_3(114) + "\n" + etc.notes.category_3(115) + "\n" + etc.notes.category_3(116) +
                        "\n\n" + etc.notes.category_2(43) + "\n" + etc.notes.category_3(117) + "\n" + etc.notes.category_3(118) + "\n" + etc.notes.category_3(119) + "\n" + etc.notes.category_3(120) +
                        "\n\n" + etc.notes.category_2(38) + "\n" + etc.notes.category_3(85) + "\n" + etc.notes.category_3(86) + "\n" + etc.notes.category_3(87) +
                        "\n\n" + etc.notes.category_2(39);

                for (int i = 88; i < 103; i++) {
                    details += etc.notes.category_3(i) + "\n";
                }

                break;

        }

        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);
    }

    public void getActiveMass2(int randomNumber) {
        title = etc.notes.category_0(2) +"2";

        switch (randomNumber) {
            case 0:
                subtitle = etc.notes.category_1(14);
                details = etc.notes.category_3(121) + "\n\n" + etc.notes.category_3(122) + "\n\n" + etc.notes.category_3(123) + "\n\n" + etc.notes.category_3(124) + "\n\n" + etc.notes.category_3(125) + "\n\n" + etc.notes.category_3(126);
                break;

            case 1:
                subtitle = etc.notes.category_1(15);
                details = etc.notes.category_3(127) + "\n\n" + etc.notes.category_3(128) + "\n\n" + etc.notes.category_2(44) + "\n\n" + etc.notes.category_3(129) + "\n\n" + etc.notes.category_3(130) + "\n\n" + etc.notes.category_3(131) +
                        "\n\n" + etc.notes.category_3(132) + "\n\n" + etc.notes.category_3(133) + "\n\n" + etc.notes.category_3(134);
                break;

            case 2:
                subtitle = etc.notes.category_1(16);
                details = etc.notes.category_3(135) + "\n\n" + etc.notes.category_3(136) + "\n\n" + etc.notes.category_3(137) + "\n\n" + etc.notes.category_3(138) + "\n\n" + etc.notes.category_3(139);
                break;

            case 3:
                subtitle = etc.notes.category_1(17);
                details = etc.notes.category_3(140) + "\n\n" + etc.notes.category_3(141);
                break;

            case 4:
                subtitle = etc.notes.category_1(18);
                details = etc.notes.category_3(142) + "\n\n" + etc.notes.category_3(143) + "\n\n" + etc.notes.category_3(144) + "\n\n" + etc.notes.category_3(145) + "\n\n" + etc.notes.category_3(146) +
                        "\n\n" + etc.notes.category_3(147) + "\n\n" + etc.notes.category_3(148);
                break;

            case 5:
                subtitle = etc.notes.category_1(19);
                details = etc.notes.category_3(149) + "\n\n" + etc.notes.category_3(150) + "\n\n" + etc.notes.category_3(151) + "\n\n" + etc.notes.category_3(152) + "\n\n" + etc.notes.category_3(153) +
                        "\n\n" + etc.notes.category_3(154);
                break;

            case 6:
                subtitle = etc.notes.category_1(20);
                details = etc.notes.category_3(155) + "\n\n" + etc.notes.category_3(156) + "\n\n" + etc.notes.category_3(157) + "\n\n" + etc.notes.category_3(158) + "\n\n" + etc.notes.category_3(159) +
                        "\n\n" + etc.notes.category_3(160) + "\n\n" + etc.notes.category_3(161) + "\n\n" + etc.notes.category_3(162) + "\n\n" + etc.notes.category_3(163);
                break;

            default:
                subtitle = etc.notes.category_1(21);
                details = etc.notes.category_2(45) + "\n" + etc.notes.category_3(164) + "\n" + etc.notes.category_3(165) + "\n" + etc.notes.category_3(166) + "\n" + etc.notes.category_3(167) +
                        "\n\n" + etc.notes.category_2(46) + "\n" + etc.notes.category_3(168) + "\n" + etc.notes.category_3(169) + "\n" + etc.notes.category_3(170) +
                        "\n\n" + etc.notes.category_2(47) + "\n" + etc.notes.category_3(171) + "\n" + etc.notes.category_3(172) +
                        "\n\n" + etc.notes.category_2(48) + "\n" + etc.notes.category_3(173) + "\n" + etc.notes.category_3(174) + "\n" + etc.notes.category_3(175) + "\n" + etc.notes.category_3(176) +
                        "\n\n" + etc.notes.category_2(49) + "\n" + etc.notes.category_3(177) + "\n" + etc.notes.category_3(178) +
                        "\n\n" + etc.notes.category_2(50) + "\n" + etc.notes.category_3(179) + "\n" + etc.notes.category_3(180) +
                        "\n\n" + etc.notes.category_2(51) + "\n" + etc.notes.category_3(181) + "\n" + etc.notes.category_3(182) +
                        "\n\n" + etc.notes.category_3(52) + "\n" + etc.notes.category_3(183) + "\n" + etc.notes.category_3(184) +
                        "\n\n" + etc.notes.category_3(53) + "\n" + etc.notes.category_3(185) + "\n" + etc.notes.category_3(186) +
                        "\n\n" + etc.notes.category_3(54) + "\n" + etc.notes.category_3(187) + "\n" + etc.notes.category_3(188) + "\n" + etc.notes.category_3(189);
                break;

        }

        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);
    }
    /////////// 극혐
    public void veryBadBMI(int index) {

        switch (index) {
            /// BMI START
            case 0:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(1);
                str_location = "";
                details = etc.notes.category_3(3) + "\n\n" + etc.notes.category_3(4) + "\n\n" + etc.notes.category_3(5) + "\n\n" + etc.notes.category_3(6) + "\n\n" + etc.notes.category_3(7) + "\n\n" + etc.notes.category_3(8);
                number = "";
                break;

            case 1:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(2);
                str_location = "";
                details = etc.notes.category_2(0) + "\n\n" + etc.notes.category_3(10) + "\n\n" + etc.notes.category_2(1) + "\n\n" + etc.notes.category_3(11);
                number = "";
                break;

            case 2:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(3);
                str_location = "";
                details = etc.notes.category_2(2) + "\n\n" + etc.notes.category_3(12) + "\n\n" + etc.notes.category_2(3) +
                        "\n\n" + etc.notes.category_3(13) + "\n\n" + etc.notes.category_2(4) + "\n\n" + etc.notes.category_3(14) +
                        "\n\n" + etc.notes.category_2(5) + "\n\n" + etc.notes.category_3(15) + "\n\n" + etc.notes.category_2(6) + "\n\n" + etc.notes.category_3(16) + "\n\n" + etc.notes.category_3(17);
                number = "";
                break;

            case 3:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(4);
                str_location = "";
                details = etc.notes.category_2(7) + "\n\n" + etc.notes.category_3(18) + "\n\n" + etc.notes.category_2(8) + "\n\n" + etc.notes.category_3(19) + "\n\n" + etc.notes.category_2(9) + "\n\n" + etc.notes.category_3(20) +
                        "\n\n" + etc.notes.category_2(10) + "\n\n" + etc.notes.category_3(21) + "\n\n" + etc.notes.category_3(22) + "\n\n" + etc.notes.category_3(23) +
                        "\n\n" + etc.notes.category_2(11) + "\n\n" + etc.notes.category_3(24) + "\n\n" + etc.notes.category_3(25) +
                        "\n\n" + etc.notes.category_2(12) + "\n\n" + etc.notes.category_3(26) + "\n\n" + etc.notes.category_3(27);
                number = "";
                break;

            case 4:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(5);
                str_location = "";
                details = etc.notes.category_3(28) + "\n\n" + etc.notes.category_3(29) + "\n\n" + etc.notes.category_3(30) + "\n\n" + etc.notes.category_3(31) +
                        "\n\n" + etc.notes.category_3(32) + "\n\n" + etc.notes.category_3(33) + "\n\n" + etc.notes.category_3(34) + "\n\n" + etc.notes.category_1(6) + "\n\n" + etc.notes.category_3(35);
                number = "";
                break;

            case 5:
                title = etc.notes.category_0(0);
                subtitle = "비만과 운동";
                str_location = "";
                details = etc.notes.category_2(40) + "\n\n" + etc.notes.category_3(103) + "\n\n" + etc.notes.category_3(104) + "\n\n" + etc.notes.category_3(105) +
                        "\n\n" + etc.notes.category_3(106) + "\n\n" + etc.notes.category_3(107) + "\n\n" + etc.notes.category_3(108);
                number = "";
                break;

            /// BMI END
        }

//        cont_0.setText(title);
//        explain_0.setText(subtitle);

        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);

        recommends.add(recommend);
    }

    public void veryBadBP(int index) {
        switch (index) {

            /// BLOOD PRESSURE START
            case 0:
                title = etc.notes.category_0(5);
                subtitle = "고혈압과 운동";
                str_location = "";
                details = etc.notes.category_2(37) + "\n\n" + etc.notes.category_3(81) + "\n\n" + etc.notes.category_3(82) + "\n\n" + etc.notes.category_3(83) + "\n\n" + etc.notes.category_3(84);
                number = "";
                break;

            case 1:
                title = etc.notes.category_0(5);
                subtitle = etc.notes.category_1(45);
                str_location = "";
                details = etc.notes.category_3(338) + "\n\n" + etc.notes.category_3(339) + "\n\n" + etc.notes.category_3(340) + "\n\n" + etc.notes.category_3(341);
                number = "";
                break;

            default:
                title = etc.notes.category_0(5);
                subtitle = etc.notes.category_1(46);
                str_location = "";
                details = etc.notes.category_2(109) + "\n\n" + etc.notes.category_3(342) + "\n\n" + etc.notes.category_3(343) + "\n\n" + etc.notes.category_3(344) + "\n\n" + etc.notes.category_3(345) +
                        "\n" + etc.notes.category_2(110) + "\n\n" + etc.notes.category_3(346) + "\n\n" + etc.notes.category_3(347) + "\n\n" + etc.notes.category_3(348);
                number = "";
                break;

            /// BLOOD PRESSURE END
        }

//        cont_0.setText(title);
//        explain_0.setText(subtitle);

        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);

    }

    public void veryBadBS(int index) {
        switch (index) {
            /// BLOOD SUGAR START

            case 0:
                title = etc.notes.category_0(6);
                subtitle = "당뇨병과 운동";
                str_location = "";
                details = etc.notes.category_3(85) + "\n\n" + etc.notes.category_3(86) + "\n\n" + etc.notes.category_3(87) + "\n\n" + etc.notes.category_2(39) + "\n\n" + etc.notes.category_3(88) + "\n\n" + etc.notes.category_3(89) +
                        "\n\n" + etc.notes.category_3(90) + "\n\n" + etc.notes.category_3(91) + "\n\n" + etc.notes.category_3(92) + "\n\n" + etc.notes.category_3(93) + "\n\n" + etc.notes.category_3(94) + "\n\n" + etc.notes.category_3(95) +
                        "\n\n" + etc.notes.category_3(96) + "\n\n" + etc.notes.category_3(97) + "\n\n" + etc.notes.category_3(98) + "\n\n" + etc.notes.category_3(99) + "\n\n" + etc.notes.category_3(100) + "\n\n" + etc.notes.category_3(101) +
                        "\n\n" + etc.notes.category_3(102);
                number = "";
                break;

            case 1:
                title = etc.notes.category_0(6);
                subtitle = etc.notes.category_1(48);
                str_location = "";
                details = etc.notes.category_3(358) + "\n\n" + etc.notes.category_3(359) + "\n\n" + etc.notes.category_3(360) + "\n\n" + etc.notes.category_3(361) + "\n\n" + etc.notes.category_3(362) + "\n\n" + etc.notes.category_3(363);
                number = "";
                break;

            case 2:
                title = etc.notes.category_0(6);
                subtitle = etc.notes.category_1(49);
                str_location = "";
                details = etc.notes.category_3(364) + "\n\n" + etc.notes.category_3(365) + "\n\n" + etc.notes.category_3(366);
                number = "";
                break;

            case 3:
                title = etc.notes.category_0(6);
                subtitle = etc.notes.category_1(50);
                str_location = "";
                details = etc.notes.category_3(367) + "\n\n" + etc.notes.category_3(368) + "\n\n" + etc.notes.category_3(369);
                number = "";
                break;

            case 4:
                title = etc.notes.category_0(6);
                subtitle = etc.notes.category_1(53);
                str_location = "";
                details = etc.notes.category_2(115) + "\n\n" + etc.notes.category_3(380) + "\n\n" + etc.notes.category_3(381) + "\n\n" + etc.notes.category_3(382) + "\n\n" + etc.notes.category_2(116) + "\n\n" + etc.notes.category_3(383) +
                        "\n\n" + etc.notes.category_2(117) + "\n\n" + etc.notes.category_3(384);
                number = "";
                break;

            default:
                title = etc.notes.category_0(6);
                subtitle = etc.notes.category_1(54);
                str_location = "";
                details = etc.notes.category_2(118) + "\n\n" + etc.notes.category_3(385) + "\n\n" + etc.notes.category_2(119) + "\n\n" + etc.notes.category_3(386) + "\n\n" + etc.notes.category_3(387) +
                        "\n\n" + etc.notes.category_2(120) + "\n\n" + etc.notes.category_3(388) + "\n\n" + etc.notes.category_3(389) + "\n\n" + etc.notes.category_3(390) +
                        "\n\n" + etc.notes.category_2(121) + "\n\n" + etc.notes.category_3(391) + "\n\n" + etc.notes.category_2(122) + "\n\n" + etc.notes.category_3(392) +
                        "\n\n" + etc.notes.category_2(123) + "\n\n" + etc.notes.category_3(393) + "\n\n" + etc.notes.category_3(394) + "\n\n" + etc.notes.category_2(124) + "\n\n" + etc.notes.category_3(395) +
                        "\n\n" + etc.notes.category_3(396) + "\n\n" + etc.notes.category_3(397);
                number = "";
                break;
            /// BLOOD SUGAR END
        }

//        cont_0.setText(title);
//        explain_0.setText(subtitle);
        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);
    }

    public void veryBadACTIVE(int index) {

        switch (index) {
            /// ACTIVITY START

            case 0:
                title = etc.notes.category_0(2);
                subtitle = etc.notes.category_1(11);
                str_location = "";
                details = etc.notes.category_2(31) + "\n\n" + etc.notes.category_3(59) + "\n\n" + etc.notes.category_3(60) + "\n\n" + etc.notes.category_3(61) + "\n\n" + etc.notes.category_3(62) +
                        "\n\n" + etc.notes.category_2(32) + "\n\n" + etc.notes.category_3(63) + "\n\n" + etc.notes.category_3(64) + "\n\n" + etc.notes.category_3(65) + "\n\n" + etc.notes.category_3(66) + "\n\n" + etc.notes.category_3(67) +
                        "\n\n" + etc.notes.category_2(33) + "\n\n" + etc.notes.category_3(68) + "\n\n" + etc.notes.category_3(69);
                number = "";
                break;

            case 1:
                title = etc.notes.category_0(2);
                subtitle = etc.notes.category_1(14);
                str_location = "";
                details = etc.notes.category_3(121) + "\n\n" + etc.notes.category_3(122) + "\n\n" + etc.notes.category_3(123) + "\n\n" + etc.notes.category_3(124) + "\n\n" + etc.notes.category_3(125) + "\n\n" + etc.notes.category_3(126);
                number = "";
                break;

            case 2:
                title = etc.notes.category_0(2);
                subtitle = etc.notes.category_1(15);
                str_location = "";
                details = etc.notes.category_3(127) + "\n\n" + etc.notes.category_3(128) + "\n\n" + etc.notes.category_2(44) + "\n\n" + etc.notes.category_3(129) + "\n\n" + etc.notes.category_3(130) + "\n\n" + etc.notes.category_3(131) +
                        "\n\n" + etc.notes.category_3(132) + "\n\n" + etc.notes.category_3(133) + "\n\n" + etc.notes.category_3(134);
                number = "";
                break;

            case 3:
                title = etc.notes.category_0(2);
                subtitle = etc.notes.category_1(16);
                str_location = "";
                details = etc.notes.category_3(135) + "\n\n" + etc.notes.category_3(136) + "\n\n" + etc.notes.category_3(137) + "\n\n" + etc.notes.category_3(138) + "\n\n" + etc.notes.category_3(139);
                number = "";
                break;

            case 4:
                title = etc.notes.category_0(2);
                subtitle = etc.notes.category_1(18);
                str_location = "";
                details = etc.notes.category_3(142) + "\n\n" + etc.notes.category_3(143) + "\n\n" + etc.notes.category_3(144) + "\n\n" + etc.notes.category_3(145) + "\n\n" + etc.notes.category_3(146) +
                        "\n\n" + etc.notes.category_3(147) + "\n\n" + etc.notes.category_3(148);
                number = "";
                break;

            case 5:
                title = etc.notes.category_0(2);
                subtitle = etc.notes.category_1(20);
                str_location = "";
                details = etc.notes.category_3(155) + "\n\n" + etc.notes.category_3(156) + "\n\n" + etc.notes.category_3(157) + "\n\n" + etc.notes.category_3(158) + "\n\n" + etc.notes.category_3(159) +
                        "\n\n" + etc.notes.category_3(160) + "\n\n" + etc.notes.category_3(161) + "\n\n" + etc.notes.category_3(162) + "\n\n" + etc.notes.category_3(163);
                number = "";
                break;

            default:
                title = etc.notes.category_0(2);
                subtitle = etc.notes.category_1(21);
                str_location = "";
                details = etc.notes.category_2(45) + "\n\n" + etc.notes.category_3(164) + "\n\n" + etc.notes.category_3(165) + "\n\n" + etc.notes.category_3(166) + "\n\n" + etc.notes.category_3(167) +
                        "\n\n" + etc.notes.category_2(46) + "\n\n" + etc.notes.category_3(168) + "\n\n" + etc.notes.category_3(169) + "\n\n" + etc.notes.category_3(170) +
                        "\n\n" + etc.notes.category_2(47) + "\n\n" + etc.notes.category_3(171) + "\n\n" + etc.notes.category_3(172) +
                        "\n\n" + etc.notes.category_2(48) + "\n\n" + etc.notes.category_3(173) + "\n\n" + etc.notes.category_3(174) + "\n\n" + etc.notes.category_3(175) + "\n\n" + etc.notes.category_3(176) +
                        "\n\n" + etc.notes.category_2(49) + "\n\n" + etc.notes.category_3(177) + "\n\n" + etc.notes.category_3(178) +
                        "\n\n" + etc.notes.category_2(50) + "\n\n" + etc.notes.category_3(179) + "\n\n" + etc.notes.category_3(180) +
                        "\n\n" + etc.notes.category_2(51) + "\n\n" + etc.notes.category_3(181) + "\n\n" + etc.notes.category_3(182) +
                        "\n\n" + etc.notes.category_3(52) + "\n\n" + etc.notes.category_3(183) + "\n\n" + etc.notes.category_3(184) +
                        "\n\n" + etc.notes.category_3(53) + "\n\n" + etc.notes.category_3(185) + "\n\n" + etc.notes.category_3(186) +
                        "\n\n" + etc.notes.category_3(54) + "\n\n" + etc.notes.category_3(187) + "\n\n" + etc.notes.category_3(188) + "\n\n" + etc.notes.category_3(189);
                number = "";
                break;

            /// ACTIVITY END
        }

//        cont_0.setText(title);
//        explain_0.setText(subtitle);
        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);

    }

    public void veryBadMEAL(int index) {

        switch (index) {
            /// MEAL START

            case 0:
                title = etc.notes.category_0(1);
                subtitle = etc.notes.category_1(7);
                str_location = "";
                details = etc.notes.category_2(13) + "\n\n" + etc.notes.category_3(36) + "\n\n" + etc.notes.category_2(14) + "\n\n" + etc.notes.category_3(37) + "\n\n" + etc.notes.category_2(15) + "\n\n" + etc.notes.category_3(38);
                number = "";
                break;

            case 1:
                title = etc.notes.category_0(1);
                subtitle = etc.notes.category_1(8);
                str_location = "";
                details = etc.notes.category_2(16) + "\n\n" + etc.notes.category_3(39) + "\n\n" + etc.notes.category_2(17) + "\n\n" + etc.notes.category_3(40) + "\n\n" + etc.notes.category_2(18) + "\n\n" + etc.notes.category_3(41) +
                        "\n\n" + etc.notes.category_2(19) + "\n\n" + etc.notes.category_3(42) + "\n\n" + etc.notes.category_2(20) + "\n\n" + etc.notes.category_3(43);
                number = "";
                break;

            case 2:
                title = etc.notes.category_0(1);
                subtitle = etc.notes.category_1(9);
                str_location = "";
                details = etc.notes.category_3(44) + "\n\n" + etc.notes.category_3(45) + "\n\n" + etc.notes.category_3(46);
                number = "";
                break;

            default:
                title = etc.notes.category_0(1);
                subtitle = etc.notes.category_1(10);
                str_location = "";
                details = etc.notes.category_2(21) + "\n\n" + etc.notes.category_3(47) + "\n\n" + etc.notes.category_2(22) + "\n\n" + etc.notes.category_3(48) + "\n\n" + etc.notes.category_3(49) +
                        "\n\n" + etc.notes.category_2(23) + "\n\n" + etc.notes.category_3(50) + "\n\n" + etc.notes.category_2(24) + "\n\n" + etc.notes.category_3(51) +
                        "\n\n" + etc.notes.category_2(25) + "\n\n" + etc.notes.category_3(52) + "\n\n" + etc.notes.category_2(26) + "\n\n" + etc.notes.category_3(53) +
                        "\n\n" + etc.notes.category_2(27) + "\n\n" + etc.notes.category_3(54) + "\n\n" + etc.notes.category_2(28) + "\n\n" + etc.notes.category_3(55) +
                        "\n\n" + etc.notes.category_2(29) + "\n\n" + etc.notes.category_3(56) + "\n\n" + etc.notes.category_2(30) + "\n\n" + etc.notes.category_3(57) + "\n\n" + etc.notes.category_3(58);
                number = "";
                break;
            /// MEAL END
        }

//        cont_0.setText(title);
//        explain_0.setText(subtitle);
        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);

    }

    //

    public void badBMI(int index) {

        switch (index) {
            /// BMI START
            case 0:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(0);
                str_location = "";
                details = etc.notes.category_3(0) + "\n\n" + etc.notes.category_3(1) + "\n\n" + etc.notes.category_3(2);
                number = "";
                break;

            case 1:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(1);
                str_location = "";
                details = etc.notes.category_3(3) + "\n\n" + etc.notes.category_3(4) + "\n\n" + etc.notes.category_3(5) + "\n\n" + etc.notes.category_3(6) + "\n\n" + etc.notes.category_3(7) + "\n\n" + etc.notes.category_3(8);
                number = "";
                break;

            case 2:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(2);
                str_location = "";
                details = etc.notes.category_2(0) + "\n\n" + etc.notes.category_3(10) + "\n\n" + etc.notes.category_2(1) + "\n\n" + etc.notes.category_3(11);
                number = "";
                break;

            case 3:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(3);
                str_location = "";
                details = etc.notes.category_2(2) + "\n\n" + etc.notes.category_3(12) + "\n\n" + etc.notes.category_2(3) +
                        "\n\n" + etc.notes.category_3(13) + "\n\n" + etc.notes.category_2(4) + "\n\n" + etc.notes.category_3(14) +
                        "\n\n" + etc.notes.category_2(5) + "\n\n" + etc.notes.category_3(15) + "\n\n" + etc.notes.category_2(6) + "\n\n" + etc.notes.category_3(16) + "\n\n" + etc.notes.category_3(17);
                number = "";
                break;

            case 4:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(4);
                str_location = "";
                details = etc.notes.category_2(7) + "\n\n" + etc.notes.category_3(18) + "\n\n" + etc.notes.category_2(8) + "\n\n" + etc.notes.category_3(19) + "\n\n" + etc.notes.category_2(9) + "\n\n" + etc.notes.category_3(20) +
                        "\n\n" + etc.notes.category_2(10) + "\n\n" + etc.notes.category_3(21) + "\n\n" + etc.notes.category_3(22) + "\n\n" + etc.notes.category_3(23) +
                        "\n\n" + etc.notes.category_2(11) + "\n\n" + etc.notes.category_3(24) + "\n\n" + etc.notes.category_3(25) +
                        "\n\n" + etc.notes.category_2(12) + "\n\n" + etc.notes.category_3(26) + "\n\n" + etc.notes.category_3(27);
                number = "";
                break;

            case 5:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(5);
                str_location = "";
                details = etc.notes.category_3(28) + "\n\n" + etc.notes.category_3(29) + "\n\n" + etc.notes.category_3(30) + "\n\n" + etc.notes.category_3(31) +
                        "\n\n" + etc.notes.category_3(32) + "\n\n" + etc.notes.category_3(33) + "\n\n" + etc.notes.category_3(34) + "\n\n" + etc.notes.category_1(6) + "\n\n" + etc.notes.category_3(35);
                number = "";
                break;

            default:
                title = etc.notes.category_0(0);
                subtitle = "비만과 운동";
                str_location = "";
                details = etc.notes.category_2(40) + "\n\n" + etc.notes.category_3(103) + "\n\n" + etc.notes.category_3(104) + "\n\n" + etc.notes.category_3(105) + "\n\n" + etc.notes.category_3(106) +
                        "\n\n" + etc.notes.category_3(107) + "\n\n" + etc.notes.category_3(108);
                number = "";
                break;

            /// BMI END
        }

//        cont_0.setText(title);
//        explain_0.setText(subtitle);
        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);

    }

    public void badBP(int index) {

        switch (index) {
            /// BLOOD PRESSURE START
            case 0:
                title = etc.notes.category_0(5);
                subtitle = "고혈압과 운동";
                str_location = "";
                details = etc.notes.category_2(37) + "\n\n" + etc.notes.category_3(81) + "\n\n" + etc.notes.category_3(82) + "\n\n" + etc.notes.category_3(83) + "\n\n" + etc.notes.category_3(84);
                number = "";
                break;

            case 1:
                title = etc.notes.category_0(5);
                subtitle = etc.notes.category_1(45);
                str_location = "";
                details = etc.notes.category_3(338) + "\n\n" + etc.notes.category_3(339) + "\n\n" + etc.notes.category_3(340) + "\n\n" + etc.notes.category_3(341);
                number = "";
                break;

            case 2:
                title = etc.notes.category_0(5);
                subtitle = etc.notes.category_1(46);
                str_location = "";
                details = etc.notes.category_2(109) + "\n\n" + etc.notes.category_3(342) + "\n\n" + etc.notes.category_3(343) + "\n\n" + etc.notes.category_3(344) + "\n\n" + etc.notes.category_3(345) +
                        "\n\n" + etc.notes.category_2(110) + "\n\n" + etc.notes.category_3(346) + "\n\n" + etc.notes.category_3(347) + "\n\n" + etc.notes.category_3(348);
                number = "";
                break;
            default:
                title = etc.notes.category_0(5);
                subtitle = etc.notes.category_1(47);
                str_location = "";
                details = etc.notes.category_3(349) + "\n\n" + etc.notes.category_3(350) + "\n\n" + etc.notes.category_3(351) + "\n\n" + etc.notes.category_3(352) + "\n\n" + etc.notes.category_3(353) +
                        "\n\n" + etc.notes.category_3(354) + "\n\n" + etc.notes.category_3(355) + "\n\n" + etc.notes.category_3(356) + "\n\n" + etc.notes.category_3(357);
                number = "";
                break;

            /// BLOOD PRESSURE END
        }
//        cont_0.setText(title);
//        explain_0.setText(subtitle);
        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);


    }

    public void badBS(int index) {
        switch (index) {
            /// BLOOD SUGAR START

            case 0:
                title = etc.notes.category_0(6);
                subtitle = "당뇨병과 운동";
                str_location = "";
                details = etc.notes.category_3(85) + "\n\n" + etc.notes.category_3(86) + "\n\n" + etc.notes.category_3(87) + "\n\n" + etc.notes.category_2(39) + "\n\n" + etc.notes.category_3(88) + "\n\n" + etc.notes.category_3(89) +
                        "\n\n" + etc.notes.category_3(90) + "\n\n" + etc.notes.category_3(91) + "\n\n" + etc.notes.category_3(92) + "\n\n" + etc.notes.category_3(93) + "\n\n" + etc.notes.category_3(94) + "\n\n" + etc.notes.category_3(95) +
                        "\n\n" + etc.notes.category_3(96) + "\n\n" + etc.notes.category_3(97) + "\n\n" + etc.notes.category_3(98) + "\n\n" + etc.notes.category_3(99) + "\n\n" + etc.notes.category_3(100) + "\n\n" + etc.notes.category_3(101) +
                        "\n\n" + etc.notes.category_3(102);
                number = "";
                break;

            case 1:
                title = etc.notes.category_0(6);
                subtitle = etc.notes.category_1(48);
                str_location = "";
                details = etc.notes.category_3(358) + "\n\n" + etc.notes.category_3(359) + "\n\n" + etc.notes.category_3(360) + "\n\n" + etc.notes.category_3(361) + "\n\n" + etc.notes.category_3(362) + "\n\n" + etc.notes.category_3(363);
                number = "";
                break;

            case 2:
                title = etc.notes.category_0(6);
                subtitle = etc.notes.category_1(49);
                str_location = "";
                details = etc.notes.category_3(364) + "\n\n" + etc.notes.category_3(365) + "\n\n" + etc.notes.category_3(366);
                number = "";
                break;

            case 3:
                title = etc.notes.category_0(6);
                subtitle = etc.notes.category_1(51);
                str_location = "";
                details = etc.notes.category_2(111) + "\n\n" + etc.notes.category_3(370) + "\n\n" + etc.notes.category_3(371) + "\n\n" + etc.notes.category_3(372) + "\n\n" + etc.notes.category_2(112) + "\n\n" + etc.notes.category_3(373) +
                        "\n\n" + etc.notes.category_3(374) + "\n\n" + etc.notes.category_3(375);
                number = "";
                break;

            case 4:
                title = etc.notes.category_0(6);
                subtitle = etc.notes.category_1(52);
                str_location = "";
                details = etc.notes.category_2(113) + "\n\n" + etc.notes.category_3(376) + "\n\n" + etc.notes.category_2(114) + "\n\n" + etc.notes.category_3(377) + "\n" + etc.notes.category_3(378) + "\n" + etc.notes.category_3(379);
                number = "";
                break;

            case 5:
                title = etc.notes.category_0(6);
                subtitle = etc.notes.category_1(50);
                str_location = "";
                details = etc.notes.category_3(367) + "\n\n" + etc.notes.category_3(368) + "\n\n" + etc.notes.category_3(369);
                number = "";
                break;

            case 6:
                title = etc.notes.category_0(6);
                subtitle = etc.notes.category_1(53);
                str_location = "";
                details = etc.notes.category_2(115) + "\n\n" + etc.notes.category_3(380) + "\n\n" + etc.notes.category_3(381) + "\n\n" + etc.notes.category_3(382) + "\n\n" + etc.notes.category_2(116) + "\n\n" + etc.notes.category_3(383) +
                        "\n\n" + etc.notes.category_2(117) + "\n\n" + etc.notes.category_3(384);
                number = "";
                break;

            default:
                title = etc.notes.category_0(6);
                subtitle = etc.notes.category_1(54);
                str_location = "";
                details = etc.notes.category_2(118) + "\n\n" + etc.notes.category_3(385) + "\n\n" + etc.notes.category_2(119) + "\n\n" + etc.notes.category_3(386) + "\n\n" + etc.notes.category_3(387) +
                        "\n\n" + etc.notes.category_2(120) + "\n\n" + etc.notes.category_3(388) + "\n\n" + etc.notes.category_3(389) + "\n\n" + etc.notes.category_3(390) +
                        "\n\n" + etc.notes.category_2(121) + "\n\n" + etc.notes.category_3(391) + "\n\n" + etc.notes.category_2(122) + "\n\n" + etc.notes.category_3(392) +
                        "\n\n" + etc.notes.category_2(123) + "\n\n" + etc.notes.category_3(393) + "\n\n" + etc.notes.category_3(394) + "\n\n" + etc.notes.category_2(124) + "\n\n" + etc.notes.category_3(395) +
                        "\n\n" + etc.notes.category_3(396) + "\n\n" + etc.notes.category_3(397);
                number = "";
                break;
            /// BLOOD SUGAR END
        }
//        cont_0.setText(title);
//        explain_0.setText(subtitle);
        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);

    }

    public void badACTIVE(int index) {
        veryBadACTIVE(index);
    }

    public void badMEAL(int index) {
        veryBadMEAL(index);
    }

    //

    public void normalBMI(int index) {
        switch (index) {
            /// BMI START
            case 0:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(0);
                str_location = "";
                details = etc.notes.category_3(0) + "\n\n" + etc.notes.category_3(1) + "\n\n" + etc.notes.category_3(2);
                number = "";
                break;

            case 1:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(2);
                str_location = "";
                details = etc.notes.category_2(0) + "\n\n" + etc.notes.category_3(10) + "\n\n" + etc.notes.category_2(1) + "\n\n" + etc.notes.category_3(11);
                number = "";
                break;

            default:
                title = etc.notes.category_0(0);
                subtitle = etc.notes.category_1(5);
                str_location = "";
                details = etc.notes.category_3(28) + "\n\n" + etc.notes.category_3(29) + "\n\n" + etc.notes.category_3(30) + "\n\n" + etc.notes.category_3(31) +
                        "\n\n" + etc.notes.category_3(32) + "\n\n" + etc.notes.category_3(33) + "\n\n" + etc.notes.category_3(34) + etc.notes.category_1(6) + etc.notes.category_3(35);
                number = "";
                break;

            /// BMI END
        }

//        cont_0.setText(title);
//        explain_0.setText(subtitle);
        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);


    }

    public void normalBP(int index) {
        badBP(index);
    }

    public void normalBS(int index) {
        badBS(index);
    }

    public void normalACTIVE(int index) {
        switch (index) {
            /// ACTIVITY START

            case 0:
                title = etc.notes.category_0(2);
                subtitle = etc.notes.category_1(11);
                str_location = "";
                details = etc.notes.category_2(31) + "\n\n" + etc.notes.category_3(59) + "\n\n" + etc.notes.category_3(60) + "\n\n" + etc.notes.category_3(61) + "\n\n" + etc.notes.category_3(62) +
                        "\n\n" + etc.notes.category_2(32) + "\n\n" + etc.notes.category_3(63) + "\n\n" + etc.notes.category_3(64) + "\n\n" + etc.notes.category_3(65) + "\n\n" + etc.notes.category_3(66) + "\n\n" + etc.notes.category_3(67) +
                        "\n\n" + etc.notes.category_2(33) + "\n\n" + etc.notes.category_3(68) + "\n\n" + etc.notes.category_3(69);
                number = "";
                break;

            case 1:
                title = etc.notes.category_0(2);
                subtitle = etc.notes.category_1(14);
                str_location = "";
                details = etc.notes.category_3(121) + "\n\n" + etc.notes.category_3(122) + "\n\n" + etc.notes.category_3(123) + "\n\n" + etc.notes.category_3(124) + "\n\n" + etc.notes.category_3(125) + "\n\n" + etc.notes.category_3(126);
                number = "";
                break;

            case 2:
                title = etc.notes.category_0(2);
                subtitle = etc.notes.category_1(15);
                str_location = "";
                details = etc.notes.category_3(127) + "\n\n" + etc.notes.category_3(128) + "\n\n" + etc.notes.category_2(44) + "\n\n" + etc.notes.category_3(129) + "\n\n" + etc.notes.category_3(130) + "\n\n" + etc.notes.category_3(131) +
                        "\n\n" + etc.notes.category_3(132) + "\n\n" + etc.notes.category_3(133) + "\n\n" + etc.notes.category_3(134);
                number = "";
                break;

            case 3:
                title = etc.notes.category_0(2);
                subtitle = etc.notes.category_1(18);
                str_location = "";
                details = etc.notes.category_3(142) + "\n\n" + etc.notes.category_3(143) + "\n\n" + etc.notes.category_3(144) + "\n\n" + etc.notes.category_3(145) + "\n\n" + etc.notes.category_3(146) + "\n\n" + etc.notes.category_3(147) +
                        "\n\n" + etc.notes.category_3(148);
                number = "";
                break;

            default:
                title = etc.notes.category_0(2);
                subtitle = etc.notes.category_1(20);
                str_location = "";
                details = etc.notes.category_3(155) + "\n\n" + etc.notes.category_3(156) + "\n\n" + etc.notes.category_3(157) + "\n\n" + etc.notes.category_3(158) + "\n\n" + etc.notes.category_3(159) + "\n\n" + etc.notes.category_3(160) +
                        "\n\n" + etc.notes.category_3(161) + "\n\n" + etc.notes.category_3(162) + "\n\n" + etc.notes.category_3(163);
                number = "";
                break;

            /// ACTIVITY END
        }

//        cont_0.setText(title);
//        explain_0.setText(subtitle);
        Recommend recommend = new Recommend();
        recommend.setTitle(title);
        recommend.setSubTitle(subtitle);
        recommend.setContent(details);
        recommends.add(recommend);

    }

    public void normalMEAL(int index) {
        veryBadMEAL(index);
    }

}
