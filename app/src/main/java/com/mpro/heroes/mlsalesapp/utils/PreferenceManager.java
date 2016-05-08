package com.mpro.heroes.mlsalesapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by cmacias on 4/3/16.
 */
public class PreferenceManager {

    public static boolean getBoolean(String strPreferenceName, boolean bDefaultValue, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPref", 0);
        return sharedPreferences.getBoolean(strPreferenceName, bDefaultValue);
    }

    public static boolean setBoolean(String strPreferenceName, boolean isSelected, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPref", 0);
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(strPreferenceName, isSelected);
            editor.apply();
            return true;
        } catch (Throwable tr) {
            //TODO: create a logger method under utils folder Logger.error(tr)
            Log.d("Fenix",tr.getMessage());
        }
        return false;
    }

    public static int getInt(String strPreferenceName, int iDefaultValue, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPref", 0);
        return sharedPreferences.getInt(strPreferenceName, iDefaultValue);
    }

    public static boolean setInt(String strPreferenceName, int iData, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPref", 0);
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(strPreferenceName, iData);
            editor.apply();
            return true;
        } catch (Throwable tr) {
            Log.d("Fenix",tr.getMessage());
        }
        return false;
    }
}
