package com.demand.goodbuddy.repository.interceptor;

import com.demand.goodbuddy.repository.converter.NullOnEmptyConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ㅇㅇ on 2017-04-24.
 */

public class HeaderInterceptor implements Interceptor {
    private String accessToken = null;
    public Retrofit retrofit;
    private OkHttpClient client;
    private Gson gson = new GsonBuilder().setLenient().create();

    public HeaderInterceptor() {
        super();
    }

    public HeaderInterceptor(String accessToken) {
        this.accessToken = accessToken;

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(this);
        client = httpClient.build();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (accessToken != null) {
            request = request.newBuilder()
                    .addHeader("Authorization", accessToken)
                    .build();
        } else {
            request = request.newBuilder().build();
        }

        Response response = chain.proceed(request);
        return response;
    }

    public Retrofit getClientForMainServer() {
        if (client == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.goodbuddy.co.kr/main/")
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        } else {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.goodbuddy.co.kr/main/")
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public Retrofit getClientForUserServer() {

        if (client == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.goodbuddy.co.kr/users/")
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        } else {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.goodbuddy.co.kr/users/")
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public Retrofit getClientForDiagnosisServer() {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.goodbuddy.co.kr/diagnosis/")
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }
}
