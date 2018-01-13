package com.bys.devicpass.utils;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class Switch {
	private WebDriver driver = null;
	private String currentwindow;
	
	public Switch(WebDriver driver) {
		
		this.driver = driver;
		currentwindow = driver.getWindowHandle();
	}
	
	//Switch to specified window
	public void  tospecificWindow(String partialTitleName, String partialTitleValue) {
		Set<String> handles = driver.getWindowHandles();
		String title;
		try {
			
			for(String handle : handles) {
				title = driver.switchTo().window(handle).getTitle();
				if(title.contains(partialTitleValue)) {
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("No Such name handle: " + partialTitleValue);
			e.printStackTrace();
		}
	}
	
	public void backToCurrentWindows() {
		driver.switchTo().window(currentwindow);
	}
	
}
