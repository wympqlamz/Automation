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
package com.by.automate.testCases.devicepass.user.workspaces.apps;

import com.by.automate.fwk.DPWebApp;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Calendar;


public class Test_FC_app_core {
	String uploadAppName;
	public DPWebApp dp = null;

	@BeforeClass
	public void setUp() {

		// 获取MainConfig.properties,然后读取到Web.properties,将内容到读取到sutpro
		// 读取 default.porperties,添加到sutpro
		dp = new DPWebApp();

		/*
		 * ／默认读取conf.ui.WebApp.default.xml = Web/DPUi_bat.xml． ｜｜
		 * ｕｉ检查时，填写uiChanges,apps读取： uiChanges.apps.xml =
		 * Web/uiChanges/DPUi_apps.xml 和 读取DPUi_CommenAreas.xml 和 读取用例step内容
		 *
		 * /打印版本等信息,
		 *
		 * /设置浏览器, 启动 ,打开网页, //准备截图
		 *
		 * /读取uiChanges/DPUi_apps.xml文件里面
		 * defaultView="apps, areas="[common,login,appsUi]"
		 * 读取uiChanges/DPUi_apps.xml文件里面,View 和 areas 和 elements
		 */
		dp.openApp();

		// dp.DP_Login("menglei02@beyondsoft.com","123456");
		dp.DP_Login();
	}

	@AfterClass
	public void tearDown() {
		dp.close();
	}

	public void openApps() {
		dp.putStep("DP0106001");
		dp.getXMLDoc("workspaces", "apps");
		dp.waitByTimeOut(5000);
		dp.setViewTo("appspage:apps");
		dp.verifyIsShown("apps");
		dp.clickOn("apps");
		dp.waitByTimeOut(1000);
	}

    public void openAllDevices() {
        dp.putStep("DP0106001");
        dp.getXMLDoc("workspaces", "apps");
        dp.waitByTimeOut(1000);
        dp.setViewTo("appspage:apps");
        dp.waitByTimeOut(10000);
        // dp.verifyIsShown("allDevices");
        dp.clickOn("allDevices");
        dp.waitByTimeOut(1000);
    }

	public void openGroups() {
		dp.putStep("DP0106001");
		dp.getXMLDoc("workspaces", "apps");
		dp.waitByTimeOut(1000);
		dp.setViewTo("appspage:apps");
		dp.verifyIsShown("Groups");
		dp.clickOn("Groups");
		dp.waitByTimeOut(1000);
	}

	// private int getScreenShotCount(String value) {
	// // (5 / 5 pictures )
	// value = StringUtils.substringBetween(value, "(", ")").trim();
	// value = value.replace("pictures", "").replace("picture", "").trim();
	// String[] str = value.split("/");
	// return Integer.parseInt(str[1].trim());
	// }
	//


/*	****************************************************************************************************Apps****************************************************************************************************   */


	public void check_app_no_records() {
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
		dp.sendKeys("Apprename", "111");
		dp.waitByTimeOut(2000);
		dp.clickOn("submit");
		dp.waitByTimeOut(2000);
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
		dp.waitByTimeOut(1000);
		dp.clear("script_rename");
		dp.waitByTimeOut(2000);
		// 6-15号发现不能用了,改用actions
		// dp.sendKeys("script_rename", "111");
		Actions actions = new Actions(dp.driver);
		actions.sendKeys("999").perform();
		dp.waitByTimeOut(1000);

		dp.clickOn("script_submit");
		dp.waitByTimeOut(2000);
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
		//从app界面
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
		Random random = new Random();
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



/*	****************************************************************************************************Reports****************************************************************************************************   */


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





/*	**************************************************************************************************** currentUse****************************************************************************************************   */

	public void currentUse_editApp() {
		dp.waitByTimeOut(3000);
		dp.clickOn("currentUse_editApp");
		dp.waitByTimeOut(2000);
		dp.clear("currentUse_rename");
		dp.waitByTimeOut(2000);
		dp.sendKeys("currentUse_rename", "111");
		dp.waitByTimeOut(2000);
		dp.clickOn("currentUse_submit");
		dp.waitByTimeOut(2000);
	}



/*	****************************************************************************************************All Devices****************************************************************************************************   */


	public void allDevice_selectDevice() {
		dp.waitByTimeOut(2000);
		dp.clickOn("allDevices");
		dp.waitByTimeOut(2000);
		new WebDriverWait(dp.driver, 10).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@book-device,'bookDevice')]")));
		// 获取所有手机数量
		List<WebElement> phone_status = dp.driver.findElements(
				By.xpath("//div[contains(@book-device,'bookDevice')]/span[contains(@class,'device-status')]"));
		List<WebElement> AvailablePhoneName = dp.driver
				.findElements(By.xpath("//div[contains(@book-device,'bookDevice')]"));
		int phone_status_size = Integer.valueOf(phone_status.size());
		int AvailablePhoneName_size = Integer.valueOf(AvailablePhoneName.size());
		System.err.println("总手机数量  is:" + phone_status_size);
		// System.err.println("总手机数量 is:" + AvailablePhoneName_size);
		assert AvailablePhoneName_size != 0 : "no Available phone, end test";

