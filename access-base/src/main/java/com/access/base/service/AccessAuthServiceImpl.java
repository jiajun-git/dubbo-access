package com.access.base.service;

import com.access.api.model.AccessCustDto;
import com.access.api.model.AccessCustGroupDto;
import com.access.api.model.AccessGroupDto;
import com.access.api.model.AccessTemporaryCustDto;
import com.access.api.service.AccessAuthService;
import com.access.base.mapper.AccessAuthMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: sjj
 * @description: 门禁授权相关方法实现
 * @create: 2021-01-08
 **/

@Service(version = "1.0.0")
public class AccessAuthServiceImpl implements AccessAuthService {

    @Autowired
    AccessAuthMapper accessAuthMapper;

    @Override
    public List<AccessTemporaryCustDto> getTemporaryCustList() {
        List<AccessTemporaryCustDto> result = accessAuthMapper.getTemporaryCustList();
        return result;
    }

    @Override
    public List<AccessGroupDto> getAccessGroupList(Integer devId) {
        List<AccessGroupDto> result = accessAuthMapper.getAccessGroupList(devId);
        return result;
    }

    @Override
    public List<AccessCustDto> getAccessCustList(Integer ver,Integer maxVer) {
        List<AccessCustDto> resule = accessAuthMapper.getAccessCustList(ver,maxVer);
        return resule;
    }

    @Override
    public List<AccessCustDto> getAccessCustWholeList(Integer ver, Integer maxVer) {
        List<AccessCustDto> resule = accessAuthMapper.getAccessCustWholeList(ver,maxVer);
        return resule;
    }

    @Override
    public List<AccessCustGroupDto> getAccessCustGroupList(Integer ver,Integer maxVer) {
        List<AccessCustGroupDto> result = accessAuthMapper.getAccessCustGroupList(ver,maxVer);
        return result;
    }

    @Override
    public List<AccessCustGroupDto> getAccessCustGroupWholeList(Integer ver, Integer maxVer) {
        List<AccessCustGroupDto> result = accessAuthMapper.getAccessCustGroupWholeList(ver,maxVer);
        return result;
    }

    @Override
    public Integer updateCustSendStatus(List<Integer> idList) {
        Integer result = accessAuthMapper.updateCustSendStatus(idList);
        return result;
    }
}
