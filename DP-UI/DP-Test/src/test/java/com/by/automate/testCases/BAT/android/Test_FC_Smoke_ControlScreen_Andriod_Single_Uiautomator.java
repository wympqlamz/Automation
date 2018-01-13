package com.by.automate.testCases.BAT.android;

import com.by.automate.fwk.DPWebApp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.by.automate.testCases.devicepass.user.workspaces.apps.Test_FC_app_core;
import java.util.ArrayList;
import java.util.List;

public class Test_FC_Smoke_ControlScreen_Andriod_Single_Uiautomator extends Test_FC_app_core {
    private int testRunTime=0;
    private float priceCurrent=0;
    private String priceValue = "";

    private String deviceName = "";
    private String maxRunTime = "";

    @Test
    public void Test010_selectAndControlOnePhone() {
        dp.getXMLDoc("workspaces", "apps");
        dp.waitByTimeOut(1000);
        dp.setViewTo("apps:Automation");
        dp.waitByTimeOut(2000);
        releaseDevice();
        dp.clickOn("groups");
        dp.waitByTimeOut(1000);
        dp.clickOn("devices");
        dp.waitByTimeOut(3000);
        List<WebElement> phone_items  = dp.getElements("phone_item_icon");
        for(WebElement i : phone_items){
            System.out.println(i.getText());
            if (i.getText().contains("Available") && !i.getText().contains("Apple")){
                i.click();
                break;
                }
        }
        dp.clickOn("controlButton");
        dp.waitByTimeOut(2000);
        dp.clickOn("controlButton_confirm");
        dp.waitByTimeOut(4000);
    }

    /*
    current Use 界面的单个设备截屏
     */
    @Test
    public void Test020_screenshot() {
        deviceName = dp.getValueOf("currentUse_deviceName");
        if (!dp.waitForElementReadyOnTimeOut("screenshot_button",3000)){
            dp.clickOn("jiantou");
            dp.waitByTimeOut(2000);
        }
        if (!dp.waitForElementReadyOnTimeOut("picture_itom",3000)){
            dp.clickOn("screenshot_button");
            dp.waitByTimeOut(3000);
            dp.verifyIsShown("picture_itom");
        }
       else{
            String num = dp.getValueOf("?/?picture_info");
            System.out.println(num);
            String[] x1 = num.split(" ");
            String x2 = x1[0];
            String[] x3 = x2.split("/");
            String pictureNum = x3[1];//获取当前设备存在的截图数量
            System.out.println("当前截图数量一共是："+pictureNum);
            dp.clickOn("screenshot_button");
            int new_pictureNum = Integer.parseInt(pictureNum)+1; //截屏后的截图数量多了一张
            dp.waitByTimeOut(5000);
            //如果出现loadmore，就一直点开，直到所有截屏出来
            while (dp.waitForElementReadyOnTimeOut("Load_more",3000)){
                dp.clickOn("Load_more");
                dp.waitByTimeOut(1000);
            }
            dp.waitByTimeOut(2000);
            List<WebElement> pictures = dp.getElements("picture_itom");//获取当前设备存在的截图数量
            dp.assertEqual(new_pictureNum,pictures.size());//截屏后，截图的数量比原来多了一张。

        }
        }

