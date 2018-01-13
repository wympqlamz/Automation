//maoyujia
package com.by.automate.testCases.BAT.android;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.by.automate.testCases.devicepass.user.workspaces.apps.Test_FC_app_core;
import com.by.automate.fwk.DPWebApp;

public class Test_FC_Smoke_ios_multiple_appiumPython extends Test_FC_app_core{


    @Test
    public void Test010_creatGroupAndUpalodIpa() {
//	    dp.openApp("workspaces", "apps","apps:Automation");
        dp.getXMLDoc("workspaces", "apps");
        dp.waitByTimeOut(1000);
        dp.setViewTo("apps:Automation");
        dp.waitByTimeOut(2000);
        releaseDevice();
        addGroup_select_multiple_IosPhone(3);
        upload_ios_app("trainticket.ipa");

    }

    @Test
    public void Test020_startAutomation() {
        dp.clickOn("apps");
        dp.waitByTimeOut(2000);
        dp.clickOn("start_automation");
        dp.clickOn("appiumPython_icon");
        dp.verifyIsShown("TestName_input");
        System.out.println(dp.getValueOf("TestName_input"));
        dp.clickOn("appiumPython_selectTestMode_nextButton");
        dp.waitByTimeOut(1000);
        upload_ios_script("trainticket_Demo.zip");
        dp.clickOn("appiumPython_selectScript_nextButton");
        dp.waitByTimeOut(2000);
        dp.clickOn("selectDevice_groupName");
        dp.clickOn("appiumPython_selectDevice_checkbox");
        dp.waitByTimeOut(2000);
        dp.clickOn("selectDevice_nextButton");
        dp.waitByTimeOut(1000);
        dp.setValueTo("MaximumTestRunTime_input","30");
        int testRunTime = Integer.parseInt(dp.getValueOf("MaximumTestRunTime_input"));//获取最大运行时间，供后面的报告时间验证用。
        System.out.println("设置的最大运行时间是" + testRunTime);
        dp.clickOn("Run_button");
        //验证扣费信息提示正确
        dp.waitForElementReadyOnTimeOut("runSuccess_popupWindow", 3000);
        System.out.println(dp.getValueOf("runSuccess_popupWindow"));
//        dp.assertEqual("Your balance is changed",dp.getValueOf("runSuccess_popupWindow"));
        dp.clickOn("close_button");
        dp.waitByTimeOut(2000);
    }

    @Test
    public void Test040_enterTestReportAndStopOneDevice() {
        //testReport default界面
        dp.clickOn("testReport");
        dp.waitByTimeOut(3000);
        dp.clickOn("report_option");
        dp.waitByTimeOut(2000);
        dp.clickOn("report_option_detailButton_Running");
        dp.waitByTimeOut(4000);
        dp.clickOn("reportDetail_option_1");

        if(dp.waitForElementReadyOnTimeOut("reportDetail_stopButton_1",2000)){
            dp.clickOn("reportDetail_stopButton_1");
            dp.waitByTimeOut(2000);
            dp.clickOn("reportDetail_stopConfimButton");
            dp.waitForElementReadyOnTimeOut("reportDetail_detailButton",25000);
            dp.clickOn("reportDetail_detailButton");
            dp.waitByTimeOut(3000);
            dp.assertEqual("Aborted",dp.getValueOf("device_detail_test_result_value"));
            dp.waitByTimeOut(2000);
        }
        else {throw new Error("NO reportDetail_stopButton_1 !!!!");}

        }

    @Test
    public void Test050_downloadReportAndVerifyWord() {
        //testReport default界面
        String Zip_path = "";
        dp.clickOn("testReport");
        dp.waitByTimeOut(2000);

        //进入运行中的报告里。到 testReport detail界面
        dp.clickOn("report_option");
        dp.waitByTimeOut(2000);
        deleteFiles(new File("C:\\Users\\Administrator\\Downloads\\"));
        System.out.println("清空Downloads文件夹....\n开始下载报告....");
        dp.waitByTimeOut(2000);
        dp.clickOn("report_option_downloadButton");
        dp.waitByTimeOut(10000);
        File file1 = new File("C:\\Users\\Administrator\\Downloads\\");
        if (file1.exists()){
            System.out.println("下载报告成功");
        }
        else {
            throw new Error("报告下载失败");
        }
        String[] fileList1 = file1.list();

        for (int i=0;i<fileList1.length;i++){
            File readfile = new File("C:\\Users\\Administrator\\Downloads\\" + fileList1[i]);
//            System.out.println("path="+readfile.getPath());
//            System.out.println("absolutepath=" +readfile.getAbsolutePath());
//            System.out.println("name="+readfile.getName());
            if(readfile.getName().contains("Test")){
//                System.out.println("path="+readfile.getPath());
//                System.out.println("absolutepath=" +readfile.getAbsolutePath());
                String name1 = readfile.getName();
                System.out.println(name1);
                Zip_path = "C:\\Users\\Administrator\\Downloads\\"+name1; // name_path：“路径+xls文件名”
                System.out.println();
                System.out.println("Zip_path is : "+Zip_path);
            }
        }
        deleteFiles(new File("E:\\maoyujia\\"));
        System.out.println("清空maoyujia文件夹....\n开始准备解压.....");
        String wordReport_path = ZipContraMultiFile(Zip_path,"E:\\maoyujia\\");//将下载的报告zip解压缩，返回wrod报告文件的路径。
        assert wordReport_path.contains(".docx"):"没有找到解压后的报告文件";



    }




