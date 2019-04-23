package com.ticka.application.helpers;

import android.content.Context;

import ir.aid.library.Frameworks.utils.SharedPreferenceUtils;

public final class UserHelper {

    private static final String KEY_USERNAME = "Username";
    private static final String KEY_PASSWORD = "Password";
    private static final String KEY_NICKNAME = "Nickname";
    private static final String KEY_EMAIL    = "Email";
    private static final String KEY_PHONE    = "Phone";

    private static UserHelper userHelper = null;
    private SharedPreferenceUtils utils;

    public UserHelper(Context context) {
        this.utils = new SharedPreferenceUtils(context);

    }

    public static UserHelper getUserHelper(Context context){

        if(userHelper == null){
            userHelper = new UserHelper(context);
        }

        return userHelper;
    }

    public String getUsername() {
        return utils.readString(
                KEY_USERNAME,
                ""
        );
    }

    public void setUsername(String username) {
        utils.writeString(
                KEY_USERNAME,
                username
        );
    }

    public String getPassword() {
        return utils.readString(
                KEY_PASSWORD,
                ""
        );
    }

    public void setPassword(String password) {
        utils.writeString(
                KEY_PASSWORD,
                password
        );
    }

    public String getNickname() {
        return utils.readString(
                KEY_NICKNAME,
                ""
        );
    }

    public void setNickname(String nickname) {
        utils.writeString(
                KEY_NICKNAME,
                nickname
        );
    }

    public String getEmail() {
        return utils.readString(
                KEY_EMAIL,
                ""
        );
    }

    public void setEmail(String email) {
        utils.writeString(
                KEY_EMAIL,
                email
        );
    }

    public String getPhone() {
        return utils.readString(
                KEY_PHONE,
                ""
        );
    }

    public void setPhone(String phone) {
        utils.writeString(
                KEY_PHONE,
                phone
        );
    }
}
