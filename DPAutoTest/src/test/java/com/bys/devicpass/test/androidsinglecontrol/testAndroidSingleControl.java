package com.bys.devicpass.test.androidsinglecontrol;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bys.devicepass.launch.Browsers;
import com.bys.devicepass.launch.BrowsersType;
import com.bys.devicepass.libs.Do;
import com.bys.devicpass.pages.AuthLoginPage;
import com.bys.devicpass.pages.ControlPage;
import com.bys.devicpass.pages.DevicesPage;
import com.bys.devicpass.pages.HomePage;
import com.bys.devicpass.utils.Switch;

public class testAndroidSingleControl {
	Browsers browser = null;
	Do du = null;
	WebDriver driver = null;
	HomePage homepage = null;
	AuthLoginPage authloginpage = null;
	DevicesPage devicepage = null;
	ControlPage controlpage = null;
	Switch switchTo = null;
	
	@BeforeMethod(alwaysRun=true)
	public void init() {
		browser = new Browsers(BrowsersType.chrome);
		this.driver = browser.driver;
		du = new Do(driver);
		homepage = new HomePage(driver);
		authloginpage = new AuthLoginPage(driver);
		controlpage = new ControlPage(driver);
		switchTo = new Switch(driver);
		devicepage = new DevicesPage(driver);
		//homepage.navigateToDP("https://www.qa.devicepass.com").navigateToAuthLogin();
		homepage.navigateToDP("https://10.23.0.34").navigateToAuthLogin();
		authloginpage.setLoginE_mail().setLoginPassword().loginDP();
		devicepage.clickItem_AllDevics().controlDev_Android();
		
	}
	
	
	/*
	 * case183:进入Current Use
	 * case184:Current Control时间设置
	 * case185:Current Control页面显示
	 */
	@Test(groups="Group1")
	public void A1_goToCurrentUse () {
		 Assert.assertEquals(controlpage.gettSCName_right(), "Screenshot", "Not find Screenshot item!");
	}
	
	/*
	 * case186:Screenshot
	 */
	@Test(groups="Group1")
	public void A2_doScreenshot_Left() {
		Assert.assertEquals(controlpage.clickScreenshot_left().getScMessage(), "Success", "Not find 'Success' info!");
	}
	
	/*
	 * case187:Reload All
	 */
	@Test(groups="Group1")
	public void A3_reloadAll() {
		Assert.assertEquals(controlpage.clickReloadAll().getDefinitionName_Bottom(), "SD", "Not find 'SD' info!");
	}

	/*
	 * case188:Release All
	 */
	@Test(groups="Group1")
	public void A4_releaseall() {
		Assert.assertTrue(true);
	}
	
	/*
	 * case189:Renew
	 */
	@Test(groups="Group1")
	public void A5_Renew() {
		Assert.assertEquals(controlpage.clickRenew().clickRenew_1Hours().clickRenew_Confirm().getRC_Info(), "Success", "Not find 'Success' info!");
	}
	
	/*
	 * case190:变更清晰度 UD/HD/SD/LD
	 */
	@Test(groups="Group1")
	public void A6_definition() {
		Assert.assertEquals(controlpage.clickSD_top().selectSD_Ultra().getDefinitionName_Bottom(), "UD", "Not find 'UD' info!");
		Assert.assertEquals(controlpage.clickSD_top().selectSD_High().getDefinitionName_Bottom(), "HD", "Not find 'HD' info!");
		Assert.assertEquals(controlpage.clickSD_top().selectSD_Standard().getDefinitionName_Bottom(), "SD", "Not find 'SD' info!");
		Assert.assertEquals(controlpage.clickSD_top().selectSD_Low().getDefinitionName_Bottom(), "LD", "Not find 'LD' info!");
		
	}
	
	/*
	 * case191:Screenshot界面切换显示
	 */
	@Test(groups="Group1")
	public void A7_SwitchSCTab() {
		
		String str = controlpage.selectScItem_right()
								.selectAppsItem_right()
								.selectadvancedItem()
								.selectFileExpItem()
								.selectInfoItem_right()
								.selectDevLogItem_right()
								.selectScItem_right()
								.clickScreenshot_left()
								.getScMessage();
		
		
		Assert.assertEquals(str, "Success", "Not find 'Success' info!");
	}

	/*
	 * case192:Apps界面切换显示
	 */
	@Test(groups="Group1")
	public void A8_SwitchAppsTab() {
		
		String str = controlpage.selectAppsItem_right()
								.selectadvancedItem()
								.selectFileExpItem()
								.selectInfoItem_right()
								.selectDevLogItem_right()
								.selectAppsItem_right()
								.getVersionName_item();
		
		Assert.assertEquals(str, "Version", "Not find 'Version' info!");
	}

	/*
	 * case193:安装app
	 */
	@Test(groups="Group2")
	public void A9_InstallApp() {
		String str = controlpage.selectAppsItem_right().clickInstallApp().getInstallAppInfo();
		System.out.println("------------------------------6");
		Assert.assertEquals(str.contains("Install app successfully"), true, "Not find 'Install app successfully' info");
	}


	@AfterMethod(alwaysRun=true)
	public void release() {
		Assert.assertEquals(controlpage.clickReleaseAll().clickRA_Confirm().getRAConfirm_Info(), "Your balance is changed", "Not find 'Your balance is changed' info!");
		driver.quit();
	}
	
}
