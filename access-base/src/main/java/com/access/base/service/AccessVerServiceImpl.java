package com.access.base.service;

import com.access.api.model.HeartBeatDto;
import com.access.api.service.AccessVerService;
import com.access.base.exception.BusinessException;
import com.access.base.mapper.PkgAccessDevMapper;
import com.alibaba.druid.util.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: sjj
 * @description: 门禁心跳实现
 * @create: 2021-01-13
 **/

@Service(version = "1.0.0")
public class AccessVerServiceImpl implements AccessVerService {

    @Autowired
    PkgAccessDevMapper pkgAccessDevMapper;

    @Override
    public HeartBeatDto queryAccessDevVer(Integer devId) {
        Map<String, Object> inMap = new HashMap<>();
        inMap.put("v_dev_id", devId);
        try {
            pkgAccessDevMapper.callHeartbeat(inMap);
        } catch (Exception ex) {
            throw new BusinessException("数据库操作执行异常");
        }
        Integer code = (Integer) inMap.get("err_code");
        String message = (String) inMap.get("err_msg");
        if (null == code || code.intValue() != 0) {
            throw new BusinessException(StringUtils.isEmpty(message) ? "未知异常" : message);
        }
        int cv = (int) inMap.getOrDefault("o_cust_ver", 0);
        int gv = (int) inMap.getOrDefault("o_group_ver", 0);
        int cgv = (int) inMap.getOrDefault("o_custgroup_ver", 0);
        int cpv = (int) inMap.getOrDefault("o_custplan_ver", 0);
        int av = (int) inMap.getOrDefault("o_aceplan_ver", 0);
        int pv = (int) inMap.getOrDefault("o_param_ver", 0);
        HeartBeatDto heartbeatVo = new HeartBeatDto();
        heartbeatVo.setAceplanVer(av);
        heartbeatVo.setGroupVer(gv);
        heartbeatVo.setCustgroupVer(cgv);
        heartbeatVo.setParamVer(pv);
        heartbeatVo.setCustplanVer(cpv);
        heartbeatVo.setCustVer(cv);
        return heartbeatVo;
    }
}
