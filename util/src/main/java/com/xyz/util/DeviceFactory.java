package com.xyz.util;

import android.os.Build;
import android.text.TextUtils;

import com.xyz.util.DeviceUtil.ROM;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ZP on 2017/12/1.
 * 设备类工厂
 */

public class DeviceFactory {

    public static String getSysUIVersion(ROM rom) {
        if (rom == ROM.MIUI) {
            return getMIUIVersion();
        }

        if (rom == ROM.SMARTISAN) {
            return getSmartisanVersion();
        }

        if (rom == ROM.FLYME) {
            return getFlyMeVersion();
        }

        if (rom == ROM.EMUI) {
            return getEMUIVersion();
        }

        if (rom == ROM.COLOROS) {
            return getColorOsVersion();
        }
        return null;
    }

    /**
     * 获取MIUI系统版本.
     * 通过属性值{@link DeviceConstant#VERSION_XIAOMI}获取，得到原始值去除"V"
     *
     * @return 小米系统MIUI版本号
     */
    private static String getMIUIVersion() {
        String version = null;
        if ("XiaoMi".equalsIgnoreCase(Build.MANUFACTURER)) {
            version = getUIVersion(DeviceConstant.VERSION_XIAOMI);
            if (!TextUtils.isEmpty(version) && version.toUpperCase().contains("V")) {
                return version.replace("V", "");
            }
        }

        return version;
    }

    /**
     * 获取锤子系统版本.
     * 通过属性值{@link DeviceConstant#VERSION_SMARTISAN}获取，得到原始值按照"-"分割取第一个
     *
     * @return 锤子系统smartisan版本号
     */
    private static String getSmartisanVersion() {
        String version = null;
        if ("Smartisan".equalsIgnoreCase(Build.MANUFACTURER)) {
            version = getUIVersion(DeviceConstant.VERSION_SMARTISAN);
            if (!TextUtils.isEmpty(version)) {
                String[] split = version.split("-");
                version = split[0];
                return version;
            }
        }
        return version;
    }

    /**
     * 获取魅族系统版本.
     *
     * @return 魅族系统Flyme版本号
     */
    private static String getFlyMeVersion() {
        if ("meizu".equalsIgnoreCase(Build.MANUFACTURER)) {
            return Build.DISPLAY;
        }
        return null;
    }

    private static String getEMUIVersion() {
        return null;
    }

    private static String getColorOsVersion() {
        return null;
    }


    /**
     * 获取ROM系统版本
     * 利用反射获取系统UI版本号，例如获取MIUI版本号
     *
     * @param uiSystem 系统标识
     * @return 版本号
     */
    private static String getUIVersion(String uiSystem) {
        Class<?> clazz;
        String version = null;
        try {
            clazz = Class.forName("android.os.Build");
            Method method = clazz.getDeclaredMethod("getString", String.class);
            method.setAccessible(true);
            version = (String) method.invoke(Build.class, uiSystem);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return version;
    }
}
