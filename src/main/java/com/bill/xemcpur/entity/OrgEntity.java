package com.bill.xemcpur.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("bd_org")
public class OrgEntity implements Serializable {
    private String classcode;
    private String code;
    private String enablestate;
    private String name;
    private String pk;
    private String ts;

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

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
        return "OrgEntity{" +
                "classcode='" + classcode + '\'' +
                ", code='" + code + '\'' +
                ", enablestate='" + enablestate + '\'' +
                ", name='" + name + '\'' +
                ", pk='" + pk + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}
