package com.example.dreamsocialclub;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by CodeWarning on 25/11/17.
 */

public class PreferenceData {
    private SharedPreferences prefs;
    private Context context;

    public PreferenceData(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences("padmafollowup", Context.MODE_PRIVATE);
        // prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getStringValue(String key) {
        return prefs.getString(key, "");
    }

    public Integer getIntegerValue(String key) {
        return prefs.getInt(key, 0);
    }

    public Float getFloatValue(String key) {
        return prefs.getFloat(key, 0);
    }

    public Boolean getBooleanValue(String key) {
        return prefs.getBoolean(key, false);
    }

    public void setValue(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void setValue(String key, Boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void setValue(String key, Integer value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void setValue(String key, Float value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(key, value);
        editor.apply();
    }
}
