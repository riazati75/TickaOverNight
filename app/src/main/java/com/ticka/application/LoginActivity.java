package com.ticka.application;

import android.content.Context;
import android.os.Bundle;

import ir.aid.library.Frameworks.setup.SetupActivity;

public class LoginActivity extends SetupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
}
