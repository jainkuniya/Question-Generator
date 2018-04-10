package com.example.user.qapp.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.util.UUID;

/**
 * Created by user on 10/4/18.
 */

public class FileNameGenerator {

    Context context;
    String fileName;
    String IMEIno;
    public FileNameGenerator(Context context) {
        this.context=context;
    }



    public String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return telephonyManager.getDeviceId();
        }
        else
            return null;
    }
    public String generateString() {
        String uuid = UUID.randomUUID().toString().substring(0,5);
        return uuid;
    }
    public String generate(){
        IMEIno=getDeviceId(context);
        fileName=IMEIno+generateString();
        return fileName;
    }
}
