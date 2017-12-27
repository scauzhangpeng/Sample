package com.xyz.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by ZP on 2017/9/14.
 * <p>
 * 设备屏幕相关属性
 * </p>
 */

public class ScreenUtil {

    /**
     * 获取手机可用宽度.
     *
     * @param context {@link Context}
     * @return 手机可用宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取手机可用高度.
     *
     * @param context {@link Context}
     * @return 手机可用高度，包括状态栏，不包括导航栏
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕真实高度.
     *
     * @param activity {@link Activity}
     * @return 屏幕真实高度，单位px
     */
    public static int getScreenRealHeight(Activity activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            return activity.getWindowManager().getDefaultDisplay().getMode().getPhysicalHeight();
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Point point = new Point();
            activity.getWindowManager().getDefaultDisplay().getRealSize(point);
            return point.y;
        }
        return activity.getWindowManager().getDefaultDisplay().getHeight();
    }

    /**
     * 获取屏幕真实宽度.
     *
     * @param activity {@link Activity}
     * @return 屏幕真实宽度, 单位px
     */
    public static int getScreenRealWidth(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Point point = new Point();
            activity.getWindowManager().getDefaultDisplay().getRealSize(point);
            return point.x;
        }
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }

    /**
     * 获取手机状态栏高度.
     *
     * @param context {@link Context}
     * @return API 23之后 24； API 19之后25； API 19之前0；单位dp，但本方法返回px
     */
    public static int getStatusBarHeight_px(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取手机状态栏高度.
     *
     * @param context {@link Context}
     * @return API 23之后 24； API 19之后25； API 19之前0；单位dp
     */
    public static int getStatusBarHeight_dp(Context context) {
        return px2dp(context, getStatusBarHeight_px(context));
    }

    /**
     * sp转px
     *
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(Context context, final float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(Context context, final float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(Context context, final float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 获取导航栏高度
     *
     * @param context 上下文
     * @return 状态栏高度，单位px
     * @deprecated Use {@link #getNavigationBarHeight(Activity)} instead.
     */
    @Deprecated()
    public static int getNavigationBarHeight(Context context) {
        if (checkDeviceHasNavigationBar(context)) {
            int resourceId = 0;
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    /**
     * 判断是否存在导航栏
     *
     * @param context 上下文
     * @return true存在，false不存在
     * @deprecated Use {@link #checkDeviceHasNavigationBar(Activity)} instead
     */
    @Deprecated
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasNavigationBar;
    }

    /**
     * 获取导航栏的高度.
     *
     * @param activity {@link Activity}
     * @return 导航栏的高度
     */
    public static int getNavigationBarHeight(Activity activity) {
        if (isLandscape(activity)) {
            return getScreenRealWidth(activity) - getScreenWidth(activity);
        } else {
            return getScreenRealHeight(activity) - getScreenHeight(activity);
        }
    }

    /**
     * 判断手机是否有导航栏
     *
     * @param activity {@link Activity}
     * @return {@code true} 有导航栏<br> {@code false} 没有导航栏
     */
    public static boolean checkDeviceHasNavigationBar(Activity activity) {
        if (getScreenRealHeight(activity) != getScreenHeight(activity) || getScreenRealWidth(activity) != getScreenWidth(activity)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否横屏
     *
     * @param context {@link Context}
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 判断是否竖屏
     *
     * @param context {@link Context}
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static int getDensityDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static float getDensityXDpi(Context context) {
        return context.getResources().getDisplayMetrics().xdpi;
    }


    public static float getDensityYDpi(Context context) {
        return context.getResources().getDisplayMetrics().ydpi;
    }

    private static double getScreenPhysicalWidth(Activity activity) {
        return Math.pow(getScreenRealWidth(activity) / getDensityXDpi(activity), 2);
    }

    private static double getScreenPhysicalHeight(Activity activity) {
        return Math.pow(getScreenRealHeight(activity) / getDensityYDpi(activity), 2);
    }

    /**
     * 获取屏幕物理尺寸(单位：英寸)
     *
     * @param activity {@link Activity}
     * @return 保留一位小数并且四舍五入的物理尺寸
     */
    public static double getScreenInches(Activity activity) {
        double x = getScreenPhysicalWidth(activity);
        double y = getScreenPhysicalHeight(activity);
        return convertNumber(Math.sqrt(x + y), 1);
    }

    private static double convertNumber(double srcNumber, int scale) {
        BigDecimal bigDecimal = new BigDecimal(srcNumber);
        BigDecimal newDecimal = bigDecimal.setScale(scale, RoundingMode.HALF_UP);
        return newDecimal.doubleValue();
    }

    /**
     * 禁止当前被截屏
     *
     * @param activity {@link Activity}
     */
    public static void disableScreenShot(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    /**
     * 显示虚拟导航栏菜单按钮.
     * 虚拟导航栏菜单按钮在4.0以后默认不显示，可以利用反射强行设置，调用位置须在setContentView之后
     * 具体可以参考5.0以及6.0中的PhoneWindow类源码
     *
     * @param window {@link Window}
     */
    public static void showNavigationMenuKey(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                Method setNeedsMenuKey = Window.class.getDeclaredMethod("setNeedsMenuKey", int.class);
                setNeedsMenuKey.setAccessible(true);
                int value = WindowManager.LayoutParams.class.getField("NEEDS_MENU_SET_TRUE").getInt(null);
                setNeedsMenuKey.invoke(window, value);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                int flags = WindowManager.LayoutParams.class.getField("FLAG_NEEDS_MENU_KEY").getInt(null);
                window.addFlags(flags);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
