package com.by.automate.testCases.BAT.android;

import com.by.automate.fwk.DPWebApp;
import com.by.automate.utils.CommonTools;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by dp on 2017/6/15.
 */
public class Test_FCMultiple_iOSDevicesControl {

    private DPWebApp dp =null;
    private String uploadAppName;
    private String iosAppName  = "./td[@class='ng-binding'][1]";
    private String iosRemoveApp  = "./td[3]//span/i[@uib-tooltip='Uninstall']";
    WebElement iosAppNameAll = null;
    List<WebElement> allInstalledApp  =  null;

    List<String> phonesName = null;
     @BeforeClass
    public void setUp(){

        dp = new DPWebApp();
        dp.openApp("devices","selectIosAlldevices");
        //dp.DP_Login();
        dp.DP_Login("qinhuan01@beyondsoft.com","123456");

    }

    @Test(priority = 1)
    public void Test010_sigleIosDeviceControlScreen() {
        //dp.getElementAttribute("controlButton","disabled");
        dp.verifyIsShown("controlButton");
        dp.verifyIsShown("bookButton");
        dp.verifyIsShown("groupButton");
        dp.verifyIsShown("releaseButton");
        dp.waitByTimeout(500);

        // 选择多台iOS设备
        phonesName = dp.selectDriversIOS(2);
        dp.waitByTimeout(2000);
        dp.clickOn("controlButton");

        dp.verifyIsShown("modalTitle");
        dp.verifyIsShown("textInfo");
        dp.verifyIsShown("inputControlTime");
        dp.verifyIsShown("cancelControlBtn");
        dp.verifyIsShown("confirmControlBtn");
        dp.waitByTimeout(500);
        dp.setValueTo("inputControlTime", "30");
        dp.waitByTimeOut(500);
        dp.clickOn("confirmControlBtn");

        // 验证控屏的两个设备的名字和选择的设备的名字是一致的
        dp.verifyIsShown("iosDeviceName1");
        dp.verifyIsShown("iosDeviceName2");
        String phoneName1 = dp.getValueOf("iosDeviceName1").replace(" ","");
        String phoneName2 = dp.getValueOf("iosDeviceName2").replace(" ","");
        dp.assertTrue(phonesName.contains(phoneName1),"选择的设备和控屏的设备不一致");
        dp.assertTrue(phonesName.contains(phoneName2),"选择的设备和控屏的设备不一致");

        // 验证弹出的控屏成功的期望消息和实际取出来的信息一致
        dp.verifyIsShown("controlSucces1");
        dp.verifyIsShown("controlSucces2");
        String getControlSucces1Msg = dp.getValueOf("controlSucces1").replace(" ","");
        String getControlSucces2Msg = dp.getValueOf("controlSucces2").replace(" ","");
        boolean falg = false;
        for (int i = 0; i< phonesName.size(); i++){

            if(getControlSucces1Msg.contains(phonesName.get(i))){
                String phonesName1 = phonesName.get(i);
                String getControlSucces1MsgMsgData = dp.getContentPropertry("dp.msg.controlnultipleios1.success" )+ phonesName1;
                dp.assertEqual(getControlSucces1MsgMsgData.replace(" ",""),getControlSucces1Msg);
                phonesName.remove(1);
                falg = true;
                i = 0;

            }

            if(getControlSucces2Msg.contains(phonesName.get(i))){
                String phonesName2 = phonesName.get(i);
                String getControlSucces2MsgMsgData = dp.getContentPropertry("dp.msg.controlnultipleios2.success" )+ phonesName2;
                dp.assertEqual(getControlSucces2MsgMsgData.replace(" ",""),getControlSucces2Msg);
                phonesName.remove(1);
                falg = true;
                i = 0;

            }

        }

    }


    @Test(priority = 2)
    public void Test020_uploadAndInstallApp(){
        dp.verifyIsShown("menuApps");
        dp.clickOn("menuApps");
        dp.waitByTimeOut(500);
        dp.verifyIsShown("uploadIosApp");
        dp.verifyIsShown("removeIosApp");
        dp.waitByTimeOut(500);
        dp.verifyIsShown("appNameList",1);
        List<WebElement> allAppName = dp.getElements("appNameList");
        for (int i = 0; i < allAppName.size(); i++) {
            String appName = allAppName.get(i).getText();
            if (appName.equals("火车票")){
                allAppName.get(i).click();
                break;

            }
        }


        //dp.clickOn("uploadIosApp");
        /*this.uploadAppName = dp.uploadAppByIOS();//上传iOS的APP
        core.isUploadStatus(); //获取上传的进度
*/
        dp.waitByTimeOut(500);
        dp.verifyIsShown("installIosApp");
        dp.clickElementByJS("installIosApp");
        dp.verifyIsShown("installingIosApp");
        String name = dp.getValueOf("installingIosApp");

        long beginTime = System.currentTimeMillis();//超时的验证
        while (name.equals("installing...")){ // 当包含，表示处于安装
            dp.waitByTimeOut(800);
            name = dp.getValueOf("installingIosApp");
            long endTime = System.currentTimeMillis();
            if ((endTime - beginTime)/1000 > 60){ //如果超过1分钟，表示安装失败
                throw new TimeoutException("uplaod ios app failed.");
            }
        }



    }

