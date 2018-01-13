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
package com.by.automate.testCases.devicepass.user.workspaces.apps.test;

import com.by.automate.fwk.DPWebApp;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(dependsOnGroups = { "setUp" })
public class Test_FC_test extends Test_FC_meng {

	@Test(groups = { "test00000_Login_www" })
	public void test00000_Login_www() {
			dp.waitByTimeOut(2000);
			dp.driver.get("https://www.devicepass.com");
			dp.waitByTimeOut(5000);
			dp.DP_Login("menglei@beyondsoft.com", "1qaz2wsx");
	}
	
	@Test(groups = { "test0000_Login_us" })
	public void test0000_Login_us() {
			dp.waitByTimeOut(2000);
			dp.driver.get("https://us.devicepass.com");
			dp.waitByTimeOut(5000);
			dp.DP_Login("qa@beyondsoft.com", "cqmygysdsS1234");
	}
	
	@Test(groups = { "test0000_Login_taier" })
	public void test0000_Login_taier() {
			dp.waitByTimeOut(2000);
			dp.driver.get("http://192.168.7.249");
			dp.waitByTimeOut(5000);
			dp.DP_Login("demo101@beyondsoft.com", "123456");
	}

	@Test(groups = { "test00000_Login_NoDelete" })
	public void test00000_Login_NoDelete() {
		dp.waitByTimeOut(5000);
		dp.DP_Login("menglei@beyondsoft.com", "123456");
	}

	@Test(groups = "test00000_Login_Delete")
	public void test00000_Login_Delete() {
		dp.DP_Login("menglei02@beyondsoft.com", "123456");
	}

	// ****************************************app(andriod和iphone)******************************************

	@Test(groups = { "test00010_openApps" })
	public void test00010_openApps() {
		openApps();
	}

	@Test(groups = "test00020_DeleteAllApp")
	public void test00020_DeleteAllApp() {
		check_app_no_records();
	}

	@Test(groups = { "test00030_app_uploadApp" })
	public void test00030_app_uploadApp() {
		dp.waitByTimeOut(3000);
		uploadApp();
		dp.waitByTimeOut(4000);
	}

	@Test(groups = { "test00040_app_uploadIpa" })
	public void test00040_app_uploadIpa() {
		dp.waitByTimeOut(3000);
		uploadApp("trainticket.ipa");
		isUploadStatus();
		dp.waitByTimeOut(4000);
	}

	@Test(groups = { "test00050_app_editApp" })
	public void test00050_app_editApp() {
		dp.waitByTimeOut(3000);
		app_editApp();
		dp.waitByTimeOut(3000);
	}

	@Test(groups = { "test00060_searchApp" })
	public void test00060_searchApp() {
		// search
		dp.waitByTimeOut(3000);
		dp.sendKeys("search", "e生财富");
		dp.waitByTimeOut(5000);
		assert dp.getValueOf("appname").contains("e生财富") : "app 界面 search 功能不可用";
		dp.clear("search");
		dp.waitByTimeOut(4000);
	}

	// ****************************************script(andriod和iphone)******************************************

	@Test(groups = "test00100_checkNoScript")
	public void test00100_checkNoScript() {
		checkAppNoScript();
	}

	@Test(groups = { "test00110_selectAppScriptType" })
	public void test00110_selectAppScriptType() {
		// 点击弹窗,并选择自动化类型
		dp.clickOn("script");
		dp.waitByTimeOut(3000);
		dp.clickOn("script_uploadBtn");
		dp.waitByTimeOut(2000);
	}

	@Test(groups = { "test00120_selectAppScriptType_python" })
	public void test00120_selectAppScriptType_python() {
		// 点击弹窗,并选择自动化类型
		Select sel = new Select(dp.driver.findElement(By.xpath("//select[@ng-change='changeFramework()']")));
		sel.selectByIndex(1);
		dp.waitByTimeOut(2000);
	}

