package com.bill.xemcpur.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 接口日志vo
 */
@TableName("bd_log")
public class LogEntity implements Serializable {
    /**接口类型*/
    private String type;
    /**请求报文*/
    private String request;
    /**响应报文*/
    private String response;
    /**时间戳*/
    private String ts;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "type='" + type + '\'' +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}
