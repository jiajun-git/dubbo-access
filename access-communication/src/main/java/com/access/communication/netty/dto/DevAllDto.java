package com.access.communication.netty.dto;

import com.access.communication.netty.dto.custgroup.CustGroupReplyDto;
import com.access.communication.netty.dto.group.GroupReplyDto;
import com.access.communication.netty.dto.heartbeat.HeartbeatReplyDto;
import com.access.communication.netty.dto.user.UserReplyDto;
import com.access.communication.netty.dto.userplan.UserPlanReplyDto;
import com.access.communication.netty.dto.visitor.VisitorReplyDto;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;

@Data
public class DevAllDto implements Serializable {

    /**
     * 门禁控制器编号
     */
    private String devSn;

    /**
     * 门禁控制器通信地址
     */
    private String address;

    /**
     * 心跳响应
     */
    private HeartbeatReplyDto heartbeatReplyDto;

    /**
     * 待下发用户计划
     */
    private LinkedList<UserPlanReplyDto> userPlanReplyList;

    /**
     * 待下发权限组
     */
    private LinkedList<GroupReplyDto> groupReplyList;

    /**
     * 待下发人员名单
     */
    private LinkedList<UserReplyDto> userReplyList;

    /**
     * 待下发人组关系
     */
    private LinkedList<CustGroupReplyDto> custGroupReplyList;

    /**
     * 待下发访客
     */
    private LinkedList<VisitorReplyDto> visitorReplyList;

    /**
     * 人员名单同步类型0-增量 1-全量
     */
    private Integer userSynType;

    /**
     * 人组关系同步类型0-增量 1-全量
     */
    private Integer custGroupSynType;
}
