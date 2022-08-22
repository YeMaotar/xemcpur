package com.bill.xemcpur.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.xemcpur.entity.SupplierEntity;
import com.bill.xemcpur.mapper.SupplierMapper;
import com.bill.xemcpur.service.SupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl  extends ServiceImpl<SupplierMapper, SupplierEntity> implements SupplierService {
}
