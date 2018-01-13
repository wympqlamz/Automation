package com.bys.devicpass.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.bys.devicepass.libs.Do;
import com.bys.devicepass.libs.Wait;
import com.bys.devicepass.locators.ControlPageLoc;
import com.bys.devicepass.locators.HomePageLoc;

public class ControlPage {
	private WebDriver driver = null;
	private Do du = null;
	private Wait waiter = null;
	private JavascriptExecutor js = null;
	static String screenshotname = "";
	
	public ControlPage(WebDriver driver) {
		this.driver = driver;
		du = new Do(driver);
		waiter = new Wait(driver);
	}
	
	/*
	 * Get text()='Screenshot' at the right of page
	 */
	public String gettSCName_right() {
		du.waitForElementIsEnable("screentshot_right", ControlPageLoc.screentshot_right_item);
		return du.what("screentshot_right", ControlPageLoc.screentshot_right_item).getText();
		
	}	
	
	
	/*
	 * select screenshot item
	 */
	public ControlPage selectScItem_right() {
		waiter.waitForElementClickable("screentshot_right_item", ControlPageLoc.screentshot_right_item);
		waiter.waitFor(500);
		du.what("screentshot_right_item", ControlPageLoc.screentshot_right_item).click();
		return this;
	}
	
	/*
	 * select Apps item
	 */
	public ControlPage selectAppsItem_right() {
		waiter.waitForElementClickable("apps_item", ControlPageLoc.apps_item);
		waiter.waitFor(800);
		du.what("apps_item", ControlPageLoc.apps_item).click();
		return this;
	}
	
	/*
	 * get version item name under Apps item
	 */
	public String getVersionName_item() {
		waiter.waitForElementClickable("version", ControlPageLoc.version);
		waiter.waitFor(800);
		String str = du.what("version", ControlPageLoc.version).getText();
		return str;
	}

	/*
	 * Install Android app	
	 */
	public ControlPage clickInstallApp() {
		waiter.waitForElementClickable("install_and_run", ControlPageLoc.install_and_run);
		du.what("install_and_run", ControlPageLoc.install_and_run).click();
		return this;
	}
	
	/*
	 * get Install Android app success message
	 */
	public String getInstallAppInfo() {
		String str = du.what("install_info", ControlPageLoc.install_info).getText();
		return str;
	}

	
	
	/*
	 * select advanced item
	 */
	public ControlPage selectadvancedItem() {
		waiter.waitForElementClickable("advanced_item", ControlPageLoc.advanced_item);
		du.what("advanced_item", ControlPageLoc.advanced_item).click();
		return this;
	}
	
	/*
	 * select File Explorer item
	 */
	public ControlPage selectFileExpItem() {
		waiter.waitForElementClickable("file_explore_item", ControlPageLoc.file_explore_item);
		du.what("file_explore_item", ControlPageLoc.file_explore_item).click();
		return this;
	}
	
	/*
	 * select Info item
	 */
	public ControlPage selectInfoItem_right() {
		waiter.waitForElementClickable("info_item", ControlPageLoc.info_item);
		du.what("info_item", ControlPageLoc.info_item).click();
		return this;
	}
	
	/*
	 * select screenshot item
	 */
	public ControlPage selectDevLogItem_right() {
		waiter.waitForElementClickable("device_logs_item", ControlPageLoc.device_logs_item);
		du.what("device_logs_item", ControlPageLoc.device_logs_item).click();
		return this;
	}
	
	/*
	 * Click screenshot at the right of page
	 */
	public ControlPage clickScreenshot_left() {
		waiter.waitForElementClickable("screentshot_left", ControlPageLoc.screentshot_left);
		du.what("screentshot_left", ControlPageLoc.screentshot_left).click();
		return this;
	}

	/*
	 * Get screenshot prompt message
	 */
	public String getScMessage() {
		waiter.waitForElementClickable("screentshotinfo", ControlPageLoc.screentshotinfo);
		String str = du.what("screentshotinfo", ControlPageLoc.screentshotinfo).getText();
		return str;
	}

