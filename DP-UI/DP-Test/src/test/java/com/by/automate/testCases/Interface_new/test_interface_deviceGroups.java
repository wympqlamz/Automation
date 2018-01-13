package com.by.automate.testCases.Interface_new;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class test_interface_deviceGroups implements interface_url {
    private HttpRequerter httpRequerter=null;

    @BeforeClass
    public void setUp() {

        httpRequerter=new HttpRequerter();
    }

    /*
    获取groups的数据
     */
    @Test
    public void Test010(){

        try{

            ApiRequest request = new ApiRequest();
            request.setUrl(api_groups);
            request.setApiKey(apiKey);
            request.setSecretKey(access_key);
            String s=httpRequerter.get(request);

            JSONObject jsonObject = new JSONObject().fromObject(s);

            String ret_code= jsonObject.getString("ret_code");
            String message= jsonObject.getString("message");
            String total= jsonObject.getString("total");
            //验证主要返回值
            Assert.assertEquals(ret_code,"3000");
            Assert.assertEquals(message,"ok");
            Assert.assertEquals(total,"3");
            //获取参数apps里的json数据
            JSONArray groups= jsonObject.getJSONArray("groups");
            int i =0;
            while (i <groups.size()){
                JSONObject jo = groups.getJSONObject(i);
                if(jo.getString("tagName").equals("mao")){
                    Assert.assertEquals(jo.getString("createDate"),"2017-05-10T06:00:31+00:00");
                }
                else if(jo.getString("tagName").equals("r")){
                    Assert.assertEquals(jo.getString("createDate"),"2017-05-10T01:44:07+00:00");
                }
                i++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
