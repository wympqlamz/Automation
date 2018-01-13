package com.by.automate.testCases.BAT.ios;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;

public class Test_FCControlAndUploadApp {
	/**
	 * DP -- 冒烟测试
	 */
	private String uploadAppName;
	private String fileName;
	private int number = 1;
	private DPWebApp dp = null;

	@BeforeClass
	public void setUp() {

		dp = new DPWebApp();
		dp.openApp();
		dp.putStep("DP0106001");
		String devices = dp.getSutFullFileName("dp.ios.devices");
		number = devices.equals("")?1:Integer.parseInt(devices);
	}

	@Test
	public void Test010_selectDevicesAndGotoControl() {

		// login
		dp.DP_Login();

		// 验证头部信息
		dp.verifyIsShown("jiantou");
		dp.verifyIsShown("messageCenter");
		dp.verifyIsShown("help");
		dp.verifyIsShown("testStatus");
		dp.verifyIsShown("userName");

		// 验证左边logo
		dp.verifyIsShown("logo");
		// 验证workspace功能模块
		dp.verifyIsShown("workspace");
		dp.verifyIsShown("apps");
		dp.verifyIsShown("testReport");
		dp.verifyIsShown("screenShots");
		// 验证devices功能模块
		dp.verifyIsShown("devices");
		dp.verifyIsShown("groups");
		// 版本和版权
		dp.verifyIsShown("build");
		dp.verifyIsShown("copyRight");
		dp.verifyIsShown("byBeyondsoft");
		// uplaod app
		dp.clickOn("apps");
		dp.waitByTimeOut(3000);
		// upload app
		this.uploadAppName = dp.uploadAppByIOS();
		isUploadStatus();

		dp.verifyIsShown("waitingMessage");
		dp.verifyIsShown("successMessage");

		String getWaitingMessage = dp.getValueOf("waitingMessage");
		String getSuccessMessage = dp.getValueOf("successMessage");

		dp.log(getWaitingMessage);
		dp.log(getSuccessMessage);

		dp.waitByTimeOut(3000);
		String getWaitingMessageByTestData = dp.getContentPropertry("dp.msg.upload.waiting");
		String getSuccessMessageByTestData = dp.getContentPropertry("dp.msg.upload.success");
		dp.waitByTimeOut(3000);

		dp.assertEqual(getWaitingMessageByTestData, getWaitingMessage);
		dp.assertEqual(getSuccessMessageByTestData, getSuccessMessage);

		dp.verifyIsShown("fileName");
		fileName = dp.getValueOf("fileName");
	}

	@Test
	public void Test020_ControlDevice(){
		
		dp.clickOn("devices");
		// 验证头部信息
		dp.verifyIsShown("jiantou");
		dp.verifyIsShown("messageCenter");
		dp.verifyIsShown("help");
		dp.verifyIsShown("testStatus");
		dp.verifyIsShown("userName");
		
		// 验证左边logo
		dp.verifyIsShown("logo");
		// 验证workspace功能模块
		dp.verifyIsShown("workspace");
		dp.verifyIsShown("apps");
		dp.verifyIsShown("testReport");
		dp.verifyIsShown("screenShots");
		// 验证devices功能模块
		dp.verifyIsShown("devices");
		dp.verifyIsShown("groups");
		// 版本和版权
		dp.verifyIsShown("build");
		dp.verifyIsShown("copyRight");
		dp.verifyIsShown("byBeyondsoft");
		
		// 选择一个设备
		dp.verifyIsShown("firstDevice");
		// log 只能输出字符串， + 转换成字符串
		dp.log(dp.isElementNotEnabled("controlButton") + "");
		dp.log(dp.isElementNotEnabled("groupButton") + "");
		dp.log(dp.isElementNotEnabled("moreButton") + "");
		List<String> devicesName = dp.selectDriversIOS(number);
		dp.waitByTimeOut(1000);
		
		
		dp.log(dp.isEnabled("controlButton") + "");
		dp.log(dp.isEnabled("groupButton") + "");
		dp.log(dp.isEnabled("moreButton") + "");
		
		dp.verifyIsShown("controlButton");
		dp.clickOn("controlButton");
		
		dp.waitByTimeOut(1000);
		if(dp.waitForElementReadyOnTimeOut("controlDeviceConfirm", 3)){
			dp.verifyIsShown("controlDeviceConfirm");
			dp.verifyIsShown("confirmButtonByControl");
			dp.log(dp.getValueOf("controlDeviceConfirm"));
			dp.clickOn("confirmButtonByControl");
			dp.waitByTimeOut(2000);
		}
		
		dp.waitByTimeOut(4000);
		
		for (int i = 1; i <= number; i++) {
			dp.verifyIsShown("deviceNames", (2*i)-1);
			dp.verifyIsShown("deviceScreens",i);
			String getDeviceNames = dp.getValueOf("deviceNames",(2*i)-1).replace(" ", "");
			// 比较 在group 页面设备的名字和control 页面设备显示的名字
			dp.assertTrue(devicesName.contains(getDeviceNames), "显示设备失败");
		}
		
		
	}

