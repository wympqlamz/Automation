package com.by.automate.testCases.devicepass.scriptRecorder;

import com.by.automate.fwk.DPWebApp;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by h on 2017/9/22.
 */
public class Test_FCScriptRecorder {
    private DPWebApp dp = null;
    String PhoneName = null;

    @BeforeClass
    public void setUp() {

        dp = new DPWebApp();
        dp.openApp("scriptRecorder", "recordingScript");
        dp.DP_Login();

    }


    @Test(priority = 1)
    public void Test010_sigleIosDeviceControlScreen() {
        dp.verifyIsShown("controlButton");
        dp.verifyIsShown("bookButton");
        dp.verifyIsShown("groupButton");
        dp.verifyIsShown("releaseButton");
        dp.waitByTimeout(500);

        // 选择单台iOS设备
        PhoneName = dp.selectDriversIOS(1).get(0);
        //dp.selectDevices(1);
        dp.waitByTimeout(500);
        dp.clickOn("controlButton");
        dp.verifyIsShown("inputControlTime");
        dp.waitByTimeout(500);
        dp.setValueTo("inputControlTime","30");
        dp.waitByTimeOut(500);
        dp.clickOn("confirmControlBtn");
        dp.waitByTimeOut(800);
        dp.verifyIsShown("menuApps");
        dp.clickOn("menuApps");




    }

}
