package com.by.automate.testCases.BAT.android;

import com.by.automate.fwk.DPWebApp;
import com.by.automate.utils.CommonTools;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


/**
 * Created by dp on 2017/6/6.
 */
public class Test_FCSingle_IosDeviceControl {

    private DPWebApp dp =null;
    String PhoneName = null;

    @BeforeClass
    public void setUp(){

        dp = new DPWebApp();
        dp.openApp("devices","selectIosAlldevices");
        dp.DP_Login();
//        dp.getXMLDoc("devices","selectIosAlldevices");
//        dp.setViewTo("alldevices:alldevices");
    }

    @Test(priority = 1)
    public void Test010_sigleIosDeviceControlScreen(){
        dp.verifyIsShown("controlButton");
        dp.verifyIsShown("bookButton");
        dp.verifyIsShown("groupButton");
        dp.verifyIsShown("releaseButton");
        dp.waitByTimeout(500);

        // 选择单台iOS设备
        PhoneName = dp.selectDriversIOS(1).get(0);
        //dp.selectDevices(1);
        dp.waitByTimeout(500);
        dp.clickOn("controlButton");

        dp.verifyIsShown("modalTitle");
        dp.verifyIsShown("textInfo");
        dp.verifyIsShown("inputControlTime");
        dp.verifyIsShown("cancelControlBtn");
        dp.verifyIsShown("confirmControlBtn");
        dp.waitByTimeout(500);
        dp.setValueTo("inputControlTime","30");
        dp.waitByTimeOut(500);
        dp.clickOn("confirmControlBtn");

        dp.verifyIsShown("controlSucces");
        String getControlSuccesMsg = dp.getValueOf("controlSucces");
        dp.log(getControlSuccesMsg);
        String getControlSuccesMsgMsgData = dp.getContentPropertry("dp.msg.controlsingleios.success" )+ PhoneName;
        dp.assertEqual(getControlSuccesMsgMsgData.replace(" ",""), getControlSuccesMsg.replace(" ",""));
        dp.waitByTimeOut(500);
        dp.verifyIsShown("timeLeft");
        dp.verifyIsShown("iosDeviceName");
        String getIosDeviceName = dp.getValueOf("iosDeviceName");
        dp.log(getIosDeviceName);
        dp.assertEqual(getIosDeviceName.replace(" ",""),PhoneName);

    }

    @Test(priority = 2)
    public void Test020_rightMenuJumps(){
        dp.verifyIsShown("menuScreenshot");
        dp.verifyIsShown("menuApps");
        dp.verifyIsShown("menuInstalledApps");
        dp.verifyIsShown("menuInfo");
        dp.waitByTimeOut(1000);

        dp.clickOn("menuApps");
        dp.verifyIsShown("uploadIosApp");
        dp.verifyIsShown("removeIosApp");
        dp.waitByTimeOut(800);
        dp.clickOn("menuInstalledApps");
        dp.verifyIsShown("reloadInstalledApps");
        dp.waitByTimeOut(800);
        dp.clickOn("menuInfo");
        dp.verifyIsShown("physicalDeviceInfo");
        dp.verifyIsShown("cpuInfo");
        dp.verifyIsShown("memoryInfo");
        dp.waitByTimeOut(800);
        dp.clickOn("menuScreenshot");
        dp.verifyIsShown("tekaScreenBtn");
        dp.verifyIsShown("downloadBtn");
        dp.verifyIsShown("moreImagesBtn");
        dp.verifyIsShown("selectAllBtn");
        dp.waitByTimeOut(1000);

    }

