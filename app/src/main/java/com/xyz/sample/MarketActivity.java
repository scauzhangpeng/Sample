package com.xyz.sample;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.xyz.util.MarketUtil;

/**
 * Created by ZP on 2018/1/17.
 */

public class MarketActivity extends BaseActivity {

    private static final String APP_PACKAGE = "com.autonavi.minimap";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_market);
    }

    public void goToMarket(View view) {
        MarketUtil.toMarket(this, APP_PACKAGE, null);
    }

    public void checkMarkets(View view) {
        MarketUtil.checkMarket(this);
    }

    public void goToQQDownload(View view) {
        MarketUtil.toQQDownload(this, APP_PACKAGE);
    }

    public void goTo360Download(View view) {
        MarketUtil.to360Download(this, APP_PACKAGE);
    }

    public void goToWandoujia(View view) {
        MarketUtil.toWandoujia(this, APP_PACKAGE);
    }

    public void goToXiaoMi(View view) {
        MarketUtil.toXiaoMi(this, APP_PACKAGE);
    }

    public void goToMeizu(View view) {
        MarketUtil.toMeizu(this, APP_PACKAGE);
    }

    public void goToSamsung(View view) {
        MarketUtil.goToSamsungMarket(this, APP_PACKAGE);
    }

    public void goToSony(View view) {
        MarketUtil.goToSonyMarket(this, "3502");
    }
}
