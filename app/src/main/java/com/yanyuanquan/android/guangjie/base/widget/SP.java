package com.yanyuanquan.android.guangjie.base.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Created by apple on 16/7/7.
 */

public class SP {


    private static Context mContext;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;
    private static Gson gson;
    public static String KEY = "guider_sharedpreference";

    public static void init(Context context) {
        mContext = context;
        sp = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        editor = sp.edit();
        gson = new Gson();
    }

    public static void save(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        editor.putString(key, value);
        try {
            editor.commit();
        }catch (Exception e){
        }
    }
    public static void save(String key,int value){
        if (TextUtils.isEmpty(key))
            return;
        editor.putInt(key,value);
        editor.commit();
    }
    public static void save(String key,long value){
        if (TextUtils.isEmpty(key)){
            return;
        }
        editor.putLong(key,value);
        editor.commit();

    }
    public static void save(String key,boolean value){
        if (TextUtils.isEmpty(key))
            return;
        editor.putBoolean(key,value).commit();
    }


    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defValue) {
        try {
            return sp.getString(key, "");
        } catch (Exception e) {
            remove(key);
        }
        return defValue;
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }


    public static int getInt(String key, int defValue) {
        try {
            return sp.getInt(key, defValue);
        } catch (RuntimeException e) {
            try {
                return Integer.parseInt(getString(key));
            } catch (Exception e2) {
                remove(key);
            }
        }
        return defValue;
    }

    public static long getLong(String key,long defvalue){
        try {
            return sp.getLong(key, defvalue);
        }catch (RuntimeException e){
            remove(key);
            return defvalue;
        }
    }
    public static long getLong(String key){
        return getLong(key,0);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        try {
            return sp.getBoolean(key, defValue);
        } catch (Exception e) {
            try {
                return Boolean.parseBoolean(getString(key));
            } catch (Exception e3) {
                remove(key);
            }
        }
        return defValue;
    }

    public static void remove(String key) {
        if (editor != null)
            editor.remove(key).commit();
    }

}