	@Test(groups = { "test00130_uploadAppScript_python" })
	public void test00130_uploadAppScript_python() {
		// 上传
		uploadApp("/AppiumPythonScript/Demo.zip");
		dp.waitByTimeOut(3000);
		dp.clickOn("scriptUpload");
		dp.waitByTimeOut(500);
		isUploadStatus();
		dp.waitByTimeOut(4000);
	}
	
	@Test(groups = { "test00131_deleteAppScript" })
	public void test00131_deleteAppScript() {
		deleteAppSingleScript();
	}
	
	@Test(groups = { "test00132_ScriptMessagebox_assert_norecords" })
	public void test00132_ScriptMessagebox_assert_norecords() {
		ScriptMessagebox_assert_norecords();
	}
	

	@Test(groups = { "test00140_selectAppScriptType_UIAutomator" })
	public void test00140_selectAppScriptType_UIAutomator() {
		// 点击弹窗,并选择自动化类型
		dp.clickOn("script_uploadBtn");
		dp.waitByTimeOut(2000);
		Select sel = new Select(dp.driver.findElement(By.xpath("//select[@ng-change='changeFramework()']")));
		sel.selectByIndex(2);
		dp.waitByTimeOut(2000);
	}

	@Test(groups = { "test00150_uploadAppScript_UIAutomator" })
	public void test00150_uploadAppScript_UIAutomator() {
//		// 上传
		uploadApp("/UiAutomatorScript/Uiautomator-Test.jar");
		dp.waitByTimeOut(3000);
		dp.clickOn("scriptUpload");
		dp.waitByTimeOut(500);
		isUploadStatus();
		dp.waitByTimeOut(5000);
	}

	@Test(groups = { "test00160_editAppScript" })
	public void test00160_editAppScript() {
		// 编辑
		dp.waitByTimeOut(1000);
		dp.clickOn("script_scriptsBtn");
		dp.waitByTimeOut(1000);
		editAppScript();
		dp.waitByTimeOut(4000);
	}

	@Test(groups = { "test00170_deleteAppScript" })
	public void test00170_deleteAppScript() {
		deleteAppSingleScript();
	}

	@Test(groups = { "test00171_ScriptMessagebox_assert_norecords" })
	public void test00171_ScriptMessagebox_assert_norecords() {
		ScriptMessagebox_assert_norecords();
	}
	
	
	@Test(groups = { "test00180_closeScriptWindows" })
	public void test00180_closeScriptWindows() {
		dp.clickOn("script_close");
	}
	// ****************************************Automation******************************************

	@Test(groups = { "test00200_automation_python_upload" })
	public void test00200_automation_python_upload() {
		openApps();
		// 点击弹窗,并选择自动化类型
		dp.clickOn("Start_automation");
		dp.waitByTimeOut(1000);
		dp.clickOn("Appium_Python");
		dp.waitByTimeOut(1000);
		dp.clickOn("UI_Automator_next");
		dp.waitByTimeOut(1000);
		uploadApp("/AppiumPythonScript/demo.zip");
		isUploadStatus();
		dp.waitByTimeOut(4000);
	}

	@Test(groups = { "test00210_automation_checkAppScript_python" })
	public void test00210_automation_checkAppScript_python() {
		// 编辑
		dp.waitByTimeOut(1000);
		if ( !dp.getValueOf("UI_Automator_uploadedname").contains("demo")){
			System.err.println("上传的app名字不正确.");
		}
		dp.waitByTimeOut(5000);
		dp.clickOn("cancel");
		dp.waitByTimeOut(1000);
	}

	@Test(groups = { "test00220_automation_UIAutomator_upload" })
	public void test00220_automation_UIAutomator_upload() {
		// 点击弹窗,并选择自动化类型
		dp.clickOn("Start_automation");
		dp.waitByTimeOut(1000);
		dp.clickOn("UI_Automator");
		dp.waitByTimeOut(1000);
		dp.clickOn("UI_Automator_next");
		dp.waitByTimeOut(2000);

		// 编辑名称
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
		uploadApp("/UiAutomatorScript/Uiautomator-Test.jar");
		isUploadStatus();
		dp.waitByTimeOut(1000);
	}

