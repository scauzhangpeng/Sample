package com.xyz.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xyz.amap.AMapAdapter;
import com.xyz.mapsdk.MapLocation;
import com.xyz.mapsdk.MapLocationClient;
import com.xyz.mapsdk.MapLocationListener;

/**
 * Created by ZP on 2018/1/16.
 */

public class LocationActivity extends BaseActivity {


    private TextView mTvLocationInfo;
    private MapLocationClient mMapLocationClient;

    private MapLocationListener mMapLocationListener = new MapLocationListener() {
        @Override
        public void onLocationChanged(MapLocation location) {
            mTvLocationInfo.setText(location.toString() + "时间戳：" + System.currentTimeMillis());
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_location);
        initView();
        initMapClient();
    }

    private void initMapClient() {
        mMapLocationClient = new MapLocationClient(getApplicationContext());
        mMapLocationClient.setMapLocationListener(mMapLocationListener);
//        mMapLocationClient.setMapAdapter(new BaiduAdapter(getApplicationContext()));
        mMapLocationClient.setMapAdapter(new AMapAdapter(getApplicationContext()));
    }

    public void startLocation(View view) {
        if (mMapLocationClient != null) {
            mMapLocationClient.startLocation();
        }
    }

    public void stopLocation(View view) {
        if (mMapLocationClient != null) {
            mMapLocationClient.stopLocation();
        }
    }

    private void initView() {
        mTvLocationInfo = (TextView) findViewById(R.id.tv_location_info);
    }
}
