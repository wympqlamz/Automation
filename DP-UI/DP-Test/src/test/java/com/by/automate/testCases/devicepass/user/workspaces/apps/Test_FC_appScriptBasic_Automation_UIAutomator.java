/**
 * 版权 @Copyright: 2017 BeiJing beyondsoft Co. Ltd. <br/>
 * 项目名称：DP-Test<br/>
 * 文件名称：Test_FC_appScriptBasic.java <br/>
 * 包名：com.by.automate.testCases.devicepass.user.workspaces.apps<br/>
 * 创建人：@author cjkl@beyondsoft.com
 * 创建时间：2017年5月18日/上午9:39:23<br/>
 * 修改人： cjkl@beyondsoft.com<br/>
 * 修改时间：2017年5月18日/上午9:39:23<br/>
 * 修改备注：<br/>
 */
package com.by.automate.testCases.devicepass.user.workspaces.apps;

import org.testng.annotations.Test;

public class Test_FC_appScriptBasic_Automation_UIAutomator extends Test_FC_app_core {
    @Test
    public void test010_check_no_records() {
        openApps();
        check_app_no_records();
    }

    @Test
    public void test020_uploadApp() {
        uploadApp();
    }

    @Test
    public void test030_checkAppNoScript() {
    	checkAppNoScript();
    }

    @Test
    public void test040_selectAppScriptType() {
        //点击弹窗,并选择自动化类型
        dp.clickOn("Start_automation");
        dp.waitByTimeOut(1000);
        dp.clickOn("UI_Automator");
        dp.waitByTimeOut(1000);
        dp.clickOn("UI_Automator_next");
        dp.waitByTimeOut(1000);
    }

    @Test
    public void test041_editonfiguration() {
        //上传
        dp.waitByTimeOut(1000);
        dp.clear("Test_Class_Name");
        dp.waitByTimeOut(1000);
        dp.clear("Test_Method_Name");
        dp.waitByTimeOut(1000);
        dp.sendKeys("Test_Class_Name", "Test_Class_Name:111");
        dp.waitByTimeOut(1000);
        dp.sendKeys("Test_Method_Name", "Test_Method_Name:111");
        dp.waitByTimeOut(1000);
        dp.clickOn("UI_Automator_next2");
        dp.waitByTimeOut(1000);
    }

    @Test
    public void test050_uploadAppScript() {
        //上传
        uploadApp("/UiAutomatorScript/Uiautomator-Test.jar");
        isUploadStatus();
        dp.waitByTimeOut(1000);
    }

    @Test
    public void test060_checkAppScript() {
        dp.waitByTimeOut(1000);
        dp.assertEqual("Uiautomator-Test.jar", dp.getValueOf("UI_Automator_uploadedname"));
        dp.waitByTimeOut(5000);
        dp.clickOn("cancel");
        dp.waitByTimeOut(1000);
    }

    @Test
    public void test070_removeApp() {
        removeApp();
    }
}
