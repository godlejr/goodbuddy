package com.demand.goodbuddy.repository;

import com.demand.goodbuddy.dto.DiagnosisCategory;
import com.demand.goodbuddy.dto.DietDiagnosisCategory;
import com.demand.goodbuddy.dto.DietSelfDiagnosis;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by ㅇㅇ on 2017-04-27.
 */

public interface DiagnosisController {
    @GET("dietDiagnosisCategories")
    Call<ArrayList<DietDiagnosisCategory>> selectDietDiagnosisCategory();

    @GET("diagnosisCategories")
    Call<ArrayList<DiagnosisCategory>> selectDiagnosisCategory();

    @POST("./")
    Call<ArrayList<DietSelfDiagnosis>> insertDietSelfDiagnosis(@Body DietSelfDiagnosis dietSelfDiagnosis);


}
