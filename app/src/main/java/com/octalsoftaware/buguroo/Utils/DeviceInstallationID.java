package com.octalsoftaware.buguroo.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;

public class DeviceInstallationID {
    private static String installation_id = "";

    /*This method is used to get android Hardware device ID.*/
    @SuppressLint("HardwareIds")
    public static String getInstallationID(Activity activity) {
        installation_id = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
        return installation_id;
    }

    /*This method is used to get android Hardware device ID.*/
    @SuppressLint("HardwareIds")
    public static String getDeviceID(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("DeviceIDWithDateTimeSP", Context.MODE_PRIVATE);
        String deviceID = sharedPreferences.getString("DeviceID", "");
        if (TextUtils.isEmpty(deviceID)) {
            String ID = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("DeviceID", "<b>Device ID : </b>" + ID);
            editor.putString("DeviceIDDateTime", "<b>Date & Time : </b>" + Utils.getDateandTimeStamp());
            editor.apply();
        }
        return sharedPreferences.getString("DeviceID", "");
    }
}