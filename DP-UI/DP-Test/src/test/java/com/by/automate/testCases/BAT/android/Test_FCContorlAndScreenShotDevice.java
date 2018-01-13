package com.by.automate.testCases.BAT.android;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;

public class Test_FCContorlAndScreenShotDevice {

	/**
	 * DP  -- 冒烟测试
	 */

	private String uploadAppName;

	private DPWebApp dp = null;

	@BeforeClass
	public void setUp() {

		dp = new DPWebApp();
		dp.openApp();
		dp.putStep("DP0106001");
	}

	@Test(priority = 1)
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

		// 选择一个设备
		dp.verifyIsShown("firstDevice");
		// log 只能输出字符串， + 转换成字符串
		dp.log(dp.isElementNotEnabled("controlButton") + "");
		dp.log(dp.isElementNotEnabled("groupButton") + "");

		List<String> devicesName = dp.selectDevices(2);
		dp.waitByTimeOut(500);

		dp.log(dp.isEnabled("controlButton") + "");
		dp.log(dp.isEnabled("groupButton") + "");

		/*
		 * // 获取选择设备的名字 String deviceName1 =
		 * dp.getElementAttribute("firstDeviceTitle", "title"); String
		 * deviceName2 = dp.getElementAttribute("secondDeviceTitle", "title");
		 */
		// String deviceName3 = dp.getElementAttribute("threeDeviceTitle",
		// "title");
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

		dp.verifyIsShown("deviceName1");
		dp.verifyIsShown("deviceName2");
		dp.verifyIsShown("deviceScreen1");
		dp.verifyIsShown("deviceScreen2");
		dp.verifyIsShown("menuButton");
		// 获取两个设备的名字
		String getDeviceName1 = dp.getValueOf("deviceName1").replace(" ", "");
		String getDeviceName2 = dp.getValueOf("deviceName2").replace(" ", "");
		// 比较 在group 页面设备的名字和control 页面设备显示的名字

		dp.assertTrue(devicesName.contains(getDeviceName1), "控制屏幕失败");
		dp.assertTrue(devicesName.contains(getDeviceName2), "控制屏幕失败");
	}

    @Test(priority =2)
	public void Test020_ShowDeviceAndVerifyScreenShot() {

		dp.waitByTimeout(4000);
		dp.verifyIsShown("screenShot");
		// before screenshot
		dp.verifyIsShown("rightButton");
		dp.clickOn("rightButton");

		// 验证元素是否显示 -- 准备测试环境
		dp.waitByTimeout(1000);
		dp.clickElementByJS("screenShot");
		dp.waitByTimeout(2000);
		dp.verifyIsShown("screenShotCount1");
//		dp.verifyIsShown("screenShotCount2");

		// 获取元素对应的文本值
		int value1 = getScreenShotCount(dp.getValueOf("screenShotCount1"));
//		int value2 = getScreenShotCount(dp.getValueOf("screenShotCount2"));

		// 输出文本值到IDE的控制台.
		dp.log(value1);
//		dp.log(value2);

		dp.waitByTimeout(2000);
		dp.clickElementByJS("screenShot");
		dp.verifyIsShown("screenShot1");
		dp.verifyIsShown("date1");

		dp.waitByTimeOut(2000);

		// after screenshot
		dp.verifyIsShown("screenShotCount1");
//		dp.verifyIsShown("screenShotCount2");

		int afterValue1 = getScreenShotCount(dp.getValueOf("screenShotCount1"));
//		int afterValue2 = getScreenShotCount(dp.getValueOf("screenShotCount2"));

		dp.log(afterValue1);
//		dp.log(afterValue2);

		// 验证图片是否截取成功
		dp.assertTrue(value1 == afterValue1 - 1, "截图失败");
//		dp.assertTrue(value2 == afterValue2 - 1, "截图失败");

	}

	//@Test
	public void Test030_uploadApp() {

		dp.clickOn("apps");
		dp.waitByTimeOut(3000);
		// upload app
		this.uploadAppName = dp.uploadApp();
		isUploadStatus();

		dp.verifyIsShown("waitingMessage");
		dp.verifyIsShown("successMessage");

		String getWaitingMessage = dp.getValueOf("waitingMessage");
		String getSuccessMessage = dp.getValueOf("successMessage");

		dp.log(getWaitingMessage);
		dp.log(getSuccessMessage);

		String getWaitingMessageByTestData = dp.getContentPropertry("dp.msg.upload.waiting");
		String getSuccessMessageByTestData = dp.getContentPropertry("dp.msg.upload.success");

		dp.assertEqual(getWaitingMessageByTestData, getWaitingMessage);
		dp.assertEqual(getSuccessMessageByTestData, getSuccessMessage);

		// 如果存在相同的app 我们需要确认 可以接受相同app 的名字。

		if(dp.waitForElementReadyOnTimeOut("renameCancel", 10)){

			dp.verifyIsShown("renameConfirm");
			dp.verifyIsShown("renameConfirm");
			dp.clickOn("renameConfirm");

			// edit app name
			dp.verifyIsShown("fileNameInput");
			dp.verifyIsShown("submitButton");
			dp.setValueTo("fileNameInput", uploadAppName = uploadAppName + "_" +System.currentTimeMillis());
			dp.clickOn("submitButton");

		}

		dp.setViewTo("dashboard:apps");
		dp.waitByTimeOut(2000);
		//dp.cofigurationAutomationTest(this.uploadAppName);
		dp.cofigurationRemoveApp(this.uploadAppName);
		/*dp.log("test123 ---------------- " +dp.getValueOf("allBanner", 5, "allFileName") );
		//dp.clickOn("allBanner", uploadAppName,"automationTest");

		dp.clickOn("RunAppButton");
		dp.verifyIsShown("configurationImage");
		dp.verifyIsShown("configurationName");
		dp.waitByTimeOut(2000);

		dp.clickOn("configurationImage");
		dp.verifyIsShown("nextButBySelectTestMode");
		dp.clickOn("nextButBySelectTestMode");
		dp.waitByTimeOut(2000);

		dp.verifyIsShown("nextButByConfiguration");
		dp.clickOn("nextButByConfiguration");
		dp.waitByTimeOut(2000);

		dp.verifyIsShown("selectDeviceGroup");
		dp.clickOn("selectDeviceGroup");
		dp.waitByTimeOut(2000);

		dp.verifyIsShown("nextButBySelectDeviceGroup");
		dp.clickOn("nextButBySelectDeviceGroup");
		dp.verifyIsShown("testName");
		dp.waitByTimeOut(2000);
		dp.setValueTo("testName", "jia");
		dp.verifyIsShown("runButByTestName");
		dp.clickOn("runButByTestName");
		dp.waitByTimeOut(2000);

		dp.verifyIsShown("testRusultButton");
		dp.clickOn("testRusultButton");
		dp.waitByTimeOut(2000);

		dp.verifyIsShown("testName1");
		dp.clickOn("testName1");
*/
	}

	@AfterClass
	public void tearDown() {
		 dp.close();
	}

	private int getScreenShotCount(String value) {

		// (5 / 5 pictures )

		value = StringUtils.substringBetween(value, "(", ")").trim();
		value = value.replace("pictures", "").replace("picture", "").trim();
		String[] str = value.split("/");
		return Integer.parseInt(str[1].trim());
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
