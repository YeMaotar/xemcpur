package com.bill.xemcpur.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 *
 * @author zhangzhw7 2021年9月7日08点44分
 * rest请求工具
 */
public class RestClientUtils {
    /**
     * 传送json类型的post请求
     * @param url
     * @param json
     * @return String
     */
    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }


    /**
     * 传送json类型的post请求
     * @param url
     * @param json
     * @return String
     */
    public static String doPostJsonToOa(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }


    /**
     *
     * 调用带文件的数据接口请求
     * @param url
     * @param data
     * @return
     *
     */

    public static String fileDataRequest(String url, String data, File pdfFile)
            throws ClientProtocolException, ParseException, IOException {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();
            StringBody requestData = new StringBody(data, ContentType.create("text/plain", Consts.UTF_8));
            FileBody bin = new FileBody(pdfFile);
            HttpEntity entity = MultipartEntityBuilder.create()
                    .addPart("file", bin)
                    .addPart("requestData", requestData)
                    .build();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(entity);
            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost);
            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            String responseStr = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
            // 销毁
            EntityUtils.consume(resEntity);
            return responseStr;
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }
}
