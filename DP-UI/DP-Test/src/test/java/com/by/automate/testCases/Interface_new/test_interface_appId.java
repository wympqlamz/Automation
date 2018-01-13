package com.by.automate.testCases.Interface_new;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.TreeMap;

public class test_interface_appId implements interface_url {
    private HttpRequerter httpRequerter=null;


    @BeforeClass
    public void setUp() {
        httpRequerter=new HttpRequerter();
    }

    /*
    只获取单个app数据---E生财富app。该app的脚本文件有一个---demo.zip。
     */
    @Test
    public void Test010(){

//        System.out.println("============================");
        try{
            ApiRequest request = new ApiRequest();
            request.setUrl(api_appId + app_id_ep2p);
            request.addSecretParam("id",app_id_ep2p);
            request.setApiKey(apiKey);
            request.setSecretKey(access_key);
            String s=httpRequerter.get(request);
            JSONObject jsonObject = new JSONObject().fromObject(s); //生成一个json对象
            String ret_code= jsonObject.getString("ret_code");
            String message= jsonObject.getString("message");
            //验证主要返回值
            Assert.assertEquals(ret_code,"3000");
            Assert.assertEquals(message,"ok");
            //获取参数apps里的json数据
            JSONArray apps= jsonObject.getJSONArray("apps");
            System.out.println(apps);
            //验证apps里 e生财富的数据和对应的script数据
            for (int i = 0; i < apps.size(); i++) {
                JSONObject jo = apps.getJSONObject(i);
//                Assert.assertEquals(jo.getString("file_id"),"cb5e7e85651d449db2b283bf0fb37dda");
                Assert.assertEquals(jo.getString("file_name"),"ep2p.apk");
                Assert.assertEquals(jo.getString("app_version"),"1.0");
                Assert.assertEquals(jo.getString("app_label"),"e生财富");
                Assert.assertEquals(jo.getString("script_count"),"1");
                Assert.assertEquals(jo.getString("modified_time"),"2017-05-10T06:03:54");
                Assert.assertEquals(jo.getString("app_type"),"apk");
                JSONArray scripts = jo.getJSONArray("scripts");
                System.out.println("scripts : "+ scripts);
                for (int j = 0; j < scripts.size(); j++) {
                    JSONObject jo2 = scripts.getJSONObject(j);
                    Assert.assertEquals(jo2.getString("file_id"), "8e34d91937e4441c9ff47c39a02ebe8d");
                    Assert.assertEquals(jo2.getString("file_name"), "demo.zip");
                    Assert.assertEquals(jo2.getString("test_type"), "Appium + Python");
                    Assert.assertEquals(jo2.getString("file_type"), "zip");
                    Assert.assertEquals(jo2.getString("remark"), "");
                    Assert.assertEquals(jo2.getString("modified_time"), "2017-08-30T07:08:13+00:00");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
