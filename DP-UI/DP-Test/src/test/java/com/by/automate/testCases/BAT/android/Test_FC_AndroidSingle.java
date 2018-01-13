/**
 * 版权 @Copyright: 2017 Copyright ® 2017 Wuhan beyondsoft Co. Ltd.
 * 项目名称：DP-Test
 * 文件名称： Test_FC_AndroidSingle.java
 * 包名：com.by.automate.testCases.BAT.android
 * 创建人：@author liujia@beyondsoft.com
 * 创建时间：2017年5月12日/上午10:45:30
 * 修改人：liujia@beyondsoft.com
 * 修改时间：2017年6月16日/下午8:10:30
 * 修改备注：
 */
package com.by.automate.testCases.BAT.android;

import com.by.automate.fwk.DPWebApp;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.xpath.XPath;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.*;
import java.awt.event.*;

import java.util.List;

/**
 * Created by liujia on 6/10/2017.
 */
public class Test_FC_AndroidSingle {
    private DPWebApp dp = null;
    private String devicename, unit;
    private int controltime;
    private double controlfee;

    @BeforeClass
    public void setUp(){

        dp = new DPWebApp();
        dp.openApp("devices","currentUse");
//        dp.DP_Login("qa@devicepass.com","cqmygysdsS1234");
        dp.DP_Login();
//        dp.DP_Login("liujiatest@beyondsoft.com","123456");
//        dp.DP_Login("liujiatestpublic@publictest.com","123456");
//        dp.DP_Login("liujia@beyondsoft.com","1qaz@WSX");
        dp.setViewTo("alldevices:groupview");
//        dp.setViewTo("currentUse:screenshotL");
        dp.waitByTimeOut(3000);

    }


