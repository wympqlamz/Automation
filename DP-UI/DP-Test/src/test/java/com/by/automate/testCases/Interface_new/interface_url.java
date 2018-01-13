package com.by.automate.testCases.Interface_new;

public interface interface_url {
    /*
    url路径
     */
    String api_apps = "https://qa.devicepass.com/user_api/api_v1/apps";
    String api_scripts  = "https://qa.devicepass.com/user_api/api_v1/scripts";
    String api_script  = "https://qa.devicepass.com/user_api/api_v1/script";//末尾还需要加进去id的变量
    String api_appId  = "https://qa.devicepass.com/user_api/api_v1/app/"; //末尾还需要加进去id的变量
    String api_balance  = "https://qa.devicepass.com/user_api/api_v1/balance";
    String api_scriptsID  = "https://qa.devicepass.com/user_api/api_v1/script/";//末尾还需要加进去id的变量
    String api_testStatus = "https://qa.devicepass.com/user_api/api_v1/testStatus";
    String api_testStatusID = "https://qa.devicepass.com/user_api/api_v1/testStatus/";
    String api_testReportID = "https://qa.devicepass.com/user_api/api_v1/testReport/"; //访问此接口，会将test_id对应的报告下载
    String api_devices = "https://qa.devicepass.com/api_v1/devices";
    String api_deviceID = "https://qa.devicepass.com/user_api/api_v1/device/";
    String api_app = "https://qa.devicepass.com/user_api/api_v1/app";
    String api_groups = "https://qa.devicepass.com/user_api/api_v1/deviceGroups";
    String api_launchAutomation = "https://qa.devicepass.com/user_api/api_v1/automationTest/";
    String api_deleteAutomation = "https://qa.devicepass.com/user_api/api_v1/automationTest/";

//  ----------------------------------------------------------------------------------------------------------------------------------

    /*
    公共参数
     */
    String signature_version = "1";
    String api_version = "1.0";

    //账号 maoyujia02@beyondsoft.com
    String apiKey = "4y31d2dah5vljzxx1liz7qgzrwwn0cf0";
    String access_key = "hz1noa1v6w2tcy9kb5a61bo0ns3mlp2ngj7seq7qlow6zqpobuorbof8ubmaonin";
    //账号 maoyujia@beyondsoft.com
    String access_key01 = "v8dzjqxzi2jncbugoj0l68ax4iruu149sd0a0dafplcrwlug1oduyad8gwvhr2wk";
    String apiKey01 = "1qcakbt2mrx4ncot25o30p90oakq9np8";


//  ----------------------------------------------------------------------------------------------------------------------------------


    //指定的appid &、scriptid 、testStatus id  参数
    String app_id_ep2p = "3d3909ee7095402d81de1304b7a549d6";
    String script_id_trainticket = "cf64884bbd1749b184db98e760990eb2";
    String app_id_trainticket = "5822b0ccd7084b59b96209f61852bc1d";
    String testStatus_id_trainticket = "1853b401bd484e7c97af7276687d291f"; //test report里火车票 的 test id



    //devices参数
    String limit_d1 = "30";
    String offset_d1 = "0";
    String device_id_1 = "02119419ffd7b768";

    //automationTest 参数
    String testType_Compatibility = "Compatibility";
    String testType_appiumPython= "appiumPython";
    String app_id = "3d3909ee7095402d81de1304b7a549d6"; // maoyujia02账号上的e生财富
    String test_name = "接口发起的自动化测试";
    Integer max_time = 10;
    Integer skip_app_uninsatll = 1;
    Integer clear_data = 0;
    String[] serials = {"192c5d5c","47Q6R16A25001543","B2T7N16822007948"};//VIVO-Xplay6、HUAWEI M2-A01W 、HUAWEI EVA-TL00 三台
    String isChromeWebTesting = "";





    //兼容性测试参数
    Integer cpuDumpInterval = 5;
    Integer memDumpInterval = 5;
    Integer screenshotInterval = 5;
    Integer screenshotCount = 10;
    Integer monkeyTestRepeatInterval = 100;
    Integer monkeyTestRepeatCount = 500;
    Integer monkeyTestPctTouch = 30;
    Integer monkeyTestPctMotion = 30;
    Integer monkeyTestPctAppswitch = 20;
    Integer monkeyTestPctSyskeys = 10;
    Integer maxUiNavDepth = 3;
    Integer maxUiNavElement = 3;
    Integer maxUiNavDuration = 120;



}
