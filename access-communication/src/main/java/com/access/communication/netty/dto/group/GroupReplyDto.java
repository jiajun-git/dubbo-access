package com.access.communication.netty.dto.group;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupReplyDto implements Serializable {

    /**
     * 门编号
     */
    private String devSn;

    /**
     * 帧序号
     */
    private String seq;

    /**
     * 权限组
     */
    private DevGroupDto devGroupDto;

}
