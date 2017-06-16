package com.example.supraja.usainfoapp;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Places;

import java.util.Collections;
import java.util.List;

/**
 * Created by Supraja on 6/13/2017.
 */

public class PlacesRecyclerAdapter extends  RecyclerView.Adapter<PlacesRecyclerAdapter.MainViewHolder>{

    private List<String> dataList;
    private List<LocationData.LocationBean> locList;
    private LayoutInflater inflater;
    private Context context;


    private ListOfPlacesFragment listOfPlacesFragment;

    public PlacesRecyclerAdapter(Context context,List<LocationData.LocationBean> loc) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.locList = loc;

    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 2;
        } else return 1;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.places_list, parent, false);
            return new MainViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

           // holder.textView.setText(dataList.get(position));
            holder.textView.setText(locList.get(position).getState());

//        holder.imageView.setImageResource(current.getImageID());

    }


    public class MainViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView2;
        ImageView imageView;

        public MainViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
           // textView2 = (TextView) itemView.findViewById(R.id.textView1);
//          imageView = (ImageView) itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"First View",Toast.LENGTH_SHORT).show();

//                    stateDetailsFragment = new StateDetailsFragment();
////                  Bundle args = new Bundle();
////                  args.putString("data", "This data has sent to FragmentTwo");
////                  stateDetailsFragment.setArguments(args);
//                    transaction=((SecondActivity)context).getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout, stateDetailsFragment);
//                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                    transaction.addToBackStack(null);
//                    transaction.commit();

                }
            });
        }
    }

}