	/*
	 * Reload page
	 */
	public ControlPage clickReloadAll() {
		waiter.waitForElementClickable("reloadall", ControlPageLoc.reloadall);
		du.what("reloadall", ControlPageLoc.reloadall).click();
		return this;
	}
	
	/*
	 * Release all devices
	 */
	public ControlPage clickReleaseAll() {
		waiter.waitForElementClickable("releaseall", ControlPageLoc.releaseall);
		du.what("releaseall", ControlPageLoc.releaseall).click();
		return this;
	}
	
	/*
	 * Click "Confirm" to release all devices
	 */
	public ControlPage clickRA_Confirm() {
		waiter.waitForElementClickable("releaseconfirmbtn", ControlPageLoc.releaseconfirmbtn);
		du.what("releaseconfirmbtn", ControlPageLoc.releaseconfirmbtn).click();
		return this;
	}
	
	/*
	 * Get release device prompt message
	 */
	public String getRAConfirm_Info() {
		waiter.waitForElementClickable("releaseconfirminfo", ControlPageLoc.releaseconfirminfo);
		String str = du.what("releaseconfirminfo", ControlPageLoc.releaseconfirminfo).getText();
		return str;
	}
	
	/*
	 * Renew
	 */
	public ControlPage clickRenew() {
		waiter.waitForElementClickable("renew", ControlPageLoc.renew);
		du.what("renew", ControlPageLoc.renew).click();
		return this;
	}

	/*
	 * Controlling device fro 1H
	 */
	public ControlPage clickRenew_1Hours() {
		waiter.waitForElementClickable("confirm_40mins", ControlPageLoc.confirm_40mins);
		du.what("confirm_40mins", ControlPageLoc.confirm_40mins).click();
		return this;
	}

	/*
	 * Submit Renew Devices opt
	 */
	public ControlPage clickRenew_Confirm() {
		waiter.waitForElementClickable("confirmbtn", ControlPageLoc.confirmbtn);
		du.what("confirmbtn", ControlPageLoc.confirmbtn).click();
		waiter.waitFor(3000);
		return this;
	}
	
	/*
	 * Get confirm info
	 */
	public String getRC_Info() {
		waiter.waitForElementClickable("confirminfo", ControlPageLoc.confirminfo);
		String str = du.what("confirminfo", ControlPageLoc.confirminfo).getText();
		return str;
	}
	
	/*
	 * click SD for changing definition
	 */
	public ControlPage clickSD_top() {
		waiter.waitForElementClickable("sdbtn_top", ControlPageLoc.sdbtn_top);
		waiter.waitFor(500);
		du.what("sdbtn_top", ControlPageLoc.sdbtn_top).click();
		return this;
	}

	/*
	 * select SD -> Ultra Definition
	 */
	public ControlPage selectSD_Ultra() {
		waiter.waitForElementClickable("ultra_definiton", ControlPageLoc.ultra_definiton);
		du.what("ultra_definiton", ControlPageLoc.ultra_definiton).click();
		return this;
	}
	
	/*
	 * select SD -> High Definition
	 */
	public ControlPage selectSD_High() {
		waiter.waitForElementClickable("High_definition", ControlPageLoc.high_definition);
		du.what("High_definition", ControlPageLoc.high_definition).click();
		return this;
	}

	/*
	 * select SD -> Standard Definition
	 */
	public ControlPage selectSD_Standard() {
		waiter.waitForElementClickable("Standard_definition", ControlPageLoc.standard_definition);
		du.what("Standard_definition", ControlPageLoc.standard_definition).click();
		return this;
	}

	/*
	 * select SD -> Low Definition
	 */
	public ControlPage selectSD_Low() {
		waiter.waitForElementClickable("Low_definition", ControlPageLoc.low_definition);
		du.what("Low_definition", ControlPageLoc.low_definition).click();
		return this;
	}

	/*
	 * Get Definition name at the left-down area of control device panel
	 */
	public String getDefinitionName_Bottom() {
		waiter.waitForElementClickable("sd_bottom", ControlPageLoc.sd_bottom);
		String str = du.what("sd_bottom", ControlPageLoc.sd_bottom).getText();
		return str;
	}

	
	
}
