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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Test_FC_appUploadEditSearchDelete extends Test_FC_app_core {
    @Test
    public void test010_check_version() {
        openApps();
        checkversion();
    }

    @Test
    public void test020_check_no_records() {
        check_app_no_records();
    }

    @Test
    public void test030_uploadApp1() {
        uploadApp();
    }

    @Test
    public void test040_editApp() {
        app_editApp();
    }

    @Test
    public void test050_searchApp() {
        //search
        dp.sendKeys("search", "111");
        dp.waitByTimeOut(2000);
        dp.assertEqual("111.apk", dp.getValueOf("appname"));
        //清除search框内容
    }

    @Test
    public void test060_deleteApp() {
        //单独删除app
        dp.clear("search");
        dp.waitByTimeOut(3000);
        dp.clickOn("app-delete");
        dp.waitByTimeOut(1000);
        dp.clickOn("remove-ok");
        dp.waitByTimeOut(3000);
    }

    @Test
    public void test070_uploadApp2() {
        uploadApp("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.apk");
        isUploadStatus();
        dp.waitByTimeOut(1000);
        try {
            new WebDriverWait(dp.driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@ng-click,'growlMessages.deleteMessage(message)')]")));
            dp.clickOn("type_error_prompt_click");
        } catch (Exception ee) {
        }
    }

    @Test
    public void test080_deleteAllApp() {
        removeApp();
    }

    @Test
    public void test090_uploadapp3() {
        dp.waitByTimeOut(4000);
        uploadApp("aaa.txt");
    }

    @Test
    public void test100_checkErrorPrompt() {
        closePrompt();
    }

    @Test
    public void test110_uploadapp4() {
        dp.waitByTimeOut(3000);
        uploadApp("baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.apk");
    }

    @Test
    public void test120_checkErrorPrompt() {
        closePrompt();
    }

    @Test
    public void test130_uploadapp5() {
        dp.waitByTimeOut(3000);
        uploadApp("com.ludashi.benchmark_7.8.0.16.0804_90.apk");
        isUploadStatus();
        dp.waitByTimeOut(3000);
    }

}

