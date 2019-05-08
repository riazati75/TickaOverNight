package com.ticka.application.core;

import com.alirezaashrafi.library.GoogleMapViewConfigs;
import com.alirezaashrafi.library.MapScale;
import com.alirezaashrafi.library.MapType;

import ir.aid.library.Frameworks.core.Core;

public class CentralCore extends Core {

    public static final String ACTION_UPDATE_ACTIVITY = "action.update.activity";

    @Override
    public void onCreate() {
        super.onCreate();
        setupFont("yekan_regular.ttf");

        GoogleMapViewConfigs.setDefaultMapType(MapType.HYBRID);
        GoogleMapViewConfigs.setDefaultLatitude(35.744920f);
        GoogleMapViewConfigs.setDefaultLongitude(51.376303f);
        GoogleMapViewConfigs.setDefaultMapZoom(17);
        GoogleMapViewConfigs.setDefaultMapScale(MapScale.HIGH);
        GoogleMapViewConfigs.setDefaultMapHeight(350);
        GoogleMapViewConfigs.setDefaultMapWidth(350);
    }

}
