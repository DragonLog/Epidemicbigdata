package com.example.epidemicbigdata.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class WebUtil {

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null) {
            return true;
        }else {
            return false;
        }
    }


}
