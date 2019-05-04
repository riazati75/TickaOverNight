package com.ticka.application;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ticka.application.models.DataModel;
import com.ticka.application.models.SideList;

import ir.aid.library.Frameworks.setup.SetupActivity;

import static com.ticka.application.core.CentralCore.ACTION_UPDATE_ACTIVITY;

@SuppressLint("StaticFieldLeak")
public class MainActivity extends SetupActivity {

    private static DataModel DATA_MODEL = null;
    private BroadcastReceiver updater;
    private Fragment lastFragment = null;
    private PopupMenu popupMenu;
    private ImageView menu , popup;
    private TextView title;
    private int pos = 0;
    private boolean isExit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        init();
    }

    private void initViews(){

        setupNotificationBar();

        menu          = findViewById(R.id.menu);
        title         = findViewById(R.id.title);
        popup         = findViewById(R.id.popup);

        menu.setVisibility(View.GONE);

        updateInformation();
    }

    private void init(){

        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu = new PopupMenu(MainActivity.this , view);
                popupMenu.inflate(R.menu.menu_on);
                popupMenu.show();
            }
        });

        setFragment(SideList.getFragments().get(0));
        title.setText(SideList.getFragmentsTitle().get(0));
    }

    private void setFragment(Fragment fragment){

        if(lastFragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(lastFragment)
                    .commit();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out)
                .addToBackStack(null)
                .commit();

        lastFragment = fragment;

    }

    public static DataModel getDataModel() {
        if(DATA_MODEL == null){
             DATA_MODEL = new DataModel();
        }
        return DATA_MODEL;
    }

    private void updateInformation(){

        updater = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                pos = pos + 1;

                setFragment(SideList.getFragments().get(pos));
                title.setText(SideList.getFragmentsTitle().get(pos));
            }
        };

        registerReceiver(updater , new IntentFilter(ACTION_UPDATE_ACTIVITY));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(updater);
        super.onDestroy();
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
        if(isExit) {

            isExit = false;

            Toast.makeText(this, "لطفا برای خروج یکبار دیگه ضربه بزنید", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = true;
                }
            } , 2000);
        }
        else {
            finish();
        }
    }
}
