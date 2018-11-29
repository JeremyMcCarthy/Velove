package com.example.jrmy.velove;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Position implements Serializable {
    private LatLng latLng;
    private String name;

    public Position(LatLng l,String n) {
        latLng=l;
        name=n;
    }

    public String getName() {
        return name;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
