package com.example.supraja.usainfoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Supraja on 6/12/2017.
 */

public class ListOfStatesFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.states_fragment,container,false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        DividerDecoration dividerDecoration = new DividerDecoration(getActivity());
        StatesRecyclerAdapter adapter = new StatesRecyclerAdapter(getActivity(),StatesList.getData());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerDecoration);

        return view;
    }
}
