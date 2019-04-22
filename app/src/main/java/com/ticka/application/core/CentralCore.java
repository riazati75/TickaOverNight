package com.ticka.application.core;

import ir.aid.library.Frameworks.core.Core;

public class CentralCore extends Core {

    public static final String ACTION_UPDATE_ACTIVITY = "action.update.activity";

    @Override
    public void onCreate() {
        super.onCreate();
        setupFont("yekan_regular.ttf");
    }

}