    @Test(priority=1)
    public void Test010DeviceSelected(){
        //清除设备组
//        deleteAllGroups();


        dp.clickOn("groups");
//        dp.setViewTo("groups:defaultview");
        dp.waitByTimeOut(3000);
        boolean a = false;
        try {
            a = dp.verifyIsShown("selectCheckbox");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (a) {
            dp.verifyIsShown("selectCheckbox");
            dp.clickOn("selectCheckbox");
            dp.waitByTimeOut(3000);
            dp.clickOn("removeBtn");
            dp.clickOn("removeconfirmBtn");
            dp.waitByTimeOut(1000);
//            dp.waitForElementReadyOnTimeOut("");
            dp.verifyIsShown("Success");
            dp.waitByTimeOut(1000);
        }
        //清理in use设备
//        dp.verifyIsShown("listviewButton");
//        dp.clickOn("listviewButton");

//        dp.waitByTimeOut(3000);

        dp.clickOn("devices");
        dp.waitByTimeOut(3000);

        dp.clickOn("allstatusMesu");
        dp.waitByTimeOut(1000);
        dp.clickOn("InUse");
        boolean b = false;
        try {
            b = dp.verifyIsShown("Selected 0 of 0");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println("=========================>"+a);

        if (!b){
            dp.clickOn("selectallinuseDevices");
            dp.clickOn("releaseButton");
            dp.waitByTimeOut(1000);
            dp.clickOn("releaseconfirmBtn");

            dp.waitByTimeOut(10000);
//            new WebDriverWait(dp.driver, 30).until(ExpectedConditions
//                .visibilityOfElementLocated(By.xpath("//div[contains(@ng-class,'wrapperClasses()')]")));

            dp.clickOn("allstatusMesu");
            dp.waitByTimeOut(1000);
            dp.clickOn("allstatus");
            dp.waitByTimeOut(1000);
            if (dp.waitForElementReadyOnTimeOut("alertmessage",1000)){
                dp.clickOn("alertmessage");
            }
            dp.clickOn("clearButton");



        }

        dp.waitByTimeOut(1000);

        dp.clickOn("allstatusMesu");
        dp.waitByTimeOut(1000);
        dp.clickOn("allstatus");
        dp.waitByTimeOut(1000);

        //检查未选设备时顶部按钮状态
        dp.verifyIsShown("alldevicesMenu_controlBtn_Disable");
        dp.verifyIsShown("alldevicesMenu_bookBtn_Disable");
        dp.verifyIsShown("alldevicesMenu_groupBtn_Disable");
        dp.verifyIsShown("alldevicesMenu_releaseBtn_Disable");
        System.out.println("=========================>");

        //查看group view显示
        dp.verifyIsShown("groupviewButton");
        dp.waitByTimeOut(1000);
        dp.clickOn("groupviewButton");

        dp.waitByTimeOut(1000);

        dp.verifyIsShown("notGrouped");
        dp.selectDevices(1);



        dp.waitByTimeOut(1000);
        devicename = dp.getValueOf("firstselectDevicename");

        dp.waitByTimeOut(1000);
        System.out.println("Device" + devicename + "Select done");

        //检查选1个available设备时顶部按钮状态

        dp.verifyIsShown("controlButton");
        dp.verifyIsNotShown("alldevicesMenu_controlBtn_Disable");
        dp.verifyIsShown("alldevicesMenu_bookBtn");
        dp.verifyIsNotShown("alldevicesMenu_bookBtn_Disable");
        dp.verifyIsShown("alldevicesMenu_groupBtn");
        dp.verifyIsNotShown("alldevicesMenu_groupBtn_Disable");
        dp.verifyIsShown("alldevicesMenu_releaseBtn_Disable");
        System.out.println("=========================>");

    }

    @Test(priority=2)
    public void Test020ControlTimeSet(){
        dp.verifyIsShown("controlButton");
        dp.clickOn("controlButton");

        dp.verifyIsShown("controltimealert");
        dp.verifyIsShown("controltimeinput");
        dp.verifyIsShown("controltimeunit");
        dp.verifyIsShown("1 device(s) you want to control");
        dp.verifyIsShown(devicename);

        dp.waitByTimeOut(3000);
        //查看Current Use显示

        controltime = 50;


//        if(controltime%30==0){
//        controlfee = controltime*1.0;
//        }
//        else {
//            controlfee = ((controltime/30)+1)*25;
//        }


        dp.setValueTo("controltimeinput",String.valueOf(controltime));
        dp.waitByTimeOut(1000);
//        dp.verifyIsShown("The maximum controllable time depends on your account balance and the device booking schedule.");
//        dp.verifyIsShown("The total cost is " + controlfee + "0");

        dp.waitByTimeOut(1000);
        System.out.println("Set time done");

    }

    @Test(priority=3)
    public void Test030checkCurrenterUseAndroidSingleTopButton() {
        dp.verifyIsShown("controlconfirm");
        dp.clickOn("controlconfirm");

        dp.waitByTimeOut(3000);

        //查看Current Use显示
        dp.verifyIsShown("Your balance is changed");
        dp.verifyIsShown("Your account will be charged for the duration you control the device " + devicename);

        dp.verifyIsShown("controldevicename");
        dp.assertEqual(devicename, dp.getValueOf("controldevicename"));

        dp.verifyIsShown("ScreenShottopBtn");
//        System.out.println(dp.getValueOf("ScreenShottopBtn"));
        dp.assertEqual("Screenshot", dp.getValueOf("ScreenShottopBtn"));
        dp.verifyIsShown("ReloadAll");
        dp.assertEqual("Reload All", dp.getValueOf("ReloadAll"));
        dp.verifyIsShown("");
        dp.assertEqual("Release All", dp.getValueOf("ReleaseAll"));
        dp.verifyIsShown("");
        dp.assertEqual("Renew", dp.getValueOf("RenewDevices"));

        //检查各按钮功能
        dp.clickOn("controldevicename");
        dp.verifyIsShown("devicemodelinfo");
        dp.assertEqual("Device model information", dp.getValueOf("devicemodelinfo"));
        dp.verifyIsShown("devicemodel");
        dp.assertEqual(devicename, dp.getValueOf("devicemodel"));

        dp.verifyIsShown("modelinfoBtn");
        dp.verifyIsShown("serialinfoBtn");
        dp.verifyIsShown("providerBtn");

        dp.clickOn("serialinfoBtn");
        dp.waitByTimeOut(1000);
        dp.verifyIsShown("SerialLabel");
//        System.out.println("=============================>"+dp.getValueOf("SerialLabel"));

        dp.clickOn("providerBtn");
        dp.waitByTimeOut(1000);
        dp.verifyIsShown("ProviderChannelLabel");
//        System.out.println("=============================>"+dp.getValueOf("ProviderChannelLabel"));

        dp.clickOn("modelinfoBtn");
        dp.waitByTimeOut(1000);
        dp.verifyIsShown("ModelLabel");
        dp.verifyIsShown("Modelinfo");
        dp.assertEqual(devicename.split(" ")[0],dp.getValueOf("Manufacturerinfo"));
        dp.verifyIsShown("ListViewImg");

        dp.clickOn("closeBtn");
        dp.waitByTimeOut(1000);

    }

    @Test(priority=4)
    public void Test040checkCurrenterUseAndroidSingleTopMenu() {
        //清晰度默认值检查
        dp.verifyIsShown("setDefinition");
        dp.assertEqual("SD", dp.getValueOf("setDefinition"));
        dp.clickOn("setDefinition");

        dp.waitByTimeOut(1000);

        dp.verifyIsShown("UD");
        dp.assertEqual("Ultra Definition", dp.getValueOf("UD"));
        dp.verifyIsShown("HD");
        dp.assertEqual("High Definition", dp.getValueOf("HD"));
        dp.verifyIsShown("SD");
        dp.assertEqual("Standard Definition", dp.getValueOf("SD"));
        dp.verifyIsShown("LD");
        dp.assertEqual("Low Definition", dp.getValueOf("LD"));

//        dp.clickOn("setDefinition");
//        dp.waitByTimeOut(1000);

        dp.verifyIsShown("ExtraInfo");
        dp.assertEqual("Extra Info", dp.getValueOf("ExtraInfo"));
        dp.clickOn("ExtraInfo");
        dp.waitByTimeOut(3000);

        dp.verifyIsShown("ScreenSizelable");
        dp.assertEqual(" Screen Size", dp.getValueOf("ScreenSizelable"));
        dp.verifyIsShown("Versionlable");
        dp.assertEqual(" Version", dp.getValueOf("Versionlable"));
        dp.verifyIsShown("Brandlable");
        dp.assertEqual(" Brand", dp.getValueOf("Brandlable"));
        dp.verifyIsShown("Platformlable");
        dp.assertEqual(" Platform", dp.getValueOf("Platformlable"));
        dp.verifyIsShown("IMEIlable");
        dp.assertEqual(" IMEI", dp.getValueOf("IMEIlable"));
        dp.verifyIsShown("ICCIDlable");
        dp.assertEqual(" ICCID", dp.getValueOf("ICCIDlable"));
        dp.verifyIsShown("Widthlable");
        dp.assertEqual(" Width", dp.getValueOf("Widthlable"));
        dp.verifyIsShown("Heightlable");
        dp.assertEqual(" Height", dp.getValueOf("Heightlable"));

        dp.clickOn("ExtraInfo");
        dp.waitByTimeOut(1000);

        dp.verifyIsShown("ShowScreen");
        dp.assertEqual("Show Screen", dp.getValueOf("ShowScreen"));
        dp.waitByTimeOut(1000);
        dp.clickOn("ShowScreen");

        dp.verifyIsShown("devicelable");
        dp.assertEqual(devicename, dp.getValueOf("devicelable"));

//        dp.clickOn("ShowScreen");
    }

    @Test(priority=5)
    public void Test050checkCurrenterUseAndroidSingleScreen(){

        dp.verifyIsShown("normalsolodeviceBtn");
        dp.clickOn("normalsolodeviceBtn");

        //检查弹出按钮
        dp.verifyIsShown("normalcloseBtn");
        dp.verifyIsShown("qrcodeBtn");
        dp.verifyIsShown("rotateLeftBtn");
        dp.verifyIsShown("rotateRightBtn");
        dp.verifyIsShown("normalreloadScreenBtn");
//        dp.verifyIsShown("hideScreenBtn");
        dp.verifyIsShown("fullScreenBtn");

        dp.verifyIsShown("speedinfo");
        System.out.println("==============>speedinfo:" + dp.getValueOf("speedinfo"));
        dp.verifyIsShown("timeleftinfo");
        System.out.println("==============>timeleftinfo:" + dp.getValueOf("timeleftinfo"));

        String timeleft = dp.getValueOf("timeleftinfo");
        int timeleftminutes = Integer.parseInt(timeleft.split(":")[0]);
        int timeleftsecond = Integer.parseInt(timeleft.split(":")[1]);
        int fullseconds = 60;
        if (timeleftminutes <controltime){
            System.out.println("==============>倒计时正确");
        }
        else if (timeleftminutes==controltime && timeleftsecond <= fullseconds){
            System.out.println("==============>倒计时正确");
        }
        else {
            System.err.print("==============>倒计时错误");
        }

        dp.verifyIsShown("solorenewdeviceBtn");
        dp.verifyIsShown("MenuBtn");
        dp.verifyIsShown("HomeBtn");
        dp.verifyIsShown("BackBtn");

        dp.verifyIsShown("DefaultMenu");//检查默认菜单显示

        dp.waitByTimeOut(1000);
        System.out.println("CurrenterUseAndroidSingleCommonDefault check done");

    }


    @Test(priority=6)
    public void Test060ScreenshotMenuCheck(){
        dp.clickOn("ScreenshotMenu");
        dp.waitByTimeOut(3000);
        dp.verifyIsShown("ScreenshotBtn");
        dp.verifyIsShown("DownloadBtn");
        dp.verifyIsShown("MoreImagesBtn");
        dp.verifyIsShown("SelectAllBtn");
    }

    @Test(priority=7)
    public void Test070AppsMenuCheck(){
        dp.clickOn("AppsMenu");
        dp.waitByTimeOut(3000);
        dp.verifyIsShown("UploadBtn");
        dp.verifyIsShown("RemoveBtn");
    }

    @Test(priority=8)
    public void Test080AdvancedMenuCheck(){
        dp.clickOn("AdvancedMenu");
        dp.waitByTimeOut(3000);
        dp.verifyIsShown("Clipboardlable");
        dp.verifyIsShown("getClipboardBtn");
        dp.verifyIsShown("Navigationlable");
        dp.verifyIsShown("AdvancedInputlable");
        dp.verifyIsShown("Shelllable");
    }

    @Test(priority=9)
    public void Test090FileExploreMenuCheck(){
        dp.clickOn("FileExploreMenu");
        dp.waitByTimeOut(3000);
        dp.verifyIsShown("dirUpBtn");
        dp.verifyIsShown("dirInput");
        dp.verifyIsShown("submitBtn");
        dp.verifyIsShown("namelabel");
        dp.verifyIsShown("sizelabel");
        dp.verifyIsShown("datelabel");
        dp.verifyIsShown("permissionslabel");
    }

    @Test(priority=10)
    public void Test100InfoMenuCheck(){
        dp.clickOn("InfoMenu");
        dp.waitByTimeOut(3000);
        dp.verifyIsShown("deviceinfoBtn");
        dp.verifyIsShown("Physical Device");
        dp.verifyIsShown("CPU");
        dp.verifyIsShown("Memory");
        dp.verifyIsShown("Platform");
        dp.verifyIsShown("Battery");
        dp.verifyIsShown("Network");
        dp.verifyIsShown("SIM");
        dp.verifyIsShown("Hardware");
        dp.verifyIsShown("Display");
    }

    @Test(priority=11)
    public void Test110LogsMenuCheck(){
        dp.clickOn("LogsMenu");
        dp.waitByTimeOut(3000);
        dp.verifyIsShown("getBtn");
        dp.verifyIsShown("levelList");
        dp.clickOn("levelList");
        dp.verifyIsShown("levelOpt");
        dp.verifyIsShown("verboseOpt");
        dp.verifyIsShown("debugOpt");
        dp.verifyIsShown("infoOpt");
        dp.verifyIsShown("warnOpt");
        dp.verifyIsShown("errorOpt");
        dp.verifyIsShown("fatalOpt");
        dp.verifyIsShown("timeInput");
        dp.verifyIsShown("pidInput");
        dp.verifyIsShown("tidInput");
        dp.verifyIsShown("tagInput");
        dp.verifyIsShown("textInput");
        dp.verifyIsShown("clearBtn");
        dp.verifyIsShown("copyBtn");
    }

    @Test(priority=12)
    public void Test120singleFingerSlide(){
        //获取屏幕分辨率
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize);

        int width = screenSize.width;
        int height = screenSize.height;



        int righttopx = width/3;
        int righttopy = height/2;
        int lefttopx = width/9*2;
        int lefttopy = height/2;

        dp.dragAndDropBy(righttopx,righttopy,lefttopx,lefttopy);

    }

    @Test(priority=13)
    public void Test130setDefinitionCheck(){

        dp.verifyIsShown("setDefinition");
        System.out.println("Old definition is"+dp.getValueOf("setDefinition"));
        dp.waitByTimeOut(1000);
        dp.clickOn("setDefinition");
        dp.clickOn("HD");
        dp.assertEqual("HD",dp.getValueOf("setDefinition"));
        System.out.println("New definition is"+dp.getValueOf("setDefinition"));

        //恢复SD设置
        dp.clickOn("setDefinition");
        dp.clickOn("SD");

    }

    @Test(priority=14)
    public void Test140setExtraInfoCheck(){

        dp.verifyIsShown("ExtraInfo");
        dp.clickOn("ExtraInfo");
        dp.waitByTimeOut(1000);

        //需要先判断是否有勾选项，有的话，清除勾选
//        DPWebApp b = null;

//        List eicb = dp.getElements("ExtraInfocblist");
//        for(int i = 0; i < eicb.size(); i++ ){
//            System.out.println("=====================>"+eicb.get(i));
//            String extrainfocb = dp.getElementAttribute("eicb.get(i)","class");
//        string a=null;
        try
        {
//            a = dp.getElementAttribute("ScreenSizecheckbox","class").split(" ")[]
            if(dp.getElementAttribute("ScreenSizecheckbox","class").split(" ")[3].equals("ng-not-empty")) {
                dp.clickOn("ScreenSizecheckbox");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        try
        {
            if(dp.getElementAttribute("Versioncheckbox","class").split(" ")[3].equals("ng-not-empty")) {
                dp.clickOn("Versioncheckbox");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (dp.getElementAttribute("Brandcheckbox", "class").split(" ")[3].equals("ng-not-empty")) {
                dp.clickOn("Brandcheckbox");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (dp.getElementAttribute("Platformcheckbox", "class").split(" ")[3].equals("ng-not-empty")) {
                dp.clickOn("Platformcheckbox");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (dp.getElementAttribute("IMEIcheckbox", "class").split(" ")[3].equals("ng-not-empty")) {
                dp.clickOn("IMEIcheckbox");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (dp.getElementAttribute("ICCIDcheckbox", "class").split(" ")[3].equals("ng-not-empty")) {
                dp.clickOn("ICCIDcheckbox");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (dp.getElementAttribute("Widthcheckbox", "class").split(" ")[3].equals("ng-not-empty")) {
                dp.clickOn("Widthcheckbox");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(dp.getElementAttribute("Heightcheckbox","class").split(" ")[3].equals("ng-not-empty")){
                dp.clickOn("Heightcheckbox");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        dp.waitByTimeOut(1000);

        dp.clickOn("Platformcheckbox");
        dp.clickOn("Brandcheckbox");
//        dp.clickOn("ExtraInfo");
        dp.waitByTimeOut(3000);
        dp.verifyIsShown("Brandinfo");
        String brandtemp = "Brand: " + devicename.split(" ")[0];
        dp.assertEqual(brandtemp,dp.getValueOf("Brandinfo"));
        dp.waitByTimeOut(3000);
        dp.verifyIsShown("Platforminfo");
        dp.assertEqual("Platform: Android",dp.getValueOf("Platforminfo"));
        dp.waitByTimeOut(3000);

        //清除Extrainfo显示
//        dp.clickOn("ExtraInfo");
        dp.waitByTimeOut(1000);
        dp.clickOn("Platformcheckbox");
        dp.clickOn("Brandcheckbox");
        dp.clickOn("ExtraInfo");
    }

    @Test(priority=15)
    public void Test150RecentUsedReleaseInUseDeviceCheck(){

        dp.clickOn("RecentUsedMenu");
        dp.waitByTimeOut(1000);
        dp.verifyIsShown("recentinuseDevice");
        dp.clickOn("recentinuseDevice");
        dp.clickOn("releaseBtn");
        dp.waitByTimeOut(3000);
        dp.clickOn("releaseconfirmBtn");
        dp.waitByTimeOut(1000);
        dp.verifyIsShown("Your balance is changed");
//        dp.verifyIsShown("Your device session with "+devicename +" has ended and "+ (int)controlfee +".0 has been deducted from your account");

    }

//
//    }

}
