package com.access.communication.netty.dto.userplan;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserPlanDto implements Serializable {

    private Integer pi;

    private List<UserPlanDetailDto> pd;
}
