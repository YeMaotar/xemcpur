package com.bill.xemcpur.utils;


import com.bill.xemcpur.entity.LogEntity;

/**
 * 日志工具类
 */
public  class LogUtils {
    public static LogEntity getEntity(String type, String request, String response){
        LogEntity entity = new LogEntity();
        entity.setType(type);
        entity.setRequest(request);
        entity.setResponse(response);
        return entity;
    }
}
