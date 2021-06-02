package com.xyz.util;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import androidx.annotation.RequiresPermission;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by ZP on 2017/9/16.
 * <p>
 * 网络相关工具类
 * </p>
 */

public class NetWorkUtil {

    /**
     * 判断WIFI是否打开.
     * 需要权限{@code android.permission.ACCESS_WIFI_STATE}
     *
     * @param context {@link Context}
     * @return WIFI是否打开 {@code true} 打开，{@code false} 未打开
     */
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public static boolean isWifiOn(Context context) {
        WifiManager wifiManager =
                (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    /**
     * 打开/关闭WIFI.
     * 需要权限{@code android.permission.CHANGE_WIFI_STATE}
     *
     * @param context {@link Context}
     * @param enable  {@code true} 打开，{@code false} 关闭
     * @return {@code true} 操作成功或者wifi状态和请求的状态一致<br>
     * {@code false} 操作失败，一般出现在用户禁止权限
     */
    @RequiresPermission(Manifest.permission.CHANGE_WIFI_STATE)
    public static boolean setWifiEnable(Context context, boolean enable) {
        WifiManager wifiManager =
                (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiManager.setWifiEnabled(enable);
    }

    /**
     * 获取已连接的WiFi名.
     *
     * @param context {@link Context}
     * @return 已连接的WiFi名或者“<unknown ssid>”
     */
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public static String getWifiName(Context context) {
        WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wm.getConnectionInfo().getSSID();
    }

    /**
     * 关闭连接WiFi.
     * 本方法是关闭wifi连接，但不会关闭WiFi开关,WiFi连接是否关闭是异步的，这里返回只是操作结果。
     *
     * @param context {@link Context}
     * @return {@code true} 操作成功
     */
    public static boolean wifiDisConnect(Context context) {
        WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int networkId = wm.getConnectionInfo().getNetworkId();
        return wm.disableNetwork(networkId);
    }


    /**
     * 连接指定WiFi.
     * 返回的结果是指操作结果，是否连接成功是异步结果，需要自行去判断。
     * 如果wifi已经连接过并且保存，则会连接。
     *
     * @param context  {@link Context}
     * @param ssid     WiFi名称
     * @param password WiFi密码
     * @return {@code true} 操作成功
     */
    public static boolean wifiConnect(Context context, String ssid, String password) {
        if (!isWifiOn(context)) {
            return false;
        }
        WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        List<WifiConfiguration> configuredNetworks = wm.getConfiguredNetworks();
        for (WifiConfiguration configuredNetwork : configuredNetworks) {
            if (configuredNetwork.SSID.equals("\"" + ssid + "\"")) {
                return wm.enableNetwork(configuredNetwork.networkId, true);
            }
        }

        WifiConfiguration config = new WifiConfiguration();
        config.SSID = "\"" + ssid + "\"";
        config.preSharedKey = "\"" + password + "\"";
        config.hiddenSSID = true;
        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        config.status = WifiConfiguration.Status.ENABLED;
        int networkId = wm.addNetwork(config);
        boolean result = wm.enableNetwork(networkId, true);
        if (!result) {
            result = wm.reconnect();
        }
        return result;
    }

    /**
     * 判断WiFi是否连接.
     *
     * @param context {@link Context}
     * @return {@code true} 已连接，{@code false} 未连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 判断移动数据是否打开.
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isGPRSOn(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            Method getMobileDataEnabledMethod = tm.getClass().getDeclaredMethod("getDataEnabled");
            if (null != getMobileDataEnabledMethod) {
                return (boolean) getMobileDataEnabledMethod.invoke(tm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 打开或关闭移动数据.
     * 需系统应用 需添加权限{@code  android.permission.MODIFY_PHONE_STATE}
     *
     * @param enabled {@code true}: 打开<br>
     *                {@code false}: 关闭
     */
    public static boolean setGRPSEnabled(Context context, final boolean enabled) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            Method setMobileDataEnabledMethod = tm.getClass().getDeclaredMethod("setDataEnabled", boolean.class);
            if (null != setMobileDataEnabledMethod) {
                setMobileDataEnabledMethod.invoke(tm, enabled);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断网络是否是4G
     * 需添加权限 {@code android.permission.ACCESS_NETWORK_STATE}，如果是WiFi已连接，则返回false
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean is4G(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
    }

    /**
     * 判断网络是否可用.
     * 如果是wifi，需要连接上
     *
     * @param context {@link Context}
     * @return {@code true} 可用， {@code false} 不可用
     */
    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取无线mac地址.
     * 如果获取不到则返回默认值，如果WiFi没有打开也是返回默认值
     *
     * @param context {@link Context}
     * @return 默认值 {@link MacUtil#DEFAULT_MAC_ADDRESS}或者无线mac地址
     */
    public static String getMacAddressCompat(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return MacUtil.getMacNougat();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return MacUtil.getMacMarshmallow();
        } else {
            return MacUtil.getMacBeforeMarshmallow(context);
        }
    }
}
