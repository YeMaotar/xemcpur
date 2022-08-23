package com.bill.xemcpur.controller;

import com.bill.xemcpur.utils.LogUtils;
import com.bill.xemcpur.service.LogSerivce;
import com.bill.xemcpur.utils.RestClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 业务单据接口入口(请购单，NC采购合同，年度计划，采购平台采购合同)
 * @author zhangzhw
 * @date 2022-06-21
 */
@RestController
@RequestMapping("bill")
public class OperationsBillController {

    @Value("${ecg.service.url}")
    private String ecgurl;
    /**
     * 注入访问第三方接口服务
     */
    @Autowired
    RestTemplate restTemplate;
    /**
     * 注入日志接口服务
     */
    @Autowired
    LogSerivce logSerivce;

    /**
     * nc请购单调用该接口转发E采购
     * @param requesting
     * @return
     */
    @PostMapping("/praybill")
    public String NcPrayBillToEBill(@RequestBody String requesting){
        String url = "http://192.168.1.20:8080/rest-api/receive/ncPoOrder";
        logSerivce.save(LogUtils.getEntity("请购单",requesting,null));
       String response =  RestClientUtils.doPostJson(url,requesting);
       return "成功";
    }

    /**
     * nc采购合同调用该接口转发E采购
     * @param requesting
     * @return
     */
    @PostMapping("/ctpu")
    public String NcCtPuToEBill(@RequestBody String requesting){
        String url = "http://192.168.1.20:8080/rest-api/receive/erpContractResult";
        String response =  RestClientUtils.doPostJson(url,requesting);
        return "成功";
    }
//
    /**
     * E采购采购合同调用该接口转发NC
     * @param requesting
     * @return
     */
    @PostMapping("/ectpu")
    public String EcgToNcCtPuBill(@RequestBody String requesting){
        String url = "http://192.168.1.5:80/uapws/rest/ecgservice/creatbill";
        logSerivce.save(LogUtils.getEntity("采购平台采购合同",requesting,null));
        String response =  RestClientUtils.doPostJson(url,requesting);
        return "成功";
    }
    /**
     * E年标计划调用该接口转发NC
     * @param requesting
     * @return
     */
    @PostMapping("/eplan")
    public String EcgPlanToNcDefBill(@RequestBody String requesting){
        String url = "http://192.168.1.5:80/uapws/rest/ecgservice/creatplanbill";
        logSerivce.save(LogUtils.getEntity("年标合同",requesting,null));
        String response =  RestClientUtils.doPostJson(url,requesting);
        return "成功";
    }
}

