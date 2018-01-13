package com.by.automate.testCases.Interface_new;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.TreeMap;

public class test_interface_scriptId implements interface_url{
    private HttpRequerter httpRequerter=null;

    @BeforeClass
    public void setUp() {
        httpRequerter=new HttpRequerter();
    }
    @Test
    public void Test010(){


        ApiRequest request = new ApiRequest();
        request.setUrl(api_scriptsID + script_id_trainticket);
        request.setApiKey(apiKey);
        request.setSecretKey(access_key);
        request.addSecretParam("id",script_id_trainticket);
        String s=httpRequerter.get(request);

        assert s.contains("\"total\":1"):"响应值有误！！";

        System.out.println("\n============================");
        try{
            JSONObject jsonObject = new JSONObject().fromObject(s);
            String ret_code= jsonObject.getString("ret_code");
            String message= jsonObject.getString("message");

            //验证主要返回值
            Assert.assertEquals(ret_code,"3000");
            Assert.assertEquals(message,"ok");

            //获取scripts的数据
            JSONArray scripts = jsonObject.getJSONArray("scripts");
            System.out.println("json里的scripts参数对应数组的值 ："+scripts);
            int i =0;
                while (i <scripts.size()){
                    JSONObject jo = scripts.getJSONObject(i);
                    Assert.assertEquals(jo.getString("test_type"),"Appium + Python");
                    Assert.assertEquals(jo.getString("file_type"),"zip");
                    Assert.assertEquals(jo.getString("remark"),"");
                    Assert.assertEquals(jo.getString("modified_time"),"2017-07-06T06:59:03+00:00");
                    i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }





}
