package com.bill.xemcpur.controller;
import java.io.*;
import java.nio.charset.Charset;
import com.alibaba.fastjson.JSONArray;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.bill.xemcpur.utils.RestClientUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 业务单据接口入口(请购单，NC采购合同，年度计划，采购平台采购合同)
 * @author zhangzhw
 * @date 2022-06-21
 */
@RestController
@RequestMapping("transit")
public class OaTransitController {
    String url="http://192.180.4.14/";
    String tabName="formmain_1582";

    //http://127.0.0.1:8097/
    //formmain_0116  本机

    /**
     * 上传文件数据到OA
     * @param multipartFile
     * @return
     */
    @PostMapping(value = "/upload")
    public String upLoadFiles(MultipartFile multipartFile) throws Exception{
        File file=multipartFileToFile(multipartFile);
        //删除缓存信息
        try {
            String str= fileDataRequest(url+"seeyon/rest/attachment?token="+getToken(), "{\"1\":1}",file);
            Map<String, Object> returnData = JSON.parseObject(str, HashMap.class);
            Map<String,Object> data0=JSON.parseObject(((List<Object>)returnData.get("atts")).get(0).toString());
            System.out.println(data0.get("fileUrl"));
            File myObj = new File(multipartFile.getOriginalFilename());
            myObj.delete();
            return data0.get("fileUrl").toString();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * 调用带文件的数据接口请求
     * @param url
     * @param data
     * @return
     *
     */

    public String fileDataRequest(String url, String data, File pdfFile)
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


    /**
     * 获取OA token
     * @param
     * @return
     */
    @PostMapping(value = "/getToken")
    public String getToken() throws Exception{
        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("userName", "xd-admin");
        myMap.put("password", "ae578eee-acff-4b75-9aea-169155531c0f");
        myMap.put("loginName", "liuxuan");
        //myMap.put("userName", "rest");
        //myMap.put("password", "01e2acf7-1ae5-45a7-864c-3958d15b5063");
        //myMap.put("loginName", "timber");
        String json= JSONUtils.toJSONString(myMap);
        String  retdata =RestClientUtils.doPostJson(url+"seeyon/rest/token", json);
        Map<String, Object> returnData = JSON.parseObject(retdata, HashMap.class);
        return returnData.get("id").toString();
    }

    /**
     *
     * @param data  调用OA流程表单接口
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/uploadMain")
    public String uploadMain(@RequestBody Map<String, Object> data) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appName", "collaboration");
        Map<String, Object> dataOne = new HashMap<String, Object>();
        dataOne.put("templateCode", "CGHTSPD");//流程表单编码
        dataOne.put("draft", "0");
        dataOne.put("attachments", data.get("fileUrl"));
        dataOne.put("subject",data.get("title"));//标题
        Map<String, Object> dataTwo = new HashMap<String, Object>();
        HashMap<String, Object> mainData =new HashMap<String, Object>();
        mainData.put("发起人", data.get("pers"));
        mainData.put("发起人部门", data.get("dept"));
        mainData.put("供应商名称", data.get("supplier"));
        mainData.put("合同编码", data.get("code"));
        mainData.put("合同名称", data.get("name"));
        mainData.put("价格依据", data.get("basis"));
        mainData.put("合同总金额", data.get("money"));
        mainData.put("合同签订人", data.get("consignee"));
        mainData.put("付款方式", data.get("paymentType"));
        mainData.put("合同类别", data.get("pactType"));
        mainData.put("合同来源", data.get("source"));
        mainData.put("备注", data.get("note"));
        mainData.put("附件ID", data.get("fileUrl"));
        mainData.put("合同附件", "8694702911343250661");
        HashMap<String, Object> thirdAttachment =new HashMap<String, Object>();
        thirdAttachment.put("subReference", 8694702911343250661L);
        thirdAttachment.put("fileUrl", -700452078380429737L);
        thirdAttachment.put("sort", "1");
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        list.add(thirdAttachment);
        dataTwo.put(tabName, mainData);
        dataTwo.put("thirdAttachments", list);
        dataOne.put("data", dataTwo);
        map.put("data", dataOne);
        String json= JSONUtils.toJSONString(map);
        String  retdata =RestClientUtils.doPostJson(url+"seeyon/rest/bpm/process/start?token="+getToken(), json);
        return  retdata.toString();
    }

    /**
     *
     * @param data  回写数据打E采购
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/writeBackToEproc")
    public Map<String,Object> writeBackToEproc(@RequestBody Map<String, Object> data) throws Exception{
        Long id=(Long) data.get("id");
        String filename=(String) data.get("filename");
        // 创建 RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //get请求参数
        headers.add("token","a01fce98-9d3d-453d-8b8d-d938ef4e82b7");
        headers.add("fileName",filename);
        // 调用下载接口进行下载
        // id 为String类型，为被调用接口参数
        ResponseEntity<byte[]> entity = restTemplate.exchange("http://127.0.0.1:8097//seeyon/rest/attachment/file/"+id, HttpMethod.GET,new org.springframework.http.HttpEntity<>(headers), byte[].class);
        // 返回数据，在下面写到输出流里面（由于我是需要把字节数组传递给另外一个系统，所以我注释了下面的代码，我不需要存本地）
        byte[] body = entity.getBody();

        if(body != null) {
            // 存到本地
            String filepath = filename;
            File file = new File(filepath);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(body, 0, body.length);
            fos.flush();
            fos.close();


            try {
                //调用E采购回写接口
                String str= fileDataRequest(url+"seeyon/rest/attachment?token="+getToken(), "{\"1\":1}",file);
                Map<String, Object> returnData = JSON.parseObject(str, HashMap.class);
                Map<String,Object> data0=JSON.parseObject(((List<Object>)returnData.get("atts")).get(0).toString());
                System.out.println(data0.get("fileUrl"));
                //删除本地文件
                file.delete();
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }



        Map<String,Object> rdata=new HashMap<String, Object>();
        rdata.put("resCode","0");
        rdata.put("resMsg","回写成功");
        rdata.put("resData","成功");

        return rdata;
    }


    /**
     *
     * 查询OA人员表清单
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/queryPersonnel")
    public List<Map> queryPersonnel() throws Exception{
        String  retdata =RestClientUtils.doPostJson(url+"seeyon/rest/personnel/query?token="+getToken(), "");
        List<Map> list= JSONArray.parseArray(retdata,Map.class);
        return list;
    }



    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public File multipartFileToFile(MultipartFile file) {
        try {
            File toFile;
            if (file != null && file.getSize() > 0) {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
                return toFile;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取流文件
     *
     * @param ins
     * @param file
     */
    public void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 该方法将字符串形式的数组转化为字符串数组
     * @param str 字符串
     * @return 返回转化完的数组
     */
    public String[] parseStrArray(String str){
        String substr = str.substring(2, str.length() - 2);
        String[] split = substr.split("\",\"");
        return split;
    }

}

