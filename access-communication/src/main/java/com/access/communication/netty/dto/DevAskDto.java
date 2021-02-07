package com.access.communication.netty.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DevAskDto implements Serializable {

    private String dev_sn;

    private Integer sign;
}
