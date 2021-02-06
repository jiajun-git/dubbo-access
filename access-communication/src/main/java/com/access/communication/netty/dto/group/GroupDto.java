package com.access.communication.netty.dto.group;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupDto implements Serializable {

    private Integer plan_id;

    private Integer group_id;

    private Integer open_type;

}
