package com.xyz.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by ZP on 2017/9/16.
 * <p>
 *     跳转意图工具类
 * </p>
 */

public final class IntentUtil {

    /**
     * 跳转至定位设置界面
     * @param context {@link Context}
     */
    public static boolean toGpsSetting(Context context) {
        return toIntent(context, Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }

    /**
     * 跳转至NFC设置界面
     * @param context {@link Context}
     */
    public static boolean toNFCSetting(Context context) {
        return toIntent(context, Settings.ACTION_NFC_SETTINGS);
    }

    /**
     * 跳转至Android Beam设置界面
     * @param context {@link Context}
     */
    public static boolean toAndroidBeamSetting(Context context) {
        return toIntent(context, Settings.ACTION_NFCSHARING_SETTINGS);
    }

    /**
     * 跳转至Android Beam设置界面
     * @param context {@link Context}
     */
    public static boolean toBluetoothSetting(Context context) {
        return toIntent(context, Settings.ACTION_BLUETOOTH_SETTINGS);
    }

    /**
     * 跳转至拨号界面
     * @param context {@link Context}
     * @param phoneNumber 需要拨打的电话号码
     */
    public static void toCallWaitConfirm(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    /**
     * 直接拨打电话
     * <p> Require {@link Manifest.permission#CALL_PHONE}
     * @param context {@link Context}
     * @param phoneNumber 需要拨打的电话号码
     */
    @RequiresPermission(Manifest.permission.CALL_PHONE)
    public static void toCallImmediately(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    /**
     * 跳转至wifi设置界面
     * @param context {@link Context}
     */
    public static boolean toWIFIsSetting(Context context) {
        return toIntent(context, Settings.ACTION_WIFI_SETTINGS);
    }

    public static boolean toQuickLaunchShortCut(Context context) {
        return toIntent(context, Settings.ACTION_QUICK_LAUNCH_SETTINGS);
    }

    /**
     * 跳转至飞行模式设置界面
     * @param context {@link Context}
     */
    public static boolean toAirPlaneModeSetting(Context context) {
        return toIntent(context, Settings.ACTION_AIRPLANE_MODE_SETTINGS);
    }

    /**
     * 跳转至电池电量设置界面
     * @param context {@link Context}
     */
    public static boolean toBatterySetting(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            return toIntent(context, Settings.ACTION_BATTERY_SAVER_SETTINGS);
        } else {
            return false;
        }
    }

    /**
     * 跳转至已安装程序设置界面
     * @param context {@link Context}
     */
    public static boolean toAllApplicationSetting(Context context) {
        return toIntent(context, Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
    }


    /**
     * 跳转至默认程序设置界面
     * @param context {@link Context}
     */
    public static boolean toDefaultApplicationSetting(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return toIntent(context, Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS);
        } else {
            return toIntent(context, Settings.ACTION_SETTINGS);
        }
    }

    /**
     * 跳转至辅助功能界面
     *
     * @param context {@link Context}
     */
    public static boolean toAccessibilitySetting(Context context) {
        return toIntent(context, Settings.ACTION_ACCESSIBILITY_SETTINGS);
    }

    /**
     * 跳转至给定包名的应用配置界面
     * @param context {@link Context}
     * @param packageName 包名
     * @return 是否跳转成功 {@code true } 成功<br>{@code false}失败
     */
    public static boolean toApplicationSetting(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        try {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + packageName));
            context.startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 打开相机进行拍照.
     * @param activity {@link Activity}
     * @param requestCode 请求码用于{@link Activity#onActivityResult(int, int, Intent)}
     * @return {@code true} 打开成功<br> {@code false} 打开失败
     */
    public static boolean toCamera(Activity activity, int requestCode) {
        return toCamera(activity, requestCode, null, null);
    }

    /**
     * 打开相机进行拍照.
     * 传入自定义的路径以及文件名进行保存，这样图片不会被压缩
     * @param activity {@link Activity}
     * @param requestCode 请求码用于{@link Activity#onActivityResult(int, int, Intent)}
     * @param output 图片输出路径
     * @param fileName 图片名称
     * @return {@code true} 打开成功<br> {@code false} 打开失败
     */
    public static boolean toCamera(Activity activity, int requestCode, String output, String fileName){
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
            if (!TextUtils.isEmpty(output) && !TextUtils.isEmpty(fileName)) {
                Uri photoUri = Uri.fromFile(new File(output + File.separator + fileName)); // 传递路径
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);// 更改系统默认存储路径
            }
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean toHiddenConfig(Context context, String packageName, String label) {
        return XiaomiDeviceUtil.toConfigApp(context, packageName, label);
    }

    public static boolean toHiddenList(Context context) {
        return XiaomiDeviceUtil.toHiddenAppList(context);
    }

    /**
     * 判断飞行模式是否开启
     * @param context {@link Context}
     * @return {@code true}开启了飞行模式<br>{@code false}未开启飞行模式
     */
    public static boolean isAirPlaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return !"0".equals(Settings.Global.getString(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON));
        } else {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) == 1;
        }
    }


    /**
     * 跳转方法
     * @param context {@link Context}
     * @param action 意图
     * @return 是否跳转成功 {@code true } 成功<br>{@code false}失败
     */
    private static boolean toIntent(Context context, String action) {
        try {
            Intent intent = new Intent(action);
            context.startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
