package com.bill.xemcpur.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.xemcpur.entity.PsonEntity;
import com.bill.xemcpur.mapper.PsonMapper;
import com.bill.xemcpur.service.PsonService;
import org.springframework.stereotype.Service;

@Service
public class PsonServiceImpl extends ServiceImpl<PsonMapper, PsonEntity> implements PsonService {
}
