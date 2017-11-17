package com.demand.goodbuddy.repository;

import com.demand.goodbuddy.dto.User;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ㅇㅇ on 2017-04-25.
 */

public interface MainController {
    @GET("login")
    Call<User> login(@QueryMap HashMap<String, String> map);

    @GET("checkEmail")
    Call<Boolean> emailCheck(@Query("email") String email);

    @POST("join")
    Call<ResponseBody> join(@QueryMap HashMap<String, String> map);

}
