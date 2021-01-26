package com.access.api.service;

import com.access.api.model.AccessCustPlanDto;

import java.util.List;

public interface AccessCustPlanService {

    /**
     * @author: sjj
     * @description: 门禁全量获取用户计划接口
     * @create: 2021-01-13
     **/
    List<AccessCustPlanDto> queryPlanList(Integer ver);


}
