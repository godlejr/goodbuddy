package com.demand.goodbuddy.diet.interactor.impl;

import com.demand.goodbuddy.diet.interactor.DietInteractor;
import com.demand.goodbuddy.diet.presenter.DietPresenter;
import com.demand.goodbuddy.dto.DiagnosisCategory;
import com.demand.goodbuddy.dto.DietDiagnosisCategory;
import com.demand.goodbuddy.dto.DietSelfDiagnosis;
import com.demand.goodbuddy.dto.User;
import com.demand.goodbuddy.flag.LogFlag;
import com.demand.goodbuddy.repository.DiagnosisController;
import com.demand.goodbuddy.repository.UserController;
import com.demand.goodbuddy.repository.interceptor.HeaderInterceptor;
import com.demand.goodbuddy.util.ErrorUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ㅇㅇ on 2017-04-26.
 */

public class DietInteractorImpl implements DietInteractor {
    private DietPresenter dietPresenter;

    private ArrayList<DietSelfDiagnosis> dietSelfDiagnosisList;
    private ArrayList<DiagnosisCategory> diagnosisCategoryList;
    private ArrayList<DietDiagnosisCategory> dietDiagnosisCategoryList;

    private DiagnosisController diagnosisController;
    private UserController userController;
    private static final Logger logger = LoggerFactory.getLogger(DietInteractorImpl.class);

    public DietInteractorImpl(DietPresenter dietPresenter) {
        this.dietPresenter = dietPresenter;
        this.dietSelfDiagnosisList = new ArrayList<>();
    }

    @Override
    public ArrayList<DietSelfDiagnosis> getDietSelfDiagnosisList() {
        return this.dietSelfDiagnosisList;
    }

    @Override
    public void setDietSelfDiagnosisListAdd(DietSelfDiagnosis dietSelfDiagnosis) {
        if (diagnosisCategoryList != null) {
            int dietSelfDiagnosisDietDiagnosisCategoryId = dietSelfDiagnosis.getDietDiagnosisCategoryId();
            this.dietSelfDiagnosisList.remove(dietSelfDiagnosisDietDiagnosisCategoryId - 1);
            this.dietSelfDiagnosisList.add(dietSelfDiagnosisDietDiagnosisCategoryId - 1, dietSelfDiagnosis);

        } else {
            this.dietSelfDiagnosisList.add(dietSelfDiagnosis);
        }
    }

    @Override
    public ArrayList<DiagnosisCategory> getDiagnosisCategoryList() {
        return this.diagnosisCategoryList;
    }

    @Override
    public void setDiagnosisList(ArrayList<DiagnosisCategory> diagnosisCategoryList) {
        this.diagnosisCategoryList = diagnosisCategoryList;
    }

    @Override
    public ArrayList<DietDiagnosisCategory> getDietDiagnosisCategoryList() {
        return this.dietDiagnosisCategoryList;
    }

    @Override
    public void setDietDiagnosisList(ArrayList<DietDiagnosisCategory> dietDiagnosisCategoryList) {
        this.dietDiagnosisCategoryList = dietDiagnosisCategoryList;
    }

    @Override
    public void setDietSelfDiagnosisListChangeDiagnosisCategoryId(DietSelfDiagnosis dietSelfDiagnosis) {
        int dietDiagnosisCategoryId = dietSelfDiagnosis.getDietDiagnosisCategoryId();
        int diagnosisCategoryId = dietSelfDiagnosis.getDiagnosisCategoryId();

        this.dietSelfDiagnosisList.get(dietDiagnosisCategoryId - 1).setDiagnosisCategoryId(diagnosisCategoryId);
    }

