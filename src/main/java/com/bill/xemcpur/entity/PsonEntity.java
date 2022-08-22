package com.bill.xemcpur.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Objects;

@TableName("bd_posn")
public class PsonEntity implements Serializable {
    private String code;
    private String dept;
    private String enablestate;
    private String id;
    private String name;
    private String org;
    private String phone;
    private String pk;
    private String ts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEnablestate() {
        return enablestate;
    }

    public void setEnablestate(String enablestate) {
        this.enablestate = enablestate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PsonEntity that = (PsonEntity) o;
        return Objects.equals(code, that.code) && Objects.equals(dept, that.dept) && Objects.equals(enablestate, that.enablestate) && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(org, that.org) && Objects.equals(phone, that.phone) && Objects.equals(pk, that.pk) && Objects.equals(ts, that.ts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, dept, enablestate, id, name, org, phone, pk, ts);
    }

    @Override
    public String toString() {
        return "PsonEntity{" +
                "code='" + code + '\'' +
                ", dept='" + dept + '\'' +
                ", enablestate='" + enablestate + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", org='" + org + '\'' +
                ", phone='" + phone + '\'' +
                ", pk='" + pk + '\'' +
                ", ts='" + ts + '\'' +
                '}';
    }
}
