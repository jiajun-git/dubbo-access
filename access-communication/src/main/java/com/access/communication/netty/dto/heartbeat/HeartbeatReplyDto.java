package com.access.communication.netty.dto.heartbeat;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HeartbeatReplyDto implements Serializable {

    private Integer status = 0;

    private String message = "SUCCESS";

    private List<HeartbeatDto> door_array;

    private Long sys_time;

}
