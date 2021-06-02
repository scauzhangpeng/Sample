package com.xyz.util;

import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by ZP on 2017/10/15.
 * 网络相关工具类单元测试
 */
@RunWith(AndroidJUnit4.class)
public class NetWorkUtilTest {

    @Rule
    public ActivityTestRule<TestActivity> mRule = new ActivityTestRule<TestActivity>(TestActivity.class);


    @Test
    public void getMacAddressCompat() throws Exception {
        String macAddressCompat = NetWorkUtil.getMacAddressCompat(mRule.getActivity());
        Assert.assertEquals("", macAddressCompat);
    }

    @Test
    public void wifiConnect() throws Exception {

        boolean b = NetWorkUtil.wifiConnect(mRule.getActivity(), "LAN", "123456");
        if (b) {
            Thread.sleep(3000);
            String wifiName = NetWorkUtil.getWifiName(mRule.getActivity());
            Assert.assertEquals("\"NSwifi\"", wifiName);
        } else {
            Assert.fail("连接失败");
        }
    }

    @Test
    public void wifiDisConnect() throws Exception {
        boolean b = NetWorkUtil.wifiDisConnect(mRule.getActivity());
        if (b) {
            Thread.sleep(300);
            boolean wifiConnected = NetWorkUtil.isWifiConnected(mRule.getActivity());
            Assert.assertFalse(wifiConnected);
        }
    }

    @Test
    public void getWifiName() throws Exception {
        String wifiName = NetWorkUtil.getWifiName(mRule.getActivity());
        Assert.assertEquals("\"NSwifi\"", wifiName);
    }

    @Test
    public void getWifiNameEmpty() throws Exception {
        if (NetWorkUtil.isWifiConnected(mRule.getActivity())) {
            NetWorkUtil.wifiDisConnect(mRule.getActivity());
            Thread.sleep(500);
        }
        String wifiName = NetWorkUtil.getWifiName(mRule.getActivity());
        Assert.assertEquals("<unknown ssid>", wifiName);
    }

    @Test
    public void isGPRSOn() throws Exception {
        boolean gprsOn = NetWorkUtil.isGPRSOn(mRule.getActivity());
        Assert.assertTrue(gprsOn);
    }

    @Test
    public void setGRPSEnabled() throws Exception {
        boolean b = NetWorkUtil.setGRPSEnabled(mRule.getActivity(), false);
        Assert.assertFalse(b);
    }

    @Test
    public void is4G() throws Exception {
        boolean g = NetWorkUtil.is4G(mRule.getActivity());
        Assert.assertTrue(g);
    }

    @Test
    public void isWifiConnected() throws Exception {
        NetWorkUtil.setWifiEnable(mRule.getActivity(), true);
        boolean wifiConnected = NetWorkUtil.isWifiConnected(mRule.getActivity());
        Assert.assertTrue(wifiConnected);
    }

    @Test
    public void setWifiEnable() throws Exception {
        boolean b = NetWorkUtil.setWifiEnable(mRule.getActivity(), true);
        Assert.assertTrue(b);
    }

    @Test
    public void isNetWorkAvailable() throws Exception {
        boolean netWorkAvailable = NetWorkUtil.isNetWorkAvailable(mRule.getActivity());
        Assert.assertTrue(netWorkAvailable);
    }

    @Test
    public void isWifiOn() throws Exception {
        boolean wifiOn = NetWorkUtil.isWifiOn(mRule.getActivity());
        Assert.assertTrue(wifiOn);
    }

}