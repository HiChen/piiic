package com.gewara.piiic.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.gewara.piiic.Configs;


/**
 * Created by user on 2016/1/26.
 */
public class SettingPreferences {
    private static SettingPreferences instance;
    private static SharedPreferences sharedPrefs;

    private final String KEYBOARD_HEIGHT = "getKeyboardHeight";
    private static String asrPtt;

    private SettingPreferences(Context context) {
        sharedPrefs = context.getSharedPreferences(Configs.SHARED, Context.MODE_PRIVATE);
    }

    public static SettingPreferences getInstance(Context context) {
        if(sharedPrefs == null)
            instance = new SettingPreferences(context.getApplicationContext());
        return instance;
    }

    public static String getAsrPtt() {
        return asrPtt;
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

    public void setKeyboardHeight(int keyboardHeight){
        putInt(KEYBOARD_HEIGHT,keyboardHeight);
    }

    public  int getKeyboardHeight(){
        return getInt(KEYBOARD_HEIGHT,500);
    }


    public class SpeechLang {
    }
}
