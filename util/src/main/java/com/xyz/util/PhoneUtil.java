package com.xyz.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZP on 2017/9/16.
 */

public class PhoneUtil {

    /**
     * 获取IMEI码
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.READ_PHONE_STATE"/>}</p>
     *
     * @return IMEI码 不适用双卡手机
     */
    @SuppressLint("HardwareIds")
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getDeviceId() : null;
    }

    /**
     * 获取IMSI码
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.READ_PHONE_STATE"/>}</p>
     *
     * @return IMSI码
     */
    @SuppressLint("HardwareIds")
    public static String getIMSI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getSubscriberId() : null;
    }

    /**
     * 隐藏中间四位手机号.
     *
     * @param phoneNumber 手机号码
     * @return 隐藏手机号码中间四位后返回，例如：135****9866
     */
    public static String hiddenMiddlePhoneNum(String phoneNumber) throws IOException {

        if (!isPhoneNumber(phoneNumber)) {
            throw new IOException("phoneNumber invalid");
        }

        return phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 简单判断手机号码是否符合.
     *
     * @param phoneNumber 手机号码
     * @return {@code true} 符合 <br> {@code false} 不符合
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().length() != 11) {
            return false;
        }
        Pattern p = Pattern.compile("^[1][0-9]{10}$");
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

}
