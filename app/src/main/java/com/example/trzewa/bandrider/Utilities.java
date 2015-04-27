package com.example.trzewa.bandrider;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Admin on 2015-04-27.
 */
public class Utilities {

    public static boolean getConnectivityStatus(Context context) {
        boolean stan = false;
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if ((netInfo != null) && netInfo.isConnected() && netInfo.isAvailable()) {
            stan = true;
        }
        return stan;
    }
}
