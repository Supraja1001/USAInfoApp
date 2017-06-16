package com.example.supraja.usainfoapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by renik on 6/13/2017.
 */

public interface ApiService {
    @GET("/feeds/flowers.json")

    Call<List<Datamm>> getFlowerNames();


}