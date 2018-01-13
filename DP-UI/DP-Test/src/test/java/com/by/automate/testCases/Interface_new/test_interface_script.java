package com.by.automate.testCases.Interface_new;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class test_interface_script implements interface_url {
    private HttpRequerter httpRequerter = null;

    @BeforeClass
    public void setUp() {
        httpRequerter = new HttpRequerter();

    }

    @Test
    public void Test010() {

        ApiRequest request = new ApiRequest();
        request.setUrl(api_script);
        request.addBodyParameter("file",new File("D:\\trainticket_Demo11.zip"));
        request.setApiKey(apiKey);
        request.setSecretKey(access_key);
        request.addQueryParameter("script_type",3);
        request.addQueryParameter("app_id",app_id_trainticket);
        String s = httpRequerter.post(request);

        assert s.contains("\"message\":\"ok\"");

    }
}
