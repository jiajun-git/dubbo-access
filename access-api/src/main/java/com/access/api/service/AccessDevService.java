package com.access.api.service;

import com.access.api.entity.PcDevInfo;
import com.access.api.model.AccessDevInitDto;

import java.util.List;

public interface AccessDevService {

    /**
     * @author: sjj
     * @description: 门禁签到根据控制器编号查询设备编号（拼接设备编号）
     * @create: 2021-01-08
     **/
    List<AccessDevInitDto> getDevInitDate(String devCode);


    /**
     * @author: sjj
     * @description: 根据控制器编号及mac地址查询设备id
     * @create: 2021-01-08
     **/
    Integer getDevIdByDevCodeAndMac(String devCode,Integer mac);

    /**
     * @author: sjj
     * @description: 根据控制器id查询控制器信息
     * @create: 2021-01-15
     **/
    PcDevInfo getPcDevInfo(Integer id);

}
