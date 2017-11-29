package com.xyz.util;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by ZP on 2017/11/29.
 * 小米机型工具测试类
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class XiaomiDeviceUtilTest {

    @Rule
    public ActivityTestRule<TestActivity> mActivity = new ActivityTestRule<TestActivity>(TestActivity.class);

    @Test
    public void toHiddenAppList() throws Exception {
        boolean b = XiaomiDeviceUtil.toHiddenAppList(mActivity.getActivity());
        Assert.assertTrue(b);
    }

    @Test
    public void toConfigApp() throws Exception {
        boolean b = XiaomiDeviceUtil.toConfigApp(mActivity.getActivity(), "com.xyz.sample", "Sample");
        Assert.assertTrue(b);

    }

}