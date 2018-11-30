package com.example.jrmy.velove;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class StationListFragment extends Fragment {

    private MainActivity activity;
    private ArrayList<Station> stations = new ArrayList<>();
    private StationAdapter stationAdapter;
    private RecyclerView rcvStation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bike_list, container, false);

        rcvStation = rootView.findViewById(R.id.rcvStations);
        rcvStation.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvStation.setLayoutManager(layoutManager);

        stationAdapter = new StationAdapter(this.getContext(),stations);
        rcvStation.setAdapter(stationAdapter);

        // Configure the click on Recyclerview
        this.configureOnClickRecyclerView();

        return rootView;
    }

    //
    public void dataReception(ArrayList<Station> s){
        stations.addAll(s);
        stationAdapter.notifyDataSetChanged();
    }

    // Configure item click on RecyclerView
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(rcvStation, R.layout.fragment_bike_list)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    // Get Station from adapter
                    Station s = stationAdapter.getStation(position);

                    // Launch the new activity with the details of the selected Station
                    activity.callDetailsActivity(s.getName());
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MapsFragment.MainActivityMapsCallBack) {
            activity = (MainActivity) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " ");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public interface MainActivityBikeListCallBack {
        void callDetailsActivity(String name);
    }
}
