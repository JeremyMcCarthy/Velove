package com.example.jrmy.velove;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class StationAdapter extends RecyclerView.Adapter<StationViewHolder> {
    private ArrayList<Station> stations;

    // Provide a suitable constructor (depends on the kind of dataset)
    public StationAdapter(ArrayList<Station> stations) {
        this.stations = stations;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_station, parent, false);
        return new StationViewHolder(rootView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Station stationToDisplay = this.stations.get(position);

        holder.txvName.setText(stationToDisplay.getName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.stations.size();
    }

    public Station getStation(int position){
        return this.stations.get(position);
    }
}
