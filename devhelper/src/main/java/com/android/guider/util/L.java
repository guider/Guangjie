package com.android.guider.util;

import android.util.Log;

/**
 * Created by apple on 16/5/23.
 */

public class L {
    private static String TAG = "zjw";
    private static boolean DEBUG = true;

    private L() {
    }

    public static void e(String msg) {
        if (DEBUG) {
            Log.e(TAG, msg + "      <<---- ERROR");
        }

    }

    public static void i(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg + "      <<----INFO ");
        }

    }
    public static void w(String msg) {
        if (DEBUG) {
            Log.w(TAG, msg + "      <<---- WRAM");
        }

    }
    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg + "      <<---- DEBUG");
        }

    }

}