	@Test(groups = { "test00230_automation_checkAppScript_UIAutomator" })
	public void test00230_automation_checkAppScript_UIAutomator() {
		// 编辑
		dp.waitByTimeOut(1000);
		if ( !dp.getValueOf("UI_Automator_uploadedname").contains("Uiautomator")){
			System.err.println("上传的app名字不正确.");
		}
		dp.waitByTimeOut(2000);
	}

	@Test(groups = { "test00240_closeAutomatortWindows" })
	public void test00240_closeAutomatortWindows() {
		dp.waitByTimeOut(2000);
		dp.clickOn("cancel");
		dp.waitByTimeOut(2000);
	}
	

	// *********************************current	// use界面************************************

	@Test(groups = { "test00300_allDevice_selectDevice_andriod"})
	public void test00300_allDevice_selectDevice_andriod() {
		openAllDevices();
		allDevice_selectphone("andriod");
		allDevice_controlDevice();
	}
	
	@Test(groups = { "test00300_allDevice_selectDevice_iphone" })
	public void test00300_allDevice_selectDevice_iphone() {
		openAllDevices();
		allDevice_selectphone("iPhone");
		allDevice_controlDevice();
	}


	@Test(groups = { "test00310_CurrentUse_uploadApp" })
	public void test00310_CurrentUse_uploadApp() {
		dp.waitByTimeOut(2000);
		uploadApp();
		dp.waitByTimeOut(4000);
	}
	
	@Test(groups = { "test00310_CurrentUse_uploadpac" })
	public void test00310_CurrentUse_uploadpac() {
		dp.waitByTimeOut(2000);
		uploadApp("trainticket.ipa");
		isUploadStatus();
		dp.waitByTimeOut(4000);
		// }
	}

	@Test(groups = { "test00320_CurrentUse_editApp" })
	public void test00320_CurrentUse_editApp() {
		currentUse_editApp();
		dp.waitByTimeOut(4000);
	}

	@Test(groups = { "test00330_currentUse_AppiumPython_scriptupload" })
	public void test00330_AppiumPython_scriptupload() {
		currentUse_AppiumPython_scriptupload();
		dp.waitByTimeOut(4000);
	}
	
	@Test(groups = { "test00340_CurrentUse_editAppiumPythonScript" })
	public void test00340_CurrentUse_editAppiumPythonScript() {
		editAppScript();
		dp.waitByTimeOut(4000);
	}
	
	@Test(groups = { "test00341_deleteAppScript" })
	public void test00341_deleteAppScript() {
		// 删除
		dp.clickOn("script-remove");
		dp.waitByTimeOut(1000);
		dp.clickOn("script-remove-confirm");
		dp.waitByTimeOut(2000);

		// 检查no record
		dp.waitByTimeOut(2000);
		String noscript = dp.getValueOf("script_norecords");
		System.out.println("noscript is" + noscript);
		assert noscript.contains("No records") : "app upload no script,check no records error";
	}
	
	@Test(groups = { "test00350_currentUse_Uiautomator_scriptupload" })
	public void test00350_currentUse_Uiautomator_scriptupload() {
		currentUse_Uiautomator_scriptupload();
		dp.waitByTimeOut(4000);
	}
	
	@Test(groups = { "test00360_currentUse_editUiautomatorScript" })
	public void test00360_currentUse_editUiautomatorScript() {
		editAppScript();
		dp.waitByTimeOut(4000);
	}
	
	@Test(groups = { "test00370_deleteAppScript" })
	public void test00370_deleteAppScript() {
		// 删除
		dp.clickOn("script-remove");
		dp.waitByTimeOut(1000);
		dp.clickOn("script-remove-confirm");
		dp.waitByTimeOut(2000);

		// 检查no record
		dp.waitByTimeOut(2000);
		String noscript = dp.getValueOf("script_norecords");
		System.out.println("noscript is" + noscript);
		assert noscript.contains("No records") : "app upload no script,check no records error";
	}
	
