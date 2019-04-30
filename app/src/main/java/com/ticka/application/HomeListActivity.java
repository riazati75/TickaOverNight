package com.ticka.application;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import ir.aid.library.Frameworks.setup.SetupActivity;

public class HomeListActivity extends SetupActivity {

    private ImageView popup;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list);
        setupNotificationBar();
        initViews();
    }

    private void initViews() {

        popup = findViewById(R.id.popup);
        fab = findViewById(R.id.fab);

        init();
    }

    private void init() {

        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(
                                HomeListActivity.this , EditActivity.class
                        )
                );
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(
                                HomeListActivity.this , MainActivity.class
                        )
                );
            }
        });
    }

    public void setupNotificationBar() {

        Window w = getWindow();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS ,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
