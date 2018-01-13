/*Apps************************
 *  openApps();               读取DPUi_apps.xml文件，获取apps视图，并点击进入app页面。
 *  check_app_no_records();	     清空已上传app、ipa，并检查no record信息。
 *  uploadApp();			      上传制定app文件：ep2p.apk。
 *  uploadApp(String appname) 上传指定名称app文件。 须将文件放在data/App/android_apk/下。
 *  isUploadStatus();		      监视app文件上传进度，如果超过10分钟，则终止。
 *  app_editApp();			      在app界面中，编辑app信息。
 *  removeApp();			      清空所有已上传app。
 *  checkAppNoScript();		       检查appScript（自动化脚本）的no record信息。
 *  editAppScript();		       编辑appScript（自动化脚本）信息。
 *  closePrompt();			       关闭右上角弹出通知信息，如剩余时间、各种错误等。
 *  checkversion();			       读取当前版本信息。
 *  app_selectPhoneGroup();	       从app界面进入自动化测试（兼容性，calabash等），进入选择设备界面后，如果同时存在多个group，会选择有存在可有手机的group（第一个gorup没有可用手机，会选择第二个）。
 *  app_selectPhone();		       从app界面进入自动化测试（兼容性，calabash等），进入选择设备界面选定group后，选择随机数量可用手机。（必须先用app_selectPhoneGroup）
*/

/*Test Report******************
 *  checkReportstatus();		持续从界面最上方Test Status检查自动化脚本运行状态，直到完成测试，生成报告，如果超过30分钟，则终止。
 *  downloadReport();			持续检查Test report界面最新的报告，检查下载按钮，直到出现为止， 然后点击下载，如果超过30分钟，则终止。（只下载一个报告）
 *  deleteAllReport();			持续检查Test report界面最新的报告，检查删除按钮，直到出现为止， 然后点击删除，如果超过30分钟，则终止。（只删除一个报告）
*/

/*Screenshots******************

*/

/*current Use******************
 * 好多模块可以和apps界面的复用。 具体可参考我的用例，不再重复说明。
 * currentUse_editApp();		currentUse界面对app信息进行编辑

*/

/*All Devices******************
 * 	openAllDevices();			读取DPUi_apps.xml文件，获取apps视图，并点击进入AllDevices页面。
 *  allDevice_selectDevice();	随机选择选择2-10个状态为available的andriod设备。
 *  allDevice_controlDevice(); 	对已选中设备，进行control。（必须先用allDevice_selectDevice）
 *  allDevice_groupDevice();	对已选中设备，建立group。（必须先用allDevice_selectDevice）
 *  releaseAll();				释放所有已控手机。

*/

/*Groups***********************
 * openGroups();				读取DPUi_apps.xml文件，获取apps视图，并点击进入Groups页面。
 * AddNewGroup();				创建group。
 * editGroup();					编辑group信息。
 * GroupSelectDevices();		对group添加随机数量手机。
 * GroupUnselectDevices();		随机数量删除group已添加手机（至少剩余1台）。
 * deleteAllGroups();			删除所有group。

*/
package com.by.automate.testCases.devicepass.user.workspaces.apps.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Calendar;
import java.util.Collections;

public class Test_FC_meng extends Test_FC_core {

	public void openApps() {
		dp.waitByTimeOut(1000);
		dp.putStep("DP0106001");
		dp.getXMLDoc("workspaces", "apps");
		dp.waitByTimeOut(10000);
		dp.setViewTo("appspage:apps");
		// dp.verifyIsShown("apps");
		dp.clickOn("apps");
		dp.waitByTimeOut(5000);
	}

	public void openAllDevices() {
		// dp.putStep("DP0106001");
		dp.getXMLDoc("workspaces", "apps");
		dp.waitByTimeOut(10000);
		dp.setViewTo("appspage:apps");
		// dp.verifyIsShown("allDevices");
		dp.clickOn("allDevices");
		dp.waitByTimeOut(4000);
	}

	public void openGroups() {
		dp.putStep("DP0106001");
		dp.getXMLDoc("workspaces", "apps");
		dp.setViewTo("appspage:apps");
		dp.waitByTimeOut(10000);
		dp.verifyIsShown("Groups");
		dp.clickOn("Groups");
		dp.waitByTimeOut(4000);
	}

	// private int getScreenShotCount(String value) {
	// // (5 / 5 pictures )
	// value = StringUtils.substringBetween(value, "(", ")").trim();
	// value = value.replace("pictures", "").replace("picture", "").trim();
	// String[] str = value.split("/");
	// return Integer.parseInt(str[1].trim());
	// }
	//

	/*
	 * *************************************************************************
	 * ***************************Apps******************************************
	 * **********************************************************
	 */

	public void check_app_no_records() {
		// 清空已上传app
		while (true) {
			try {
				// WebDriverWait
				dp.waitByTimeOut(4000);
				new WebDriverWait(dp.driver, 8).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//input[contains(@ng-click,'toggleSelectAll()')]")));
				// WebDriverWait driverWait = (WebDriverWait) new
				// WebDriverWait(dp.driver, 5);
				// driverWait.until(new
				// ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@ng-click,'toggleSelectAll()')]"));
				dp.clickOn("selectedAll");
				dp.clickOn("remove");
				dp.waitByTimeOut(500);
				dp.clickOn("remove-cancel");
				dp.waitByTimeOut(500);
				dp.clickOn("remove");
				dp.waitByTimeOut(500);
				dp.clickOn("remove-ok");
			} catch (Exception e) {
				dp.log("no apps...");
				break;
			}
		}
		// 检查no_records
		dp.waitByTimeOut(1000);
		try {
			dp.verifyIsShown("currentUse_apps_norecords");
			dp.log("no_record prompt is correct");
		} catch (Exception e) {
			dp.log("no_record prompt is incorrect", 3);
		}
	}

