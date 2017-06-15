package com.example.supraja.usainfoapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by renik on 6/13/2017.
 */

public class RestClient {
// url
  private static Retrofit getRetrofitInstance(){
      return new Retrofit.Builder()
              .baseUrl("https://restcountries.eu/rest/v2/")
              .addConverterFactory(GsonConverterFactory.create())
              .build();

  }

  //API Service

    public static ApiService getApiService(){
        return getRetrofitInstance().create(ApiService.class);
    }
}
