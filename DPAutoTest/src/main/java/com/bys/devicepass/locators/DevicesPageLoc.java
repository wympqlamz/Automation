package com.bys.devicepass.locators;

public interface DevicesPageLoc {
	String alldevices = "//a[child::span[contains(text(),'All Devices')]]";
	String allmobiles = "//div[@class='device-list']/div[@class='device-item ng-scope not-selected-device']";
	String controlbtn_AllDevices = "//button[@class='btn btn-sm btn-primary btn-device-control']";
	String confirm_20mins = "//div[@class='btn-group pull-left']/button[2]";
	String confirmbtn = "//div[@class='modal-footer']/button[1]";
	
}
