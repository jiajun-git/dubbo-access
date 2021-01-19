package com.access.base.service;

import com.access.api.model.AccessCustPlanDto;
import com.access.api.service.AccessCustPlanService;
import com.access.base.mapper.AccessCustPlanMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: sjj
 * @description: 门禁用户计划实现
 * @create: 2021-01-13
 **/

@Service(version = "1.0.0")
public class AccessCustPlanServiceImpl implements AccessCustPlanService {

    @Autowired
    AccessCustPlanMapper accessCustPlanMapper;

    @Override
    public List<AccessCustPlanDto> queryPlanList() {
        List<AccessCustPlanDto> result = accessCustPlanMapper.queryPlanList();
        return result;
    }
}