    @Test
    public void Test060_clearData(){
        releaseDevice();
        removeApps();
        dp.setViewTo("appspage:apps");
        deleteAllGroups();//meng
        deleteAllReport();//meng
    }


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

    public void upload_ios_script(String scriptName){
        //如果存在脚本,点下一步，如果不存在脚本，点上传。
        if(!dp.waitForElementReadyOnTimeOut("uploadFile_name_1", 2000)){
            String currentpath_temp = System.getProperty("user.dir");
            String currentpath = currentpath_temp.replaceAll("\\\\", "/");
            String scriptpath = (currentpath + "/src/test/resources/data/App/iOS_app/script/" + scriptName);
            dp.sendKeys("upload", scriptpath);
            WebElement ele = new WebDriverWait(dp.driver,20).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/div/div/section[3]/div[1]/div/div[3]/table/tbody/tr[1]/td[1]")));
            ele.click();
            dp.clickOn("close_uploadApp");
        }
        else{
            dp.clickOn("uploadFile_name_1");
        }
        dp.waitByTimeOut(2000);

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
    依次选择多台ios手机
    @number : 选择的ios手机个数
     */
    public void addGroup_select_multiple_IosPhone(int number) {
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
    解压zip包到指定的路径下面。
    @param：
        zippath：zip包路径
        outzippath：输出的目录，例如   E://maoyujia//
     */

    public static String ZipContraMultiFile(String zippath ,String outzippath){

        String report_path = "";
        System.out.println("开始将 "+zippath+" 解压到" +outzippath);
        try {
            File file = new File(zippath);
            File outFile = null;
            ZipFile zipFile = new ZipFile(file);
            ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry = null;
            InputStream input = null;
            OutputStream output = null;
            while((entry = zipInput.getNextEntry()) != null){
//                System.out.println("解压缩" + entry.getName() + "文件");
                outFile = new File(outzippath + File.separator + entry.getName());
//                System.out.println("outFile为" + outFile);
                if(!outFile.getParentFile().exists()){
                    outFile.getParentFile().mkdirs();
//                    System.out.println("创建目录" );
                }
                if(!outFile.exists()){
                    outFile.createNewFile();
//                    System.out.println("创建文件" );
                }
                input = zipFile.getInputStream(entry);
                output = new FileOutputStream(outFile);
                int temp = 0;
                while((temp = input.read()) != -1){
                    output.write(temp);
                }
                input.close();
                output.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //以下是获取文件夹下面所有文件

        File file = new File(outzippath);
        System.out.println("获取"+ outzippath +"里所有文件");
        String[] fileList = file.list();
        for (int i=0;i<fileList.length;i++){
            File readfile = new File(outzippath + fileList[i]);
//            System.out.println("path="+readfile.getPath());
//            System.out.println("absolutepath=" +readfile.getAbsolutePath());
//            System.out.println("name="+readfile.getName());
            if(readfile.getName().contains("report")){
                String reportName = readfile.getName();
                report_path = outzippath+reportName; // 路径+doc文件名,即：下载解压后的word报告
                System.out.println("下载后的报告文件路径是"+report_path);
            }
            System.out.println("---------------------");
        }
        return report_path;
    }

    /*
    删除指定的文件，或文件夹里的所有文件。
    @param：
        file：要删除的文件的路径

     */
    private  void deleteFiles(File file){
        if(file.exists()){
            if(file.isFile()){
                file.delete();
                System.out.println(" 删除文件："+file);
            }
            else if(file.isDirectory()){
                System.out.println(" 删除目录："+file);
                File[] files = file.listFiles();
                for(int i=0;i < files.length;i++){
                    this.deleteFiles(files[i]);
                }}
        }
        else {throw new Error("System.out.println(\"要删除的文件或文件夹不存在\")");
        }



    }
}
