package com.example.jrmy.velove;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.*;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private InterfaceRequete service;

    private TextView mTextView;
    private JsonArray listStations;
    private ArrayList<Station> stations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://download.data.grandlyon.com/")
                .build();
        service = retrofit.create(InterfaceRequete.class);

        getStationsFromServer();



    }

    private void getStationsFromServer(){
        Call<JsonObject> myJson = service.getstations();
        myJson.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                JsonObject o = response.body();
                formatageData(o);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                mTextView.setText("et non !");
            }
        });
    }

    private void formatageData(JsonObject o){
        stations = new ArrayList<>();
        listStations = o.get("features").getAsJsonArray();
        for (int i=0; i<listStations.size();i++){
            stations.add(new Station());
            stations.get(i).setID(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("number").getAsInt());
            stations.get(i).setName(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("name").getAsString());
            stations.get(i).setAdress(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("address").getAsString());
            stations.get(i).setAdress2(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("address2").getAsString());
            stations.get(i).setCommune(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("commune").getAsString());
            stations.get(i).setNumArrondissement(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("nmarrond").getAsInt());
            stations.get(i).setBonus(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("bonus").getAsString());
            stations.get(i).setPole(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("pole").getAsString());
            stations.get(i).setLatitude(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("lat").getAsFloat());
            stations.get(i).setLongitude(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("lng").getAsFloat());
            stations.get(i).setBikeStands(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("bike_stands").getAsInt());
            stations.get(i).setStatus(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("status").getAsString());
            stations.get(i).setAvailableBikeStands(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("available_bike_stands").getAsInt());
            stations.get(i).setAvailableBikes(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("available_bikes").getAsInt());
            stations.get(i).setAvailabilityCode(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("availabilitycode").getAsInt());
            stations.get(i).setAvailability(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("availability").getAsString());
            stations.get(i).setBanking(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("banking").getAsInt());
            stations.get(i).setGid(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("gid").getAsInt());
            stations.get(i).setLastUpdate(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("last_update").getAsString());
            stations.get(i).setLastUpdateFme(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("last_update_fme").getAsString());
            stations.get(i).setCodeInsee(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject().get("code_insee").getAsInt());
        }

        mTextView.setText(stations.get(0).getName());
    }
}
