package com.bill.xemcpur.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Objects;

@TableName("bd_dept")
public class DeptEntity implements Serializable {
    private String pk;
    private String code;
    private String name;
    private String classcode;
    private String org;
    private String enablestate;
    private String ts;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getEnablestate() {
        return enablestate;
    }

    public void setEnablestate(String enablestate) {
        this.enablestate = enablestate;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public DeptEntity(String pk, String code, String name, String classcode, String org, String enablestate, String ts) {
        this.pk = pk;
        this.code = code;
        this.name = name;
        this.classcode = classcode;
        this.org = org;
        this.enablestate = enablestate;
        this.ts = ts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptEntity that = (DeptEntity) o;
        return Objects.equals(pk, that.pk) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(classcode, that.classcode) && Objects.equals(org, that.org) && Objects.equals(enablestate, that.enablestate) && Objects.equals(ts, that.ts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, code, name, classcode, org, enablestate, ts);
    }

    @Override
    public String toString() {
        return "DeptEntity{" +
                "pk='" + pk + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", classcode='" + classcode + '\'' +
                ", org='" + org + '\'' +
                ", enablestate='" + enablestate + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}
