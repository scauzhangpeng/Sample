package com.xyz.util;

import android.os.Build;

/**
 * Created by ZP on 2017/9/14.
 * <p>
 * 设备相关工具类
 * </p>
 */

public class DeviceUtil {

    public enum ROM {
        SMARTISAN,
        MIUI,
        FLYME,
        EMUI,
        COLOROS
    }

    /**
     * 设备API版本
     *
     * @return 例如：25
     */
    public static int getSystemAPIVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * Android 版本
     *
     * @return 例如：7.1.2
     */
    public static String getSystemReleaseVersion() {
        return Build.VERSION.RELEASE;
    }


    public static boolean isEmulator() {
        return false;
    }


    /**
     * 获取系统UI版本
     *
     * @param rom {@link DeviceUtil.ROM}
     * @return 系统UI版本号
     */
    public static String getSysUIVersionCompat(ROM rom) {
        return DeviceFactory.getSysUIVersion(rom);
    }


}
