package com.access.api.service;

import com.access.api.model.AccessCustDto;
import com.access.api.model.AccessCustGroupDto;
import com.access.api.model.AccessGroupDto;
import com.access.api.model.AccessTemporaryCustDto;

import java.util.List;

public interface AccessAuthService {

    /**
     * @author: sjj
     * @description: 门禁获取临时人员授权名单
     * @create: 2021-01-19
     **/

    List<AccessTemporaryCustDto> getTemporaryCustList();

    /**
     * @author: sjj
     * @description: 门禁获取权限组
     * @create: 2021-01-22
     **/
    List<AccessGroupDto> getAccessGroupList(Integer devId);

    /**
     * @author: sjj
     * @description: 增量获取人员名单
     * @create: 2021-01-25
     **/
    List<AccessCustDto> getAccessCustList(Integer ver,Integer maxVer);

    /**
     * @author: sjj
     * @description: 全量获取人员名单
     * @create: 2021-01-25
     **/
    List<AccessCustDto> getAccessCustWholeList(Integer ver,Integer maxVer);

    /**
     * @author: sjj
     * @description: 增量获取人组关系
     * @create: 2021-01-25
     **/
    List<AccessCustGroupDto> getAccessCustGroupList(Integer ver,Integer maxVer);

    /**
     * @author: sjj
     * @description: 全量获取人组关系
     * @create: 2021-01-25
     **/
    List<AccessCustGroupDto> getAccessCustGroupWholeList(Integer ver,Integer maxVer);

    /**
     * @author: sjj
     * @description: 根据id修改临时人员名单下发状态
     * @create: 2021-01-26
     **/
    Integer updateCustSendStatus(List<Integer> idList);
}
