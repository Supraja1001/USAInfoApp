package com.example.supraja.usainfoapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Supraja on 6/13/2017.
 */

public class ListOfPlacesFragment extends Fragment {

    Context mContext;
    View view;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.places_fragment,container,false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        DividerDecoration dividerDecoration = new DividerDecoration(getActivity());
        PlacesRecyclerAdapter adapter = new PlacesRecyclerAdapter(getActivity(),getList(),getLocationList(),new ListOfPlacesFragment() );
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerDecoration);

        return view;
    }

    public static String AssetJSONFile (String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();

        return new String(formArray);
    }

    public List<String> getList(){
        mContext = getApplicationContext();
        List<String> formList = new ArrayList<>();
        String placeName;
        try {
            String jsonLocation = AssetJSONFile("places.json", mContext);
            JSONObject jsonobject = new JSONObject(jsonLocation);
            JSONArray jarray = jsonobject.getJSONArray("PlacesToVisit");
            for(int i=0;i<jarray.length();i++)
            {
                JSONObject jb =(JSONObject) jarray.get(i);
                placeName = jb.getString("place");
                Log.e("####",placeName.toString());
                formList.add(placeName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return formList;
    }
    public List<String> getLocationList(){
        mContext = getApplicationContext();
        List<String> locList = new ArrayList<>();
        String locName;
        try {
            String jsonLocation = AssetJSONFile("places.json", mContext);
            JSONObject jsonobject = new JSONObject(jsonLocation);
            JSONArray jarray = jsonobject.getJSONArray("PlacesToVisit");
            for(int i=0;i<jarray.length();i++)
            {
                JSONObject jb =(JSONObject) jarray.get(i);
                locName = jb.getString("Location");
                Log.e("####",locName.toString());
                locList.add(locName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locList;
    }
}
