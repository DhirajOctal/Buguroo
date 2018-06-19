package com.octalsoftaware.buguroo.Utils;

import android.os.Environment;
import android.os.StatFs;

import java.text.DecimalFormat;

public class DiskUtils {

    /*This method is use to check external storage is available or not.*/
    public static boolean isExternalStorageIsAvailabe() {
        boolean isSDCardAvailabe = false;
        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();
        if (isSDSupportedDevice && isSDPresent) {
            isSDCardAvailabe = true;
        } else {
            isSDCardAvailabe = false;
        }
        return isSDCardAvailabe;
    }

    /*totalMemory method is use to get total memory of device internal memory or external memory. pass true false in parameter to check memory of internal memory
     * or external memory (true for check total size of external memory and false for check total size of internal memoyr).*/
    public static String totalMemory(boolean isExternal) {
        StatFs statFs;
        if (isExternal)
            statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        else
            statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        return bytesToHuman((long) (statFs.getBlockCount() * statFs.getBlockSize()));
    }

    /*freeMemory method is use to check free memory of device internal memory or external memory. pass true parameter if you want to
    check free space of external storage or false to check free space of internal storage*/
    public static String freeMemory(boolean isExternal) {
        StatFs statFs;
        if (isExternal)
            statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        else
            statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        return bytesToHuman((long) (statFs.getAvailableBlocks() * statFs.getBlockSize()));
    }

    /*busyMemory method is use to check busy memory of device internal memory or external memory. pass true parameter if you want to
        check busy memory of external storage or pass false to check busy memory of internal storage*/
    public static String busyMemory(boolean isExternal) {
        StatFs statFs;
        if (isExternal)
            statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        else
            statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        long total = (statFs.getBlockCount() * statFs.getBlockSize());
        long free = (statFs.getAvailableBlocks() * statFs.getBlockSize());
        return bytesToHuman(total - free);
    }

    //   This method is use to converting bytes to human readable format.
    public static String bytesToHuman(long size) {
        long Kb = 1 * 1024;
        long Mb = Kb * 1024;
        long Gb = Mb * 1024;
        long Tb = Gb * 1024;
        long Pb = Tb * 1024;
        long Eb = Pb * 1024;
        if (size < Kb) return floatForm(size) + " byte";
        if (size >= Kb && size < Mb) return floatForm((double) size / Kb) + " Kb";
        if (size >= Mb && size < Gb) return floatForm((double) size / Mb) + " Mb";
        if (size >= Gb && size < Tb) return floatForm((double) size / Gb) + " Gb";
        if (size >= Tb && size < Pb) return floatForm((double) size / Tb) + " Tb";
        if (size >= Pb && size < Eb) return floatForm((double) size / Pb) + " Pb";
        if (size >= Eb) return floatForm((double) size / Eb) + " Eb";
        return "???";
    }

    public static String floatForm(double d) {
        return new DecimalFormat("#.##").format(d);
    }
}