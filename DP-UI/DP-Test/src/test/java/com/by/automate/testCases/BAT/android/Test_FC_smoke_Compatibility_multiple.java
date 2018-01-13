/**
 * 版权 @Copyright: 2017 BeiJing beyondsoft Co. Ltd. <br/>
 * 项目名称：DP-Test<br/>
 * 文件名称：啊啊.java <br/>
 * 包名：com.by.automate.testCases.devicepass.user.workspaces.apps<br/>
 * 创建人：@author cjkl@beyondsoft.com
 * 创建时间：2017年5月16日/下午5:43:26<br/>
 * 修改人： cjkl@beyondsoft.com<br/>
 * 修改时间：2017年5月16日/下午5:43:26<br/>
 * 修改备注：<br/>
 */
package com.by.automate.testCases.BAT.android;

import com.by.automate.testCases.devicepass.user.workspaces.apps.Test_FC_app_core;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

public class Test_FC_smoke_Compatibility_multiple extends Test_FC_app_core {
    @Test
    public void test010_deleteAllReport() {
        openApps();
    	deleteAllReport();
    }

    
    @Test
    public void test011_selectDevice() {
        dp.clickOn("allDevices");
        allDevice_selectDevice();
        allDevice_controlDevice();
//        selectDevice();
    }

    
    @Test
    public void test02_check_no_records() {
        check_app_no_records();
    }

    
    @Test
    public void test030_uploadApp() {
        uploadApp();
    }

    
    @Test
    public void test040_run_Compatibility() {
        //点击弹窗,并选择自动化类型
        dp.waitByTimeOut(4000);
        dp.clickOn("currentUse_Start_automation");
        dp.waitByTimeOut(1000);
        dp.clickOn("currentUse_Compatibility");
        dp.waitByTimeOut(1000);
        dp.clickOn("currentUse_UI_Automator_next");
        dp.waitByTimeOut(1000);
        dp.clickOn("currentUse_Compatibility_next2");
        dp.waitByTimeOut(2000);
        dp.clickOn("currentUse_Compatibility_run");
        dp.waitByTimeOut(1000);
        dp.clickOn("currentUse_TestResult");
        dp.waitByTimeOut(1000);
    }

    
    @Test
    public void test050_checkReportstatus() {
    	checkReportstatus();
    }


    @Test
    public void test060_releaseAll() {
        releaseAll();
    }

    
    @Test
    public void test070_checkreportdetail() {
    	
    }
}


