package com.yenaly.yenaly_libs.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences工具类
 */
@SuppressWarnings("all")
public class SharedPreferenceUtil {

    private static final String PREFERENCE_NAME;

    static {
        PREFERENCE_NAME = ContextUtil.getApplicationContext().getPackageName();
    }

    public static void write(Context context, String k, boolean v) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(k, v);
        editor.apply();
    }

    public static void write(Context context, String k, int v) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(k, v);
        editor.apply();
    }

    public static void write(Context context, String k, long v) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putLong(k, v);
        editor.apply();
    }


    public static void write(Context context, String k, String v) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(k, v);
        editor.apply();
    }


    public static boolean readBoolean(Context context, String k) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preference.getBoolean(k, false);
    }

    public static boolean readBoolean(Context context, String k, boolean defBool) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preference.getBoolean(k, defBool);
    }

    public static int readInt(Context context, String k) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preference.getInt(k, 0);
    }

    public static int readInt(Context context, String k, int defv) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preference.getInt(k, defv);
    }

    public static long readLong(Context context, String k) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preference.getLong(k, 0);
    }

    public static long readLong(Context context, String k, long defv) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preference.getLong(k, defv);
    }

    public static String readString(Context context, String k) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preference.getString(k, null);
    }

    public static String readString(Context context, String fileName, String k, String defV) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        return preference.getString(k, defV);
    }

    public static String readString(Context context, String k, String defV) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preference.getString(k, defV);
    }

    public static void remove(Context context, String k) {
        SharedPreferences preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.remove(k);
        editor.apply();
    }

    public static void clean(Context cxt) {
        SharedPreferences preference = cxt.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.clear();
        editor.apply();
    }

    /*******************************************************************************/

    public static void write(Context context, String fileName, String k, boolean v) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(k, v);
        editor.apply();
    }

    public static void write(Context context, String fileName, String k, int v) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(k, v);
        editor.apply();
    }

    public static void write(Context context, String fileName, String k, long v) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putLong(k, v);
        editor.apply();
    }


    public static void write(Context context, String fileName, String k, String v) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(k, v);
        editor.apply();
    }


    public static boolean readBoolean(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        return preference.getBoolean(k, false);
    }

    public static boolean readBoolean(Context context, String fileName, String k, boolean defBool) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        return preference.getBoolean(k, defBool);
    }

    public static int readInt(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        return preference.getInt(k, 0);
    }

    public static int readInt(Context context, String fileName, String k, int defv) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        return preference.getInt(k, defv);
    }

    public static long readLong(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        return preference.getLong(k, 0);
    }

    public static long readLong(Context context, String fileName, String k, long defv) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        return preference.getLong(k, defv);
    }

    public static String readStringFile(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        return preference.getString(k, null);
    }

    public static String readStringFile(Context context, String fileName, String k, String defV) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        return preference.getString(k, defV);
    }

    public static void remove(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.remove(k);
        editor.apply();
    }

    public static void clean(Context cxt, String fileName) {
        SharedPreferences preference = cxt.getSharedPreferences(fileName, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.clear();
        editor.apply();
    }
}
