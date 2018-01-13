/**
 * 版权 @Copyright: 2017 Copyright ® 2017 Wuhan beyondsoft Co. Ltd.
 * 项目名称：DP-Test
 * 文件名称： Test_FC_Devicesmanage_smoke.java
 * 包名：com.by.automate.testCases.BAT.android
 * 创建人：@author liujia@beyondsoft.com
 * 创建时间：2017年5月12日/上午10:45:30
 * 修改人：liujia@beyondsoft.com
 * 修改时间：2017年6月15日/下午10:35:30
 * 修改备注：
 */
package com.by.automate.testCases.BAT.android;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;
import com.by.automate.utils.CommonTools;

/**
 * 包名称： com.by.automate.testCases.devicepass.user.devices.allDevices
 * 类名称：Test_FC_Devicesmanage_smoke<br/>
 * 类描述：<br/>
 * @version <br/>
 * TODO
 */
public class Test_FC_Devicesmanage_smoke
{
	private DPWebApp dp = null;
	private String booktime_tmp, starttime_tmp, startday_tmp, PhoneName;
	List<String> deviceNames;

	@BeforeClass
	public void setUp(){

		dp = new DPWebApp();
//		dp.getXMLDoc("devices","alldevices");
 		dp.openApp("devices","alldevices");
 		dp.DP_Login();
// 		dp.DP_Login("liujia@beyondsoft.com","123456");
// 		dp.DP_Login("liujia@beyondsoft.com","1qaz@WSX");
// 		dp.DP_Login("admin104@beyondsoft.com","bjqasys104");
// 		dp.waitByTimeOut(10000);
// 		dp.uiMapUpdateView("alldevices:groupview");
//        dp.DP_Login("liujiatestpublic@publictest.com","123456");
        dp.setViewTo("alldevices:groupview");
 		dp.waitByTimeOut(3000);

	}

