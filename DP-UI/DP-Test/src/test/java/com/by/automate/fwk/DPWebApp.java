package com.by.automate.fwk;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.by.automate.base.WebApp;

public class DPWebApp extends WebApp {

	public String email;
	public String password;
	public String totalTeams = "./td[2]/span";  //定义所有列 的集合，(/ 前面加"."来表示在验的时候，从第一行第一个开始执行到最后一个)
	public String companyNameTitle  = "./td[1]";
	public String totalUsers  = "./td[3]/span";
	public String totalDevices  = "./td[4]/span";  // String 是一个字符串类型， 该后面元素定义的是将 定位符 直接过来
	public String deploymentOption  = "./td[5]";
	public String storageSizeTitle  = "./td[6]";
	public String createDateTitle  = "./td[7]";

	public DPWebApp() {

		this.email = getSutFullFileName("dp.userName");
		this.password = getSutFullFileName("dp.password");

	}

	@Override
	protected String getAppName() {

		return "DPWebApp";
	}

	/**
	 * 封装方法,login, 提供给所有的测试脚本.调用该方法,直接可以登录到Devicepass网站.
	 *
	 * @param email
	 *            用户名
	 * @param password
	 *            密码
	 */
	public void DP_Login(String email, String password) {

		if (email.equals("")) {
			email = this.email;
		}
		if (password.equals("")) {
			password = this.password;
		}

		verifyIsShown("email");
		verifyIsShown("password");

		setValueTo("email", email);
		waitByTimeOut(1000);
		setValueTo("password", password);

		clickOn("loginButton");
		waitByTimeOut(2000);
	}

	public void DP_Login() {

		this.DP_Login("", "");
	}

	public String uploadApp() {

		// setValue("upload",
		// "C:\\Users\\qinhuan02\\Desktop\\ep2p_automation_.apk");
		String path = (this.dataFile + "App/android_apk/" + this.getSutFullFileName("upload.App")).replace("/", "\\");
		//setValue("upload", path);
		sendKeys("upload", path);
		return this.getSutFullFileName("upload.App").replace(".apk", "").replace(".ipa", "");
	}

	/*
	 * 上传android脚本文件
	 * @author: maoyjia
	 *
	 * @param  elementname
	 * 				定位的元素名字。
	 * @param  frameName
	 * 				脚本框架类型；appiumPython、calabash、uiautomator
	 */
	public void uploadAndroidScript(String elementname,String frameName) {
		String path = "";
		if(frameName=="appiumPython"){
			path = (this.dataFile + "App/android_apk/AppiumPythonScript/" + this.getSutFullFileName("upload.androidScript_pythonAppium")).replace("/", "\\");
		}
		if(frameName=="calabash"){
			path = (this.dataFile + "App/android_apk/CalabashScript/" + this.getSutFullFileName("upload.androidScript_calabash")).replace("/", "\\");
		}
		if(frameName=="uiautomator"){
			path = (this.dataFile + "App/android_apk/UiAutomatorScript/" + this.getSutFullFileName("upload.androidScript_uiautomator")).replace("/", "\\");
		}

		sendKeys(elementname, path);
	}

	public String uploadAppByIOS() {

		// setValue("upload",
		// "C:\\Users\\qinhuan02\\Desktop\\ep2p_automation_.apk");
		String path = (this.dataFile + "App/iOS_app/" + this.getSutFullFileName("upload.ipa")).replace("/", File.separatorChar+"");
		//setValue("upload", path);
		sendKeys("upload", path);
		return this.getSutFullFileName("upload.ipa").replace(".apk", "").replace(".ipa", "");
	}


	public String uploadScriptByIOS() {

		// setValue("upload",
		// "C:\\Users\\qinhuan02\\Desktop\\ep2p_automation_.apk");
		String path = (this.dataFile + "App/iOS_script/" + this.getSutFullFileName("upload.iosScript")).replace("/", File.separatorChar+"");
		sendKeys("selectFile", path);
		return this.getSutFullFileName("upload.iosScript").replace(".zip", "").replace(".zip", "");
	}

