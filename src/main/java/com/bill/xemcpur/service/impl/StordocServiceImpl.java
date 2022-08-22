package com.bill.xemcpur.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.xemcpur.entity.StordocEntity;
import com.bill.xemcpur.mapper.StordocMapper;
import com.bill.xemcpur.service.StordocService;
import org.springframework.stereotype.Service;

@Service
public class StordocServiceImpl extends ServiceImpl<StordocMapper, StordocEntity> implements StordocService {
}
