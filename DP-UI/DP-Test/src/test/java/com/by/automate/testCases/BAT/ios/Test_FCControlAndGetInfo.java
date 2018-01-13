package com.by.automate.testCases.BAT.ios;

import com.by.automate.fwk.DPWebApp;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

public class Test_FCControlAndGetInfo {
	/**
	 * DP -- 冒烟测试
	 */
	private String uploadAppName;

	private DPWebApp dp = null;

	@BeforeClass
	public void setUp() {

		dp = new DPWebApp();
		dp.openApp();
		dp.putStep("DP0106001");
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
		List<String> devicesName = dp.selectDriversIOS(1);
		dp.waitByTimeOut(500);

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
		
		dp.verifyIsShown("deviceName1");
		dp.verifyIsShown("deviceScreen1");
		// 获取两个设备的名字
		String getDeviceName1 = dp.getValueOf("deviceName1").replace(" ", "");


		dp.assertTrue(devicesName.contains(getDeviceName1), "显示设备失败");

	}

	

	@Test
	public void Test030_GetInfo() {


		dp.clickOn("Info");
		dp.waitByTimeOut(3000);
		
		
		dp.verifyIsShown("Serial");
		dp.verifyIsShown("deviceName");
		dp.verifyIsShown("OS");
		dp.verifyIsShown("Version");
		dp.verifyIsShown("SDK");
		dp.verifyIsShown("IMEI");
		dp.verifyIsShown("ICCID");

		dp.log("Serial - " + dp.getValueOf("Serial"));
		dp.log("deviceName - " + dp.getValueOf("deviceName"));
		dp.log("OS - " + dp.getValueOf("OS"));
		dp.log("Version - " + dp.getValueOf("Version"));
		dp.log("SDK - " + dp.getValueOf("SDK"));
		dp.log("IMEI - " + dp.getValueOf("IMEI"));
		dp.log("ICCID - " + dp.getValueOf("ICCID"));

	}

	@AfterClass
	public void tearDown() {
		 dp.close();
	}

}
