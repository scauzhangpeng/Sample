package com.xyz.mapsdk;

import android.content.Context;
import android.util.Log;

/**
 * Created by ZP on 2018/1/15.
 */

public class MapLocationClient {
    private static final String TAG = "MapLocationClient";

    protected MapLocationListener mMapLocationListener;

    protected Context mContext;

    protected Adapter mAdapter;

    public MapLocationClient(Context context) {
        mContext = context;
    }

    public void startLocation() {
        Log.d(TAG, "startLocation: ");
        mAdapter.startLocation();
        mAdapter.setMapLocationListener(mMapLocationListener);
    }

    public void stopLocation() {
        Log.d(TAG, "stopLocation: ");
        mAdapter.stopLocation();
    }

    public void setMapLocationListener(MapLocationListener locationListener) {
        mMapLocationListener = locationListener;
    }

    public void setMapAdapter(BaseMapAdapter adapter) {
        mAdapter = adapter;
    }
}
