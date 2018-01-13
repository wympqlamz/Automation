package com.by.automate.testCases.BAT.android;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;

public class Test_FCCreateDeviceGroup {

	private DPWebApp dp = null;
	private List<String> names;
	private String groupName = "addGroupByAutomation";

	@BeforeClass
	public void setUp() {

		dp = new DPWebApp();
		dp.openApp();
		dp.putStep("DP0106003");
	}

    @Test(priority = 1)
	public void Test010_selectDevices() {

		// login
		dp.DP_Login();

		// 验证头部信息
		dp.verifyIsShown("jiantou");
		dp.verifyIsShown("messageCenter");
		dp.verifyIsShown("help");
		dp.verifyIsShown("testStatus");
		dp.verifyIsShown("userName");

	}

    @Test(priority = 2)
	public void Test020_selectDevicesAndtoCreateGroup() {

		names = dp.createGroupAndAddDevices(groupName, 3);

	}

    @Test(priority = 3)
	public void Test030VerifyAddGroup(){

		dp.verifyAddGroup(groupName, names);
	}

    @Test(priority = 4)
	public void Test040DeleteGroup(){

		dp.deleteDeviceGroupByName(groupName);
	}

	@AfterClass
	public void tearDown(){

		dp.close();
	}

}
