package com.by.automate.testCases.Interface_new;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Map;
import java.util.TreeMap;

public class test_interface_Launch_Stop_AutomationTest implements  interface_url{
    private HttpRequerter httpRequerter=null;
    private String stop_testStatusId = "";
    private CommonTools co ;

    @BeforeMethod
    public void setUp() {

        httpRequerter=new HttpRequerter();
    }

    /*
    使用账号maoyujia@beyondsoft.com
    发起兼容性测试。
     */
    @Test
    public void Test010_LaunchAutomation1(){
        co = new CommonTools();
        try{

            ApiRequest request = new ApiRequest();
            request.setUrl(api_launchAutomation + testType_Compatibility);
            request.setApiKey(apiKey);
            request.setSecretKey(access_key);

            //还需要将runAutomationTestParam传进去
            // runAutomationTestParam里的json内容 ==》  data : { device_group或者serials，options ：{ 剩余所有参数 } }
            //先创建options
            Map<String,Object > options = new TreeMap<String, Object>();
            options.put("cpuDumpInterval",cpuDumpInterval);
            options.put("memDumpInterval",memDumpInterval);
            options.put("screenshotInterval",screenshotInterval);
            options.put("screenshotCount",screenshotCount);
            options.put("monkeyTestRepeatInterval",monkeyTestRepeatInterval);
            options.put("monkeyTestRepeatCount",monkeyTestRepeatCount);
            options.put("monkeyTestPctTouch",monkeyTestPctTouch);
            options.put("monkeyTestPctMotion",monkeyTestPctMotion);
            options.put("monkeyTestPctAppswitch",monkeyTestPctAppswitch);
            options.put("monkeyTestPctSyskeys",monkeyTestPctSyskeys);
            options.put("maxUiNavDepth",maxUiNavDepth);
            options.put("maxUiNavElement",maxUiNavElement);
            options.put("maxUiNavDuration",maxUiNavDuration);
            options.put("isChromeWebTesting",isChromeWebTesting);

            //创建data,将optins等其他参数放到data里
            Map<String,Object > data_map = new TreeMap<String, Object>();
            data_map.put("options",options);
            data_map.put("serials",co.getRandomSerial());
            data_map.put("app_id",app_id);
            data_map.put("test_name",test_name);
            data_map.put("max_time",max_time);
            data_map.put("skip_app_uninsatll",skip_app_uninsatll);
            data_map.put("clear_data",clear_data);

            JSONObject data_jsonobj = new JSONObject().fromObject(data_map);
            System.out.println(data_jsonobj);

            request.addBodyParameter("data",data_jsonobj.toString());

            request.addSecretParam("id",testType_Compatibility);
            String s=httpRequerter.post(request);
            assert s.contains("\"message\":\"OK\""):"the response is error !!!";

            JSONObject json_s = new JSONObject().fromObject(s);
            //获取刚刚发起的自动化测试的test id
            stop_testStatusId = json_s.getString("test_id");
            if(stop_testStatusId.equals("null")){
                throw new Error("没有成功发起自动化测试");

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //使用Test010里的stop_testStatusId，作为参数
    @Test
    public void Test011_StopAutomation1(){


        try{
            Thread.sleep(10000);
            ApiRequest request = new ApiRequest();
            request.setUrl(api_deleteAutomation + stop_testStatusId);
            request.setApiKey(apiKey);
            request.setSecretKey(access_key);
            request.addSecretParam("id",stop_testStatusId);
            String  s = httpRequerter.delete_query(request);
            assert s.contains("\"message\":\"Canceling...\""):"the response is error !!!";

        }catch (Exception e){
            e.printStackTrace();
            throw new Error("!!!!");
        }
    }


}
