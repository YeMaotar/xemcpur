package com.bill.xemcpur.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.xemcpur.entity.MaterialEntity;
import com.bill.xemcpur.mapper.MaterialMapper;
import com.bill.xemcpur.service.MaterialService;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, MaterialEntity> implements MaterialService {
}
