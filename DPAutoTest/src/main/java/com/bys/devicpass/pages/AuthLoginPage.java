package com.bys.devicpass.pages;

import org.openqa.selenium.WebDriver;

import com.bys.devicepass.libs.Do;
import com.bys.devicepass.locators.AuthLoginLoc;

public class AuthLoginPage {
	private WebDriver driver = null;
	private Do du = null;
	
	public AuthLoginPage(WebDriver driver) {
		this.driver = driver;
		du = new Do(this.driver);
	}
	
	public AuthLoginPage setLoginE_mail() {
		du.waitForElementPresent("LoginE_mail", AuthLoginLoc.LoginE_mail);
		//du.what("LoginE_mail", AuthLoginLoc.LoginE_mail).sendKeys("zhaozhijian02@beyondsoft.com");
		du.what("LoginE_mail", AuthLoginLoc.LoginE_mail).sendKeys("admin101@beyondsoft.com");
		return this;
	}
	
	
	
	public AuthLoginPage setLoginPassword() {
		du.waitForElementPresent("Loginpassword", AuthLoginLoc.Loginpassword);
		//du.what("Loginpassword", AuthLoginLoc.Loginpassword).sendKeys("123456");
		du.what("Loginpassword", AuthLoginLoc.Loginpassword).sendKeys("123123");
		return this;
	}
	
	public void loginDP() {
		
		du.what("Sign_inBtn", AuthLoginLoc.Sign_inBtn).click();;
	}
}
