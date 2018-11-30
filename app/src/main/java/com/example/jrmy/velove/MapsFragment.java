package com.example.jrmy.velove;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragment extends Fragment implements OnMapReadyCallback {
    public static final String ARG_OBJECT = "object";
    private MainActivity activity;
    private MapView mapView;
    private GoogleMap gMap;
    private LatLngBounds lyon;
    private ArrayList<Position> positions=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_maps, container, false);
        mapView=rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        Log.d("MAP","yesyes");
        return rootView;
    }

    public void dataReception(ArrayList<Position> p){
        positions.addAll(p);
        for(Position pp : positions) {
            gMap.addMarker(new MarkerOptions().position(pp.getLatLng()).title(pp.getName()));
        }
        //gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lyon,12));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("DATA","map recue");
        gMap = googleMap;
        activity.callDataReception();
        lyon = new LatLngBounds(
                new LatLng(45.714131, 4.784641), new LatLng(45.800052, 4.910138));
        gMap.setLatLngBoundsForCameraTarget(lyon);
        gMap.setMinZoomPreference(11);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lyon.getCenter(), 12));
        gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(),15));
                return false;
            }
        });
        gMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                activity.callDetailsActivity(marker.getTitle());
            }
        });
        mapView.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityMapsCallBack) {
            activity = (MainActivity) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public interface MainActivityMapsCallBack {
        void callDataReception();
        void callDetailsActivity(String name);
    }
}
