package com.access.base.service;

import com.access.api.model.AccessDevInitDto;
import com.access.api.service.AccessDevService;
import com.access.base.mapper.DrDevDoorMapper;
import com.access.base.mapper.PcDevInfoMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: sjj
 * @description: 门禁设备相关方法实现
 * @create: 2021-01-08
 **/

@Service
public class AccessDevServiceImpl implements AccessDevService {

    @Autowired
    private DrDevDoorMapper drDevDoorMapper;

    @Autowired
    private PcDevInfoMapper pcDevInfoMapper;

    @Override
    public List<AccessDevInitDto> getDevInitDate(String devCode) {

        List<AccessDevInitDto> result = drDevDoorMapper.getDevInitDate(devCode);
        return result;
    }

    @Override
    public Integer getDevIdByDevCodeAndMac(String devCode, Integer mac) {
        Integer result = drDevDoorMapper.getDevIdByDevCodeAndMac(devCode, mac);
        return result;
    }
}
