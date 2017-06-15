package com.example.supraja.usainfoapp;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Supraja on 6/9/2017.
 */

public class SecondActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView textView;
    String name;
    JSONObject object;
    Context context;

    public static final String BASE_URL = "https://restcountries.eu/rest/v2/";
    private static final String TAG = "SecondActivity";

    List<User> Users;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("USAInfo");
        setSupportActionBar(toolbar);

        textView = (TextView)findViewById(R.id.textView);
         name = getIntent().getStringExtra("profileName");
        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
        textView.setText(name);


        ApiService apiService = RestClient.getApiService();
        Call<User> userCall=apiService.getCountryNames();
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("><><><><><>>",""+response.body().getName());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        ListOfStatesFragment statesFragment = new ListOfStatesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_layout, statesFragment);
        fragmentTransaction.commit();





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

            menu.findItem(R.id.profileName).setTitle("Hey");
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profileName:
                Toast.makeText(this,"ProfileName",Toast.LENGTH_LONG).show();
                break;
            case R.id.logout:
                logoutUser();
                break;
        }
        return true;
    }

    private void logoutUser() {
        LoginManager.getInstance().logOut();
        Toast.makeText(this,"Logged out",Toast.LENGTH_LONG).show();

//        session.setLogin(false);
//
//        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        SecondActivity.this.finish();
    }
}
