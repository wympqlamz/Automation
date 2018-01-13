package com.by.automate.testCases.Interface_new;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.TreeMap;

public class test_interface_apps implements interface_url{

    private HttpRequerter httpRequerter=null;

    @BeforeClass
    public void setUp() {

        httpRequerter=new HttpRequerter();
    }

    /*
    只提供公共参数，获取所有app的数据
     */
    @Test
    public void Test010(){

        try{

            ApiRequest request = new ApiRequest();
            request.setUrl(api_apps);
            request.setApiKey(apiKey);
            request.setSecretKey(access_key);
            String s=httpRequerter.get(request);

            JSONObject jsonObject = new JSONObject().fromObject(s); //生成一个json对象

            String ret_code= jsonObject.getString("ret_code");
            String message= jsonObject.getString("message");
            String total= jsonObject.getString("total");
            //验证主要返回值
            Assert.assertEquals(ret_code,"3000");
            Assert.assertEquals(message,"ok");
            Assert.assertEquals(total,"3");
            //获取参数apps里的json数据
            JSONArray apps= jsonObject.getJSONArray("apps");
            System.out.println("json里的apps参数对应数组的值 ："+apps);
            int i =0;
            while (i <apps.size()){
                JSONObject jo = apps.getJSONObject(i);
                if(jo.getString("file_name").equals("ep2p.apk")){
                    Assert.assertEquals(jo.getString("app_label"),"e生财富");
                }
                else if(jo.getString("file_name").equals("trainticket.ipa")){
                    Assert.assertEquals(jo.getString("app_label"),"火车票");
                }
                i++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
