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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("FlowerChecker");
        setSupportActionBar(toolbar);

        textView = (TextView)findViewById(R.id.textView);
        name = getIntent().getStringExtra("profileName");
        Log.d("####","Profile Name: "+name);
        textView.setText(name);

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

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

        @Override
        public void onBackPressed() {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }

}
