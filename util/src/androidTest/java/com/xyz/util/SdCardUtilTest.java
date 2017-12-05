package com.xyz.util;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by ZP on 2017/9/15.
 * <p>
 * SD Card测试类
 * </p>
 */
@RunWith(AndroidJUnit4.class)
public class SdCardUtilTest {
    @Rule
    public ActivityTestRule<TestActivity> mActivity = new ActivityTestRule<TestActivity>(TestActivity.class);


    @Test
    public void isSdCardUseful() throws Exception {
        Assert.assertEquals("", true, SdCardUtil.isSdCardUseful());
    }

}