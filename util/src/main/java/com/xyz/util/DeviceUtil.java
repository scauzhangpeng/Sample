package com.xyz.util;

import android.os.Build;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    /**
     * 获取CPU核心数，来源Facebook
     *
     * @return CPU核心数或者-1
     */
    public static int getNumberOfCpuCores() {
        return DeviceInfo.getNumberOfCPUCores();
    }

    /**
     * 获取基带版本
     *
     * @return 基带版本或者空字符串
     */
    public static String getBasebandVersion() {
        String Version = "";
        try {
            Class cl = Class.forName("android.os.SystemProperties");
            Object invoker = cl.newInstance();
            Method m = cl.getMethod("get", new Class[]{String.class, String.class});
            Object result = m.invoke(invoker, new Object[]{"gsm.version.baseband", "no message"});
            Version = (String) result;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Version;
    }


}
