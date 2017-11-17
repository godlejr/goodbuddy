package com.demand.goodbuddy.repository;

import com.demand.goodbuddy.dto.ActiveMass;
import com.demand.goodbuddy.dto.BloodPressure;
import com.demand.goodbuddy.dto.BloodSugar;
import com.demand.goodbuddy.dto.Bmi;
import com.demand.goodbuddy.dto.BmiScore;
import com.demand.goodbuddy.dto.DietSelfDiagnosisScore;
import com.demand.goodbuddy.dto.Message;
import com.demand.goodbuddy.dto.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public interface UserController {
    @PUT("{userId}/deviceidAndFcmToken")
        // deviceId, fcmToken
    Call<ResponseBody> updateDeviceIdToken(@Path("userId") int userId, @QueryMap HashMap<String, String> map);

    @POST("{userId}/bloodPressure")
    Call<ResponseBody> insertBloodPressure(@Path("userId") int userId, @Body BloodPressure bloodPressure);

    @POST("{userId}/bloodSugar")
    Call<ResponseBody> insertBloodSugar(@Path("userId") int userId, @Body BloodSugar bloodSugar);

    @POST("{userId}/bmi")
    Call<ResponseBody> insertBmi(@Path("userId") int userId, @Body Bmi bmi);

    @POST("{userId}/activeMass")
    Call<ResponseBody> insertActiveMass(@Path("userId") int userId, @QueryMap HashMap<String, String> map);

    @GET("{userId}/deviceIds")
    Call<Integer> checkDeviceId(@Path("userId") int userId, @QueryMap HashMap<String, String> map);

    @GET("{userId}/bloodPressure")
    Call<ArrayList<BloodPressure>> selectBloodPressure(@Path("userId") int userId);

    @GET("{userId}/activeMass")
    Call<ArrayList<ActiveMass>> selectActiveMass(@Path("userId") int userId);

    @GET("{userId}/bloodSugar")
    Call<ArrayList<BloodSugar>> selectBloodSugar(@Path("userId") int userId);

    @GET("{userId}/diagnosis")
    Call<ArrayList<DietSelfDiagnosisScore>> selectDiagnosis(@Path("userId") int userId);

    @GET("{userId}/bmi")
    Call<ArrayList<BmiScore>> selectBmi(@Path("userId") int userId);

    @GET("{userId}/maxBloodPressureAverage")
    Call<Float> selectMaxBloodPressureAverage(@Path("userId") int userId);

    @GET("{userId}/minBloodPressureAverage")
    Call<Float> selectMinBloodPressureAverage(@Path("userId") int userId);

    @GET("{userId}/activeMassAverage")
    Call<Float> selectActiveMassAverage(@Path("userId") int userId);

    @GET("{userId}/bloodSugarAverage")
    Call<Float> selectBloodSugarAverage(@Path("userId") int userId);

    @GET("{userId}/diagnosisAverage")
    Call<Float> selectDiagnosisAverage(@Path("userId") int userId);

    @GET("{userId}/bmiAverage")
    Call<Float> selectBmiAverage(@Path("userId") int userId);

    @GET("{userId}/dateCheck/{checkId}")
    Call<Integer> selectDateCheck(@Path("userId") int userId, @Path("checkId") int checkId);


    @POST("{userId}/message")
    Call<Message> insertMessage(@Path("userId") int userId, @Body Message message);

    @GET("{userId}/message")
    Call<List<Message>> selectMessageByUserIdAndOffset(@Path("userId") int userId, @Query("offset") int offset);

    @POST("{id}/edit")
    Call<User> updateUser(@Path("id") int id, @QueryMap  HashMap<String, String> map);

    @POST("{id}/editPassword")
    Call<ResponseBody> updatePassword(@Path("id") int id, @Query("password") String password);
}
