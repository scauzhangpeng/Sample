package com.xyz.util;

import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by ZP on 2017/9/16.
 */
@RunWith(AndroidJUnit4.class)
public class PhoneUtilTest {

    @Rule
    public ActivityTestRule<TestActivity> mTestActivity =
            new ActivityTestRule<TestActivity>(TestActivity.class);


    @Test
    public void getIMEI() throws Exception {
        Assert.assertEquals("imei:", "", PhoneUtil.getIMEI(mTestActivity.getActivity()));
    }

    @Test
    public void getIMSI() throws Exception {
        Assert.assertEquals("imsi:", "", PhoneUtil.getIMSI(mTestActivity.getActivity()));
    }

}