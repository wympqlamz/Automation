package com.by.automate.testCases.Interface_new;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.TreeMap;

public class test_interface_balance implements interface_url {
//    private commonTools co;
//    private sha256 sa;
//    private TreeMap map;
//    private  long time_stamp;
//    private  String url_balance;
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
//        url_balance = api_balance;   //接口----余额
//        params ="api_key="+apiKey+"&signature_version=1&api_version=1.0&signature="+sa.getSignature(map,access_key);
    }

    /*
    只提供公共参数，获取余额数据
     */
    @Test
    public void Test010(){

//        httprequest re = new httprequest();
//
//        String s= re.sendGet( url_balance,params);
//        System.out.println("服务器的响应值是 ："+s);
//        assert s.contains("\"message\":\"OK\""):"响应值有误！！";
//
//
//        System.out.println("\n============================");

        try{

            ApiRequest request = new ApiRequest();
            request.setUrl(api_balance);
            request.setApiKey(apiKey);
            request.setSecretKey(access_key);
            String s=httpRequerter.get(request);

            JSONObject jsonObject = new JSONObject().fromObject(s); //生成一个json对象

            String ret_code= jsonObject.getString("ret_code");
            String message= jsonObject.getString("message");
            String user_balance= jsonObject.getString("user_balance");
            String team_balance= jsonObject.getString("team_balance");
            String company_balance= jsonObject.getString("company_balance");

            //验证主要返回值
            Assert.assertEquals(ret_code,"3000");
            Assert.assertEquals(message,"OK");
            Assert.assertEquals(user_balance,"0.0");
//            Assert.assertEquals(team_balance,"921724.0");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
