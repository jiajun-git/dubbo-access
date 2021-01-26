package com.access.base.mapper;


import com.access.api.model.AccessCustDto;
import com.access.api.model.AccessCustGroupDto;
import com.access.api.model.AccessGroupDto;
import com.access.api.model.AccessTemporaryCustDto;

import java.util.List;

/**
 * @Author：sjj
 * @Description:
 * @Date: Created at 2021/01/19
 * @Moditify By:
 */

public interface AccessAuthMapper {


    List<AccessTemporaryCustDto> getTemporaryCustList();

    List<AccessGroupDto> getAccessGroupList(Integer devId);

    List<AccessCustDto> getAccessCustList(Integer ver,Integer maxVer);

    List<AccessCustDto> getAccessCustWholeList(Integer ver, Integer maxVer);

    List<AccessCustGroupDto> getAccessCustGroupList(Integer ver, Integer maxVer);

    List<AccessCustGroupDto> getAccessCustGroupWholeList(Integer ver, Integer maxVer);
}
