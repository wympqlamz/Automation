package com.by.automate.testCases.devicepass.AutomationTestCase;

import com.by.automate.fwk.DPWebApp;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutomationTestCase1 {
    private DPWebApp dp = null;

    @BeforeClass
    public void setUp() {
        dp = new DPWebApp();
        dp.openApp("workspaces", "apps", "apps:Automation");
        dp.waitByTimeOut(2000);
        dp.DP_Login("maoyujia@beyondsoft.com", "123456");

    }

    @Test
    public void test010_runAutomatin_1() {
        dp.waitForElementReadyOnTimeOut("apps", 10000);
        dp.clickOn("apps");
        dp.waitByTimeOut(2000);
        dp.setValueTo("Apps_SearchInput","掌上生活");
        dp.waitByTimeOut(2000);
        dp.selectAppFromApps("掌上生活");
        dp.waitByTimeOut(2000);
        dp.clickOn("StartAutomationButton");
        dp.waitByTimeOut(2000);
        //检查testname输入
        String testname1 = dp.getValueOf("Apps_StartAutomation_TestName_Input");
        assert testname1.contains("测试2017");
        dp.waitByTimeOut(1000);
        dp.setValueTo("Apps_StartAutomation_TestName_Input","aaaaaaaaaaaa");
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_Default_TestName_Button");
        String testname2 = dp.getValueOf("Apps_StartAutomation_TestName_Input");
        assert testname2.equals(testname1);
        dp.waitByTimeOut(2000);

    }

    @AfterMethod
    public void tearDown(){
        dp.close();
    }

}
