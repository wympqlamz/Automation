package com.by.automate.testCases.BAT.ios;

import com.by.automate.fwk.DPWebApp;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

public class Test_FCContorlAndScreenShotDevice {

	/**
	 * DP -- 冒烟测试
	 */
	private String uploadAppName;
	private WebElement scrpitElement;

	private DPWebApp dp = null;
	
	private int number =1;

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
	public void Test020_ShowDeviceAndVerifyScreenShot() {

		dp.waitByTimeout(4000);
		dp.verifyIsShown("screenShot");
		if(dp.waitForElementReadyOnTimeOut("rightButton", 3)){
			dp.verifyIsShown("rightButton");
			dp.clickOn("rightButton");
		}

		// 验证元素是否显示 -- 准备测试环境
		dp.waitByTimeout(1000);
		dp.clickElementByJS("screenShot");
		dp.waitByTimeout(5000);
		String results = "";
		for (int i = 1; i <=number; i++) {
			
			dp.verifyIsShown("screenShotCounts", i*2);
			
			int values = getScreenShotCount(dp.getValueOf("screenShotCounts", i*2));
			dp.log(values);
			results += values+"-";
			
		}

		dp.waitByTimeout(2000);
		dp.clickElementByJS("screenShot");
		dp.verifyIsShown("screenShot1");
		dp.verifyIsShown("date1");
		dp.waitByTimeOut(4000);
		
		String afterResults = "";
		for (int i = 1; i <=number; i++) {
			
			dp.verifyIsShown("screenShotCounts", i*2);
			
			int values = getScreenShotCount(dp.getValueOf("screenShotCounts", i*2));
			dp.log(values);
			afterResults += values+"-";
			
		}
		// 验证图片是否截取成功
		dp.log(results);
		dp.log(afterResults);
		String[] before = results.split("-");
		String[] after = afterResults.split("-");
		for (int i = 0; i < after.length; i++) {
			int b = Integer.parseInt(before[i]);
			int a = Integer.parseInt(after[i]);
			dp.assertTrue(b == (a - 1), "截图失败");
		}
		
	}

	@Test
	public void Test030_uploadScript() {

		dp.clickOn("Apps");
		dp.waitByTimeOut(3000);
		
		List<WebElement> appListName = dp.getElements("appListName");
		List<WebElement> appList = dp.getElements("startAutomationApp");
		

		String elementText = "";
		for (int i = 0; i < appListName.size(); i++) {
			elementText = appListName.get(i).getText();
			if(elementText.contains("TrainTicket")){
				scrpitElement = appList.get(i);
				scrpitElement.click();
				break;
	 		}
			
		}
		
//		dp.verifyIsShown("startAutomationApp");
//		dp.clickOn("startAutomationApp");
		
		dp.waitByTimeOut(1000);
		dp.clickOn("next");
		
		dp.waitByTimeOut(1000);
		this.uploadAppName = dp.uploadScriptByIOS();
		
		long time=System.currentTimeMillis();
		String scriptName=String.valueOf(time);
		dp.setValueTo("remarkName", scriptName);
		dp.clickElementByJS("Upload");
		isUploadStatus();
		
		dp.waitByTimeOut(2000);
		
		
		List<WebElement> scriptList = dp.getElements("scriptListName");
		
		List<WebElement> selectScript = dp.getElements("selectScript");
		
		dp.log(scriptName);
		for (int i = 0; i < scriptList.size(); i++) {
			elementText = scriptList.get(i).getText();
			
			if (elementText instanceof String && scriptName instanceof String) {
				elementText = ((String) elementText).trim();
				scriptName = ((String) scriptName).trim();
				selectScript.get(i).click();

	        }
			
		}
		dp.waitByTimeOut(1000);
		dp.clickOn("sriptNext");
		dp.setValueTo("automationTime","8");
		dp.clickOn("startAutomation");
		dp.clickOn("Close");
		
		dp.waitByTimeOut(1000);

		dp.clickOn("testReport");
		
		dp.waitByTimeOut(1000);
		
		while(true){
			String scriptStatus = dp.getElementAttribute("scriptProgress", "value");
			if(scriptStatus.contains("success") || scriptStatus.contains("fail")){
				dp.log(scriptStatus);
				break;
	 		}else{
	 			dp.log(scriptStatus);
	 			dp.waitByTimeOut(10000);
	 		}
		}
		
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
		dp.verifyIsShown("uploadScriptProgress");
		String percent = dp.getValueOf("uploadScriptProgress");

		// for / while / do while
		while (!(percent.equals("100%"))) {

			dp.waitByTimeOut(1000);
			percent = dp.getValueOf("uploadScriptProgress");

			dp.log("Current Percent : " + percent);
		}

		dp.log("Upload App Success.");
	}

}
