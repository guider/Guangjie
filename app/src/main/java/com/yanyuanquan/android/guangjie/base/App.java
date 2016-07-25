package com.yanyuanquan.android.guangjie.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.yanyuanquan.android.guangjie.base.widget.SP;


/**
 * Created by guider on 16/6/29.
 * Email guider@yeah.net
 * github https://github.com/guider
 */
public class App extends Application {

    private static Context app;
    private static Handler mHandler;

    public static final boolean isDebug = com.yanyuanquan.android.automvp.BuildConfig.DEBUG;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        mHandler = new Handler(getMainLooper());
        init();
    }

    private void init() {
        SP.init(this);

    }

    public static Context getApp() {
        return app;
    }

    public static Handler getHandler() {
        return mHandler;
    }


    private static Activity currentActivity;

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity act) {
        currentActivity = act;
    }
}
