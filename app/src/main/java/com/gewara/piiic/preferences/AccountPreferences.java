package com.gewara.piiic.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.gewara.piiic.Configs;
import com.gewara.piiic.models.Account;


/**
 * Created by user on 2016/1/26.
 */
public class AccountPreferences {
    private static AccountPreferences instance;
    private static SharedPreferences sharedPrefs;

    private final String KEYBOARD_HEIGHT = "getKeyboardHeight";
    private final String ACCOUNT = "acount";
    private String EMAIL = "email";

    private AccountPreferences(Context context) {
        sharedPrefs = context.getSharedPreferences(Configs.SHARED, Context.MODE_PRIVATE);
    }

    public static AccountPreferences getInstance(Context context) {
        if(sharedPrefs == null)
            instance = new AccountPreferences(context.getApplicationContext());
        return instance;
    }

//    public static Footer getFooter() {
//        return null;
//    }

    public static Account getAccount() {
        return null;
    }

    public String getString(String key, String defaultValue) {
        return sharedPrefs.getString(key, defaultValue);
    }

    public String getString(String key) {
        return sharedPrefs.getString(key, "");
    }

    public boolean getBool(String key) {
        return sharedPrefs.getBoolean(key, false);
    }

    public boolean getBool(String key, boolean defaultValue) {
        return sharedPrefs.getBoolean(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPrefs.getInt(key, defaultValue);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor edit = sharedPrefs.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public void putBool(String key, boolean value) {
        SharedPreferences.Editor edit = sharedPrefs.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor edit = sharedPrefs.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public void srtAccount(String acount){
        putString(ACCOUNT, acount);
    }

    public String getEmail() {
        return getString(EMAIL);
    }

    public void setEmall(String email){
        putString(EMAIL,email);
    }

//    public static String getAccount(){
//        return  getString(ACCOUNT);
//    }
}
