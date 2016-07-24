package com.android.guider.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by apple on 16/1/6.
 */
public class NetUtil {


    /**
     * 无网络连接, 值为{@value}
     */
    public static final int NETWORK_NONE = -1;
    /**
     * 未知的无线网络连接, 值为{@value}
     */
    public static final int NETWORK_UNKOWN = 0;
    /**
     * wifi连接, 值为{@value}
     */
    public static final int NETWORK_WIFI = 1;
    /**
     * 2G网络连接, 值为{@value}
     */
    public static final int NETWORK_2G = 2;
    /**
     * 3G网络连接, 值为{@value}
     */
    public static final int NETWORK_3G = 3;

    /**
     * 中国移动 运营商网络, 值为{@value}
     */
    public static final int NETWORK_OPERATOR_MOBILE = 861;
    /**
     * 中国联通 运营商网络, 值为{@value}
     */
    public static final int NETWORK_OPERATOR_UNICOM = 862;
    /**
     * 中国电信 运营商网络, 值为{@value}
     */
    public static final int NETWORK_OPERATOR_TELECOM = 863;

    /**
     * 判断当前网络类型。<br/>
     * 需要注意的是，在数据连接情况下，如果是2G网络，则返回 {@value #NETWORK_2G}；如果是3G网络，则返回
     * {@value #NETWORK_3G}；如果是未知的数据连接，则返回{@value #NETWORK_UNKOWN}
     *
     * @param context
     * @return 无网络连接 {@value #NETWORK_NONE} | wifi方式连接 {@value #NETWORK_WIFI} |
     * 2G网络手机数据连接 {@value #NETWORK_2G} | 3G网络手机数据连接 {@value #NETWORK_3G}
     * | 未知手机数据连接 {@value #NETWORK_UNKOWN}
     * @see #NETWORK_NONE
     * @see #NETWORK_WIFI
     * @see #NETWORK_2G
     * @see #NETWORK_3G
     * @see #NETWORK_UNKOWN
     */
    public static int getNetworkType(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            return NETWORK_NONE;
        }

        // 判断网络连接类型，2G 3G或wifi
        int netType = info.getType();
        if (netType == ConnectivityManager.TYPE_WIFI) {
            return NETWORK_WIFI;
        } else {
            int subType = info.getSubtype();
            switch (subType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return NETWORK_2G; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return NETWORK_2G; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return NETWORK_3G; // ~ 400-7000 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return NETWORK_2G; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return NETWORK_3G; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return NETWORK_3G; // ~ 600-1400 kbps
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return NETWORK_2G; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return NETWORK_3G; // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return NETWORK_3G; // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return NETWORK_3G; // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return NETWORK_2G; // ~25 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    return NETWORK_3G; // ~ 5 Mbps
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return NETWORK_3G; // ~ 10+ Mbps
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                    return NETWORK_3G; // ~ 1-2 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return NETWORK_3G; // ~ 10-20 Mbps

                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    return NETWORK_UNKOWN;// Unknown
            }

            return NETWORK_UNKOWN;
        }
    }

    /**
     * 判断当前网络是否可用
     *
     * @param context
     * @return 当前网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            return false;
        }

        return true;
    }

    /**
     * 获取当前的ip, 如果获取失败, 则返回null
     *
     * @return 当前的ip, 如果获取失败, 则返回null
     */
    public static String getHostIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> ipAddr = intf.getInetAddresses(); ipAddr
                        .hasMoreElements(); ) {
                    InetAddress inetAddress = ipAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {

        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 读取运营商类型 MCC+MNC(mobile country code + mobile network code), CDMA可能会不好使.
     * 参考：http://www.2cto.com/kf/201212/180259.html
     */
    public static int getNetworkOperatorType(Context context) {
        TelephonyManager telManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String operator = telManager.getNetworkOperator();

        // 飞行模式下 获取不到operator
        if (operator == null || operator.length() < 3) {
            return NETWORK_UNKOWN;
        }

        // operator 由mcc + mnc组成 中国的mcc为460
        String mcc = operator.substring(0, 3);
        if (!mcc.equals("460")) {// 非中国运营商
            return NETWORK_UNKOWN;
        }

        // 这里取得mnc来判断是国内的哪个运营商
        String mnc = operator.substring(3);
        int mncIntVar = 0;
        try {
            mncIntVar = Integer.parseInt(mnc);
        } catch (NumberFormatException e) {
        }

        switch (mncIntVar) {
            case 0:
            case 2:
            case 7:
                return NETWORK_OPERATOR_MOBILE;
            case 1:
            case 6:
                return NETWORK_OPERATOR_UNICOM;
            case 3:
            case 5:
                return NETWORK_OPERATOR_TELECOM;
            default:
                break;
        }

        return NETWORK_UNKOWN;
    }

    /**
     * 调用系统浏览器打开某个网址
     *
     * @param context
     * @param url
     */
    public static void openUrl(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));// 网页
        context.startActivity(intent); // 启动浏览器
    }
}
