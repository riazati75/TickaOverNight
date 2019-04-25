package com.ticka.application;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import ir.aid.library.Frameworks.setup.SetupActivity;

public class LoginActivity extends SetupActivity {

    private static final int LENGTH_ANIMATION = 800;

    private Button btnPhone  , btnCode;
    private LinearLayout inputPhone , inputCode;
    private RelativeLayout layoutInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        btnPhone = findViewById(R.id.btnPhone);
        btnCode  = findViewById(R.id.btnCode);
        inputPhone = findViewById(R.id.inputPhone);
        inputCode  = findViewById(R.id.inputCode);
        layoutInput  = findViewById(R.id.layoutInput);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                firstAnimation();
            }
        } , 400);

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePhoneLayout();
            }
        });

        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(
                                LoginActivity.this , MainActivity.class
                        )
                );
                finish();
            }
        });

    }

    private void firstAnimation(){

        int fx = layoutInput.getWidth() / 2;
        int fy = layoutInput.getHeight() / 2;
        Animator anim = ViewAnimationUtils.createCircularReveal(layoutInput , fx , fy , 0 , 2*fx);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                layoutInput.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //baseLayout.setVisibility(View.INVISIBLE);
            }
        });
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(LENGTH_ANIMATION);
        anim.start();

    }

    private void closePhoneLayout() {

        Animation animation = AnimationUtils.loadAnimation(this , R.anim.input_zoom_out);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                inputPhone.setVisibility(View.GONE);
                openCodeLayout();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        inputPhone.startAnimation(animation);
        animation.start();
    }

    private void openCodeLayout() {
        Animation animation = AnimationUtils.loadAnimation(this , R.anim.input_zoom_in);
        inputCode.startAnimation(animation);
        inputCode.setVisibility(View.VISIBLE);
        animation.start();
    }

    private void initViews(){
        setNotificationBar();
        initBackground();
    }

    private void initBackground(){

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
}
