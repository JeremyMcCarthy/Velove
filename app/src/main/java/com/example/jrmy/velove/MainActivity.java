package com.example.jrmy.velove;

import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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

import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TableLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    CollectionPagerAdapter mCollectionPagerAdapter;
    ViewPager mViewPager;
    private GoogleMap mMap;
    private InterfaceRequete service;

    private TextView mTextView;
    private JsonArray listStations;
    private ArrayList<Station> stations;

/*mViewPager.addOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });*/


        /*final ActionBar actionBar = getSupportActionBar();
        // Specify that tabs should be displayed in the action bar.
        actionBar.set
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // show the given tab
                mViewPager.setCurrentItem(tab.getPosition());
            }
    @Override
            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // hide the given tab
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // probably ignore this event
            }
        };

        // Add 3 tabs, specifying the tab's text and TabListener
        for (int i = 0; i < 3; i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("Tab " + (i + 1))
                            .setTabListener(tabListener));
        }*/

    }

    /*@Override
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

// ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mCollectionPagerAdapter =
                new CollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(mCollectionPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

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
    }*/

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/
}
