package com.access.communication.netty.dto.heartbeat;

import lombok.Data;

import java.io.Serializable;

@Data
public class DoorVerDto implements Serializable {

    private String dev_sn;
    private Integer group_ver;
    private Integer custplan_ver;
    private Integer aceplan_ver;
    private Integer param_ver;

}
