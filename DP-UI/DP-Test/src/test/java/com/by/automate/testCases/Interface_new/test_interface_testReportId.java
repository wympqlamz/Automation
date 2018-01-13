package com.by.automate.testCases.Interface_new;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class test_interface_testReportId implements interface_url {


    private HttpRequerter httpRequerter=null;

    @BeforeClass
    public void setUp() {
        httpRequerter=new HttpRequerter();
    }

    @Test
    public void Test010(){
        ApiRequest request = new ApiRequest();
        request.setUrl(api_testReportID + testStatus_id_trainticket);
        request.setApiKey(apiKey);
        request.setSecretKey(access_key);
        request.addSecretParam("id",testStatus_id_trainticket);
        String s=httpRequerter.get(request);
        System.out.println(s);
        assert s!=null&&!s.isEmpty();
        com.alibaba.fastjson.JSONObject json=com.alibaba.fastjson.JSONObject.parseObject(s);
        String filePath=json.getString("zipFile");
        File file=new File(filePath);
        assert (file!=null&&file.exists()&&file.isFile());

//        httprequest re = new httprequest();
//        String s= re.sendGetFile( url_testReportId,params,"E:\\reportDownload","report.zip");

    }

}
