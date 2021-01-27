package com.access.base.mapper;


import com.access.api.model.AccessCustDto;
import com.access.api.model.AccessCustGroupDto;
import com.access.api.model.AccessGroupDto;
import com.access.api.model.AccessTemporaryCustDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author：sjj
 * @Description:
 * @Date: Created at 2021/01/19
 * @Moditify By:
 */

public interface AccessAuthMapper {


    List<AccessTemporaryCustDto> getTemporaryCustList();

    List<AccessGroupDto> getAccessGroupList(@Param("devId")Integer devId);

    List<AccessCustDto> getAccessCustList(@Param("ver")Integer ver,@Param("maxVer")Integer maxVer);

    List<AccessCustDto> getAccessCustWholeList(@Param("ver")Integer ver, @Param("maxVer")Integer maxVer);

    List<AccessCustGroupDto> getAccessCustGroupList(@Param("ver")Integer ver, @Param("maxVer")Integer maxVer);

    List<AccessCustGroupDto> getAccessCustGroupWholeList(@Param("ver")Integer ver, @Param("maxVer")Integer maxVer);

    Integer updateCustSendStatus(@Param("idList")List<Integer> idList);
}
