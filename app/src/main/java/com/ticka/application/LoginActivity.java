package com.ticka.application;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import ir.aid.library.Frameworks.setup.SetupActivity;

import static com.ticka.application.services.SMSService.SMS_ACTION;

public class LoginActivity extends SetupActivity {

    private static final int LENGTH_ANIMATION = 800;
    private static final int REQUEST_CODE = 21;

    private Button btnPhone  , btnCode;
    private EditText inPhone, inCode;
    private LinearLayout inputPhone , inputCode;
    private RelativeLayout layoutInput;
    private BroadcastReceiver sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        smsInitial();
    }

    private void initViews(){

        setNotificationBar();

        btnPhone = findViewById(R.id.btnPhone);
        btnCode  = findViewById(R.id.btnCode);
        inPhone  = findViewById(R.id.inPhone);
        inCode   = findViewById(R.id.inCode);
        inputPhone   = findViewById(R.id.inputPhone);
        inputCode    = findViewById(R.id.inputCode);
        layoutInput  = findViewById(R.id.layoutInput);

        initActivity();
    }

    private void initActivity(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                firstAnimation();
            }
        } , 400);

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(
                                LoginActivity.this , HomeListActivity.class
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

    private void smsInitial(){

        sms = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if(SMS_ACTION.equals(intent.getAction())){
                    String code = intent.getStringExtra("code");
                    inCode.setText(code);
                }
            }
        };
    }

    private void checkPermission(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(ContextCompat.checkSelfPermission(this , Manifest.permission.RECEIVE_SMS)
                    != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.RECEIVE_SMS
                        }, REQUEST_CODE
                );
            }
            else {
                closePhoneLayout();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SMS_ACTION);
                        intent.putExtra("code" , "564731");
                        sendBroadcast(intent);
                    }
                } , 2500);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE){

            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

                closePhoneLayout();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SMS_ACTION);
                        intent.putExtra("code" , "564731");
                        sendBroadcast(intent);
                    }
                } , 2500);
            }
            else {
                checkPermission();
            }
        }
    }

    @Override
    protected void onResume() {
        registerReceiver(sms , new IntentFilter(SMS_ACTION));
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(sms);
        super.onDestroy();
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
