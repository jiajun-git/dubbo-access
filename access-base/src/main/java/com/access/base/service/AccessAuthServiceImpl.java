package com.access.base.service;

import com.access.api.model.AccessTemporaryCustDto;
import com.access.api.service.AccessAuthService;
import com.access.base.mapper.AccessAuthMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: sjj
 * @description: 门禁授权相关方法实现
 * @create: 2021-01-08
 **/

@Service(version = "1.0.0")
public class AccessAuthServiceImpl implements AccessAuthService {

    @Autowired
    AccessAuthMapper accessAuthMapper;

    @Override
    public List<AccessTemporaryCustDto> getTemporaryCustList() {
        List<AccessTemporaryCustDto> result = accessAuthMapper.getTemporaryCustList();
        return result;
    }
}
