package com.access.base.mapper;


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
}
