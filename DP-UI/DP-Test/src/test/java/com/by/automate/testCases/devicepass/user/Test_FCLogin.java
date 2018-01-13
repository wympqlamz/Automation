package com.by.automate.testCases.devicepass.user;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;

public class Test_FCLogin {
	
	private DPWebApp dp = null;
	
	@BeforeClass
	public void setUp(){
		
		dp = new DPWebApp();
 		dp.openApp();
	}
	
	@Test
	public void Test010LoginInvalidEmail(){
		
		// 验证元素是否出现, --- 看页面某个东西
		dp.verifyIsShown("");
		
		// 点击某个按钮
		dp.clickOn("");
		
		// 往某个文本框里面输入值
		dp.setValueTo("", "");
		
		// 取值
		dp.getValueOf("");
		
		// 验证值是否跟期望值一致
		
		dp.assertEqual("", "");
		
		
		
		
		/*dp.verifyIsShown("email");
		dp.verifyIsShown("password");
	//	dp.verifyIsShown("loginButton");
		
		dp.setValueTo("email", "invalidUsername@beyondsoft.com");
		dp.waitByTimeOut(500);
		dp.setValueTo("password", "sys123");
		dp.waitByTimeOut(500);
		//dp.clickOn("loginButton");
		dp.KeyPress(61);
		dp.waitByTimeOut(500);
		dp.KeyPress(66);
		dp.waitByTimeOut(2000);
		dp.getScrentShot();
	//	String errorMessage = dp.getValueOf("");
	//	dp.assertEquest("", "Please enter a valid email");
*/	}
	
	/*@Test()
	public void Test020LoginInvalidPassword(){
		
		dp.setValueTo("email", "admin101@beyondsoft.com");
		dp.waitByTimeOut(500);
		dp.setValueTo("password", "123456");
		dp.waitByTimeOut(500);
		dp.clickOn("loginButton");
		dp.waitByTimeOut(500);
		
	}
	
	
	
	@Test
	public void Test030Login(){
		
		dp.setValueTo("email", "admin101@beyondsoft.com");
		dp.waitByTimeOut(500);
		dp.setValueTo("password", "sys123");
		dp.waitByTimeOut(500);
		dp.clickOn("loginButton");
		dp.waitByTimeOut(500);
	}*/
	
	@AfterClass
	public void tearDown(){
		
		dp.close();
		
	}
}
