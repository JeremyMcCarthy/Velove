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
    private JsonObject r;
    private Station firstStation;
    private ArrayList<JsonObject> propStations;
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
                firstStation = new Station();
                propStations = new ArrayList<JsonObject>();
                listStations = o.get("features").getAsJsonArray();
                int len = listStations.size();
                //mTextView.setText(Integer.toString(len));
                r = listStations.get(0).getAsJsonObject();
                JsonObject prop = r.get("properties").getAsJsonObject();
                int id = prop.get("number").getAsInt();
                //mTextView.setText(Integer.toString(id));
                firstStation.setID(id);
                for (int i=0; i<listStations.size();i++){
                    propStations.add(listStations.get(i).getAsJsonObject().get("properties").getAsJsonObject());
                }
                for (int i=0; i<propStations.size();i++){
                    stations.add();
                }

                mTextView.setText(propStations.toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                mTextView.setText("et non !");
            }
        });
    }
}
