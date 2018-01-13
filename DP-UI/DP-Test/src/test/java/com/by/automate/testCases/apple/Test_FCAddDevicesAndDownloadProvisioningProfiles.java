package com.by.automate.testCases.apple;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.AppleApp;

public class Test_FCAddDevicesAndDownloadProvisioningProfiles {
	
	private AppleApp apple = null;
	
	@BeforeClass
	public void setUp(){
		apple = new AppleApp();
		apple.openApp();
		
	}
	
	@Test
	public void Test010_loginApple(){
		
		apple.apple_Login();
	}
	
	@Test
	public void Test020_addDevices(){
		
		apple.verifyIsShown("overview");
		apple.verifyIsShown("cretificates");
		apple.clickOn("cretificates");
		apple.verifyIsShown("title");
		apple.verifyIsShown("deviceAll");
		apple.clickOn("deviceAll");
		
		apple.verifyIsShown("contentDevices");
		String title = apple.getValueOf("contentDevices").trim();
		
		for (int i = 0; i < 5; i++) {
			if(!title.equals("All Devices")){
				apple.waitByTimeOut(2000);
				apple.refresh();
				apple.verifyIsShown("contentDevices");
				title = apple.getValueOf("contentDevices").trim();
			}else{
				if(apple.waitForElementReadyOnTimeOut("addDevices",5)){
					apple.clickOn("addDevices");
					apple.waitByTimeout(3000);
					apple.verifyIsShown("name");
					apple.verifyIsShown("udid");
				
					break;
				}else{
					apple.refresh();
				}
			}
	
		}
		
		apple.setValueTo("name", "test");
		apple.waitByTimeOut(1000);
		apple.setValueTo("udid", "d8bb1a6e1633aad0f75c93da500b70628b835001");
		apple.waitByTimeOut(2000);
		apple.verifyIsShown("cancel");
		apple.rollToElement("cancel");
		apple.verifyIsShown("continue");
		apple.clickElementByJS("continue");
		
		// check device
		apple.waitByTimeOut(2000);
		apple.verifyIsShown("message");
		apple.rollToElement("registerButton");
		apple.verifyIsShown("cancelButton");
		apple.verifyIsShown("cancelBack");
		//apple.verifyIsShown("registerButton");
		
		String message = apple.getValueOf("message").trim();
		
		if(message.equals("The following devices are either already present and were not modified or contain invalid identifiers.")){
			apple.clickOn("cancelButton");
		}else{
			apple.clickOn("registerButton");
		}
		apple.waitByTimeOut(4000);
	}
	
	@Test
	public void Test030_addProvisioningProfile(){
		
		apple.verifyIsShown("ppAll");
		apple.clickOn("ppAll");
		
		apple.verifyIsShown("contentPP");
		String title = apple.getValueOf("contentPP").trim();
		
		for (int i = 0; i < 5; i++) {
			if(!title.equals("iOS Provisioning Profiles")){
				apple.waitByTimeOut(2000);
				apple.refresh();
				apple.verifyIsShown("contentPP");
				title = apple.getValueOf("contentPP").trim();
			}
			break;
		}
		
		apple.verifyIsShown("allNames", 2);
		List<WebElement> names = apple.getElements("allNames");
		int index = 0;
		for (int i = 0; i < names.size(); i++) {
			String value = names.get(i).getText().trim();
			if(value.equals("WDA3")){
				index = i;
				break;
			}
		}
		
		apple.rollToElementByElement(names.get(index));
		apple.waitByTimeOut(2000);
		names.get(index).click();
		apple.verifyIsShown("editButton");
		apple.waitByTimeOut(2000);
		apple.clickOn("editButton");
		
		apple.log("Done");
	}
	
}
