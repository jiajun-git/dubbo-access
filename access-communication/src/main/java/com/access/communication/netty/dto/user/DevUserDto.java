package com.access.communication.netty.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DevUserDto implements Serializable {

    private String dev_sn;

    private Integer is_end;

    private Integer max_ver;

    private List<UserDto> cust_arr;
}
