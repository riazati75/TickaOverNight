package com.ticka.application.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;

public class ActivityOpt {

    private static ActivityOpt opt = null;
    private static OnReceivedAction onReceivedAction;
    private BroadcastReceiver receiver;

    private ActivityOpt() {
        this.receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onReceivedAction.onAction();
            }
        };
    }

    public static ActivityOpt getInstance(OnReceivedAction onReceivedAction){
        ActivityOpt.onReceivedAction = onReceivedAction;

        if(opt == null){
            opt = new ActivityOpt();
        }

        return opt;
    }

    public void register(AppCompatActivity appCompatActivity , String filter){
        appCompatActivity.registerReceiver(receiver , new IntentFilter(filter));
    }

    public void unregister(AppCompatActivity appCompatActivity){
        appCompatActivity.unregisterReceiver(receiver);
    }

    public interface OnReceivedAction {
        void onAction();
    }
}
