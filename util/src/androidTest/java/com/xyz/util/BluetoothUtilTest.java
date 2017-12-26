package com.xyz.util;

import android.bluetooth.BluetoothDevice;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.xyz.util.bluetooth.BluetoothUtil;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by ZP on 2017/11/14.
 * 蓝牙工具类测试类
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BluetoothUtilTest {
    @Test
    public void getBondedDevices() throws Exception {
        Set<BluetoothDevice> bondedDevices = BluetoothUtil.getBondedDevices();
        Assert.assertEquals(2, bondedDevices.size());
    }

    @Rule
    public ActivityTestRule<TestActivity> mActivity = new ActivityTestRule<TestActivity>(TestActivity.class);


    @Test
    public void getBluetoothMacAddress() throws Exception {
        String bluetoothMacAddress = BluetoothUtil.getBluetoothMacAddress(mActivity.getActivity());
        Assert.assertEquals("", "02:00:00:00:00:00", bluetoothMacAddress);
    }


    @Test
    public void isBluetoothExit() throws Exception {
        boolean bluetoothExit = BluetoothUtil.isBluetoothExit();
        Assert.assertTrue(bluetoothExit);
    }

    @Test
    public void isBluetoothEnable() throws Exception {
        boolean bluetoothEnable = BluetoothUtil.isBluetoothEnable();
        Assert.assertTrue(bluetoothEnable);
    }

    @Test
    public void enableBluetooth() throws Exception {

    }

}