    @Test(priority = 3)
    public void Test030_showScreenOperate() {
        dp.waitByTimeOut(2000);
        dp.verifyIsShown("showScreenBtn");
        dp.clickOn("showScreenBtn");
        dp.waitByTimeOut(500);
        //等修复1666的bug后恢复
        dp.verifyIsShown("allUsingphone", 1);
        dp.verifyIsShown("allUsingphoneCheckbox", 1);

        List<WebElement> allCheckbox = dp.getElements("allUsingphoneCheckbox");
        int random = CommonTools.getRandom(allCheckbox.size());//生成随机数
        allCheckbox.get(random).click();
        /*dp.verifyIsShown("firstUsingphoneCheckbox");
        dp.clickOn("firstUsingphoneCheckbox");
*/
        dp.verifyIsNotShown("synconBtn");// 验证该元素消失
        dp.waitByTimeOut(500);

        /* int beginTime = 0;
        for (int i = 0; i < allCheckbox.size(); i++) {
            String classVal = dp.getElementAttribute("allUsingphoneCheckbox", "class");
            if (classVal.contains("ng-not-empty")) {
                dp.clickOn("allExtraCheckbox", i);
                dp.waitByTimeOut(500);
                beginTime = i;
                break;
            }

        }*/
    }

    @Test(priority = 4)
    public void Test040_uninstalledApp(){
        dp.verifyIsShown("menuInstalledApps");
        dp.clickOn("menuInstalledApps");
        dp.verifyIsShown("reloadInstalledApps");
        dp.verifyIsShown("allInstalledAppList",1);
        allInstalledApp = dp.getElements("allInstalledAppList");
        for (int i = 0; i < allInstalledApp.size(); i++) {
            String appName = allInstalledApp.get(i).findElement(By.xpath(iosAppName)).getText();
            if (appName.equals("火车票")){
                allInstalledApp.get(i).findElement(By.xpath(iosRemoveApp)).click();
                break;

            }
        }
        dp.waitByTimeOut(600);
        dp.verifyIsShown("confirmTitle");
        dp.verifyIsShown("uninstallAppMessage");
        dp.verifyIsShown("cancelUninstallApp");
        dp.verifyIsShown("confirmUninstallApp");
        dp.clickOn("confirmUninstallApp");
        dp.waitByTimeOut(1000);
        dp.verifyIsShown("successUninstallAppMessage");
        String getSuccessUninstallAppMessage = dp.getValueOf("successUninstallAppMessage");
        dp.log(getSuccessUninstallAppMessage);
        dp.waitByTimeOut(500);
        String getSuccessUninstallAppMessageData = dp.getContentPropertry("dp.msg.uninstallapp.success");
        dp.assertEqual(getSuccessUninstallAppMessageData, getSuccessUninstallAppMessage);



    }


    @Test(priority = 5)
    public void Test050_fullScreen(){

            dp.verifyIsShown("threePoint");
            dp.clickOn("threePoint");
            dp.waitByTimeOut(500);
            dp.verifyIsShown("fullScreenBtn");
            dp.clickOn("fullScreenBtn");
            dp.verifyIsShown("udInFullscreen");
            dp.verifyIsShown("hdInFullscreen");
            dp.verifyIsShown("sdInFullscreen");
            dp.verifyIsShown("ldInFullscreen");
            dp.verifyIsShown("homeInFullscreen");
            dp.verifyIsShown("rotateLeftInFullscreen");
            dp.verifyIsShown("rotateRightInFullscreen");
            dp.verifyIsShown("screenshotInFullscreen");
            dp.waitByTimeOut(500);
            dp.verifyIsShown("closeFullScreen");
            //dp.clickOn("closeFullScreen");
            dp.clickElementByJS("closeFullScreen");
            dp.uiMapUpdateView("alldevices:control");// 跳转回control界面

    }

    @Test(priority = 6)
    public void Test060_trickCheckboxAgain(){
        dp.waitByTimeOut(2000);
        dp.verifyIsShown("showScreenBtn");
        dp.clickElementByJS("showScreenBtn");
        //dp.clickOn("showScreenBtn");
        dp.waitByTimeOut(500);
       /* dp.verifyIsShown("firstUsingphoneCheckbox");
        dp.clickOn("firstUsingphoneCheckbox");*/
        //等修复1666bug
        dp.verifyIsShown("allUsingphone", 1);
        dp.verifyIsShown("allUsingphoneCheckbox", 1);

        List<WebElement> allCheckbox = dp.getElements("allUsingphoneCheckbox");
        int beginTime = 0;
        for (int i = 0; i < allCheckbox.size(); i++) {
            String classVal = dp.getElementAttribute("allUsingphoneCheckbox", i+1,"class");
            if (classVal.contains("fa-square-o")) {
                dp.clickOn("allUsingphoneCheckbox", i+1);
                dp.waitByTimeOut(500);
                beginTime = i;
                break;
            }
        }
        dp.verifyIsShown("synconBtn");// 验证该元素出现
        dp.waitByTimeOut(500);

    }

    @Test(priority = 7)
    public void Test070_releaseDevice(){
        dp.verifyIsShown("releaseAllBtn");
        dp.clickOn("releaseAllBtn");
        dp.waitByTimeout(1000);
        dp.verifyIsShown("titleIcon");
        dp.verifyIsShown("confirmTitle");
        dp.verifyIsShown("closeBtn");
        dp.verifyIsShown("checkbox");
        String classVal = dp.getElementAttribute("checkbox","class");
        if(classVal.contains("ng-empty")){
            dp.clickOn("checkbox");
        }else {
            dp.log("checkbox has been cheked.");
        }
        dp.verifyIsShown("cancelBtn");
        dp.verifyIsShown("confirmBtn");

        dp.verifyIsShown("confirmBtn");
        dp.clickOn("confirmBtn");

        dp.verifyIsShown("controlButton");
        dp.verifyIsShown("bookButton");
        dp.verifyIsShown("groupButton");
        dp.verifyIsShown("releaseButton");

    }

    @AfterClass
    public void setTearDown(){

        dp.close();
    }

}
