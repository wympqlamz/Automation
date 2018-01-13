package com.by.automate.testCases.devicepass.admin.management;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;
import com.by.automate.utils.CommonTools;

public class Test_FCCompanies_TotalTitleClick {

	private DPWebApp dp = null;

	//
	WebElement companyNameTitle = null;  //companyNameTitle 是对象，所以前面用WebElement(代表任何web对象)
	WebElement totalTeams =  null;
	WebElement totalUsers =   null;
	WebElement totalDevices =   null;
	WebElement deploymentOption =   null;
	WebElement storageSizeTitle =   null;
	WebElement createDateTitle =  null;



	String companyNameTitleVal ="";   // 对象为字符串 ，定义类型
	String totalTeamsVal ="";
	String totalUsersVal = "";
	String totalDevicesVal ="";
	String deploymentOptionVal = "";
	String storageSizeTitleVal = "";
	String createDateTitleVal ="";

	String other="";

	List<WebElement> companys  =  null;  //companys在这里表示集合,该页面下所有的company

	@BeforeClass
	public void setUp(){

		dp = new DPWebApp();
		dp.openApp();

	}

	@Test(priority = 1)
	public void Test010_clickTotalTitle(){
		dp.DP_Login();

		//验证Mangement模块
		dp.verifyManagementElement();

		//进入companies界面
		dp.waitByTimeOut(1000);
		dp.clickOn("companies");
		dp.waitByTimeOut(1000);


		dp.verifyIsShown("newCompany");
		dp.verifyIsShown("list",1);   //验证该界面的下第一页中，所有的list下的company

		companys = dp.getElements("list");  //获取具有相同样式的所有元素，在当前页面
		dp.log("Company Name  ---  Total Teams  ---  Total Users  ---   Total Devices ---  Deployment Option ---  Storage Size  ---  Create Date");
		for(int i= 0 ; i<companys.size();i++){
			logCompanysInfo(i);  //调封装的方法(logCompansInfo)，来进行所有的验证

			//做移动操作，在页面上，循环的移动在totalTeams，totalUsers，totalDevices，逐行的移动
			mouseOn(totalTeams);
			dp.waitByTimeOut(500);
			mouseOn(totalUsers);
			dp.waitByTimeOut(500);
			mouseOn(totalDevices);
		}


		clickOtherAndGetInfo(dp.totalTeams);
		clickOtherAndGetInfo(dp.totalUsers);
		clickOtherAndGetInfo(dp.totalDevices);
		//verifyOther();
		// click verify teamsInCompanyName

	}


	private void mouseOn(WebElement element){

		Actions actions = new Actions(dp.driver);
        actions.moveToElement(element).build().perform();
	}

	private void logCompanysInfo(int i){

		 companyNameTitle =  companys.get(i).findElement(By.xpath(dp.companyNameTitle));  //依据前面在该页面，获取具有相同样式的所有元素，逐行 来获取 该行中的该元素，通过xpath的定位符，调用已经封装的所有列的元素
		 totalTeams =  companys.get(i).findElement(By.xpath(dp.totalTeams));
		 totalUsers =  companys.get(i).findElement(By.xpath(dp.totalUsers));
		 totalDevices =  companys.get(i).findElement(By.xpath(dp.totalDevices));
		 deploymentOption =  companys.get(i).findElement(By.xpath(dp.deploymentOption));
		 storageSizeTitle =  companys.get(i).findElement(By.xpath(dp.storageSizeTitle));
		 createDateTitle =  companys.get(i).findElement(By.xpath(dp.createDateTitle));



		 companyNameTitleVal = companyNameTitle.getText(); // 获得该元素的文本值赋给定义的一个对象，最后将对象打印出来
		 totalTeamsVal = totalTeams.getText();
		 totalUsersVal = totalUsers.getText();
		 totalDevicesVal = totalDevices.getText();
		 deploymentOptionVal = deploymentOption.getText();
		 storageSizeTitleVal = storageSizeTitle.getText();
		 createDateTitleVal = createDateTitle.getText();
		 dp.log(companyNameTitleVal+"  ---  "+totalTeamsVal+"  ---  "+totalUsersVal+"  ---   "+totalDevicesVal+" ---  "+deploymentOptionVal+" ---  "+storageSizeTitleVal+"  ---  "+createDateTitleVal);
	}


	// 封装一个随机的点击某一行的元素并打印输出信息来
	private void clickOtherAndGetInfo(String title){
		//dp.uiMapUpdateView("dashboard:companies");
		int random = CommonTools.getRandom(companys.size()-1); // 依据前面获取的company数。来生成一个随机数(CommonTools 是一个工具库，getRandom 是去这个库里调用随机数)
		dp.log(random);// 打印随机数
		dp.waitByTimeOut(2000);
		companys = dp.getElements("list");
		logCompanysInfo(random);                               // 调用上面封装的logCompanysInfo, 打印随机生的该行的文本信息
		WebElement element  = companys.get(random).findElement(By.xpath(title)); //定义一个对象
		other = element.getText();  // 获得该对象的文本值赋给变量other
		element.click(); // 点击该变量
		dp.waitByTimeOut(800);
		verifyOther();              // 验证点击后的界面信息
		dp.back();
		dp.uiMapUpdateView("companies:companies"); // 返回到点击前的界面
	}

	private void verifyOther(){
		if(other.contains("team")){
			dp.uiMapUpdateView("teams:teams"); //点击teams图标，跳转到teams界面去验元素
			dp.verifyIsShownTeamsElement();

		}
		if(other.contains("user")){
			dp.uiMapUpdateView("users:users"); //点击Users图标，跳转到Users界面验元素
			dp.verifyIsShownUsersElement();

		}
		if(other.contains("device") && !other.contains("no")){  //验证该company下有设备后的界面效果
			dp.uiMapUpdateView("alldevices:alldevices");
			dp.verifyIsShown("allTeamsBtnName");
			dp.verifyIsShown("teamViewBtn");
		}
		if(other.contains("no")){                // 验证company下没有设备的结果
			dp.uiMapUpdateView("alldevices:alldevices");
			dp.verifyIsShown("allCompanyBtnName");
		}

	}

	@AfterClass
	public void tearDowm(){

		dp.close();
	}
}
