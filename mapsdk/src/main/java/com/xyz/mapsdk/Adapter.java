package com.xyz.mapsdk;

/**
 * Created by ZP on 2018/1/15.
 */

public interface Adapter {

    void startLocation();

    void setMapLocationListener(MapLocationListener locationListener);

    void stopLocation();
}
