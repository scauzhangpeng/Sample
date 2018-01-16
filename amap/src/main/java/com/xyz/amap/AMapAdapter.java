package com.xyz.amap;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.xyz.mapsdk.BaseMapAdapter;
import com.xyz.mapsdk.MapLocation;

/**
 * Created by ZP on 2018/1/15.
 */

public class AMapAdapter extends BaseMapAdapter {
    private static final String TAG = "AMapAdapter";
    private AMapLocationClient mAMapLocationClient;


    private AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            MapLocation location = new MapLocation();
            location.setProvince(aMapLocation.getProvince());
            location.setCity(aMapLocation.getCity());
            location.setDistrict(aMapLocation.getDistrict());
            location.setLatitude(aMapLocation.getLatitude());
            location.setLongitude(aMapLocation.getLongitude());
            mMapLocationListener.onLocationChanged(location);
        }
    };

    public AMapAdapter(Context context) {
        super(context);
        mAMapLocationClient = new AMapLocationClient(mContext);
        mAMapLocationClient.setLocationListener(mAMapLocationListener);
    }

    @Override
    public void startLocation() {
        Log.d(TAG, "startLocation: ");
        mAMapLocationClient.startLocation();
    }

    @Override
    public void stopLocation() {
        Log.d(TAG, "stopLocation: ");
        mAMapLocationClient.stopLocation();
    }
}
