package com.access.communication.netty.dto.userplan;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DevUserPlanDto implements Serializable {

    private String dev_sn;

    private Integer is_end;

    private Integer max_ver;

    private List<UserPlanDto> plan_arr;
}
