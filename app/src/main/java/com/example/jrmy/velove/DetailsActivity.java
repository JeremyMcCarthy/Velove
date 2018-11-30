package com.example.jrmy.velove;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        tvStationAddress.setText(station.getAdress()+"\n"+station.getAdress2());
        TextView tvAvailableBikes = findViewById(R.id.availableBikes);
        tvAvailableBikes.setText(""+station.getAvailableBikes());
        TextView tvAvailableBikeStands = findViewById(R.id.availableBikeStands);
        tvAvailableBikeStands.setText(""+station.getAvailableBikeStands());
        TextView tvLastUpdate = findViewById(R.id.lastUpdate);
        tvLastUpdate.setText(station.getLastUpdate());

        //limite de une requête par jour ...
        ImageView imageView =findViewById(R.id.imageView3);
        Log.d("STREET","entering");
        String url = "https://maps.googleapis.com/maps/api/streetview?size=400x400&location="+station.getLatitude()+","+station.getLongitude()+"&fov=90&heading=235&key=AIzaSyDrtYUze0KhgopDcNrjR4OiNTeAm9kAoMo";
        Picasso.with(getApplicationContext()).load(url).into(imageView);
        Log.d("STREET","exiting");
    }
}
