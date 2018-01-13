//maoyujia
package com.by.automate.testCases.devicepass.user.workspaces.apps;

import java.util.List;

import org.omg.PortableServer.ServantRetentionPolicyValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;

public class Test_FC_AutomationTest_Uiautomator {
	private DPWebApp dp = null;
	
	
	@BeforeClass
	public void setUp() {

		dp = new DPWebApp();
		dp.getXMLDoc("workspaces", "apps");
		dp.openApp();
		dp.DP_Login();	
		dp.uiMapUpdateView("apps:Automation");
		dp.waitByTimeOut(2000);
		add_group();
		upload_App_automation();
		
	}	
	
	@Test
	public void Test010_enterUiautomator() {	
		dp.clickOn("apps");
		dp.clickOn("start_automation");
		dp.clickOn("UIAutomator_icon");
		dp.clickOn("next_button_1");
		dp.verifyIsShown("run_test");
		dp.verifyIsShown("title_selectTestMode");
		dp.verifyIsShown("title_selectScript");
		dp.verifyIsShown("title_selectDeiveGroup");
		dp.verifyIsShown("title_nameYourTest");
		dp.verifyIsShown("title_runTest_2");
		dp.verifyIsShown("uiautomator_previousButton_1");
		dp.verifyIsShown("uiautomator_nextButton_1");
	
		dp.setValueTo("testClass_input", "Uiautomator-Test");
		dp.setValueTo("testMethod_input", "Sample.ep2p.TestCase");
		dp.clickOn("uiautomator_nextButton_1");
	}
	
	@Test
	public void Test020_enterScriptView() {
		
		dp.waitByTimeOut(2000);
		dp.verifyIsShown("run_test");
		dp.verifyIsShown("title_selectTestMode");
		dp.verifyIsShown("title_selectScript");
		dp.verifyIsShown("title_selectDeiveGroup");
		dp.verifyIsShown("title_nameYourTest");
		dp.verifyIsShown("title_runTest_2");
		
		dp.assertEqual("App Name", dp.getValueOf("selectScript_AppName"));
		dp.assertEqual("App Version", dp.getValueOf("selectScript_AppVersion"));
		dp.assertEqual("Framework", dp.getValueOf("selectScript_Framework"));
		dp.assertEqual("File Name", dp.getValueOf("selectScript_AppFileName"));
		dp.assertEqual("File Size", dp.getValueOf("selectScript_AppFileSize"));
		dp.assertEqual("Script Name", dp.getValueOf("title_scriptName"));
		dp.assertEqual("Remark", dp.getValueOf("title_scriptRemark"));		
		dp.assertEqual("File Size", dp.getValueOf("title_scriptFileSize"));
		dp.assertEqual("Upload Time", dp.getValueOf("title_scriptUploadTime"));	
		dp.assertEqual("If you do not know how to write a script,please click here.", dp.getValueOf("uploadFile_info"));	
		dp.waitByTimeOut(1000);	
		
	}
	
	@Test
	public void Test030_uploadScript() {
		//如果存在脚本直接下一步，不存在脚本再上传。
		if(!dp.waitForElementReadyOnTimeOut("uploadFile_name_1", 2000)){
				
			dp.uploadAndroidScript("uploadFile_button","uiautomator");
			WebElement ele = new WebDriverWait(dp.driver,20).until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/div/div/section[3]/div[1]/div/div[3]/table/tbody/tr[1]/td[1]")));
			ele.click();
			dp.clickOn("close_uploadApp");
		}
		else{
			dp.clickOn("uploadFile_name_1");
		}
		dp.waitByTimeOut(2000);	
		dp.clickOn("next_button_4");
	
		dp.waitByTimeOut(1000);	
		dp.verifyIsShown("run_test");
		dp.verifyIsShown("title_selectTestMode");
		dp.verifyIsShown("title_selectScript");
		dp.verifyIsShown("title_selectDeiveGroup");
		dp.verifyIsShown("title_nameYourTest");
		dp.verifyIsShown("title_runTest_2");
		dp.verifyIsShown("previous_button_2");
		dp.assertEqual("Detail of Devices", dp.getValueOf("detailOfDevice_text"));	
		dp.assertEqual("mao(1)", dp.getValueOf("select_groupName1"));	
		
	}
	
	@Test
	public void Test040_verifyGroupView() {
		dp.clickOn("select_groupName1");
		dp.waitByTimeOut(1000);	
		dp.clickOn("next_button_3");
		
		dp.waitByTimeOut(1000);	
		dp.verifyIsShown("run_test");
		dp.verifyIsShown("title_selectTestMode");
		dp.verifyIsShown("title_selectScript");
		dp.verifyIsShown("title_selectDeiveGroup");
		dp.verifyIsShown("title_nameYourTest");
		dp.verifyIsShown("title_runTest_2");
		
		//中间内容有变化，暂时不验证
		//
		
		
		dp.verifyIsShown("previous_button_3");
		dp.verifyIsShown("Run_button");
		
	}
	
