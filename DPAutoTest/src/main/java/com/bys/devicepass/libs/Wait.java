package com.bys.devicepass.libs;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {
	private WebDriver driver;
	
	public Wait(WebDriver driver) {
		this.driver = driver;
	}
	
	//An element is present on the DOM of a page and is visible or invisible
	public void waitForElementpresent(String locatorname, String locatorvalue) {
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorvalue)));
		} catch (NoSuchElementException e) {
			System.out.println("Can not locate at xpath " + "'" + locatorname + ": " + locatorvalue + "'");
		}
	}

	//An element is present on the DOM of a page and is visible
	public void waitForElementvisible(WebElement element) {
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
	} 
	
	//An element is visible and enabled such that you can click it
	public void waitForElementClickable(String locatorname, String locatorvalue) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(locatorvalue)));
	}
	
	public void waitFor(long timeout) {
		
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
} 
