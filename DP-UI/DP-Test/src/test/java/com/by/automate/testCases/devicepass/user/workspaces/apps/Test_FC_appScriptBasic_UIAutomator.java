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

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class Test_FC_appScriptBasic_UIAutomator  extends Test_FC_app_core{
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
        dp.clickOn("script");
        dp.waitByTimeOut(1000);
        Select sel = new Select(dp.driver.findElement(By.xpath("//select[@ng-change='changeFramework()']")));
        sel.selectByIndex(2);
        dp.waitByTimeOut(1000);
    }

    @Test
    public void test050_uploadAppScript() {
        //上传
        uploadApp("/UiAutomatorScript/Uiautomator-Test.jar");
        dp.waitByTimeOut(3000);
        dp.clickOn("scriptUpload");
        dp.waitByTimeOut(500);
        isUploadStatus();
        dp.waitByTimeOut(1000);
    }

    @Test
    public void test060_editAppScript() {
        editAppScript();
    }

    @Test
    public void test070_deleteAppScript() {
        //删除
        dp.clickOn("script-remove");
        dp.waitByTimeOut(1000);
        dp.clickOn("script-remove-confirm");
        dp.waitByTimeOut(2000);
    }

    @Test
    public void test080_checkNorecords() {
        //检查no record
        dp.waitByTimeOut(2000);
        String noscript = dp.getValueOf("script_norecords");
        assert noscript.contains("No records") : "app upload no script,check no records error";
    }


    @Test
    public void test090_closeScriptWindows() {
        dp.clickOn("script_close");
    }

    @Test
    public void test100_removeApp() {
        removeApp();
    }

}