    @Test
    public void Test030_uploadApp(){
        int i= 0;
        dp.waitByTimeOut(2000);
        dp.clickOn("Apps_option");
        if(dp.waitForElementReadyOnTimeOut("checkbox_all",3000)){
            dp.clickOn("checkbox_all");
            dp.clickOn("currentUse_remove");
            dp.clickOn("currentUse_remove_ok");
            dp.waitByTimeOut(1000);
        }
        String currentpath_temp = System.getProperty("user.dir");
        String currentpath = currentpath_temp.replaceAll("\\\\", "/");
        String apppath = (currentpath + "/src/test/resources/data/App/android_apk/" + "ep2p.apk");
        dp.sendKeys("upload", apppath);
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

    @Test
    public void Test040_startAutomation(){
        dp.waitByTimeOut(2000);
        dp.clickOn("currentUse_start_automation");
        dp.clickOn("currentUse_UI_Automator_icon");
        dp.clickOn("currentUse_UI_Automator_nextButton_1");
        dp.waitByTimeOut(2000);
        dp.setValueTo("testClass_input","Uiautomator-Test");
        dp.setValueTo("testMethod_input","Sample.ep2p.TestCase");
        dp.clickOn("currentUse_UI_Automator_nextButton_2");
        CurrentUse_uploadScript();
        dp.waitByTimeOut(2000);
        dp.setValueTo("MaximumTestRunTime_input","30");
        testRunTime = Integer.parseInt(dp.getValueOf("MaximumTestRunTime_input"));//获取最大运行时间
        System.out.println("设置的最大运行时间是"+testRunTime);
        String price_str = dp.getValueOf("price");//
        System.out.println(price_str);
        String[] str =  price_str.split(" RMB");
        System.out.println(str[0]);
        String[] str_2 = str[0].split("tely ");
        if(str_2[1].substring(str_2[1].length()-1,str_2[1].length()).equals("0")){  //如果拿到的是0.80.则只保留0.8
            priceValue = str_2[1].substring(0,str_2[1].length()-1);
            System.out.println("priceValue数值1是 ："+ priceValue);
        }
        else {
            priceValue = str_2[1];
            System.out.println("priceValue数值2是 ："+ priceValue);
        }
        priceCurrent = Float.parseFloat(priceValue) ;  //拿到当前扣费的金额
        System.out.println("当前扣费的金额是"+priceCurrent);
        dp.waitByTimeOut(2000);
        dp.clickOn("Run_button");
        dp.waitByTimeOut(2000);
        dp.clickOn("close_button");
        dp.waitByTimeOut(2000);
    }

    @Test
    public void Test050_checkTestStatus(){
        //检查test status
        dp.waitForElementReadyOnTimeOut("TestStatus",5000);
        dp.clickOn("TestStatus");
        dp.waitByTimeOut(1000);
        String info1 = dp.getValueOf("TestStatus_info_Running");
        dp.assertContains(info1,"is running");
        dp.waitByTimeOut(1000);
        dp.clickOn("report_option");
        dp.waitForElementReadyOnTimeOut("report_option_detailButton_noRun",300000);
        dp.waitByTimeOut(2000);
        dp.clickOn("TestStatus");
        dp.waitByTimeOut(1000);
        String info2 = dp.getValueOf("TestStatus_info_noRunning");
        dp.assertContains(info2,"No running");
        dp.waitByTimeOut(1000);
        //检查测试报告detail
        dp.clickOn("report_option_detailButton_noRun");
        dp.waitByTimeOut(4000);
        dp.assertEqual(deviceName,dp.getValueOf("report_deviceNameValue"));
//        dp.assertEqual("Success",dp.getValueOf("report_result_status"));

    }

    @Test
    public void Test060_releaseAndCheckBilling(){
        while (dp.waitForElementReadyOnTimeOut("runSuccess_popupWindow",3000)){
            dp.clickOn("close_popupWindow_1");
            dp.waitByTimeOut(1000);
        }
        dp.clickOn("account_icon");
        dp.clickOn("billing");
        //移动到页面最底部
//        ((JavascriptExecutor) dp.driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        dp.waitByTimeOut(2000);
        //检查自动化测试的扣费记录
        String amount = dp.getValueOf("Amount_1");
        System.out.println(amount);
        String[] str = amount.split("-");
        System.out.println(str[1]);
        float price = Float.parseFloat(str[1]);//billing下面左侧的扣费价格。
        System.out.println(price);
        assert price == priceCurrent:"自动化测试的扣费记录不正确";

        releaseDevice();
    }

    @Test
    public void Test070_clearData(){

        removeApps();
        dp.setViewTo("appspage:apps");
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


    public void CurrentUse_uploadScript() {
        //如果存在脚本,点下一步，如果不存在脚本，点上传。
        if (!dp.waitForElementReadyOnTimeOut("uploadFile_name_1", 2000)) {

            dp.uploadAndroidScript("uploadFile_button", "uiautomator");
            WebElement ele = new WebDriverWait(dp.driver, 20).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/div/div/section[3]/div[1]/div/div[3]/table/tbody/tr[1]/td[1]")));
            ele.click();
            dp.waitByTimeOut(2000);
//            dp.clickOn("close_uploadApp");
        } else {
            dp.clickOn("uploadFile_name_1");
        }
        dp.waitByTimeOut(2000);
        dp.clickOn("UiAutomator_nextButton_3");

        dp.waitByTimeOut(1000);
    }


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
    上传app安装包
     */
    public void upload_app(String appname, String type){
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
        if(type=="ios") {
            String apppath = (currentpath + "/src/test/resources/data/App/iOS_app/" + appname);
            dp.sendKeys("upload", apppath);
        }
        else if (type=="android"){
            String apppath = (currentpath + "/src/test/resources/data/App/android_apk/" + appname);
            dp.sendKeys("upload", apppath);
        }
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
    依次选择1台或多台手机
    @number : 选择的ios手机个数
    @type: "android" 或者 "ios" ,传入andriod就选安卓设备，传入ios就选苹果设备
     */
    public void addGroup_select_Phone(int number,String type) {
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
        //挑出android或者ios设备，放到可用集合内
        List<WebElement> Available_phones = new ArrayList<WebElement>();
        List<WebElement> phone_items  = dp.getElements("phone_item_icon");
        if (type == "android"){
            for(WebElement i : phone_items){
                if (i.getText().contains("Available") && !i.getText().contains("Apple")){
                    Available_phones.add(i);
            }}}
        else if (type=="ios"){
            for(WebElement i : phone_items){
                if (i.getText().contains("Available") && i.getText().contains("Apple")){
                    Available_phones.add(i);
                }}
        }
        System.out.println("可选择的设备数量为"+Available_phones.size());
        int phone_nums = Available_phones.size();
        if (number > phone_nums) {
            number = phone_nums;
        }
        for (int k=0;k<number;k++){
            Available_phones.get(k).click();
        }
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
