package com.by.automate.testCases.Interface_new;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.TreeMap;

public class test_interface_devices_deviceID implements interface_url {
    //    private commonTools co;
//    private sha256 sa;
//    private TreeMap map;
//    private  long time_stamp;
//    private String url_devices;
    private String device_id;
    private String device_serial;


    private HttpRequerter httpRequerter = null;

    @BeforeMethod
    public void setUp() {

        httpRequerter = new HttpRequerter();

    }

    /*
    获取device的数据
     */
    @Test
    public void Test010_devices() {

        ApiRequest request = new ApiRequest();
        request.setUrl(api_devices);
        request.setApiKey(apiKey);
        request.setSecretKey(access_key);
        request.addQueryParameter("limit",limit_d1);
        request.addQueryParameter("offset",offset_d1);
        String s = httpRequerter.get(request);
        assert s.contains("\"message\":\"ok\"");

        JSONObject jsonObject = new JSONObject().fromObject(s); //生成一个json对象
        JSONArray devices = jsonObject.getJSONArray("devices");
        JSONObject jo = devices.getJSONObject(0);

        device_id = jo.getString("id"); //取出第一个设备的id号
        device_serial = jo.getString("serial"); //取出第一个设备的序列号


    }

    @Test
    public void Test020_deivceID() {

        //使用test010中拿到的序列号，作为此接口的参数
        ApiRequest request = new ApiRequest();
        request.setUrl(api_deviceID + device_serial);
        request.setApiKey(apiKey);
        request.setSecretKey(access_key);

        request.addSecretParam("id",device_serial);

        String s = httpRequerter.get(request);
        assert s.contains("\"message\":\"ok\"");
        assert s.contains("\"total\":1"):"response is Error";

//        JSONObject jsonObject = new JSONObject().fromObject(s); //生成一个json对象
//        JSONArray devices = jsonObject.getJSONArray("devices");
//        JSONObject jo = devices.getJSONObject(0);


    }
}
