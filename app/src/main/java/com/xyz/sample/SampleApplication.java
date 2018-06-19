package com.xyz.sample;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by ZP on 2018/3/2.
 */

public class SampleApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initLeakCanary();
    }

    private void initLeakCanary() {

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