		// 删除不可用,获得可用手机数量
		int ii = 0;
		for (int i = 0; i < phone_status_size; i++) {
			String status = phone_status.get(i).getText();
			// System.err.println("目前读取第 "+i +"个手机. "+"手机状态是:"+status);
			if (!status.equals("Available")) {
				// System.err.println("删除第"+ii+"个手机");
				phone_status.remove(ii);
				AvailablePhoneName.remove(ii);
				i--;
			} else {
				ii++;
			}
			phone_status_size = Integer.valueOf(phone_status.size());
			AvailablePhoneName_size = Integer.valueOf(AvailablePhoneName.size());
			// System.err.println("排查循环过程中手机数量:" + phone_status_size+"具体为:" +
			// phone_status);
			// System.err.println("排查循环过程中手机数量:" +
			// AvailablePhoneName_size+"具体为:" + AvailablePhoneName);
		}
		//// 可用手机数量打印输出
		// System.err.println("可用手机数量 phone_status is:" +
		//// phone_status_size+"具体为:" + phone_status);
		// System.err.println("可用手机数量 AvailablePhoneName is:" +
		//// AvailablePhoneName_size+"具体为:" + AvailablePhoneName);
		System.err.println("可用手机数量  is:" + phone_status_size);
		assert AvailablePhoneName_size != 0 : "no Available android phone, end test";

		// 用手机中,筛选Android手机
		ii = 0;
		for (int i = 0; i < AvailablePhoneName_size; i++) {
			String iOS = AvailablePhoneName.get(i).getAttribute("title");
			System.err.println("手机 型号是:" + iOS);
			if (iOS.contains("iPhone")) {
				System.err.println("删除第" + ii + "个手机");
				phone_status.remove(ii);
				AvailablePhoneName.remove(ii);
				i--;
			} else {
				ii++;
			}
			phone_status_size = Integer.valueOf(phone_status.size());
			AvailablePhoneName_size = Integer.valueOf(AvailablePhoneName.size());
			// System.err.println("排查循环过程中可用Android手机数量:" +
			// phone_status_size+"具体为:" + phone_status);
			// System.err.println("排查循环过程中可用Android手机数量:" +
			// AvailablePhoneName_size+"具体为:" + AvailablePhoneName);
		}
		// 可用Android手机数量打印输出
		// System.err.println("可用Android手机数量 phone_status is:" +
		// phone_status_size+"具体为:" + phone_status);
		// System.err.println("可用Android手机数量 AvailablePhoneName is:" +
		// AvailablePhoneName_size+"具体为:" + AvailablePhoneName);
		System.err.println("可用Android手机数量 :" + phone_status_size);
		assert AvailablePhoneName_size != 0 : "no Available android phone, end test";

		// 控制随机数可用手机
		Random random = new Random();
		int a;
		if (AvailablePhoneName_size > 10) {
			// 范围2-10
			a = random.nextInt(9) + 2;
		} else if (AvailablePhoneName_size == 1) {
			// 只有1台可用
			a = 1;
		} else {
			// 范围:2-AvailablePhoneName_size
			a = random.nextInt(AvailablePhoneName_size - 1) + 2;
		}

		// //如果不注释,则必定控制全部可用手机
		// a=AvailablePhoneName_size;

		// //如果不注释,则必定控制1部可用手机
		// a=1;

		List list = new ArrayList();
		for (int i = 0; i < a; i++) {
			int aa = random.nextInt(AvailablePhoneName_size);
			while (list.contains(aa)) {
				aa = random.nextInt(AvailablePhoneName_size);
			}
			String PhoneName = AvailablePhoneName.get(aa).getAttribute("title");
			AvailablePhoneName.get(aa).click();
			System.err.println("共选用 " + (a) + " 部android手机," + "本次选取第 " + (i + 1) + " 部,手机型号为:" + PhoneName);
			dp.waitByTimeOut(1000);
			list.add(aa);
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
		dp.clickOn("control_device");
		dp.waitByTimeOut(2000);
		dp.clear("control_time");
		dp.waitByTimeOut(500);
		dp.sendKeys("control_time", "30");
		dp.waitByTimeOut(1000);
		dp.clickOn("confirm");
		dp.waitByTimeOut(8000);
		// dp.verifyIsShown("currentUse_apps_click");
		dp.clickOn("currentUse_apps_click");
		dp.waitByTimeOut(1000);
	}

	public void allDevice_groupDevice() {
		// allDevices界面进行grous创建
		dp.clickOn("group_device");
		dp.waitByTimeOut(2000);
		dp.clear("Group_name");
		dp.waitByTimeOut(500);
		dp.sendKeys("Group_name", "aaa");
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





/*	*************************************************************************************************** Groups****************************************************************************************************   */

	public void AddNewGroup() {
		openGroups();
		// 打开彈窗
		dp.waitByTimeOut(1000);
		dp.clickOn("Groups_AddNewGroup");
		dp.waitByTimeOut(1000);

		// 彈窗发送内容
		Actions actions = new Actions(dp.driver);
		dp.waitByTimeOut(1000);
		// Random ramdom = new Random();
		// int num = ramdom.nextInt(999999999);
		// actions.sendKeys("1"+ num).perform();
		actions.sendKeys("c1m").perform();

		// 提交
		dp.waitByTimeOut(500);
		dp.clickOn("Groups_AddNewGroup_Submit");
		dp.waitByTimeOut(1000);
	}

	public void editGroup() {
		// 打开彈窗
		dp.waitByTimeOut(1000);
		dp.clickOn("Groups_edit");
		dp.waitByTimeOut(1000);

		// 彈窗发送内容
		Actions actions = new Actions(dp.driver);
		dp.waitByTimeOut(1000);
		actions.sendKeys(Keys.BACK_SPACE).sendKeys("123").perform();
		dp.waitByTimeOut(1000);

		// 提交
		dp.clickOn("Groups_AddNewGroup_Submit");
		dp.waitByTimeOut(1000);
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
		// Random random = new Random();
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
		Random random = new Random();
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
		// Random random = new Random();
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
		Random random = new Random();
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
		// Random random = new Random();
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
