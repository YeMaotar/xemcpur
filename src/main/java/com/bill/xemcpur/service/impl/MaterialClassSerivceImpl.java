package com.bill.xemcpur.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.xemcpur.entity.MaterialclassEntity;
import com.bill.xemcpur.mapper.MaterialClassMapper;
import com.bill.xemcpur.service.MaterialClassService;
import org.springframework.stereotype.Service;

@Service
public class MaterialClassSerivceImpl extends ServiceImpl<MaterialClassMapper, MaterialclassEntity> implements MaterialClassService {
}
