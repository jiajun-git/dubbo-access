package com.access.communication.netty.dto.visitor;

import lombok.Data;

import java.io.Serializable;

@Data
public class VisitorReplyDto implements Serializable {

    /**
     * 门编号
     */
    private String devSn;

    /**
     * 帧序号
     */
    private String seq;

    /**
     * 临时名单
     */
    private DevVisitorDto devVisitorDto;

}
