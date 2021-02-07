package com.access.communication.netty.dto.custgroup;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustGroupReplyDto implements Serializable {

    /**
     * 门编号
     */
    private String devSn;

    /**
     * 帧序号
     */
    private String seq;

    /**
     * 人组关系
     */
    private DevCustGroupDto devCustGroupDto;

}
