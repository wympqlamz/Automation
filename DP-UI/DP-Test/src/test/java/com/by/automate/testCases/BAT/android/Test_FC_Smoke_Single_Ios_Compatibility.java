//maoyujia
package com.by.automate.testCases.BAT.android;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;
import com.by.automate.testCases.devicepass.user.workspaces.apps.Test_FC_app_core;

public class Test_FC_Smoke_Single_Ios_Compatibility extends Test_FC_app_core {

    private String device_Name;
    private String TestName;

//    @BeforeClass
//    public void setUp() {
//        dp = new DPWebApp();
//        dp.openApp("workspaces", "apps","apps:Automation");
//        dp.DP_Login();
//        dp.waitByTimeOut(2000);
//        addGroup_select_IosPhone(1);
//        upload_ios_app("trainticket.ipa");
//    }


    @Test
    public void Test010_startCompatibility() {
        dp.getXMLDoc("workspaces", "apps");
        dp.waitByTimeOut(2000);
        dp.setViewTo("apps:Automation");
        dp.waitByTimeOut(2000);
        releaseDevice();
        addGroup_select_IosPhone(1);
        upload_ios_app("trainticket.ipa");
        dp.clickOn("apps");
        dp.waitByTimeOut(2000);
        dp.clickOn("start_automation");
        dp.clickOn("Compatibility_icon");
        TestName = dp.getValueOf("TestName_input");
        dp.clickOn("Compatibility_nextButton_1");
        dp.waitByTimeOut(1000);
        dp.setValueTo("testRepeat_input","500");
        dp.waitByTimeOut(1000);
        dp.clickOn("Compatibility_nextButton_2");
        dp.waitByTimeOut(1000);

    }

    @Test
    public void Test030_selectDeviceAndRun() {
        dp.clickOn("selectDevice_groupName");
        dp.clickOn("selectDevice_deviceName");
        dp.waitByTimeOut(1000);
        device_Name = dp.getValueOf("selectDevice_deviceName");
        dp.clickOn("selectDevice_nextButton");
        dp.waitByTimeOut(1000);
        dp.setValueTo("MaximumTestRunTime_input","30");
        dp.clickOn("Run_button");
        //验证扣费信息提示正确
        dp.waitForElementReadyOnTimeOut("runSuccess_popupWindow",3000);
        dp.assertEqual("Your balance is changed",dp.getValueOf("runSuccess_popupWindow"));
        dp.clickOn("close_button");
        dp.waitByTimeOut(2000);
    }

    @Test
    public void Test040_checkTestReport() {
        //testReport default界面
        dp.clickOn("testReport");
        dp.waitByTimeOut(2000);
        //获取testname、testtype、appName
        String report_default_testName = dp.getValueOf("report_testName_1");
        String report_default_testType = dp.getValueOf("report_testType_1");
        String report_default_appName = dp.getValueOf("report_appName_1");
        System.out.println(report_default_testName);
        System.out.println(report_default_testType);
        System.out.println(report_default_appName);
        //进入运行中的报告里。到 testReport detail界面
        dp.clickOn("report_option");
        dp.waitByTimeOut(2000);
        dp.clickOn("report_option_detailButton_Running");
        dp.waitByTimeOut(4000);
        //检查testReport detail界面里的app名字、TestName、TestType、运行状态 是正确的
        dp.assertEqual(report_default_appName,dp.getValueOf("report_appNameValue"));
        dp.assertEqual(report_default_testName,dp.getValueOf("report_TestNameValue"));
        dp.assertEqual(report_default_testType,dp.getValueOf("report_testTypeValue"));
        dp.assertEqual("Running",dp.getValueOf("report_result_status"));
        //获取当前testReport detail界面的设备名字
        String report_detail_deviceName   = dp.getValueOf("report_deviceNameValue");
        System.out.println("当前进行自动化的手机设备是："+report_detail_deviceName);

        //等待报告执行完毕后，再次进入报告的detail界面
        dp.clickOn("testReport");
        dp.waitByTimeOut(2000);
        dp.clickOn("report_option");
        dp.waitForElementReadyOnTimeOut("report_option_detailButton_noRun", 600000 );
        dp.clickOn("report_option_detailButton_noRun");
        dp.waitByTimeOut(2000);
        //获取脚本的测试结果状态、执行时间
        String report_testResult = dp.getValueOf("report_result_status");
        String test_duration = dp.getValueOf("report_testDuration_Value").trim();
        System.out.println("此次测试运行时间为："+test_duration);
        //将时间换成分钟
        if(test_duration.contains("m")) {
            String[] time = test_duration.split("m");
            String minute = time[0].trim();
            System.out.println("minute ：" + minute);
            int test_time = Integer.parseInt(minute); //得到运行时间，单位是分钟
            assert (test_time < 10) :"自动化测试时间超出最大时间范围";

        }else if (!test_duration.contains("m")){
            String time = test_duration.trim().substring(0, test_duration.length() - 1);
            System.out.println("time ：" + time);
            System.out.println("自动化脚本运行时间不到一分钟");
        }
        //再次检查testReport detail界面里的app名字、TestName、TestType、运行状态

        dp.assertEqual(report_default_appName,dp.getValueOf("report_appNameValue"));
        dp.assertEqual(report_default_testName,dp.getValueOf("report_TestNameValue"));
        dp.assertEqual(report_default_testType,dp.getValueOf("report_testTypeValue"));
        dp.assertEqual("Success",dp.getValueOf("report_result_status"));



        //进入到device detail界面
        dp.waitByTimeOut(2000);
        dp.clickOn("report_deviceNameValue");
        dp.waitByTimeOut(4000);
        //检查device detail界面的设备名字、testResult、testDuration正确
        dp.assertEqual(report_detail_deviceName,dp.getValueOf("device_detail_deviceName_value"));
        dp.assertEqual(report_testResult,dp.getValueOf("device_detail_test_result_value"));
        dp.assertEqual(test_duration,dp.getValueOf("device_detail_test_duration_value"));
    }

