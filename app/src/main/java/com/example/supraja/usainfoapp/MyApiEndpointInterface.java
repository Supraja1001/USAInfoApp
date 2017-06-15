package com.example.supraja.usainfoapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by renik on 6/12/2017.
 */

public interface MyApiEndpointInterface {

    @GET("all")
    Call<User> getCountryNames();

}
