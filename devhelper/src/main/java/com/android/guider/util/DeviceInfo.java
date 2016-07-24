package com.android.guider.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.List;
import java.util.UUID;

/**
 * @Created by apple on 16/1/15.
 * @description:
 * @projectName:YYQ
 */

public class DeviceInfo {

    /**
     * 获取当前应用程序的版本号(获取AndroidManifest文件中android:versionName属性值)
     *
     * @return 当前应用程序的版本号
     */
    public static String getAppVersionName(Context context) {
        try {
            PackageInfo info = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(),
                            PackageManager.GET_CONFIGURATIONS);
            // 应用程序版本名称, 例如2.1.1
            String appVersionName = info.versionName;
            return appVersionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "1.0";
    }

    /**
     * 获取当前应用程序的版本号(获取AndroidManifest文件中android:versionCode属性值)
     *
     * @return 当前应用程序的版本号
     */
    public static int getAppVersionCode(Context context) {
        try {
            PackageInfo info = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(),
                            PackageManager.GET_CONFIGURATIONS);

            // 应用程序的版本号
            int appVersionCode = info.versionCode;

            return appVersionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 1;
    }

    /**
     * 获取手机的型号信息, 例如 Xiaomi_2S
     *
     * @return 手机的型号信息
     */
    public static String getModelInfo() {
        return Build.MANUFACTURER + "_" + Build.MODEL;
    }

    /**
     * 获取手机操作系统的版本号, 例如4.0.3
     *
     * @return 手机操作系统的版本号
     */
    public static String getOsVersionName() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取签名信息, 并且将签名信息进行MD5加密
     *
     * @param context
     * @return
     */
    public static String getSignatureInfo(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            return(info.signatures[0].toCharsString());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取手机的IMEI
     *
     * @return 手机的IMEI
     */
    public static String getIMEI(Context context) {
        String imei = "";
        try {
            TelephonyManager manager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (manager != null)
                imei = manager.getDeviceId();
        } catch (Exception e) {

        }
        return imei;
    }

    /**
     * 获取手机的CUID，用于统计
     *
     * @param context
     * @return 手机的CUID
     */
    public static String getCuid(Context context) {
        String imei = getIMEI(context);
        if (TextUtils.isEmpty(imei)) {
            imei = "0";
        }

        String deviceId = getDeviceID(context, imei);

        StringBuilder builder = new StringBuilder(imei);

        return deviceId + "|" + builder.reverse().toString();
    }

    private static String getDeviceID(Context context, String imei) {
        String androidId = getAndroidId(context);
        String uuid = UUID.randomUUID().toString();

        StringBuilder sb = new StringBuilder();
        sb.append(imei);
        sb.append(androidId);
        sb.append(uuid);

        return sb.toString();
    }

    private static String getAndroidId(Context context) {
        String androidId = "";
        androidId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        if (androidId == null)
            androidId = "";
        return androidId;
    }

    public static String getUmengDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = Settings.Secure.getString(
                        context.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断当前应用程序是否是在前台运行
     *
     * @return true-在前台运行; false-otherwise
     */
    public static boolean isAppShowing(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();

        if (appProcesses == null)
            return false;

        // 可以根据importance的不同来判断前台或后台.
        // RunningAppProcessInfo里面的常量importance就是上面所说的前台后台,
        // 其实importance是表示这个app进程的重要性, 因为系统回收时候,
        // 会根据importance来回收进程的. 具体可以去看文档...
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : appProcesses) {
            if (runningAppProcessInfo.processName.equals(context
                    .getPackageName())) {
                int status = runningAppProcessInfo.importance;
                if (status == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE
                        || status == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {

                    return true;
                }
            }
        }

        return false;
    }
}
