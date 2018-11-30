package com.example.jrmy.velove;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

//Fragment d'affichage de la carte avec les markers des stations
public class MapsFragment extends Fragment implements OnMapReadyCallback {
    private MainActivity activity;
    private MapView mapView;
    private GoogleMap gMap;
    private ArrayList<Position> positions=new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_maps, container, false);
        mapView=rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        //Récupération des positions d'une instance précédente si jamais le fragment a été détruit
        if(savedInstanceState!=null) {
            positions = (ArrayList<Position>) savedInstanceState.getSerializable("positions");
        }

        return rootView;
    }

    //Méthode gérant la réception des données des markers
    public void dataReception(ArrayList<Position> p){
        positions.removeAll(positions);
        positions.addAll(p);
        for(Position pp : positions) {
            gMap.addMarker(new MarkerOptions().position(pp.getLatLng()).title(pp.getName()));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        //la carte est prête, on appelle la méthode de callback afin de récupérer les données
        activity.callDataReception();

        //Permet de fixer des limites à la caméra de la carte dans la zone géographique de Lyon
        LatLngBounds lyon = new LatLngBounds(
                new LatLng(45.714131, 4.784641), new LatLng(45.800052, 4.910138));
        gMap.setLatLngBoundsForCameraTarget(lyon);

        //Zoom et centrage sur la zone de Lyon
        gMap.setMinZoomPreference(11);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lyon.getCenter(), 12));

        //Ajout d'un déplacement de caméra et d'un zoom lors d'un clic sur un marker
        gMap.setOnMarkerClickListener(marker -> {
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(),15));
            return false;
        });

        //Appel à l'activité DetailsActivity lors d'un clic sur la bulle d'info du marker
        gMap.setOnInfoWindowClickListener(marker -> activity.callDetailsActivity(marker.getTitle()));

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

    //sécurisation des données lorsque le fragment est détruit
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putParcelableArrayList("positions",positions);
        super.onSaveInstanceState(savedInstanceState);
    }
}