	@Test(priority=1)
	public void Test010DevicesManageDisplay(){
	    //清除设备组
//        deleteAllGroups();


        dp.clickOn("groups");
        dp.waitByTimeOut(5000);
        boolean a = false;
        try {
            a = dp.verifyIsShown("selectCheckbox");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (a) {
            dp.verifyIsShown("selectCheckbox");
            dp.clickOn("selectCheckbox");
            dp.waitByTimeOut(1000);
            dp.clickOn("removeBtn");
            dp.clickOn("removeconfirmBtn");
            dp.waitByTimeOut(1000);
            dp.verifyIsShown("Success");
            dp.waitByTimeOut(1000);
        }

        dp.clickOn("devices");
        dp.waitByTimeOut(3000);

        //检查进入All Devices时默认的View模式，及可见的view按钮
        dp.verifyIsShown("groupviewBtn_selected");

		//查看list view显示
		dp.verifyIsShown("listviewButton");
		dp.clickOn("listviewButton");

		dp.waitByTimeOut(3000);

		dp.verifyIsNotShown("groupviewBtn_selected");
		dp.verifyIsShown("groupviewButton");
		dp.verifyIsShown("listviewBtn_selected");


		//验证设备列表表关元素的存在
		dp.verifyIsShown("Name");
		dp.verifyIsShown("Status");
		dp.verifyIsShown("Position");
//		dp.verifyIsShown("Company");
		dp.verifyIsShown("Team");
		dp.verifyIsShown("Groups");

		//查看group view显示
		dp.verifyIsShown("groupviewButton");
		dp.clickOn("groupviewButton");

		dp.waitByTimeOut(1000);

		dp.verifyIsShown("notGrouped");
		//准备工作创建单设备组
//		PhoneName = dp.bookingDevices(1).get(0);

		dp.waitByTimeOut(1000);
		System.out.println("DevicesManageDisplay done");
	}

	@Test(priority=2)
	public void Test020SelectOneDevice(){
//		PhoneName = dp.selectDevices(1).get(0);
		PhoneName = dp.bookingDevices(1).get(0);

//		dp.clickOn("firstDevice");
		System.out.println("SelectOneDevices done======"+PhoneName);
	}

	@Test(priority=3)
	public void Test030GreatDeviceGroup(){
//		dp.verifyIsShown(PhoneName);
//		dp.clickOn(PhoneName);
//        PhoneName = dp.selectDevices(1).get(0);
		dp.verifyIsShown("groupButton");
		dp.clickOn("groupButton");
		dp.waitByTimeOut(3000);
//		dp.clickOn("alldevices_managedevicesIcon_addnewgroupBtn");
//		dp.clear("alldevices_managedevicesIcon_addnewgroupBtn_groupnameInput");
//		dp.setValueTo("alldevices_managedevicesIcon_addnewgroupBtn_groupnameInput","1");
//		dp.verifyIsShown("modalTitle");
        dp.clickOn("alldevicesMenu_groupBtn_addnewgroupBtn");
        dp.waitByTimeOut(1000);
		dp.setValueTo("alldevicesMenu_groupBtn_addnewgroupBtn_groupnameInput", "1");
        dp.waitByTimeOut(1000);
        dp.clickOn("alldevicesMenu_groupBtn_addnewgroupBtn_submitBtn");
//        dp.clickOn("alldevices_managedevicesIcon_addnewgroupBtn_submitBtn");
        dp.waitByTimeOut(1000);
		dp.clickOn("alldevicesMenu_groupBtn_submitBtn");
		dp.waitByTimeOut(1000);

//        dp.verifyIsShown("Success");
//        dp.verifyIsShown("Add devices to group [1] successfully.");
        dp.waitByTimeOut(1000);
		System.out.println("CreateDeviceGroup done");

		}

	@Test(priority=4)
	public void Test040DevicesManageBook_perhalfanhour(){
//	    dp.clickOn("groupviewButton");
//        dp.waitByTimeOut(3000);
	    dp.verifyIsShown("groupslist");
        dp.clickOn("groupslist");
        dp.waitByTimeOut(3000);
        dp.verifyIsShown("firstnewgroup");
        dp.waitByTimeOut(3000);
        dp.clickOn("firstnewgroup");
        dp.waitByTimeOut(5000);
//		deviceNames = dp.bookingDevices(1);
//		dp.driver.findElement(By.xpath("//div[contains(@class,'device-item-icon ng-isolate-scope')]")).click();
//		PhoneName = dp.driver.findElement(By.xpath("//div[contains(@class,'device-item-icon ng-isolate-scope')]")).getAttribute("title");
//		dp.verifyIsShown(PhoneName);
        PhoneName = dp.getValueOf("firstDeviceTitle");
        dp.clickOn("firstDevice");
//		PhoneName = dp.getValueOf("firstDeviceTitle");
		dp.waitByTimeOut(5000);

		dp.clickOn("bookButton");
		dp.waitByTimeOut(1000);

		//核对预约设备model信息
		dp.verifyIsShown("model");
		dp.assertEqual(PhoneName,dp.getValueOf("model"));
//		String modelname =dp.getValueOf("model");
//		dp.assertEqual(modelname.replace(" ",""),PhoneName);
		dp.waitByTimeOut(3000);

		//per half an hour模式下，点击第一个available的时间块



        while (true){
        dp.verifyIsShown("1stperhalfanhouravailabletime");
        break;

        }

        //获取预约的开始时间
        dp.clickOn("1stperhalfanhouravailabletime");
        starttime_tmp = dp.getElementAttribute("1stperhalfanhourbookedtime","title").split("-")[0];
        dp.waitByTimeOut(1000);
        dp.clickOn("Submit");
        dp.waitByTimeOut(1000);
        dp.verifyIsShown("Success");                                                                                                                                                                                                                                                                                                                                                                                                          		dp.verifyIsShown("Success");
        dp.verifyIsShown("Operate successfully.");
//        dp.verifyIsShown("24 RMB has been deducted from your account.");

        dp.verifyIsShown("Your balance is changed");                                                                                                                                                                                                                                                                                                                                                                                                          		dp.verifyIsShown("Success");
//        dp.verifyIsShown("You have booked " + PhoneName + " and 24.0 has been deducted from your account");
        dp.waitByTimeOut(5000);


		//获取devices页面设备预约的开始时间
		booktime_tmp = dp.getElementAttribute("booktime","title").split(" ")[2];
//            dp.driver.findElement(By.xpath("//span[contains(@class,'book-time ng-binding ng-scope')]")).getAttribute("title").split(",")[1];

		//判断预约时间是否是下午
		Calendar cal=Calendar.getInstance();
		int h=cal.get(Calendar.HOUR_OF_DAY);

		if(h>=12){
			int i = Integer.parseInt(starttime_tmp.split(":")[0]);
			i = i +12;
			String hour= Integer.toString(i);
			starttime_tmp = hour + ":" + starttime_tmp.split(":")[1];
		}
		starttime_tmp = starttime_tmp + ":00";
		dp.assertEqual(starttime_tmp, booktime_tmp);

		dp.clickOn("firstselectDevice");
        dp.waitByTimeOut(1000);
		System.out.println("Book 1 device for half an hour - today");
	}

	@Test(priority=5)
	public void Test050DevicesManageBook_perday(){
		//通过Book快捷按钮预约设备_应保证是同一个设备
        //用设备组过滤设备
        dp.verifyIsShown("groupslist");
        dp.clickOn("groupslist");
        dp.waitByTimeOut(3000);
        dp.verifyIsShown("1");
        dp.clickOn("firstnewgroup");
        dp.waitByTimeOut(5000);

		dp.clickOn("firstdevicebook");
//		PhoneName = dp.driver.findElement(By.xpath("//div[contains(@class,'device-item-icon ng-isolate-scope')]")).getAttribute("title");
		dp.waitByTimeOut(5000);

		//核对预约设备model信息
		dp.verifyIsShown("model");
		dp.assertEqual(PhoneName, dp.getValueOf("model"));
		dp.waitByTimeOut(3000);

		//切换到per day 模式下，点击第一个available的时间块
		dp.clickOn("perday");
        dp.waitByTimeOut(1000);
		dp.clickOn("1stperdayavailabletime");
        dp.waitByTimeOut(3000);
		startday_tmp = dp.driver.findElement(By.xpath("//span[contains(@class,'select-day ng-scope bookSelf')]")).getAttribute("title").replace("/", "-");
		dp.waitByTimeOut(3000);

		//转换模式的确认框检查
		dp.clickOn("perhalfanhour");
        dp.waitByTimeOut(1000);
		dp.verifyIsShown("saveyourlastoperation");
		dp.clickOn("Confirm");
		dp.waitByTimeOut(1000);

		dp.verifyIsShown("Success");
		dp.verifyIsShown("Operate successfully.");
//		dp.verifyIsShown("1152 RMB has been deducted from your account.");
        dp.verifyIsShown("Your balance is changed");                                                                                                                                                                                                                                                                                                                                                                                                          		dp.verifyIsShown("Success");
//        dp.verifyIsShown("You have booked " + PhoneName + " and 1152.0 has been deducted from your account");

		dp.waitByTimeOut(3000);
		System.out.println("Book model changing prompt");
		System.out.println("Book 1 device for whole day - tomorrow");

		}

	@Test(priority=6)
	public void Test060DevicesManageCancelBook_perhalfanhour(){

		//per half an hour模式下，点击取消第一个available的时间块
		dp.driver.findElement(By.xpath("//span[contains(@class,'select-time ng-scope bookSelf')]")).click();
		dp.waitByTimeOut(3000);
		dp.clickOn("Submit");
		dp.waitByTimeOut(1000);

		dp.verifyIsShown("Success");
		dp.verifyIsShown("Operate successfully.");
//		dp.verifyIsShown("24 RMB will be refunded to your account.");
        dp.verifyIsShown("Your balance is changed");
//        dp.verifyIsShown("You have canceled booking "+PhoneName+" and 24.00 has been refunded to your account");

		dp.waitByTimeOut(3000);
		System.out.println("Cancel book 1 device for half an hour - today");



		//获取devices页面设备预约的全天预约的开始时间
		booktime_tmp = dp.driver.findElement(By.xpath("//span[contains(@class,'book-time ng-binding ng-scope')]")).getAttribute("title");
		startday_tmp = startday_tmp + ", 00:00:00";
		startday_tmp = "Aug " + startday_tmp.split("-")[1];//添加的月份需要一个算法来实现


		dp.assertEqual(startday_tmp, booktime_tmp);
		System.out.println("Book time has been changed successfully");

	}

	@Test(priority=7)
	public void Test070DevicesManageCancelBook_perday(){

		dp.clickOn("listviewButton");
		dp.waitByTimeOut(3000);

		//验证设备列表表关元素的存在
		dp.verifyIsShown("Name");
		dp.verifyIsShown("Status");
		dp.verifyIsShown("Position");
//		dp.verifyIsShown("Company");
		dp.verifyIsShown("Team");
		dp.verifyIsShown("Groups");

        //用设备组设备
        dp.verifyIsShown("groupslist");
        dp.clickOn("groupslist");
        dp.waitByTimeOut(3000);
//        dp.verifyIsShown("1");
//        dp.clickOn("firstnewgroup");
//        dp.waitByTimeOut(5000);

		//定位之前预约设备

//		dp.verifyIsShown("PhoneName");

        //选设备点击Book按钮
//        dp.verifyIsShown("selectallcheckbox");
//        dp.clickOn("selectallcheckbox");
        dp.clickOn("bookButton");
        dp.waitByTimeOut(1000);


		//per day模式下，点击取消第一个available的时间块
        dp.verifyIsShown("perday");
        dp.clickOn("perday");
        dp.waitByTimeOut(3000);
		dp.clickOn("1stperdaybookedtime");
		dp.waitByTimeOut(3000);
		dp.clickOn("Submit");
		dp.waitByTimeOut(1000);
//        dp.driver.findElement()显性等待
        dp.verifyIsShown("Success");

        new WebDriverWait(dp.driver, 30).until(ExpectedConditions
            .visibilityOfElementLocated(By.xpath("//div[contains(@ng-class,'wrapperClasses()')]")));

        dp.verifyIsShown("Operate successfully.");
//        dp.verifyIsShown("1152 RMB will be refunded to your account.");
        dp.verifyIsShown("Your balance is changed");
//        dp.verifyIsShown("You have canceled booking "+PhoneName+" and 1152.00 has been refunded to your account");
		dp.waitByTimeOut(3000);
		System.out.println("Cancel book 1 device for half an hour - today");

		//获取devices页面设备预约的全天预约的开始时间
//		booktime_tmp = dp.driver.findElement(By.xpath("//span[contains(@class,'book-time ng-binding ng-scope')]")).getAttribute("title");
//		startday_tmp = startday_tmp + ", 00:00:00";
//		startday_tmp = "Jun " + startday_tmp.split("-")[1];
//        dp.clickOn("groupslist");


//		dp.assertEqual(startday_tmp, booktime_tmp);
		System.out.println("Book time has been changed successfully");
		dp.waitByTimeOut(3000);

	}

    @Test(priority=8)
    public void Test080DeleteDevicesGroup() {
        dp.clickOn("groups");
        dp.waitByTimeOut(3000);
        dp.clickOn("selectCheckbox");
        dp.waitByTimeOut(1000);
        dp.clickOn("removeBtn");
        dp.clickOn("removeconfirmBtn");

        dp.verifyIsShown("Success");
//        List<WebElement> groups_list = dp.getElements("groupslist");//获取设备列表

    }


/*		// 验证元素是否出现, --- 看页面某个东西
		dp.verifyIsShown("");

		// 点击某个按钮
		dp.clickOn("");

		// 往某个文本框里面输入值
		dp.setValueTo("", "");

		// 取值
		dp.getValueOf("");

		// 验证值是否跟期望值一致

		dp.assertEqual("", "");

*/


		/*dp.verifyIsShown("email");
		dp.verifyIsShown("password");
	//	dp.verifyIsShown("loginButton");

		dp.setValueTo("email", "invalidUsername@beyondsoft.com");
		dp.waitByTimeOut(500);
		dp.setValueTo("password", "sys123");
		dp.waitByTimeOut(500);
		//dp.clickOn("loginButton");
		dp.KeyPress(61);
		dp.waitByTimeOut(500);
		dp.KeyPress(66);
		dp.waitByTimeOut(2000);
		dp.getScrentShot();
	//	String errorMessage = dp.getValueOf("");
	//	dp.assertEquest("", "Please enter a valid email");
*/
}

	/*@Test()
	public void Test020LoginInvalidPassword(){

		dp.setValueTo("email", "admin101@beyondsoft.com");
		dp.waitByTimeOut(500);
		dp.setValueTo("password", "123456");
		dp.waitByTimeOut(500);
		dp.clickOn("loginButton");
		dp.waitByTimeOut(500);

	}



	@Test
	public void Test030Login(){

		dp.setValueTo("email", "admin101@beyondsoft.com");
		dp.waitByTimeOut(500);
		dp.setValueTo("password", "sys123");
		dp.waitByTimeOut(500);
		dp.clickOn("loginButton");
		dp.waitByTimeOut(500);
	}*/
//
//	@AfterClass
//	public void tearDown(){
//
//		dp.close();

//	}

