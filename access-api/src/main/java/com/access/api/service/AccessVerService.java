package com.access.api.service;

import com.access.api.model.HeartBeatDto;

public interface AccessVerService {

    /**
     * @author: sjj
     * @description: 门禁签到根据设备id查询各个版本号
     * @create: 2021-01-08
     **/
    HeartBeatDto queryAccessDevVer(Integer devId);

}
