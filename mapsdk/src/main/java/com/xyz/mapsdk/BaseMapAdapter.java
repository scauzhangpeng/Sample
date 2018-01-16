package com.xyz.mapsdk;

import android.content.Context;

/**
 * Created by ZP on 2018/1/15.
 */

public abstract class BaseMapAdapter implements Adapter {

    protected Context mContext;

    public BaseMapAdapter(Context context) {
        mContext = context;
    }

    protected MapLocationListener mMapLocationListener;

    @Override
    public void setMapLocationListener(MapLocationListener locationListener) {
        mMapLocationListener = locationListener;
    }
}
