package com.appondefica;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig(String url) {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(url).
                addConverterFactory(GsonConverterFactory.create()).build();
    }
    public CEPService getCEPService()
    {
        return this.retrofit.create(CEPService.class);
    }
    public IBGEService getIBGEService() { return this.retrofit.create(IBGEService.class); }
}
