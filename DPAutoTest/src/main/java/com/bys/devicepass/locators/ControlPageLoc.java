package com.bys.devicepass.locators;

public interface ControlPageLoc {
	String screentshot_left = "//div[@class='filter-bar clear-fix']/descendant::span[text()='Screenshot']";
	
	String reloadall = "//button[descendant::span[contains(text(),'Reload All')]]";
	
	String releaseall = "//button[parent::div[contains(@class,'filter')]][contains(@class,'release-all')]";
	String releaseconfirmbtn = "//div[contains(@class,'modal')]/button[contains(@class,'primary')]";
	String releaseconfirminfo = "//h4[parent::div[contains(@class,'growl')]][contains(text(), 'Your ')]";
	
	String renew = "//button[parent::div[@class='filter-bar clear-fix']][descendant::span[text()='Renew']]";
	String confirm_40mins = "//div[@class='btn-group pull-left']/button[4]";
	String confirmbtn = "//div[@class='modal-footer']/button[1]";
	String confirminfo = "//h4[parent::div[contains(@class,'growl-message ')]]";
	
	String sdbtn_top = "//div[contains(@class,'clear-fix')]/span[contains(@class,'unselectable ')]/a[contains(@class,'toggle')]/span[contains(@class,'binding')]";
	//String sdbtn_top = "/html/body/div[1]/div[3]/div/div/div/div[1]/span[1]/a/span[1]";
	
	String ultra_definiton = "//span[contains(text(),'Ultra ')]";
	String high_definition = "//span[contains(text(),'High ')]";
	String standard_definition = "//span[contains(text(),'Standard ')]";
	String low_definition = "//span[contains(text(),'Low ')]";
	String sd_bottom = "//div[contains(@class,'extend-column')]/span[contains(@class,'label')]";
	
	String screentshot_right_item = "//p[contains(text(),'Screenshot')]";
	String screentshotinfo = "//h4[parent::div[@class='growl-message ng-binding']][text()='Success']";

	String apps_item = "//p[parent::div[@class='device-operate-action-handle operate-action-apps']][text()='Apps']";
	String version = "//span[text()='Version'][ancestor::th[contains(@class,'sortable ')]]";
	String install_and_run = "//table[contains(@class,'border-top')]/descendant::span[contains(@class,'action-install')]";
	String install_info = "//div[contains(text(),'Install app successfully')]";
	
	//String screenshot_right = "//div[contains(@class,'screenshot active')][descendant::p[text()='Screenshot']]";
	String advanced_item = "//p[contains(text(),'Advanced')][contains(@class,'binding')]";
	String file_explore_item = "//p[contains(text(),'File Explorer')]";
	String info_item = "//p[contains(text(),'Info')][contains(@class,'binding')]";
	String device_logs_item = "//p[contains(text(),'Device Logs')][contains(@class,'binding')]";
	
	
}
