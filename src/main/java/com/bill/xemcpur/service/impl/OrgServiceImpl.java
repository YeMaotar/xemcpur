package com.bill.xemcpur.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.xemcpur.entity.OrgEntity;
import com.bill.xemcpur.mapper.OrgMapper;
import com.bill.xemcpur.service.OrgService;
import org.springframework.stereotype.Service;

@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, OrgEntity> implements OrgService {
}
