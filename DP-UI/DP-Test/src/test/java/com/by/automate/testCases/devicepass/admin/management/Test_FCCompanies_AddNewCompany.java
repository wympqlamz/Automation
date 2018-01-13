package com.by.automate.testCases.devicepass.admin.management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;
import com.by.automate.utils.CommonTools;

import java.util.List;


public class Test_FCCompanies_AddNewCompany {

	private DPWebApp dp = null;

	private String companyAccount = CommonTools.getEnglish(12) + "@qq.com";
	private String companyPhone = CommonTools.getNumber(10);
	private String companyAccountName = CommonTools.getEnglish(8);
	private String companyAccountPassword = CommonTools.getEnglish(4) + CommonTools.getNumber(4);
	private String conpanyName = CommonTools.getEnglish(20);
	private String storageSize = CommonTools.getNumber(6);
	private String updateCompanyName = "qinhuan_test";
	private String updateCompanyStorageSize = "999999";
	private String rechargeForComoany = "./td[10]//i[@uib-tooltip='Recharge']";
	private String balanceForCompany = "./td[8]/span";
	List<WebElement> companys = null;
	private String rechargeAmountNumber = CommonTools.getNumber(2);

	@BeforeClass
	public void setUp() {

		dp = new DPWebApp();
		dp.openApp();

	}

