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

public class Test_FC_appBasic extends Test_FC_app_core {
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
    public void test030_editApp() {
        app_editApp();
    }

    @Test
    public void test040_searchApp() {
        //search
        dp.sendKeys("search", "111");
        dp.waitByTimeOut(2000);
        dp.assertEqual("111.apk", dp.getValueOf("appname"));
        //清除search框内容
        dp.clear("search");
        dp.waitByTimeOut(3000);
    }

    @Test
    public void test050_deleteApp() {
        //单独删除app
        dp.waitByTimeOut(1000);
        dp.clickOn("app-delete");
        dp.waitByTimeOut(1000);
        dp.clickOn("remove-ok");
        dp.waitByTimeOut(3000);
    }
}


