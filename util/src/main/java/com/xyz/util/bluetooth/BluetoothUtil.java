package com.xyz.util.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ZP on 2017/9/16.
 * <p>
 * 蓝牙相关工具类
 * </p>
 */

public class BluetoothUtil {

    /**
     * 是否存在蓝牙模块.
     *
     * @return {@code true} 存在 {@code false} 不存在
     */
    public static boolean isBluetoothExit() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null;
    }

    /**
     * 蓝牙是否启动.
     *
     * @return {@code true}  已经开启 {@code false} 不存在或者未开启
     */
    public static boolean isBluetoothEnable() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            if (defaultAdapter.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 开启蓝牙设备.
     * 本方法开启蓝牙，会出现界面让用户选择是否开启或者拒绝，调用本方法不会出现权限选择，返回值仅仅
     * 代表调用成功，后续蓝牙是否开启成功需要调用者在{@link Activity#onActivityResult(int, int, Intent)}
     * 中根据传入得requestCode进行处理。
     *
     * @param activity {@link Activity}
     * @return {@code true} 开启执行成功,{@code false} 开启执行失败
     */
    public static boolean enableBluetooth(Activity activity, int requestCode) {
        try {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(intent, requestCode);
        } catch (ActivityNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 开启蓝牙设备.
     * 本方法直接开启蓝牙，不会有任何提示，如果开启成功则返回{@code true}，如果开启失败则返回
     * {@code false}，部分会提示用户是否给予权限，如果用户禁止，也是返回{@code false}
     *
     * @return {@code true} 启动成功， {@code false} 启动失败，可能是权限未获取
     */
    public static boolean enableBluetooth() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.enable();
    }

    /**
     * 设置蓝牙设备可见时间.
     *
     * @param activity {@link Activity}
     * @param time     可见时间，单位/秒,如果小于0或者大于3600，则覆盖变成默认值120
     * @return {@code true} 设置成功，{@code false} 设置失败
     */
    public static boolean setVisibilityTime(Activity activity, int time, int requestCode) {
        try {
            Intent bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            bluetoothIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, time);
            activity.startActivityForResult(bluetoothIntent, requestCode);
            return true;
        } catch (ActivityNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 获取设备的蓝牙mac地址
     *
     * @param context {@link Context}
     * @return 设备蓝牙的mac地址，如果低于6.0，且蓝牙模块不存在，则返回默认的"02:00:00:00:00:00"
     */
    public static String getBluetoothMacAddress(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return android.provider.Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");
        } else {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null) {
                return defaultAdapter.getAddress();
            } else {
                return "02:00:00:00:00:00";
            }
        }
    }

    /**
     * 获取已配对的蓝牙设备.
     * 本方法需要开启蓝牙，如果设备没有蓝牙模块或者蓝牙未开启，则会返回空集合,不返回null
     *
     * @return 返回空集合或者已配对的集合
     */
    public static Set<BluetoothDevice> getBondedDevices() {
        Set<BluetoothDevice> result = null;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            result = defaultAdapter.getBondedDevices();
        }
        return result != null ? result : new HashSet<BluetoothDevice>();
    }


}
