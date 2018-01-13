package com.by.automate.testCases.Interface_new;

import android.webkit.CookieManager;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;

import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class HttpRequerter
{
    private HttpClient httpclient;

    /**
     * 签名的值
     */
    private final static String SIGNATURE = "signature";

    /**
     * 签名版本
     */
    private final String SIGNATURE_VERSION = "signature_version";

    /**
     *代表用户的key
     */
    private final String API_KEY = "api_key";

    private  final String TIME_STAMP = "time_stamp";



    public String post(ApiRequest request){
        loadSignParms(request);
        String url=getQueryUrl(request);
        System.out.println("HTTP Post URL: "+url);

        System.out.println("request.getBodyParams(): "+request.getBodyParams());
        return post(url, request.getBodyParams(), new Header[]{});
    }

    public String get(ApiRequest request){
        loadSignParms(request);
        String url=getQueryUrl(request);
        System.out.println("HTTP Get URL: "+url);
        return get(url, new Header[]{});
    }


    private void loadSignParms(ApiRequest parameter){
        if(parameter.getApiKey()!=null)
        {
            parameter.addQueryParameter(API_KEY, parameter.getApiKey());
        }
        if(parameter.getQueryParams()==null||!parameter.getQueryParams().containsKey(SIGNATURE_VERSION)){
            parameter.addQueryParameter( SIGNATURE_VERSION, "1");
        }


//        if(parameter.getQueryParams()==null || !parameter.getQueryParams().containsKey(TIME_STAMP)){
//            parameter.addQueryParameter( TIME_STAMP, String.valueOf((new Date()).getTime()));
//        }

        sha256 sa = new sha256();
        String signature = sa.getSignature(parameter.getAllSecretParams(),parameter.getSecretKey());
        if(signature!=null&&!signature.isEmpty()){
            parameter.setSignature(signature);
        }
    }


    private String getQueryUrl(ApiRequest request){
        String url=joinUrl(request.getUrl(),request.getQueryParams());
        String signature =request.getSignature();
        if(signature!=null&&!signature.isEmpty()){
            url=joinUrl(url, SIGNATURE,request.getSignature());
        }
        return url;
    }

    private String joinUrl(String url,Map<String,Object> map){
        if(map!=null&&!map.isEmpty()){
            StringBuffer sb=new StringBuffer();
            for(Map.Entry<String,Object> entry:map.entrySet()){
                String key=entry.getKey();
                Object value=entry.getValue();
                sb.append(key).append("=").append(encodeValue(value)).append("&");
            }
            if(sb.length()>0){
                sb.setLength(sb.length()-1);
            }
            return joinUrl(url,sb.toString());
        }else{
            return url;
        }
    }
    private String joinUrl(String url,String key,Object value){
        if(key!=null&&!key.isEmpty()){
            value=encodeValue(value);
            return joinUrl(url,key+"="+value);
        }else{
            return url;
        }
    }

    private String joinUrl(String url, String parms) {
        if (parms != null && !parms.isEmpty()) {
            if (url.contains("?")) {
                if (!url.endsWith("&")) {
                    url = url + "&";
                }
                url = url + parms;
            } else {
                url = url + "?" + parms;
            }
        }
        return url;
    }

    private String encodeValue(Object value){
        try {
            return (value!=null? URLEncoder.encode(String.valueOf(value), "UTF-8"):"");
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
        return "";
    }

    private String post(String url, Map<String, Object> body, Header[] headers) {

        try
        {
            return postBase(url, body, headers, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    private boolean resetClient(String url){
        if(url.startsWith("https")){try {
            if (httpclient==null) {
                httpclient = (HttpClient) new SSLClient();
                httpclient.getParams().setParameter("http.socket.timeout",60000);
                httpclient.getParams().setParameter("http.connection.timeout",60000);
                httpclient.getParams().setParameter("http.connection-manager.timeout",1200000l);
            }else if(!(httpclient instanceof SSLClient)){
                httpclient = (HttpClient) new SSLClient();
                httpclient.getParams().setParameter("http.socket.timeout",60000);
                httpclient.getParams().setParameter("http.connection.timeout",60000);
                httpclient.getParams().setParameter("http.connection-manager.timeout",1200000l);
            }} catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (KeyManagementException e) {
            e.printStackTrace();
            e.printStackTrace();
            return false;
        }
        }else {
            if (httpclient==null) httpclient = new DefaultHttpClient();
            else if(httpclient instanceof SSLClient){
                httpclient = new DefaultHttpClient();
            }
        }
        return true;
    }

    private String postBase(String url,Object data ,Header[] headers,boolean shutdown) throws Exception
    {
        if(!resetClient(url)){
            throw new Exception("reset http client error");
        }
        String secretKey = "";
        // 创建httppost
        HttpPost httppost = new HttpPost();

        //        Map<String, String> paramters = new TreeMap<>();

        if (headers != null && headers.length > 0) {
            httppost.setHeaders(headers);
        }
        try {
            if(data!=null){
                if (data instanceof JSONObject) {
                    //System.out.println(data);
                    StringEntity postingString = new StringEntity(((JSONObject) data).toJSONString(), ContentType.APPLICATION_JSON);// json传递
                    httppost.setEntity(postingString);
                } else if (data instanceof String) {
                    //System.out.println(data);
                    StringEntity postingString = new StringEntity((String)data,ContentType.APPLICATION_JSON);// json传递
                    //httppost.setHeader("Content-type", "application/json");
                    httppost.setEntity(postingString);
                } else if (data instanceof Map) {
                    List<File> files=new ArrayList<File>();
                    Map<String,Object> values=new HashMap<String,Object>();
                    int index=0;
                    for(Map.Entry<String,Object> entry:((Map<String,Object>)data).entrySet()){
                        String key=entry.getKey();
                        Object value=entry.getValue();
                        if(value!=null && value instanceof File){
                            files.add((File)value);
                        }else{
                            values.put(key,value);
                        }
                        index++;
                    }
                    if(!files.isEmpty()){
                        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
                        for(File f:files){
                            FileBody bin = new FileBody(f,ContentType.DEFAULT_BINARY, URLEncoder.encode(f.getName(), "UTF-8"));
                            entityBuilder.addPart("file", bin);
                        }
                        if(!values.isEmpty()){
                            StringBody body=new StringBody(JSONObject.toJSONString(values),ContentType.APPLICATION_JSON);
                            entityBuilder.addPart("data",body);
                        }
                        httppost.setEntity(entityBuilder.build());
                    }else{
                        StringEntity postingString = new StringEntity(JSONObject.toJSONString(data),ContentType.APPLICATION_JSON);// json传递
                        httppost.setEntity(postingString);
                    }
                } else if (data instanceof File){
                    MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
                    File f=(File)data;
                    FileBody bin = new FileBody(f,ContentType.DEFAULT_BINARY,URLEncoder.encode(f.getName(), "UTF-8"));
                    entityBuilder.addPart("file", bin);
                    HttpEntity reqEntity =entityBuilder.build();
                    httppost.setEntity(reqEntity);
                }
            }
            httppost.setURI(URI.create(url));
            HttpResponse response = httpclient.execute(httppost);
            return parseResponse(response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (shutdown) {
                shutdown();
            }
        }
    }

    private String parseResponse(HttpResponse response){
        // 获取响应实体
        HttpEntity entity = response.getEntity();
        //System.out.println("--------------------------------------");
        // 打印响应状态
        //System.out.println(response.getStatusLine());
        if (entity != null) {
            if (response.getHeaders("content-disposition").length > 0 && response.getHeaders("content-disposition")[0].getValue().contains("attachment")) {

                String fileName = response.getHeaders("content-disposition")[0].getValue().split("=")[1];
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2"+fileName);
                String filePath = null;
                try {
                    filePath = saveFile(entity, fileName);
                    JSONObject jsonRoot = new JSONObject();
                    jsonRoot.put("status", 1);
                    jsonRoot.put("zipFile", filePath);
                    jsonRoot.put("ret_code", "3000");
                    return jsonRoot.toJSONString();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                try {
                    String body = EntityUtils.toString(entity);
                    System.out.println("Response content: "+body);
                    return body;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }else{
            return null;
        }
    }

    private String get(String url,Header[] headers) {
        try
        {
            return get(url,headers, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 发送 get请求
     */
    private String get(String url,Header[] headers, boolean shutdown) throws Exception
    {
        if (!resetClient(url)) {
            throw new Exception("reset http client error");
        }
        try {
            // 创建httpget.
            System.out.println("**********"+url);
            //            String signedUrl = RequestSignatureHandler.getSignedUrl(url,
            //                    new TreeMap(),secretKey);
            //            System.out.println(signedUrl);

            HttpGet httpget = new HttpGet(url);
            if (headers != null && headers.length > 0) {
                httpget.setHeaders(headers);
            }
            //            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            HttpResponse response = httpclient.execute(httpget);
            return parseResponse(response);
            //System.out.println("------------------------------------");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (shutdown) {
                shutdown();
            }
        }

    }

    public String  delete_query(ApiRequest request ){
        loadSignParms(request);
        String url=getQueryUrl(request);
        System.out.println("HTTP Get URL: "+url);
        return delete_i(url,request.getBodyParams(), new Header[]{});
    }

    private String delete_i(String url, Map<String, Object> body, Header[] headers) {

        try
        {
            return deleteBase(url, body, headers, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

//    public static byte[] doDelete(String url) {
//        InputStream in;
//        byte[] bre = null;
//        HttpResponse response;
//        CookieManager manager = CookieManager.getInstance();
//        if (url != null && url.length() != 0) {
//            URL myurl = URL.parseString(url);
//            Cookie[] cookies = manager.getCookies(myurl);
//            HttpDelete delete = new HttpDelete(url);
//            if (cookies != null && cookies.length > 0) {
//                StringBuilder sb = new StringBuilder();
//                for (Cookie ck : cookies) {
//                    sb.append(ck.name).append('=').append(ck.value).append(";");
//                }
//                String sck = sb.toString();
//                if (sck.length() > 0) {
//                    delete.setHeader("Cookie", sck);
//                }
//            }
//            delete.setHeader("Accept-Encoding", "gzip, deflate");
//            delete.setHeader("Accept-Language", "zh-CN");
//            delete.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
//            try {
//                response = new DefaultHttpClient().execute(delete);
//                if (response != null) {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode == 200 || statusCode == 403) {
//                        Header[] headers = response.getHeaders("Set-Cookie");
//                        if (headers != null && headers.length > 0) {
//                            for (Header header : headers) {
//                                manager.setCookie(myurl, header.getValue());
//                            }
//                        }
//                        in = response.getEntity().getContent();
//                        if (in != null) {
//                            bre = ResourceUtil.readStream(in);
//                        }
//                    }
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return bre;
//    }


    private String deleteBase(String url,Object data ,Header[] headers,boolean shutdown) throws Exception
    {
        if(!resetClient(url)){
            throw new Exception("reset http client error");
        }
        String secretKey = "";
        // 创建httppost
        HttpDelete http_delete = new HttpDelete();

        //        Map<String, String> paramters = new TreeMap<>();

        if (headers != null && headers.length > 0) {
            http_delete.setHeaders(headers);
        }
        try {
            http_delete.setURI(URI.create(url));
            HttpResponse response = httpclient.execute(http_delete);
            return parseResponse(response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (shutdown) {
                shutdown();
            }
        }
    }

    private String getTempFolderPath(){
        return System.getProperty("java.io.tmpdir");
    }

    private String saveFile(HttpEntity entity, String fileName) throws IOException {
        //File file = File.createTempFile("dp_",fileName);
        File file=new File(getTempFolderPath(),fileName);
        System.out.println(file);
        InputStream is = null;
        FileOutputStream os = null;
        try {
            is = entity.getContent();

            os = new FileOutputStream(file);

            byte[] buffer = new byte[4 * 1024];
            int read;
            while ((read = is.read(buffer)) > 0) {

                os.write(buffer, 0, read);
            }


        } catch (final ConnectionClosedException ignore) {
            // ignore this error
//            ignore.printStackTrace();
        }finally
        {
           if(os!=null){
               os.close();
           }
           if(is!=null){
               is.close();
           }
        }

        return file.getPath();
    }

    public void shutdown() {
        httpclient.getConnectionManager().shutdown();
    }


    class SSLClient extends DefaultHttpClient{
        public SSLClient() throws NoSuchAlgorithmException, KeyManagementException {
            super();
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                        String authType) throws CertificateException
                {
                }
                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                        String authType) throws CertificateException {
                }
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = this.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", 443, ssf));
        }
    }




}
