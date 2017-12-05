package com.xyz.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.format.Formatter;

import com.xyz.util.bean.SDCardInfo;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.STORAGE_SERVICE;

/**
 * Created by ZP on 2017/9/15.
 * <p>
 * SD Card相关工具类
 * </p>
 */

public class SdCardUtil {


    /**
     * 判断SD卡是否被挂在.
     *
     * @return {@code true} 已挂载<br> {@code false} 未挂载
     */
    public static boolean isSdCardUseful() {
        String externalStorageState = Environment.getExternalStorageState();
        return externalStorageState.equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 获取手机所有存储路径.
     *
     * @param context {@link Context}
     * @return 包含所有存储路径的实体
     * @see SDCardInfo 存储路径实体
     */
    public static List<SDCardInfo> getAllExtendedMemory(Context context) {
        List<SDCardInfo> list = new ArrayList<>();
        StorageManager mStorageManager = (StorageManager) context.getSystemService(STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
//            Method getState = storageVolumeClazz.getMethod("getState");
            Method getVolumeState = StorageManager.class.getDeclaredMethod("getVolumeState", String.class);
            Method getMaxFileSize = storageVolumeClazz.getMethod("getMaxFileSize");
//            Method isPrimary = storageVolumeClazz.getMethod("isPrimary");
//            Method isEmulated = storageVolumeClazz.getMethod("isEmulated");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                SDCardInfo bean = new SDCardInfo();
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                String state = (String) getVolumeState.invoke(mStorageManager, path);
                long maxFileSize = (long) getMaxFileSize.invoke(storageVolumeElement);
//                boolean primary = (Boolean) isPrimary.invoke(storageVolumeElement);
//                boolean emulated = (Boolean) isPrimary.invoke(storageVolumeElement);
                bean.setPath(path);
                bean.setRemovable(removable);
                bean.setState(state);
                bean.setMaxFileSize(maxFileSize);
                list.add(bean);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取手机SD卡总容量.
     *
     * @param context {@link Context}
     * @return SD卡总容量，单位/GB、MB、KB
     */
    public static String formatSdMemory(Context context) {
        return formatMemory(context, false, true);
    }

    /**
     * 获取手机SD卡可用空间.
     *
     * @param context {@link Context}
     * @return SD卡可用空间，单位/GB、MB、KB
     */
    public static String formatSdAvailableMemory(Context context) {
        return formatMemory(context, false, false);
    }

    /**
     * 获取手机TF卡总容量.
     *
     * @param context {@link Context}
     * @return TF卡总容量，单位/GB、MB、KB
     */
    public static String formatTfMemory(Context context) {
        return formatMemory(context, true, true);
    }

    /**
     * 获取手机TF卡可用空间.
     *
     * @param context {@link Context}
     * @return TF卡可用空间，单位/GB、MB、KB
     */
    public static String formatTfAvailableMemory(Context context) {
        return formatMemory(context, true, false);
    }

    /**
     * 获取SD卡路径.
     *
     * @param context {@link Context}
     * @return SD卡路径或者null
     */
    public static String getSdCardPath(Context context) {
        return getPath(context, false, true);
    }

    /**
     * 获取TF卡路径.
     *
     * @param context {@link Context}
     * @return TF卡路径或者null
     */
    public static String getTfCardPath(Context context) {
        return getPath(context, true, false);
    }

    /**
     * 获取存储空间路径.
     * 如果存储卡未挂载则返回null,如果isTfCard == isSdCard,会优先返回sd卡路径
     *
     * @param context  {@link Context}
     * @param isTfCard 检查TF卡
     * @param isSdCard 检查SD卡
     * @return 存储空间路径, 或者null
     */
    private static String getPath(Context context, boolean isTfCard, boolean isSdCard) {
        List<SDCardInfo> allExtendedMemory = getAllExtendedMemory(context);
        if (allExtendedMemory != null) {
            for (SDCardInfo bean : allExtendedMemory) {
                if (!bean.isRemovable() == isSdCard && Environment.MEDIA_MOUNTED.equals(bean.getState())) {
                    return bean.getPath();
                }
                if (bean.isRemovable() == isTfCard && Environment.MEDIA_MOUNTED.equals(bean.getState())) {
                    return bean.getPath();
                }
            }
        }
        return null;
    }

    private static String formatMemory(Context context, boolean isTfCard, boolean isTotal) {
        List<SDCardInfo> allExtendedMemory = getAllExtendedMemory(context);
        if (allExtendedMemory != null) {
            for (SDCardInfo bean : allExtendedMemory) {
                if (bean.isRemovable() == isTfCard && Environment.MEDIA_MOUNTED.equals(bean.getState())) {
                    if (isTotal) {
                        return Formatter.formatFileSize(context, getTotalMemory(bean.getPath()));
                    } else {
                        return Formatter.formatFileSize(context, getAvailableMemory(bean.getPath()));
                    }
                }
            }
        }
        return Formatter.formatFileSize(context, 0);
    }

    public static long getTotalMemory(String filePath) {
        StatFs statFs = new StatFs(filePath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return statFs.getTotalBytes();
        }
        int blockCount = statFs.getBlockCount();
        int blockSize = statFs.getBlockSize();
        return blockCount * blockSize;
    }

    public static long getAvailableMemory(String filePath) {
        StatFs statFs = new StatFs(filePath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return statFs.getAvailableBytes();
        }
        int blockSize = statFs.getBlockSize();
        int availableBlocks = statFs.getAvailableBlocks();
        return blockSize * availableBlocks;
    }


//    public static List<HomeDirBean> getAllExternalStorage(Context context) {
//        List<HomeDirBean> storagePath = new ArrayList<>();
//        StorageManager storageManager = (StorageManager) context.getSystemService(STORAGE_SERVICE);
//        StorageVolume[] storageVolumes;
//        try {
//            Method getVolumeList = StorageManager.class.getDeclaredMethod("getVolumeList");
//            storageVolumes = (StorageVolume[]) getVolumeList.invoke(storageManager);
//            Method getVolumeState = StorageManager.class.getDeclaredMethod("getVolumeState", String.class);
//            for (StorageVolume storageVolume : storageVolumes) {
//                String desc = storageVolume.getDescription(context);
////                Log.i(TAG, "storageVolume name--->" + desc);
//                Method getPath = StorageVolume.class.getMethod("getPath");
//                String path = (String) getPath.invoke(storageVolume);
////                Log.i(TAG, "StoragePath--->" + path);
//                //这里需要用StorageManager反射调用getVolumeState函数，而不应该用StorageVolume的getState方法，因为可能会报错
//                String state = (String) getVolumeState.invoke(storageManager, path);
////                Log.i(TAG, "storageVolume State--->" + state);
//                if (Environment.MEDIA_MOUNTED.equals(state)) {
//                    HomeDirBean bean = new HomeDirBean(path, desc);
//                    storagePath.add(bean);
//                }
//            }
//        } catch (Exception e) {
////            Log.e(TAG, e.getMessage());
//        }
//        return storagePath;
//    }

}
