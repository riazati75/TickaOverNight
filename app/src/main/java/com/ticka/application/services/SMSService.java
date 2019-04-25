package com.ticka.application.services;

import android.content.Context;
import android.content.Intent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ir.aid.library.Frameworks.receiver.SMSScanner;

public class SMSService extends SMSScanner {

    public static final String SMS_ACTION = "ticka.action.SMS";

    @Override
    protected void onMessageReceived(Context context, Intent intent, String phone, String message) {

        if(isPhoneValidate(phone)){
            Intent smsCallback = new Intent(SMS_ACTION);
            smsCallback.putExtra("code" , getCode(message));
            context.sendBroadcast(smsCallback);
        }
    }

    private boolean isPhoneValidate(String phone){
        return phone.equals("");
    }

    private String getCode(String message){
        String code = "";
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(message);
        while(m.find()) {
            code = code + m.group();
        }
        return code;
    }
}
