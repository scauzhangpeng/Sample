package com.xyz.util;

import android.content.Context;
import android.os.Build;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.filters.SmallTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by ZP on 2017/9/14.
 * <p>
 * 屏幕属性测试
 * </p>
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ScreenUtilTest extends ActivityInstrumentationTestCase2<TestActivity> {


    private Context mContext;

    public ScreenUtilTest() {
        super(TestActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mContext = InstrumentationRegistry.getTargetContext();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void getScreenHeight() throws Exception {
        Assert.assertEquals("屏幕可用高度：", 1280, ScreenUtil.getScreenHeight(mContext));
    }

    @Test
    public void getScreenWidth() throws Exception {
        Assert.assertEquals("屏幕可用宽度：", 720, ScreenUtil.getScreenWidth(mContext));
    }

    @Test
    public void getScreenRealHeight() throws Exception {
        Assert.assertEquals("屏幕物理高度：", 1280, ScreenUtil.getScreenRealHeight(getActivity()));
    }

    @Test
    public void getScreenRealWidth() throws Exception {
        Assert.assertEquals("屏幕物理宽度：", 720, ScreenUtil.getScreenRealWidth(getActivity()));
    }

    @Test
    public void getStatusBarHeight_px() throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Assert.assertEquals("API > 23:", 24, ScreenUtil.getStatusBarHeight_px(mContext));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertEquals("API > 19:", 25, ScreenUtil.getStatusBarHeight_px(mContext));
        } else {
            Assert.assertEquals("API < 19:", 0, ScreenUtil.getStatusBarHeight_px(mContext));
        }
    }

    /**
     * API 23之后是24
     * API 19之后是25
     * API 19之前是0
     *
     * @throws Exception
     */
    @Test
    public void getStatusBarHeight_dp() throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Assert.assertEquals("API > 23:", 24, ScreenUtil.getStatusBarHeight_dp(mContext));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertEquals("API > 19:", 25, ScreenUtil.getStatusBarHeight_dp(mContext));
        } else {
            Assert.assertEquals("API < 19:", 0, ScreenUtil.getStatusBarHeight_dp(mContext));
        }
    }

    @Test
    public void sp2px() throws Exception {

    }

    @Test
    public void px2sp() throws Exception {

    }

    @Test
    public void dp2px() throws Exception {

    }

    @Test
    public void px2dp() throws Exception {

    }

    @Test
    public void getDensity() throws Exception {
        Assert.assertEquals("", 2.5, ScreenUtil.getDensity(mContext), 0.0);
    }

    /**
     * @throws Exception
     */
    @Test
    public void getNavigationBarHeight() throws Exception {
        Assert.assertEquals("导航栏高度：", 0, ScreenUtil.getNavigationBarHeight(mContext));
    }

    /**
     * 华为Mate 8 动态隐藏导航栏的时候。无法正确判断是否显示了导航栏
     *
     * @throws Exception
     */
    @Test
    public void checkDeviceHasNavigationBar() throws Exception {
        Assert.assertEquals("是否有导航栏", false, ScreenUtil.checkDeviceHasNavigationBar(mContext));
    }

    @Test
    public void getNavigationBarHeightByCalculate() throws Exception {
        Assert.assertEquals("导航栏高度：", 0, ScreenUtil.getNavigationBarHeight(getActivity()));
    }

    @Test
    public void checkDeviceHasNavigationBarByCalculate() throws Exception {
        Assert.assertEquals("是否有导航栏", false, ScreenUtil.checkDeviceHasNavigationBar(getActivity()));
    }

    @Test
    public void isLandscape() throws Exception {
        Assert.assertEquals("是否横屏", true, ScreenUtil.isLandscape(mContext));
    }

    @Test
    public void isPortrait() throws Exception {
        Assert.assertEquals("是否竖屏", false, ScreenUtil.isPortrait(mContext));
    }

    @Test
    public void getDensityDpi() throws Exception {
        Assert.assertEquals("", 400, ScreenUtil.getDensityDpi(mContext));
    }

    @Test
    public void getDensityXDpi() throws Exception {
        Assert.assertEquals("", 365.7, ScreenUtil.getDensityXDpi(mContext), 0.0);
    }

    @Test
    public void getDensityYDpi() throws Exception {
        Assert.assertEquals("", 366.6, ScreenUtil.getDensityYDpi(mContext), 0.0);
    }

    @Test
    public void getScreenPhysicalWidth() throws Exception {

    }

    @Test
    public void getScreenPhysicalHeight() throws Exception {

    }

    @Test
    public void getScreenInches() throws Exception {
        Assert.assertEquals("屏幕尺寸(英寸)", 6.0, ScreenUtil.getScreenInches(getActivity()), 0.0);
    }
}