	public void uploadApp() {
		dp.uploadApp();
		dp.waitByTimeOut(500);
		isUploadStatus();
	}

	public void uploadApp(String appname) {
		String currentpath_temp = System.getProperty("user.dir");
		String currentpath = currentpath_temp.replaceAll("\\\\", "/");
		// System.out.println("currentpath is......"+currentpath);
		String apppath = (currentpath + "/src/test/resources/data/App/android_apk/" + appname);
		// System.out.println("apppath is......"+apppath);
		try {
			dp.sendKeys("UploadFile", apppath);
		} catch (Exception e) {
			try {
				dp.sendKeys("currentUse_scriptSelectFile", apppath);
			} catch (Exception ee) {
				try {
					dp.sendKeys("scriptSelectFile", apppath);
				} catch (Exception eee) {
					try {
						dp.sendKeys("upload", apppath);
					} catch (Exception eeee) {
					}
				}
			}
		}
	}

	public void isUploadStatus() {
		dp.verifyIsShown("progressBar");
		String percent = dp.getValueOf("progressBar");

		// 时间判断
		Calendar now = Calendar.getInstance();
		Calendar target = Calendar.getInstance();
		target.add(Calendar.MINUTE, 10);

		while (!(percent.equals("100%"))) {
			dp.waitByTimeOut(1000);
			// 时间判断
			if (now.after(target)) {
				break;
			}
			now = Calendar.getInstance();
			percent = dp.getValueOf("progressBar");
			dp.log("Current Percent : " + percent);
		}
		dp.log("Upload App finish...");
	}

