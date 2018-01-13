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
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Test_FC_group_Basic extends Test_FC_app_core {
    @Test
    public void test010_check_no_records() {
    	deleteAllGroups();
    }

    @Test
    public void test020_AddNewGroup() {
    	AddNewGroup();
    }

    @Test
    public void test030_editGroup() {
    	editGroup();
    }

    @Test
    public void test040_ManageSelectDevices() {
        GroupSelectDevices();
    }

    @Test
    public void test050_ManageUnselectDevices() {
        GroupUnselectDevices();
    }

//    @Test
//    public void test050_deletegroup() {
//        //单独删除app
//        dp.waitByTimeOut(1000);
//        dp.clickOn("Groups_deleteDevices");
//        dp.waitByTimeOut(1000);
//        dp.clickOn("remove-ok");
//        dp.waitByTimeOut(3000);
//    }











}


