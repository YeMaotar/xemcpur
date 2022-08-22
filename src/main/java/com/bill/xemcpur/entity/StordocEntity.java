package com.bill.xemcpur.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("bd_stordoc")
public class StordocEntity implements Serializable {
    private String code;
    private String enablestate;
    private String name;
    private String org;
    private String pk;
    private String ts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEnablestate() {
        return enablestate;
    }

    public void setEnablestate(String enablestate) {
        this.enablestate = enablestate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "StordocEntity{" +
                "code='" + code + '\'' +
                ", enablestate='" + enablestate + '\'' +
                ", name='" + name + '\'' +
                ", org='" + org + '\'' +
                ", pk='" + pk + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}
