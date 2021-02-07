package com.access.communication.netty.dto.group;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DevGroupDto implements Serializable {

    private String dev_sn;

    private Integer is_end;

    private Integer max_ver;

    private List<GroupDto> group_arr;
}
