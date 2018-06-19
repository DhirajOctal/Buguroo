package com.octalsoftaware.buguroo.Utils;

import android.annotation.SuppressLint;
import android.os.Build;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static final boolean isShowLog = false;

    /*This method is use to get current date and time according to given format for example (dd-MM-yyyy hh:mm:ss a)(13-06-2018 02:31:51 PM)*/
    public static String getDateandTimeStamp() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        return sdf.format(new Date());
    }
}