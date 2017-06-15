package com.example.supraja.usainfoapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Supraja on 6/12/2017.
 */

public class StateJsonParser {

    public static ArrayList<String> usStates = new ArrayList<>();
    public static ArrayList<String> favTeams = new ArrayList<>();

    public   String getJSONString(Context context) {
        String str = "";
        try {
            AssetManager assetManager = context.getAssets();
            InputStream in = assetManager.open("states.json");
            InputStreamReader isr = new InputStreamReader(in);
            char[] inputBuffer = new char[100];

            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return str;
    }

    public  void parseJSON(Context context) throws JSONException {

        String string = getJSONString(context);

        JSONObject o = new JSONObject(string);
        JSONArray a = o.getJSONArray("Names");
        for (int i = 0; i < a.length(); i++) {
            Log.d("States", a.getString(i));
        }
    }
}

//        JSONObject json;
//
//        try {
//            json = new JSONObject(getJSONString(getApplicationContext()));
//
//            JSONObject statesJsonObject = json.getJSONObject("STATES");
//            Log.e("####",statesJsonObject.toString());
//
//            String state = statesJsonObject.getString("NH");
//            Log.e("####",state);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        //implement logic with JSON here
//    }
//
//
//    public static ArrayList<String> getUsStates(JSONObject obj) {
//        usStates.clear();
//        try {
//
//            JSONArray arr = obj.getJSONArray("Names");
//            String s;
//            for (int i = 0; i < arr.length(); i++) {
//                obj = arr.getJSONObject(i);
//                s = obj.getString("NH");
//                usStates.add(s);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return usStates;
//    }
//
//    public static ArrayList<String> getFavTeams(JSONObject obj) {
//        favTeams.clear();
//        try {
//
//            JSONArray arr = obj.getJSONArray("favorite_teams");
//            String s;
//            for (int i = 0; i < arr.length(); i++) {
//                obj = arr.getJSONObject(i);
//                s = obj.getString("name");
//                favTeams.add(s);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return favTeams;
//    }
//
//    public static String getName(JSONObject obj) {
//        String s1 = "";
//        try {
//
//            s1 = obj.getString("name");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return s1;
//    }
//
//
//    public static String getId(JSONObject obj) {
//        String s1 = "";
//        try {
//
//            s1 = obj.getString("id");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return s1;
//    }
//}
