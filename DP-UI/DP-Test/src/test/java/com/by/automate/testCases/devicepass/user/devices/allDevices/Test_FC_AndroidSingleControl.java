/*
 * 
 * History
 * Date          		Version     Author    Description(Change)
 * ----------- --- ------------- ----------------------------------------
 * 14:58 2017/11/1		0.1			Jay-Z	  AndroidSingleControl test case for checking list	
 * 
 */

package com.by.automate.testCases.devicepass.user.devices.allDevices;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;


public class Test_FC_AndroidSingleControl {

	private DPWebApp dp = null;
	private List<String> deviceinfo;
	
	@Parameters({"url","username","password"}) 
	@BeforeMethod(alwaysRun=true)
	public void tearUp(String url, String username, String password) {
		dp = new DPWebApp();
		dp.openApp("devices", "alldevices", "alldevices:control");
	
		//dp.DP_Login("zhaozhijian02@beyondsoft.com", "123456");
		//dp.DP_Login("test03@bys.com", "123123");

		dp.openUrl(url);
		dp.DP_Login(username, password);
	
		dp.clickWhat("groups")
		  .clickWhat("All Devices");
		
		deviceinfo = dp.selectAvailableAndroidDev();
		
		dp.clickWhat("controlButton");
		if(deviceinfo.get(1).contains("Available")) {
			dp.clickWhat("confirmButtonByControl");
		 }
		
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ScreenshotMenu");
	}

