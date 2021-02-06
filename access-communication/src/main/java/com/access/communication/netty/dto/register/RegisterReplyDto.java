package com.access.communication.netty.dto.register;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RegisterReplyDto implements Serializable {

    private Integer status = 0;

    private String message = "SUCCESS";

    private List<DoorInfoDto> door_array;
}
