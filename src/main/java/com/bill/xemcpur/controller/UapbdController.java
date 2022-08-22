package com.bill.xemcpur.controller;

import com.bill.xemcpur.entity.*;
import com.bill.xemcpur.service.IUapbdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 基础数据接口入口(物料分类，物料，部门，业务员，供应商，仓库，业务单元)
 * @author zhangzhw
 * @date 2022-06-21
 */
@RestController
@RequestMapping("uapbd")
public class UapbdController {
    @Autowired
    private IUapbdService service;
    /**
     * 部门入口
     * @param dept
     * @return
     */
    @PostMapping("dept")
    public boolean addDept(@RequestBody List<DeptEntity> dept){
        service.addDept(dept);
        return true;
        //return new Result(200,"成功",null);
        //return service.addDept(dept);

    }
    /**
     * 物料入口
     * @param material
     * @return
     */
    @PostMapping("material")
    public boolean addMaterial(@RequestBody List<MaterialEntity> material){
        return service.addMaterial(material);
        //return true;
    }
    /**
     * 业务单元入口
     * @param org
     * @return
     */
    @PostMapping("org")
    public boolean addOrg(@RequestBody List<OrgEntity> org){
        return service.addOrg(org);
        //return true;
    }
    /**
     * 人员入口
     * @param pson
     * @return
     */
    @PostMapping("pson")
    public boolean addPson(@RequestBody List<PsonEntity> pson){
        return service.addPson(pson);
        //return true;
    }
    /**
     * 仓库入口
     * @param stordoc
     * @return
     */
    @PostMapping("stordoc")
    public boolean addStord(@RequestBody List<StordocEntity> stordoc){
        return service.addStordoc(stordoc);
        //return true;
    }
    /**
     * 供应商入口
     * @param supper
     * @return
     */
    @PostMapping("supper")
    public boolean addSupplier(@RequestBody List<SupplierEntity> supper){
        return service.addSupplier(supper);
        //return true;
    }
    /**
     * 物料分类入口
     * @param materialclass
     * @return
     */
    @PostMapping("materialclass")
    public boolean addMaterialclass(@RequestBody List<MaterialclassEntity> materialclass){
        return service.addMaterialClass(materialclass);
        //return true;
    }

    /**
     * 工号入口
     * @param object
     * @return
     */
    @PostMapping("object")
    public boolean addObject(@RequestBody List<ObjectEntity> object){
        return service.addObject(object);
    }
}