	@Test(priority = 1)
	public void Test010_addNewCompany() {
		dp.DP_Login();
		// 验证头部信息
		dp.verifyIsShown("jiantou");
		dp.verifyIsShown("messageCenter");
		dp.verifyIsShown("help");
		dp.verifyIsShown("testStatus");
		dp.verifyIsShown("userName");

		// 验证左边logo
		dp.verifyIsShown("logo");
		// 验证workspace功能模块
		dp.verifyIsShown("workspace");
		dp.verifyIsShown("apps");
		dp.verifyIsShown("testReport");
		dp.verifyIsShown("screenShots");
		// 验证devices功能模块
		dp.verifyIsShown("devices");
		dp.verifyIsShown("groups");
		// 验证Mangement模块
		dp.verifyIsShown("management");
		dp.verifyIsShown("companies");
		dp.verifyIsShown("teams");
		dp.verifyIsShown("users");
		dp.verifyIsShown("configuration");
		dp.verifyIsShown("rate");
		// 版本和版权
		dp.verifyIsShown("build");
		dp.verifyIsShown("copyRight");
		dp.verifyIsShown("byBeyondsoft");

		// 跳转到companies界面，并点击 add New Company ==> company profile
		dp.waitByTimeOut(1000);
		dp.clickOn("companies");
		dp.waitByTimeOut(1000);
		dp.verifyIsShown("newCompany");
		dp.clickOn("newCompany");
		dp.verifyIsShown("addCompanyTitle");
		dp.verifyIsShown("stepsIndicator");
		dp.verifyIsShown("companyName");
		dp.verifyIsShown("inputCompanyName");
		dp.verifyIsShown("storageSize");
		dp.verifyIsShown("inputStorageSize");
		dp.verifyIsShown("deploymentOption");
		dp.verifyIsShown("selectDeploymentOption");
		// dp.verifyIsShown("nextByCreateCompany");

		// 判断Next按钮是不可点击的
		dp.log(dp.getElementAttribute("nextByCreateCompany", "disabled"));
		dp.isElementNotEnabled("nextByCreateCompany");

		// 输入超过30个字符的company name
        dp.setValueTo("inputCompanyName","fejwyr843fheifhf383`3fe@dwweycksdjdei");
        dp.waitByTimeOut(500);
        dp.verifyIsShown("inputMoreThanThirtyMsg");
        String inputMoreThanThirtyMsg = dp.getValueOf("inputMoreThanThirtyMsg");
        dp.log(inputMoreThanThirtyMsg);
        dp.assertEqual(inputMoreThanThirtyMsg,"Please enter a company name which should be no more than 30 characters.");

        // 删除输入的company name
        dp.clear("inputCompanyName");
        dp.waitByTimeOut(500);
        dp.verifyIsShown("removeCompanyNameMsg");
        String removeCompanyNameMsg = dp.getValueOf("removeCompanyNameMsg");
        dp.log(removeCompanyNameMsg);
        dp.assertEqual(removeCompanyNameMsg,"Please enter a company name.");

        // 输入已有相同的Company Name
        dp.setValueTo("inputCompanyName","qinhuan public");
        dp.waitByTimeOut(800);
        dp.verifyIsShown("inputSameCompanyNameMsg");
        String inputSameCompanyNameMsg = dp.getValueOf("inputSameCompanyNameMsg");
        dp.log(inputSameCompanyNameMsg);
        dp.assertEqual(inputSameCompanyNameMsg,"The company name already exists");

        // 输入正常的字符的company name
        dp.clear("inputCompanyName");
        dp.waitByTimeOut(500);
		dp.setValue("inputCompanyName", conpanyName);

		// 输入非数字的storageSize
        dp.setValueTo("inputStorageSize","fejwyr843fheifhf38");
        dp.waitByTimeOut(500);
        dp.verifyIsShown("inputNonNumberMsg");
        String inputNonNumberMsg = dp.getValueOf("inputNonNumberMsg");
        dp.log(inputNonNumberMsg);
        dp.assertEqual(inputNonNumberMsg,"Please enter a positive integer which should be less than 999999.");

        // 删除输入的storageSize
        dp.clear("inputStorageSize");
        dp.waitByTimeOut(500);
        dp.verifyIsShown("removeStorageSizeMsg");
        String removeStorageSizeMsg = dp.getValueOf("removeStorageSizeMsg");
        dp.log(removeStorageSizeMsg);
        dp.assertEqual(removeStorageSizeMsg,"Please enter storage size");

        // 输入超过999999数字的storageSize
        dp.setValueTo("inputStorageSize","1000000");
        dp.waitByTimeOut(500);
        dp.verifyIsShown("inputNonNumberMsg");
        String inputMoreThanValueMsg = dp.getValueOf("inputNonNumberMsg");
        dp.log(inputMoreThanValueMsg);
        dp.assertEqual(inputMoreThanValueMsg,"Please enter a positive integer which should be less than 999999.");

        //输入正常的storageSize
        dp.clear("inputStorageSize");
        dp.waitByTimeOut(500);
		dp.setValue("inputStorageSize", storageSize);

		// 随机选择部署方式(私有或公有)
		dp.clickOn("selectDeploymentOption");
		dp.verifyIsShown("DeploymentOptionList",1);
        List<WebElement> deploymentOption = dp.getElements("DeploymentOptionList");
        int random = CommonTools.getRandom(deploymentOption.size());
        dp.waitByTimeOut(500);
        deploymentOption.get(random).click();
		/*dp.verifyIsShown("selectOnPremise");
		dp.verifyIsShown("selectPublicCloud");
		dp.clickOn("selectPublicCloud");*/
		dp.isElementEnabled("nextByCreateCompany");
		// dp.verifyIsShown("nextByCreateCompany");
		dp.clickOn("nextByCreateCompany");

		// 创建company的账户
		dp.verifyIsShown("companyAccount");
		dp.verifyIsShown("inputCompanyAccount");
		dp.verifyIsShown("companyPhone");
		dp.verifyIsShown("inputCompanyPhone");
		dp.verifyIsShown("companyAccountName");
		dp.verifyIsShown("inputCompanyAccountName");
		dp.verifyIsShown("companyAccountPassword");
		dp.verifyIsShown("inputCompanyAccountPassword");
		dp.verifyIsShown("companyAccountConfirmPassword");
		dp.verifyIsShown("inputCompanyAccountConfirmPassword");
		dp.verifyIsShown("previousByCompanyAccount");

		dp.log(dp.getElementAttribute("submitByCompanyAccount", "disabled"));
		dp.isElementNotEnabled("submitByCompanyAccount");
		dp.waitByTimeOut(600);

		dp.setValue("inputCompanyAccount", companyAccount);
		dp.waitByTimeOut(500);
		dp.setValue("inputCompanyPhone", companyPhone);
		dp.waitByTimeOut(500);
		dp.setValue("inputCompanyAccountName", companyAccountName);
		dp.waitByTimeOut(500);
		dp.setValue("inputCompanyAccountPassword", companyAccountPassword);
		dp.waitByTimeOut(500);
		dp.setValue("inputCompanyAccountConfirmPassword", companyAccountPassword);
		dp.waitByTimeOut(500);

		dp.isElementEnabled("submitByCompanyAccount");
		dp.clickOn("previousByCompanyAccount");
		dp.verifyIsShown("nextByCreateCompany");
		dp.waitByTimeOut(500);
		dp.clickOn("nextByCreateCompany");
		dp.waitByTimeOut(500);
		dp.clickOn("submitByCompanyAccount");

		// 判断Success的文本消息
		dp.verifyIsShown("successAddCompanyMessage");
		String getSuccessAddCompanyMessage = dp.getValueOf("successAddCompanyMessage");
		dp.log(getSuccessAddCompanyMessage);
		String getSuccessAddCompanyMessageData = dp.getContentPropertry("dp.msg.addcompany.success");
		dp.assertEqual(getSuccessAddCompanyMessageData, getSuccessAddCompanyMessage);

		dp.verifyIsShown("addCompanySuccessfullyTitle");
		dp.verifyIsShown("addCompanyClose");
		dp.clickOn("addCompanyClose");
		dp.waitByTimeOut(500);

	}

