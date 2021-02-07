package com.access.communication.netty.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DevReceiveDto implements Serializable {

    /**
     * 功能码
     */
    private String type;

    /**
     * json数据
     */
    private String data;


}
