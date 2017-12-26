package com.xyz.util;

import android.os.Build;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by ZP on 2017/9/14.
 * <p>
 * 设备信息测试类
 * </p>
 */
@RunWith(AndroidJUnit4.class)
public class DeviceUtilTest {


    /**
     * "Nexus 6"
     *
     * @throws Exception
     */
    @Test
    public void testBuildModel() throws Exception {
        Assert.assertEquals("型号", "", Build.MODEL);
    }

    /**
     * <b>锤子：smartisan</b>
     * <b>小米/红米：Xiaomi</b>
     *
     * @throws Exception
     */
    @Test
    public void testBuildManufacturer() throws Exception {
        Assert.assertEquals("厂商", "", Build.MANUFACTURER);
    }

    @Test
    public void testBuildId() throws Exception {
        Assert.assertEquals("版本号", "", Build.ID);
    }

    @Test
    public void testBuildDisplay() throws Exception {
        Assert.assertEquals("版本号", "", Build.DISPLAY);
    }

    /**
     * <b>红米4x：santoni</b>
     * <b>红米3：ido</b>  待验证
     * <b>红米2：arnami</b> 待验证
     * <b>红米Note2：hermes</b> 待验
     *
     * @throws Exception
     */
    @Test
    public void testBuildProduct() throws Exception {
        Assert.assertEquals("产品", "", Build.PRODUCT);
    }

    /**
     * <b>红米4x：santoni</b>
     *
     * @throws Exception
     */
    @Test
    public void testBuildDevice() throws Exception {
        Assert.assertEquals("设备", "", Build.DEVICE);
    }

    @Test
    public void testBuildBoard() throws Exception {
        Assert.assertEquals("主板", "", Build.BOARD);
    }

    @Test
    public void testBuildBrand() throws Exception {
        Assert.assertEquals("品牌", "", Build.BRAND);
    }

    /**
     * <b> qcom：高通</b>
     *
     * @throws Exception
     */
    @Test
    public void testBuildHardware() throws Exception {
        Assert.assertEquals("硬件/处理器", "", Build.HARDWARE);
    }

    /**
     * adb devices 可查看
     *
     * @throws Exception
     */
    @Test
    public void testBuildSerial() throws Exception {
        Assert.assertEquals("序列号", "", Build.SERIAL);
    }

    @Test
    public void getSystemAPIVersion() throws Exception {
        Assert.assertEquals("Android API", 25, DeviceUtil.getSystemAPIVersion());
    }

    @Test
    public void getSystemReleaseVersion() throws Exception {
        Assert.assertEquals("Android 版本", "", DeviceUtil.getSystemReleaseVersion());
    }

    @Test
    public void isEmulator() throws Exception {

    }

    @Test
    public void getNumberOfCpuCores() throws Exception {
        Assert.assertEquals(8, DeviceUtil.getNumberOfCpuCores());
    }

    @Test
    public void getBaseBrand_Ver() throws Exception {
        Assert.assertEquals("", DeviceUtil.getBasebandVersion());
    }

    @Test
    public void getLinuxCoreVersion() throws Exception {
        Assert.assertEquals("", DeviceUtil.getLinuxCoreVersion());
    }

}