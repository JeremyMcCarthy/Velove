package com.example.jrmy.velove;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceRequete {

    @GET("wfs/rdata?SERVICE=WFS&VERSION=2.0.0&outputformat=GEOJSON&maxfeatures=30&request=GetFeature&typename=jcd_jcdecaux.jcdvelov&SRSNAME=urn%3Aogc%3Adef%3Acrs%3AEPSG%3A%3A4171&fbclid=IwAR29hPZV2iPdKzWlCaEayAkE7RQT1g2KHjW-ovruGgY2ui0dFTDXCVivEbM")
    Call<JsonObject> getstations();
}
