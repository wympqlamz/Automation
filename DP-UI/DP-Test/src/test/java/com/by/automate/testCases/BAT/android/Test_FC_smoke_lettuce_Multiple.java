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

import com.by.automate.testCases.devicepass.user.workspaces.apps.Test_FC_app_core;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Test_FC_smoke_lettuce_Multiple extends Test_FC_app_core {
	@Test
	public void test010_check_group_no_records() {
		deleteAllGroups();
	}

	@Test
	public void test020_AddNewGroup() {
		AddNewGroup();
	}

	@Test
	public void test021_AddNewGroupSelectDevices() {
		GroupSelectDevices();
	}

	@Test
	public void test030_check_app_no_records() {
		 dp.clickOn("apps");
//		openApps();
		check_app_no_records();
	}

	@Test
	public void test031_uploadApp() {
		uploadApp();
	}


	@Test
	public void test041_run_Compatibility() {
		openApps();
		// 点击弹窗,并选择自动化类型
		dp.verifyIsShown("Start_automation");
		dp.waitByTimeOut(5000);
		dp.clickOn("Start_automation");
		dp.waitByTimeOut(1000);
		dp.clickOn("Lettuce");
		dp.waitByTimeOut(1000);
		dp.clickOn("UI_Automator_next");
		dp.waitByTimeOut(1000);
	}

	@Test
	public void test050_uploadAppScript() {
		// 上传
		uploadApp("/AppiumPythonScript/demo.zip");
		isUploadStatus();
		dp.waitByTimeOut(1000);
	}

	@Test
	public void test060_checkAppScript() {
		// 编辑
		dp.waitByTimeOut(1000);
		dp.assertEqual("demo.zip", dp.getValueOf("UI_Automator_uploadedname"));
		dp.waitByTimeOut(500);
		dp.clickOn("UI_Automator_uploadedname");
		dp.waitByTimeOut(500);
		dp.clickOn("Calabash_next2");
	}

	@Test
	public void test070_selectPhoneGroup() {
		app_selectPhoneGroup();
	}

	@Test
	public void test080_selectPhone() {
		app_selectPhone();
	}

	@Test
	public void test081_runtest() {
		dp.clickOn("Calabash_next3");
		dp.waitByTimeOut(1000);
		dp.clickOn("Calabash_run");
		dp.waitByTimeOut(1000);
		dp.clickOn("Calabash_Test Result");
	}

	@Test
	public void test090_checkReportResult() {
		checkReportstatus();
	}



	@Test
	public void test120_downloadReport() {
		downloadReport();
	}
	
	@Test
	public void test130_deletedReport() {
		deleteAllReport();
	}

}
