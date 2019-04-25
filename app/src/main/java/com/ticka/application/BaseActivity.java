package com.ticka.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        WebView web = findViewById(R.id.web);

        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);

        web.loadUrl(
                "file:///android_asset/web/index.html"
        );

    }
}
