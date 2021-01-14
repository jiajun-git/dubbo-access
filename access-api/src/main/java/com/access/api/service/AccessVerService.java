package com.access.api.service;

import com.access.api.model.HeartBeatDto;

public interface AccessVerService {

    /**
     * @author: sjj
     * @description: 门禁签到根据控制器编号查询设备编号（拼接设备编号）
     * @create: 2021-01-08
     **/
    HeartBeatDto queryAccessDevVer(Integer devId);

}
