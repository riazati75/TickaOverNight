package com.ticka.application;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class BaseActivity extends AppCompatActivity {

    private CardView cv1 , cv2;

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


        cv1 = findViewById(R.id.cv1);
        cv2 = findViewById(R.id.cv2);

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cv1.animate().rotationY(90).setInterpolator(new LinearInterpolator()).setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        cv1.setVisibility(View.GONE);
                        cv2.setVisibility(View.VISIBLE);
                        cv2.animate().setInterpolator(new LinearInterpolator()).rotationY(0).setDuration(500).start();

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
            }
        });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cv2.animate().rotationY(-90).setInterpolator(new LinearInterpolator()).setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        cv2.setVisibility(View.GONE);
                        cv1.setVisibility(View.VISIBLE);
                        cv1.animate().setInterpolator(new LinearInterpolator()).rotationY(0).setDuration(500).start();

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
            }
        });
    }
}