	/* 
	 * Case:进入Current Use,Current Control时间设置,Current Control页面显示
	 * 
	 * 1.点击鼠标右键，从Control菜单进入
	 * 2.设置控屏时间，如50分钟
	 * 3.进入单Android设备的控屏页面
	 */
	@Test(groups="Group1")
	public void A1_goInToCurrentUse() {
		
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(1000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
		
		dp.getXMLDoc("devices", "alldevices");
		dp.setViewTo("alldevices:groupview");
		dp.waitByTimeout(2000);
		dp.clickWhat("All Devices")
		  .selectAvailableAndroidDev();
		  
		dp.clickWhat("controlButton")
		  .waitForElementClickable("cdcDurationCustomer")
		  .clear("cdcDurationCustomer");
		dp.sendKeys("cdcDurationCustomer", "50");
		
		String str = dp.clickWhat("confirmButtonByControl")
					   .waitForElementClickable("controlDeviceChargedInfo")	
				       .getElementText("controlDeviceChargedInfo");
		Assert.assertEquals(str.contains("Your balance is changed"),true);
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}

	/*
	 * Case:Screenshot
	 * 点击Current Control顶部Screenshot按钮
	 */
	@Test(groups="Group1")
	public void B2_doScreentshot_Top() {
		
		int previousnumber = dp.getCurrentScreenshotNum();
		
		dp.clickWhat("ScreenShottopBtn").waitByTimeOut(2000);
		
		int currentnumber = dp.getCurrentScreenshotNum();
		Assert.assertEquals((currentnumber-previousnumber) == 1, true);
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}
	
	/*
	 * Case:Reload All
	 * 点击Current Control顶部Reload Allt按钮
	 */
	@Test(groups="Group1")
	public void C3_doReloadAll() {
		dp.clickWhat("ReloadAll")
		  .clickWhat("AppsMenu")
		  .clickWhat("ReloadAll")
		  .clickWhat("AdvancedMenu");
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}

	/*
	 * Case:Release All
	 * 点击Current Control顶部Release All按钮
	 */
	@Test(groups="Group1")
	public void D4_doReleaseAll() {
		Assert.assertTrue(true);
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}
	
	/*
	 * Case:Renew
	 * 点击Current Control顶部Renew按钮
	 */
	@Test(groups="Group1")
	public void E5_doRenew() {
		dp.clickWhat("RenewDevices")
		  .clickWhat("cdcDuration20min")
		  .clickWhat("confirmButtonByControl")
		  .waitForElementReadyOnTimeOut("renewDeviceChargedInfo", 20);
		
		String str = dp .getElementText("renewDeviceChargedInfo");
		
		Assert.assertEquals(str.contains("Success"), true);
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}
	
	/*
	 * Case:变更清晰度
	 * 通过选择顶部清晰度菜单SD，变换清晰度
	 */
	@Test(groups="Group1")
	public void F6_doDefination() {
		dp.clickWhat("setDefinition")
		  .clickWhat("UD")
		  .clickWhat("setDefinition")
		  .clickWhat("HD")
		  .clickWhat("setDefinition")
		  .clickWhat("SD")
		  .clickWhat("setDefinition")
		  .clickWhat("LD");
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}

	/*
	 * Case: Screenshot界面切换显示
	 * 点击屏幕右侧Screenshot菜单项
	 */
	@Test(groups="Group1")
	public void G7_switchScreenshotItem() {
		int beforeamount = dp.getCurrentScreenshotNum();
		
		dp.clickWhat("AppsMenu")
		  .clickWhat("ScreenshotMenu")
		  .clickWhat("AdvancedMenu")
		  .clickWhat("ScreenshotMenu")
		  .clickWhat("InfoMenu")
		  .clickWhat("ScreenshotMenu");
		
		int afteramount = dp.getCurrentScreenshotNum();
		
		Assert.assertEquals(beforeamount == afteramount, true);
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}
	
	/*
	 * Case:Apps界面切换显示
	 * 点击屏幕右侧Apps菜单项
	 */
	@Test(groups="Group1")
	public void H8_switchAppsItem() {
		dp.clickWhat("AppsMenu")
		  .clickWhat("ScreenshotMenu")
		  .clickWhat("AppsMenu")
		  .clickWhat("AdvancedMenu")
		  .clickWhat("AppsMenu")
		  .clickWhat("InfoMenu")
		  .clickWhat("AppsMenu");
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}
	
	/*
	 * Case:安装app
	 * 1.点击设备屏幕右侧Apps菜单下的Install按钮Action
	 * 2.鼠标悬停在Installing…x上
	 * 3.x此为数字，即当前页面显示的设备数量
	 */
	@Test(groups="Group1")
	public void I9_InstallApps() {
		dp.clickWhat("AppsMenu")
		  .clickWhat("InstallBtn")
		  .waitForElementReadyOnTimeOut("InstallSuccessMsg", 100);
		
		String str = dp.getElementText("InstallSuccessMsg");
		
		Assert.assertEquals(str.contains("Install app successfully"), true);
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}
	
	/*
	 * Case:下载app
	 * 点击设备屏幕右侧Apps菜单下的Download按钮Action
	 */
	@Test(groups="Group1")
	public void J10_DownloadApp() {
		boolean flag = dp.clickWhat("AppsMenu").downloadApp("downloadBtn1", "downloadappname1", 100);
		Assert.assertEquals(flag, true);
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
		
	}
	
	/*
	 * Case:Apps Edit
	 * 点击设备屏幕右侧Apps菜单下的Download按钮Action
	 * 修改app名字，并添加注释。
	 */
	@Test(groups="Group1")
	public void K11_EditApp() {
		
		//Generate random number using for appname version and bug ID
		String count1 = dp.getRandomNum(1);
		String count2 = dp.getRandomNum(1);
		String count3 = dp.getRandomNum(1);
		String count4 = dp.getRandomNum(4);
		String count5 = dp.getRandomNum(4);
		String appnewname = "TestAppnameV"+count1+"."+count2+"."+count3;
		String remark = "Fix issue ID: "+count4+","+count5+"...";
		
		//Edit app name
		dp.clickWhat("AppsMenu")
		  .clickWhat("EditApp1")
		  .waitForElementClickable("EditAppFileName1")
		  .clear("EditAppFileName1");
		dp.sendKeys("EditAppFileName1", appnewname);
		
		//Edit app remark
		dp.waitForElementClickable("EditAppRemark1")
		  .clear("EditAppRemark1");
		dp.sendKeys("EditAppRemark1", remark);
		
		//Submit
		dp.clickWhat("EditAppSubmitBtn");
		
		//Display name on Apps screen.
		String appnamedp = dp.waitForElementClickable("downloadappname1").getElementText("downloadappname1");
		appnamedp = appnamedp.substring(0, appnamedp.length()-4);
		
		//Re-get the app remark
		String js = "return document.getElementsByName(\"remark\")[0].value";
		String appremarkdp = dp.clickWhat("EditApp1").waitForElementClickable("EditAppRemark1").excuteJs(js);
		dp.clickWhat("EditAppCloseBtn");
		
		//Check appname if correct
		Assert.assertEquals(appnamedp.equals(appnewname), true);
		
		//Check remark if correct
		Assert.assertEquals(appremarkdp.equals(remark), true);
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}

	/*
	 * Case:弹出/浮动菜单-全屏
	 * 点击设备右上角菜单中的Full Screen进入全屏模式
	 */
	@Test(groups="Group1")
	public void L12_EnterFullScreen() {
		boolean flag = dp.clickWhat("normalsolodeviceBtn")
						 .clickWhat("fullScreenBtn")
						 .waitForElementClickable("fullScreenCancelBtn")
						 .clickOn("fullScreenCancelBtn");
		
		Assert.assertEquals(flag, true);
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	
	}
	
	/*
	 * Case:Advanced - Navigation - delete
	 * 1. 点击屏幕右侧Advanced菜单项
	 * 2. 在文本框Navigation中输入www.baidu.com
	 * 3. 查看是否在手机浏览器中打开网址。 //需要Appium实现现操作手机来验证
	 * 4. 点击Navigation后便Reset按钮
	 * 5. 查看是否可以清空文本框，手机关闭浏览器。//Html无法抓到文本值，需要前端帮忙查看
	 */
	@Test(groups="Group1")
	public void M13_AdvancedNavigation() {
		String url = "http://www.baidu.com";
		
		dp.clickWhat("AdvancedMenu")
		  .sendKeys("NavigationTextfield", url);
		dp.clickWhat("NavigationTextfieldSubmitBtn");
		dp.waitByTimeOut(3000);
		dp.clickWhat("NavigationTextfieldClearBtn");
		
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
		
	}
	
	/*
	 * Case:File Explorer界面切换显示
	 * 点击屏幕右侧File Explorer菜单项
	 */
	@Test(groups="Group1")
	public void N14_SwitchFileExplorer() {
		dp.clickWhat("FileExploreMenu")
		  .clickWhat("AppsMenu")
		  .clickWhat("AdvancedMenu")
		  .clickWhat("FileExploreMenu");
		  
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}
	
	/*
	 * Case:Info界面切换显示
	 * 点击屏幕右侧Info菜单项
	 * 
	 */
	@Test(groups="Group1")
	public void O15_SwitchInfo() {
		dp.clickWhat("InfoMenu")
		  .clickWhat("FileExploreMenu")
		  .clickWhat("AppsMenu")
		  .clickWhat("AdvancedMenu")
		  .clickWhat("FileExploreMenu")
		  .clickWhat("InfoMenu");
		  
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}
	
	/*
	 * Case:Device Logs界面
	 * Device Logs界面切换显示
	 * 
	 */
	@Test(groups="Group1")
	public void P16_SwitchDeviceLogs() {
		dp.clickWhat("LogsMenu")
		  .clickWhat("FileExploreMenu")
		  .clickWhat("AppsMenu")
		  .clickWhat("AdvancedMenu")
		  .clickWhat("FileExploreMenu")
		  .clickWhat("InfoMenu")
		  .clickWhat("LogsMenu");
		  
		//release device
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn");
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		dp.waitByTimeOut(1000);
		dp.close();

	}
	
}



