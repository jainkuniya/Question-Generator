package com.example.user.qapp.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by vishwesh on 11/04/18.
 */

public class DeviceInfo {

    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return telephonyManager.getDeviceId();
        } else {
            return null;
        }
    }

    public static String getIEMINO(Context context) {
        String IEMI = getDeviceId(context);
        if (IEMI == null) {
            Log.e("DeviceInfo", "Can't find IEMI no.");
            return "unknown_device";
        } else {
            return IEMI;
        }
    }
}
