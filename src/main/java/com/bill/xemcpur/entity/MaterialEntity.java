package com.bill.xemcpur.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Objects;

@TableName("bd_material")
public class MaterialEntity implements Serializable {
    private String classcode;
    private String code;
    private String enablestate;
    private String measdoc;
    private String name;
    private String pk;
    private String spec;
    private String ts;
    private String type;

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

    public String getMeasdoc() {
        return measdoc;
    }

    public void setMeasdoc(String measdoc) {
        this.measdoc = measdoc;
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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialEntity that = (MaterialEntity) o;
        return Objects.equals(classcode, that.classcode) && Objects.equals(code, that.code) && Objects.equals(enablestate, that.enablestate) && Objects.equals(measdoc, that.measdoc) && Objects.equals(name, that.name) && Objects.equals(pk, that.pk) && Objects.equals(spec, that.spec) && Objects.equals(ts, that.ts) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classcode, code, enablestate, measdoc, name, pk, spec, ts, type);
    }

    @Override
    public String toString() {
        return "MaterialEntity{" +
                "classcode='" + classcode + '\'' +
                ", code='" + code + '\'' +
                ", enablestate='" + enablestate + '\'' +
                ", measdoc='" + measdoc + '\'' +
                ", name='" + name + '\'' +
                ", pk='" + pk + '\'' +
                ", spec='" + spec + '\'' +
                ", ts='" + ts + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
