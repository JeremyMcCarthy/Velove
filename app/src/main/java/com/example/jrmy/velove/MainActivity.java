package com.example.jrmy.velove;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity implements MapsFragment.MainActivityMapsCallBack, StationListFragment.MainActivityBikeListCallBack {

    CollectionPagerAdapter mCollectionPagerAdapter;
    ViewPager mViewPager;
    private InterfaceRequete service;

    private StationListFragment stationFragment;
    private MapsFragment mapsFragment;

    private JsonArray listStations;
    private ArrayList<Station> stations = new ArrayList<>();
    private ArrayList<Position> positions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the Retrofit service
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://download.data.grandlyon.com/")
                .build();
        service = retrofit.create(InterfaceRequete.class);

        // fill the list of stations with the stations obtained with a request on the server
        getStationsFromServer();

        //Adapter pour la gestion des onglets et la gestion des swipes entre les différents fragments
        mCollectionPagerAdapter =
                new CollectionPagerAdapter(
                        getSupportFragmentManager());
        //Association de l'Adapter au ViewPager
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(mCollectionPagerAdapter);
        //Mise en place de la barre d'onglets
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    // Create the view of the ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Perform action for each button pressed in the ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            case R.id.action_refresh:
                // User chose the "refresh" action

                // fill the list of stations with the stations obtained with a request on the server (refresh the data)
                getStationsFromServer();
                return true;

            default: // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


    // fill the list of stations with the stations obtained with a request on the server
    private void getStationsFromServer(){
        Call<JsonObject> myJson = service.getstations();
        // Send the request to the server
        myJson.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                JsonObject o = response.body();
                formatageData(o);
                stationFragment = mCollectionPagerAdapter.getStationFragment();
                stationFragment.dataReception(stations);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    // Fill the stations with their properties and fill the list of stations with the stations
    private void formatageData(JsonObject o){
        listStations = o.get("features").getAsJsonArray();
        for (int i=0; i<listStations.size();i++){
            stations.add(new Station(""));
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
    }

    //Méthode de callback de l'interface du fragment de la carte MapsFragment
    //permet de passer les données nécessaires aux markers
    //on utilise l'objet Position afin de ne passer que les informations nécessaires pour chaque station
    @Override
    public void callDataReception() {
        mapsFragment = mCollectionPagerAdapter.getMapsFragment();
        for(Station s : stations) {
            positions.add(new Position(new LatLng(s.getLatitude(),s.getLongitude()),s.getName()));
        }
        //appel de la méthode de MapsFragment gérant les données des markers
        mapsFragment.dataReception(positions);
    }

    //Méthode de callback de l'interface du fragment de la carte MapsFragment ET de liste BikeListFragment
    //permet quand on clique respectivement sur la bulle d'info d'un marker pour MapsFragment
    //et quand on clique sur un item de la liste pour BikeListFragment
    //de lancer l'activité DetailsActivity qui affiche des détails pour une station
    //on passe donc les informations de la station via l'intent
    @Override
    public void callDetailsActivity(String name) {
        Intent intent = new Intent(this, DetailsActivity.class);
        for(Station s : stations) {
            if (s.getName().equals(name)) {
                intent.putExtra("station",s);
            }
        }
        startActivity(intent);
    }
}
