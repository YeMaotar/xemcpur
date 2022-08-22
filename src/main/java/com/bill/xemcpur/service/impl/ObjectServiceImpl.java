package com.bill.xemcpur.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.xemcpur.entity.ObjectEntity;
import com.bill.xemcpur.mapper.ObjectMapper;
import com.bill.xemcpur.service.ObjectService;
import org.springframework.stereotype.Service;

@Service
public class ObjectServiceImpl extends ServiceImpl<ObjectMapper, ObjectEntity> implements ObjectService {
}
