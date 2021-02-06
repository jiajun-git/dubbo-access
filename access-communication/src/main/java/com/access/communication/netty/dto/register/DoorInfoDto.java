package com.access.communication.netty.dto.register;

import lombok.Data;

import java.io.Serializable;

@Data
public class DoorInfoDto implements Serializable {

    private Integer mac;

    private String dev_sn;

}
