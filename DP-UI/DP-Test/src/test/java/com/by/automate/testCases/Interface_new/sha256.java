package com.by.automate.testCases.Interface_new;




import com.alibaba.fastjson.JSON;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class sha256 {




    public static String HMACSHA256(byte[] data, byte[] key)
    {
        try  {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");//（可以将字符串换成SignatureAlgorithm.HS256.getJcaName()）
            mac.init(signingKey);
            return byte2hex(mac.doFinal(data));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String byte2hex(byte[] b)
    {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString();
    }


    /*
        获取HMACSHA256签名
     */
    public  String getSignature(Map map, String apiKey){
//        com.by.automate.testCases.BAT.android.sha256 ss = new com.by.automate.testCases.BAT.android.sha256();
//        commonTools co = new commonTools();
//        String jsonstr = co.get_jsonStr(map);//拿到固定map的对应json
        if(apiKey!=null&&!apiKey.isEmpty()&&map!=null){
            String jsonstr = JSON.toJSONString(map);
            System.out.println("Signature JSON:"+ jsonstr);
            String key = apiKey;
    //        String key = "7arro76w17pys8p99ibr8gisnknj3gws";
            try{
                byte[] data_json = jsonstr.getBytes("UTF-8");
                byte[] data_key = key.getBytes("UTF-8");
                String sign_str=HMACSHA256(data_json,data_key);
                System.out.println("Signature is : "+sign_str);
                return sign_str;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String args[]){

//        String jsonstr = "{\"access_key\":\"7arro76w17pys8p99ibr8gisnknj3gws\",\"api_key\":\"7arro76w17pys8p99ibr8gisnknj3gws\",\"api_version\":\"1.0\",\"limit\":\"5\",\"offset\":\"0\",\"os_type\":\"0\",\"signature_version\":\"1\"}";
//        String key = "7arro76w17pys8p99ibr8gisnknj3gws";
//        try{
//            byte[] data_json = jsonstr.getBytes("UTF-8");
//            byte[] data_key = key.getBytes("UTF-8");
//            String s= sha256.HMACSHA256(data_json,data_key);
//            System.out.println(s);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }

}
