package com.by.automate.testCases.BAT.android;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.by.automate.utils.CommonTools;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.by.automate.fwk.DPWebApp;

public class Test_FCBookingDevices {

	private DPWebApp dp = null;
	private List<String> deviceNames;

	@BeforeClass
	public void setUp() {

		dp = new DPWebApp();
		dp.openApp();

	}

	@Test(priority=1)
	public void Test010_SelectDevicesAndGotoBook() {

		// login
		dp.DP_Login();

		// 验证头部信息
		dp.verifyIsShown("jiantou");
		dp.verifyIsShown("messageCenter");
		dp.verifyIsShown("help");
		dp.verifyIsShown("testStatus");
		dp.verifyIsShown("userName");
	}

    @Test(priority=2)
	public void Test020_BookDevice(){

		deviceNames = dp.selectDevices(1);
		//dp.verifyIsShown("moreButton");
		//dp.clickOn("moreButton");
		dp.verifyIsShown("bookingButton");
		dp.clickOn("bookingButton");
		dp.verifyIsShown("bookDeviceNames" ,1);

		List<WebElement> devices = dp.getElements("bookDeviceNames");

		for (int i = 0; i < devices.size(); i++) {

			String getDeviceNameOnBookDevicePage = devices.get(i).getText().replace(" ", "").replace("\n", "");
			boolean falg =false;
			for (int j = 0; j < deviceNames.size(); j++) {
				dp.log("book device -- " + getDeviceNameOnBookDevicePage);
				dp.log("device -- " + deviceNames.get(j) );
				if(getDeviceNameOnBookDevicePage.equals(deviceNames.get(j))){
					falg = true;
					break;
				}
			}
			dp.assertTrue(falg, "选择设备预约,在book device 页面显示设备名字不对应.");

		}

	}

	@Test(priority=3)
    public void Test030_SelectTimeToBookSelf(){

	    dp.verifyIsShown("allTimeBlocks",1); // 获取所有时间块（没半个小时一个，但定位符包含50个）
	    List<WebElement> allTimeBlockNum = dp.getElements("allTimeBlocks");
	    int beginTime = 0;
	    for (int i = 0;i<allTimeBlockNum.size();i++) {
	        String classVal = allTimeBlockNum.get(i).getAttribute("class");
	        dp.log(classVal);
	        if(classVal.contains("bookable") && !(classVal.contains("notBookable"))){
	            beginTime = i;
	            break;
            }
        }
        Map<Integer,Integer> number = new HashMap<Integer, Integer>();
	    int key = 1;
        for (int i = beginTime +1;i<allTimeBlockNum.size();i++) {
            String classVal = allTimeBlockNum.get(i).getAttribute("class");
            dp.log(classVal);
            if(classVal.contains("bookable")){
                number.put(key,i);
                key++;
            }else{
                continue;
            }
        }
        int random = CommonTools.getRandom(number.size());
        dp.clickOn("allTimeBlocks",number.get(random));
        dp.waitByTimeout(500);

        if(number.get(random) >25 && number.get(random) <=50){
            String timeBlock = allTimeBlockNum.get(number.get(random)-1).getAttribute("title");
            dp.log(timeBlock);

            //int currentHour = Integer.parseInt(timeBlock.split(":"))[0];

        }


    }



	/*@AfterClass
	public void tearDown(){

		dp.close();
	}*/

}