	/**
	 * 随机选择设备可用设备
	 *
	 * @param number
	 *            设备个数 -- 当传入数字大于平台设备数量时，默认会选择平台所有的可用设备.
	 *
	 * @return
	 *
	 * 		返回随机选择设备的名字
	 */
	public List<String> selectDevices(int number) {

		List<String> deviceNames = new ArrayList<String>();
		verifyIsShown("allDevices", 1);
		verifyIsShown("allDevicesStatus", 1);
		verifyIsShown("devicesNames", 1);

		int countDevices = getElementsSize("allDevices");

		List<WebElement> status = getElements("allDevicesStatus");
		String locator = browserName.equals("*firefox") ?"devicesNamesByFirefox":"devicesNames";
		List<WebElement> names = getElements(locator);

		Map<Integer, String> deviceList = new HashMap<Integer, String>();
		List<Integer> device_no = new ArrayList<Integer>();

		for (int i = 0; i < countDevices; i++) {

			if(browserName.equals("*firefox")){
				log(names.get(i).getAttribute("title") + " -- " + status.get(i).getText() + " -- "
						+ status.get(i).getAttribute("class"));
			}else{
				log(names.get(i).getText() + " -- " + status.get(i).getText() + " -- "
						+ status.get(i).getAttribute("class"));
			}


			String classVal = status.get(i).getAttribute("class");
			if (!(classVal.equals(DeviceData.DEVICE_PREPARING)) && !(classVal.equals(DeviceData.DEVICE_DISCONNECTED)) && !(classVal.equals(DeviceData.DEVICE_CLEANING)) && !(classVal.equals(DeviceData.DEVICE_BUSY)) && !(classVal.equals(DeviceData.DEVICE_UNAUTHORIZED))) {
				String name  = "";
				if(browserName.equals("*firefox")){
					name = names.get(i).getAttribute("title");
				}else{
					name = names.get(i).getText();
				}

				// 添加 iphone 的判断
				if(!(name.contains("Apple") && name.contains("iPhone"))){
					// 排除相同device name 因为在选择时会根据组来统计所有的设备, 然后会出现相同设备的名字
					if(!deviceList.values().contains(name)){
						device_no.add(i);
						deviceList.put(i, name);
					}
				}

				/*boolean statu = true;
				for (String device : deviceList.values()) {
					if(device.equals(name)){
						statu = false;
						break;
					}
				}
				if(statu){
					device_no.add(i);
					deviceList.put(i, name);
				}*/
			}

		}

		if (number > device_no.size()) {
			number = device_no.size();
		}
		for (int i = 0; i < number; i++) {
            List<WebElement> devices = getElements("allDevices");
			int random = (int) (Math.random() * countDevices);
			log("random : " + random);
			int val = getValidDevice(device_no, random);
			if (val == -1) {
				i--;
			} else {
				devices.get(val).click();

				deviceNames.add(deviceList.get(val).replace(" ", ""));
				waitByTimeOut(500);

			}
		}

		return deviceNames;
	}


    /**
     * 随机选择可以预约的设备
     *
     */
    public List<String> bookingDevices(int number) {

        List<String> deviceNames = new ArrayList<String>();
        verifyIsShown("allDevices", 1);
        verifyIsShown("allDevicesStatus", 1);
        verifyIsShown("devicesNames", 1);

        int countDevices = getElementsSize("allDevices");


        Map<Integer, String> deviceList = new HashMap<Integer, String>();
        List<Integer> device_no = new ArrayList<Integer>();
        // TODO
        Map<Integer,Integer> devicesBookable = new HashMap<Integer, Integer>();
        int index = 1;
        for (int k = 0; k < countDevices; k++){
            String bookDevicesLocator = "./div/i";
            List<WebElement> photos = getElements("photos");

            try {
                WebElement element = photos.get(k).findElement(By.xpath(bookDevicesLocator));
                waitByTimeout(800);
                if(element.isDisplayed()){
                    devicesBookable.put(index,k);
                    index++;
                }else{
                    log(k +"设备没有预约标志");
                    continue;
                }
            }catch (Exception e){
                log(k +"设备没有预约标志");
                continue;
            }

        }

        log(devicesBookable.toString());
        // END

        List<WebElement> devices = getElements("allDevices");
        List<WebElement> status = getElements("allDevicesStatus");
        String locator = browserName.equals("*firefox") ?"devicesNamesByFirefox":"devicesNames";
        List<WebElement> names = getElements(locator);
        for (int i = 1; i <= devicesBookable.size(); i++) {

            String classVal = status.get(devicesBookable.get(i)).getAttribute("class");
            log(classVal);
            if (!(classVal.equals(DeviceData.DEVICE_BUSY)) && !(classVal.equals(DeviceData.DEVICE_PREPARING)) && !(classVal.equals(DeviceData.DEVICE_DISCONNECTED)) && !(classVal.equals(DeviceData.DEVICE_UNAUTHORIZED))  && !(classVal.equals(DeviceData.DEVICE_CLEANING))) {
                String name  = "";
                if(browserName.equals("*firefox")){
                    name = names.get(devicesBookable.get(i)).getAttribute("title");
                }else{
                    name = names.get(devicesBookable.get(i)).getText();
                    log(name);
                }

                // 添加 iphone 的判断
                if(!(name.contains("Apple") && name.contains("iPhone"))){
                    // 排除相同device name 因为在选择时会根据组来统计所有的设备, 然后会出现相同设备的名字
                    if(!deviceList.values().contains(name)){
                        device_no.add(i);
                        deviceList.put(i, name);
                    }
                }

				/*boolean statu = true;
				for (String device : deviceList.values()) {
					if(device.equals(name)){
						statu = false;
						break;
					}
				}
				if(statu){
					device_no.add(i);
					deviceList.put(i, name);
				}*/
            }

        }
        log(deviceList.toString());
        if (number > device_no.size()) {
            number = device_no.size();
        }
        for (int i = 0; i < number; i++) {

            int random = (int) (Math.random() * countDevices);
            log("random : " + random);
            int val = getValidDevice(device_no, random);
            if (val == -1) {
                i--;
            } else {
                devices.get(val).click();

                deviceNames.add(deviceList.get(val).replace(" ", ""));
                waitByTimeOut(500);

            }
        }

        return deviceNames;
    }

