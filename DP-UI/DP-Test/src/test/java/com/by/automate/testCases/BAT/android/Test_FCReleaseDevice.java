package com.by.automate.testCases.BAT.android;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;

import java.util.List;

public class Test_FCReleaseDevice {

	private DPWebApp dp = null;
	private String deviceName;

	@BeforeClass
	public void setUp() {

		dp = new DPWebApp();
		dp.openApp();
		dp.putStep("DP0106002");
		// login
		dp.DP_Login();
	}

    @Test(priority = 1)
	public void Test010_SelectDevice() {

		// 验证头部信息
		dp.verifyIsShown("jiantou");
		dp.verifyIsShown("messageCenter");
		dp.verifyIsShown("help");
		dp.verifyIsShown("testStatus");
		dp.verifyIsShown("userName");

		// 过滤设备
		dp.verifyIsShown("moreFilters");
		dp.clickOn("moreFilters");
		dp.waitByTimeOut(1000);

		deviceName = dp.selectDevices(1).get(0);

		dp.verifyIsShown("controlButton");
		dp.clickOn("controlButton");
        dp.waitByTimeOut(4000);
		if(dp.waitForElementReadyOnTimeOut("controlDeviceConfirm", 3)){
			dp.verifyIsShown("controlDeviceConfirm");
			dp.verifyIsShown("confirmButtonByControl");
			dp.log(dp.getValueOf("controlDeviceConfirm"));
			dp.clickOn("confirmButtonByControl");
			dp.waitByTimeOut(2000);
		}

		dp.verifyIsShown("devices");
		dp.clickOn("devices");
		dp.waitByTimeOut(1000);

	}

    @Test(priority = 2)
	public  void Test020_ReleaseDevice(){

		//
		dp.selectDeviceByName(deviceName);
		dp.waitByTimeout(1000);

		dp.verifyIsShown("releaseButton");
		dp.clickOn("releaseButton");
		dp.waitByTimeout(1000);

		dp.verifyIsShown("confirmButton");
		dp.clickOn("confirmButton");
		dp.waitByTimeout(4000);

	}

    @Test(priority = 3)
	public void Test030_CheckDeviceStatus(){

		String deviceStatus = dp.getDeviceStatusByName(deviceName);
		dp.assertTrue(deviceStatus.equals("Available"), deviceName + " release failed.");
	}

	@AfterClass
	public  void tearDown(){

		dp.close();
	}
}
