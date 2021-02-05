package com.access.api.service;

import com.access.api.model.AccessRecordDto;

import java.util.List;

public interface AccessRecordService {

    /**
     * @author: sjj
     * @description: 门禁刷卡记录入库
     * @create: 2021-02-04
     **/
    Integer insertAccessRecord(List<AccessRecordDto> recordList);
}