	/**
	 * 随机选择设备可用设备(iOS)
	 *
	 * @param number
	 *            设备个数 -- 当传入数字大于平台设备数量时，默认会选择平台所有的可用设备.
	 *
	 * @return
	 *
	 * 		返回随机选择设备的名字
	 */
	public List<String> selectDriversIOS(int number) {

		List<String> deviceNames = new ArrayList<String>();
		verifyIsShown("allDevices", 1);
		verifyIsShown("allDevicesStatus", 1);
		verifyIsShown("devicesNames", 1);

		int countDevices = getElementsSize("allDevices");
		List<WebElement> devices = getElements("allDevices");
		List<WebElement> status = getElements("allDevicesStatus");
		String locator = browserName.equals("*firefox") ?"devicesNamesByFirefox":"devicesNames";
		List<WebElement> names = getElements(locator);

		Map<Integer, String> deviceList = new HashMap<Integer, String>();
		List<Integer> device_no = new ArrayList<Integer>();

		for (int i = 0; i < countDevices; i++) {

			if(browserName.equals("*firefox")){
				log(names.get(i).getAttribute("title") + " -- " + status.get(i).getText() + " -- "
						+ status.get(i).getAttribute("class"));
			}else{
				log(names.get(i).getText() + " -- " + status.get(i).getText() + " -- "
						+ status.get(i).getAttribute("class"));
			}


			String classVal = status.get(i).getAttribute("class");
            if (!(classVal.equals(DeviceData.DEVICE_PREPARING)) && !(classVal.equals(DeviceData.DEVICE_DISCONNECTED)) && !(classVal.equals(DeviceData.DEVICE_CLEANING)) && !(classVal.equals(DeviceData.DEVICE_BUSY)) && !(classVal.equals(DeviceData.DEVICE_UNAUTHORIZED))) {
				String name  = "";
				if(browserName.equals("*firefox")){
					name = names.get(i).getAttribute("title");
				}else{
					name = names.get(i).getText();
				}

				// 添加 iphone 的判断
				if((name.contains("Apple") && name.contains("iPhone"))){
					// 排除相同device name 因为在选择时会根据组来统计所有的设备, 然后会出现相同设备的名字
					if(!deviceList.values().contains(name)){
						device_no.add(i);
						deviceList.put(i, name);
					}
				}

				/*boolean statu = true;
				for (String device : deviceList.values()) {
					if(device.equals(name)){
						statu = false;
						break;
					}
				}
				if(statu){
					device_no.add(i);
					deviceList.put(i, name);
				}*/
			}

		}

		if (number > device_no.size()) {
			number = device_no.size();
		}
		for (int i = 0; i < number; i++) {

			int random = (int) (Math.random() * countDevices);
			log("random : " + random);
			int val = getValidDevice(device_no, random);
			if (val == -1) {
				i--;
			} else {
				devices.get(val).click();

				deviceNames.add(deviceList.get(val).replace(" ", ""));
				waitByTimeOut(500);

			}
		}

		if (deviceNames.size()!= number || deviceNames == null){
			throw new NullPointerException("No available devices found for IOS.");
		}

		return deviceNames;
	}

	public void selectDeviceByName(String ... names){

		List<WebElement> devices = getElements("allDevices");
		String locator = browserName.equals("*firefox") ?"devicesNamesByFirefox":"devicesNames";
		List<WebElement> dn = getElements(locator);
		Map<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i < dn.size(); i++) {
			String name  = "";
			if(browserName.equals("*firefox")){
				name = dn.get(i).getAttribute("title").replace(" ", "");
			}else{
				name = dn.get(i).getText().replace(" ", "");
			}
			map.put(name, i);
		}

