package com.bys.devicepass.libs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OperateDevices {
	
	private WebDriver driver = null;
	private Do du = null;
	
	public OperateDevices(WebDriver driver) {
		this.driver = driver;
		du = new Do(driver);
	}
	
	/*
	 * Collect all devices on All Devices page 
	 */
	
	public List<WebElement> collectAllDevs(String locatorname, String locatorvalue) {
		
		List<WebElement> elements = du.whats(locatorname, locatorvalue);
		return elements;
	} 
	
	/*  
	 * get Device name through the index on All Devices page  
	 */
	public String getDevName_Android(int index) {
		String devicename = "";
		try {
			 devicename = driver.findElement(By.xpath("//*[@class='device-list']/div[" + index + "]/div/div[3]")).getText();
			
		} catch (Exception e) {
			System.out.println("Can not locate at xpath " + "'" + "devicename: //*[@class='device-list']/div[" + index + "]/div/div[3]" + "'");
		}
		
		return devicename;
		
	}
	
	/*  
	 * get Device Status through the index on All Devices page  
	 */
	public String getDevStatus_Android(int index) {
		String deviceStatus = "";
		try {
			deviceStatus = driver.findElement(By.xpath("//*[@class='device-list']/div[" + index + "]/div/span")).getText();
			
		} catch (Exception e) {
			System.out.println("Can not locate at xpath " + "'" + "deviceStatus: //*[@class='device-list']/div[" + index + "]/div/span" + "'");
		}
		
		return deviceStatus;
	}
	
	public void toBeSelectedDev_Android(int index) {
		String deviceStatus = "";
		try {
			driver.findElement(By.xpath("//*[@class='device-item ng-scope not-selected-device'][ " + index + " ]")).click();
			
		} catch (Exception e) {
			System.out.println("Can not locate at xpath " + "'" + "deviceStatus: //*[@class='device-list']/div[" + index + "]/div/span" + "'");
		}
		
	}

}