    @Test
    public void Test050_clearData(){
        releaseDevice();
        removeApps();
        dp.setViewTo("appspage:apps");
        deleteAllGroups();//meng
        deleteAllReport();//meng

    }



    /*
	从device里选择inuse的手机，进行release。
	 */
    public void releaseDevice() {
        dp.waitByTimeOut(2000);
        dp.clickOn("devices");
        dp.waitByTimeOut(2000);
        dp.clickOn("All_Status");
        dp.waitByTimeOut(2000);
        dp.clickOn("All_Status_In_Use");
        dp.waitByTimeOut(2000);
        if(dp.getValueOf("selected_0_of_0").contains("Selected 0 of 0")){
            System.out.println("当前没有inUse的设备");
        }
        else {
            dp.clickOn("checkbox_device");
            WebElement releaseButton= dp.driver.findElement(By.xpath("//button[@uib-tooltip='Release']"));
            System.out.println("releaseButton 是否可用,true or false ： "+releaseButton.isEnabled());
            if (releaseButton.isEnabled()){
                dp.clickOn("releaseButton");
                dp.waitByTimeOut(2000);
                dp.clickOn("releaseButton_confirm");
                dp.waitByTimeOut(2000);
            }
            else {
                dp.clickOn("groups");
                releaseDevice();
            }
        }
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
    上传一个ios的app安装包
     */
    public void upload_ios_app(String appname){
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
        String currentpath_temp = System.getProperty("user.dir");
        String currentpath = currentpath_temp.replaceAll("\\\\", "/");
        String apppath = (currentpath + "/src/test/resources/data/App/iOS_app/" + appname);
        dp.sendKeys("upload", apppath);
        //一直到app上传完毕，关掉上传窗口
        while(i<20){
            dp.waitByTimeOut(3000);
            System.out.println(dp.getValueOf("uploading_info"));
            if(dp.getValueOf("uploading_info").equals("(1/1)")){
                dp.waitByTimeOut(2000);
                dp.clickOn("close_uploadApp");
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
    依次选择1台或多台ios手机
    @number : 选择的ios手机个数
     */
    public void addGroup_select_IosPhone(int number) {
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
        //挑出ios设备，放到可用集合内
        List<WebElement> Available_ios_phones = new ArrayList<WebElement>();
        List<WebElement> phone_items  = dp.getElements("phone_item_icon");
        for(WebElement i : phone_items){
            if (i.getText().contains("Available") && i.getText().contains("Apple")){
                Available_ios_phones.add(i);
            }}
        System.out.println(Available_ios_phones.size());
        int ios_nums = Available_ios_phones.size();
        if (number > ios_nums) {
            number = ios_nums;
        }
        for (int k=0;k<number;k++){
            Available_ios_phones.get(k).click();
        }
        dp.clickOn("groupBtn");
        dp.clickOn("AllDevices_GroupBtn_AddNewGroupBtn");
        dp.clickOn("AllDevices_GroupBtn_AddNewGroupBtn_SubmitBtn");
        dp.clickOn("AllDevices_GroupBtn_checkbox");
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
