package com.access.base.service;

import com.access.api.model.AccessCustBaseDto;
import com.access.base.mapper.PcCustomerBaseMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sjj
 * @description: 门禁授权相关方法实现
 * @create: 2021-01-08
 **/

@Service(version = "1.0.0")
public class AccessCustBaseService {

    @Autowired
    PcCustomerBaseMapper pcCustomerBaseMapper;

    public AccessCustBaseDto getCustDataByCustCode(String custCode){
        AccessCustBaseDto accessCustBaseDto = pcCustomerBaseMapper.getCustDataByCustCode(custCode);
        return accessCustBaseDto;

    }


}
