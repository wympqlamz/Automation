package com.by.automate.testCases.Interface_new;

import org.apache.http.util.TextUtils;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by scott on 17-6-20.
 */
public class ApiRequest
{
    /**
     * 目标url
     */
    private String url;

    /**
     * 基础参数
     */
    private Map<String, Object> queryParams;

    /**
     * 基础参数
     */
    private Map<String, Object> bodyParams;

    /**
     * 需要一起签名的Header
     */
    private Map<String, String> headers;


    private Map<String,String> secretParams;

    /**
     * 需要过滤的关键字
     */
    private String[] blackKeys;

    /**
     * 放到url中的访问api的key.
     */
    private String apiKey;

    /**
     * 签名时使用的key.
     */
    private String secretKey;

    /**
     * 签名
     */
    private String signature=null;

    /**
     * 1: 签名算法
     * 2：使用api_key
     * 0: 无
     */
    private int urlKeyType=1;


    public final static String POST = "POST";
    public final static String GET = "GET";
    public final static String DELETE = "DELETE";
    public final static String PUT = "PUT";



    private String httpMethod= GET;

    public ApiRequest(String url, Map<String, Object> queryParams, Map<String, Object> bodyParams, Map<String, String> headers, String[] blackKeys) {
        this.url = url;
        this.queryParams = queryParams;
        this.bodyParams = bodyParams;
        this.headers = headers;
        this.blackKeys = blackKeys;
    }

    public ApiRequest() {
    }

    public void clear(){
        this.url=null;
        this.queryParams=new HashMap<String, Object>();
        this.bodyParams=new HashMap<String, Object>();
        this.headers=new HashMap<String, String>();
        this.blackKeys=null;
        this.secretParams=new HashMap<String, String>();
        apiKey=null;
        secretKey=null;
        signature=null;
    }


    public ApiRequest(String url, Map<String, Object> queryParams) {
        this(url, queryParams, null, null,null);
    }

    public void addQueryParameter(String key,Object obejct){
        if(key!=null&&!key.isEmpty()){
            if(queryParams==null){
                queryParams=new HashMap<String, Object>();
            }
            if(obejct==null){
                queryParams.put(key,"");
            }else{
                queryParams.put(key,obejct);
            }
        }
    }

    public void addQueryParameter(Map<String,Object> maps){
        if(queryParams==null){
            queryParams=new HashMap<String, Object>();
        }
        if(maps==null&&!maps.isEmpty()){
            queryParams.putAll(maps);
        }
    }


    public void addBodyParameter(String key,Object obejct){
        if(key!=null&&!key.isEmpty()){
            if(bodyParams==null){
                bodyParams=new HashMap<String, Object>();
            }
            if(obejct==null){
                bodyParams.put(key,"");
            }else{
                bodyParams.put(key,obejct);
            }
        }
    }

    public void addSecretParam(String key,String value){
        if(key==null||key.isEmpty()){
            return;
        }
        initSecretParams();
        secretParams.put(key,value==null?"":value);
    }


    public void addHeaderParameter(String key,Object obejct){
        if(key!=null&&!key.isEmpty()){
            if(headers==null){
                headers=new HashMap<String, String>();
            }
            if(obejct==null){
                headers.put(key,"");
            }else{
                headers.put(key,String.valueOf(obejct));
            }
        }
    }



    private Map<String,String> initSecretParams(){
        if(secretParams==null){
            secretParams=new TreeMap<String,String>();
        }
        return secretParams;
    }

    public void addSecretStrParams(Map<String,String> params){
        if(params!=null&&!params.isEmpty()){
            for(Map.Entry<String,String> entry:params.entrySet()){
                addSecretParam(entry.getKey(),entry.getValue());
            }
        }
    }
    public void addSecretParams(Map<String,Object> params){
        if(params!=null&&!params.isEmpty()){
            for(Map.Entry<String,Object> entry:params.entrySet()){
                Object value=entry.getValue();
                if(value==null){
                    addSecretParam(entry.getKey(),"");
                }else{
                    if(!(value instanceof File)){
                        addSecretParam(entry.getKey(),String.valueOf(value));
                    }
                }
            }
        }
    }

    /**
     * 把查询字符串中的各个项目转换成map
     *
     * @param url
     */
    private void handQueryParams(String url) {
        //System.out.println("input url:" + url);
        URI serverUrl = URI.create(url);
        String query = serverUrl.getQuery();
        //System.out.println("parsed query:" + query);
        if (!TextUtils.isEmpty(query)) {
            String[] kvs = query.split("&");
            for (String kv : kvs) {
                String[] kvAry = kv.split("=");
                addSecretParam(kvAry[0], kvAry[1]);
            }
        }
        //addParmsToMap(parameter.getQueryParams(),map);
    }


    public Map<String,String> getAllSecretParams(){
        initSecretParams();
        addSecretParams(queryParams);
        handQueryParams(url);
        addSecretParams(bodyParams);
        addSecretStrParams(headers);
        return filterBlackKeys(secretParams);
    }

    private Map<String,String> filterBlackKeys(Map<String,String> map){
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(blackKeys!=null&&blackKeys.length>0){
                Map<String,String> newSecretParams=new TreeMap<String,String>();
                for(String blackKey:blackKeys){
                    if(blackKey==null||!blackKey.equals(entry.getKey())){
                        newSecretParams.put(entry.getKey(),entry.getValue());
                        break;
                    }
                }
                return newSecretParams;
            }else{
                break;
            }
        }
        return map;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String[] getBlackKeys() {
        return blackKeys;
    }

    public void setBlackKeys(String[] blackKeys) {
        this.blackKeys = blackKeys;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    public Map<String, Object> getBodyParams() {
        return bodyParams;
    }

    public void setBodyParams(Map<String, Object> bodyParams) {
        this.bodyParams = bodyParams;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getUrlKeyType() {
        return urlKeyType;
    }

    public void setUrlKeyType(int urlKeyType) {
        this.urlKeyType = urlKeyType;
    }



}