	@Test(priority = 2)
	public void Test020_searchCompanyByName() { // 依据companyName来搜索新建的company,并比较搜索出来就是新建的company

		dp.verifyIsShown("searchCompanyByName");
		dp.setValue("searchCompanyByName", conpanyName);
		dp.verifyIsShown("searchedByName");
		String getSearchedByName = dp.getValueOf("searchedByName");
		dp.log("getSearchedByName");
		dp.assertEqual(getSearchedByName, conpanyName);

	}

	@Test(priority = 3)
	public void Test030_editCompany() {

		dp.verifyIsShown("editAddedCompany");
		dp.clickOn("editAddedCompany");
		dp.verifyIsShown("updateCompanyTitle");
		dp.verifyIsShown("updateCompanyNameTitle");
		dp.verifyIsShown("inputCompanyName");
		dp.verifyIsShown("updateCompanyStorageSizeTitle");
		dp.verifyIsShown("inputStorageSize");
		dp.verifyIsShown("updateCompanyDeploymentOptionTitle");
		dp.verifyIsShown("selectDeploymentOption");
		dp.waitByTimeOut(500);
		dp.setValueTo("inputCompanyName", updateCompanyName);
		dp.waitByTimeOut(500);
		dp.setValueTo("inputStorageSize", updateCompanyStorageSize);
		dp.getElementAttribute("selectDeploymentOption", "disabled");
		dp.verifyIsShown("cancelUpdateCompany");
		dp.verifyIsShown("submitUpdateCompany");
		dp.clickOn("submitUpdateCompany");
		dp.verifyIsShown("successUpdateCompanyMessage");
		String getSuccessUpdateCompanyMessage = dp.getValueOf("successUpdateCompanyMessage");
		dp.log(getSuccessUpdateCompanyMessage);
		dp.waitByTimeOut(500);
		String getSuccessUpdateCompanyMessageData = dp.getContentPropertry("dp.msg.updatecompany.success");
		dp.assertEqual(getSuccessUpdateCompanyMessageData, getSuccessUpdateCompanyMessage);
		dp.waitByTimeOut(800);

	}

