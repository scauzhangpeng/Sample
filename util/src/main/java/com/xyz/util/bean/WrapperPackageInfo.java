package com.xyz.util.bean;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.text.format.Formatter;

/**
 * Created by ZP on 2017/12/5.
 */

public class WrapperPackageInfo {

    private PackageInfo mPackageInfo;

    private String appName;

    private boolean isSystemApp = false;

    private Drawable mDrawable;

    private long appSize;

    public WrapperPackageInfo(PackageInfo packageInfo, String appName, boolean isSystemApp, Drawable drawable, long appSize) {
        mPackageInfo = packageInfo;
        this.appName = appName;
        this.isSystemApp = isSystemApp;
        mDrawable = drawable;
        this.appSize = appSize;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }

    public PackageInfo getPackageInfo() {
        return mPackageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        mPackageInfo = packageInfo;
    }

    public boolean isSystemApp() {
        return isSystemApp;
    }

    public void setSystemApp(boolean systemApp) {
        isSystemApp = systemApp;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getAppSize() {
        return appSize;
    }

    public void setAppSize(long appSize) {
        this.appSize = appSize;
    }

    public String formatAppSize(Context context) {
        return Formatter.formatFileSize(context, this.appSize);
    }

    @Override
    public String toString() {
        return "WrapperPackageInfo{" +
                "mPackageInfo=" + mPackageInfo +
                ", appName='" + appName + '\'' +
                ", isSystemApp=" + isSystemApp +
                '}';
    }
}
