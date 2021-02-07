package com.access.communication.netty.udp.task;

import com.access.communication.netty.dto.visitor.VisitorReplyDto;
import com.access.communication.netty.udp.handler.AccessChannelHandler;
import com.access.communication.service.AccessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ScheduleTask {

    @Autowired
    private AccessService accessService;

    private static ConcurrentHashMap<String, String> lastSendMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, Integer> retryNumMap = new ConcurrentHashMap<>();

    @Scheduled(fixedDelay =5000)
    public void retryTask() {
        Map<String, String> currentSendMap = accessService.getSendingData();
        lastSendMap.forEach((address, data) -> {
            if(null != currentSendMap.get(address) && StringUtils.equals(data, currentSendMap.get(address))){
                Integer retryNum = retryNumMap.get(address) == null ? 0 : retryNumMap.get(address);
                if(retryNum <= 3){
                    log.error("下发中断，第{}次重试", retryNum + 1);
                    accessService.resendData(address, data);
                    retryNumMap.put(address, retryNum + 1);
                }
            }
        });
        lastSendMap.putAll(currentSendMap);


    }

    @Scheduled(fixedDelay =5000)
    public void visitorTask() {
        Map<String, LinkedList<VisitorReplyDto>> visitorDevMap = accessService.getVisitorList();
        for (String devSn : visitorDevMap.keySet()) {
            accessService.sendVisitor(devSn, visitorDevMap.get(devSn));
        }
    }

}
