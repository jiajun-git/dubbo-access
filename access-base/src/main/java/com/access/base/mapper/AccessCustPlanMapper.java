package com.access.base.mapper;


import com.access.api.model.AccessCustPlanDto;

import java.util.List;

/**
 * @Author：sjj
 * @Description:
 * @Date: Created at 2021/01/9
 * @Moditify By:
 */

public interface AccessCustPlanMapper {


    List<AccessCustPlanDto> queryPlanList(Integer ver);
}
