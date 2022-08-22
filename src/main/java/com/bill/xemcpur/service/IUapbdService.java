package com.bill.xemcpur.service;

import com.bill.xemcpur.entity.*;

import java.util.List;

/**
 *
 */
public interface IUapbdService {
    boolean addDept(List<DeptEntity> dept);
    boolean addMaterial(List<MaterialEntity> material);
    boolean addOrg(List<OrgEntity> org);
    boolean addMaterialClass(List<MaterialclassEntity> materialclass);
    boolean addPson(List<PsonEntity> dept);
    boolean addStordoc(List<StordocEntity> stordoc);
    boolean addSupplier(List<SupplierEntity> supplier);
    boolean addObject(List<ObjectEntity> object);

}
