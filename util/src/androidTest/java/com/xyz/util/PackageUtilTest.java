package com.xyz.util;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xyz.util.bean.WrapperPackageInfo;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by ZP on 2017/11/22.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PackageUtilTest {

    @Rule
    public ActivityTestRule<TestActivity> mActivity = new ActivityTestRule<TestActivity>(TestActivity.class);

    @Test
    public void getAppName() throws Exception {
        String appName = PackageUtil.getAppName(mActivity.getActivity());
        Assert.assertEquals("sample", appName);
    }

    @Test
    public void launchApp() throws Exception {
        boolean result = PackageUtil.launchApp(mActivity.getActivity(), "com.xiaomi.market");
//        boolean result = PackageUtil.launchApp(mActivity.getActivity(), "com.newsoft.mpc");
        Assert.assertTrue(result);
    }


    @Test
    public void getApplicationMeta() throws Exception {

    }

    @Test
    public void getAppVersionCode() throws Exception {
        String appVersionName = PackageUtil.getAppVersionName(mActivity.getActivity());
        Assert.assertEquals("1.0.0", appVersionName);
    }

    @Test
    public void getAppVersionName() throws Exception {
        int appVersionCode = PackageUtil.getAppVersionCode(mActivity.getActivity());
        Assert.assertEquals(100, appVersionCode);
    }

    @Test
    public void getInstallApp() throws Exception {
        List<WrapperPackageInfo> installApp = PackageUtil.getInstallApp(mActivity.getActivity());
        System.out.println(installApp.toString());
    }

    @Test
    public void getTaskAffinity() throws Exception {
        String taskAffinity = PackageUtil.getTaskAffinity(mActivity.getActivity());
        Assert.assertEquals("aa", taskAffinity);
    }
}