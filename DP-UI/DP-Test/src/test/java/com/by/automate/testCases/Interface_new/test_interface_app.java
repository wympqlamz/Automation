package com.by.automate.testCases.Interface_new;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONAware;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.TreeMap;


public class test_interface_app implements interface_url{

    private HttpRequerter httpRequerter=null;


    @BeforeClass
    public void setUp() {

        httpRequerter=new HttpRequerter();

    }


    /*
    使用账号maoyujia@beyondsoft.com
    post请求，上传一个指定的app文件到服务器
     */
    @Test
    public void Test010_uploadApp() {
        System.out.println(System.getProperty("user.dir"));

        ApiRequest request = new ApiRequest();
        request.setUrl(api_app);
        request.addBodyParameter("file",new File(System.getProperty("user.dir")+"\\src\\test\\java\\com\\by\\automate\\testCases\\Interface_new\\app\\com.citiccard.mobilebank_055812.apk"));
        request.setApiKey(apiKey01);
        request.setSecretKey(access_key01);
        String s=httpRequerter.post(request);

        com.alibaba.fastjson.JSONObject jsonObject=com.alibaba.fastjson.JSONObject.parseObject(s);
        Assert.assertEquals(jsonObject.getString("ret_code"),"3000");
        Assert.assertEquals(jsonObject.getString("message"),"ok");

        //拿到上传的app的信息
        JSONArray upload_app = jsonObject.getJSONArray("upload_app");
        System.out.println(upload_app);
        for(int i=0;i<upload_app.size();i++){
            com.alibaba.fastjson.JSONObject jo = upload_app.getJSONObject(i);
//            Assert.assertEquals(jo.getString("app_label"),"火车票");
            Assert.assertEquals(jo.getString("file_name"),"com.citiccard.mobilebank_055812.apk");

        }


//        JSONArray apps= jsonObject.getJSONArray("apps");


    }

}
