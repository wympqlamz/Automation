package com.by.automate.testCases.BAT.ios;

import com.by.automate.fwk.DPWebApp;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by dp on 2017/6/6.
 */
public class Test_FCSingle_IosDeviceControl {

    private DPWebApp dp =null;

    @BeforeClass
    public void setUp(){

        dp = new DPWebApp();
        dp.openApp();
        dp.DP_Login();
    }

    @Test(priority = 1)
    public void Test010_sigleIosDeviceControlScreen(){

        // login
        dp.DP_Login();

        // 验证头部信息
        dp.verifyIsShown("jiantou");
        dp.verifyIsShown("messageCenter");
        dp.verifyIsShown("help");
        dp.verifyIsShown("testStatus");
        dp.verifyIsShown("userName");

        // 验证左边logo
        dp.verifyIsShown("logo");
        // 验证workspace功能模块
        dp.verifyIsShown("workspace");
        dp.verifyIsShown("apps");
        dp.verifyIsShown("testReport");
        dp.verifyIsShown("screenShots");
        // 验证devices功能模块
        dp.verifyIsShown("devices");
        dp.verifyIsShown("groups");
        // 版本和版权
        dp.verifyIsShown("build");
        dp.verifyIsShown("copyRight");
        dp.verifyIsShown("byBeyondsoft");

        // 选择单台iOS设备
        dp.selectDriversIOS(1);
        //dp.selectDevices(1);
        
    }


}
