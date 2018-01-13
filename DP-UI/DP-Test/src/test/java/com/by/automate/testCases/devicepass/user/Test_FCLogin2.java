package com.by.automate.testCases.devicepass.user;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;

public class Test_FCLogin2 {

	private DPWebApp dp = null;
	
	@BeforeClass
	public void setUp(){
		
		dp = new DPWebApp();
		dp.openApp();
		
	}
	
	@Test
	public void Test1(){
		
		dp.verifyIsShown("email");
		dp.verifyIsShown("password");
		
		dp.setValueTo("email","admin101@beyondsoft.com");
		dp.waitByTimeOut(1000);
		dp.setValueTo("password","sys123");
		
		
		dp.clickOn("loginButton");
		dp.waitByTimeOut(2000);
	}
	
	@AfterClass
	public void tearDown(){
		
		dp.close();
	}
	
	
	
	
	
	
}
