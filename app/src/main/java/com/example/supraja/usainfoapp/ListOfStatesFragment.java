package com.example.supraja.usainfoapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Supraja on 6/12/2017.
 */

public class ListOfStatesFragment extends Fragment {

    Context mContext;
    StatesRecyclerAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.states_fragment,container,false);

        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        DividerDecoration dividerDecoration = new DividerDecoration(getActivity());

        //StatesRecyclerAdapter adapter = new StatesRecyclerAdapter(getActivity(),StatesList.getData(),new ListOfStatesFragment() );
       // recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerDecoration);


       // HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
       // httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

       // OkHttpClient okHttpClient= new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("http://services.hanselandpetal.com")
                .addConverterFactory(GsonConverterFactory.create())
               // .client(okHttpClient)
                .build();

        ApiService res=retrofit.create(ApiService.class);

        Call<List<Datamm>> call=res.getFlowerNames();
        call.enqueue(new Callback<List<Datamm>>()
        {
            StatesRecyclerAdapter adapter;
            @Override
            public void onResponse(Call<List<Datamm>> call, Response<List<Datamm>> response) {
                // Log.e(".......","sd"+response.body().get(1).getName());


                List<Datamm> data=response.body();
                adapter =new StatesRecyclerAdapter(getContext(),data);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Datamm>> call, Throwable t) {

            }
        });



               return view;
    }

}
