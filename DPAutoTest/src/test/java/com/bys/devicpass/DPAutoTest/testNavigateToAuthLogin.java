package com.bys.devicpass.DPAutoTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bys.devicepass.launch.Browsers;
import com.bys.devicepass.launch.BrowsersType;
import com.bys.devicpass.pages.AuthLoginPage;
import com.bys.devicpass.pages.HomePage;


public class testNavigateToAuthLogin {
	Browsers browser = null;
	WebDriver driver = null;
	HomePage homepage = null;
	AuthLoginPage authlogin = null;
	
	
	@BeforeMethod(alwaysRun=true)
	public void init() {
		browser = new Browsers(BrowsersType.chrome);
		this.driver = browser.driver;
		homepage = new HomePage(driver);
		authlogin = new AuthLoginPage(driver);
	}
	
	@Test(groups="Group1")
	public void test1() {
		homepage.navigateToDP("https://www.qa.devicepass.com");
		homepage.navigateToAuthLogin();
		authlogin.setLoginE_mail().setLoginPassword().loginDP();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Login successfully");
		
	}

	@AfterMethod(alwaysRun=true)
	public void release() {
		driver.quit();
	}
}
