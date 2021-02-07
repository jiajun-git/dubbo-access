package com.access.communication.netty.dto.userplan;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPlanReplyDto implements Serializable {

    /**
     * 门编号
     */
    private String devSn;

    /**
     * 帧序号
     */
    private String seq;

    /**
     * 用户计划
     */
    private DevUserPlanDto devUserPlanDto;

}
