package com.access.api.service;

import com.access.api.model.AccessTemporaryCustDto;

import java.util.List;

public interface AccessAuthService {

    /**
     * @author: sjj
     * @description: 门禁获取临时人员授权名单
     * @create: 2021-01-19
     **/

    List<AccessTemporaryCustDto> getTemporaryCustList();
}
