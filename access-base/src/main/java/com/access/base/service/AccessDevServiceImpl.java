package com.access.base.service;

import com.access.api.entity.DrDevDoor;
import com.access.api.entity.PcDevInfo;
import com.access.api.model.AccessDevInitDto;
import com.access.api.service.AccessDevService;
import com.access.base.mapper.DrDevDoorMapper;
import com.access.base.mapper.PcDevInfoMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: sjj
 * @description: 门禁设备相关方法实现
 * @create: 2021-01-08
 **/

@Service(version = "1.0.0")
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
    public DrDevDoor getDevInfoByDevCodeAndMac(String devCode, Integer mac) {
        DrDevDoor result = drDevDoorMapper.getDevInfoByDevCodeAndMac(devCode, mac);
        return result;
    }

    @Override
    public PcDevInfo getPcDevInfo(Integer id) {
        PcDevInfo pcDevInfo = new PcDevInfo();
        pcDevInfo.setId(id);
        PcDevInfo result = pcDevInfoMapper.selectOne(pcDevInfo);
        return result;
    }
}
