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
package com.by.automate.testCases.devicepass.user.workspaces.apps;

import org.testng.annotations.Test;

public class Test_FC_app_CurrentUse_ScriptBasic_UIAutomator_auto extends Test_FC_app_core {
    @Test
    public void test01_selectDevice() {
        openAllDevices();
//        selectDevice();
        allDevice_selectDevice();
        allDevice_controlDevice();
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
    public void test040_selectAppScriptType() {
        //点击弹窗,并选择自动化类型
        dp.waitByTimeOut(4000);
        dp.clickOn("currentUse_Start_automation");
        dp.waitByTimeOut(1000);
        dp.clickOn("currentUse_UI_Automator");
        dp.waitByTimeOut(1000);
        dp.clickOn("currentUse_UI_Automator_next");
        dp.waitByTimeOut(1000);
        dp.clear("currentUse_Test_Class_Name");
        dp.sendKeys("currentUse_Test_Class_Name", "111");
        dp.clear("currentUse_Test_Method_Name");
        dp.sendKeys("currentUse_Test_Method_Name", "111");
        dp.waitByTimeOut(500);
        dp.clickOn("currentUse_UI_Automator_next2");
        dp.waitByTimeOut(1000);
    }

    @Test
    public void test050_uploadAppScript() {
        uploadApp("/UiAutomatorScript/Uiautomator-Test.jar");
        isUploadStatus();
        dp.waitByTimeOut(1000);
    }

    @Test
    public void test060_checkAppScript() {
        dp.waitByTimeOut(1000);
        dp.assertEqual("Uiautomator-Test.jar", dp.getValueOf("currentUse_UI_Automator_uploadedname"));
        dp.waitByTimeOut(5000);
        dp.clickOn("cancel");
        dp.waitByTimeOut(1000);
    }

    @Test
    public void test100_removeApp() {
        removeApp();
    }

    @Test
    public void test110_releaseAll() {
        releaseAll();
    }

}


