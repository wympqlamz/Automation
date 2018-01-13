package com.by.automate.testCases.devicepass.user;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;

public class Test_FCHelp {

	private DPWebApp dp = null;

	@BeforeClass
	public void setUp() {

		dp = new DPWebApp();
		dp.openApp();

	}
	
	/**
	 * 使用有效账号登录到DP。 
	 */
	@Test
	public void test010LoginDevicePass() {

		dp.verifyIsShown("logo");
		dp.verifyIsShown("accountLabel");
		dp.verifyIsShown("passwordLabel");
		dp.verifyIsShown("email");
		dp.verifyIsShown("password");
		dp.verifyIsShown("loginButton");
		dp.verifyIsShown("forgottenPwd");
		dp.verifyIsShown("build");
		dp.verifyIsShown("copyRight");
		dp.verifyIsShown("bySoft");
		
		dp.setValueTo("email", "admin101@beyondsoft.com");
		dp.waitByTimeOut(500);
		dp.setValue("password", "sys123");
		dp.waitByTimeOut(500);
		dp.clickOn("loginButton");
		dp.waitByTimeout(5000);
		

	}
	
	@AfterClass
	public void tearDowm(){
		
		// 关闭浏览器
		dp.close();
	}
	


}
