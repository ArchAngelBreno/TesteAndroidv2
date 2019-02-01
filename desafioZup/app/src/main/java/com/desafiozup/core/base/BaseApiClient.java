package com.desafiozup.core.base;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApiClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://bank-app-test.herokuapp.com/api/";

    public static Retrofit providesRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(providesOkHttpClient())
                    .baseUrl(BASE_URL)
                    .build();
        }

        return retrofit;
    }

    private static OkHttpClient providesOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }
}
