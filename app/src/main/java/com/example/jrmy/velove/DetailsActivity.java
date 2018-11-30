package com.example.jrmy.velove;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle extras = getIntent().getExtras();
        Station station =(Station) extras.getSerializable("station");
        TextView tvStationName = findViewById(R.id.stationName);
        tvStationName.setText(station.getName());
        TextView tvStationAddress = findViewById(R.id.stationAddress);
        tvStationAddress.setText(station.getAdress()+station.getAdress2());
        TextView tvAvailableBikes = findViewById(R.id.availableBikes);
        tvAvailableBikes.setText(""+station.getAvailableBikes());
        TextView tvAvailableBikeStands = findViewById(R.id.availableBikeStands);
        tvAvailableBikeStands.setText(""+station.getAvailableBikeStands());
        TextView tvLastUpdate = findViewById(R.id.lastUpdate);
        tvLastUpdate.setText(""+station.getAvailableBikeStands());
    }
}