		for (int i = 0; i < names.length; i++) {
			int index = map.get(names[i]);
			devices.get(index).click();
			waitByTimeOut(500);
		}

	}

	public String getDeviceStatusByName(String names){

	    verifyIsShown("allDevicesStatus",1);
		List<WebElement> status = getElements("allDevicesStatus");
		String locator = browserName.equals("*firefox") ?"devicesNamesByFirefox":"devicesNames";
		List<WebElement> dn = getElements(locator);
		Map<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i < dn.size(); i++) {
			String name  = "";
			if(browserName.equals("*firefox")){
				name = dn.get(i).getAttribute("title").replace(" ", "");
			}else{
				name = dn.get(i).getText().replace(" ", "");
			}
			map.put(name, i);
		}

		int index = map.get(names);
		return status.get(index).getText();
	}


	private int getValidDevice(List<Integer> list,Integer random) {

		if(list.contains(random)){
			list.remove(random);
			return random;
		}else{
			return -1;
		}
	/*	for (Integer integer : list) {
			if (integer == random) {
				list.remove(integer);
				return random;
			} else {
				continue;
			}
		}

		return -1;*/
	}

	public List<String> createGroupAndAddDevices(String groupName, int device_number){

		// select device
		List<String> deviceNames = selectDevices(device_number);

		verifyIsShown("groupButton");
		clickOn("groupButton");

		// go to 'add devices to group' dialog
		verifyIsShown("siteMap");
		verifyIsShown("modalTitle");
		verifyIsShown("closeBtn");
		verifyIsShown("targetType1");
		verifyIsShown("controlLabel1");
		verifyIsShown("groupName1");
		verifyIsShown("inputName");

		verifyIsShown("targetType2");
		verifyIsShown("controlLabel2");
		verifyIsShown("groupName2");
		verifyIsShown("existedName");
		verifyIsShown("cancelBtn");
		verifyIsShown("submitBtn");

		//
		int groupNameLength = groupName.length();
		if(groupNameLength > 30){
			log("Please enter a group name which is less than 30 characters", 3);
		}
		setValue("inputName", groupName);

		if(isEnabled("submitBtn")){
			clickOn("submitBtn");
		}

		waitByTimeOut(3000);
		return deviceNames;
	}

	public void verifyAddGroup(String groupName, List<String> names){

		List<WebElement> groups = getElements("allGroup");

		boolean falg = false;
		int i = 0;
		for (WebElement webElement : groups) {
			String name = webElement.getText();
			if(name.contains(groupName)){
				falg = true;
				break;
			}else{
				i++;
			}
		}

		assertTrue(falg, "create group [" + groupName + "] is exist.");

		List<WebElement> allDeviceName =  driver.findElements(By.cssSelector(".group-device")).get(i).findElements(By.cssSelector(".device-name"));
			//	driver.findElements("");

		for (int j = 0; j < allDeviceName.size(); j++) {

			String deviceName = allDeviceName.get(j).getAttribute("title");
			boolean falg2 = false;

			for (String name : names) {
				log("after : " + deviceName+ " ?=  before : " + name);
				if(name.replaceAll(" ", "").equals(deviceName.replaceAll(" ", ""))){
					falg2 = true;
					break;
				}
			}
			assertTrue(falg2, "add device name [ " + deviceName+ "] to  group [" + groupName + "] failed .");

		}

	}

	public void deleteDeviceGroupByName(String groupName){

		log("Delete group name " + groupName);
		verifyIsShown("groups");
		clickOn("groups");

		verifyIsShown("addNewGroupButton");
		verifyIsShown("removeButton");
		verifyIsShown("allGroupName",1);
		verifyIsShown("allGroupCheckBox",1);

		List<WebElement> gourpNames = getElements("allGroupName");
		List<WebElement> chcekBox = getElements("allGroupCheckBox");
		for (int i = 0; i < gourpNames.size(); i++) {

			String name = gourpNames.get(i).getText().trim();
			if(name.equals(groupName)){
				chcekBox.get(i).click();
				break;
			}
		}

		clickOn("removeButton");
		verifyIsShown("confirmRemoveDeviceGourp");
		clickOn("confirmRemoveDeviceGourp");
		waitByTimeOut(2000);
		verifyIsShown("addNewGroupButton");
		verifyIsShown("removeButton");
		gourpNames = getElements("allGroupName");

		boolean falg = false;
		for (int i = 0; i < gourpNames.size(); i++) {

			String name = gourpNames.get(i).getText().trim();
			if(name.equals(groupName)){
				falg = true;

			}
		}

		assertTrue(!falg, "remove device group name --  " + groupName + " failed." );
		log("Delete group name " + groupName + " success.");
	}

	/**
	 * 根据
	 */
	public void cofigurationAutomationTest(String fileName){
		this.appsActions(fileName, "Automation");
	}

	public void cofigurationEdtiApp(String fileName){
		this.appsActions(fileName, "Edit");
	}

	public void cofigurationRemoveApp(String fileName){
		this.appsActions(fileName, "Remove");
	}

	/**
	 * 根据File name 匹配行数作为索引, 操作action
	 * @param fileName
	 * @param action
	 */
	public void appsActions(String fileName, String action){

		String elementName = "";
		if(action.equals("Automation")){
			elementName = "automationTest";
		}else if(action.equals("Edit")){
			elementName = "editApp";
		}else if(action.equals("Remove")){
			elementName = "removeApp";
		}

		List<WebElement> fileNames = getElements("allFileName");
		int index = -1;
		for (int i = 0; i < fileNames.size(); i++) {
			String name = fileNames.get(i).getText().trim();
			if((fileName+".apk").equals(name)){
				log("Get File Name for DP : " + name);
				index = i;
				break;
			}
		}

		try {
			getElements(elementName).get(index).click();
		} catch (WebDriverException e) {
			waitByTimeOut(3000);
			getElements(elementName).get(index).click();
		}

	}

	private void selectedDevices(String sys){


	}

	public void verifyIsShownTeamsElement(){

		verifyIsShown("teamsInCompanyName");//验证点击total teams的图标进入到teams的信息
	    verifyIsShown("addNewTeamBtn");
		verifyIsShown("removeTeamBtn");
	    verifyIsShown("teamList");

	}

	public void verifyIsShownUsersElement(){

		verifyIsShown("usersInCompanyName");
		verifyIsShown("usersInCompanyTitle");
		verifyIsShown("selectCompanyDropdownBtn1");
		verifyIsShown("addNewUserBtn");
		verifyIsShown("assignUserBtn");
		verifyIsShown("moreBtnName");
		verifyIsShown("moreDropdownBtn");
		verifyIsShown("allStatusBtnName");
		verifyIsShown("allStatusDropdownBtn");
		verifyIsShown("allAuthTypesBtnName");
		verifyIsShown("allAuthTypesDropdownBtn");
		verifyIsShown("allRolesBtnName");
		verifyIsShown("allRolesDropdownBtn");
		verifyIsShown("allTeamsBtnName");
		verifyIsShown("allTeamsDropdownBtn");
		verifyIsShown("searchUsers");


	}
	public void verifyManagementElement(){

		//验证Mangement模块
		verifyIsShown("management");
		verifyIsShown("companies");
		verifyIsShown("teams");
		verifyIsShown("users");
		verifyIsShown("configuration");
		verifyIsShown("rate");
	}

