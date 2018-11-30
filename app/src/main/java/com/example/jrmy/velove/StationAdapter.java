package com.example.jrmy.velove;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StationAdapter extends RecyclerView.Adapter<StationViewHolder> {

    private Context context;
    private ArrayList<Station> stations;

    // Constructor of the Adapter
    public StationAdapter(Context context,ArrayList<Station> stations) {
        this.stations = stations;
        this.context=context;
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
        Station stationToDisplay = this.stations.get(position);
        holder.txvName.setText(stationToDisplay.getName());
        // Turn the indicator for each station in green if there are bikes available otherwise, turn it red
        if (stationToDisplay.getAvailableBikes()>0){
            holder.txvAvailability.setBackgroundColor(Color.GREEN);
        }
        else{
            holder.txvAvailability.setBackgroundColor(Color.RED);
        }
        // Put the bike icon in each imageView for each station (this image is free)
        Picasso.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/Upright_urban_bicyclist.svg/1116px-Upright_urban_bicyclist.svg.png").into(holder.imgStation);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.stations.size();
    }

    // Return the Station corresponding to a position in the list of stations
    public Station getStation(int position){
        return this.stations.get(position);
    }
}