	@Test
	public void Test050_writeTestNameAndRun() {
		dp.setValue("testName_input", "uiautomator");
		dp.clickOn("Run_button");

		dp.waitByTimeOut(1000);	
		dp.verifyIsShown("run_test");
		dp.verifyIsShown("title_selectTestMode");
		dp.verifyIsShown("title_selectScript");
		dp.verifyIsShown("title_selectDeiveGroup");
		dp.verifyIsShown("title_nameYourTest");
		dp.verifyIsShown("title_runTest_2");
		dp.verifyIsShown("control_button");
		dp.verifyIsShown("testResult_button");
		dp.verifyIsShown("close_button");
		dp.assertEqual("1 of 1 devices run successfully.", dp.getValueOf("success_info"));
		
		dp.clickOn("close_button");
	}
	
	@AfterClass
	public void tearDown(){		
		
		dp.close();
		}
	
	
	
//	
//	/*
//	 * 判断元素是否存在，存在返回true，不存在返回false
//	 * @author : maoyujia	
//	 */	
//    public boolean elementExist(By locator)
//	    {
//        try {
//	        dp.driver.findElement(locator);
//	        return true;
//	        } 
//        catch (Exception e) {
//	        return false;
//	        }
//	    }
	
    /*
     * 移动鼠标到某个坐标
     * @author : maoyujia
     * @param  x:坐标值   y:坐标值
     */	
    public void moveMouse(int x , int y){
		Actions action = new Actions(dp.driver);
		action.moveByOffset(x, y).perform();
		}
	
    /*
     *验证automation的前置条件：上传app，先清空app列表再上传
     *@author : maoyujia
     */
    public void upload_App_automation(){		
		int i=0;
		dp.clickOn("apps");
		dp.waitByTimeOut(2000);
		//先清空app
		if(dp.waitForElementReadyOnTimeOut("checkbox_all", 2000)){
			 dp.clickOn("checkbox_all");	
			 dp.clickOn("remove_allCheckbox");	
			 dp.clickOn("remove_confirm");	
			 dp.waitByTimeOut(2000);
		 }		
		dp.uploadApp(); //传入第一个app
		//一直到app上传完毕，关掉上传窗口
		while(i<20){
			dp.waitByTimeOut(3000);
			System.out.println(dp.getValueOf("uploading_info"));
			if(dp.getValueOf("uploading_info").equals("(1/1)")){ 
				dp.waitByTimeOut(2000);
//				clickOn("close_uploadApp");
				break;
			}
			else{
				i++;			
			}
		}
	}	

    /*
	 * 清空当前apps列表
	 * @author: maoyujia
	 */
	public void removeApps(){	
		try{
			dp.clickOn("apps");
			dp.waitByTimeOut(2000);		
			dp.clickOn("checkbox_all");	
			dp.clickOn("remove_allCheckbox");	
			dp.clickOn("remove_confirm");	
			dp.waitByTimeOut(1000);
			}
		catch (Exception e) {
			System.out.println("remove app list fail !!!!!!!!!!!!!!!!!!!!!!");
			}
		}
	
	/*
	 * @author: maoyujia
	 * 验证automation的前置条件：初始化组，组名是mao，先清空再创建
	 */	
	public void add_group(){
		dp.clickOn("groups");	
		dp.waitByTimeOut(2000);
		if(dp.waitForElementReadyOnTimeOut("all_group", 2000)){
			dp.clickOn("all_group");	
			dp.clickOn("removeButton");	
			dp.clickOn("confirmRemoveDeviceGourp");	
			 }
		dp.waitByTimeOut(2000);
		dp.clickOn("devices");		
		dp.waitByTimeOut(2000);
		List<WebElement> phone_status  = dp.getElements("phone_status");
		for(WebElement i : phone_status){
//				 System.out.println(i.getText());
			if (i.getText().equals("Available")){
				i.click();
				break;
				 }
			 }
			 //创建组名 为mao
		dp.clickOn("groupBtn"); 
		dp.setValueTo("inputName", "mao");
		dp.clickOn("submit_button"); 
		dp.waitByTimeOut(2000);
	}
	
	
	/*
	 * 清空当前group列表
	 * @author: maoyujia
	 */
	public void removeGroup(){
		try{
			dp.clickOn("groups");
			dp.waitByTimeOut(2000);	
			dp.clickOn("all_group");	
			dp.clickOn("removeButton");	
			dp.clickOn("confirmRemoveDeviceGourp");	
		}
		catch (Exception e) {
			System.out.println("remove group list fail !!!!!!!!!!!!!!!!!!!!!!");
		}
		dp.waitByTimeOut(1000);			
		}
		
	
}
