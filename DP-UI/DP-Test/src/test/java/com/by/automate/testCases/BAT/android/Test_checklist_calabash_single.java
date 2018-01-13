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

import com.by.automate.fwk.DPWebApp;
import com.by.automate.testCases.devicepass.user.workspaces.apps.Test_FC_app_core;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Test_checklist_calabash_single {
    private DPWebApp dp = null;

    @BeforeClass
    public void setUp() {
        dp = new DPWebApp();
        dp.openApp("workspaces", "apps", "apps:Automation");
        dp.waitByTimeOut(2000);
        dp.DP_Login("maoyujia@beyondsoft.com", "123456");

    }

	@Test
	public void test010_runCalabashSingle() {
        dp.waitForElementReadyOnTimeOut("apps", 10000);
        dp.clickOn("apps");
        dp.waitByTimeOut(2000);
        dp.setValueTo("Apps_SearchInput","掌上生活");

        dp.waitByTimeOut(2000);
        dp.selectAppFromApps("掌上生活");
        dp.waitByTimeOut(2000);
        dp.clickOn("StartAutomationButton");
        dp.waitByTimeOut(2000);
        dp.clickOn("Calabash_icon");
        dp.setValueTo("Apps_StartAutomation_TestName_Input","Calabash单设备");
        dp.waitByTimeOut(2000);
        dp.clickOn("Apps_StartAutomation_Calabash_SelectTestMode_NextButton");
        dp.waitByTimeOut(2000);
        dp.select_scripts("maryKey.zip");
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_Calabash_SelectScript_NextButton");
        dp.automationSelectDevice_selectOneAvailableDevice(1); //选择一台可用设备
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_Calabash_SelectDevice_NextButton");
        dp.waitByTimeOut(2000);
        dp.clickOn("Run_button");
        dp.waitByTimeOut(2000);
        dp.clickOn("close_button");
        dp.waitByTimeOut(2000);
	}

    @Test
    public void test020_runLettuceSingle() {
        dp.waitForElementReadyOnTimeOut("apps", 10000);
        dp.clickOn("apps");
        dp.waitByTimeOut(2000);
        dp.setValueTo("Apps_SearchInput","WeChat");
        dp.waitByTimeOut(2000);
        dp.selectAppFromApps("WeChat");
        dp.waitByTimeOut(2000);
        dp.clickOn("StartAutomationButton");
        dp.waitByTimeOut(2000);
        dp.clickOn("Lettuce_icon");
        dp.setValueTo("Apps_StartAutomation_TestName_Input","Lettuce单设备");
        dp.waitByTimeOut(2000);
        dp.clickOn("Apps_StartAutomation_Lettuce_SelectTestMode_NextButton");
        dp.waitByTimeOut(2000);
        dp.select_scripts("features_lettuce.zip");
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_Lettuce_SelectScript_NextButton");
        dp.automationSelectDevice_selectOneAvailableDevice(1); //选择一台可用设备
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_Lettuce_SelectDevice_NextButton");
        dp.waitByTimeOut(2000);
        dp.clickOn("Run_button");
        dp.waitByTimeOut(2000);
        dp.clickOn("close_button");
        dp.waitByTimeOut(2000);
    }

    @Test
    public void test030_runUiautomatorSingle() {
        //控屏发起
        dp.waitForElementReadyOnTimeOut("devices", 10000);
        dp.clickOn("devices");
        dp.waitByTimeOut(3000);
        List<WebElement> phone_items  = dp.getElements("phone_item_icon");

        System.out.println(phone_items);
        for(WebElement i : phone_items){
            System.out.println(i.getText());
            if (i.findElement(By.tagName("span")).getAttribute("class").contains("Available") && !i.getText().contains("Apple")){
                i.click();
                break;
            }
        }
        dp.clickOn("controlButton");
        dp.waitByTimeOut(2000);
        dp.clickOn("controlButton_confirm");
        dp.waitByTimeOut(2000);
        dp.clickOn("Control_Apps");

        dp.setValueTo("Apps_SearchInput","WeChat");
        dp.waitByTimeOut(2000);
        dp.selectAppFromApps("WeChat");
        dp.waitByTimeOut(2000);
        dp.clickOn("StartAutomationButton");
        dp.waitByTimeOut(2000);
        dp.clickOn("UIAutomator_icon");
        dp.setValueTo("Apps_StartAutomation_TestName_Input","UIAutomator单设备");
        dp.waitByTimeOut(2000);
        dp.clickOn("Apps_StartAutomation_Uiautomator_SelectTestMode_NextButton");
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_Uiautomator_Configuration_NextButton");
        dp.waitByTimeOut(2000);
        dp.select_scripts("Uiautomator-Test(1).jar");
        dp.clickOn("Apps_StartAutomation_Uiautomator_SelectScript_NextButton");
        dp.waitByTimeOut(2000);
        dp.clickOn("Run_button");
        dp.waitByTimeOut(2000);
        dp.clickOn("close_button");
    }

    @Test
    public void test040_runAppiumPythonSingle(){
        dp.waitForElementReadyOnTimeOut("apps", 10000);
        dp.clickOn("apps");
        dp.waitByTimeOut(2000);
        dp.setValueTo("Apps_SearchInput","掌上生活");
        dp.waitByTimeOut(2000);
        dp.selectAppFromApps("掌上生活");
        dp.waitByTimeOut(2000);
        dp.clickOn("StartAutomationButton");
        dp.waitByTimeOut(2000);
        dp.clickOn("AppiumPython_icon");
        dp.setValueTo("Apps_StartAutomation_TestName_Input","AppiumPython单设备");
        dp.waitByTimeOut(2000);
        dp.clickOn("Apps_StartAutomation_AppiumPython_SelectTestMode_NextButton");
        dp.waitByTimeOut(2000);
        dp.select_scripts("maryKey.zip");
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_AppiumPython_SelectScript_NextButton");
        dp.automationSelectDevice_selectOneAvailableDevice(1); //选择一台可用设备
        dp.waitByTimeOut(1000);
        dp.clickOn("Apps_StartAutomation_Calabash_NextButton3");
        dp.waitByTimeOut(2000);
        dp.clickOn("Run_button");
        dp.waitByTimeOut(2000);
        dp.clickOn("close_button");
        dp.waitByTimeOut(2000);


    }





}
