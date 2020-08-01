package com.basis.financial.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class APIClient {
    private static APIClient mAPIClient = null;
    public static final String BASE_URL = "https://git.io";
    private Retrofit mRetrofit;
    private Gson mGson;

    private APIClient() {

    }


    public static APIClient getInstance() {
        if (mAPIClient == null) {

            mAPIClient = new APIClient();

            return mAPIClient;
        } else {
            return mAPIClient;
        }

    }

    public Gson getGsonInstance() {
        if (mGson == null) {
            mGson = new GsonBuilder().setLenient().create();
            return mGson;
        }
        return mGson;
    }

    public Retrofit getRetrofitInstance() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(getGsonInstance()))

                    .build();
            return mRetrofit;
        } else {
            return mRetrofit;
        }

    }


}
