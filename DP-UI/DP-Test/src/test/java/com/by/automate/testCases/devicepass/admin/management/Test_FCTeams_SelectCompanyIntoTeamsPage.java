package com.by.automate.testCases.devicepass.admin.management;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;
import com.by.automate.utils.CommonTools;

public class Test_FCTeams_SelectCompanyIntoTeamsPage {

	private DPWebApp dp =null;
	private String inputNewTeamName = CommonTools.getEnglish(15);

	@BeforeClass
	public void setUp(){

		dp = new DPWebApp();
		dp.openApp();
		dp.DP_Login();
	}

	@Test(priority = 1)
	public void Test010_selectCompany(){

		dp.verifyManagementElement();

		//进入teams界面
		dp.waitByTimeOut(1000);
		dp.clickOn("teams");
		dp.waitByTimeOut(1000);
		dp.verifyIsShown("selectCompanyTitle");
		dp.verifyIsShown("selectCompanyTextbox");
		dp.clickOn("selectCompanyTextbox");
		dp.waitByTimeOut(500);
		//dp.verifyIsShown("firstBySelcetCompany");


		List<WebElement> allCompanyName = dp.getElements("allCompanyByMenu");  //将所有的companyName生成一个集合
		List<String> values = new ArrayList<String>();  //将除去第一个的所有的集合
		for (int i = 1; i < allCompanyName.size(); i++) {


			String value = allCompanyName.get(i).getText(); //获得每一个元素的文本
			values.add(value); // 将value 的值添加到values的集合中去
		}
		int random = CommonTools.getRandom(values.size()-1); // 从values集合中随机生成一个数字并打印出来
		dp.log(values.get(random));
		String selectCompanyName = values.get(random); // 取出该随机数的文本值
		dp.setValueTo("selectCompanyTextbox", values.get(random)); // 定位符给予Select来做点击的操作
		dp.waitByTimeOut(1000);
		dp.uiMapUpdateView("teams:teams");

		dp.verifyIsShown("teamsInCompanyTitle");
		dp.verifyIsShown("teamsInCompanyName");
		String selectedCompanyName = dp.getValueOf("teamsInCompanyName"); // 在取出当前的company Name的名字
		dp.assertEqual(selectedCompanyName, selectCompanyName); // 两次取出来的文本值进行对比，是否一样的
		dp.verifyIsShown("selectCompanyDropdownBtn");
		dp.clickOn("selectCompanyDropdownBtn");
		dp.waitByTimeOut(500);
		List<WebElement> allCompanyNameOfBtn = dp.getElements("allCompanyOfBtn");
		int randomSum = CommonTools.getRandom(allCompanyNameOfBtn.size());
		dp.log(randomSum);  // 打印随机数
		dp.waitByTimeOut(500);
		//  stale element reference: element is not attached to the page document(元素过期)
		//allCompanyNameOfBtn = dp.getElements("allCompanyOfBtn");
		allCompanyNameOfBtn.get(randomSum).click(); // 点击随机数
		dp.waitByTimeOut(500);

	}

	@Test(priority = 2)
	public void Test020_addNewTeam(){
		dp.verifyIsShown("addNewTeamBtn");
		dp.clickOn("addNewTeamBtn");
		dp.verifyIsShown("iconTeam");
		dp.verifyIsShown("addNewTeamTitle");
		dp.verifyIsShown("closeBtn");
		dp.verifyIsShown("teamNameTitle");
		dp.verifyIsShown("inputTeamName");
		dp.verifyIsShown("cancelAddNewTeam");
		String status = dp.getElementAttribute("submitAddNewTeam", "disabled");
		dp.log(status);  // 返回true
		dp.assertTrue(status.equals("true"),"元素可点,应该不能点击.");// 断言该元素是不可点击的

		dp.waitByTimeOut(1000);
		dp.setValueTo("inputTeamName", inputNewTeamName);
		dp.waitByTimeOut(500);
		dp.verifyIsShown("submitAddNewTeam");
		dp.clickOn("submitAddNewTeam");
		dp.verifyIsShown("successAddTeamMsg");
		String getSuccessAddTeamMsg = dp.getValueOf("successAddTeamMsg");
		dp.log(getSuccessAddTeamMsg);
		String getSuccessAddTeamMsgData = dp.getContentPropertry("dp.msg.addteam.success");
		dp.assertEqual(getSuccessAddTeamMsgData, getSuccessAddTeamMsg);
		//  dp.getElementAttribute("removeTeamBtn", "disabled");

	}

	@Test(priority = 3)
	public void Test030_searchNewTeam(){

		dp.verifyIsShown("searchTeamByName");
		dp.setValueTo("searchTeamByName", inputNewTeamName);
		dp.waitByTimeOut(800);
	}

	@Test(priority = 4)
	public void Test040_editTeam(){

		dp.verifyIsShown("editTeamInAction");
		dp.clickOn("editTeamInAction");
		dp.verifyIsShown("iconfontTeam");
		dp.verifyIsShown("addTeamtitle");
		dp.verifyIsShown("closeBtn");
		dp.verifyIsShown("teamNameTitle");
		dp.verifyIsShown("editTeamName");
		dp.verifyIsShown("cancelEditTeam");
		dp.verifyIsShown("submitEditTeam");
		dp.waitByTimeOut(800);
		dp.setValueTo("editTeamName", "qinhuan_testTeam_Demo");
		dp.waitByTimeOut(500);
		dp.clickOn("submitEditTeam");
		dp.verifyIsShown("successEditTeamMsg");
		String getSuccessEditTeamMsg = dp.getValueOf("successEditTeamMsg");
		dp.log(getSuccessEditTeamMsg);
		String getSuccessEditTeamMsgData = dp.getContentPropertry("dp.msg.editteam.success");
		dp.assertEqual(getSuccessEditTeamMsgData, getSuccessEditTeamMsg);
		dp.waitByTimeOut(800);
	}

	@Test(priority = 5)
	public void Test050_removeNewTeam(){
		dp.verifyIsShown("searchTeamByName");
		dp.setValueTo("searchTeamByName", "qinhuan_testTeam_Demo");
		dp.waitByTimeOut(800);
		dp.verifyIsShown("removeTeamInAction");
		dp.clickOn("removeTeamInAction");
		dp.verifyIsShown("iconfontRemoveTeam");
		dp.verifyIsShown("confirmRemoveTitle");
		dp.verifyIsShown("closeRemoveBtn");
		dp.verifyIsShown("confirmRemoveText");
		dp.waitByTimeOut(500);
		String getConfirmRemoveText = dp.getValueOf("confirmRemoveText");
		dp.log(getConfirmRemoveText);
		String getConfirmRemoveTextData = dp.getContentPropertry("dp.msg.remove.contentText");
		dp.assertEqual(getConfirmRemoveTextData, getConfirmRemoveText);
		dp.verifyIsShown("cancelRemoveTeamBtn");
		dp.verifyIsShown("confirmRemoveTeamBtn");
		dp.waitByTimeOut(600);
		dp.clickOn("confirmRemoveTeamBtn");
		dp.verifyIsShown("successRemoveTeamMsg");
		String getSuccessRemoveTeamMsg = dp.getValueOf("successRemoveTeamMsg");
		dp.log(getSuccessRemoveTeamMsg);
		String getSuccessRemoveTeamMsgData = dp.getContentPropertry("dp.msg.removeteam.success");
		dp.assertEqual(getSuccessRemoveTeamMsgData, getSuccessRemoveTeamMsg);

	}

	@AfterClass
	public void tearDown(){

		dp.close();
	}

}
