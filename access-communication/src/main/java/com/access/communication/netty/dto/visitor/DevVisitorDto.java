package com.access.communication.netty.dto.visitor;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DevVisitorDto implements Serializable {

    private String dev_sn;

    private Integer is_end;

    private List<VisitorDto> cust_arr;
}
