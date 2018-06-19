package com.octalsoftaware.buguroo.Utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ContextInformation {

    public static boolean getScreenLockStatus(Activity activity) {
        boolean isScreenLock = false;
        KeyguardManager myKM = (KeyguardManager) activity.getSystemService(Context.KEYGUARD_SERVICE);
        isScreenLock = myKM != null && myKM.inKeyguardRestrictedInputMode();
        return isScreenLock;
    }

    public static void getBatteryConsumptionPerApp() {

    }

    public static void getDataConsumptionPerApp(Activity activity) {

    }

    public static long getNumberOfInstalledApp(Activity activity) {
        int numberOfNonSystemApps = 0;
        List<ApplicationInfo> appList = activity.getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo info : appList) {
            if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                numberOfNonSystemApps++;
            }
        }
        return numberOfNonSystemApps;
    }

    public static String getOsInformation() {
        return Build.VERSION.RELEASE + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();
    }

    public String getDeviceType() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String string) {
        if (string == null || string.length() == 0) {
            return "";
        }
        char first = string.charAt(0);
        if (Character.isUpperCase(first)) {
            return string;
        } else {
            return Character.toUpperCase(first) + string.substring(1);
        }
    }

    public static String getDeviceLanguage() {
        return Locale.getDefault().getDisplayLanguage();
    }
}