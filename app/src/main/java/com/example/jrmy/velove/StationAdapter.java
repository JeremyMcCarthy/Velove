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


    public StationAdapter(Context context,ArrayList<Station> stations) {
        this.stations = stations;
        this.context=context;
    }


    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_station, parent, false);
        return new StationViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        Station stationToDisplay = this.stations.get(position);
        holder.txvName.setText(stationToDisplay.getName());
        // On change la couleur de font d'un textView qui nous sert d'indicateur
        // Si le nombre de vélos disponibles est positif, on le met en vert, en rouge sinon
        if (stationToDisplay.getAvailableBikes()>0){
            holder.txvAvailability.setBackgroundColor(Color.GREEN);
        }
        else{
            holder.txvAvailability.setBackgroundColor(Color.RED);
        }
        // On affiche une icone de vélos (libre de droit) devant chaque station en utilisant PICASSO
        Picasso.with(context).load("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/Upright_urban_bicyclist.svg/1116px-Upright_urban_bicyclist.svg.png").into(holder.imgStation);
    }


    @Override
    public int getItemCount() {
        return this.stations.size();
    }

    // On retourne la station correspondant à la position passée en argument
    public Station getStation(int position){
        return this.stations.get(position);
    }
}