    @Test(priority = 3)
    public void Test030_changeDedinition(){

        dp.verifyIsShown("changeDefinition");
        dp.clickOn("changeDefinition");
        dp.verifyIsShown("setUD");
        dp.verifyIsShown("setHD");
        dp.verifyIsShown("setSD");
        dp.verifyIsShown("setLD");
        dp.waitByTimeOut(500);
        dp.clickOn("setHD");
//        String value = dp.getValueOf("setHD");
//        String valueActual = dp.getValueOf("changeDefinition");
//        dp.assertEqual("changeDefinition","setHD");

    }
    @Test(priority = 4)
    public void Test040_addExtraInfo() {
        dp.verifyIsShown("extrainfoBtn");
        dp.clickOn("extrainfoBtn");
        dp.waitByTimeOut(500);
        dp.verifyIsShown("allExtraCheckbox",1);
        List<WebElement> allCheckbox = dp.getElements("allExtraCheckbox");
        int beginTime = 0;
        for(int i = 0; i < allCheckbox.size();i ++){
            String classVal = dp.getElementAttribute("allExtraCheckbox","class");
            if(classVal.contains("ng-not-empty")){
                dp.clickOn("allExtraCheckbox",i);
                dp.waitByTimeOut(500);
                beginTime = i;
                break;
            }

        }
        dp.waitByTimeOut(2000);
        List<WebElement> extraInfos = null;
        dp.verifyIsShown("allExtraInfo", 1);
        extraInfos = dp.getElements("allExtraInfo");
        for (int i = 1; i <= extraInfos.size(); i++) {
            dp.log(i);
        }
        int[] fournumber = this.randomGenerator(4, extraInfos.size()); // 将生成的随机数放到一个数组
        extraInfos = dp.getElements("allExtraInfo");  // 再取一次所有的allExtraInfo，避免数据过期
        for (int j = 0; j<fournumber.length; j ++){
            extraInfos.get(fournumber[j]).click(); // 依次点击生成的随机数
        }

    }

    @Test(priority = 5)
    public void Test050_screenshots(){
        dp.clickOn("tekaScreenBtn");
        dp.waitByTimeOut(4000);
        /*dp.clickOn("tekaScreenBtn");
        dp.waitByTimeOut(500);*/
        dp.verifyIsShown("screenshotdata");
        int data1 = getScreenShotCount(dp.getValueOf("screenshotdata"));//取整串里的单个值
        dp.log(data1);

        dp.waitByTimeout(2000);
        dp.clickElementByJS("tekaScreenBtn");
        dp.waitByTimeOut(3000);
        dp.verifyIsShown("screenshotImg");
        int data2 = getScreenShotCount(dp.getValueOf("screenshotdata"));
        dp.log(data2);
        dp.assertTrue(data1 == data2 - 1, "截图失败");



    }

    @Test(priority = 6)
    public void Test060_releaseDevice(){
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

        // 验证释放设备后的推送消息
        /*dp.verifyIsShown("releaseDevicesSuccessMsg");
        String getReleaseDevicesSuccessMsg = dp.getValueOf("releaseDevicesSuccessMsg")+".";
        dp.log(getReleaseDevicesSuccessMsg);
        String getReleaseDevicesSuccessMsgData = dp.getContentPropertry("dp.msg.releasesingleios.success" );

        String  getReleaseDevicesSuccessMsgDataVal= getReleaseDevicesSuccessMsgData.replace("[phoneName]",PhoneName);
        dp.assertEqual(getReleaseDevicesSuccessMsgDataVal.replace(" ",""), getReleaseDevicesSuccessMsg.replace(" ",""));*/

        dp.verifyIsShown("controlButton");
        dp.verifyIsShown("bookButton");
        dp.verifyIsShown("groupButton");
        dp.verifyIsShown("releaseButton");


    }
    @AfterClass
    public void setTearDown(){

            dp.close();
    }


    // 封装方法 生成多个随机数
    private int[] randomGenerator(int number,int max){
        int[] ret = new int[number];
        for(int i =0; i < number; i++){
            int random  = CommonTools.getRandom(max);
            ret[i] = random;
        }
        return ret;
    }

    private int getScreenShotCount(String value) {

        // (5 / 5 pictures )

        value = StringUtils.substringBetween(value, "(", ")").trim(); //去两端的括号和空格
        value = value.replace("pictures", "").replace("picture", "").trim();
        String[] str = value.split("/"); //分割数组
        return Integer.parseInt(str[1].trim());
    }




}


