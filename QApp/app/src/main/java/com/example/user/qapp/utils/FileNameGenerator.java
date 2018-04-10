package com.example.user.qapp.utils;

import android.content.Context;

import java.util.UUID;

public class FileNameGenerator {

    public static String generateFromIEMIAndTimeStamp(Context context) {
        return DeviceInfo.getIEMINO(context) + "_" + System.currentTimeMillis();
    }
}