	@Test(groups = { "test00380_currentUse_script_close" })
	public void test00380_script_close() {
		dp.waitByTimeOut(4000);
		currentUse_script_close();
	}
	
//	*******current use 界面  ：automation
	@Test(groups = { "test00410_CurrentUse_automation_Script_AppiumPython" })
	public void test00410_CurrentUse_automation_Script_AppiumPython() {
		// 点击弹窗,并选择自动化类型
		dp.waitByTimeOut(4000);
		dp.clickOn("currentUse_Start_automation");
		dp.waitByTimeOut(1000);
		dp.clickOn("currentUse_Appium_Python");
		dp.waitByTimeOut(1000);
		dp.clickOn("currentUse_UI_Automator_next");
		dp.waitByTimeOut(1000);
	}

	@Test(groups = { "test00420_CurrentUse_automation_uploadScript_AppiumPython" })
	public void test00420_CurrentUse_automation_uploadScript_AppiumPython() {
		uploadApp("/AppiumPythonScript/demotest.zip");
		isUploadStatus();
		dp.waitByTimeOut(4000);
		// }
	}


	
	
	@Test(groups = { "test00430_currentUse_automation_AppiumPython_cancel" })
	public void test00430_AppiumPython_cancel() {
		cancel();
	}

	@Test(groups = { "test00440_currentUse_automation_Script_UIAutomator" })
	public void test00440_currentUse_automation_Script_UIAutomator() {
		// 点击弹窗,并选择自动化类型
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

	@Test(groups = { "test00450_currentUse_automation_uploadScript_AppiumPython" })
	public void test00450_currentUse_automation_uploadScript_AppiumPython() {
		// for (int i = 0; i < 12; i++) {
		uploadApp("/UiAutomatorScript/Uiautomator-Test.jar");
		isUploadStatus();
		// if (i == 0) {
//		dp.assertEqual("Uiautomator-Test.jar", dp.getValueOf("currentUse_UI_Automator_uploadedname"));
//		// }
		dp.waitByTimeOut(5000);
		uploadApp("/UiAutomatorScript/Uiautomator.jar");
		isUploadStatus();
		dp.waitByTimeOut(4000);
		// }
	}

	@Test(groups = { "test00460_currentUse_automation_UIAutomator_cancel" })
	public void test00460_currentUse_automation_UIAutomator_cancel() {
		cancel();
		dp.waitByTimeOut(2000);
	}

	
	// *********************************groups界面************************************

	
	@Test(groups = { "openGroups" })
	public void test00400_openGroups() {
		openGroups();
	}
	
	
	@Test(groups = { "Groups_AddNewGroup" })
	public void test00410_Groups_AddNewGroup() {
		AddNewGroup();
	}
	


	@Test(groups = { "Groups_editGroup" })
	public void test00420_Groups_editGroup() {
		editGroup();
	}

	@Test(groups = { "Groups_SelectDevices" })
	public void test00430_Groups_SelectDevices() {
		GroupSelectDevices();
	}
	
	@Test(groups = { "Groups_UnselectDevices" })
	public void test00440_Groups_UnselectDevices() {
		GroupUnselectDevices();
	}
	
	@Test(groups = { "Groups_deleteSingleGroup" })
	public void test00450_Groups_deleteSingleGroup() {
		deleteSingleGroup();
	}

	@Test(groups = { "Groups_deleteAllGroups" })
	public void test00460_Groups_deleteAllGroups() {
		deleteAllGroups();
	}
	
	
	
//*******************************最后收尾工作************
	@Test(groups = { "test90000_releaseAll" })
	public void test90000_releaseAll() {
		releaseAll();
		dp.waitByTimeOut(3000);
	}

	@Test(groups = { "test91000_removeApp" })
	public void test91000_removeApp() {
		openApps();
		removeApp();
		check_app_no_records();
		dp.waitByTimeOut(4000);
	}

	@Test(groups = { "test99999_tearDown" })
	public void test99999_tearDown() {
		dp.close();
	}

}
