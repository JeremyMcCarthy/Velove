package com.example.jrmy.velove;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class StationViewHolder extends RecyclerView.ViewHolder {
    public TextView txvName;

    public StationViewHolder(View rootView) {
        super(rootView);
        this.txvName = rootView.findViewById(R.id.tvStationName);
    }
}
