package com.example.jrmy.velove;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Position implements Serializable, Parcelable {
    private LatLng latLng;
    private String name;

    public Position(LatLng l,String n) {
        latLng=l;
        name=n;
    }

    protected Position(Parcel in) {
        latLng = in.readParcelable(LatLng.class.getClassLoader());
        name = in.readString();
    }

    public static final Creator<Position> CREATOR = new Creator<Position>() {
        @Override
        public Position createFromParcel(Parcel in) {
            return new Position(in);
        }

        @Override
        public Position[] newArray(int size) {
            return new Position[size];
        }
    };

    public String getName() {
        return name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(latLng,flags);
        dest.writeString(name);
    }
}
