package com.access.communication.netty.dto.heartbeat;

import lombok.Data;

import java.io.Serializable;

@Data
public class HeartbeatDto implements Serializable {

    private String dev_sn;

    private Integer timeout_limit;
}
