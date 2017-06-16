package com.example.supraja.usainfoapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;
import static java.security.AccessController.getContext;

/**
 * Created by Supraja on 6/12/2017.
 */

public class StatesRecyclerAdapter extends  RecyclerView.Adapter<StatesRecyclerAdapter.GenericViewHolder>{

    private StateDetailsFragment stateDetailsFragment;
    private ListOfStatesFragment listOfStatesFragment;
    FragmentTransaction transaction;
    FragmentManager fragmentManager;

    //private List<StatesList> dataList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    List<Datamm> flowerdatalist;

    public StatesRecyclerAdapter(Context context, List<Datamm> data) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.flowerdatalist = data;
        this.listOfStatesFragment = listOfStatesFragment;

    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 2;
        } else return 1;
    }

    @Override
    public int getItemCount() {
        return flowerdatalist.size();
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 1) {
            View view = inflater.inflate(R.layout.list, parent, false);
            return new MainViewHolder(view);
        } else {
            View view2 = inflater.inflate(R.layout.list2, parent, false);
            return new SecondViewHolder(view2);
        }

    }

    @Override
    public void onBindViewHolder(final GenericViewHolder holder, final int position) {
       // final StatesList current = flowerdatalist.get(position);

        switch (holder.getItemViewType()) {

            case 1:

                MainViewHolder mainHolder = (MainViewHolder) holder;

                mainHolder.textView.setText(flowerdatalist.get(position).getName());

                final String url="http://services.hanselandpetal.com/photos/"+flowerdatalist.get(position).getPhoto();

                Picasso.with(((MainViewHolder) holder).imageView.getContext())
                        .load(url)
                        .into(((MainViewHolder) holder).imageView, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Log.e("gg>><><>M>",""+holder.getAdapterPosition());
                            }
                        });

                mainHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        stateDetailsFragment = new StateDetailsFragment();
                        Bundle args = new Bundle();
                        args.putString("Image", url);
                        stateDetailsFragment.setArguments(args);
                        transaction=((SecondActivity)context).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, stateDetailsFragment);
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                });

                break;

            case 2:

                SecondViewHolder secondHolder = (SecondViewHolder) holder;

                secondHolder.textView.setText(flowerdatalist.get(position).getName());

                final String url2 ="http://services.hanselandpetal.com/photos/"+flowerdatalist.get(position).getPhoto();

                Picasso.with(((SecondViewHolder) holder).imageView.getContext())
                        .load(url2)
                        .into(((SecondViewHolder) holder).imageView, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Log.e("gg>><><>M>",""+holder.getAdapterPosition());
                            }
                        });

                secondHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stateDetailsFragment = new StateDetailsFragment();
                        Bundle args = new Bundle();
                        args.putString("Image", url2);
                        stateDetailsFragment.setArguments(args);
                        transaction=((SecondActivity)context).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, stateDetailsFragment);
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                });
                break;

        }

    }

    public class GenericViewHolder extends RecyclerView.ViewHolder {

        public GenericViewHolder(View itemView) {
            super(itemView);
        }

    }

    public class MainViewHolder extends GenericViewHolder {

        TextView textView;
        ImageView imageView;

        public MainViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context,"First View",Toast.LENGTH_SHORT).show();
//
//                    stateDetailsFragment = new StateDetailsFragment();
////                  Bundle args = new Bundle();
////                  args.putString("data", "This data has sent to FragmentTwo");
////                  stateDetailsFragment.setArguments(args);
//                    transaction=((SecondActivity)context).getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.frame_layout, stateDetailsFragment);
//                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//
//                }
//            });
        }
    }

    public class SecondViewHolder extends GenericViewHolder {

        TextView textView;
        ImageView imageView;

        public SecondViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}
