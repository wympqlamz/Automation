package com.bys.devicpass.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.bys.devicepass.libs.Do;
import com.bys.devicepass.libs.OperateDevices;
import com.bys.devicepass.libs.Wait;
import com.bys.devicepass.locators.DevicesPageLoc;

public class DevicesPage {
	
	private WebDriver driver = null;
	private Do du = null;
	private Wait waiter = null;
	OperateDevices od = null;
	
	public DevicesPage(WebDriver driver) {
		this.driver = driver;
		du = new Do(driver);
		waiter = new Wait(driver);
		od = new OperateDevices(driver);
	}
	
	public DevicesPage clickItem_AllDevics() {
		waiter.waitForElementClickable("alldevices", DevicesPageLoc.alldevices);
		du.what("alldevices", DevicesPageLoc.alldevices).click();
		return this;
	}
	
	public void controlDev_Android() {
		int i =1;
		boolean flag = false;
		List<WebElement> elements = od.collectAllDevs("allmobiles", DevicesPageLoc.allmobiles);

		//check unit if it's a Android mobile
		for(; i < elements.size() + 1; i++) {
			String deviceName = od.getDevName_Android(i);
//System.out.println("ssssssssssssssss-------------------------------"+deviceName);
			String deviceStatus = od.getDevStatus_Android(i);
			if(elements.size() == i) {
				flag = true;
			}
			//check if it matches with Apple mobile
			if (deviceName.matches("Apple\\b.*")) {
				if(flag) {
					System.out.println("Currently, all Android units are ordered by other people! Pls execute scripts later!");
					flag = false;
					driver.quit();
					System.exit(0);
				}
				continue;
			} else if (deviceStatus.equals("Available")) {
				od.toBeSelectedDev_Android(i);
				System.out.println(deviceName + " is preparing for test!");
				du.waitForElementIsEnable("controlbtn_AllDevices", DevicesPageLoc.controlbtn_AllDevices);
				du.what("controlbtn_AllDevices", DevicesPageLoc.controlbtn_AllDevices).click();
				du.waitForElementIsEnable("confirm_20mins", DevicesPageLoc.confirm_20mins); //set device use time(20 mins)
				du.what("confirm_20mins", DevicesPageLoc.confirm_20mins).click();
				du.waitForElementIsEnable("confirmbtn", DevicesPageLoc.confirmbtn);
				du.what("confirmbtn", DevicesPageLoc.confirmbtn).click();
				int b = 1;
				i = b;
				break;
			} else {
				if(flag) {
					System.out.println("Currently, no Android unit can be operated! Pls excute scripts later!");
					flag = false;
					driver.quit();
					System.exit(0);
				}
			}
			
		}
	}
}
