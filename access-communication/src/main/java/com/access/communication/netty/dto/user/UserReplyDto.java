package com.access.communication.netty.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserReplyDto implements Serializable {

    /**
     * 门编号
     */
    private String devSn;

    /**
     * 帧序号
     */
    private String seq;

    /**
     * 用户名单
     */
    private DevUserDto devUserDto;

}