	@Test
	public void Test030_installApp() {

		dp.waitByTimeout(4000);
		dp.verifyIsShown("screenShot");

		if (dp.waitForElementReadyOnTimeOut("rightButton", 3)) {
			dp.verifyIsShown("rightButton");
			dp.clickOn("rightButton");
		}

		// 验证元素是否显示
		dp.waitByTimeout(1000);
		dp.verifyIsShown("Apps");
		dp.clickElementByJS("Apps");
		dp.waitByTimeout(1000);

		List<WebElement> appListName = dp.getElements("appFileNames");
		List<WebElement> appInstallList = dp.getElements("installation");

		String elementText = "";
		for (int i = 0; i < appListName.size(); i++) {
			elementText = appListName.get(i).getText();
			if (elementText.equals(fileName)) {
				appInstallList.get(i).click();
				;
				break;
			}
		}

		dp.verifyIsShown("installAppSuccess");

		String getSuccessMessage = dp.getValueOf("installAppSuccess");
		String getSuccessMessageByTest = dp.getContentPropertry("dp.msg.install.success");
		dp.assertIndexof(getSuccessMessage, getSuccessMessageByTest);

	}

	String scriptName = "";
	String elementText = "";

	@Test
	public void Test040_uploadScript() {

		dp.waitByTimeOut(2000);

		List<WebElement> appListName = dp.getElements("appFileNames");
		List<WebElement> appAutomationList = dp.getElements("startAutomationApp");

		for (int i = 0; i < appListName.size(); i++) {
			elementText = appListName.get(i).getText();
			if (elementText.equals(fileName)) {
				appAutomationList.get(i).click();
				;
				break;
			}
		}

		dp.waitByTimeOut(1000);
		dp.verifyIsShown("next");
		dp.clickOn("next");

		dp.waitByTimeOut(1000);
		this.uploadAppName = dp.uploadScriptByIOS();

		long time = System.currentTimeMillis();
		scriptName = String.valueOf(time);
		dp.setValueTo("remarkName", scriptName);
		dp.clickElementByJS("Upload");
		isUploadStatus();

	}

	@Test
	public void Test050_runScript() {

		dp.waitByTimeOut(2000);

		List<WebElement> scriptList = dp.getElements("scriptListName");
		List<WebElement> selectScript = dp.getElements("selectScript");

		dp.log(scriptName);
		for (int i = 0; i < scriptList.size(); i++) {
			elementText = scriptList.get(i).getText();
			if (scriptName.equals(elementText)) {
				elementText = ((String) elementText).trim();
				scriptName = ((String) scriptName).trim();
				selectScript.get(i).click();

			}

		}
		dp.waitByTimeOut(1000);
		dp.clickOn("sriptNext");
		dp.setValueTo("automationTime", "8");
		dp.clickOn("startAutomation");
		dp.clickOn("Close");

		dp.waitByTimeOut(1000);

		dp.clickOn("testReport");
		dp.waitByTimeOut(1000);
		while (true) {
			String scriptStatus = dp.getElementAttribute("scriptProgress", "value");
			if (scriptStatus.contains("success") || scriptStatus.contains("fail")) {
				dp.log(scriptStatus);
				break;
			} else {
				dp.log(scriptStatus);
				dp.waitByTimeOut(10000);
			}
		}
	}

	@Test
	public void Test060_deleteApp() {

		dp.verifyIsShown("apps");
		dp.clickOn("apps");
		dp.verifyIsShown("removeFirstApp");
		dp.clickOn("removeFirstApp");
		dp.verifyIsShown("confirm");
		dp.clickOn("confirm");
		dp.waitByTimeOut(800);
		dp.verifyIsShown("deleteAppSuccess");
		String getSuccessMessage = dp.getValueOf("deleteAppSuccess");
		dp.log(getSuccessMessage);
	}

	@AfterClass
	public void tearDown() {

		dp.close();
	}

	private void isUploadStatus() {
		dp.verifyIsShown("progressBar");
		String percent = dp.getValueOf("progressBar");

		// for / while / do while
		while (!(percent.equals("100%"))) {

			dp.waitByTimeOut(1000);
			percent = dp.getValueOf("progressBar");

			dp.log("Current Percent : " + percent);
		}

		dp.log("Upload App Success.");
	}
}
