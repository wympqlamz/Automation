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

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


public class Test_FC_app_CurrentUse_ScriptBasic_UIAutomator extends Test_FC_app_core {
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
    public void test050_selectAppScriptType() {
        //点击弹窗,并选择自动化类型
        dp.waitByTimeOut(4000);
        dp.clickOn("currentUse_script");
        dp.waitByTimeOut(1000);
        Select sel = new Select(dp.driver.findElement(By.xpath("//select[@ng-change='changeFramework()']")));
        sel.selectByIndex(2);
        dp.waitByTimeOut(1000);
    }

    @Test
    public void test060_uploadAppScript() {
        //上传
//		uploadApp("Uiautomator-Test.jar");
        uploadApp("/UiAutomatorScript/Uiautomator-Test.jar");
        dp.waitByTimeOut(3000);
        dp.clickOn("currentUse_scriptUpload");
        dp.waitByTimeOut(500);
        isUploadStatus();
        dp.waitByTimeOut(1000);
    }

    @Test
    public void test070_editAppScript() {
        //编辑
        dp.waitByTimeOut(2000);
        dp.clickOn("currentUse_script_edit");
        dp.waitByTimeOut(1000);
        dp.clear("currentUse_script_rename");
        dp.waitByTimeOut(2000);
        dp.sendKeys("currentUse_script_rename", "111");
        dp.waitByTimeOut(2000);
        dp.clickOn("currentUse_script_submit");
        dp.waitByTimeOut(2000);
    }

    @Test
    public void test080_deleteAppScript() {
        //删除
        dp.clickOn("currentUse_script-remove");
        dp.waitByTimeOut(1000);
        dp.clickOn("currentUse_script-remove-confirm");
    }

    @Test
    public void test090_checkNorecords() {
        //检查no record
        dp.waitByTimeOut(2000);
        String noscript = dp.getValueOf("script_norecords");
        assert noscript.contains("No records"):"app upload no script,check no records error";
        dp.waitByTimeOut(2000);
        dp.clickOn("currentUse_script_cancel");
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


