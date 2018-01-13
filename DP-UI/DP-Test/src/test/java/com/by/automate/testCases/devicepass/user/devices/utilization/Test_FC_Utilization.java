 
/* History
 * Date          		Version     Author    Description(Change)
 * ----------- --- ------------- ----------------------------------------
 * 10:48 2017/11/3		0.1			Jay-Z	  Utilization test case for checking list	
 * 
 */

package com.by.automate.testCases.devicepass.user.devices.utilization;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;

public class Test_FC_Utilization {
	private DPWebApp dp = null;
	
	@Parameters({"url","username","password"}) 
	@BeforeMethod(alwaysRun=true)
	public void tearUp(String url, String username, String password) {
	
		dp = new DPWebApp();
		dp.openApp("devices", "alldevices", "alldevices:control");
		dp.openUrl(url);
		dp.DP_Login(username, password);
		dp.clickWhat("All Devices");
	}
	
	/*
	 * Case:查下界面显示
	 * 查看界面UI是否显示合理
	 */
	@Test(groups="Group2")
	public void A1_utilizationUI() {
		dp.getXMLDoc("devices", "utilization");
		dp.setViewTo("utilization:deviceUtilizationview");
		dp.waitByTimeOut(2000);
		
		dp.clickWhat("Utilization");
		
		//UI 显示Device Brand Chart
		Assert.assertEquals(dp.verifyIsShown("uDBChart"), true);
		
		//UI 显示Actual usage hours
		Assert.assertEquals(dp.verifyIsShown("AusTop20"), true);
				
		//UI 显示Actual usage hours Avg Brand
		Assert.assertEquals(dp.verifyIsShown("AuhAvgBrand"), true);
		
	}
	
	/*
	 * Case:设备利用率的数据
	 * 查看第一个设备利用率是否超过100%
	 */
	@Test(groups="Group2")
	public void B1_uCalculator() {
		dp.getXMLDoc("devices", "utilization");
		dp.setViewTo("utilization:deviceUtilizationview");
		dp.waitByTimeOut(2000);
		dp.clickWhat("Utilization");
		
		List<WebElement> charbars = null;
		String temp = "";
		double percentchart = 0;
		
		//Get chart bar percent of the first brand including Actual and Billable under Top20
		dp.waitForElementReadyOnTimeOut("FirstBars", 20);
		charbars = dp.getElements("FirstBars");
		for(int i=0; i<charbars.size(); i++) {
			temp = charbars.get(i).getText();
			if(0 == i) {
				percentchart = Double.parseDouble(temp.substring(0, charbars.size()-1));
				Assert.assertEquals(percentchart < 100, true);
				dp.clickWhat("Billablehours");
			}
			if(1 == i) {
				percentchart = Double.parseDouble(temp.substring(0, charbars.size()-1));
				Assert.assertEquals(percentchart < 100, true);
			}

		}
		
	}
	
	/*
	 * Case:查看设备使用记录
	 * 随机选择一个品牌来查看设备的使用记录
	 * (控制一台设备，然后释放，查看设备使用记录)
	 */
	@Parameters({"username1","password1","username","password"})
	@Test(groups="Group2")
	public void C1_uRecords(String username1, String password1, String username, String password) {
		
		List<String> deviceinfo;
		String mobilebrand = "";
		String mobileserial = "";
		String current_useremailadd = "";
				
		//logout and login with common user for generating device operation records.
		dp.longin_NewClient(username1, password1)
		  .clickWhat("All Devices");
		
		//Get Device status and name
		deviceinfo = dp.selectAvailableAndroidDev();
			
		//Control device
		dp.clickWhat("controlButton");
		if(deviceinfo.get(1).contains("Available")) {
			dp.clickWhat("confirmButtonByControl");
		 }
		deviceinfo.clear();
		
		//Click control device name
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("controldevicename");
		
		//获取被控制设备的Brand和serial
		mobilebrand = dp.getDeviceInfomation("modelinfoBtn", "Manufacturerinfo");
		mobileserial = dp.getDeviceInfomation("serialinfoBtn", "Serialinfo");
		dp.clickWhat("closeBtn");
		
		//Renew option
		dp.clickWhat("RenewDevices")
		  .clickWhat("cdcDuration20min")
		  .clickWhat("confirmButtonByControl")
		  .waitForElementReadyOnTimeOut("renewDeviceChargedInfo", 30);
		
		//release device
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.clickWhat("ReleaseAll")
		  .clickWhat("ReleaseAllComfirmBtn"); 
		
		//获取当前用户的Email地址
		dp.getXMLDoc("management", "users");
		dp.setViewTo("users:usersPage");
		dp.waitByTimeout(2000);
		current_useremailadd = dp.getCurrentUserEmailAddre();
		
		//logout and login with system admin user for checking device operation records.
		dp.getXMLDoc("devices", "currentUse");
		dp.setViewTo("currentUse:screenshotL");
		dp.waitByTimeout(2000);
		dp.longin_NewClient(username, password)
		  .clickWhat("devices");
		deviceinfo = dp.selectAvailableAndroidDev();
			
		//获取普通操作用户所在的Company，Team和自己的名字
		dp.getXMLDoc("management", "users");
		dp.setViewTo("users:usersPage");
		dp.waitByTimeout(2000);
		Map<String, String> usermessage = dp.getUserInfo(current_useremailadd);
		String usercompany = usermessage.get("Company");
		String userteam = usermessage.get("Team");
		String usernameown = usermessage.get("Name");

		dp.getXMLDoc("devices", "utilization");
		dp.setViewTo("utilization:deviceUtilizationview");
		dp.waitByTimeOut(2000);
		dp.clickWhat("Utilization");
		List<String> devicerecords = dp.getDeviceOperationRecords(mobilebrand, mobileserial);
		int count = 0;
		for(int i=0; i<devicerecords.size(); i++) {
			if(0 == count || 4 == count || 8 == count ) {
				if(0 == count) {
					Assert.assertEquals(devicerecords.get(i).equals("Release device"), true);
					count++;
					continue;
				}
				if(4 == count) {
					Assert.assertEquals(devicerecords.get(i).equals("Proactive release"), true);
					count++;
					continue;
				}
				if(8 == count) {
					Assert.assertEquals(devicerecords.get(i).equals("Charge for renewing device"), true);
					count++;
					continue;
				}
				if(12 == count) {
					Assert.assertEquals(devicerecords.get(i).equals("Charge for device control"), true);
					count++;
					continue;
				}
			}else if(1 == count || 5 == count || 9 == count || 13 == count) {
				Assert.assertEquals(devicerecords.get(i).equals(usercompany), true);
				count++;
				continue;
			}else if(2 == count || 6 == count || 10 == count  || 14 == count) {
				Assert.assertEquals(devicerecords.get(i).equals(userteam), true);
				count++;
				continue;
			}else if(3 == count || 7 == count || 11 == count  || 15 == count) {
				Assert.assertEquals(devicerecords.get(i).equals(usernameown), true);
				count++;
				continue;
			}
		}
	}
		
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		dp.close();
	}

}