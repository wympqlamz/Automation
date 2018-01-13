package com.by.automate.testCases.devicepass;

import com.by.automate.fwk.DPWebApp;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Created by Justin on 2017/7/3.
 */
public class InitiaDevicepass {

    public DPWebApp dp = null;

    @BeforeClass
    public void SetUp(){
        dp = new DPWebApp();
    }

    @AfterClass
    public void tearDown(){

        dp.close();
    }

}
