package com.access.base.service;

import com.access.api.entity.*;
import com.access.api.model.AccessCustBaseDto;
import com.access.api.model.AccessRecordDto;
import com.access.api.service.AccessRecordService;
import com.access.base.exception.BusinessException;
import com.access.base.mapper.*;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author: sjj
 * @description: 门禁刷卡记录方法实现
 * @create: 2021-02-05
 **/

@Service(version = "1.0.0")
public class AccessRecordServiceImpl implements AccessRecordService {

    @Autowired
    PcDevInfoMapper pcDevInfoMapper;

    @Autowired
    DrDevDoorMapper drDevDoorMapper;
    @Autowired
    AccessCustBaseService accessCustBaseService;
    @Autowired
    DrCardEventMapper drCardEventMapper;
    @Autowired
    PcCardEventMapper pcCardEventMapper;
    @Autowired
    DrCardPassagewayMapper drCardPassagewayMapper;
    @Autowired
    DrCardLawlessMapper drCardLawlessMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertAccessRecord(List<AccessRecordDto> recordList) {

        Map<String, List<AccessRecordDto>> map = recordList.stream().collect(groupingBy(AccessRecordDto::getDev_sn));
        PcDevInfo pcDevInfo = new PcDevInfo();

        Integer result = 1;
        for (List<AccessRecordDto> valueList : map.values()) {

            for (AccessRecordDto accessRecordDto : valueList) {
                String devCode = accessRecordDto.getDev_sn().substring(0, accessRecordDto.getDev_sn().length() - 1);
                Integer mac = Integer.valueOf(accessRecordDto.getDev_sn().substring(accessRecordDto.getDev_sn().length() - 1));
                //根据门禁控制器编号查询门禁控制器信息
                pcDevInfo.setDevcode(devCode);
                pcDevInfo = pcDevInfoMapper.selectOne(pcDevInfo);
                //查询设备信息
                DrDevDoor drDevDoor = drDevDoorMapper.getDevInfoByDevCodeAndMac(devCode, mac);

                //查询人员信息
                AccessCustBaseDto accessCustBaseDto = accessCustBaseService.getCustDataByCustCode(accessRecordDto.getCust_code());

                if (accessRecordDto.getIs_lawful() == 0) {
                    //合法记录
                    if (pcDevInfo.getDevtypeid() == 5) {
                        //通道记录入通道记录表
                        DrCardPassageway drCardPassageway = new DrCardPassageway();
                        drCardPassageway.setChanneldirection(drDevDoor.getDirection());
                        drCardPassageway.setCardno(accessRecordDto.getCard_no());
                        drCardPassageway.setChannelmode(2);
                        drCardPassageway.setIstail(0);
                        drCardPassageway.setDevid(pcDevInfo.getId());
                        drCardPassageway.setDooraddr(mac);
                        drCardPassageway.setEventtime(accessRecordDto.getTime());
                        drCardPassageway.setCollecttime(new Date());
                        drCardPassageway.setWritetime(new Date());
                        drCardPassageway.setIslawless(0);
                        drCardPassageway.setCustid(accessCustBaseDto.getCustId());
                        drCardPassageway.setCustname(accessCustBaseDto.getCustName());
                        drCardPassageway.setDeptid(accessCustBaseDto.getDeptId());
                        drCardPassageway.setDevname(accessCustBaseDto.getDeptName());
                        drCardPassageway.setDevname(pcDevInfo.getDevname());
                        drCardPassageway.setWorkno(accessCustBaseDto.getWorkNo());
                        drCardPassageway.setDeptcode(accessCustBaseDto.getDeptCode());
                        result = drCardPassagewayMapper.insertSelective(drCardPassageway);
                        if (result <= 0) {
                            throw new BusinessException("数据库操作执行异常");
                        }

                    } else {
                        //门禁记录入门禁记录表
                        DrCardEvent drCardEvent = new DrCardEvent();
                        drCardEvent.setDevid(pcDevInfo.getId());
                        drCardEvent.setDooraddr(mac);
                        drCardEvent.setCardno(accessRecordDto.getCard_no());
                        drCardEvent.setEventtime(accessRecordDto.getTime());
                        drCardEvent.setCollecttime(new Date());
                        drCardEvent.setWritetime(new Date());
                        drCardEvent.setRecflag(0);
                        drCardEvent.setCollectid(0);
                        drCardEvent.setCustid(accessCustBaseDto.getCustId());
                        drCardEvent.setCustname(accessCustBaseDto.getCustName());
                        drCardEvent.setDeptid(accessCustBaseDto.getDeptId());
                        drCardEvent.setDevname(accessCustBaseDto.getDeptName());
                        drCardEvent.setDevname(pcDevInfo.getDevname());
                        drCardEvent.setWorkno(accessCustBaseDto.getWorkNo());
                        drCardEvent.setDeptcode(accessCustBaseDto.getDeptCode());
                        drCardEvent.setIsfacerecord(0);
                        drCardEvent.setDirection(drDevDoor.getDirection());
                        result = drCardEventMapper.insertSelective(drCardEvent);
                        if (result <= 0) {
                            throw new BusinessException("数据库操作执行异常");
                        }
                    }

                    //是否为考勤设备,若为考勤设备记录同时入考勤记录表
                    if (pcDevInfo.getFunctiontype() == 1) {
                        PcCardEvent pcCardEvent = new PcCardEvent();
                        pcCardEvent.setDevid(pcDevInfo.getId());
                        pcCardEvent.setDooraddr(mac);
                        pcCardEvent.setCardno(accessRecordDto.getCard_no());
                        pcCardEvent.setEventtime(accessRecordDto.getTime());
                        pcCardEvent.setCollecttime(new Date());
                        pcCardEvent.setWritetime(new Date());
                        pcCardEvent.setRecflag(0);
                        pcCardEvent.setCollectid(0);
                        pcCardEvent.setCustid(accessCustBaseDto.getCustId());
                        pcCardEvent.setCustname(accessCustBaseDto.getCustName());
                        pcCardEvent.setDeptid(accessCustBaseDto.getDeptId());
                        pcCardEvent.setDevname(accessCustBaseDto.getDeptName());
                        pcCardEvent.setDevname(pcDevInfo.getDevname());
                        pcCardEvent.setWorkno(accessCustBaseDto.getWorkNo());
                        pcCardEvent.setDeptcode(accessCustBaseDto.getDeptCode());
                        pcCardEvent.setIsfacerecord(0);
                        pcCardEvent.setDirection(drDevDoor.getDirection());
                        result = pcCardEventMapper.insertSelective(pcCardEvent);
                        if (result <= 0) {
                            throw new BusinessException("数据库操作执行异常");
                        }
                    }

                } else {
                    //非法记录
                    DrCardLawless drCardLawless = new DrCardLawless();
                    drCardLawless.setDevid(pcDevInfo.getId());
                    drCardLawless.setDooraddr(mac);
                    drCardLawless.setCardno(accessRecordDto.getCard_no());
                    drCardLawless.setEventtime(accessRecordDto.getTime());
                    drCardLawless.setCollecttime(new Date());
                    drCardLawless.setWritetime(new Date());
                    drCardLawless.setRecflag(0);
                    drCardLawless.setCollectid(0);
                    drCardLawless.setCustid(accessCustBaseDto == null? null :accessCustBaseDto.getCustId());
                    drCardLawless.setCustname(accessCustBaseDto == null? null :accessCustBaseDto.getCustName());
                    drCardLawless.setDeptid(accessCustBaseDto == null? null :accessCustBaseDto.getDeptId());
                    drCardLawless.setDevname(accessCustBaseDto == null? null :accessCustBaseDto.getDeptName());
                    drCardLawless.setDevname(pcDevInfo.getDevname());
                    drCardLawless.setWorkno(accessCustBaseDto == null? null :accessCustBaseDto.getWorkNo());
                    drCardLawless.setDeptcode(accessCustBaseDto == null? null :accessCustBaseDto.getDeptCode());
                    drCardLawless.setIsfacerecord(0);
                    drCardLawless.setDirection(drDevDoor.getDirection());
                    result = drCardLawlessMapper.insertSelective(drCardLawless);
                    if (result <= 0) {
                        throw new BusinessException("数据库操作执行异常");
                    }

                }


            }
        }
        return result;
    }
}
