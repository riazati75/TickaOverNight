package com.ticka.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alirezaashrafi.library.GoogleMapView;
import com.alirezaashrafi.library.MapScale;
import com.alirezaashrafi.library.MapType;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        GoogleMapView googleMapView = findViewById(R.id.googleMapView);
        googleMapView.setLatitude(35.744920f);
        googleMapView.setLongitude(51.376303f);
        googleMapView.setMapType(MapType.SATELLITE);
        googleMapView.setMapScale(MapScale.HIGH);
        googleMapView.setMapZoom(15);
        googleMapView.setMapWidth(350);
        googleMapView.setMapHeight(350);
        googleMapView.setZoomable(this);
//        googleMapView.setLocation(location);

    }
}
