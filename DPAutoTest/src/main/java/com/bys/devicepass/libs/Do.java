package com.bys.devicepass.libs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Do {
	
	private WebDriver driver;
	private Wait waiter;
	
	public Do(WebDriver driver) {
		
		this.driver = driver;
		waiter = new Wait(driver);
	}
	
	public WebElement what(String locatorname, String locatorvalue) {
		WebElement we = null;
		
		try {
			we = driver.findElement(By.xpath(locatorvalue));
		} catch (NoSuchElementException e) {
			
			System.out.println("Can not locate at xpath " + "'" + locatorname + ": " + locatorvalue + "'");
		}
		return we;
	}
	
	public List<WebElement> whats(String locatorname,  String locatorvalue) {
		List<WebElement> wes = null;
		try {
			
			wes = driver.findElements(By.xpath(locatorvalue));
			
		} catch (NoSuchElementException e) {
			
			System.out.println("Can not locate at xpath " + "'" + locatorname + ": " + locatorvalue + "'");
		}
		
		return wes;
	} 

	public WebElement whatCSS (String locatorname,  String locatorvalue) {
		WebElement we = null;
		try {
			we = driver.findElement(By.xpath(locatorvalue));
		} catch (NoSuchElementException e) {
			System.out.println("Can not locate at xpath " + "'" + locatorname + ": " + locatorvalue + "'");
		}
		
		return we;
	}
	
	public void waitForElementPresent(String locatorname, String locatorvalue) {
		waiter.waitForElementpresent(locatorname, locatorvalue);
	}
	
	public void waitForElementIsEnable(String locatorname, String locatorvalue) {
		waiter.waitForElementClickable(locatorname, locatorvalue);
	}
}