    @Override
    public void getDietDiagnosisCategory(User user) {
        String accessToken = user.getAccessToken();

        diagnosisController = new HeaderInterceptor(accessToken).getClientForDiagnosisServer().create(DiagnosisController.class);
        Call<ArrayList<DietDiagnosisCategory>> callSelectDietDiagnosisCategory = diagnosisController.selectDietDiagnosisCategory();
        callSelectDietDiagnosisCategory.enqueue(new Callback<ArrayList<DietDiagnosisCategory>>() {
            @Override
            public void onResponse(Call<ArrayList<DietDiagnosisCategory>> call, Response<ArrayList<DietDiagnosisCategory>> response) {
                if (response.isSuccessful()) {
                    ArrayList<DietDiagnosisCategory> dietDiagnosisCategories = response.body();
                    dietPresenter.onSuccessGetDietDiagnosisCategoryList(dietDiagnosisCategories);
                } else {
                    dietPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DietDiagnosisCategory>> call, Throwable t) {
                log(t);
                dietPresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public void getDiagnosisCategory(User user) {
        String accessToken = user.getAccessToken();

        diagnosisController = new HeaderInterceptor(accessToken).getClientForDiagnosisServer().create(DiagnosisController.class);
        Call<ArrayList<DiagnosisCategory>> callSelectDiagnosisCategory = diagnosisController.selectDiagnosisCategory();
        callSelectDiagnosisCategory.enqueue(new Callback<ArrayList<DiagnosisCategory>>() {
            @Override
            public void onResponse(Call<ArrayList<DiagnosisCategory>> call, Response<ArrayList<DiagnosisCategory>> response) {
                if (response.isSuccessful()) {
                    ArrayList<DiagnosisCategory> diagnosisCategories = response.body();
                    dietPresenter.onSuccessGetDiagnosisCategoryList(diagnosisCategories);
                } else {
                    dietPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DiagnosisCategory>> call, Throwable t) {
                log(t);
                dietPresenter.onNetworkError(null);
            }
        });
    }

    @Override
    public void setDietDiagnosisData(DietSelfDiagnosis dietSelfDiagnosis, User user) {
        String accessToken = user.getAccessToken();

        diagnosisController = new HeaderInterceptor(accessToken).getClientForDiagnosisServer().create(DiagnosisController.class);
        Call<ArrayList<DietSelfDiagnosis>> callSelectDiagnosisCategory = diagnosisController.insertDietSelfDiagnosis(dietSelfDiagnosis);
        callSelectDiagnosisCategory.enqueue(new Callback<ArrayList<DietSelfDiagnosis>>() {
            @Override
            public void onResponse(Call<ArrayList<DietSelfDiagnosis>> call, Response<ArrayList<DietSelfDiagnosis>> response) {
                if (response.isSuccessful()) {
                    dietPresenter.onSuccessSetDiagnosisData();
                } else {
                    dietPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DietSelfDiagnosis>> call, Throwable t) {
                log(t);
                dietPresenter.onNetworkError(null);
            }
        });

    }

    @Override
    public void getDate(User user, final int flag) {
        String accessToken = user.getAccessToken();
        int userId = user.getId();

        userController = new HeaderInterceptor(accessToken).getClientForUserServer().create(UserController.class);
        Call<Integer> callSelectDateCheck = userController.selectDateCheck(userId, flag);
        callSelectDateCheck.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    int checkedDate = response.body();
                    dietPresenter.onSuccessGetDate(checkedDate);
                } else {
                    dietPresenter.onNetworkError(new ErrorUtil(getClass()).parseError(response));
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                log(t);
                dietPresenter.onNetworkError(null);
            }
        });
    }

    private static void log(Throwable throwable) {
        StackTraceElement[] ste = throwable.getStackTrace();
        String className = ste[0].getClassName();
        String methodName = ste[0].getMethodName();
        int lineNumber = ste[0].getLineNumber();
        String fileName = ste[0].getFileName();

        if (LogFlag.printFlag) {
            if (logger.isInfoEnabled()) {
                logger.error("Exception: " + throwable.getMessage());
                logger.error(className + "." + methodName + " " + fileName + " " + lineNumber + " " + "line");
            }
        }
    }

}
