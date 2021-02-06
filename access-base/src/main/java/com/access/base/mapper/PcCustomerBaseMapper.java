package com.access.base.mapper;

import com.access.api.entity.PcCustomerBase;
import com.access.api.model.AccessCustBaseDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;


public interface PcCustomerBaseMapper extends Mapper<PcCustomerBase> {
    AccessCustBaseDto getCustDataByCustCode(@Param("custCode") String custCode);
}