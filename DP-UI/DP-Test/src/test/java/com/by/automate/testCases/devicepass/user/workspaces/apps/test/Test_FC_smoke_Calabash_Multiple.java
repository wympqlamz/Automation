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

public class Test_FC_smoke_Calabash_Multiple extends Test_FC_app_core {
	@Test
	public void test010_check_group_no_records() {
		deleteAllGroups();
	}

//	@Test
//	public void test020_AddNewGroup() {
//		AddNewGroup();
//	}

	@Test
	public void test021_AddNewGroupSelectDevices() {
//		GroupSelectDevices();
		allDevice_selectDevice();
		allDevice_groupDevice();
	}

	@Test
	public void test030_check_app_no_records() {
		// dp.clickOn("apps");
		openApps();
		check_app_no_records();
	}

	@Test
	public void test031_uploadApp() {
		uploadApp();
	}

	@Test
	public void test040_editApp() {
		app_editApp();
	}

	@Test
	public void test041_app_automation_Calabash() {
		openApps();
		// 点击弹窗,并选择自动化类型
		dp.verifyIsShown("Start_automation");
		dp.waitByTimeOut(5000);
		dp.clickOn("Start_automation");
		dp.waitByTimeOut(1000);
		dp.clickOn("Calabash");
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
		// dp.clear("Calabash_control_time");
		// dp.waitByTimeOut(500);
		// dp.sendKeys("Calabash_control_time","120");
		// dp.waitByTimeOut(500);
		dp.clickOn("Calabash_run");
		dp.waitByTimeOut(1000);
		dp.clickOn("Calabash_Test Result");
	}

	@Test
	public void test090_checkReportResult() {
		checkReportstatus();
	}

	@Test
	public void test100_checkGroupsPhoneStatus() {
		dp.waitByTimeOut(2000);
		dp.clickOn("allDevices");
//		openAllDevices();
		dp.driver.findElement(By.xpath("//span[text()='All Groups']")).click();
		// *[text()='Test Status']"/
		dp.waitByTimeOut(1000);
		dp.driver.findElement(By.xpath("//li[@ng-repeat='groups in groupDeviceList' and @class='ng-scope']")).click();
		dp.waitByTimeOut(10000);

		// check status
		List<WebElement> phone_status = dp.driver.findElements(
				By.xpath("//div[contains(@book-device,'bookDevice')]/span[contains(@class,'device-status')]"));
		int phone_status_size = Integer.valueOf(phone_status.size());
		System.err.println("本组手机总数量 is:" + phone_status_size);

		for (int i = 0; i < phone_status_size; i++) {
			String status = phone_status.get(i).getText();
			assert status.equals( "Available"): "there is unAvailable android phone, end test";
		}
		System.out.println("check Groups Phone Status OK");
	}

	@Test
	public void test110_deleteOneDetailReport() {
		dp.waitByTimeOut(3000);
		dp.clickOn("Test_Report");
		dp.waitByTimeOut(1000);

		// 点击小点点,跳出操作按钮
		new WebDriverWait(dp.driver, 5).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@ng-click,'showButton(row.testId,1)')]")));
		List<WebElement> reports = dp.driver
				.findElements(By.xpath("//div[contains(@ng-click,'showButton(row.testId,1)')]"));
		dp.waitByTimeOut(1000);
		reports.get(0).click();

		dp.waitByTimeOut(3000);
		dp.clickOn("reprot_Detail");
		dp.waitByTimeOut(3000);
		dp.driver.findElement(By.xpath("//input[@ng-checked='selectedDevicesIds[row.serial]']")).click();
		dp.waitByTimeOut(1000);
		dp.driver.findElement(By.xpath("//button[@ng-click='batchRemoveDeviceReport()']")).click();
		dp.waitByTimeOut(3000);
		dp.clickOn("reprot_remove_confirm");
		dp.waitByTimeOut(2000);
		
	}

	@Test
	public void test120_downloadReport() {
		downloadReport();
	}

}
