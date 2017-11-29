package com.xyz.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by ZP on 2017/11/17.
 */
public class PhoneUtilTest {


    @Test
    public void hiddenMiddlePhoneNum() throws Exception {
        String hiddenPhoneNumber = PhoneUtil.hiddenMiddlePhoneNum("13634429866");
        Assert.assertEquals("", "136****9866", hiddenPhoneNumber);
    }

    @Test(expected = IOException.class)
    public void testErrorPhoneNumber() throws Exception {
        String hiddenPhoneNumber = PhoneUtil.hiddenMiddlePhoneNum("1363442986");
        Assert.assertEquals("", "136****986", hiddenPhoneNumber);
    }

    @Test
    public void isPhoneNumber() throws Exception {
        boolean phoneNumber = PhoneUtil.isPhoneNumber("13634429866");
        Assert.assertTrue(phoneNumber);
    }

}