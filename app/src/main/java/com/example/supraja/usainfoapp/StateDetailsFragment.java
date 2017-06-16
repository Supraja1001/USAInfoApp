package com.example.supraja.usainfoapp;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Supraja on 6/12/2017.
 */

public class StateDetailsFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap googleMap;
    View v;
    private MapView mapView;
    private Bundle mBundle;
    private boolean mapsSupported = true;
    private ListOfPlacesFragment listOfPlacesFragment;
    FragmentTransaction transaction;
    Button button;
    Context context;
    ImageView imageView;


    public StateDetailsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.state_details_fragment,container,false);
        button = (Button)v.findViewById(R.id.visitPlaces);
        imageView = (ImageView)v.findViewById(R.id.image);
        context = v.getContext();

        String imgPath = getArguments().getString("Image");
        Toast.makeText(context,"In Details"+imgPath,Toast.LENGTH_SHORT).show();
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
        imageView.setImageBitmap(bitmap);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfPlacesFragment = new ListOfPlacesFragment();
//                  Bundle args = new Bundle();
//                  args.putString("data", "This data has sent to FragmentTwo");
//                  listOfPlacesFragment.setArguments(args);
                transaction=((SecondActivity)context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, listOfPlacesFragment);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mapView = (MapView)v.findViewById(R.id.mapView);
//        if(mapView!=null){
//            mapView.onCreate(null);
//            mapView.onResume();
//            mapView.getMapAsync(this);
//        }
    }

    @Override
    public void onMapReady(GoogleMap mGoogleMap) {
        MapsInitializer.initialize(getContext());
        googleMap = mGoogleMap;

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(43.011040, -71.481180)).title("Greenbelt").snippet("Iam here"));
        CameraPosition greenbelt = CameraPosition.builder().target(new LatLng(43.011040, -71.481180))
                .zoom(15).bearing(0).tilt(45).build();
        mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(greenbelt));


    }
}