package com.bill.xemcpur.service.impl;

import com.bill.xemcpur.entity.*;
import com.bill.xemcpur.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class UapbdServiceImpl implements IUapbdService {
    @Autowired
    private DeptService deptservice;
    @Autowired
    private MaterialService materialservice;
    @Autowired
    private OrgService orgservice;
    @Autowired
    private PsonService psonservice;
    @Autowired
    private StordocService stordocservice;
    @Autowired
    private SupplierService supplierservice;
    @Autowired
    private MaterialClassService materialClassService;
    @Autowired
    private ObjectService objectService;

    @Override
    public boolean addDept(List<DeptEntity> dept) {
        deptservice.saveBatch(dept);
        return true;
    }

    @Override
    public boolean addMaterial(List<MaterialEntity> material) {
        materialservice.saveBatch(material);
        return true;
    }

    @Override
    public boolean addOrg(List<OrgEntity> org) {
        orgservice.saveBatch(org);
        return true;
    }

    @Override
    public boolean addMaterialClass(List<MaterialclassEntity> materialclass) {
        materialClassService.saveBatch(materialclass);
        return true;
    }

    @Override
    public boolean addPson(List<PsonEntity> pson) {
        psonservice.saveBatch(pson);
        return true;
    }

    @Override
    public boolean addStordoc(List<StordocEntity> stordoc) {
        stordocservice.saveBatch(stordoc);
        return true;
    }

    @Override
    public boolean addSupplier(List<SupplierEntity> supplier) {
        supplierservice.saveBatch(supplier);
        return true;
    }

    @Override
    public boolean addObject(List<ObjectEntity> object) {
        objectService.saveBatch(object);
        return true;
    }
}
