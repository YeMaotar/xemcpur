package com.bill.xemcpur.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.xemcpur.entity.LogEntity;
import com.bill.xemcpur.mapper.LogMapper;
import com.bill.xemcpur.service.LogSerivce;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogSerivce {
}
