package com.access.communication.netty.dto.userplan;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPlanDetailDto implements Serializable {

    private Integer w;

    private Integer bh;

    private Integer bm;

    private Integer eh;

    private Integer em;
}
