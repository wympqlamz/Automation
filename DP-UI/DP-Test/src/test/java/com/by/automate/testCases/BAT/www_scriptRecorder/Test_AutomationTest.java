package com.by.automate.testCases.BAT.www_scriptRecorder;

import com.by.automate.fwk.DPWebApp;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
maoyujia 2017-09-22

 */
public class Test_AutomationTest {
    private DPWebApp dp = null;

    @BeforeMethod
    public void setUp() {
        dp = new DPWebApp();
//		dp.getXMLDoc("workspaces","screenshots");
        dp.openApp("workspaces","apps","apps:Automation");

    }

    /*
    账号：maoyujia
    app文件：pingan
     */
    @Test(dataProvider = "test_pingAn")
    public void test010_start_PingAn(String email_user,String email_password,String appName,String platform,String testName,String scriptName) {
        dp.waitByTimeOut(2000);
        dp.driver.get("https://www.devicepass.com/auth/?ref=require_signin");
        dp.DP_Login(email_user,email_password);
        dp.waitForElementReadyOnTimeOut("apps",10000);
        dp.clickOn("apps");
        dp.waitForElementReadyOnTimeOut("Apps_AppName_title",10000);
        //选择app，开始appium自动化
        dp.start_automationTest_from_apps(appName,platform);
        dp.waitByTimeOut(1000);
        dp.clickOn("appiumPython_icon");
        dp.setValueTo("Apps_StartAutomation_TestName_Input",testName);
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_AppiumPython_NextButton1");
        dp.waitByTimeOut(2000);
        //选择要执行的脚本脚本
        dp.select_scripts(scriptName);
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_AppiumPython_NextButton2");
        //选择状态可用的运行设备,输入设备数量
        dp.automationSelectDevice_selectOneAvailableDevice(10);
        if(dp.waitForElementReadyOnTimeOut("Apps_StartAutomation_AppiumPython_NextButton3",10000)){
            dp.waitByTimeOut(2000);
            dp.clickOn("Apps_StartAutomation_AppiumPython_NextButton3");
        }
        dp.waitByTimeOut(2000);
        //设置最大运行时间
        dp.setValueTo("MaximumTestRunTime_input","30");
        dp.clickOn("Run_button");
        dp.waitByTimeOut(2000);
    }

    /*
    账号：
    app文件：神行太保
     */
    @Test(dataProvider = "test_taiBao")
    public void test020_start_TaiBao(String email_user,String email_password,String appName,String platform,String testName,String scriptName) {
        dp.waitByTimeOut(2000);
        dp.driver.get("https://www.devicepass.com/auth/?ref=require_signin");
        dp.DP_Login(email_user,email_password);
        dp.waitForElementReadyOnTimeOut("apps",10000);
        dp.clickOn("apps");
        dp.waitForElementReadyOnTimeOut("Apps_AppName_title",10000);
        //选择app，开始appium自动化
        dp.start_automationTest_from_apps(appName,platform);
        dp.waitByTimeOut(1000);
        dp.clickOn("appiumPython_icon");
        dp.setValueTo("Apps_StartAutomation_TestName_Input",testName);
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_AppiumPython_NextButton1");
        dp.waitByTimeOut(2000);
        //选择要执行的脚本脚本
        dp.select_scripts(scriptName);
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_AppiumPython_NextButton2");
        //选择状态可用的运行设备,输入设备数量
        dp.automationSelectDevice_selectOneAvailableDevice(20);
        if(dp.waitForElementReadyOnTimeOut("Apps_StartAutomation_AppiumPython_NextButton3",10000)){
            dp.waitByTimeOut(2000);
            dp.clickOn("Apps_StartAutomation_AppiumPython_NextButton3");
        }
        dp.waitByTimeOut(2000);
        //设置最大运行时间
        dp.setValueTo("MaximumTestRunTime_input","30");
        dp.clickOn("Run_button");
        dp.waitByTimeOut(2000);


    }

    /*
    账号：maoyujia
    app文件：招商银行掌上生活
     */
    @Test(dataProvider = "test_zhaoShang")
    public void test030_start_ZhaoShang(String email_user,String email_password,String appName,String platform,String testName,String scriptName) {
        dp.waitByTimeOut(2000);
        dp.driver.get("https://www.devicepass.com/auth/?ref=require_signin");
        dp.DP_Login(email_user,email_password);
        dp.waitForElementReadyOnTimeOut("apps",10000);
        dp.clickOn("apps");
        dp.waitForElementReadyOnTimeOut("Apps_AppName_title",10000);
        //选择app，开始appium自动化
        dp.start_automationTest_from_apps(appName,platform);
        dp.waitByTimeOut(1000);
        dp.clickOn("appiumPython_icon");
        dp.setValueTo("Apps_StartAutomation_TestName_Input",testName);
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_AppiumPython_NextButton1");
        dp.waitByTimeOut(2000);
        //选择要执行的脚本脚本
        dp.select_scripts(scriptName);
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_AppiumPython_NextButton2");
        //选择状态可用的运行设备,输入设备数量
        dp.automationSelectDevice_selectOneAvailableDevice(10);
        if(dp.waitForElementReadyOnTimeOut("Apps_StartAutomation_AppiumPython_NextButton3",10000)){
            dp.waitByTimeOut(2000);
            dp.clickOn("Apps_StartAutomation_AppiumPython_NextButton3");
        }
        dp.waitByTimeOut(2000);
        //设置最大运行时间
        dp.setValueTo("MaximumTestRunTime_input","30");
        dp.clickOn("Run_button");
        dp.waitByTimeOut(2000);
    }

    @DataProvider(name = "test_taiBao")
    public static Object[][] taiBaoNumbers() {
        return new Object[][] { {  "menglei@beyondsoft.com","1qaz2wsx","SIT神行太保", "0","神行太保-menglei","Python-SIT神行太保-小米4A (带手势beta2).zip" } };
    }

    @DataProvider(name = "test_pingAn")
    public static Object[][] pingAnNumbers() {
        return new Object[][] { { "maoyujia@beyondsoft.com","1qaz2wsx","平安好房", "0","平安好房-maoyujia","Python-平安好房-4.5.0-maoyujia.zip" } };
    }

    @DataProvider(name = "test_zhaoShang")
    public static Object[][] zhaoShangNumbers() {
        return new Object[][] { {"qinhuan@beyondsoft.com","1qaz2wsx", "掌上生活", "1","cmblife-6.0.0-Alisa","Alisa_Python-cmblife-6.0.0-0927.zip" } };
    }


    @AfterMethod
    public void tearDown() {
        dp.close();
    }

//    @Test
//    public void test010_startCompatibility() {
//        dp.clickOn("apps");
//        dp.waitForElementReadyOnTimeOut("Apps_AppName_title",10000);
//        dp.start_automationTest_from_apps("南方航空");
//        dp.setValueTo("Apps_StartAutomation_TestName_Input","安卓--兼容性--单设备");
//        dp.clickOn("Apps_StartAutomation_Compatibility_NextButton1");
//        dp.clickOn("Apps_StartAutomation_Compatibility_NextButton2");
//        dp.automationSelectDevice_selectOneAvailableDevice();
//
//
//
//    }
}
