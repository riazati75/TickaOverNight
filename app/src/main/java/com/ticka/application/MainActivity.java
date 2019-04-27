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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ticka.application.adapters.SideAdapter;
import com.ticka.application.models.DataModel;
import com.ticka.application.models.SideList;
import com.ticka.application.models.SideModel;

import java.util.ArrayList;
import java.util.List;

import ir.aid.library.Frameworks.setup.SetupActivity;

import static com.ticka.application.core.CentralCore.ACTION_UPDATE_ACTIVITY;

@SuppressLint("StaticFieldLeak")
public class MainActivity extends SetupActivity {

    private static MainActivity activity;
    private List<SideModel> sideModels = new ArrayList<>();
    private BroadcastReceiver updater;
    private Fragment lastFragment = null;
    private DataModel dataModel = null;
    private SideAdapter sideAdapter;
    private RecyclerView sideMenu;
    private PopupMenu popupMenu;
    private DrawerLayout drawer_layout;
    private ImageView menu , popup;
    private TextView title;
    private boolean isExit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        init();
    }

    private void initViews(){

        activity = this;

        updateInformation();
        setupNotificationBar();

        drawer_layout = findViewById(R.id.drawer_layout);
        menu = findViewById(R.id.menu);
        title = findViewById(R.id.title);
        popup = findViewById(R.id.popup);
        sideMenu = findViewById(R.id.sideMenu);

        initSideMenu();
    }

    private void init(){

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sideAdapter.notifyDataSetChanged();
                drawer_layout.openDrawer(Gravity.END);
            }
        });

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

    private void initSideMenu(){

        sideMenu.setLayoutManager(new LinearLayoutManager(
                this , LinearLayoutManager.VERTICAL , false
        ));
        sideMenu.setHasFixedSize(true);

        for(int i = 0; i < SideList.TITLES.length; i++){
            sideModels.add(new SideModel(
                    SideList.ICONS[i],
                    SideList.TITLES[i]
            ));
        }

        sideAdapter = new SideAdapter(this, sideModels)
                .setOnItemClickListener(new SideAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        setFragment(SideList.getFragments().get(position));
                        title.setText(SideList.getFragmentsTitle().get(position));
                        sideAdapter.setLastPosition(position);

                        if(drawer_layout.isDrawerOpen(Gravity.END)){
                            drawer_layout.closeDrawer(Gravity.END);
                        }
                    }
                });
        sideMenu.setAdapter(sideAdapter);
    }

    public static MainActivity getActivity() {
        return activity;
    }

    public DataModel getDataModel() {
        if(dataModel == null){
             dataModel = new DataModel();
        }
        return dataModel;
    }

    private void updateInformation(){

        updater = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                int pos = sideAdapter.getLastPosition() + 1;
                sideAdapter.setLastPosition(pos);

                setFragment(SideList.getFragments().get(pos));
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
        if(drawer_layout.isDrawerOpen(Gravity.END)){
            drawer_layout.closeDrawer(Gravity.END);
        }
        else if(isExit) {

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
