package com.xyz.util;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by ZP on 2017/11/29.
 * 意图跳转测试类
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntentUtilTest {

    @Rule
    public ActivityTestRule<TestActivity> mActivity = new ActivityTestRule<TestActivity>(TestActivity.class);

    @Test
    @Ignore
    public void toHiddenConfig() throws Exception {

    }

    @Test
    @Ignore
    public void toHiddenList() throws Exception {

    }

    @Test
    public void toApplicationSetting() throws Exception {
        boolean b = IntentUtil.toApplicationSetting(mActivity.getActivity(), "com.xyz.sample");
        Assert.assertTrue(b);
    }

    @Test
    public void isAirPlaneModeOn() throws Exception {
        Assert.assertFalse(IntentUtil.isAirPlaneModeOn(mActivity.getActivity()));
    }

    @Test
    public void toAirPlaneModeSetting() throws Exception {
        boolean b = IntentUtil.toAirPlaneModeSetting(mActivity.getActivity());
        Assert.assertTrue(b);
    }

    @Test
    public void toBatterySetting() throws Exception {
        boolean b = IntentUtil.toBatterySetting(mActivity.getActivity());
        Assert.assertTrue(b);
    }

    @Test
    public void toAllApplicationSetting() throws Exception {
        boolean b = IntentUtil.toAllApplicationSetting(mActivity.getActivity());
        Assert.assertTrue(b);
    }

    @Test
    public void toDefaultApplicationSetting() throws Exception {
        boolean b = IntentUtil.toDefaultApplicationSetting(mActivity.getActivity());
        Assert.assertTrue(b);
    }

    @Test
    public void toQuickLaunchShortCut() throws Exception {
        boolean b = IntentUtil.toQuickLaunchShortCut(mActivity.getActivity());
        Assert.assertTrue(b);
    }

    @Test
    public void toWIFIsSetting() throws Exception {
        boolean b = IntentUtil.toWIFIsSetting(mActivity.getActivity());
        Assert.assertTrue(b);
    }

    @Test
    public void toCallWaitConfirm() throws Exception {
        IntentUtil.toCallWaitConfirm(mActivity.getActivity(), "10086");
    }

    @Test
    public void toCallImmediately() throws Exception {
        IntentUtil.toCallImmediately(mActivity.getActivity(), "10086");
    }

    @Test
    public void toAndroidBeamSetting() throws Exception {
        boolean b = IntentUtil.toAndroidBeamSetting(mActivity.getActivity());
        Assert.assertTrue(b);
    }

    @Test
    public void toGpsSetting() throws Exception {
        boolean b = IntentUtil.toGpsSetting(mActivity.getActivity());
        Assert.assertTrue(b);
    }

    @Test
    public void toNFCSetting() throws Exception {
        boolean b = IntentUtil.toNFCSetting(mActivity.getActivity());
        Assert.assertTrue(b);
    }

}