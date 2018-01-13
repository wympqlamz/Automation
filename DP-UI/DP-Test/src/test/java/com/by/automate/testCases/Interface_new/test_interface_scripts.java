package com.by.automate.testCases.Interface_new;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.TreeMap;

public class test_interface_scripts implements interface_url{
//    private commonTools co;
//    private sha256 sa;
//    private TreeMap map;
//    private  long time_stamp;
//    private String url_scripts;
//    private String params;

    private HttpRequerter httpRequerter=null;

    @BeforeClass
    public void setUp() {

        httpRequerter=new HttpRequerter();

//        co = new commonTools();
//        sa = new sha256();
//        time_stamp = co.getMillisecond();
//        map = new TreeMap();
//        map.put("api_key",apiKey);
//        map.put("signature_version",signature_version);
//        map.put("api_version",api_version);//
//        url_scripts = api_scripts; //接口----所有脚本
//        params ="api_key="+apiKey+"&signature_version=1&api_version=1.0&signature="+sa.getSignature(map,access_key);
    }

    @Test
    public void Test010(){

//        httprequest re = new httprequest();
//
//        String s= re.sendGet( url_scripts,params);
//        System.out.println("服务器的响应值是 ："+s);

        ApiRequest request = new ApiRequest();
        request.setUrl(api_scripts);
        request.setApiKey(apiKey);
        request.setSecretKey(access_key);
        String s=httpRequerter.get(request);
        assert s.contains("\"message\":\"ok\""):"响应值有误！！";


        System.out.println("\n============================");
        try{
            JSONObject jsonObject = new JSONObject().fromObject(s); //生成一个json对象

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
                if(jo.getString("file_name").equals("demo.zip")){
                    Assert.assertEquals(jo.getString("test_type"),"Appium + Python");
                    Assert.assertEquals(jo.getString("file_type"),"zip");
                    Assert.assertEquals(jo.getString("remark"),"");
                    Assert.assertEquals(jo.getString("modified_time"),"2017-08-30T07:08:13+00:00");
                }
                else if(jo.getString("file_name").equals("trainticket.ipa")){
                    Assert.assertEquals(jo.getString("test_type"),"Appium + Python");
                    Assert.assertEquals(jo.getString("file_type"),"zip");
                    Assert.assertEquals(jo.getString("remark"),"");
                    Assert.assertEquals(jo.getString("modified_time"),"2017-07-06T06:59:03+00:00");
                }
                i++;
            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
