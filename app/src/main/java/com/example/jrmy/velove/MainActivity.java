package com.example.jrmy.velove;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.*;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView.findViewById(R.id.textView);
        try {
            String myurl = "https://download.data.grandlyon.com/wfs/rdata?SERVICE=WFS&VERSION=2.0.0&outputformat=GEOJSON&maxfeatures=30&request=GetFeature&typename=jcd_jcdecaux.jcdvelov&SRSNAME=urn%3Aogc%3Adef%3Acrs%3AEPSG%3A%3A4171&fbclid=IwAR352IoV57G0M3KnO2Rhxtb7ZuRJ94CMJHc52FuRkNmEOzugfL_VAMzxVvY";

            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            String test = inputStream.toString();
            //mTextView.setText(test);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
