package com.bys.devicpass.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.bys.devicepass.libs.Do;
import com.bys.devicepass.locators.AuthLoginLoc;
import com.bys.devicepass.locators.HomePageLoc;
import com.bys.devicpass.utils.Switch;

public class HomePage {
	
	private WebDriver driver = null;
	private Do du = null;
	private JavascriptExecutor js = null;
	private Switch swc = null;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		du = new Do(driver);
		swc = new Switch(driver);
	}
	
	public HomePage navigateToDP(String url) {
		driver.get(url);
		return this;
	}
	
	public void navigateToAuthLogin() {
		
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",du.what("console_link", HomePageLoc.console_link));
		swc.tospecificWindow("AuthLoginHandleName", AuthLoginLoc.AuthLoginHandleName);
	}
	
}
