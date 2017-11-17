package com.demand.goodbuddy.main.base.interactor.impl;

import com.demand.goodbuddy.dto.ActiveMass;
import com.demand.goodbuddy.dto.BloodPressure;
import com.demand.goodbuddy.dto.BloodSugar;
import com.demand.goodbuddy.dto.BmiScore;
import com.demand.goodbuddy.dto.DietSelfDiagnosisScore;
import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.LogFlag;
import com.demand.goodbuddy.main.base.interactor.MainInteractor;
import com.demand.goodbuddy.main.base.presenter.MainPresenter;
import com.demand.goodbuddy.repository.UserController;
import com.demand.goodbuddy.repository.interceptor.HeaderInterceptor;
import com.demand.goodbuddy.util.ErrorUtil;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public class MainInteractorImpl implements MainInteractor {
    private MainPresenter mainPresenter;

    private ArrayList<ActiveMass> activeMassList;
    private ArrayList<BloodSugar> bloodSugarList;
    private ArrayList<DietSelfDiagnosisScore> dietSelfDiagnosisScoreList;
    private ArrayList<BmiScore> bmiScoreList;
    private ArrayList<BloodPressure> bloodPressureList;

    private Float averageActiveMass;
    private Float averageBloodSugar;
    private Float averageMaxBloodPressure;
    private Float averageMinBloodPressure;

    private Float averageDietSelfDiagnosisScore;
    private Float averageBmiScore;




    private UserController userController;

    private User user;

    private static final Logger logger = LoggerFactory.getLogger(MainInteractorImpl.class);

    public MainInteractorImpl(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void getActiveMassData(User user) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<ArrayList<ActiveMass>> callSelectActiveMass = userController.selectActiveMass(userId);
        callSelectActiveMass.enqueue(new Callback<ArrayList<ActiveMass>>() {
            @Override
            public void onResponse(Call<ArrayList<ActiveMass>> call, Response<ArrayList<ActiveMass>> response) {
                if (response.isSuccessful()) {
                    ArrayList<BarEntry> activeMassBarEntryList = null;
                    activeMassList = response.body();
                    int activeMassListSize = activeMassList.size();

                    if (activeMassListSize > 0) {
                        activeMassBarEntryList = new ArrayList<>();
                        for (int i = 0; i < activeMassListSize; i++) {
                            ActiveMass activeMass = activeMassList.get(i);
                            activeMassBarEntryList.add(new BarEntry(i, activeMass.getData()));    // param2: 높이   param1: labelList index
                        }
                    }
                    mainPresenter.onSuccessGetActiveMassData(activeMassBarEntryList);
                } else {
                    mainPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }

            }

            @Override
            public void onFailure(Call<ArrayList<ActiveMass>> call, Throwable t) {
                log(t);
                mainPresenter.onNetworkError(null);
            }
        });

    }

    @Override
    public void getActiveMassChartLabels(User user) {
        int activeMassListSize = activeMassList.size();
        ArrayList<String> activityLabelList = new ArrayList<>();

        for (int i = 0; i < activeMassListSize; i++) {
            String createdAt = activeMassList.get(i).getCreatedAt();
            String[] date = createdAt.split("-");
            activityLabelList.add(date[1] + "." + date[2]);
        }

        mainPresenter.onSuccessGetActiveMassChartLabels(activityLabelList);
    }

    @Override
    public void getAverageActiveMass(User user) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<Float> callSelectAverageActiveMass = userController.selectActiveMassAverage(userId);
        callSelectAverageActiveMass.enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                if (response.isSuccessful()) {
                    averageActiveMass = response.body();
                    mainPresenter.onSuccessGetAverageActiveMass(averageActiveMass);
                } else {
                    mainPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {
                log(t);
                mainPresenter.onNetworkError(null);
            }
        });

    }

    @Override
    public void getBloodPressureData(User user) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<ArrayList<BloodPressure>> callSelectBloodPressure = userController.selectBloodPressure(userId);

        callSelectBloodPressure.enqueue(new Callback<ArrayList<BloodPressure>>() {
            @Override
            public void onResponse(Call<ArrayList<BloodPressure>> call, Response<ArrayList<BloodPressure>> response) {
                if (response.isSuccessful()) {
                    bloodPressureList = response.body();
                    int bloodPressureListSize = bloodPressureList.size();
                    ArrayList<Entry> minBloodPressureEntryList = null;
                    ArrayList<Entry> maxBloodPressureEntryList = null;

                    if (bloodPressureListSize > 0) {
                        minBloodPressureEntryList = new ArrayList<>();
                        maxBloodPressureEntryList = new ArrayList<>();

                        for (int i = 0; i < bloodPressureListSize; i++) {
                            BloodPressure bloodPressure = bloodPressureList.get(i);
                            minBloodPressureEntryList.add(new BarEntry(i, bloodPressure.getMinData()));
                            maxBloodPressureEntryList.add(new BarEntry(i, bloodPressure.getMaxData()));
                        }
                    }
                    mainPresenter.onSuccessGetBloodPressureData(minBloodPressureEntryList, maxBloodPressureEntryList);
                } else {
                    mainPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BloodPressure>> call, Throwable t) {
                log(t);
                mainPresenter.onNetworkError(null);
            }
        });

    }


    @Override
    public void getBloodPressureChartLabels(User user) {
        ArrayList<String> bloodPressureLabelList = new ArrayList<>();
        int bloodPressureListSize = bloodPressureList.size();

        for (int i = 0; i < bloodPressureListSize; i++) {
            String createdAt = bloodPressureList.get(i).getCreatedAt();
            String[] date = createdAt.split("-");
            bloodPressureLabelList.add(date[1] + "." + date[2]);
        }

        mainPresenter.onSuccessGetBloodPressureChartLabels(bloodPressureLabelList);
    }

    @Override
    public void getAverageMinBloodPressure(User user) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<Float> callSelectAverageMinBloodPressure = userController.selectMinBloodPressureAverage(userId);
        callSelectAverageMinBloodPressure.enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                if (response.isSuccessful()) {
                    averageMinBloodPressure = response.body();
                    mainPresenter.onSuccessGetAverageMinBloodPressure(averageMinBloodPressure);
                } else {
                    mainPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {
                log(t);
                mainPresenter.onNetworkError(null);
            }
        });

    }

    @Override
    public void getAverageMaxBloodPressure(User user) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<Float> callSelectAverageMaxBloodPressure = userController.selectMaxBloodPressureAverage(userId);
        callSelectAverageMaxBloodPressure.enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                if (response.isSuccessful()) {
                    averageMaxBloodPressure = response.body();
                    mainPresenter.onSuccessGetAverageMaxBloodPressure(averageMaxBloodPressure);
                } else {
                    mainPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {
                log(t);
                mainPresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public void getBloodSugarData(User user) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<ArrayList<BloodSugar>> callSelectBloodSugar = userController.selectBloodSugar(userId);

        callSelectBloodSugar.enqueue(new Callback<ArrayList<BloodSugar>>() {
            @Override
            public void onResponse(Call<ArrayList<BloodSugar>> call, Response<ArrayList<BloodSugar>> response) {
                if (response.isSuccessful()) {
                    bloodSugarList = response.body();
                    int bloodSugarListSize = bloodSugarList.size();
                    ArrayList<Entry> bloodSugarEntryList = null;

                    if (bloodSugarListSize > 0) {
                        bloodSugarEntryList = new ArrayList<>();
                        for (int i = 0; i < bloodSugarListSize; i++) {
                            BloodSugar bloodSugar = bloodSugarList.get(i);
                            bloodSugarEntryList.add(new BarEntry(i, bloodSugar.getData()));
                        }
                    }

                    mainPresenter.onSuccessGetBloodSugarData(bloodSugarEntryList);
                } else {
                    mainPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BloodSugar>> call, Throwable t) {
                log(t);
                mainPresenter.onNetworkError(null);
            }
        });

    }

    @Override
    public void getBloodSugarChartLabels(User user) {
        int bloodSugarListSize = bloodSugarList.size();

        ArrayList<String> bloodSugarLabelList = new ArrayList<>();
        for (int i = 0; i < bloodSugarListSize; i++) {
            String createdAt = bloodSugarList.get(i).getCreatedAt();
            String[] date = createdAt.split("-");
            bloodSugarLabelList.add(date[1] + "." + date[2]);
        }

        mainPresenter.onSuccessGetBloodSugarChartLabels(bloodSugarLabelList);
    }

    @Override
    public void getAverageBloodSugar(User user) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<Float> callSelectAverageBloodSugar = userController.selectBloodSugarAverage(userId);
        callSelectAverageBloodSugar.enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                if (response.isSuccessful()) {
                    averageBloodSugar = response.body();
                    mainPresenter.onSuccessGetAverageBloodSugar(averageBloodSugar);

                } else {
                    mainPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {
                log(t);
                mainPresenter.onNetworkError(null);
            }
        });

    }

    @Override
    public void getDietScoreData(User user) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<ArrayList<DietSelfDiagnosisScore>> callSelectDietDiagnosisScore = userController.selectDiagnosis(userId);
        callSelectDietDiagnosisScore.enqueue(new Callback<ArrayList<DietSelfDiagnosisScore>>() {
            @Override
            public void onResponse(Call<ArrayList<DietSelfDiagnosisScore>> call, Response<ArrayList<DietSelfDiagnosisScore>> response) {
                if (response.isSuccessful()) {
                    dietSelfDiagnosisScoreList = response.body();
                    int dietSelfDiagnosisScoreListSize = dietSelfDiagnosisScoreList.size();
                    ArrayList<Entry> dietDiagnosisScoreEntryList = null;

                    if (dietSelfDiagnosisScoreListSize > 0) {
                        dietDiagnosisScoreEntryList = new ArrayList<>();
                        for (int i = 0; i < dietSelfDiagnosisScoreListSize; i++) {
                            dietDiagnosisScoreEntryList.add(new Entry(i, dietSelfDiagnosisScoreList.get(i).getScore()));
                        }

                    }

                    mainPresenter.onSuccessGetDietScoreData(dietDiagnosisScoreEntryList);
                } else {
                    mainPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DietSelfDiagnosisScore>> call, Throwable t) {
                log(t);
                mainPresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public void getDietScoreChartLabels(User user) {
        int dietSelfDiagnosisScoreListSize = dietSelfDiagnosisScoreList.size();

        ArrayList<String> dietSelfDiagnosisScoreLabelList = new ArrayList<>();
        for (int i = 0; i < dietSelfDiagnosisScoreListSize; i++) {
            String createdAt = dietSelfDiagnosisScoreList.get(i).getCreatedAt();
            String[] date = createdAt.split("-");
            dietSelfDiagnosisScoreLabelList.add(date[1] + "." + date[2]);
        }

        mainPresenter.onSuccessGetDietScoreChartLabels(dietSelfDiagnosisScoreLabelList);
    }

    @Override
    public void getAverageDietScore(User user) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<Float> callSelectAverageDiagnosisScore = userController.selectDiagnosisAverage(userId);
        callSelectAverageDiagnosisScore.enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                if (response.isSuccessful()) {
                    averageDietSelfDiagnosisScore = response.body();
                    mainPresenter.onSuccessGetAverageDietDiagnosisScore(averageDietSelfDiagnosisScore);
                } else {
                    mainPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {
                log(t);
                mainPresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public void getBmiData(User user) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<ArrayList<BmiScore>> callSelectBmiScore = userController.selectBmi(userId);
        callSelectBmiScore.enqueue(new Callback<ArrayList<BmiScore>>() {
            @Override
            public void onResponse(Call<ArrayList<BmiScore>> call, Response<ArrayList<BmiScore>> response) {
                if (response.isSuccessful()) {
                    bmiScoreList = response.body();
                    int bmiScoreListSize = bmiScoreList.size();
                    ArrayList<Entry> bmiEntryList = null;

                    if (bmiScoreListSize > 0) {
                        bmiEntryList = new ArrayList<>();
                        for (int i = 0; i < bmiScoreListSize; i++) {
                            bmiEntryList.add(new Entry(i, bmiScoreList.get(i).getScore()));
                        }
                    }

                    mainPresenter.onSuccessGetBmiData(bmiEntryList);
                } else {
                    mainPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BmiScore>> call, Throwable t) {
                log(t);
                mainPresenter.onNetworkError(null);
            }
        });

    }

    @Override
    public void getBmiChartLabels(User user) {
        int bmiScoreListSize = bmiScoreList.size();
        ArrayList<String> bmiLabelList = new ArrayList<>();
        for (int i = 0; i < bmiScoreListSize; i++) {
            String createdAt = bmiScoreList.get(i).getCreatedAt();
            String[] date = createdAt.split("-");
            bmiLabelList.add(date[1] + "." + date[2]);
        }

        mainPresenter.onSuccessGetBmiChartLabels(bmiLabelList);
    }

    @Override
    public void getAverageBmiScore(User user) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<Float> callSelectAverageBmiScore = userController.selectBmiAverage(userId);
        callSelectAverageBmiScore.enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                if (response.isSuccessful()) {
                    averageBmiScore = response.body();
                    mainPresenter.onSuccessGetAverageBmiScore(averageBmiScore);
                } else {
                    mainPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {
                log(t);
                mainPresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public void setAverageActiveMass(Float averageActiveMass) {
        this.averageActiveMass = averageActiveMass;
    }

    @Override
    public void setAverageBloodSugar(Float averageBloodSugar) {
        this.averageBloodSugar = averageBloodSugar;

    }

    @Override
    public void setAverageMaxBloodPressure(Float averageMaxBloodPressure) {
        this.averageMaxBloodPressure = averageMaxBloodPressure;
    }

    @Override
    public void setAverageMinBloodPressure(Float averageMinBloodPressure) {
        this.averageMinBloodPressure = averageMinBloodPressure;
    }

    @Override
    public void setAverageDietSelfDiagnosisScore(Float averageDietSelfDiagnosisScore) {
        this.averageDietSelfDiagnosisScore = averageDietSelfDiagnosisScore;

    }

    @Override
    public void setAverageBmiScore(Float averageBmiScore) {
        this.averageBmiScore = averageBmiScore;

    }

    @Override
    public Float getAverageActiveMass() {
        return this.averageActiveMass;
    }

    @Override
    public Float getAverageBloodSugar() {
        return this.averageBloodSugar;
    }

    @Override
    public Float getAverageMaxBloodPressure() {
        return this.averageMaxBloodPressure;
    }

    @Override
    public Float getAverageMinBloodPressure() {
        return this.averageMinBloodPressure;
    }

    @Override
    public Float getAverageDietSelfDiagnosisScore() {
        return this.averageDietSelfDiagnosisScore;
    }

    @Override
    public Float getAverageBmiScore() {
        return this.averageBmiScore;
    }

    private static void log(Throwable throwable) {
        StackTraceElement[] ste = throwable.getStackTrace();
        String className = ste[0].getClassName();
        String methodName = ste[0].getMethodName();
        int lineNumber = ste[0].getLineNumber();
        String fileName = ste[0].getFileName();

        if (LogFlag.printFlag) {
            if (logger.isInfoEnabled()) {
                logger.info("Exception: " + throwable.getMessage());
                logger.info(className + "." + methodName + " " + fileName + " " + lineNumber + " " + "line");
            }
        }
    }
}
