package com.xyz.util;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import androidx.annotation.RequiresApi;

/**
 * 相机工具类.
 * <p>打开/关闭闪光灯，兼容性</p>
 * Created by ZP on 2018/2/28.
 */

public class CameraUtil {

    private String mId;
    private CameraManager mCameraManager;
    private Camera mCamera;

    private CameraUtil() {

    }

    public static CameraUtil getInstance() {
        return InnerClazz.instance;
    }


    public boolean openFlash(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return openFlashM(context);
        } else {
            return openFlashLollipop();
        }
    }

    public boolean closeFlash() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return closeFlashM();
        } else {
            return closeFlashLollipop();
        }
    }


    private boolean openFlashM(Context context) {
        mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        if (mCameraManager == null) {
            return false;
        } else {
            try {
                String[] cameraIdList = mCameraManager.getCameraIdList();
                for (String id : cameraIdList) {
                    CameraCharacteristics cameraCharacteristics = mCameraManager.getCameraCharacteristics(id);
                    Boolean available = cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    /*
                     * 获取相机面对的方向
                     * CameraCharacteristics.LENS_FACING_FRONT 前置摄像头
                     * CameraCharacteristics.LENS_FACING_BACK 后置摄像头
                     * CameraCharacteristics.LENS_FACING_EXTERNAL 外部的摄像头
                     */
                    Integer integer = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);

                    if (available != null && available && integer != null && integer == CameraCharacteristics.LENS_FACING_BACK) {
                        mCameraManager.setTorchMode(id, true);
                        mId = id;
                        return true;
                    }
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean closeFlashM() {
        if (mCameraManager != null) {
            try {
                mCameraManager.setTorchMode(mId, false);
                return true;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean openFlashLollipop() {
        if (mCamera == null) {
            mCamera = Camera.open();
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(parameters);
            mCamera.startPreview();
            return true;
        }
        return false;
    }

    private boolean closeFlashLollipop() {
        if (mCamera != null) {
            mCamera.startPreview();
            mCamera.release();
            mCamera = null;
            return true;
        }
        return false;
    }


    private static class InnerClazz {
        private static final CameraUtil instance = new CameraUtil();
    }
}