	@Test(priority = 4)
	public void Test040_removeCompany() {

		dp.verifyIsShown("searchCompanyByName");
		dp.clickOn("searchCompanyByName");
		dp.setValue("searchCompanyByName", updateCompanyName);
		dp.waitByTimeOut(400);
		dp.verifyIsShown("removeAddedCompany");
		dp.clickOn("removeAddedCompany");
		dp.waitByTimeOut(500);
		dp.verifyIsShown("confirmTitle");
		dp.verifyIsShown("removecompanyMessage");
		dp.verifyIsShown("cancelRemoveCompany");
		dp.verifyIsShown("submitRemoveCompany");
		dp.clickOn("submitRemoveCompany");
		dp.verifyIsShown("successRemoveCompanyMessage");
		String getSuccessRemoveCompanyMessage = dp.getValueOf("successRemoveCompanyMessage");
		dp.log(getSuccessRemoveCompanyMessage);
		dp.waitByTimeOut(500);
		String getSuccessRemoveCompanyMessageData = dp.getContentPropertry("dp.msg.removecompany.success");
		dp.assertEqual(getSuccessRemoveCompanyMessageData, getSuccessRemoveCompanyMessage);

	}

	@Test(priority = 5)
	public void Test050_rechargeForCompany(){
		dp.verifyIsShown("searchCompanyByName");
		dp.waitByTimeOut(600);
		dp.clear("searchCompanyByName");
		dp.verifyIsShown("list",1);
		companys = dp.getElements("list");
		int random = CommonTools.getRandom(companys.size());
		dp.waitByTimeOut(500);
        String balance = companys.get(random).findElement(By.xpath(balanceForCompany)).getText();
        dp.log(balance);
		companys.get(random).findElement(By.xpath(rechargeForComoany)).click();
		dp.waitByTimeOut(800);
		dp.verifyIsShown("chargeTitle");
        dp.verifyIsShown("closeBtn");
        dp.verifyIsShown("equivalentAmount");
        String equivalentAmountText = dp.getValueOf("equivalentAmount");
        dp.log(equivalentAmountText);
        dp.assertEqual(equivalentAmountText,"0 DP Coins");
        dp.verifyIsShown("amountConvertedInfo");
        String amountConvertedInfo = dp.getValueOf("amountConvertedInfo");
        dp.log(amountConvertedInfo);
        dp.assertEqual(amountConvertedInfo,"1 RMB = 1 DP Coins");
        dp.verifyIsShown("usableTimeIsAround");
        dp.verifyIsShown("unitPriceInfo");
        String unitPriceInfo = dp.getValueOf("unitPriceInfo");
        dp.log(unitPriceInfo);
        dp.assertEqual(unitPriceInfo,"The unit price is approximately 0.8 DP Coins per device per minute.");
        dp.verifyIsShown("amountTitle");
        dp.verifyIsShown("inputAmount");
        dp.verifyIsShown("cancelRecharge");
        dp.verifyIsShown("submitRecharge");
        dp.waitByTimeOut(800);
        dp.setValueTo("inputAmount",rechargeAmountNumber);
        String equivalentAmountText1 = dp.getValueOf("equivalentAmount");
        dp.log(equivalentAmountText1);
        dp.assertEqual(equivalentAmountText1,(rechargeAmountNumber + " " + "DP Coins"));
        dp.waitByTimeOut(500);
        dp.clickOn("submitRecharge");
        dp.verifyIsShown("successRechargeCompanyMessage");
        String getSuccessRechargeCompanyMessageMessage = dp.getValueOf("successRechargeCompanyMessage");
        dp.log(getSuccessRechargeCompanyMessageMessage);
        dp.waitByTimeOut(500);
        String getSuccessRechargeCompanyMessageMessageData = dp.getContentPropertry("dp.msg.rechargecompany.success");
        dp.assertEqual(getSuccessRechargeCompanyMessageMessageData, getSuccessRechargeCompanyMessageMessage);
        dp.waitByTimeOut(800);
        companys = dp.getElements("list");
        String balance1 = companys.get(random).findElement(By.xpath(balanceForCompany)).getText();
        dp.assertEqual(Float.parseFloat(balance1),Float.parseFloat(balance) +Float.parseFloat( rechargeAmountNumber));//将String转换成float类型

	}

	@AfterClass
	public void tearDown() {

		dp.close();
	}
}
