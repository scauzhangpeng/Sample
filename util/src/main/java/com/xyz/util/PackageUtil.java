package com.xyz.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.xyz.util.bean.WrapperPackageInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZP on 2017/10/12.
 * 包名相关工具类
 */

public class PackageUtil {


    /**
     * Uses package manager to find the package name of the device launcher. Usually this package
     * is "com.android.launcher" but can be different at times. This is a generic solution which
     * works on all platforms.`
     *
     * @param context {@link Context}
     */
    public static String getLauncherPackageName(Context context) {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = context.getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }

    /**
     * 获取{@code <Application>}标签下的meta-data值.
     *
     * @param context        {@link Context}
     * @param metaName{@code <meta-data>}的字段名
     * @return {@code <meta-data>}的字段值
     */
    public static String getApplicationMeta(Context context, String metaName) {
        try {
            ApplicationInfo info = context.getPackageManager().
                    getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return info.metaData.getString(metaName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取应用版本号.
     *
     * @param context {@link Context}
     * @return build.gradle 配置的versionCode
     */
    public static int getAppVersionCode(Context context) {
        if (context != null) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                    if (packageInfo != null) {
                        return packageInfo.versionCode;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    /**
     * 获取应用版本名.
     *
     * @param context {@link Context}
     * @return build.gradle 配置的versionName
     */
    public static String getAppVersionName(Context context) {
        if (context != null) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                    if (packageInfo != null) {
                        return packageInfo.versionName;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 根据包名启动APP.
     *
     * @param context     {@link Context}
     * @param packageName 要启动的APP的包名
     * @return {@code true} 启动成功，{@code false} 启动失败
     */
    public static boolean launchApp(Context context, String packageName) {
        Intent launchIntentForPackage = getLaunchAppIntent(context, packageName);
        if (launchIntentForPackage == null) {
            return false;
        }
        try {
            context.startActivity(launchIntentForPackage);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据包名启动APP.
     *
     * @param activity    {@link Activity}
     * @param packageName 要启动的APP的包名
     * @return {@code true} 启动成功，{@code false} 启动失败
     */
    public static boolean launchApp(Activity activity, String packageName, int requestCode) {
        Intent launchIntentForPackage = getLaunchAppIntent(activity, packageName);
        if (launchIntentForPackage == null) {
            return false;
        }
        try {
            activity.startActivityForResult(launchIntentForPackage, requestCode);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private static Intent getLaunchAppIntent(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        return context.getPackageManager().getLaunchIntentForPackage(packageName);
    }

    /**
     * 获取应用的名称.
     *
     * @param context {@link Context}
     * @return 应用名称
     */
    public static String getAppName(Context context) {
        if (context != null) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                    if (packageInfo != null) {
                        return packageInfo.applicationInfo.loadLabel(packageManager).toString();
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取已安装的应用
     *
     * @param context {@link Context}
     * @return {@link WrapperPackageInfo} 列表
     */
    public static List<WrapperPackageInfo> getInstallApp(Context context) {
        List<WrapperPackageInfo> result = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
        for (PackageInfo installedPackage : installedPackages) {
            String appName = installedPackage.applicationInfo.loadLabel(pm).toString();
            Drawable drawable = installedPackage.applicationInfo.loadIcon(pm);
            //应用程序大小
            String sourceDir = installedPackage.applicationInfo.sourceDir;
            File file = new File(sourceDir);
            long length = 0L;
            if (file.exists()) {
                length = file.length();
            }
            if ((installedPackage.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                result.add(new WrapperPackageInfo(installedPackage, appName, false, drawable, length));
            } else {
                result.add(new WrapperPackageInfo(installedPackage, appName, true, drawable, length));
            }
        }
        return result;
    }
}
