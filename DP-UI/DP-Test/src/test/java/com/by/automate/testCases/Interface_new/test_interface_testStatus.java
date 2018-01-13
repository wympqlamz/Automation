package com.by.automate.testCases.Interface_new;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.TreeMap;

public class test_interface_testStatus implements interface_url{
//    private commonTools co;
//    private sha256 sa;
//    private TreeMap map;
//    private  long time_stamp;
//    private String url_testStatus;
//    private String params;

    private HttpRequerter httpRequerter=null;

    @BeforeClass
    public void setUp() {


        httpRequerter=new HttpRequerter();

    }

    /*
    账号用maoyujia02@beyondsoft.com
    返回测试报告的数量、内容；当前固定4个报告内容
     */


    @Test
    public void Test010_testStatus() {
        ApiRequest request = new ApiRequest();
        request.setUrl(api_testStatus);
        request.setApiKey(apiKey);
        request.setSecretKey(access_key);
        String s=httpRequerter.get(request);

//        httprequest re = new httprequest();
//        String s = re.sendGet(url_testStatus, params);
        System.out.println("服务器的响应值是 ：" + s);
        assert s.contains("\"message\":\"ok\""):"响应值有误！！";
        System.out.println("\n============================");
        try{
            JSONObject jsonObject = new JSONObject().fromObject(s); //生成一个json对象
            String ret_code= jsonObject.getString("ret_code");
            String message= jsonObject.getString("message");

            //验证主要返回值
            Assert.assertEquals(ret_code,"3000");
            Assert.assertEquals(message,"ok");

            //获取summaries
            JSONArray summaries = jsonObject.getJSONArray("summaries");
            System.out.println("json里的summaries参数对应数组的值 ："+summaries);
            for(int i=0;i<summaries.size();i++){
                JSONObject jo = summaries.getJSONObject(i);
                if(jo.getString("test_name").equals("Test20170706145839")){
                    Assert.assertEquals(jo.getString("test_status"),"completed");
                    Assert.assertEquals(jo.getString("app_name"),"火车票");
                    Assert.assertEquals(jo.getString("app_version"),"1");
                    Assert.assertEquals(jo.getString("os_type"),"ios");
                    Assert.assertEquals(jo.getString("test_type"),"Appium + Python");
                    JSONArray results = jo.getJSONArray("results");
                    System.out.println("results : "+ results);
                    for (int j = 0; j < results.size(); j++) {
                        JSONObject jo2 = results.getJSONObject(j);
                        if(jo2.getString("device").equals("959b3e10d395a1be77e02a89ced52ea955164020")){
                            Assert.assertEquals(jo2.getString("test_status"), "-900");
                            Assert.assertEquals(jo2.getString("status_desc"), "aborted");
                        }
                        else if(jo2.getString("device").equals("1e7974e2d287b1e75b178867eefc848677d521d7")){
                            Assert.assertEquals(jo2.getString("test_status"), "1");
                            Assert.assertEquals(jo2.getString("status_desc"), "successful");//这块目前有bug
                        }
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
