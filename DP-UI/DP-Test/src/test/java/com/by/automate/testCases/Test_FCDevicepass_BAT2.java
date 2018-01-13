package com.by.automate.testCases;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;

import android.R.integer;

public class Test_FCDevicepass_BAT2 {

	/**
	 * DP -- 冒烟测试
	 */

	private DPWebApp dp = null;

	@BeforeClass
	public void setUp() {

		dp = new DPWebApp();
		dp.openApp();
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

		List<String> deviceList = dp.createGroupAndAddDevices("automation_add", 4);

		for (String string : deviceList) {
			dp.log("Device name : "+ string);
		}

		dp.verifyAddGroup("automation_add", deviceList);

	}
}
