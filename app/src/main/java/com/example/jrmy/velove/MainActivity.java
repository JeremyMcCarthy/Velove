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
    private int nbStations;
    private JsonObject r;
    private ArrayList<JsonObject> stations;

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
                listStations = o.get("features").getAsJsonArray();
                nbStations = listStations.size();
                mTextView.setText(listStations.toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                mTextView.setText("et non !");
            }
        });
        /*for (int i=0; i<nbStations;i++){
            r = listStations.get(i).getAsJsonObject();
            stations.set(i,r);
        }
        mTextView.setText(r.toString());*/
    }
}
