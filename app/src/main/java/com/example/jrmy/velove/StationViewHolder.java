package com.example.jrmy.velove;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StationViewHolder extends RecyclerView.ViewHolder {

    public TextView txvName;
    public TextView txvAvailability;
    public ImageView imgStation;


    public StationViewHolder(View rootView) {
        super(rootView);
        this.txvName = rootView.findViewById(R.id.tvStationName);
        this.txvAvailability = rootView.findViewById(R.id.textView);
        this.imgStation = rootView.findViewById(R.id.imageView);
    }
}
