package com.by.automate.testCases.BAT.android;

import com.by.automate.fwk.DPWebApp;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_FC_MultipleAndroidsControl {
	private DPWebApp dp = null;

	@BeforeClass
	public void setUp() {
		dp = new DPWebApp();
//		dp.getXMLDoc("workspaces","screenshots");
 		dp.openApp("workspaces","screenshots","devices:AndroidsAndIos");
 		dp.DP_Login("wanglin04@beyondsoft.com","123456");
 		dp.waitByTimeOut(10000);
 	    }

    //清空dp系统中所有的截图
    @Test(priority = 1)
    public void test010_RemoveAllScreenshots() {
        dp.clickOn("LeftMenuScreenshots");
        dp.waitByTimeOut(1000);

	    if (dp.waitForElementReadyOnTimeOut("norecords",3000)) {
            dp.clickOn("LeftMenuAllDevices");
            dp.waitByTimeOut(2000);
        }
        else {
            dp.waitByTimeOut(500);
            while (dp.waitForElementReadyOnTimeOut("Load_more",5000)){
                dp.clickOn("Load_more");
                dp.waitByTimeOut(1000);
            }
            dp.clickOn("screenshots-selectAll");
            dp.clickOn("remove");
            dp.waitByTimeOut(1000);
            dp.clickOn("remove-confirm");
            dp.waitByTimeOut(1000);
        }
            dp.waitByTimeOut(1000);
            dp.clickOn("LeftMenuAllDevices");
            dp.waitByTimeOut(1000);
    }
//选择多台Android设备进入控屏页面
    @Test(priority = 2)
    public void test020_ControlMultipleAndroids() {
        //选择多个Android设备
    String androidDeviceName = dp.selectDevices(2).get(0);
       dp.waitByTimeOut(1000);
    //点击control按钮
        dp.waitByTimeout(1000);
        dp.clickOn("controlButton");
        dp.waitByTimeout(1000);
//        dp.verifyIsShown("modalTitle");
//        dp.verifyIsShown("textInfo");
        dp.verifyIsShown("inputControlTime");
        dp.verifyIsShown("cancelControlBtn");
        dp.verifyIsShown("confirmControlBtn");
        dp.waitByTimeout(500);
        dp.setValueTo("inputControlTime", "30");
        dp.waitByTimeOut(500);
    //点击control-confirm按钮进入control页面
        dp.clickOn("confirmControlBtn");
        dp.verifyIsShown("controlAndroidSuccess");
        dp.waitByTimeOut(500);

        // 验证控屏的两个设备的名字和选择的设备的名字是一致的
        dp.verifyIsShown("AndroidDeviceName1");
        dp.verifyIsShown("AndroidDeviceName2");
        String androidDeviceName1 = dp.getValueOf("AndroidDeviceName1").replace(" ","");
        String androidDeviceName2 = dp.getValueOf("AndroidDeviceName2").replace(" ","");
        dp.assertTrue(androidDeviceName1.contains(androidDeviceName1),"选择的设备和控屏的设备一致");
        dp.assertTrue(androidDeviceName1.contains(androidDeviceName2),"选择的设备和控屏的设备不一致");

        String getControlAndroidSuccess1Msg = dp.getValueOf("controlAndroidSuccess1");
        dp.waitByTimeOut(500);
        String getControlAndroidSuccess2Msg = dp.getValueOf("controlAndroidSuccess2");
        dp.waitByTimeOut(500);
        dp.log(getControlAndroidSuccess1Msg);
        dp.log(getControlAndroidSuccess2Msg);

        String getControlAndroidSuccess1MsgData = dp.getContentPropertry("dp.msg.controlAndroidSuccess1.success") + androidDeviceName1 + ".";
        String getControlAndroidSuccess2MsgData = dp.getContentPropertry("dp.msg.controlAndroidSuccess2.success") + androidDeviceName2 + ".";



        dp.waitByTimeOut(500);
        dp.verifyIsShown("androidDeviceName1");
        dp.verifyIsShown("androidDeviceName2");

        String getAndroidDeviceName1 = dp.getValueOf("androidDeviceName1");
        String getAndroidDeviceName2 = dp.getValueOf("androidDeviceName2");


        dp.log(getAndroidDeviceName1);
        dp.log(getAndroidDeviceName2);

//        dp.assertEqual(getAndroidDeviceName.replace(" ", ""), androidDeviceName);
//        dp.assertEqual(getIosDeviceName.replace(" ", ""), iosDeviceName);

    }

// 在android和ios控屏页面检查顶部菜单
    @Test(priority=3)
    public void test030_CheckAllButtonsAndRenew() {
        dp.verifyIsShown("TopScreenshotBtn");
        System.out.println(dp.getValueOf("TopScreenshotBtn"));
        dp.assertEqual("Screenshot", dp.getValueOf("TopScreenshotBtn"));
        dp.verifyIsShown("ReloadAll");
        dp.assertEqual("Reload All", dp.getValueOf("ReloadAll"));
        dp.verifyIsShown("");
        dp.assertEqual("Release All", dp.getValueOf("ReleaseAll"));
        dp.verifyIsShown("");
        dp.assertEqual("Renew", dp.getValueOf("RenewDevices"));
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
        dp.clickOn("setDefinition");
        dp.waitByTimeOut(1000);
        dp.verifyIsShown("ExtraInfo");
        dp.assertEqual("Extra Info", dp.getValueOf("ExtraInfo"));
        dp.clickOn("ExtraInfo");
        dp.waitByTimeOut(1000);
        dp.verifyIsShown("Timeleft");
        dp.waitByTimeOut(1000);
       //点击renew按钮
        dp.clickOn("RenewDevices");
        dp.waitByTimeout(500);
        dp.setValueTo("inputRenewTime", "5");
        dp.waitByTimeOut(500);
        dp.clickOn("RenewConfirm");
        String androidrestTime = dp.getValueOf("androidrestTime");
        String iosrestTime = dp.getValueOf("iosrestTime");
//       取time的冒号前整数
        String[] androidrestTime_str = androidrestTime.split(":");
        String androidrestTime_string = androidrestTime_str[0];

        String[] iosrestTime_str = iosrestTime.split(":");
        String iosrestTime_string = iosrestTime_str[0];
        String inputRenewTime = dp.getValueOf("inputRenewTime");

        int androidrestTime_int = Integer.parseInt(androidrestTime_string);
        int iosrestTime_int = Integer.parseInt(iosrestTime_string);
        int inputRenewTime_int = Integer.parseInt(inputRenewTime);

        int androidTotaltime_int = androidrestTime_int + inputRenewTime_int;
        int iosTotaltime_int = iosrestTime_int + inputRenewTime_int;

        if (androidTotaltime_int<36 && androidTotaltime_int>33){
            System.out.print("Renew android successfully");
        }
        else {System.out.print("Renew android failed");}

        if (iosTotaltime_int<36 && iosTotaltime_int>33){
            System.out.print("Renew IOS successfully");
        }
       else {System.out.print("Renew IOS failed");}
    }

//点击top screenshot按钮和中间的screenshot icon-> screenshots按钮
    @Test(priority=4)
    public void test040_ClickTwoScreenshotsButtons() {
        //点击top screenshot按钮
        dp.clickOn("TopScreenshotBtn");
        dp.verifyIsShown("TakeScreenshotMeg");
        dp.waitByTimeOut(1000);

//        String androidscreenshots = dp.getValueOf("androidscreenshotscount");

        String androidpicinfo = dp.getValueOf("?/?androidpictureinfo");
        dp.waitByTimeOut(1000);
        String iospicinfo = dp.getValueOf("?/?iospictureinfo");
        dp.waitByTimeOut(1000);

        System.out.println(androidpicinfo);
        System.out.println(iospicinfo);

        String androidpiccount = androidpicinfo.substring(2,3); //获取当前android设备存在的截图数量
        System.out.println(androidpiccount);

        String iospiccount = iospicinfo.substring(3,4); //获取当前ios设备存在的截图数量
        System.out.println(iospiccount);

        //点击screenshotsIcon->screenshots button
        dp.clickOn("ScreenshotBtn");
        int newandroidpiccount = Integer.parseInt(androidpiccount)+1; //截屏后的截图数量total多了一张
        System.out.println(newandroidpiccount);
        int newiospiccount = Integer.parseInt(iospiccount)+1; //截屏后的截图数量total多了一张
        System.out.println(newiospiccount);
        dp.waitByTimeOut(5000);

        //List<WebElement> pictures = dp.getElements("picture_itom");//获取当前设备存在的截图数量
        //dp.assertEqual(new_pictureNum,pictures.size());//截屏后，截图的数量比原来多了一张。


//    if (!dp.waitForElementReadyOnTimeOut("pictureitem",3000)){
//                dp.clickOn("ScreenshotBtn");
//                dp.waitByTimeOut(2000);
//                dp.verifyIsShown("pictureitem");
//            }
//            else{
//                String picnum = dp.getValueOf("?/?picture_info");
//                System.out.println(picnum);
//                String pictureNum = picnum.substring(3,4); //获取当前设备存在的截图数量
//                System.out.println(pictureNum);
//                dp.clickOn("screenshot_button");
//                int new_pictureNum = Integer.parseInt(pictureNum)+1; //截屏后的截图数量多了一张
//                dp.waitByTimeOut(5000);
//                //List<WebElement> pictures = dp.getElements("picture_itom");//获取当前设备存在的截图数量
//                //dp.assertEqual(new_pictureNum,pictures.size());//截屏后，截图的数量比原来多了一张。
//            }
     }


     }
//  public void select_IOS_device(){
//    List<WebElement> phone_items  = dp.getElements("phone_item_icon");
//        for(WebElement i : phone_items){
//            if (i.getText().contains("Available") && i.getText().contains("Apple")){
//                i.click();
//               break;
//            }
//        }
//    }