//	private void setAllCheckboxEmpty(){
//		verifyIsShown("allExtraCheckbox",1);
//		List<WebElement> allCheckbox = getElements("allExtraCheckbox");
//		int beginTime = 0;
//		for(int i = 0; i < allCheckbox.size();i ++){
//			String classVal = getElementAttribute("allExtraCheckbox","class");
//			if(classVal.contains("ng-not-empty")){
//				clickOn("allExtraCheckbox",i);
//				beginTime = i;
//				break;
//			}
//
//		}
//
//	}

    /*
    指定一个app的名字，点击此app的自动化测试按钮
    @appName
        指定的App Name 名字
    @platform
        0 代表安卓
        1 代表ios
     */
    public void start_automationTest_from_apps(String appName,String platform){
        String platform_new ="";
        List<WebElement> list = driver.findElements(By.tagName("tr"));
        if(platform =="0"){
            platform_new = "Android";
        }
        else if (platform == "1"){
            platform_new = "iOS";
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
            if (list.get(i).getText().contains(appName) && list.get(i).getText().contains(platform_new)) {
                WebElement aa = list.get(i);
                List<WebElement> bb = aa.findElements(By.tagName("td"));
                List<WebElement> cc = bb.get(9).findElements(By.tagName("span"));
                cc.get(0).click();
                break;
            }
        }
    }

    public void uploadScript(String scriptName){
        String currentpath_temp = System.getProperty("user.dir");
        String currentpath = currentpath_temp.replaceAll("\\\\", "/");
        String scriptPath = (currentpath + "/src/test/resources/data/App/android_apk/" + scriptName);
        sendKeys("UploadFile", scriptPath);
    }

    public void select_scripts(String scriptName){
        List<WebElement> list = driver.findElements(By.xpath("//tr[@ng-repeat='row in appScripts']"));
        if(list==null || list.size()==0){
            throw new Error(" there are no available scripts !!!!!");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
            if (list.get(i).getText().contains(scriptName)) {
                WebElement aa = list.get(i);
                List<WebElement> bb = aa.findElements(By.tagName("td"));
                bb.get(0).click();
                break;
            }
        }
    }

    /*
    从apps的应用列表里选择自己想要的app
     */
    public  void selectAppFromApps(String appName){
        List<WebElement> wel = getElements("Apps_AppDiv");
        try {
            for(WebElement el:wel){
                System.out.println(el.getText());
                if(el.getText().contains(appName)){
                    el.click();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    运行自动化测试的select device界面，device列表里面，选择状态可用的设备
    @deviceNum
        想要选择的可用的设备数量
     */
    public void automationSelectDevice_selectOneAvailableDevice(int deviceNum){
        clickOn("Apps_StartAutomation_SelectDevice_Devices_Title");
        waitByTimeOut(2000);
        List<WebElement> devicelist = new ArrayList<WebElement>();
        List<WebElement> list = getElements("Apps_StartAutomation_SelectDevice_Available_Device");
        for(WebElement i:list){
            System.out.println(i.getText());
            if(i.getAttribute("ng-if").contains("Available")){
                devicelist.add(i);
            }
        }
        for (int i = 0; i < deviceNum; i++) {
            devicelist.get(i).click();
            waitByTimeOut(500);
        }
    }

    /*
    meng
     */
    public void uploadApp(String appname) {
        String currentpath_temp = System.getProperty("user.dir");
        String currentpath = currentpath_temp.replaceAll("\\\\", "/");
        // System.out.println("currentpath is......"+currentpath);
        String apppath = (currentpath + "/src/test/resources/data/App/android_apk/" + appname);
        // System.out.println("apppath is......"+apppath);
        try {
            sendKeys("UploadFile", apppath);
        } catch (Exception e) {
            try {
                sendKeys("currentUse_scriptSelectFile", apppath);
            } catch (Exception ee) {
                try {
                    sendKeys("scriptSelectFile", apppath);
                } catch (Exception eee) {
                    try {
                        sendKeys("upload", apppath);
                    } catch (Exception eeee) {
                    }
                }
            }
        }
    }

    /*
    meng
     */
    public void isUploadStatus() {
        verifyIsShown("progressBar");
        String percent = getValueOf("progressBar");

        // 时间判断
        Calendar now = Calendar.getInstance();
        Calendar target = Calendar.getInstance();
        target.add(Calendar.MINUTE, 10);

        while (!(percent.equals("100%"))) {
            waitByTimeOut(1000);
            // 时间判断
            if (now.after(target)) {
                break;
            }
            now = Calendar.getInstance();
            percent = getValueOf("progressBar");
            log("Current Percent : " + percent);
        }
        log("Upload App finish...");
    }

    /*
    meng
     */
    public void uploadAppScript(String sciptName) {
        //上传
//        uploadApp("/AppiumPythonScript/Demo.zip");
        uploadApp(sciptName);
        waitByTimeOut(3000);
        clickOn("scriptUpload");
        waitByTimeOut(500);
        isUploadStatus();
        waitByTimeOut(1000);
    }
    
    
    /**
     * @Author: Jay-Z
     * @Description: Under All Devices page, Select Android unit with status: available/In use
     * if find, Keep this under selected status, otherwise drop this test script
     * MeanWhile, return device name and status with List.
     * dp.getXMLDoc("devices", "alldevices");
	 * dp.setViewTo("alldevices:groupview");
     * @Para: null
     * @return: List<String>
     */
	public List<String> selectAvailableAndroidDev() {
		
		List<String> deviceinfo = new ArrayList<String>();
		String devicestatus = ""; //选取设备当前的状态
		String devicesname = "";
		
		waitForElementClickable("allDevicesStatus");
		waitForElementClickable("devicesNames");
		List<WebElement> selectdevicesstatus = getElements("allDevicesStatus"); 
		List<WebElement> selectdevicesname = getElements("devicesNames"); 
		int count = 0;
		
		
		
		for(int i = 0; i < selectdevicesname.size(); i++) {
			count++;
			devicesname = selectdevicesname.get(i).getText();
			devicestatus = selectdevicesstatus.get(i).getText();
			if(!devicesname.matches("Apple\\b.*")) {
				if(devicestatus.matches("Available") || devicestatus.matches("In Use")) {
					selectdevicesname.get(i).click();
					deviceinfo.add(selectdevicesname.get(i).getText());
					deviceinfo.add(selectdevicesstatus.get(i).getText());
					log("Device: "+devicesname + "(Status: " + devicestatus + ") is preparing test!");
					break;
				}
			}
			if(selectdevicesname.size() == count && !devicestatus.matches("Available") && !devicestatus.matches("In Use")) {
				log("No available Android unit, try later!", 3);
				driver.close();
			}
		}
		return deviceinfo;

	}
	
    /**
     * @Author: Jay-Z
     * @Description: An expectation for checking an element if it is visible and enable such you can
     * click it
     * @Para: elementName: Define the element name in xml file.
     * 		  inSec: wait for driver locating at element during expected gap, default 20sec.
     * @Return: If locator element, return true, otherwise false.
     * 			
     */			
	public DPWebApp waitForElementClickable(String elementName) {
		return waitForElementClickable(elementName, 20);
	}
	
	public DPWebApp waitForElementClickable(String elementName, int inSec) {
		String locator = "";
		try {
			locator = getLocatorStr(getElementLocator(elementName));
			new WebDriverWait(driver, inSec).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		} catch(TimeoutException e) {
			log("Can not locate element on Webpage " + "elementName = " + elementName +" | locator = '" + locator + "'", 3);
		
		}
		return this;
	}

    /**
     * @Author: Jay-Z
     * @Description: Encapsulate method clickOn(), judge element if it is visible and enable.
     * @Para: elementName: Define the element name in xml file.
     * @Return: this
     * 			
     */	
	public DPWebApp clickWhat(String elementName) {
		waitForElementClickable(elementName);
		clickOn(elementName);
		waitByTimeOut(400);
		return this;
	}
  
	/**
     * @Author: Jay-Z
     * @Description: Under CurrentUse -> Screenshot page, [Screenshot Page] Get current screenshot number
     *  			dp.getXMLDoc("devices", "currentUse");
     * 				dp.setViewTo("currentUse:screenshotL");
     * @Para: elementName: Define the element name in xml file.
     * @Return: If no picture in screenshot area, return 0, otherwise return screenshot number.
     */
	public int getCurrentScreenshotNum() {
		int currentNum = 0;
		String locator = getLocatorStr(getElementLocator("SelectAllBtn2"));
		waitByTimeOut(3000);
		String  checkboxstatus = driver.findElement(By.xpath(locator)).getAttribute("disabled");
		if(null == checkboxstatus) {
			waitForElementReadyOnTimeOut("DisplayedSCAndLoadMore",20);
			currentNum = getElements("DisplayedSCAndLoadMore").size();
			if(currentNum > 5) {
				driver.navigate().refresh();
				waitForElementReadyOnTimeOut("ScreenshotShowingNumItem",20);
				String[] str = getElementText("ScreenshotShowingNumItem").split("/");
				currentNum = Integer.parseInt(str[1]);
				}
		}
		return currentNum;
	}

	/**
     * @Author: Jay-Z
     * @Description: Under CurrentUse -> Apps page,
     * 				 1.download the app you specify at. 
     * 				 2.please point out the path of download file, otherwise using browser default.
     * 				dp.getXMLDoc("devices", "currentUse");
     * 				dp.setViewTo("currentUse:screenshotL");
     * @Para: downloadBtn: Define the element name in xml file, which app you'd like to download.
     * 		  downloadappname: The name of app in xml
     * 		  downloadpath: your browser store file path.
     * 		  inSec:In this time section, the download file you specify at should be totally completed. eg: In 100 seconds, the file is still 
     * 		  not ready in target path, return false.
     * @Return: If no picture in screenshot area, return 0, otherwise return screenshot number.
     */ 
	public boolean downloadApp(String downloadBtn, String downloadappname, int inSec) {
		String browserfiledownloadpath = "C:/Users/" + System.getProperty("user.name") + "/Downloads";
		
		return downloadApp(downloadBtn,downloadappname, browserfiledownloadpath, inSec);
	}
	
	public boolean downloadApp(String downloadBtn, String downloadappname, String downloadpath, int inSec) {
		int temptime = inSec*100;
		int tempcount = 0;
		boolean flag = false;
		String[] filenames = null;
		
		File file = new File(downloadpath);
		File[] tempfilename = file.listFiles();
		
		String appnamedp = waitForElementClickable(downloadappname).getElementText(downloadappname);
		
		//Check if appnamedp already exists in downloadpath,if so, remove target.
		for(int i=0; i < tempfilename.length; i++) {
			if(tempfilename[i].getName().equals(appnamedp)) {
				tempfilename[i].delete();
			}
		}
		
		clickWhat(downloadBtn);
		
		while(temptime <= inSec*1000) {
			
			waitByTimeOut(inSec*100);
			
			filenames = file.list();

			for(int i=0; i < filenames.length; i++) {
				if(filenames[i].equals(appnamedp)) {
					return flag = true;
				} else {
					tempcount++;
				}
				if(inSec*1000 < temptime && filenames.length == tempcount) {
					log("Download App:" + appnamedp + "failed", 3);
					return flag;
				}
			}
			
			temptime = temptime+ inSec*100;
			tempcount = 0;
			filenames = null;
		}
		
		return flag;
	}
	
	/**
	 * @Author:Jay-Z
	 * @Description: Under CurrentUse -> Device Information panel, get Device Information
	 * 				dp.getXMLDoc("devices", "currentUse");
	 *				dp.setViewTo("currentUse:screenshotL");
	 * @Para: infotitleLoc: there are 3 opts for choosing including 'Model Info', 'Serial Info' and 'Provider'
	 * 		  infoitemLoc: Switch to infotitle you specify at, choose infoitem you'd like to. 
	 * @Return: Return above item
	 */
	public String getDeviceInfomation(String infotitleLoc, String infoitemLoc) {
		return clickWhat(infotitleLoc).getElementText(infoitemLoc);
	}
	
	/**
	 * @Author:Jay-Z
	 * @Description: Under Utilization function, get Device operation records
	 *				dp.getXMLDoc("devices", "utilization");
	 *				dp.setViewTo("utilization:deviceUtilizationview");
	 * @Para: infotitleLoc: there are 3 opts for choosing including 'Model Info', 'Serial Info' and 'Provider'
	 * 		  infoitemLoc: Switch to infotitle you specify at, choose infoitem you'd like to. 
	 * @Return: Return above item Object
	 */
	public List<String> getDeviceOperationRecords(String devicename, String deviceserial) {
		
		String devicebrand = "";
		String serial = "";
		int count =0;
		
		//Get all device brands object and all all module num of brand object
		List<WebElement> alldevicebrands = waitForElementClickable("uAllBrands").getElements("uAllBrands");
		List<WebElement> allmodulenum = waitForElementClickable("uABCounts").getElements("uABCounts");
		
		
		//Get brand under utilization, if not exist, throw exception
		for(int i=0; i<alldevicebrands.size(); i++) {
			moveMouseOn("uAllBrands");
			devicebrand = alldevicebrands.get(i).getText();
			if(devicebrand.equals(devicename)) {
				allmodulenum.get(i).click();
				break;
			}
			if(alldevicebrands.size() == count && !devicebrand.equals(devicename)) {
				log("Can not find the brand '" + devicebrand + "' under 'Device Brand Chart' of Utilization", 3);
			}
			count++;
		}
		
		//Get all serials and actions object of device brand
		List<WebElement> serials = waitForElementClickable("uBAMSerials").getElements("uBAMSerials");
		List<WebElement> actions = waitForElementClickable("uBAMActions").getElements("uBAMActions");
 		count = 0;
		for(int i=0; i<serials.size(); i++) {
			serial = serials.get(i).getText();
			if(serial.equals(deviceserial)) {
				actions.get(i).click();
				break;
			}
			if(serials.size() == count && !serial.equals(deviceserial)) {
				if(!serial.equals(deviceserial)) {
					log("Can not find the serial '" + serial + "' of brand '" + devicebrand + "' under 'Device Brand Chart' of Utilization", 3);
				} else {
					log("Can not find the action of serial '" + serial + "' of brand '" + devicebrand + "' under 'Device Brand Chart' of Utilization", 3);
				}
			}
			count++;
		}
		
		
		
		List<WebElement> recordtypes = waitForElementClickable("uBMRecordsType").getElements("uBMRecordsType");	//Get all records 'Type' object of utilization 
		
		List<WebElement> recordcompanys = waitForElementClickable("uBMRecordsCompany").getElements("uBMRecordsCompany");	//Get all records 'Company' object of utilization 
		
		List<WebElement> recordteams = waitForElementClickable("uBMRecordsTeam").getElements("uBMRecordsTeam");	//Get all records 'Team' object of utilization 
		
		List<WebElement> recordusers = waitForElementClickable("uBMRecordsUser").getElements("uBMRecordsUser");	//Get all records 'User' object of utilization 
		
		//put the underground three of the List (recordtypes/recordcompanys/recordteams/recordusers) in deviceRecords
		List<String> deviceRecords = new ArrayList<String>();
		for(int i=0; i<4; i++) {
			deviceRecords.add(recordtypes.get(i).getText());
			deviceRecords.add(recordcompanys.get(i).getText());
			deviceRecords.add(recordteams.get(i).getText());
			deviceRecords.add(recordusers.get(i).getText());
		}
		
		return deviceRecords;
	}

	/**
	 * @Author:Jay-Z
	 * @Description:Through Email, get user other info.
	 * 						this.getXMLDoc("management", "users");
	 *						this.setViewTo("users:usersPage");
	 * @Para: Email you'd like to seek for
	 * @return: Map<String,String> 
	 * 			Key,Value
	 * 			First String:  Email, Company, Team...
	 * 			Second String: value, value,   value
	 * 
	 */
	public Map<String,String> getUserInfo(String emailaddress) {
		
		
		waitByTimeOut(1000);
		String current_url = driver.getCurrentUrl().replace("/devices", "users?companyId=0");
		driver.get(current_url);
		waitForElementClickable("searchCheckbox").sendKeys("searchCheckbox", emailaddress);
		
		Map<String,String> usermessage = new HashMap<String,String>();
		waitByTimeOut(2000);
		if(!(getElements("seekuseremaillist").size() == 1)) {
			waitByTimeOut(6000);
		}
		
		String user_name = waitForElementClickable("seekusername").getElementText("seekusername");
		String user_email = waitForElementClickable("seekuseremail").getElementText("seekuseremail");
		String user_company = waitForElementClickable("seekusercompany").getElementText("seekusercompany");
		String user_team = "";
		if(waitForElementClickable("seekuserteam1").getElementText("seekuserteam1").equals("")) {
			user_team = waitForElementClickable("seekuserteam2").getElementText("seekuserteam2");
		} else {
			user_team = waitForElementClickable("seekuserteam1").getElementText("seekuserteam1");
		}
		
		String user_phone = waitForElementClickable("seekuserphone").getElementText("seekuserphone");
		String user_createdate = waitForElementClickable("seekuserdate").getElementText("seekuserdate");
		String user_authtype = waitForElementClickable("seekuserauthtype").getElementText("seekuserauthtype");
		String user_status = waitForElementClickable("seekuserstatus").getElementText("seekuserstatus");
		String user_balance = waitForElementClickable("seekuserbalance").getElementText("seekuserbalance");
		String user_remaininghours = waitForElementClickable("seekuserremaininghours").getElementText("seekuserremaininghours");	
		
		usermessage.put("Name", user_name);						usermessage.put("Email", user_email);	
		usermessage.put("Company", user_company);				usermessage.put("Team", user_team);
		usermessage.put("Phone", user_phone);					usermessage.put("Create Date", user_createdate);
		usermessage.put("Auth Type", user_authtype);			usermessage.put("Status", user_status);
		usermessage.put("Balance(DP Coins)", user_balance);		usermessage.put("Remaining Hours", user_remaininghours);

		return usermessage;
	}
	
	/**
	 * @Auth:Jay-Z
	 * @Description:Get current login user Email address.
	 * 						this.getXMLDoc("management", "users");
	 *						this.setViewTo("users:users"); 				
	 * @return: String
	 */
	public String getCurrentUserEmailAddre() {
		
		waitByTimeOut(2000);
		String current_url = driver.getCurrentUrl();
		String urlaccount = current_url.replace("/devices", "/account");
		driver.get(urlaccount);
		String emailaddress = waitForElementClickable("currentuserEmailaddress").getElementText("currentuserEmailaddress");
		driver.get(current_url);
		waitByTimeOut(2000);
		return emailaddress;
	}

	/**
	 * @Author Jay-Z
	 * @Description Click logout button to achieve login again with password and user name you'd like to specify at.
	 * @Para user name, password
	 * @return this
	 * 
	 */
	public DPWebApp longin_NewClient(String new_username, String New_nassword) {
		
		clickWhat("userName").clickWhat("Logout");
		waitForElementClickable("email").sendKeys("email", new_username);
		waitForElementClickable("password").sendKeys("password", New_nassword);
		clickWhat("loginButton");
		
		return this;
	}

	/**
	 * @author Jay-Z
	 * @Description Get different random number, quantity of random by your decision
	 * @param int count, quantity of random
	 * @Return random number
	 */
	public String getRandomNum(int count) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		String str = "0123456789";
		for(int i=0; i<count; i++) {
			int num = random.nextInt(str.length());
			sb.append(str.charAt(num));
			str = str.replace(str.charAt(num)+"", "");
		}
		return sb.toString();
	}

}
