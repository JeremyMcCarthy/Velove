package com.example.jrmy.velove;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class BikeListFragment extends Fragment {

    private ArrayList<Station> stations = new ArrayList<>();
    private StationAdapter stationAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bike_list, container, false);
        RecyclerView rcvStation = rootView.findViewById(R.id.rcvStations);
        rcvStation.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvStation.setLayoutManager(layoutManager);

        stationAdapter = new StationAdapter(stations);
        rcvStation.setAdapter(stationAdapter);


        return rootView;
    }

    public void dataReception(ArrayList<Station> s){
        stations.addAll(s);
        stationAdapter.notifyDataSetChanged();
    }

}
