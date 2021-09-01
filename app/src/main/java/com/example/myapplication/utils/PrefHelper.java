package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefHelper {
    public static final String STORAGE_NAME = "Storage name";

    private static SharedPreferences sharedPreferences = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;

    public static void init(Context cntxt){
        context = cntxt;
    }
    private static void init(){
        sharedPreferences = context.getSharedPreferences(STORAGE_NAME, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public static void setGetOnBoardIsShown(){
        if (sharedPreferences == null){
            init();
        }
        editor.putBoolean("onboard", true);
        editor.apply();
    }
    public static boolean getOnBoardIsShown(){
        if (sharedPreferences == null){
            init();
        }
        return sharedPreferences.getBoolean("onboard", false);
    }
}
