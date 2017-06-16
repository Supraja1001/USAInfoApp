package com.example.supraja.usainfoapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by renik on 6/13/2017.
 */

public interface ApiService {

    @GET("/feeds/flowers.json")
    Call<List<Datamm>> getFlowerNames();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<AnswersResponse.ItemsBean> getAnswers();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<AnswersResponse.ItemsBean> getAnswers(@Query("tagged") String tags);


}