package com.xyz.util;

import android.os.Build;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

/**
 * Created by ZP on 2017/10/12.
 * Launcher 单元测试
 */
@RunWith(Parameterized.class)
public class PackageUtilParamTest {

    @Parameterized.Parameters
    public static Iterable<String[]> data() {
        return Arrays.asList(new String[][]{
                {"smartisan", "com.smartisanos.launcher"},
                {"meizu", "com.meizu.flyme.launcher"},
                {"huawei", "com.huawei.android.launcher"},
                {"samsung", "com.sec.android.app.launcher"},
        });
    }

    @Rule
    public ActivityTestRule<TestActivity> mActivity = new ActivityTestRule<TestActivity>(TestActivity.class);

    private String mDevice;
    private String launcherName;

    private String currentDevice;

    public PackageUtilParamTest(String device, String launcherName) {
        this.mDevice = device;
        this.launcherName = launcherName;
    }

    @Before
    public void setUp() {
        currentDevice = Build.MANUFACTURER;
    }

    @Test
    public void getLauncherPackageName() throws Exception {
        String launcherPackageName = PackageUtil.getLauncherPackageName(mActivity.getActivity());
        System.out.println("mDevice:" + mDevice);
        if (currentDevice.toLowerCase().contains(mDevice)) {
            Assert.assertEquals(launcherName, launcherPackageName);
        } else {
            Assert.fail("test device is not " + mDevice);
        }
    }

}