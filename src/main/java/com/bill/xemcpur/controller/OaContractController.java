package com.bill.xemcpur.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.bill.xemcpur.utils.RestClientUtils;
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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @title: FileController
 * @Author ZhangZw
 * @Date: 2022/4/25 15:56
 * @Version 1.0
 * 文件上传接口
 */
@RestController
@RequestMapping("/OaContract")
public class OaContractController {
    //String url="http://192.180.4.14/";  //OA地址
    String tabName="formmain_1582";    //OA流程表单名称
    String url="http://192.168.1.117:8097/";   //OA地址
    //String tabName="formmain_0116"; 本机
    //String fileSite="C:\\Users\\Timber\\Desktop\\OEFile\\";  //文件保存地址
    String fileSite="/Users/astronaut/IdeaProjects/";

    /**
     * 上传文件数据到OA
     * @param multipartFile
     * @return
     */
    @PostMapping(value = "/upload")
    public String upLoadFiles(@RequestParam("multipartFile") MultipartFile multipartFile) throws Exception{
        File file=multipartFileToFile(multipartFile);
        //删除缓存信息
        try {
            String str= RestClientUtils.fileDataRequest(url+"seeyon/rest/attachment?token="+getToken(), "{\"1\":1}",file);
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
     * 提供id下载文件
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam(value="id") String id)
            throws IOException {
        String filePath = fileSite+getCurrTime()+id;
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String(id.getBytes("UTF-8"),"ISO-8859-1")));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }



    /**
     * 获取OA token
     * @param
     * @return
     */
    @PostMapping(value = "/getToken")
    public String getToken() throws Exception{
        Map<String, String> myMap = new HashMap<String, String>();
        //myMap.put("userName", "xd-admin");
        //myMap.put("password", "ae578eee-acff-4b75-9aea-169155531c0f");
        //myMap.put("loginName", "liuxuan");
        myMap.put("userName", "rest");
        myMap.put("password", "01e2acf7-1ae5-45a7-864c-3958d15b5063");
        myMap.put("loginName", "timber");
        String json= JSONUtils.toJSONString(myMap);
        String  retdata =RestClientUtils.doPostJsonToOa(url+"seeyon/rest/token", json);
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
        dataOne.put("attachments", data.get("field0013"));
        dataOne.put("subject",data.get("field0005"));//标题
        Map<String, Object> dataTwo = new HashMap<String, Object>();
        HashMap<String, Object> mainData =new HashMap<String, Object>();
        mainData.put("发起人", data.get("field0001"));
        mainData.put("发起人部门", data.get("field0002"));
        mainData.put("供应商名称", data.get("field0003"));
        mainData.put("合同编码", data.get("field0004"));
        mainData.put("合同名称", data.get("field0005"));
        mainData.put("价格依据", data.get("field0006"));
        mainData.put("合同总金额", data.get("field0007"));
        mainData.put("合同签订人", data.get("field0008"));
        mainData.put("付款方式", data.get("field0009"));
        mainData.put("合同类别", data.get("field0010"));
        mainData.put("合同来源", data.get("field0011"));
        mainData.put("备注", data.get("field0012"));
        mainData.put("附件ID", data.get("field0013"));
        //mainData.put("合同附件", "8694702911343250661");
        HashMap<String, Object> thirdAttachment =new HashMap<String, Object>();
        thirdAttachment.put("subReference", 8694702911343250661L);
        thirdAttachment.put("fileUrl", -700452078380429737L);
        thirdAttachment.put("sort", "1");
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        list.add(thirdAttachment);
        dataTwo.put(tabName, mainData);
        //dataTwo.put("thirdAttachments", list);
        dataOne.put("data", dataTwo);
        map.put("data", dataOne);
        String json= JSONUtils.toJSONString(map);
        String  retdata =RestClientUtils.doPostJsonToOa(url+"seeyon/rest/bpm/process/start?token="+getToken(), json);
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
        String filepath = fileSite+getCurrTime();
        Long id=(Long) data.get("id");
        String filename=(String) data.get("filename");
        // 创建 RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //get请求参数
        headers.add("token",getToken());
        headers.add("fileName",filename);
        // 调用下载接口进行下载
        // id 为String类型，为被调用接口参数
        ResponseEntity<byte[]> entity = restTemplate.exchange(url+"seeyon/rest/attachment/file/"+id, HttpMethod.GET,new org.springframework.http.HttpEntity<>(headers), byte[].class);
        // 返回数据，在下面写到输出流里面（由于我是需要把字节数组传递给另外一个系统，所以我注释了下面的代码，我不需要存本地）
        byte[] body = entity.getBody();

        if(body != null) {
            //判断文件夹是否存在，不存在则创建一个 一个月一个文件夹
            judgeSite(filepath);
            // 存到本地
            filepath= filepath + id+"." +getFileSffix(filename);
            File file = new File(filepath);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(body, 0, body.length);
            fos.flush();
            fos.close();
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
    public String queryPersonnel(@RequestBody Map<String, Object> data) throws Exception{
        String  retdata =RestClientUtils.doPostJsonToOa(url+"seeyon/rest/personnel/query?token="+getToken(), JSONUtils.toJSONString(data));
        return retdata;
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

    public static void main(String[] args) {
        try {
            Map<String, Object> tabMap = new HashMap<String, Object>();
            tabMap.put("id",  -7837679920534370626L);
            tabMap.put("filename",  "2016制造费用.xls");
            new OaContractController().writeBackToEproc(tabMap);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 判断指定文件夹是否存在
     */
    public void judgeSite(String site){
        File file = new File(site);
        if(!file.exists()&& !file.isDirectory()){
            file.mkdirs();                        //把a.sql也当做文件夹名来创建
            file.getParentFile().mkdirs();       //获取路径的根路径领创建文件，到最后最后一级路径前E:\新建文件夹\20211110\
        }
    }


    public  String getCurrTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMM");
        return simpleDateFormat.format(new Date())+"/";
    }


    public String getFileSffix(String filename){
        String[] strs=filename.split("\\.");
        return strs[strs.length-1];
    }

}