	public void app_editApp() {
		dp.waitByTimeOut(3000);
		dp.clickOn("editApp");
		dp.waitByTimeOut(2000);
		dp.clear("Apprename");
		dp.waitByTimeOut(2000);
		String list0[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
				" ", " ", " ", " ", " ", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "三", "剑", "客",
				"雷", "锋", "精", "神", "卡", "拉", "法", "艰", "苦", "拉", "萨", "积", "分", "刷", "卡", "单", "据", "反", "抗", "拉", "萨",
				"积", "分", "卡", "阿", "桑", "杰", "看", "来", "飞", "机", "阿", "拉", "山", "口", "附", "近", "阿", "斯", "利", "康", "附",
				"近", "阿", "斯", "科", "利", "积", "分", "阿", "喀", "琉", "斯", "家", "反", "抗", "拉", "萨", "积", "分", "拉", "丝", "机",
				"积", "分", "看", "来", "`", "~", "！", "@", "#", "￥", "%", "…", "&", "*", "（", "）", "—", "-", "+", "=",
				" " };

		for (int i = 0; i < 40; i++) {
			int a = random.nextInt(list0.length);
			dp.sendKeys("Apprename", list0[a]);
		}
		dp.sendKeys("Apprename", "！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		dp.waitByTimeOut(2000);

		for (int i = 0; i < 30; i++) {
			int aa = random.nextInt(list0.length);
			dp.sendKeys("Appremark", list0[aa]);
		}
		dp.sendKeys("Appremark", "！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		dp.waitByTimeOut(2000);
		dp.clickOn("submit");
		dp.waitByTimeOut(4000);
	}

	public void removeApp() {
		// 清空已上传app
		while (true) {
			try {
				// WebDriverWait
				dp.waitByTimeOut(2000);
				new WebDriverWait(dp.driver, 3).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//input[contains(@ng-click,'toggleSelectAll()')]")));
				// WebDriverWait driverWait = (WebDriverWait) new
				// WebDriverWait(dp.driver, 5);
				// driverWait.until(new
				// ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@ng-click,'toggleSelectAll()')]"));
				dp.clickOn("selectedAll");
				dp.clickOn("remove");
				dp.waitByTimeOut(500);
				dp.clickOn("remove-cancel");
				dp.waitByTimeOut(500);
				dp.clickOn("remove");
				dp.waitByTimeOut(500);
				dp.clickOn("remove-ok");
			} catch (Exception e) {
				dp.log("no apps...");
				break;
			}
		}
	}

	public void checkAppNoScript() {
		dp.waitByTimeOut(3000);
		dp.verifyIsShown("script");
		String script = dp.getElementText("script");
		assert script.contains("no scripts") : "app upload no script,check error";
	}

	public void editAppScript() {
		// 编辑
		dp.waitByTimeOut(2000);
		dp.clickOn("script_edit");
		dp.waitByTimeOut(2000);
		dp.clear("script_rename");
		dp.waitByTimeOut(2000);

		String list0[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
				" ", " ", " ", " ", " ", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "三", "剑", "客",
				"雷", "锋", "精", "神", "卡", "拉", "法", "艰", "苦", "拉", "萨", "积", "分", "刷", "卡", "单", "据", "反", "抗", "拉", "萨",
				"积", "分", "卡", "阿", "桑", "杰", "看", "来", "飞", "机", "阿", "拉", "山", "口", "附", "近", "阿", "斯", "利", "康", "附",
				"近", "阿", "斯", "科", "利", "积", "分", "阿", "喀", "琉", "斯", "家", "反", "抗", "拉", "萨", "积", "分", "拉", "丝", "机",
				"积", "分", "看", "来", "`", "~", "！", "@", "#", "￥", "%", "…", "&", "*", "（", "）", "—", "-", "+", "=",
				" " };

		for (int i = 0; i < 40; i++) {
			int a = random.nextInt(list0.length);
			dp.sendKeys("script_rename", list0[a]);
		}
		dp.sendKeys("script_rename", "！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		dp.waitByTimeOut(2000);

		for (int i = 0; i < 30; i++) {
			int aa = random.nextInt(list0.length);
			dp.sendKeys("script_remark", list0[aa]);
		}
		dp.sendKeys("script_remark", "！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		dp.waitByTimeOut(2000);

		dp.clickOn("script_submit");
	}

	public void closePrompt() {
		dp.waitByTimeOut(2000);
		while (true) {
			try {
				dp.waitByTimeOut(1000);
				new WebDriverWait(dp.driver, 3).until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//button[contains(@ng-click,'growlMessages.deleteMessage(message)')]")));
				dp.clickOn("type_error_prompt_click");
			} catch (Exception e) {
				dp.log("no message for prompt ...");
				break;
			}
		}
	}

	public void cancel() {
		dp.waitByTimeOut(5000);
		dp.clickOn("cancel");
		dp.waitByTimeOut(1000);
	}

	public void checkversion() {
		// 验证头部信息
		dp.waitByTimeOut(1000);
		new WebDriverWait(dp.driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Help']"))).click();
		dp.driver.findElement(By.xpath("//*[text()='Release Notes']")).click();
		dp.waitByTimeOut(3000);
		String CurrentVersion = dp.driver
				.findElement(By.xpath("//span[contains(@class,'current-version-device ng-binding')]")).getText();
		// System.err.println("Device Pass Current Version is:
		// "+CurrentVersion);
		dp.waitByTimeOut(3000);
		dp.driver.findElement(By.xpath("//button[contains(@ng-click,'cancel()')]")).click();
		dp.waitByTimeOut(2000);
	}

	public void app_selectPhoneGroup() {
		// 从app界面
		dp.waitByTimeOut(1000);
		List<WebElement> avaliblephone = dp.driver
				.findElements(By.xpath("//div[contains(@ng-click,'toggleDeviceGroup(group)')]/span[2]"));
		int groupnum = Integer.valueOf(avaliblephone.size());
		System.err.println("共有: \"" + groupnum + "\" 个设备组");
		for (int i = 0; i < groupnum; i++) {
			String phonenum_temp = avaliblephone.get(i).getText();
			String phonenum = phonenum_temp.replace("(", "").replace(")", "");
			System.err.println("目前查找第: \"" + (i + 1) + "\" 个设备组");
			System.err.println("可用手机数量为: " + phonenum);

			int phonenum1 = Integer.valueOf(phonenum);
			if (phonenum1 != 0) {
				avaliblephone.get(i).click();
				dp.waitByTimeOut(1000);
				break;
			}
			assert i != (groupnum - 1) : "没有可用的手机, 终止测试 ";
		}
	}

	public void app_selectPhone() {
		// 编辑
		dp.waitByTimeOut(1000);
		List<WebElement> phone = dp.driver.findElements(By.xpath(
				"//div[contains(@ng-repeat,'device in selectedGroupDevices')]/i[contains(@ng-if,'device.status')]"));
		int phonenum = Integer.valueOf(phone.size());
		System.err.println("共有: \"" + phonenum + "\" 部手机");

		List<WebElement> avaliblephonename = dp.driver.findElements(By.xpath(
				"//div[contains(@ng-repeat,'device in selectedGroupDevices')]/span[contains(@class,'padding-left-5 ng-binding')]"));

		// 删除不可用的
		int ii = 0;
		for (int i = 0; i < phonenum; i++) {
			dp.waitByTimeOut(500);
			String phonestatus = phone.get(i).getAttribute("ng-if");
			// System.err.println(i+"\" 部手机,状态是"+phonestatus);
			if (!phonestatus.contains("Available")) {
				avaliblephonename.remove(i - ii);
				// System.err.println(i+"remove: ");
				// System.err.println("ii: \""+ii);
				ii++;
			}
			dp.waitByTimeOut(500);
		}

		// 可用手机数量,随机点击可用的
		int avaliblephonenum = Integer.valueOf(avaliblephonename.size());
		System.err.println("共有: \"" + avaliblephonenum + "\" 部可用手机");
		// Random random = new Random();
		int randomnum = random.nextInt(avaliblephonenum);
		while (randomnum == 0) {
			randomnum = random.nextInt(avaliblephonenum);
		}
		System.err.println("共选择选择: \"" + (randomnum) + "\" 部手机");
		for (int i = 0; i < randomnum; i++) {
			avaliblephonename.get(i).click();
			String name = avaliblephonename.get(i).getText();
			System.err.println("目前选择第: \"" + (i + 1) + "\" 部手机, 型号为: " + name);
			dp.waitByTimeOut(500);
		}
	}

	
	public void deleteAppSingleScript() {
		// 删除
		dp.clickOn("script-remove");
		dp.waitByTimeOut(1000);
		dp.clickOn("script-remove-confirm");
		dp.waitByTimeOut(3000);
	}
	
	
	public void ScriptMessagebox_assert_norecords() {
		// 检查no record
		dp.waitByTimeOut(2000);
		String noscript = dp.getValueOf("script_norecords");
		System.out.println("noscript is" + noscript);
		assert noscript.contains("No records") : "app upload no script,check no records error";
	}
	
	
	/*
	 * *************************************************************************
	 * ***************************Reports***************************************
	 * *************************************************************
	 */

	public void checkReportstatus() {
		// 时间判断
		Calendar now = Calendar.getInstance();
		Calendar target = Calendar.getInstance();
		target.add(Calendar.MINUTE, 30);

		while (true) {
			try {
				dp.waitByTimeOut(8000);
				closePrompt();
				dp.waitByTimeOut(1000);
				if (now.after(target)) {
					break;
				}
				dp.clickOn("TestStatus");
				dp.waitByTimeOut(500);
				dp.driver.findElement(By.xpath("//a[contains(@uib-tooltip,'device(s) is running')]"));
				dp.waitByTimeOut(500);
				dp.clickOn("TestStatus");
				dp.waitByTimeOut(20000);
			} catch (Exception e) {
				break;
			}
		}
	}

	public void downloadReport() {
		dp.waitByTimeOut(4000);
		dp.clickOn("Test_Report");
		dp.waitByTimeOut(3000);

		// 时间判断
		Calendar now = Calendar.getInstance();
		Calendar target = Calendar.getInstance();
		target.add(Calendar.MINUTE, 30);

		while (true) {
			try {
				dp.waitByTimeOut(2000);
				if (now.after(target)) {
					break;
				}
				now = Calendar.getInstance();

				// 点击小点点,跳出操作按钮
				new WebDriverWait(dp.driver, 3).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[contains(@ng-click,'showButton(row.testId,1)')]")));
				List<WebElement> reports = dp.driver
						.findElements(By.xpath("//div[contains(@ng-click,'showButton(row.testId,1)')]"));
				dp.waitByTimeOut(1000);
				reports.get(0).click();
				System.out.println("find 展开 按钮，并click.");

				// 查找下载按钮
				new WebDriverWait(dp.driver, 3).until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[contains(@ng-click,'downLoadTestReportDetail(row.testId)')]")));
				dp.waitByTimeOut(2000);
				dp.clickOn("reprot_download");
				dp.waitByTimeOut(5000);
				dp.clickOn("reprot_closebutton");
				dp.waitByTimeOut(2000);
				break;
			} catch (Exception e) {
				dp.clickOn("reprot_closebutton");
				dp.waitByTimeOut(30000);
			}
		}

	}
	
	public void deleteOneDetailReport() {
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

	public void deleteAllReport() {
		dp.waitByTimeOut(1000);
		dp.clickOn("Test_Report");
		dp.waitByTimeOut(3000);

		// 时间判断
		Calendar now = Calendar.getInstance();
		Calendar target = Calendar.getInstance();
		target.add(Calendar.MINUTE, 30);

		while (true) {
			try {
				dp.waitByTimeOut(2000);
				if (now.after(target)) {
					break;
				}
				now = Calendar.getInstance();

				// 点击小点点,跳出操作按钮
				new WebDriverWait(dp.driver, 3).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[contains(@ng-click,'showButton(row.testId,1)')]")));
				List<WebElement> reports = dp.driver
						.findElements(By.xpath("//div[contains(@ng-click,'showButton(row.testId,1)')]"));
				dp.waitByTimeOut(1000);
				reports.get(0).click();

				// 删除报告
				dp.waitByTimeOut(1000);
				dp.clickOn("reprot_remove");
				dp.waitByTimeOut(1000);
				dp.clickOn("reprot_remove_confirm");
				dp.waitByTimeOut(1000);
			} catch (Exception e) {
				// TODO: handle exception
				dp.log("no report now...");
				break;
			}
		}
	}

	/*
	 * *************************************************************************
	 * ***************************
	 * currentUse***************************************************************
	 * *************************************
	 */

	public void currentUse_editApp() {
		dp.waitByTimeOut(3000);
		dp.clickOn("currentUse_editApp");
		dp.waitByTimeOut(2000);
		dp.clear("currentUse_rename");
		dp.waitByTimeOut(2000);
		String list0[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
				" ", " ", " ", " ", " ", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "三", "剑", "客",
				"雷", "锋", "精", "神", "卡", "拉", "法", "艰", "苦", "拉", "萨", "积", "分", "刷", "卡", "单", "据", "反", "抗", "拉", "萨",
				"积", "分", "卡", "阿", "桑", "杰", "看", "来", "飞", "机", "阿", "拉", "山", "口", "附", "近", "阿", "斯", "利", "康", "附",
				"近", "阿", "斯", "科", "利", "积", "分", "阿", "喀", "琉", "斯", "家", "反", "抗", "拉", "萨", "积", "分", "拉", "丝", "机",
				"积", "分", "看", "来", "`", "~", "！", "@", "#", "￥", "%", "…", "&", "*", "（", "）", "—", "-", "+", "=",
				" " };

		for (int i = 0; i < 40; i++) {
			int a = random.nextInt(list0.length);
			dp.sendKeys("currentUse_rename", list0[a]);
		}
		dp.sendKeys("currentUse_rename", "！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		dp.waitByTimeOut(2000);

		for (int i = 0; i < 30; i++) {
			int aa = random.nextInt(list0.length);
			dp.sendKeys("currentUse_remark", list0[aa]);
		}
		dp.sendKeys("currentUse_remark", "！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		dp.waitByTimeOut(2000);

		dp.clickOn("currentUse_submit");
		dp.waitByTimeOut(4000);
	}

	public void currentUse_AppiumPython_scriptupload() {
		// 点击弹窗,并选择自动化类型
		dp.clickOn("currentUse_script");
		dp.waitByTimeOut(1000);
		dp.clickOn("script_uploadBtn");
		dp.waitByTimeOut(1000);
		Select sel = new Select(dp.driver.findElement(By.xpath("//select[@ng-change='changeFramework()']")));
		sel.selectByIndex(1);
		dp.waitByTimeOut(1000);
		uploadApp("/AppiumPythonScript/demo.zip");
		dp.waitByTimeOut(3000);
		dp.clickOn("currentUse_scriptUpload");
		dp.waitByTimeOut(500);
		isUploadStatus();
		dp.waitByTimeOut(4000);
	}

	public void currentUse_Uiautomator_scriptupload() {
		// 点击弹窗,并选择自动化类型
		dp.waitByTimeOut(1000);
		dp.clickOn("script_uploadBtn");
		Select sel = new Select(dp.driver.findElement(By.xpath("//select[@ng-change='changeFramework()']")));
		sel.selectByIndex(2);
		dp.waitByTimeOut(1000);
		uploadApp("/UiAutomatorScript/Uiautomator-Test.jar");
		dp.waitByTimeOut(3000);
		dp.clickOn("currentUse_scriptUpload");
		dp.waitByTimeOut(500);
		isUploadStatus();
		dp.waitByTimeOut(4000);
	}

	public void currentUse_editAppScript() {
		dp.waitByTimeOut(1000);
		dp.clickOn("script_scriptsBtn");
		editAppScript();
		dp.waitByTimeOut(4000);
	}

	public void currentUse_script_close() {
		dp.clickOn("currentUse_script_cancel");
	}

	/*
	 * *************************************************************************
	 * ***************************All
	 * Devices******************************************************************
	 * **********************************
	 */

	public void allDevice_selectphone(String mobile) {
		dp.waitByTimeOut(2000);
		dp.clickOn("allDevices");
		dp.waitByTimeOut(2000);

		new WebDriverWait(dp.driver, 10).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@book-device,'bookDevice')]")));
		// 获取所有手机数量
		List<WebElement> phone = dp.driver.findElements(By.xpath("//div[contains(@book-device,'bookDevice')]"));

		List<WebElement> android = new ArrayList<WebElement>();
		List<WebElement> iphone = new ArrayList<WebElement>();

		List<WebElement> Available_android = new ArrayList<WebElement>();
		List<WebElement> Available_iphone = new ArrayList<WebElement>();

		int phone_size = Integer.valueOf(phone.size());
		System.err.println("总手机数量  is:" + phone_size);
		assert phone_size != 0 : "no Available phone, end test";

		// 分组Android和iPhone手机
		for (int i = 0; i < phone_size; i++) {
			String phone_name = phone.get(i).getAttribute("title");
			// System.err.println("手机 型号是:" + phone_name);
			if (phone_name.contains("iPhone")) {
				iphone.add(phone.get(i));
			} else {
				android.add(phone.get(i));
			}
		}

		int size = 0;
		if (mobile.contains("iPhone")) {
			int iphone_size = Integer.valueOf(iphone.size());
			// 获得可用iphone手机数量
			for (int i = 0; i < iphone_size; i++) {
				String status = iphone.get(i).findElement(By.xpath(".//span[contains(@class,'device-status')]"))
						.getText();
				// System.err.println("目前读取第 " + i + "个iphone手机. " + "手机状态是:" +
				// status);
				if (status.equals("Available")) {
					Available_iphone.add(iphone.get(i));
				}
			}
			// 获得可用总数量
			size = Integer.valueOf(Available_iphone.size());
			System.err.println("共有" + size + "部可用iphone手机");
			assert size != 0 : "no Available iphone, end test";
		} else {
			int android_size = Integer.valueOf(android.size());
			// 获得可用android手机数量
			for (int i = 0; i < android_size; i++) {
				String status = android.get(i).findElement(By.xpath(".//span[contains(@class,'device-status')]"))
						.getText();
				// System.err.println("目前读取第 " + i + "个android手机. " + "手机状态是:" +
				// status);
				if (status.equals("Available")) {
					Available_android.add(android.get(i));
				}
			}
			// 获得可用总数量
			size = Integer.valueOf(Available_android.size());
			System.err.println("共有" + size + "部可用android手机");
			assert phone_size != 0 : "no Available android phone, end test";
		}

		// 控制随机数可用手机
		// Random random = new Random();
		int a = 0;
		if (size > 10) {
			// 范围2-10
			a = random.nextInt(9) + 2;
		} else if (size == 1) {
			// 只有1台可用
			a = 1;
		} else {
			// 范围:2-AvailablePhoneName_size
			a = random.nextInt(size - 1) + 2;
		}

		// //如果不注释,则必定控制1部可用手机
		// a=1;

		List list = new ArrayList();
		if (mobile.contains("iPhone")) {
			for (int ai = 0; ai < a; ai++) {
				int aa = random.nextInt(size);
				while (list.contains(aa)) {
					aa = random.nextInt(size);
				}
				String andriodPhoneName = Available_iphone.get(aa).getAttribute("title");
				Available_iphone.get(aa).click();
				System.err.println("共选用 " + (a) + " 部苹果手机," + "本次选取第 " + (ai + 1) + " 部,手机型号为:" + andriodPhoneName);
				dp.waitByTimeOut(1000);
				list.add(aa);
			}
		}

		if (!mobile.contains("iPhone")) {
			for (int ai = 0; ai < a; ai++) {
				int aa = random.nextInt(size);
				while (list.contains(aa)) {
					aa = random.nextInt(size);
				}
				String andriodPhoneName = Available_android.get(aa).getAttribute("title");
				Available_android.get(aa).click();
				System.err
						.println("共选用 " + (a) + " 部android手机," + "本次选取第 " + (ai + 1) + " 部,手机型号为:" + andriodPhoneName);
				dp.waitByTimeOut(1000);
				list.add(aa);
			}
		}

		// //随机点击一台设备
		// int aa=random.nextInt(androidPhonenum);
		// dp.waitByTimeOut(200);
		// String PhoneName =AvailablePhoneName.get(aa).getAttribute("title");
		// System.err.println("本次使用android手机型号为:"+PhoneName);
		// AvailablePhoneName.get(aa).click();
	}

	public void allDevice_controlDevice() {
		// allDevices界面控制手机
		dp.waitByTimeOut(2000);
		dp.clickOn("control_device");
		dp.waitByTimeOut(2000);
		dp.clear("control_time");
		dp.waitByTimeOut(500);

		List<WebElement> times = dp.driver.findElements(By.xpath("//span[contains(@class,'device-description')]"));
		int finall = 60;
		for (int i = 0; i < times.size(); i++) {
			String avalible_time[] = times.get(i).getText().split(" ");

			int minutes = Integer.valueOf(avalible_time[6]).intValue();
			if (finall > minutes) {
				finall = minutes;
			}
		}
		System.err.println("手机控时间为：" + finall);
		dp.sendKeys("control_time", String.valueOf(finall));
		dp.waitByTimeOut(1000);
		dp.clickOn("confirm");
		dp.waitByTimeOut(30000);
		// dp.verifyIsShown("currentUse_apps_click");
		dp.clickOn("currentUse_apps_click");
		dp.waitByTimeOut(3000);
	}

	public void allDevice_groupDevice() {
		// allDevices界面进行grous创建
		dp.clickOn("group_device");
		dp.waitByTimeOut(2000);
		dp.clear("Group_name");
		dp.waitByTimeOut(500);
		int a = random.nextInt(999999999);
		dp.sendKeys("Group_name", "aaa" + a);
		dp.waitByTimeOut(1000);
		dp.clickOn("Group_name_Submit");
		dp.waitByTimeOut(3000);
	}

	public void releaseAll() {
		dp.waitByTimeOut(2000);
		dp.clickOn("CurrentUse");
		dp.waitByTimeOut(2000);
		dp.clickOn("currentUse_releaseAll");
		dp.waitByTimeOut(2000);
		dp.clickOn("remove-ok");
		dp.waitByTimeOut(2000);
	}

	/*
	 * *************************************************************************
	 * **************************
	 * Groups*******************************************************************
	 * *********************************
	 */

	public void AddNewGroup() {
		// 打开彈窗
		dp.waitByTimeOut(3000);
		dp.clickOn("Groups_AddNewGroup");
		dp.waitByTimeOut(3000);

		dp.clear("Groups_GroupName_input");
		dp.waitByTimeOut(2000);
		String list0[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
				" ", " ", " ", " ", " ", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "三", "剑", "客",
				"雷", "锋", "精", "神", "卡", "拉", "法", "艰", "苦", "拉", "萨", "积", "分", "刷", "卡", "单", "据", "反", "抗", "拉", "萨",
				"积", "分", "卡", "阿", "桑", "杰", "看", "来", "飞", "机", "阿", "拉", "山", "口", "附", "近", "阿", "斯", "利", "康", "附",
				"近", "阿", "斯", "科", "利", "积", "分", "阿", "喀", "琉", "斯", "家", "反", "抗", "拉", "萨", "积", "分", "拉", "丝", "机",
				"积", "分", "看", "来", "`", "~", "！", "@", "#", "￥", "%", "…", "&", "*", "（", "）", "—", "-", "+", "=",
				" " };

		for (int i = 0; i < 20; i++) {
			int a = random.nextInt(list0.length);
			dp.sendKeys("Groups_GroupName_input", list0[a]);
		}
		dp.sendKeys("Groups_GroupName_input", "！！！！！！！！！！");
		dp.waitByTimeOut(200);

		// // 彈窗发送内容
		// Actions actions = new Actions(dp.driver);
		// dp.waitByTimeOut(1000);
		// int b = random.nextInt(999999999);
		// actions.sendKeys("c1m" + b).perform();

		// 提交
		dp.waitByTimeOut(500);
		dp.clickOn("Groups_AddNewGroup_Submit");
		dp.waitByTimeOut(2000);
	}

	public void editGroup() {
		// 打开彈窗
		dp.waitByTimeOut(5000);
		dp.clickOn("Groups_edit");
		dp.waitByTimeOut(1000);

		dp.clear("Groups_edit_name_input");
		dp.waitByTimeOut(2000);
		String list0[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
				" ", " ", " ", " ", " ", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "三", "剑", "客",
				"雷", "锋", "精", "神", "卡", "拉", "法", "艰", "苦", "拉", "萨", "积", "分", "刷", "卡", "单", "据", "反", "抗", "拉", "萨",
				"积", "分", "卡", "阿", "桑", "杰", "看", "来", "飞", "机", "阿", "拉", "山", "口", "附", "近", "阿", "斯", "利", "康", "附",
				"近", "阿", "斯", "科", "利", "积", "分", "阿", "喀", "琉", "斯", "家", "反", "抗", "拉", "萨", "积", "分", "拉", "丝", "机",
				"积", "分", "看", "来", "`", "~", "！", "@", "#", "￥", "%", "…", "&", "*", "（", "）", "—", "-", "+", "=",
				" " };

		for (int i = 0; i < 20; i++) {
			int a = random.nextInt(list0.length);
			dp.sendKeys("Groups_edit_name_input", list0[a]);
		}
		dp.sendKeys("Groups_edit_name_input", "！！！！！！！！！！");
		dp.waitByTimeOut(200);

		//
		// // 彈窗发送内容
		// Actions actions = new Actions(dp.driver);
		// dp.waitByTimeOut(1000);
		// int a = random.nextInt(999999999);
		// actions.sendKeys(Keys.BACK_SPACE).sendKeys("123" + a).perform();
		// dp.waitByTimeOut(1000);
		//
		// // 提交
		dp.clickOn("Groups_AddNewGroup_Submit");
		dp.waitByTimeOut(4000);
	}

	public void deleteSingleGroup() {
		// 单独删除
		dp.waitByTimeOut(1000);
		dp.clickOn("Groups_deleteDevices");
		dp.waitByTimeOut(1000);
		dp.clickOn("remove-ok");
		dp.waitByTimeOut(3000);
	}

	public void deleteAllGroups() {
		openGroups();
		// 清空已上传app
		while (true) {
			try {
				// WebDriverWait
				dp.waitByTimeOut(2000);
				new WebDriverWait(dp.driver, 3).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//input[contains(@ng-click,'toggleSelectAll()')]")));
				// WebDriverWait driverWait = (WebDriverWait) new
				// WebDriverWait(dp.driver, 5);
				// driverWait.until(new
				// ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@ng-click,'toggleSelectAll()')]"));
				dp.clickOn("selectedAll");
				dp.clickOn("Groups_remove");
				dp.waitByTimeOut(500);
				dp.clickOn("remove-cancel");
				dp.waitByTimeOut(500);
				dp.clickOn("Groups_remove");
				dp.waitByTimeOut(500);
				dp.clickOn("remove-ok");
			} catch (Exception e) {
				dp.log("no apps...");
				break;
			}
		}
		// 检查no_records
		dp.waitByTimeOut(1000);
		try {
			dp.verifyIsShown("currentUse_apps_norecords");
			dp.log("no_record prompt is correct");
		} catch (Exception e) {
			dp.log("no_record prompt is incorrect", 3);
		}
	}

	public void GroupSelectDevices() {
		dp.waitByTimeOut(1000);
		dp.clickOn("Groups_ManageDevices");
		dp.waitByTimeOut(1000);
		new WebDriverWait(dp.driver, 20).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@ng-repeat,'device in unSelectDevices')]")));

		// //根据元素查找已选和未选 手机
		List<WebElement> unSelectDevices = dp.driver
				.findElements(By.xpath("//div[contains(@ng-repeat,'device in unSelectDevices')]"));
		//
		// //获取所有手机数量
		int UnSelectDevicesNum = Integer.valueOf(unSelectDevices.size());
		System.err.println("可添加手机总数量  is:" + (UnSelectDevicesNum));
		assert (UnSelectDevicesNum) != 0 : "no  phone, end test";

		// //随机点击一台或多台设备
		// int a = random.nextInt(UnSelectDevicesNum);
		// while (a==0) {
		// a = random.nextInt(UnSelectDevicesNum);
		// }
		// for (int i = 0; i < a; i++) {
		// //根据元素查找未选 手机
		// unSelectDevices =
		// dp.driver.findElements(By.xpath("//div[contains(@ng-repeat,'device in
		// unSelectDevices')]"));
		// UnSelectDevicesNum = Integer.valueOf(unSelectDevices.size());
		// //进行随机选择
		// int aa = random.nextInt(UnSelectDevicesNum);
		// String PhoneName = unSelectDevices.get(aa).getAttribute("title");
		// System.err.println("共选用 " + (a) + " 部android手机," + "本次选取第 " + (i + 1)
		// + " 部,手机型号为:" + PhoneName);
		// unSelectDevices.get(aa).click();
		// dp.waitByTimeOut(500);
		// }

		// 全部选择
		// Random random = new Random();
		int a = random.nextInt(UnSelectDevicesNum);
		while (a == 0) {
			a = random.nextInt(UnSelectDevicesNum);
		}
		for (int i = 0; i < a; i++) {
			// 根据元素查找未选 手机
			unSelectDevices = dp.driver
					.findElements(By.xpath("//div[contains(@ng-repeat,'device in unSelectDevices')]"));
			UnSelectDevicesNum = Integer.valueOf(unSelectDevices.size());
			// 进行随机选择
			int aa = random.nextInt(UnSelectDevicesNum);
			String PhoneName = unSelectDevices.get(aa).getAttribute("title");
			System.err.println("共选用 " + (a) + " 部android手机," + "本次选取第 " + (i + 1) + " 部,手机型号为:" + PhoneName);
			unSelectDevices.get(aa).click();
			dp.waitByTimeOut(500);
		}

		// //全部选择
		// int a = random.nextInt(UnSelectDevicesNum);
		// for (int i = 0; i < UnSelectDevicesNum; i++) {
		// //根据元素查找未选 手机
		// unSelectDevices =
		// dp.driver.findElements(By.xpath("//div[contains(@ng-repeat,'device in
		// unSelectDevices')]"));
		// int UnSelectDevicesNum1 = Integer.valueOf(unSelectDevices.size());
		// //进行随机选择
		// int aa = random.nextInt(UnSelectDevicesNum1);
		// String PhoneName = unSelectDevices.get(aa).getAttribute("title");
		// System.err.println("共选用 " + (UnSelectDevicesNum) + " 部android手机," +
		// "本次选取第 " + (i + 1) + " 部,手机型号为:" + PhoneName);
		// unSelectDevices.get(aa).click();
		// dp.waitByTimeOut(500);
		// }

		// //随机点击一台设备
		// int aa=random.nextInt(UnSelectDevicesNum);
		// String PhoneName =unSelectDevices.get(aa).getAttribute("title");
		// System.err.println("本次使用手机为:"+PhoneName);
		// unSelectDevices.get(aa).click();
		// dp.waitByTimeOut(1000);

		// 点击提交
		dp.clickOn("Groups_AddDevicesToGroup_Submit");
		dp.waitByTimeOut(2000);
	}

	public void GroupUnselectDevices() {
		dp.waitByTimeOut(1000);
		dp.clickOn("Groups_ManageDevices");
		dp.waitByTimeOut(1000);
		new WebDriverWait(dp.driver, 20).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(@ng-repeat,'device in selectDevices')]")));

		// //根据元素查找已选手机
		List<WebElement> selectDevices = dp.driver
				.findElements(By.xpath("//div[contains(@ng-repeat,'device in selectDevices')]"));
		//
		// //获取已选手机数量
		int SelectDevicesNum = Integer.valueOf(selectDevices.size());
		System.err.println("本组已添加手机数量  is:" + (SelectDevicesNum));
		assert (SelectDevicesNum) != 0 : "no Available phone, end test";

		// 随机点击一台或多台设备
		// Random random = new Random();
		int a = random.nextInt(SelectDevicesNum);
		while (a == 0) {
			a = random.nextInt(SelectDevicesNum);
		}
		for (int i = 0; i < a; i++) {
			// 根据元素查找已选手机
			selectDevices = dp.driver.findElements(By.xpath("//div[contains(@ng-repeat,'device in selectDevices')]"));
			int SelectDevicesNum1 = Integer.valueOf(selectDevices.size());
			// 进行随机选择
			int aa = random.nextInt(SelectDevicesNum1);
			String PhoneName = selectDevices.get(aa).getAttribute("title");
			System.err.println("共移除 " + (a) + " 部android手机," + "本次选取第 " + (i + 1) + " 部,手机型号为:" + PhoneName);
			selectDevices.get(aa).click();
			dp.waitByTimeOut(500);
		}

		// //全部选择
		// for (int i = 0; i < SelectDevicesNum; i++) {
		// //根据元素查找已选手机
		// selectDevices =
		// dp.driver.findElements(By.xpath("//div[contains(@ng-repeat,'device in
		// selectDevices')]"));
		// int SelectDevicesNum1 = Integer.valueOf(selectDevices.size());
		// //进行随机选择
		// int aa = random.nextInt(SelectDevicesNum1);
		// String PhoneName = selectDevices.get(aa).getAttribute("title");
		// System.err.println("共选用 " + (SelectDevicesNum) + " 部android手机," +
		// "本次选取第 " + (i + 1) + " 部,手机型号为:" + PhoneName);
		// selectDevices.get(aa).click();
		// dp.waitByTimeOut(500);
		// }

		// //随机点击一台设备
		// int aa=random.nextInt(UnSelectDevicesNum);
		// dp.waitByTimeOut(200);
		// String PhoneName =unSelectDevices.get(aa).getAttribute("title");
		// System.err.println("本次使用android手机型号为:"+PhoneName);
		// unSelectDevices.get(aa).click();
		// dp.waitByTimeOut(1000);

		dp.clickOn("Groups_AddDevicesToGroup_Submit");
		dp.waitByTimeOut(3000);
	}

}
