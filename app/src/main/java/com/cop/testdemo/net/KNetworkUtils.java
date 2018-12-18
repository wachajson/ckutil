package com.cop.testdemo.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class KNetworkUtils {
    public static final int NETWORK_TYPE_TD_SCDMA = 17;
    public static final int NETWORK_TYPE_UNKNOWN = -1;
    public static final int NETWORK_TYPE_WIFI = 0;
    public static final int NETWORK_TYPE_3G = 1;
    public static final int NETWORK_TYPE_2G = 2;
    public static final int NETWORK_TYPE_4G = 3;
    public static final int NETWORK_TYPE_GPRS = 4;
    public static final int CHINA_MOBILE_CMCC = 1;
    public static final int CHINA_UNICOM = 2;
    public static final int CHINA_TELECOM = 3;
    public static final int UNKONW = 4;

    public KNetworkUtils() {
    }



    public static boolean isConnection4G(Context context) {
        boolean var0 = false;

        try {
            Context var1 =context;
            TelephonyManager var2 = (TelephonyManager)var1.getSystemService("phone");
            switch(var2.getNetworkType()) {
                case 13:
                    var0 = true;
            }
        } catch (Exception var3) {

        }

        return var0;
    }






    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }




}

