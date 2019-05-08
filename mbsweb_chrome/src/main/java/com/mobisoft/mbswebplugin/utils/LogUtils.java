package com.mobisoft.mbswebplugin.utils;

import android.util.Log;

/**
 * Description:
 * Copyright  : Copyright (c) 2019
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2019/04/23
 * Time       : 13:37
 * Project    ：weblibrary.
 */
public class LogUtils {
    private static boolean isLoggable = false;

    public static void init(boolean isLoggable) {
        LogUtils.isLoggable = isLoggable;
    }

    public static void v(String tag, String msg) {
        if (isLoggable) Log.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isLoggable) Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (isLoggable) Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (isLoggable) Log.w(tag, msg);
    }

    public static void wtf(String tag, String msg) {
        if (isLoggable) Log.wtf(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isLoggable) Log.e(tag, msg);
    }
}
